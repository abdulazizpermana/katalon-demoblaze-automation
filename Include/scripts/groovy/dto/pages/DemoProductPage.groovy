package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DemoProductPage {

    private TestObject obj(String xpath) {
        TestObject to = new TestObject()
        to.addProperty("xpath", ConditionType.EQUALS, xpath)
        return to
    }

    // === Locators ===
    TestObject getProductByName(String productName) {
        return obj("//a[normalize-space()='${productName}']")
    }
    TestObject getAddToCartButton() {
        return obj("//a[normalize-space()='Add to cart']")
    }

    // === Actions ===
    void clickProduct(String productName) {
        WebUI.waitForElementVisible(getProductByName(productName), 10)
        WebUI.click(getProductByName(productName))
    }
    void clickAddToCart() {
        WebUI.waitForElementVisible(getAddToCartButton(), 10)
        WebUI.click(getAddToCartButton())
    }
    void handleAlert() {
        WebUI.waitForAlert(5)
        WebUI.verifyAlertPresent(5)
        WebUI.acceptAlert()
    }
}