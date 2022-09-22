package modules.config;

import modules.util.aliyunFile.OssConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * Author：感觉自己是巨星
 * Date：2022-09-20-16:42
 * Description：<描述>
 */
//@Configuration
public class UploadFileConfig {

    /**
     * 设置文件上传大小
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大,如果超出了该大小需要设置全局异常来处理MaxUploadSizeExceededException
        factory.setMaxFileSize(DataSize.ofMegabytes(10)); //MB
        //factory.setMaxFileSize(DataSize.ofKilobytes(80)); //KB
        //factory.setMaxFileSize(DataSize.ofGigabytes(80)); //Gb

        /// 设置总上传数据总大小
        //factory.setMaxRequestSize(DataSize.ofMegabytes(5));
        return factory.createMultipartConfig();
    }



}
