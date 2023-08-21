package shipHubModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class LoginToShipHubKeywords {

	@Keyword
	def OpeningShipHubLoginPage(String URL=GlobalVariable.ShipHubURL) {

		WebUI.comment("Here we are opening Shiphub login page")
		WebUI.openBrowser(URL)
		WebUI.maximizeWindow()
	}

	@Keyword
	def VerifyShipHubLoginPageDisplayedWithURl() {
		WebUI.comment("Here we Are Verifying the ShipHubSignIN page Displayed successfuly or not")
		WebUI.verifyElementVisible(findTestObject('Object Repository/2.ShipHubLoginpageObjects/SignInpage'))
	}

	@Keyword
	def ClickLoginWithValidCredentials() {
		WebUI.comment("Here we Are entering the username in user box field")
		WebUI.setText(findTestObject('Object Repository/2.ShipHubLoginpageObjects/username'), GlobalVariable.username)
		WebUI.comment("Here we Are entering the passwor in password box field")
		WebUI.setText(findTestObject('Object Repository/2.ShipHubLoginpageObjects/userpassword'), GlobalVariable.password)
		WebUI.comment("Here we are clicking login button")
		WebUI.click(findTestObject('Object Repository/2.ShipHubLoginpageObjects/LogInButton'))
		// Get the title of the current window
		String windowTitle = WebUI.getWindowTitle()

		// Print the title
		println("Window Title: " + windowTitle)
	}

	@Keyword
	def VerifytheLoginHappenedsuccessfully() {
		WebUI.comment("Here we Are Verifying the ShipHub login happened successfuly or not")
		WebUI.verifyElementVisible(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordershipment'))
	}
}

