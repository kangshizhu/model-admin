package modules.controller;


import io.swagger.annotations.ApiOperation;
import modules.dto.SysMenuDto;
import modules.dto.UsersDto;
import modules.entity.Users;
import modules.service.ISysMenuService;
import modules.service.IUsersService;
import modules.util.PasswordUtil;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-20
 */
@RestController
@RequestMapping("/sysmenu")
public class SysMenuController {
    @Resource
    ISysMenuService  iSysMenuService;



}
