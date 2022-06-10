package com.hekai.back.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

//字段为空的话，不序列化进json数据，即不像前台返回空的值。
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SverResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private String msg;
	private T data;
	public int getStatus() {
		return this.status;
	}
	public T getData() {
		return data;
	}

	public String getMsg() {
		return msg;
	}
	
	@JsonIgnore
	public boolean isSuccess() {
		return this.status==ResponseCode.SUCCESS.getCode();
	}

	private SverResponse(int status) {
		this.status = status;
	}

	private SverResponse(int status, T data) {
		this.status = status;
		this.data = data;
	}

	private SverResponse(int status, String msg, T data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	private SverResponse(int status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public static <T> SverResponse<T> createRespBySuccess() {
		return new SverResponse<>(ResponseCode.SUCCESS.getCode());
	}

	public static <T> SverResponse<T> createRespBySuccess(T data) {
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), data);
	}

	public static <T> SverResponse<T> createRespBySuccess(String msg, T data) {
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg, data);
	}

	public static <T> SverResponse<T> createRespBySuccessMessage(String msg) {
		return new SverResponse<>(ResponseCode.SUCCESS.getCode(), msg);
	}

	public static <T> SverResponse<T> createRespByError() {
		return new SverResponse<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
	}

	public static <T> SverResponse<T> createByErrorMessage(String errorMessage) {
		return new SverResponse<T>(ResponseCode.ERROR.getCode(), errorMessage);
	}
	
	public static <T> SverResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
		return new SverResponse<T>(errorCode, errorMessage);
	}
}
