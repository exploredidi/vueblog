package com.example.commons.lang;


import lombok.Data;

import java.io.Serializable;

/*
* 对结果进行封装
*
* 序列化
* 默认使用jdk自带序列化
*
* 用于我们的异步统一返回的结果封装
*
* 一般来说，结果里面有几个要素必要的
*   ：   是否成功，可用code表示（如200表示成功，400表示异常）
        结果消息
        结果数据
* */
@Data
public class Result implements Serializable {

        private int code;  //200正常，非200不正常
        private String msg;
        private Object data;

        //下面给出几个比较通用的方法
        public static Result succ(Object data) {
            return succ(200,"操作成功",data);
    }

        public static Result succ(int code,String mess, Object data) {
             Result m = new Result();
             m.setCode(code);
             m.setData(data);
             m.setMsg(mess);
             return m;
         }

        public static Result fail(String mess) {
             return fail(400,mess,null);
    }


        public static Result fail(int code,String mess, Object data) {
            Result m = new Result();
            m.setCode(code);
            m.setData(data);
            m.setMsg(mess);
            return m;
        }

}
