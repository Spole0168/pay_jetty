package com.ibs.portal.framework.server.domain;

import java.io.Serializable;

public interface IEntityEncrypt extends Serializable, Comparable<Object>{
	/**
	 * 获得ID
	 *
	 * @return 获得ID
	 */
	String getId();
	
	String getEncryptData();
	
	void setEncryptData(String encryptData);
}
