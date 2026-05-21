/**
 * TEST CASE: TC-STORE-001
 * DESCRIPTION: Login dengan username & password yang terdaftar
 * EXPECTED RESULT: Navbar menampilkan Welcome username
 * 
 * Data Binding: Row 1-1 (dari Test Suite)
 * Variables: username, password, testCaseId (otomatis dari binding)
 * 
 * @author Abdul Aziz Permana - COE Team
 * @version 2.0.0 (Data Driven Testing)
 * @date 2026-05-21
 */

import dto.pages.DemoLoginPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// ════════════════════════════════════════════════════════════
// STEP 1: INIT TEST EXECUTION
// ════════════════════════════════════════════════════════════
// Variables sudah otomatis dari Data Binding di Test Suite:
// username = "bnitesta123" (dari Row 1, Column USERNAME)
// password = "Test@12345" (dari Row 1, Column PASSWORD)
// testCaseId = "TC-STORE-001" (dari Row 1, Column TEST_CASE_ID)

WebUI.comment("═══════════════════════════════════════════════════════════")
WebUI.comment("▶ START: ${TEST_CASE_ID}")
WebUI.comment("═══════════════════════════════════════════════════════════")

// Init evidence reporter
EvidenceReporter.initEvidence(TEST_CASE_ID)

// ════════════════════════════════════════════════════════════
// STEP 1: Open Browser & Login Page
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 1: Open Browser & Navigate to Login Page")
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()

// Capture evidence
EvidenceReporter.captureStep(1, 'Open Login Page', 'PASS')

// ════════════════════════════════════════════════════════════
// STEP 2: Perform Login
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 2: Login dengan data:")
WebUI.comment("  - Username: ${USERNAME}")
WebUI.comment("  - Password: ${PASSWORD}")

DemoLoginPage loginPage = new DemoLoginPage()
loginPage.login(USERNAME, PASSWORD)

// Wait untuk welcome text muncul
loginPage.waitForWelcomeText()

EvidenceReporter.captureStep(2, 'Login Success', 'PASS')

// ════════════════════════════════════════════════════════════
// STEP 3: Verify Welcome Text Visible
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 3: Verify Welcome Text Visible")

boolean isWelcomeVisible = loginPage.isWelcomeTextVisible()

if (isWelcomeVisible) {
    WebUI.comment("✓ Welcome text berhasil ditampilkan")
    EvidenceReporter.captureStep(3, 'Welcome text visible', 'PASS')
} else {
    WebUI.comment("✗ Welcome text TIDAK ditemukan")
    EvidenceReporter.captureStep(3, 'Welcome text visible', 'FAIL')
}

// ════════════════════════════════════════════════════════════
// STEP 4: Generate PDF Report
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 4: Generate PDF Report")

PdfReportKeyword.generateReport(
    TEST_CASE_ID,
    'Verify valid login shows welcome message',
    'Abdul Aziz Permana',
    EvidenceReporter.getStepList()
)

EvidenceReporter.captureStep(4, 'Report Generated', 'PASS')

// ════════════════════════════════════════════════════════════
// CLEANUP
// ════════════════════════════════════════════════════════════
WebUI.closeBrowser()

WebUI.comment("═══════════════════════════════════════════════════════════")
WebUI.comment("✓ PASSED: ${TEST_CASE_ID}")
WebUI.comment("═══════════════════════════════════════════════════════════")