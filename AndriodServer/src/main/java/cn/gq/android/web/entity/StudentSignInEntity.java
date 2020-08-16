package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/5/5 11:45
 * @update 2020/5/5
 * Description:
 * @since 1.0.0
 */
public class StudentSignInEntity implements Serializable {
    private Integer  id ;
    private Integer  signInId ;
    private  String studentId ;
    private Long signInTime ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSignInId() {
        return signInId;
    }

    public void setSignInId(Integer signInId) {
        this.signInId = signInId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Long getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Long signInTime) {
        this.signInTime = signInTime;
    }
}
