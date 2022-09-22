package modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import modules.dto.UsersMechanismDto;
import modules.entity.UsersMechanism;
import modules.vo.Result;

/**
 * Author：感觉自己是巨星
 * Date：2022-09-21-15:49
 * Description：<描述>
 */
public interface IUsersMechanismService extends IService<UsersMechanism> {

    Result userRegister(UsersMechanismDto usersMechanismDto);

}
