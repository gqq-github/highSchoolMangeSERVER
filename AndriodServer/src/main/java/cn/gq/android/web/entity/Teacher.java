package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/4/23 14:34
 * @update 2020/4/23
 * Description:
 * @since 1.0.0
 */
public class Teacher implements Serializable {
    private String tid ;
    private String tname ;
    private String tdeg ;
    private Integer collegeId ;

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTdeg() {
        return tdeg;
    }

    public void setTdeg(String tdeg) {
        this.tdeg = tdeg;
    }
}
