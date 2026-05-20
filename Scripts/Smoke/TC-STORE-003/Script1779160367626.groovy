/**
 * Memverifikasi produk berhasil ditambahkan ke cart
 * setelah login valid.
 * @author NamaKamu - COE Team
 * @version 1.0.0
 * @date 2026-05-19
 * @see JIRA Ticket: QA-STORE-003
 */

import dto.pages.DemoLoginPage
import dto.pages.DemoProductPage
import utils.EvidenceReporter
import utils.PdfReportKeyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory
import internal.GlobalVariable

def td = TestDataFactory.findTestData('TD_Store/DataStore')
String username    = td.getValue('USERNAME', 5)
String password    = td.getValue('PASSWORD', 5)
String productName = td.getValue('PRODUCT_NAME', 5)
String testCaseId  = td.getValue('TEST_CASE_ID', 5)

// ── Init evidence ────────────────────────────────────────────────
EvidenceReporter.initEvidence(testCaseId)

WebUI.openBrowser('')
WebUI.navigateToUrl(GlobalVariable.BASE_URL)
WebUI.maximizeWindow()
EvidenceReporter.captureStep(1, 'Open Web Page', 'PASS')

// Login dulu
DemoLoginPage loginPage = new DemoLoginPage()
loginPage.login(username, password)
loginPage.isWelcomeTextVisible()
WebUI.comment("Login berhasil")
EvidenceReporter.captureStep(2, 'Login Page', 'PASS')

// Pilih produk & add to cart
DemoProductPage productPage = new DemoProductPage()
productPage.clickProduct(productName)
EvidenceReporter.captureStep(3, 'Select Product Page', 'PASS')
WebUI.delay(2)

productPage.clickAddToCart()
productPage.handleAlert()
EvidenceReporter.captureStep(4, 'Add to cart', 'PASS')
WebUI.delay(2)

// ── Generate PDF ─────────────────────────────────────────────────
PdfReportKeyword.generateReport(
	testCaseId,
	'Select Product Add to Cart',
	'Abdul Aziz Permana',
	EvidenceReporter.getStepList()
)

WebUI.closeBrowser()