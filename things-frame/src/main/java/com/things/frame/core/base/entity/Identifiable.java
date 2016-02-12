package com.things.frame.core.base.entity;

import java.io.Serializable;

public interface Identifiable extends Serializable {


	public abstract String getId();


	public abstract void setId(String id);
	
}
