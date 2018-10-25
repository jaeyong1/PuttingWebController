package com.jyp.shopmanager.domain;

public class JSONResponse {
	public boolean success = true;
	public String errmsg = "";
	public Object body = null;

	public void success(Object obj) {
		success = true;
		body = obj;
	}

	public void error(JSONResponseErrorMessages errorTextMessage) {
		success = false;
		body = null;
		errmsg = errorTextMessage.toString();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

}
