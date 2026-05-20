/**
 * Memverifikasi login dengan kredensial valid berhasil
 * dan user diarahkan ke halaman Make Appointment.
 * @author Abdul Aziz Permana - COE Team
 * @version 1.0.0
 * @date 2026-05-19
 * @see JIRA Ticket: QA-SSO-001
 */

import dto.pages.LoginPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import dto.pages.AppointmentPage

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory

import internal.GlobalVariable

// Ambil data dari Test Data
def td = TestDataFactory.findTestData('TD_Login/DataLogin')

String username = td.getValue('USERNAME', 1)
String password = td.getValue('PASSWORD', 1)
String testCaseId = td.getValue('TEST_CASE_ID', 1)
String expectedResult = td.getValue('EXPECTED_RESULT', 1)

// ── Init evidence ────────────────────────────────────────────────
EvidenceReporter.initEvidence(testCaseId)

// Path project
String projectDir = System.getProperty("user.dir")

// Folder screenshot
String screenshotFolder = projectDir + "/Screenshots"

// Buka browser
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL + '/profile.php#login')
WebUI.maximizeWindow()
EvidenceReporter.captureStep(1, 'Open Web Page', 'PASS')

// Login
LoginPage loginPage = new LoginPage()
loginPage.login(username, password)
WebUI.comment("Login dilakukan dengan username: ${username}")
WebUI.delay(2)
EvidenceReporter.captureStep(2, 'Login Success', 'PASS')

// Verifikasi halaman Make Appointment muncul
AppointmentPage appointmentPage = new AppointmentPage()

WebUI.waitForElementVisible(
	appointmentPage.getPageTitle(),
	15
)
WebUI.verifyElementVisible(
	appointmentPage.getPageTitle()
)
EvidenceReporter.captureStep(3, 'Welcome text visible', 'PASS')

WebUI.comment(
	"✓ PASSED: ${testCaseId} - Halaman Make Appointment berhasil tampil"
)

// ── Generate PDF ─────────────────────────────────────────────────
PdfReportKeyword.generateReport(
	testCaseId,
	'Verify valid login Appointment',
	'Abdul Aziz Permana',
	EvidenceReporter.getStepList()
)

WebUI.closeBrowser()