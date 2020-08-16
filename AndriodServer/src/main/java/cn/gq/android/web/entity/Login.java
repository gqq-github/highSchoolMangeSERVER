package cn.gq.android.web.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author DELL
 * @create 2020/3/1 14:07
 * @update 2020/3/1
 * Description: 对用是数据库中的login表
 * @since 1.0.0
 */

public class Login implements Serializable {
    private String userId ;
    private String passWord;
    private Date upDate ;
    private Date creatDate;
    /***
     * 角色id 0是教务管理员，1是教师，2学生
     */
    private Integer role ;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
