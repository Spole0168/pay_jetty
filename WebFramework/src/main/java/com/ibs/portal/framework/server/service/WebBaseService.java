package com.ibs.portal.framework.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibs.portal.framework.server.context.UserContext;
import com.ibs.portal.framework.server.domain.IUser;

public abstract class WebBaseService {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected String getUserName() {
		return UserContext.getUserContext().getUserName();
	}

	protected IUser getCurrentUser() {
		return UserContext.getUserContext().getCurrentUser();
	}

}
