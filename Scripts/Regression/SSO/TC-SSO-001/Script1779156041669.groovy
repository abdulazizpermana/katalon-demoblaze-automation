/**
 * Memverifikasi login berdasarkan data driven testing
 * menggunakan Katalon Test Suite Data Binding.
 *
 * @author Abdul Aziz Permana - COE Team
 * @version 2.1.0
 * @date 2026-05-20
 * @see JIRA Ticket: QA-SSO-DDT
 */
import dto.pages.AppointmentPage
import dto.pages.AppointmentLoginPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable

// test
// ======================================================
// Helper buat TestObject dari xpath — pengganti findTestObject
// karena kamu tidak pakai Object Repository
// ======================================================
def obj(String xpath) {
    TestObject to = new TestObject()
    to.addProperty('xpath', ConditionType.EQUALS, xpath)
    return to
}

// ======================================================
// Variable dari Test Suite Data Binding
// ⚠️ Harus HURUF BESAR — sesuai nama kolom di Excel
// ======================================================
println("Running : ${TEST_CASE_ID}")
WebUI.comment("Scenario  : ${SCENARIO}")
WebUI.comment("Test Case : ${TEST_CASE}")
WebUI.comment("Criteria  : ${CRITERIA}")

// ======================================================
// Init Evidence
// ======================================================
EvidenceReporter.initEvidence(TEST_CASE_ID)

// ======================================================
// STEP 1 — Open Browser
// ======================================================
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/profile.php#login')
WebUI.maximizeWindow()
EvidenceReporter.captureStep(1, 'Open Login Page', 'PASS')

// ======================================================
// STEP 2 — Login
// ======================================================
AppointmentLoginPage loginPage = new AppointmentLoginPage()
loginPage.login(USERNAME, PASSWORD)
WebUI.comment("Login dengan username: ${USERNAME}")
WebUI.delay(2)

// ======================================================
// POSITIVE TEST — Criteria = Normal
// ======================================================
if (CRITERIA == 'Normal') {

    AppointmentPage appointmentPage = new AppointmentPage()
    WebUI.waitForElementVisible(appointmentPage.getPageTitle(), 15)
    WebUI.verifyElementVisible(appointmentPage.getPageTitle())

    EvidenceReporter.captureStep(2, 'Login Success - halaman Appointment tampil', 'PASS')
    WebUI.comment("✅ PASSED: ${TEST_CASE_ID}")

// ======================================================
// NEGATIVE TEST — Criteria = Abnormal
// ======================================================
} else {

    // Ganti findTestObject() dengan obj() helper di atas
    // Sesuaikan xpath dengan elemen error di aplikasi kamu
    TestObject errorLabel = obj("//p[@class='lead text-danger']")

    WebUI.waitForElementVisible(errorLabel, 10)

    String errorText = WebUI.getText(errorLabel)
    WebUI.comment("Error text: ${errorText}")

    WebUI.verifyMatch(
        errorText,
        'Login failed! Please ensure the username and password are valid.',
        false
    )

    EvidenceReporter.captureStep(2, "Login ditolak: ${errorText}", 'PASS')
    WebUI.comment("✅ PASSED NEGATIVE: ${TEST_CASE_ID}")
}

// ======================================================
// Generate PDF Evidence
// ======================================================
PdfReportKeyword.generateReport(
    TEST_CASE_ID,
    TEST_CASE,
    'Abdul Aziz Permana',
    EvidenceReporter.getStepList()
)

WebUI.closeBrowser()