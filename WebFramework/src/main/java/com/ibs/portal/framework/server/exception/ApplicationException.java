package com.ibs.portal.framework.server.exception;

import java.io.Serializable;

public abstract class ApplicationException extends BaseRuntimeException
		implements Serializable, ExceptionSource, ExceptionSeverity {

	private static final long serialVersionUID = 1L;

	public ApplicationException(String message, Throwable cause, int code) {
		super(message, cause, code, ERR_SOURCE_APPLICAITON, ERR_SEVERITY_NORMAL);
	}

	public ApplicationException(String message, Throwable cause, int code,
			int severity) {
		super(message, cause, code, ERR_SOURCE_APPLICAITON, severity);
	}

	public ApplicationException(String message, Throwable cause, int code,
			int source, int severity) {
		super(message, cause, code, source, severity);
	}
	/**
	 * 增加该异常方法为了实现biz层的异常与接口服务层实现无缝连接。
		 * @CreateTime: 2016年10月18日下午12:51:53
		 * @Creater:	   Shangzhuzi
		 * @MethodParamer:@param message
		 * @MethodParamer:@param codeStr
		 * @packageName :com.ibs.portal.framework.server.exception
	 */
	public ApplicationException(String message, String codeStr) {
		super(message, codeStr);
	}
}
