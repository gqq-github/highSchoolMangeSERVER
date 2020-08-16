package cn.gq.android.web.utils;
/**
 * @author DELL
 * @create 2020/3/1 13:32
 * @update 2020/3/1
 * Description:
 * @since 1.0.0
 */
public class Utils {
//    public Object getNewObject (StringBuffer data,Class clazz){
//     return JSON.parseObject(data.toString(),clazz);
//    }

    /***
     * 根据角色的id匹配对应的角色
     */
 public static String getRealRole (int role) {
     String realRole = "";
     switch (role) {
         case 0 :
             realRole="manger" ;
             break;
         case 1 :
             realRole = "teacher" ;
             break;
         case 2:
             realRole = "student" ;
             break;
     }
     return  realRole ;
 }
}
