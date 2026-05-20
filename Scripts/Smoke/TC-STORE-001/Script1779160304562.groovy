import dto.pages.DemoLoginPage


import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory
import internal.GlobalVariable


def td = TestDataFactory.findTestData('TD_Store/DataStore')
String username   = td.getValue('USERNAME', 1)
String password   = td.getValue('PASSWORD', 1)
String testCaseId = td.getValue('TEST_CASE_ID', 1)

// ── Init evidence ────────────────────────────────────────────────
EvidenceReporter.initEvidence(testCaseId)

// ── Step 1: Open Browser ─────────────────────────────────────────
WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()
EvidenceReporter.captureStep(1, 'Open Login Page', 'PASS')

// ── Step 2: Login ────────────────────────────────────────────────
DemoLoginPage loginPage = new DemoLoginPage()
loginPage.login(username, password)
loginPage.waitForWelcomeText()
EvidenceReporter.captureStep(2, 'Login Success', 'PASS')

// ── Step 3: Verify Welcome Text ──────────────────────────────────
loginPage.isWelcomeTextVisible()
EvidenceReporter.captureStep(3, 'Welcome text visible', 'PASS')

// ── Generate PDF ─────────────────────────────────────────────────
PdfReportKeyword.generateReport(
    testCaseId,
    'Verify valid login shows welcome message',
    'Abdul Aziz Permana',
    EvidenceReporter.getStepList()
)

WebUI.closeBrowser()