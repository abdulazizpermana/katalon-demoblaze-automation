// Include/scripts/groovy/dto.pages/DemoLoginPage.groovy
package dto.pages

import dto.locator.DemoLoginPageObjLoc       // ← import locator
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

/**
 * Aksi-aksi yang tersedia di halaman Login Demoblaze.
 * Tidak mengandung xpath — semua locator ada di DemoLoginPageObjLoc.
 *
 * @author Abdul Aziz Permana - COE Team
 * @version 2.0.0
 * @date 2026-05-20
 * @see JIRA Ticket: QA-STORE-001
 */
class DemoLoginPage {

    /**
     * Klik tombol Sign in di navbar untuk membuka modal login.
     */
    void openLoginModal() {
        WebUI.comment("Click Sign in navbar")
        WebUI.click(DemoLoginPageObjLoc.SIGN_IN_NAV_BUTTON())
        WebUI.waitForElementVisible(DemoLoginPageObjLoc.LOGIN_MODAL(), 10)
        WebUI.comment("Login modal visible")
    }

    void inputUsername(String username) {
        WebUI.comment("Input username: ${username}")
        WebUI.waitForElementVisible(DemoLoginPageObjLoc.USERNAME_FIELD(), 10)
        WebUI.setText(DemoLoginPageObjLoc.USERNAME_FIELD(), username)
    }

    void inputPassword(String password) {
        WebUI.comment("Input password")
        WebUI.waitForElementVisible(DemoLoginPageObjLoc.PASSWORD_FIELD(), 10)
        WebUI.setText(DemoLoginPageObjLoc.PASSWORD_FIELD(), password)
    }

    void clickLogin() {
        WebUI.comment("Click login button")
        WebUI.click(DemoLoginPageObjLoc.LOGIN_BUTTON())
    }

    void waitForWelcomeText(int timeoutSeconds = 15) {
        WebUI.waitForElementVisible(DemoLoginPageObjLoc.WELCOME_NAV_TEXT(), timeoutSeconds)
    }

    void login(String username, String password) {
        openLoginModal()
        inputUsername(username)
        inputPassword(password)
        clickLogin()
    }

    boolean isWelcomeTextVisible() {
        waitForWelcomeText()
        return WebUI.verifyElementVisible(DemoLoginPageObjLoc.WELCOME_NAV_TEXT())
    }
}