package com.ibs.portal.framework.server.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class BaseRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364872269911149050L;

	/**
	 * 来源
	 */
	private int source;

	/**
	 * 严重程度
	 */
	private int severity;

	/**
	 * 代码
	 */
	private int code;
	/**
	 * 异常编码
	 * shangzhzi
	 */
	private String codeStr;

	// 错误参数
	private Object[] errorParameters;

	Throwable cause = null;

	public BaseRuntimeException(String message, Throwable cause) {
		this(message, cause, -1, ExceptionSource.ERR_SOURCE_UNKNOWN,
				ExceptionSeverity.ERR_SEVERITY_NORMAL);
	}

	public BaseRuntimeException(String message, Throwable cause, int code,
			int source, int severity) {
		super(message);
		this.cause = cause;
		this.code = code;
		this.severity = severity;
		this.source = source;
	}
	/**
	 * 增加该异常方法为了实现biz层的异常与接口服务层实现无缝连接。
		 * @CreateTime: 2016年10月18日下午12:51:53
		 * @Creater:	   Shangzhuzi
		 * @MethodParamer:@param message
		 * @MethodParamer:@param codeStr
		 * @packageName :com.ibs.portal.framework.server.exception
	 */
	public BaseRuntimeException(String message, String codeStr ) {
		super(message);
		this.codeStr = codeStr;
	}
	
	

	public BaseRuntimeException(String message, Throwable cause, int code) {
		this(message, cause, code, ExceptionSource.ERR_SOURCE_UNKNOWN,
				ExceptionSeverity.ERR_SEVERITY_NORMAL);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause.getMessage());
		this.cause = cause;
	}

	public BaseRuntimeException(int code) {
		this(null, null, code);
	}

	public Throwable getReason() {
		return cause;
	}

	public BaseRuntimeException() {
		super();
	}

	public void printStackTrace(PrintStream stream) {
		if (cause != null) {
			cause.printStackTrace(stream);
			stream.println(new StringBuffer("rethrow as exception ").append(
					this.getClass().getName()).toString());
		}
		super.printStackTrace(stream);
	}

	public void printStackTrace(PrintWriter writer) {
		if (cause != null) {
			cause.printStackTrace(writer);
			writer.println(new StringBuffer("rethrow as exception ").append(
					this.getClass().getName()).toString());
		}
		super.printStackTrace(writer);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public Object[] getErrorParameters() {
		return errorParameters;
	}

	public void setErrorParameters(Object[] errorParameters) {
		this.errorParameters = errorParameters;
	}

	public String getCodeStr() {
		return codeStr;
	}

	public void setCodeStr(String codeStr) {
		this.codeStr = codeStr;
	}


}
