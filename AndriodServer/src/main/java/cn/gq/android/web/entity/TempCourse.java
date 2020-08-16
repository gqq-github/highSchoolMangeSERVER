package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/4/27 16:12
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
public class TempCourse implements Serializable {
   public String courseName ;
   public String courseId ;
   public Integer courseType ;
   public String  teacherName ;
    public  Integer cmtId  ;

    public TempCourse(String courseName, String courseId, Integer courseType, String teacherName) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseType = courseType;
        this.teacherName = teacherName;
    }
}
