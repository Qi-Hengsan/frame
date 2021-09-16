package com.wwj.common.module.sys.VO.query;

import com.wwj.common.module.sys.entity.SysMenu;
import com.wwj.common.module.sys.entity.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @Classname MenuRoleVO
 * @Description TODO
 * @Date 2021/7/12 22:15
 * @author by ztsong
 */
@Data
public class MenuRoleVO extends SysMenu {

    List<SysRole> roleList;

}
