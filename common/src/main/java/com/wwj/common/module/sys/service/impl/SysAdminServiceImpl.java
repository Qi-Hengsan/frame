package com.wwj.common.module.sys.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wwj.authority.util.JwtTokenUtil;
import com.wwj.core.exception.GlobalException;
import com.wwj.core.pagination.PageInfo;
import com.wwj.core.pagination.Paging;
import com.wwj.core.service.impl.BaseServiceImpl;
import com.wwj.common.enums.SaveEnum;
import com.wwj.common.module.sys.VO.*;
import com.wwj.common.module.sys.VO.query.OrgInfoQueryVO;
import com.wwj.common.module.sys.VO.query.SysAdminQueryVO;
import com.wwj.common.module.sys.domain.AdminUserDetails;
import com.wwj.common.module.sys.entity.*;
import com.wwj.common.module.sys.mapper.*;
import com.wwj.common.module.sys.param.SysAdminBaseParam;
import com.wwj.common.module.sys.param.SysAdminPageParam;
import com.wwj.common.module.sys.param.SysAdminUpdatePasswdParam;
import com.wwj.common.module.sys.param.UpdateStatusParam;
import com.wwj.common.module.sys.service.SysAdminService;
import com.wwj.common.utils.PojoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 后台用户表 服务实现类
 *
 * @author Zhutaosong
 * @since 2021-06-01
 */
