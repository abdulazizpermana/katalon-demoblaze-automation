package dto.locator

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject

/**
 * Kumpulan locator untuk halaman Login Demoblaze.
 * Hanya berisi xpath/locator — tidak ada aksi WebUI di sini.
 * Jika UI berubah, cukup update file ini saja.
 *
 * @author Abdul Aziz Permana - COE Team
 * @version 1.0.0
 * @date 2026-05-20
 */
class DemoLoginPageObjLoc {

    /**
     * Factory method: buat TestObject dari xpath string.
     * Private karena hanya dipakai internal class ini.
     */
    private static TestObject obj(String xpath) {
        TestObject to = new TestObject()
        to.addProperty('xpath', ConditionType.EQUALS, xpath)
        return to
    }

    // Navbar
    static TestObject SIGN_IN_NAV_BUTTON() {
        return obj("//a[@id='login2']")
    }

    static TestObject WELCOME_NAV_TEXT() {
        return obj("//a[@id='logout2']")
    }

    // Modal Login
    static TestObject LOGIN_MODAL() {
        return obj("//div[@id='logInModal'][@style='display: block;']")
    }

    static TestObject USERNAME_FIELD() {
        return obj("//input[@id='loginusername']")
    }

    static TestObject PASSWORD_FIELD() {
        return obj("//input[@id='loginpassword']")
    }

    static TestObject LOGIN_BUTTON() {
        return obj("//button[normalize-space()='Log in']")
    }
}