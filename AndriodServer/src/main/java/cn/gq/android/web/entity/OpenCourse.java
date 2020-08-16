package cn.gq.android.web.entity;

/**
 * @author DELL
 * @create 2020/4/27 12:08
 * @update 2020/4/27
 * Description:
 * @since 1.0.0
 */
public class OpenCourse {
    private Integer collegeId ;
    private Integer majorId ;
    private String courseId ;
    private Integer courseType;
    private String teacherId ;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
