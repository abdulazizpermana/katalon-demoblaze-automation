package dto.pages


import com.kms.katalon.core.testobject.ConditionType
import dto.locator.AppointmentPageObjLoc
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class AppointmentPage extends AppointmentPageObjLoc {

    // === Actions ===
	boolean isPageLoaded() {
		WebUI.waitForElementVisible(getPageTitle(), 10)
		return WebUI.verifyElementVisible(getPageTitle())
	}
    void selectFacility(String facility) {
        WebUI.selectOptionByLabel(getFacilityDropdown(), facility, false)
    }
    void setVisitDate(String date) {
        WebUI.setText(getVisitDateField(), date)
    }
    void inputComment(String comment) {
        WebUI.setText(getCommentField(), comment)
    }
    void clickBook() {
        WebUI.click(getBookButton())
    }
    boolean isConfirmationShown() {
        return WebUI.verifyElementVisible(getConfirmationHeader(), 10)
    }
}