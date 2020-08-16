package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/4/21 22:47
 * @update 2020/4/21
 * Description:
 * @since 1.0.0
 */
public class Major implements Serializable {
    private Integer mid ;
    private  String mname ;
    private String mdetail ;
    private Integer collegeId ;

    public Major(Integer mid, String mname) {
        this.mid = mid;
        this.mname = mname;
    }
    public  Major () {}
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMdetail() {
        return mdetail;
    }

    public void setMdetail(String mdetail) {
        this.mdetail = mdetail;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
}
