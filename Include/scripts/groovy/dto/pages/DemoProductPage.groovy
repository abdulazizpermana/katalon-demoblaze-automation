package dto.pages

import com.kms.katalon.core.testobject.ConditionType
import dto.locator.DemoProductPageObjLoc
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DemoProductPage extends DemoProductPageObjLoc {

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