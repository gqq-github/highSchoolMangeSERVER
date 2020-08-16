package cn.gq.android.web.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Base64;

/**
 * @author DELL
 * @create 2020/5/8 22:35
 * @update 2020/5/8
 * Description: 封装学生提交的作业图片
 * @since 1.0.0
 */
public class StudentHomework implements Serializable {
    private Integer id ;
    private  Integer tshId ;
    private String studentId ;
    private  String homeworkPicture;
    private   byte [] image ;
    private String teacherPicture ;
    // 接收文件类型的数据
    private MultipartFile studentFile ;
    private MultipartFile teacherFile ;
    private String  fileUrl ;

    public MultipartFile getStudentFile() {
        return studentFile;
    }

    public void setStudentFile(MultipartFile studentFile) {
        this.studentFile = studentFile;
    }

    public MultipartFile getTeacherFile() {
        return teacherFile;
    }

    public void setTeacherFile(MultipartFile teacherFile) {
        this.teacherFile = teacherFile;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTshId() {
        return tshId;
    }

    public void setTshId(Integer tshId) {
        this.tshId = tshId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getHomeworkPicture() {
        return homeworkPicture;
    }

    public void setHomeworkPicture(String homeworkPicture) {
        this.homeworkPicture = homeworkPicture;
    }

    public String getTeacherPicture() {
        return teacherPicture;
    }

    public void setTeacherPicture(String teacherPicture) {
        this.teacherPicture = teacherPicture;
    }

    public byte[] getImage() {

        return image ;
    }

    public void setImage(byte[] image) {
        this.image = image ;

    }
}