@Slf4j
@Service
public class SysAdminServiceImpl
    extends BaseServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Autowired
    private SysAdminMapper sysAdminMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SysAdminLoginLogMapper loginLogMapper;
    @Autowired
    private SysAdminOrgRelationMapper sysAdminOrgRelationMapper;
    @Autowired
    private SysAdminRoleRelationMapper sysAdminRoleRelationMapper;
    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveSysAdmin(SysAdminBaseParam param) throws Exception {
        //新增用户时判断账号和身份证号是否重复
        SysAdmin sysAdminUserName = sysAdminMapper.selectOne(new LambdaQueryWrapper<SysAdmin>()
                .eq(SysAdmin::getUsername,param.getUsername()));
        SysAdmin sysAdminIdCard = sysAdminMapper.selectOne(new LambdaQueryWrapper<SysAdmin>()
                .eq(SysAdmin::getIdCard,param.getIdCard()));
        if(ObjectUtil.isNotNull(sysAdminUserName)){
            return SaveEnum.USERNAME_REPEAT.getId();
        }
        if(ObjectUtil.isNotNull(sysAdminIdCard)){
            return SaveEnum.CARD_REPEAT.getId();
        }
        SysAdmin sysAdmin = PojoUtils.convertOne(param, SysAdmin.class);
        sysAdmin.setPassword(passwordEncoder.encode("12345678"));
        boolean flag = super.save(sysAdmin);
        for (int i = 0; i < param.getDepartment().length; i++) {
            SysAdminOrgRelation sysAdminOrgRelation = new SysAdminOrgRelation();
            sysAdminOrgRelation.setOrgInfoId(param.getDepartment()[i]);
            sysAdminOrgRelation.setSysAdminId(sysAdmin.getId());
            sysAdminOrgRelation.setSort(i);
            sysAdminOrgRelationMapper.insert(sysAdminOrgRelation);
        }
        if(ObjectUtil.isNotNull(param.getRole())){
            for (int i : param.getRole()) {
                SysAdminRoleRelation sysAdminRoleRelation = new SysAdminRoleRelation();
                sysAdminRoleRelation.setAdminId(sysAdmin.getId());
                sysAdminRoleRelation.setRoleId(i);
                sysAdminRoleRelationMapper.insert(sysAdminRoleRelation);
            }
        }
        updateRoleNum();
        return flag?SaveEnum.SAVE_SUCCESS.getId():SaveEnum.SAVE_FALSE.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSysAdmin(SysAdminBaseParam param) throws Exception {
        SysAdminQueryVO sysAdminById = sysAdminMapper.getSysAdminById(param.getId());
        SysAdmin sysAdmin = PojoUtils.convertOne(param, SysAdmin.class);
        sysAdmin.setPassword(sysAdminById.getPassword());
        boolean flag = super.updateById(sysAdmin);
        sysAdminOrgRelationMapper.delete(new LambdaQueryWrapper<SysAdminOrgRelation>()
                .eq(SysAdminOrgRelation::getSysAdminId, sysAdmin.getId()));
        for (int i = 0; i < param.getDepartment().length; i++) {
            SysAdminOrgRelation sysAdminOrgRelation = new SysAdminOrgRelation();
            sysAdminOrgRelation.setOrgInfoId(param.getDepartment()[i]);
            sysAdminOrgRelation.setSysAdminId(sysAdmin.getId());
            sysAdminOrgRelation.setSort(i);
            sysAdminOrgRelationMapper.insert(sysAdminOrgRelation);
        }
        sysAdminRoleRelationMapper.delete(new LambdaQueryWrapper<SysAdminRoleRelation>()
                .eq(SysAdminRoleRelation::getAdminId, sysAdmin.getId()));
        for (int i : param.getRole()) {
            SysAdminRoleRelation sysAdminRoleRelation = new SysAdminRoleRelation();
            sysAdminRoleRelation.setAdminId(sysAdmin.getId());
            sysAdminRoleRelation.setRoleId(i);
            sysAdminRoleRelationMapper.insert(sysAdminRoleRelation);
        }
        updateRoleNum();
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteSysAdmin(Integer id) throws Exception {
        sysAdminOrgRelationMapper.delete(new LambdaQueryWrapper<SysAdminOrgRelation>()
                .eq(SysAdminOrgRelation::getSysAdminId, id));
        sysAdminRoleRelationMapper.delete(new LambdaQueryWrapper<SysAdminRoleRelation>()
                .eq(SysAdminRoleRelation::getAdminId, id));
        updateRoleNum();
        return super.removeById(id);
    }

    @Override
    public SysAdminInfoVO getSysAdminById(Integer id){
        SysAdminQueryVO sysAdminById = sysAdminMapper.getSysAdminById(id);
        SysAdminInfoVO result = PojoUtils.convertOne(sysAdminById, SysAdminInfoVO.class, "password");
        // 查询该用户的主部门
        Integer mainOrgId = orgInfoMapper.getMainOrgByUserId(id);
        result.setMainDepName(getPath(mainOrgId, StringUtils.EMPTY));
        // 查询所有该用户所有部门
        List<Integer> orgIdByUserId = orgInfoMapper.getOrgIdByUserId(id);
        List<SysAdminListOrgInfoVO> department = new ArrayList<>();
        for (Integer childId : orgIdByUserId) {
            List<OrgInfoQueryVO> childList = orgInfoMapper.getOrgInfoByChildId(childId);
            List<SysAdminListOrgInfoVO> childOrgInfo = generateOrgInfoTree(childList);
            department.addAll(childOrgInfo);
        }
        result.setDepartment(department);
        // 查询该用户得所有角色
        List<SysRole> roleList = sysRoleMapper.getRoleList(id);
        if(ObjectUtil.isNotEmpty(roleList)){
            List<SysAdminRoleInfoVO> roles = PojoUtils.convert(roleList, SysAdminRoleInfoVO.class);
            result.setRole(roles);
            result.setRoleName(roleList.get(0).getName());
        }
        return result;
    }
    private String getPath(Integer parentId, String result) {
        if (Objects.equals(parentId, 0)) {
            return result;
        }
        // 查询父节点
        OrgInfo supTagDef = orgInfoMapper.selectOne(new LambdaQueryWrapper<OrgInfo>()
                .eq(OrgInfo::getId, parentId));
        if (Objects.isNull(supTagDef)) {
            return "";
        }
        result = "/" + supTagDef.getOrgName() + result;
        return getPath(supTagDef.getParentId(), result);
    }

    @Override
    public Paging<SysAdminInfoVO> getSysAdminPageList(SysAdminPageParam param) throws Exception {
        param.setPageSorts(null);
        Page<SysAdminQueryVO> page = new PageInfo<>(param, null,null);
        IPage<SysAdminQueryVO> iPage = sysAdminMapper.getSysAdminPageList(page, param);
        List<SysAdminInfoVO> pageListVOS = PojoUtils.convert(iPage.getRecords(), SysAdminInfoVO.class);
        List<SysAdminInfoVO> result = new ArrayList<>();
        if(ObjectUtil.isNotNull(pageListVOS)){
            AtomicLong serNo = new AtomicLong(0);
            result = pageListVOS.stream().map(one -> {
                SysAdminInfoVO sysAdminById = getSysAdminById(one.getId());
                sysAdminById.setSerNo((param.getPageIndex() - 1) * param.getPageSize() + serNo.incrementAndGet());
                return sysAdminById;
            }).collect(Collectors.toList());
        }
        Page<SysAdminInfoVO> retPage = new Page<>(iPage.getCurrent(), iPage.getSize(), iPage.getTotal());
        retPage.setRecords(result);
        return new Paging<SysAdminInfoVO>(retPage);
    }

    @Override
    public SysAdmin getAdminByUsername(String username) {
        SysAdmin sysAdmin = baseMapper.selectOne(new LambdaQueryWrapper<SysAdmin>()
                .eq(SysAdmin::getUsername, username));
        if (ObjectUtil.isNotNull(sysAdmin)) {
            return sysAdmin;
        }
        return null;
    }

    @Override
    public AdminUserDetails loadUserByUsername(String username) {
        // 获取用户信息
        SysAdmin admin = getAdminByUsername(username);
        if (ObjectUtil.isNotNull(admin)) {
            List<SysRole> roleList = sysRoleMapper.getRoleList(admin.getId());
            AdminUserDetails adminUserDetails = new AdminUserDetails(admin, roleList);
            return adminUserDetails;
        }
        return null;
    }

    @Override
    public LoginUserInfoVO login(String username, String password) {
        // 对密码进行base64解码
        byte[] decode = Base64.getDecoder().decode(password);
        password = new String(decode, StandardCharsets.UTF_8);

        AdminUserDetails adminUserDetails = loadUserByUsername(username);
        if (Objects.isNull(adminUserDetails)) {
            return new LoginUserInfoVO();
        }
        if (!adminUserDetails.isEnabled()) {
            LoginUserInfoVO infoVO = new LoginUserInfoVO();
            infoVO.setToken("该账号被禁用");
            return infoVO;
        }
        if (!passwordEncoder.matches(password, adminUserDetails.getPassword())) {
            log.error("登录：密码不正确");
            return new LoginUserInfoVO();
        }
        return userInfoToken(adminUserDetails);
    }
    private LoginUserInfoVO userInfoToken(AdminUserDetails adminUserDetails){
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminUserDetails
                , null
                , adminUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateUserNameAndIdStr(adminUserDetails.getUsername(), adminUserDetails.getSysAdmin().getId());
        if (StrUtil.isBlank(token)) {
            return loginUserInfoVO;
        }
        loginUserInfoVO.setToken(token);
        loginUserInfoVO.setTokenHead(tokenHead);
        loginUserInfoVO.setName(adminUserDetails.getSysAdmin().getName());
        loginUserInfoVO.setUserId(adminUserDetails.getSysAdmin().getId());
        return loginUserInfoVO;
    }
    @Override
    public String getUserName(Integer id) {
        return sysAdminMapper.getUserNameById(id);
    }

    /**
     * 查询所有用户
     */
    @Override
    public List<SysAdminVO> getAllUserList() {
        List<SysAdmin> sysAdmins = sysAdminMapper.selectList(null);
        List<SysAdminVO> sysAdminVOS = PojoUtils.convert(sysAdmins, SysAdminVO.class);
        return sysAdminVOS;
    }

    @Override
    public List<SysAdminListOrgInfoVO> listOrgInfo() {
        List<OrgInfoQueryVO> list = orgInfoMapper.getOrgInfoList();
        List<SysAdminListOrgInfoVO> result = generateOrgInfoTree(list);
        return result;
    }

    @Override
    public List<SysAdminRoleInfoVO> listRoleInfo() {
        List<SysRole> allRoleList = sysRoleMapper.getAllRoleList();
        List<SysAdminRoleInfoVO> result = PojoUtils.convert(allRoleList, SysAdminRoleInfoVO.class);
        return result;
    }

    @Override
    public boolean updateStatus(UpdateStatusParam param) {
        boolean flag = sysAdminMapper.updateStatus(param.getId(), param.getStatus());
        return flag;
    }

    @Override
    public boolean updatePassword(SysAdminUpdatePasswdParam passwdParam) {
        if (!Objects.equals(passwdParam.getPasswd(), passwdParam.getConfirm())) {
            throw new GlobalException("两次密码不一致");
        }
        boolean flag = this.update(Wrappers.<SysAdmin>lambdaUpdate()
                .eq(SysAdmin::getId, passwdParam.getId())
                .set(true, SysAdmin::getPassword, passwordEncoder.encode(passwdParam.getPasswd())));
        return flag;
    }

    @Override
    public SysUpdateAdminInfoVO getUpdateAdminInfo(Integer id) {
        SysAdminQueryVO sysAdminById = sysAdminMapper.getSysAdminById(id);
        SysUpdateAdminInfoVO result = PojoUtils.convertOne(sysAdminById, SysUpdateAdminInfoVO.class, "password");
        // 查询该用户的所有部门
        List<SysAdminOrgRelation> orgRelations = sysAdminOrgRelationMapper.selectList(new LambdaQueryWrapper<SysAdminOrgRelation>()
                .eq(SysAdminOrgRelation::getSysAdminId, id)
                .orderByAsc(SysAdminOrgRelation::getSort));
        List<Integer> orgIds = orgRelations.stream().map(orgInfo -> orgInfo.getOrgInfoId()).collect(Collectors.toList());
        result.setDepartment(orgIds);
        // 查询该用户的所有角色
        List<SysRole> roleList = sysRoleMapper.getRoleList(id);
        List<Integer> roleIds = roleList.stream().map(role -> role.getId()).collect(Collectors.toList());
        result.setRole(roleIds);
        return result;
    }

    @Override
    public String getName(Integer userId) {
        return sysAdminMapper.getName(userId);
    }

    @Override
    public LoginUserInfoVO refreshToken(HttpServletRequest request) {
        String oldToken = request.getHeader(tokenHeader);
        jwtTokenUtil.getUserNameFromToken(oldToken);
        String newToken = jwtTokenUtil.refreshHeadToken(oldToken);
        if (newToken == null) {
            return null;
        }
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();
        loginUserInfoVO.setUserId(jwtTokenUtil.getUserIdFromToken(newToken));
        loginUserInfoVO.setName(jwtTokenUtil.getUserNameFromToken(newToken));
        loginUserInfoVO.setTokenHead(tokenHead);
        loginUserInfoVO.setToken(newToken);
        return loginUserInfoVO;
    }

    private List<SysAdminListOrgInfoVO> generateOrgInfoTree(List<OrgInfoQueryVO> list) {
        List<SysAdminListOrgInfoVO> orgInfoList = PojoUtils.convert(list, SysAdminListOrgInfoVO.class);
        List<SysAdminListOrgInfoVO> result = orgInfoList.stream().filter(orgInfo -> Objects.equals(orgInfo.getParentId(), 0))
                .map(orgInfo -> {
                    orgInfo.setChildList(getChildrens(orgInfo, orgInfoList));
                    return orgInfo;
                }).collect(Collectors.toList());
        return result;
    }

    private List<SysAdminListOrgInfoVO> getChildrens(SysAdminListOrgInfoVO orgInfo, List<SysAdminListOrgInfoVO> orgInfoList) {
        List<SysAdminListOrgInfoVO> childrens = orgInfoList.stream().filter(one -> Objects.equals(one.getParentId(), orgInfo.getId()))
                .map(one -> {
                    one.setChildList(getChildrens(one, orgInfoList));
                    return one;
                }).collect(Collectors.toList());
        return childrens;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRoleNum() {
        List<RoleNumVO> roleNumVOS = sysRoleMapper.getRoleNum();
        for (RoleNumVO roleNumVO : roleNumVOS) {
            SysRole sysRole = sysRoleMapper.selectById(roleNumVO.getRoleId());
            sysRole.setAdminCount(roleNumVO.getNum());
            sysRoleMapper.updateById(sysRole);
        }
    }

}
