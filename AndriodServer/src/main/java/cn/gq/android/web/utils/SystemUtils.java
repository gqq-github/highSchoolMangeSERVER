package cn.gq.android.web.utils;

/**
 * @author DELL
 * @create 2020/6/8 11:32
 * @update 2020/6/8
 * Description: 判断当前系统的环境
 * @since 1.0.0
 */
public class SystemUtils {


        // 时候为window的环境
      public static boolean isWinEnv () {
           String os = System.getProperty("os.name");
           return  os.toLowerCase().startsWith("win") ;
      }
}
