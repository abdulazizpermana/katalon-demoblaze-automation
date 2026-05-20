package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

public class LoginPage {

	private TestObject obj(String xpath) {
		TestObject to = new TestObject()
		to.addProperty("xpath", ConditionType.EQUALS, xpath)
		return to
	}

	// === Locators ===
	TestObject getUsernameField() {
		return obj("//input[@id='txt-username']")
	}
	TestObject getPasswordField() {
		return obj("//input[@id='txt-password']")
	}
	TestObject getLoginButton() {
		return obj("//button[@id='btn-login']")
	}
	TestObject getErrorMessage() {
		return obj("//p[@class='lead text-danger']")
	}

	// === Actions ===
	void inputUsername(String username) {
		WebUI.setText(getUsernameField(), username)
	}
	void inputPassword(String password) {
		WebUI.setText(getPasswordField(), password)
	}
	void clickLogin() {
		WebUI.click(getLoginButton())
	}
	void login(String username, String password) {
		inputUsername(username)
		inputPassword(password)
		clickLogin()
	}
}