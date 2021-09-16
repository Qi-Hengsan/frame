package com.wwj.common.module.teacher.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wwj.common.module.teacher.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 122439195@qq.com
 */
public interface TeacherMapper extends BaseMapper<Teacher> {

    /**
     * 批量修改状态
     * @param ids
     * @param status
     */
    void batchUpdateStatus(@Param("ids") List<Integer> ids,@Param("status") Integer status);

}