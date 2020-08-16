package cn.gq.android.web.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author DELL
 * @create 2020/4/30 9:34
 * @update 2020/4/30
 * Description:
 * @since 1.0.0
 */
public class MyResult <T> implements Serializable {
      private  String msg ;
      private  Integer code ;
      private T data ;
      public MyResult (Integer code , String msg) {
           this.code = code ;
           this.msg = msg ;
      }
      public MyResult (Integer code,String msg, T data) {
          this (code,msg) ;
          this.data = data ;
      }

      public static MyResult success (String msg) {
          return new MyResult<>(200,msg) ;
      }
      public static  MyResult success (String msg,  Object data) {
          return  new MyResult(200,msg,data);
      }
      public static  MyResult faile (String msg) {
          //205表示数据查询不到
          return new MyResult(205,msg);
      }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
