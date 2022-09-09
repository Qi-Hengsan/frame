package com.wwj.common.module.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwj.common.module.teacher.domain.TeacherUserDetails;
import com.wwj.common.module.teacher.entity.Teacher;
import com.wwj.common.module.teacher.param.TeacherAddParam;
import com.wwj.common.module.teacher.param.TeacherPageParam;
import com.wwj.common.module.teacher.param.TeacherUpdateParam;
import com.wwj.core.pagination.Paging;
import com.wwj.common.module.teacher.VO.TeacherVO;
import com.wwj.common.module.sys.VO.LoginUserInfoVO;

/**
 * @author wushilin
 * @version 1.0
 * @date 2021/08/18 16:35:42
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 教师信息管理-分页
     * @param teacherPageParam 参数
     * @return TeacherVO
     */
    Paging<TeacherVO> listPage(TeacherPageParam teacherPageParam);

    /**
     * 重置密码
     * @param id 教师id
     */
    void resetPassword(Integer id);

    /**
     * 权限校验
     * @param userName 用户名
     * @return TeacherUserDetails
     */
    TeacherUserDetails loadUserByUsername(String userName);

    /**
     * 教师登录
     * @param username 用户名
     * @param password 密码
     * @return LoginUserInfoVO
     */
    LoginUserInfoVO login(String username, String password);

    /**
     * 批量修改状态
     * @param param 参数
     */
    void batchUpdateStatus(TeacherUpdateParam param);

    /**
     * 添加教师
     * @param param 参数
     */
    void add(TeacherAddParam param);
}
