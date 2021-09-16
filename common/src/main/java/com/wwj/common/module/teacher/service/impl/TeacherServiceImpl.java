package com.wwj.common.module.teacher.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wwj.authority.util.JwtTokenUtil;
import com.wwj.common.module.sys.VO.LoginUserInfoVO;
import com.wwj.common.module.teacher.VO.TeacherVO;
import com.wwj.common.module.teacher.domain.TeacherUserDetails;
import com.wwj.common.module.teacher.entity.Teacher;
import com.wwj.common.module.teacher.mapper.TeacherMapper;
import com.wwj.common.module.teacher.param.TeacherPageParam;
import com.wwj.common.module.teacher.param.TeacherUpdateParam;
import com.wwj.common.module.teacher.service.TeacherService;
import com.wwj.common.utils.PojoUtils;
import com.wwj.core.pagination.Paging;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/18 16:40:43
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Paging<TeacherVO> listPage(TeacherPageParam query) {
        Page<Teacher> teacherPage = baseMapper.selectPage(new Page<>(query.getPageIndex(), query.getPageSize()),
                Wrappers.<Teacher>lambdaQuery()
                        .eq(query.getStatus() != null, Teacher::getStatus, query.getStatus())
                        .like(StringUtils.isNotBlank(query.getName()), Teacher::getName, query.getName())
                        .eq(StringUtils.isNotBlank(query.getPhone()), Teacher::getPhone, query.getPhone()));
        IPage<TeacherVO> convert = PojoUtils.convert(teacherPage, TeacherVO.class);
        return new Paging<>(convert);
    }

    /**
     * 重置密码
     * @param id 教师id
     */
    @Override
    public void resetPassword(Integer id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setPassword(passwordEncoder.encode("123456"));
        baseMapper.updateById(teacher);
    }

    @Override
    public TeacherUserDetails loadUserByUsername(String userName) {
        // 获取教师信息
        Teacher teacher = super.getOne(new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getUsername, userName));
        if (Objects.nonNull(teacher)) {
            return new TeacherUserDetails(teacher);
        }
        return null;
    }

    @Override
    public LoginUserInfoVO login(String username, String password) {
        // 对密码进行base64解码
        byte[] decode = Base64.getDecoder().decode(password);
        password = new String(decode, StandardCharsets.UTF_8);

        TeacherUserDetails adminUserDetails = loadUserByUsername(username);
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

    private LoginUserInfoVO userInfoToken(TeacherUserDetails adminUserDetails){
        LoginUserInfoVO loginUserInfoVO = new LoginUserInfoVO();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(adminUserDetails
                , null
                , adminUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateUserNameAndIdStr(adminUserDetails.getUsername(), adminUserDetails.getTeacher().getId());
        if (StrUtil.isBlank(token)) {
            return loginUserInfoVO;
        }
        loginUserInfoVO.setToken(token);
        loginUserInfoVO.setTokenHead(tokenHead);
        loginUserInfoVO.setName(adminUserDetails.getTeacher().getUsername());
        loginUserInfoVO.setUserId(adminUserDetails.getTeacher().getId());
        return loginUserInfoVO;
    }

    /**
     * 批量修改状态
     * @param param 参数
     */
    @Override
    public void batchUpdateStatus(TeacherUpdateParam param) {
        baseMapper.batchUpdateStatus(param.getIds(),param.getStatus());
    }
}
