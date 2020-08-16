package cn.gq.android.web.entity;

import javax.swing.text.html.ListView;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author DELL
 * @create 2020/4/18 20:24
 * @update 2020/4/18
 * Description:
 * @since 1.0.0
 */
public class CollegesAndMajors implements Serializable {
    private  Integer cid ;
    private String cname ;
    private ArrayList <String> majors;

    public CollegesAndMajors() {
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    public ArrayList<String> getMajors() {
        return majors;
    }

    public void setMajors(ArrayList<String> majors) {
        this.majors = majors;
    }
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
}
