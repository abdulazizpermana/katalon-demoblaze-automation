// Include/scripts/groovy/dto.pages/DemoLoginPage.groovy
package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.driver.DriverFactory

/**
 * Representasi halaman Login pada aplikasi Demoblaze.
 * @author Abdul Aziz Permana - COE Team
 * @version 1.1.0
 * @date 2026-05-20
 * @see JIRA Ticket: QA-STORE-001
 */
class DemoLoginPage {

    // =========================================================
    // LOCATORS
    // =========================================================

    private TestObject createTestObject(String xpath) {
        TestObject to = new TestObject()
        to.addProperty('xpath', ConditionType.EQUALS, xpath)
        return to
    }

    /** Tombol "Sign in" di navbar atas */
    TestObject getSignInNavButton() {
        return createTestObject("//a[@id='login2']")
    }

    /** Modal container login */
    TestObject getLoginModal() {
        return createTestObject("//div[@id='logInModal'][@style='display: block;']")
    }

    /** Input username di dalam modal */
    TestObject getUsernameField() {
        return createTestObject("//input[@id='loginusername']")
    }

    /** Input password di dalam modal */
    TestObject getPasswordField() {
        return createTestObject("//input[@id='loginpassword']")
    }

    /** Tombol Log in di dalam modal */
    TestObject getLoginButton() {
        return createTestObject("//button[normalize-space()='Log in']")
    }

    /** Elemen navbar yang muncul setelah login berhasil */
    TestObject getWelcomeNavText() {
        return createTestObject("//a[@id='logout2']")
    }

    // =========================================================
    // ACTIONS
    // =========================================================

    /**
     * Klik tombol Sign in di navbar untuk membuka modal login.
     * Wajib dipanggil sebelum inputUsername / inputPassword.
     */
    void openLoginModal() {
        WebUI.comment("Click Sign in navbar to open login modal")
        WebUI.click(getSignInNavButton())
        // Tunggu modal benar-benar visible sebelum lanjut
        WebUI.waitForElementVisible(getLoginModal(), 10)
        WebUI.comment("Login modal is now visible")
    }

    /**
     * Mengisi field username pada form login.
     * @param username nilai username yang akan diinput
     */
    void inputUsername(String username) {
        WebUI.comment("Input username: ${username}")
        WebUI.waitForElementVisible(getUsernameField(), 10)
        WebUI.setText(getUsernameField(), username)
    }

    /**
     * Mengisi field password pada form login.
     * @param password nilai password yang akan diinput
     */
    void inputPassword(String password) {
        WebUI.comment("Input password")
        WebUI.waitForElementVisible(getPasswordField(), 10)
        WebUI.setText(getPasswordField(), password)
    }

    /**
     * Menekan tombol Log in pada modal login.
     */
    void clickLogin() {
        WebUI.comment("Click login button")
        WebUI.click(getLoginButton())
    }

    /**
     * Menunggu welcome text muncul di navbar setelah login.
     * @param timeoutSeconds maksimal waktu tunggu
     */
    void waitForWelcomeText(int timeoutSeconds = 15) {
        WebUI.waitForElementVisible(getWelcomeNavText(), timeoutSeconds)
    }

    /**
     * Kombinasi aksi login lengkap:
     * buka modal → input username → input password → klik login.
     * @param username nilai username
     * @param password nilai password
     */
    void login(String username, String password) {
        openLoginModal()          // ← ini yang sebelumnya hilang
        inputUsername(username)
        inputPassword(password)
        clickLogin()
    }

    /**
     * Cek apakah welcome text di navbar terlihat.
     * @return true jika elemen terlihat
     */
    boolean isWelcomeTextVisible() {
        waitForWelcomeText()
        return WebUI.verifyElementVisible(getWelcomeNavText())
    }
}