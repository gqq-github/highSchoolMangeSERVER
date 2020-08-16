package cn.gq.android.web.entity;

import java.io.Serializable;

/**
 * @author DELL
 * @create 2020/4/21 22:44
 * @update 2020/4/21
 * Description:
 * @since 1.0.0
 */
public class College implements Serializable {
    private Integer cid  ;
    private String cname ;
    private String cdetail;
    private Integer masterId;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdetail() {
        return cdetail;
    }

    public void setCdetail(String cdetail) {
        this.cdetail = cdetail;
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }
}
