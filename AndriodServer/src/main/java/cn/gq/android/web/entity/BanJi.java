package cn.gq.android.web.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author DELL
 * @create 2020/4/22 20:58
 * @update 2020/4/22
 * Description:
 * @since 1.0.0
 */
public class BanJi implements Serializable {
    private Integer cid ;
    private Integer mid ;
    private ArrayList<String> banjis ;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public ArrayList<String> getBanjis() {
        return banjis;
    }

    public void setBanjis(ArrayList<String> banjis) {
        this.banjis = banjis;
    }
}
