package com.jyp.putting.domain;

public class Player {
	String loginId; // 로그인아이디
	String locationId; // 지점ID
	String locationName; // 지점이름
	String deviceId; // 장치ID
	int selectedMapId; // 선택한 지도 ID
	String heightData; // 높이데이터
	int runState; // 동작상태

	public Player() {
		super();
	}
	
	public Player(String loginId, String locationId, String locationName, String deviceId, int selectedMapId,
			String heightData, int runState) {
		super();
		this.loginId = loginId;
		this.locationId = locationId;
		this.locationName = locationName;
		this.deviceId = deviceId;
		this.selectedMapId = selectedMapId;
		this.heightData = heightData;
		this.runState = runState;
	}

	

	@Override
	public String toString() {
		return "Player [loginId=" + loginId + ", locationId=" + locationId + ", locationName=" + locationName
				+ ", deviceId=" + deviceId + ", selectedMapId=" + selectedMapId + ", heightData=" + heightData
				+ ", runState=" + runState + "]";
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getSelectedMapId() {
		return selectedMapId;
	}

	public void setSelectedMapId(int selectedMapId) {
		this.selectedMapId = selectedMapId;
	}

	public String getHeightData() {
		return heightData;
	}

	public void setHeightData(String heightData) {
		this.heightData = heightData;
	}

	public int getRunState() {
		return runState;
	}

	public void setRunState(int runState) {
		this.runState = runState;
	}

}
