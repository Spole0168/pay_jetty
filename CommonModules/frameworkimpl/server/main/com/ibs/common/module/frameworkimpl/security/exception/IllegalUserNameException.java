package com.ibs.common.module.frameworkimpl.security.exception;

public class IllegalUserNameException extends AuthorizeException {
	/**
	 *
	 */
	private static final long serialVersionUID = -8223801794137567077L;
	private static String IllegalUserNameKey = "Authorize.illegal.username";

	public IllegalUserNameException() {
		super(IllegalUserNameKey);
	}
}
