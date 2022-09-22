package modules.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import modules.entity.BaseEntity;
import modules.util.phone.Phone;

/**
 * Author：感觉自己是巨星
 * Date：2022-09-21-16:05
 * Description：<描述>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Users对象，包含验证码", description="")
public class UsersMechanismDto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "验证码",required = true)
    private String code;

    @ApiModelProperty(value = "主键id",required = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "手机号",required = true)
    @Phone
    private String phone;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "滑块对象X轴最终坐标")
    private Integer puzzleXAxis;

    @ApiModelProperty(value = "滑动距离")
    private Integer distance;
}
