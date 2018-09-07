package com.jyp.putting.domain;

public class TableVo {
	private String id;
	private String name;

	//Web->Server on HTTP JSON
	public TableVo() {
		super();
	}

	//for DB xml process
	public TableVo(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "tableVo [id=" + id + ", name=" + name + "]";
	}

}
