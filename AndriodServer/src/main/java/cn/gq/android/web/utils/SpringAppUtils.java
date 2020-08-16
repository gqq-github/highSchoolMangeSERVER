package cn.gq.android.web.utils;

import lombok.extern.java.Log;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author DELL
 * @create 2020/3/6 22:28
 * @update 2020/3/6
 * Description:
 * @since 1.0.0
 */
@Component
@EnableAutoConfiguration
@Log
public class SpringAppUtils implements ApplicationContextAware {
    public static ApplicationContext applicationContext=null;
    @Override
    public void setApplicationContext(ApplicationContext var) throws BeansException
    {
        if(applicationContext == null) {
            applicationContext = var;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
