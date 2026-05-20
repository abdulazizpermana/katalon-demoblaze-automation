/**
 * Memverifikasi login dengan password salah
 * menampilkan pesan error yang sesuai.
 * @author Abdul Aziz Permana - COE Team
 * @version 1.0.0
 * @date 2026-05-19
 * @see JIRA Ticket: QA-SSO-002
 */

import pages.LoginPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory
import internal.GlobalVariable

// Ambil data dari Test Data
def td = TestDataFactory.findTestData('TD_Login/DataLogin')
String username = td.getValue('USERNAME', 2)
String password = td.getValue('PASSWORD', 2)
String testCaseId = td.getValue('TEST_CASE_ID', 2)

WebUI.comment("▶ START: ${testCaseId}")

// Buka browser
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/profile.php#login')
WebUI.maximizeWindow()

// Login dengan password salah
LoginPage loginPage = new LoginPage()
loginPage.login(username, password)

// Tunggu lalu verifikasi error message muncul
WebUI.waitForElementVisible(loginPage.getErrorMessage(), 15)
WebUI.verifyElementVisible(loginPage.getErrorMessage())
WebUI.comment("✓ PASSED: ${testCaseId} - Error message muncul sesuai ekspektasi")

WebUI.closeBrowser()