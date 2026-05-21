package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import dto.locator.AppointmentLoginPageObjLoc
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil

public class AppointmentLoginPage extends AppointmentLoginPageObjLoc {

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