/**
 * Memverifikasi login dengan password salah
 * menampilkan alert error.
 * @author NamaKamu - COE Team
 * @version 1.0.0
 * @date 2026-05-19
 * @see JIRA Ticket: QA-STORE-002
 */

import dto.pages.DemoLoginPage

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory
import internal.GlobalVariable

def td = TestDataFactory.findTestData('TD_Store/DataStore')
String username   = td.getValue('USERNAME', 2)
String password   = td.getValue('PASSWORD', 2)
String testCaseId = td.getValue('TEST_CASE_ID', 2)

WebUI.comment("▶ START: ${testCaseId}")

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()

DemoLoginPage loginPage = new DemoLoginPage()
loginPage.login(username, password)

WebUI.waitForAlert(5)
WebUI.verifyAlertPresent(5)
String alertText = WebUI.getAlertText()
WebUI.comment("Alert muncul: ${alertText}")
WebUI.acceptAlert()
WebUI.comment("✓ PASSED: ${testCaseId} - Alert error muncul sesuai ekspektasi")

WebUI.closeBrowser()