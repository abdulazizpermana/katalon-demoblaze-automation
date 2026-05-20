package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class AppointmentPage {

    private TestObject obj(String xpath) {
        TestObject to = new TestObject()
        to.addProperty("xpath", ConditionType.EQUALS, xpath)
        return to
    }

    // === Locators ===
    TestObject getPageTitle() {
        return obj("//h2[normalize-space()='Make Appointment']")
    }
    TestObject getFacilityDropdown() {
        return obj("//select[@id='combo_facility']")
    }
    TestObject getVisitDateField() {
        return obj("//input[@id='txt_visit_date']")
    }
    TestObject getCommentField() {
        return obj("//textarea[@id='txt_comment']")
    }
    TestObject getBookButton() {
        return obj("//button[@id='btn-book-appointment']")
    }
    TestObject getConfirmationHeader() {
        return obj("//h2[normalize-space()='Appointment Confirmation']")
    }

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