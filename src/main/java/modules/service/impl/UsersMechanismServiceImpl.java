package modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import modules.dto.UsersMechanismDto;
import modules.entity.UsersMechanism;
import modules.mapper.UsersMechanismMapper;
import modules.service.IUsersMechanismService;
import modules.util.PasswordUtil;
import modules.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Author：感觉自己是巨星
 * Date：2022-09-21-15:50
 * Description：<描述>
 */
@Service
public class UsersMechanismServiceImpl extends ServiceImpl<UsersMechanismMapper, UsersMechanism> implements IUsersMechanismService {

    @Resource
    UsersMechanismMapper usersMechanismMapper;
    @Override
    public Result userRegister(UsersMechanismDto usersMechanismDto) {
        QueryWrapper<UsersMechanism> queryWrapper=new QueryWrapper<>();
        //校验手机号是否重复,判断用户是否存在
        queryWrapper.eq("phone",usersMechanismDto.getPhone());
        UsersMechanism usersMechanism=usersMechanismMapper.selectOne(queryWrapper);
        if(usersMechanism==null){
            usersMechanism=new UsersMechanism();
            BeanUtils.copyProperties(usersMechanismDto,usersMechanism);
            //密码加密
            String password = PasswordUtil.encrypt("cloud", usersMechanism.getPassword(),PasswordUtil.SALT);
            usersMechanism.setPassword(password);
            usersMechanismMapper.insert(usersMechanism);
            return Result.OK("注册成功");
        }else{
            return Result.error("该手机号已注册!");
        }

    }
}
