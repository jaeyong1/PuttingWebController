package com.jyp.putting.domain;

public class FieldItem {
	int id;
	String ccname;
	String holename;
	String desc;
	String heightdata;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCcname() {
		return ccname;
	}

	public void setCcname(String ccname) {
		this.ccname = ccname;
	}

	public String getHolename() {
		return holename;
	}

	public void setHolename(String holename) {
		this.holename = holename;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getHeightdata() {
		return heightdata;
	}

	public void setHeightdata(String heightdata) {
		this.heightdata = heightdata;
	}

	public FieldItem(int id, String ccname, String holename, String desc, String heightdata) {
		super();
		this.id = id;
		this.ccname = ccname;
		this.holename = holename;
		this.desc = desc;
		this.heightdata = heightdata;
	}

}
