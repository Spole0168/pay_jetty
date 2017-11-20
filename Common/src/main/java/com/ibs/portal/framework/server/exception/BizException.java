package com.ibs.portal.framework.server.exception;

import java.io.Serializable;

/**
 * 应用异常,一般是业务约束相关,unChecked异常
 * 
 * @author luoyue
 * 
 */
public class BizException extends ApplicationException implements Serializable,
		ExceptionSource, ExceptionSeverity {

	private static final long serialVersionUID = 7865482707056545923L;

	public BizException(String message) {
		super(message, null, ERR_SOURCE_BIZ);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause, ERR_SOURCE_BIZ);
	}

	public BizException(String message, Throwable cause, int severity) {
		super(message, cause, -1, ERR_SOURCE_BIZ, severity);
	}

	public BizException(String message, Throwable cause, int source,
			int severity) {
		super(message, cause, -1, source, severity);
	}

	public BizException(String message, Throwable cause, int code, int source,
			int severity) {
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
	public BizException(String message, String codeStr) {
		super(message, codeStr);
	}

}
