package com.jyp.shopmanager.domain;

public enum JSONResponseErrorMessages {
	LoginAgain("Please login again."), // 앱에서 올라온 로그인토큰이 DB에 없을때
	errorMessages1("Internal Error1"), //
	errorMessages2("Internal Error2");

	/**
	 * 이하 에러구현 코드들..
	 */

	private final String messageText;

	private JSONResponseErrorMessages(String s) {
		messageText = s;
	}

	public boolean equalsName(String otherName) {
		// (otherName == null) check is not needed because name.equals(null)
		// returns false
		return messageText.equals(otherName);
	}

	public String toString() {
		return this.messageText;
	}

}
