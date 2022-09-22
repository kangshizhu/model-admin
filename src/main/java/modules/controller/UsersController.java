package modules.controller;


import io.swagger.annotations.ApiOperation;
import modules.dto.UsersDto;
import modules.dto.UsersMechanismDto;
import modules.entity.Users;
import modules.service.IUsersMechanismService;
import modules.service.IUsersService;
import modules.util.PasswordUtil;
import modules.util.RedisUtil;
import modules.util.SliderCheckUtil;
import modules.vo.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 *  用户表
 * </p>
 *
 * @author kangshizhu
 * @since 2022-09-19
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    IUsersService iUsersService;

    @Resource
    IUsersMechanismService iUsersMechanismService;

    @Autowired
    private RedisUtil redisUtil;


    @ApiOperation(value = "用户管理端后台登录", notes = "用户管理端后台登录用户type为0")
    @PostMapping(value = "/login")
    @ResponseBody
    public Result login(@RequestBody UsersDto usersDto) {
        return Result.OK(iUsersService.login(usersDto));
    }

    @ApiOperation(value = "用户管理端修改密码", notes = "用户管理端修改密码 密码加密")
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestBody  UsersDto usersDto) {
        Users users=new Users();
        BeanUtils.copyProperties(usersDto,users);
        String userpassword = PasswordUtil.encrypt("cloud", usersDto.getPassword(),PasswordUtil.SALT);
        users.setPassword(userpassword);
        iUsersService.updateById(users);
        return Result.OK("修改");
    }

    @ApiOperation(value = "获取滑块拼图和拼接图片", notes = "获取滑块拼图和拼接图片,返回base64后的 拼接图和 拼接方块")
    @GetMapping(value = "/getCode")
    @ResponseBody
    public Result getCode() throws Exception {
        return Result.OK(SliderCheckUtil.build());
    }


    @ApiOperation(value = "校验是否滑动成功", notes = "校验是否滑动成功，并下发短信")
    @PostMapping(value = "/verifyCode")
    @ResponseBody
    public Result verifyCode(@RequestBody  @Valid UsersMechanismDto usersMechanismDto) throws Exception {
        Integer puzzleXAxis = usersMechanismDto.getPuzzleXAxis();
        Integer distance = usersMechanismDto.getDistance();
        String phone = usersMechanismDto.getPhone();
        boolean flag = SliderCheckUtil.verifySlider(puzzleXAxis, distance);
        if(flag){
            Object o = redisUtil.get(phone);
            if(o==null ||StringUtils.isEmpty(o.toString())){
                String code="yanzhengma";
                redisUtil.set(phone,code,120);
                //TODO
                //发送验证码待实现
                return Result.OK("验证成功，短信已下发至您的手机");
            }else{
                return Result.error("验证码已发送,请"+redisUtil.getExpire(phone)+"秒后再试");
            }

        }else{
            return Result.error("验证失败");
        }
    }

    @ApiOperation(value = "机构人员注册", notes = "机构人员注册")
    @PostMapping(value = "/userRegister")
    @ResponseBody
    public Result userRegister(@RequestBody @Valid UsersMechanismDto usersDto){
        String phone = usersDto.getPhone();
        Object value = redisUtil.get(phone);
        if(value != null && StringUtils.isNotEmpty(value.toString())){
            String code = usersDto.getCode();
            //校验验证码是否正确
            if(value.toString().equals(code)){
                //验证码清除
                redisUtil.del(phone);
                return (Result) iUsersMechanismService.userRegister(usersDto);
            }else{
                return Result.error("验证码不正确!");
            }
        }else{
            return Result.error("验证码失效，请重新验证!");
        }

    }








}
