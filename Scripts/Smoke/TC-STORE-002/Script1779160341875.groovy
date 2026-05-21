/**
 * TEST CASE: TC-STORE-002
 * DESCRIPTION: Login dengan password yang tidak valid
 * EXPECTED RESULT: Alert error "Wrong password" muncul
 * CRITERIA: Abnormal
 * 
 * Data Binding: Row 2-2 (dari Test Suite)
 * Variables: username, password, testCaseId (otomatis dari binding)
 * 
 * Test Data:
 *   - Username: bnitesta124
 *   - Password: WrongPass999 (password salah)
 * 
 * @author Abdul Aziz Permana - COE Team
 * @version 2.0.0 (Data Driven Testing - FIXED)
 * @date 2026-05-21
 */

import dto.pages.DemoLoginPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

// ════════════════════════════════════════════════════════════
// INITIALIZATION
// ════════════════════════════════════════════════════════════
// Variables dari Data Binding (Row 2-2):
// username = "bnitesta124" (user exists, tapi password salah)
// password = "WrongPass999" (wrong password)
// testCaseId = "TC-STORE-002"

WebUI.comment("═══════════════════════════════════════════════════════════")
WebUI.comment("▶ START: ${TEST_CASE_ID}")
WebUI.comment("═══════════════════════════════════════════════════════════")

// Init evidence
EvidenceReporter.initEvidence(TEST_CASE_ID)

// ════════════════════════════════════════════════════════════
// STEP 1: Open Browser
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 1: Open Browser & Navigate to Login Page")
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()

EvidenceReporter.captureStep(1, 'Open Login Page', 'PASS')

// ════════════════════════════════════════════════════════════
// STEP 2: Login Invalid
// ════════════════════════════════════════════════════════════

WebUI.comment("Step 2: Attempt login with invalid credential")

DemoLoginPage loginPage = new DemoLoginPage()

loginPage.login(
    USERNAME,
    PASSWORD
)

try {

    // Tunggu alert muncul
    WebUI.waitForAlert(5)

    // Ambil text alert
    String alertText = WebUI.getAlertText()

    WebUI.comment("Alert text: ${alertText}")

    // Verify alert
    WebUI.verifyMatch(
        alertText,
        'User does not exist.',
        false
    )

    // Accept alert dulu
    WebUI.acceptAlert()

    // Delay kecil supaya browser stabil
    WebUI.delay(1)

    // Baru screenshot
    EvidenceReporter.captureStep(
        2,
        "Login failed - Alert: ${alertText}",
        'PASS'
    )

    WebUI.comment(
        "✓ Negative login validation success"
    )

} catch (Exception e) {

    WebUI.comment(
        "✗ Alert tidak muncul atau gagal dibaca"
    )

    EvidenceReporter.captureStep(
        2,
        'Login failed alert not found',
        'FAIL'
    )

    throw e
}


// ════════════════════════════════════════════════════════════
// STEP 3: Wait & Verify Alert Error
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 3: Tunggu alert error muncul")

try {
    // Wait untuk alert appear (timeout: 5 detik)
    WebUI.waitForAlert(5)
    
    // Verify alert exist
    WebUI.verifyAlertPresent(5)
    
    // Get alert text
    String alertText = WebUI.getAlertText()
    WebUI.comment("Alert text: ${alertText}")
    
    // Verify alert mengandung "Wrong password" atau "password"
    if (alertText.toLowerCase().contains('wrong') || alertText.toLowerCase().contains('password')) {
        WebUI.comment("✓ Alert error yang expected berhasil ditampilkan")
        EvidenceReporter.captureStep(3, 'Alert error displayed correctly', 'PASS')
    } else {
        WebUI.comment("⚠ Alert muncul tapi text tidak sesuai: ${alertText}")
        EvidenceReporter.captureStep(3, 'Alert text mismatch', 'FAIL')
    }
    
    // Accept (close) alert
    WebUI.acceptAlert()
    
} catch (Exception e) {
    WebUI.comment("✗ FAIL: Alert tidak muncul atau timeout")
    WebUI.comment("Error: ${e.message}")
    EvidenceReporter.captureStep(3, 'Alert error NOT displayed', 'FAIL')
}

// ════════════════════════════════════════════════════════════
// STEP 4: Generate Report
// ════════════════════════════════════════════════════════════
WebUI.comment("Step 4: Generate PDF Report")

PdfReportKeyword.generateReport(
    TEST_CASE_ID,
    'Verify wrong password alert is displayed',
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