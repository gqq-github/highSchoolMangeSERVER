package cn.gq.android.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author DELL
 * @create 2020/6/5 11:51
 * @update 2020/6/5
 * Description: 设置图片的静态资源映射
 * @since 1.0.0
 */
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
    /*
    * 设置静态资源映射
    * */
    @Value("${android.file.save.file}")
    String fileRoot ;
    @Value("${android.file.save.file.linux}")
    String fileLinuxRoot  ;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        /**
         * http:// 服务地址：8080/file/图片名称
         * 自动映射到服务端的目录root/Myfile/图片名称
         */
        String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {  //如果是Windows系统
            registry.addResourceHandler("/android/**")
                    // /apple/**表示在磁盘apple目录下的所有资源会被解析为以下的路径
                    .addResourceLocations("file:"+fileRoot) //媒体资源
                    .addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面
        } else {  //linux 和mac
            registry.addResourceHandler("/android/**")
                    .addResourceLocations("file:"+fileLinuxRoot)   //媒体资源
                    .addResourceLocations("classpath:/META-INF/resources/");  //swagger2页面;
        }
    }
}
