package cn.gq.android.web.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/5/10 21:13
 * @update 2020/5/10
 * Description:
 * @since 1.0.0
 */
public class CheckHomework implements Serializable {
    private byte[] image ;
    private String teacherPic ;
    private Integer answerId ;
    private Integer credit;
    private MultipartFile check_homework_file ;
    private String check_homework_path ;



    public byte[] getImage() {
        return image;
    }

    public MultipartFile getCheck_homework_file() {
        return check_homework_file;
    }

    public void setCheck_homework_file(MultipartFile check_homework_file) {
        this.check_homework_file = check_homework_file;
    }

    public String getCheck_homework_path() {
        return check_homework_path;
    }

    public void setCheck_homework_path(String check_homework_path) {
        this.check_homework_path = check_homework_path;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTeacherPic() {
        return teacherPic;
    }

    public void setTeacherPic(String teacherPic) {
        this.teacherPic = teacherPic;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }
}
