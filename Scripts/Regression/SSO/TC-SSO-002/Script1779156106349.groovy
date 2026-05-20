/**
 * Memverifikasi login invalid menampilkan pesan error.
 * @author Abdul Aziz Permana - COE Team
 * @version 2.0.0
 * @date 2026-05-20
 * @see JIRA Ticket: QA-SSO-002
 */

import dto.pages.LoginPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable

def obj(String xpath) {
    TestObject to = new TestObject()
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

WebUI.comment("▶ START: ${TEST_CASE_ID} | Criteria: ${CRITERIA}")

EvidenceReporter.initEvidence(TEST_CASE_ID)

// Step 1: Open browser
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/profile.php#login')
WebUI.maximizeWindow()
EvidenceReporter.captureStep(1, 'Open Login Page', 'PASS')

// Step 2: Login dengan kredensial salah
LoginPage loginPage = new LoginPage()
loginPage.login(USERNAME, PASSWORD)
WebUI.comment("Login dengan username: ${USERNAME}")
WebUI.delay(2)

// Step 3: Verifikasi error message muncul
TestObject errorLabel = obj("//p[@class='lead text-danger']")
WebUI.waitForElementVisible(errorLabel, 10)

String errorText = WebUI.getText(errorLabel)
WebUI.comment("Error text: ${errorText}")

WebUI.verifyMatch(
    errorText,
    'Login failed! Please ensure the username and password are valid.',
    false
)

EvidenceReporter.captureStep(2, "Login ditolak - pesan error tampil: ${errorText}", 'PASS')
WebUI.comment("✅ PASSED NEGATIVE: ${TEST_CASE_ID}")

// Generate PDF
PdfReportKeyword.generateReport(
    TEST_CASE_ID,
    TEST_CASE,
    'Abdul Aziz Permana',
    EvidenceReporter.getStepList()
)

WebUI.closeBrowser()