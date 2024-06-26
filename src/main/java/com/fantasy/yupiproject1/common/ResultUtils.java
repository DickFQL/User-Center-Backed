package com.fantasy.yupiproject1.common;

/**
 * 返回工具类
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }
    /**
     * 失败
     *
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode){
        return new BaseResponse<>(errorCode);
    }
    /**
     * 失败
     *
     * @return
     */
    public static BaseResponse error(int code, String message, String description){
        return new BaseResponse<>(code,null,message,description);
    }
    /**
     * 失败
     *  自定义消息和描述
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description){
        return new BaseResponse<>(errorCode.getCode(),null,message,description);
    }
    /**
     * 失败
     *  自定义描述
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String description){
        return new BaseResponse<>(errorCode.getCode(),null,errorCode.getMessage(),description);
    }
}

