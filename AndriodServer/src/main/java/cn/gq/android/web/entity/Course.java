package cn.gq.android.web.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author DELL
 * @create 2020/4/16 21:33
 * @update 2020/4/16
 * Description: 课程的实体类
 * @since 1.0.0
 */
public class Course implements Serializable {
    private String courseId ;
    private String courseName ;
    private String courseCredit ;
    private String courseDetail ;
    private String courseData ;
   public Course () {}
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCredit() {
        return Integer.parseInt(courseCredit);
    }

    public void setCourseCredit(String courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseDetail() {
        return courseDetail;
    }

    public void setCourseDetail(String courseDetail) {
        this.courseDetail = courseDetail;
    }

    public Date getCourseData()
    {
        Date date= null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(courseData);
        } catch (ParseException e) {
            System.out.println("日期转化失败");
            e.printStackTrace();
        }
        return date;
    }

    public void setCourseData(String courseData) {
        this.courseData = courseData;
    }
}
