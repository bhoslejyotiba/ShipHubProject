package shipHubModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable






public class RoutesKeyword {


	@Keyword
	def ClickLoginWithValidCredentialsInRoutes() {
		WebUI.comment("Here we are opening Shiphub login page")
		WebUI.openBrowser(GlobalVariable.RoutesURL)
		WebUI.maximizeWindow()
		WebUI.comment("Here we Are entering the username in user box field")
		WebUI.setText(findTestObject('Object Repository/RoutesObjects/username'), GlobalVariable.username)
		WebUI.comment("Here we Are entering the passwor in password box field")
		WebUI.setText(findTestObject('Object Repository/RoutesObjects/password'), GlobalVariable.password)
		WebUI.comment("Here we are clicking login button")
		WebUI.click(findTestObject('Object Repository/RoutesObjects/Login'))
		// Get the title of the current window
		String windowTitle = WebUI.getWindowTitle()

		// Print the title
		println("Window Title: " + windowTitle)
		WebUI.verifyElementVisible(findTestObject('Object Repository/RoutesObjects/Orderrouting'))

	}


	@Keyword
	def SelectDeliveryDateAndPrintAllOrdersInRoutes() {
		WebUI.comment("Here we Are Select Delivery Date")
		WebUI.click(findTestObject('Object Repository/RoutesObjects/selectDate'))

		String day = GlobalVariable.Day;
		String month = GlobalVariable.Month;
		String year = GlobalVariable.Year;

		String[] arr // Declare the 'arr' variable outside the loop

		while (true) {
			String monthyear = DriverFactory.getWebDriver().findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText()
			println(monthyear)
			arr = monthyear.split(" ") // Assign value to the 'arr' variable

			String mon = arr[0]
			String yr = arr[1]
			if (mon.equalsIgnoreCase(month) && yr.equals(year)) {
				break
			} else {
				WebUI.click(findTestObject('Object Repository/RoutesObjects/nextmonth'))
			}
		}

		List<WebElement> dates = DriverFactory.getWebDriver().findElements(By.xpath("//div[@class='react-datepicker__month']//div[@class='react-datepicker__week']//div"))
		for (WebElement ele : dates) {
			String dt = ele.getText()
			println(dt)
			if(dt.contains(day)) {
				ele.click()
				WebUI.delay(10)
				break

			}
		}
	}

	@Keyword
	def SelectOrderIdandPlanRoutes() {
		WebUI.comment("Here we are Selecting Order Id to plan routs")
		String checkboxPath = "//div[@class='sc-hHLeRK gFYXSL rdt_TableBody']//div[@class='sc-hKMtZM sc-gKXOVf imjOZz dqmQwT rdt_TableCell']//input[@type='checkbox']"
		List<WebElement> checkboxes =DriverFactory.getWebDriver().findElements(By.xpath(checkboxPath))

		int count = 0
		for (WebElement checkbox : checkboxes) {
			if (count < GlobalVariable.CountClickCheckbox) {
				checkbox.click()
				count++
			} else {
				break
			}
		}
		WebUI.click(findTestObject('Object Repository/RoutesObjects/assigndriver'))
		WebUI.selectOptionByLabel(findTestObject('Object Repository/RoutesObjects/selectdriver'), GlobalVariable.SelectDriver, false)
		WebUI.selectOptionByLabel(findTestObject('Object Repository/RoutesObjects/selectorigin'), GlobalVariable.SelectOrigion, false)
		WebUI.click(findTestObject('Object Repository/RoutesObjects/assign'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/RoutesObjects/planroute'))
		WebUI.selectOptionByLabel(findTestObject('Object Repository/RoutesObjects/selectdriver'), GlobalVariable.SelectDriver, false)
		WebUI.click(findTestObject('Object Repository/RoutesObjects/plan'))
		WebUI.delay(4)
		List<WebElement> labelcode = DriverFactory.getWebDriver().findElements(By.xpath("//div[@class='sc-hHLeRK gFYXSL rdt_TableBody']//div[@id='cell-10-undefined']//div[text()][not(contains(., 'Not Assigned'))]"))

		List<String> addressList = []
		for (WebElement code : labelcode) {
			String ds = code.getText()
			println(ds)
			addressList.add(ds) // Add the text to the addressList
		}

		GlobalVariable.RoutesLabelCode = addressList
	}
}