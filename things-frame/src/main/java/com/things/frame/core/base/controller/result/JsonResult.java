package com.things.frame.core.base.controller.result;

import com.alibaba.fastjson.JSONObject;

public class JsonResult extends AbstractResult {

	private static final long serialVersionUID = 4242158553469361221L;
	
	public JsonResult() { }
	
	public JsonResult(Status status, Object message) {
		super.setStatus(status); 
		super.setMessage(message);
	}

	@Override
	public String toString() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("status", super.getStatus().toString());
		jsonObj.put("message", super.getMessage());
		
		return jsonObj.toJSONString();
	}
}
