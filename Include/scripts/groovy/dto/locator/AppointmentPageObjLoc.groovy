package dto.locator
import dto.pages.AppointmentPage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When



class AppointmentPageObjLoc {
    private TestObject obj(String xpath) {
        TestObject to = new TestObject()
        to.addProperty("xpath", ConditionType.EQUALS, xpath)
        return to
    }

    // Locators
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

 
}