package cn.gq.android.web.entity;


import java.io.Serializable;
import java.util.List;

/**
 * @author DELL
 * @create 2020/4/27 16:11
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
public class TempData implements Serializable {
  public   Integer majorId ;

   public   String majorName ;
  public   List<TempCourse> courses;

    public TempData(Integer majorId, String majorName, List<TempCourse> courses) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.courses = courses;
    }
}
