/**
 * TEST CASE: TC-STORE-005
 * DESCRIPTION: Login lalu add produk ke cart
 * EXPECTED RESULT: Alert "Product added" muncul
 *
 * @author Abdul Aziz Permana
 * @version 3.0.0
 */

import dto.pages.DemoLoginPage
import dto.pages.DemoProductPage

import utils.EvidenceReporter
import utils.PdfReportKeyword

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

// ======================================================
// START TEST
// ======================================================

WebUI.comment("════════════════════════════════════════")
WebUI.comment("▶ START: ${TEST_CASE_ID}")
WebUI.comment("════════════════════════════════════════")

EvidenceReporter.initEvidence(TEST_CASE_ID)

// ======================================================
// INIT PAGE OBJECT
// ======================================================

DemoLoginPage loginPage =
	new DemoLoginPage()

DemoProductPage productPage =
	new DemoProductPage()

try {

	// ======================================================
	// STEP 1: OPEN STORE
	// ======================================================

	WebUI.comment(
		"Step 1: Open Browser & Navigate"
	)

	WebUI.openBrowser('')

	WebUI.navigateToUrl(
		GlobalVariable.BASE_URL
	)

	WebUI.maximizeWindow()

	EvidenceReporter.captureStep(
		1,
		'Open Store Page',
		'PASS'
	)

	// ======================================================
	// STEP 2: LOGIN
	// ======================================================

	WebUI.comment(
		"Step 2: Login"
	)

	WebUI.comment(
		"Username: ${USERNAME}"
	)

	loginPage.login(
		USERNAME,
		PASSWORD
	)

	loginPage.waitForWelcomeText()

	boolean isLoginSuccess =
		loginPage.isWelcomeTextVisible()

	if(isLoginSuccess) {

		WebUI.comment(
			"✓ Login Success"
		)

		EvidenceReporter.captureStep(
			2,
			'Login Success',
			'PASS'
		)

	} else {

		throw new Exception(
			'Login failed'
		)
	}

	// ======================================================
	// STEP 3: SELECT PRODUCT
	// ======================================================

	WebUI.comment(
		"Step 3: Select Product"
	)

	WebUI.comment(
		"Product: ${PRODUCT_NAME}"
	)

	productPage.clickProduct(
		PRODUCT_NAME
	)

	WebUI.delay(2)

	EvidenceReporter.captureStep(
		3,
		'Product Selected',
		'PASS'
	)

	// ======================================================
	// STEP 4: ADD TO CART
	// ======================================================

	WebUI.comment(
		"Step 4: Add Product To Cart"
	)

	productPage.clickAddToCart()

	// Tunggu alert
	WebUI.waitForAlert(5)

	// Ambil alert text
	String alertText =
		WebUI.getAlertText()

	WebUI.comment(
		"Alert: ${alertText}"
	)

	// Verify alert
	WebUI.verifyMatch(
		alertText,
		'Product added.',
		false
	)

	// Accept alert dulu
	WebUI.acceptAlert()

	WebUI.delay(1)

	// Baru screenshot
	EvidenceReporter.captureStep(
		4,
		"Product Added - Alert: ${alertText}",
		'PASS'
	)

	// ======================================================
	// STEP 5: GENERATE PDF
	// ======================================================

	PdfReportKeyword.generateReport(
		TEST_CASE_ID,
		'Verify product added to cart',
		'Abdul Aziz Permana',
		EvidenceReporter.getStepList()
	)

	EvidenceReporter.captureStep(
		5,
		'PDF Report Generated',
		'PASS'
	)

	WebUI.comment(
		"✓ PASSED: ${TEST_CASE_ID}"
	)

} catch(Exception e) {

	WebUI.comment(
		"✗ FAILED: ${e.getMessage()}"
	)

	EvidenceReporter.captureStep(
		99,
		"ERROR: ${e.getMessage()}",
		'FAIL'
	)

	throw e

} finally {

	WebUI.closeBrowser()

	WebUI.comment(
		"Browser closed"
	)
}

WebUI.comment("════════════════════════════════════════")
WebUI.comment("END TEST")
WebUI.comment("════════════════════════════════════════")