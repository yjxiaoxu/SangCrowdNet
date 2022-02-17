package com.yjxiaoxu.crowd.util;

/**
 * ClassName:ResultEntity
 * Package:com.yjxiaoxu.crowd.util
 * Description:定义一个工具类，统一整个项目中ajax请求返回的结果（）未来也可以用于分布式架构各个模块间调用时返回同一类型
 *
 * @Date:2021/8/15 16:59
 * @Author:小许33058485@qq.com
 */
public class ResultEntity<T> {
    //成功
    private static final String SUCCESS = "SUCCESS";
    //失败
    private static final String FAILED = "FAILED";
    //用来封装当前的请求结果是否成功
    private String result;
    //请求处理失败时返回的消息
    private String message;
    //返回的数据
    private T data;
    public ResultEntity() {}
    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    /**
     * 自定义（声明）泛型，可以设置任何字符，在方法调用时根据传入的值来确定具体类型
     * @param <Type>
     * @return
     */
    //请求处理成功且不需要返回数据的工具方法
    public static <Type> ResultEntity<Type> successWithOutDate() {
        return new ResultEntity<Type>(SUCCESS, null, null);
    }
    //请求处理成功且需要返回数据的工具方法
    public static <Type> ResultEntity<Type> successWithDate(Type data) {
        return new ResultEntity<Type>(SUCCESS, null, data);
    }
    //请求处理失败返回错误消息的工具方法
    public static <Type> ResultEntity<Type> failed(String message) {
        return new ResultEntity<Type>(FAILED, message, null);
    }
    //setter和getter方法
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ResultEntity{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
