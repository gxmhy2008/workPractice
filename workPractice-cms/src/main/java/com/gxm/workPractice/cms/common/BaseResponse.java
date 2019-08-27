package com.gxm.workPractice.cms.common;

/**
 * API接口的基础返回类
 *
 */
public class BaseResponse<T> {
    /**
     * 是否成功
     */
    private String result;

    /**
     * 说明
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public BaseResponse() {

    }

    public BaseResponse(String result , String msg, T data) {
        this.result=result;
        this.msg= msg;
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
    public String toString() {
    	 return "{" +
                 "\"result\":\"" + result + '\"' +
                 ", \"msg\":\"" + msg + '\"' +
                 ", \"data\":" + data +
                 '}';
    }
}
