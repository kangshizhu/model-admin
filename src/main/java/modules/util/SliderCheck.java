package modules.util;

import lombok.Data;

/**
 * @Date 2022年9月21日
 * @Author chenguitong
 * @ClassName SliderCheck
 * 滑块校验
 **/
@Data
public class SliderCheck {

    // 原图 base64
    private String resourceImg;
    private Integer resourceWidth;
    private Integer resourceHeight;

    // 拼图 base64
    private String puzzleImg;
    private Integer puzzleWidth;
    private Integer puzzleHeight;

    // 坐标
    private Integer puzzleXAxis;
    private Integer puzzleYAxis;


}
