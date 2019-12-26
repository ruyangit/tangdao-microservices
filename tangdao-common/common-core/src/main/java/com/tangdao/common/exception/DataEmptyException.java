package com.tangdao.common.exception;

/**
 * @ClassName: DataEmptyException.java
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author ruyang
 * @date 2018年12月11日 下午2:24:03
 * 
 */
public class DataEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataEmptyException(String message) {
		super(message);
	}

	public DataEmptyException() {
	}

	public DataEmptyException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataEmptyException(Throwable cause) {
		super(cause);
	}
}
