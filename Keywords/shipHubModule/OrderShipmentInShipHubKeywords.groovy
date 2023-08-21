package shipHubModule;

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.event.KeyEvent

import org.openqa.selenium.Alert
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.driver.DriverFactory;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class OrderShipmentInShipHubKeywords {

	@Keyword
	def VerifyafterLoginuserredirectedtoOrderShiomentpagesuccessfully() {
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordershipment'))
		// Get the title of the current window
		String windowTitle = WebUI.getWindowTitle()
		// Print the title
		println("Window Title: " + windowTitle)
		WebUI.comment("Here we Are Verifying the user redirected to ordershiment page after login")
		WebUI.verifyElementVisible(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordershipment'))
		WebUI.delay(5)
	}

	@Keyword
	def SelectDeliveryDateAndPrintAllOrders() {
		WebUI.comment("Here we Are Select Delivery Date")
		WebUI.delay(5)
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/SelectDeliveryDate'))

		String day = GlobalVariable.Day;
		String month = GlobalVariable.Month;
		String year = GlobalVariable.Year;

		String[] arr // Declare the 'arr' variable outside the loop

		while (true) {
			String monthyear = DriverFactory.getWebDriver().findElement(By.xpath("(//th[@class='date-switch'])[3]")).getText()
			println(monthyear)
			arr = monthyear.split(" ") // Assign value to the 'arr' variable

			String mon = arr[0]
			String yr = arr[1]
			if (mon.equalsIgnoreCase(month) && yr.equals(year)) {
				break
			} else {
				WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/NextMonthArrow'))
			}
		}

		List<WebElement> dates = DriverFactory.getWebDriver().findElements(By.xpath("(//table[@class=' table-condensed'])[3]//tr//td[@class='day  ']"))
		for (WebElement ele : dates) {
			String dt = ele.getText()
			println(dt)
			if(dt.contains(day)) {
				ele.click()
				WebUI.delay(10)
				break

			}
		}

		//		int row=DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']/tbody/tr")).size()
		//		println(row)
		//		int col=DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']/thead/tr/th")).size()
		//		println(col)
		//
		//		for(int r=1; r<=row; r++)
		//		{
		//			for(int c=1; c<=col; c++)
		//			{
		//				List<WebElement> data=DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr["+r+"]/td["+c+"]"))
		//				for(WebElement ele:data)
		//				{
		//					String dts=ele.getText()
		//					println(dts+"     ")
		//				}
		//			}
		//			println()
		//		}
	}



	@Keyword
	def VerifyCustomerPlacedOrderIdIsDisplayingInShipHubSuccessfully() {
		WebUI.comment("Here we Are Verifying Customer Placed Order ID is Displaying In ShipHub Successfully")
		List<WebElement> orderElements = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr/td[3]"))
		String paymentOrderId = GlobalVariable.PaymentOrderID

		for (WebElement ID : orderElements) {
			WebUI.verifyEqual(ID.getText(), paymentOrderId)
			break
		}
	}


	@Keyword
	def VerifyCustomerPlacedOrderIdIsDisplayingZonewiseSuccessfully() {
		String zoneName = GlobalVariable.SelectZone
		println(zoneName)
		WebUI.comment("Here we Are Verifying Customer Placed Order ID is Displaying In ShipHub when zone is selected Successfully")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/SelectZone'))
		Mobile.delay(2)
		List<WebElement> selectzone = DriverFactory.getWebDriver().findElements(By.xpath("//label[text()]"))
		for (WebElement zone : selectzone) {
			if(zone.getText().contains(zoneName))
			{

				WebUI.delay(2)
				zone.click()
				Mobile.delay(5)
				break
			}
		}

		List<WebElement> orderElements1 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr/td[3]"))
		String paymentOrderId1 = GlobalVariable.PaymentOrderID

		for (WebElement ID1 : orderElements1) {
			Mobile.delay(2)
			WebUI.verifyEqual(ID1.getText(), paymentOrderId1)
			break
		}
	}

	@Keyword
	def VerifyCustemerOrderIdisDisplayingSuccessfullyWhenWeSelectOrderType() {
		String zoneName1 = GlobalVariable.OrderType
		println(zoneName1)
		WebUI.comment("Here we Are Verifying Customer Placed Order ID is Displaying In ShipHub when order type is selected Successfully")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/SelectOrderType'))
		Mobile.delay(2)
		List<WebElement> selectzone1 = DriverFactory.getWebDriver().findElements(By.xpath("//label[text()]"))
		for (WebElement zone1 : selectzone1) {
			if(zone1.getText().contains(zoneName1))
			{


				WebUI.delay(2)
				zone1.click()
				Mobile.delay(5)
				break
			}
		}

		List<WebElement> orderElements2 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr/td[3]"))
		String paymentOrderId2 = GlobalVariable.PaymentOrderID

		for (WebElement ID2 : orderElements2) {
			Mobile.delay(2)
			WebUI.verifyEqual(ID2.getText(), paymentOrderId2)
			break
		}
	}

	@Keyword
	def VerifySearchBoxFieldworkedSuccessfully() {
		WebUI.comment("Here we Are Verifying Search box work successfully")
		WebUI.setText(findTestObject('Object Repository/3.OrderShipmentpageObjects/searchbox'), GlobalVariable.PaymentOrderID)
		WebUI.comment("Here we Are Verifying Search box with orderid successfully")
		def text1=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/verifysearchorderid'))
		println(text1)
		WebUI.verifyEqual(GlobalVariable.PaymentOrderID, text1)
		WebUI.clearText(findTestObject('Object Repository/3.OrderShipmentpageObjects/searchbox'))
		Mobile.delay(5)
		WebUI.setText(findTestObject('Object Repository/3.OrderShipmentpageObjects/searchbox'), GlobalVariable.PaymentCustomerName)
		Mobile.delay(2)
		WebUI.comment("Here we Are Verifying Search box with recipient name successfully")
		def text2=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/verifysearchrecipientname'))
		println(text2)
		WebUI.verifyEqual(GlobalVariable.PaymentCustomerName, text2)
		WebUI.clearText(findTestObject('Object Repository/3.OrderShipmentpageObjects/searchbox'))
		WebUI.delay(5)
	}

	@Keyword
	def VerifyOrderSummaryPagebyClickOrderIdnumber() {
		List<WebElement> orderElements3 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr/td[3]//a"));
		String paymentOrderId3 = GlobalVariable.PaymentOrderID;
		WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10); // Adjust the timeout as needed

		for (WebElement ID3 : orderElements3) {
			String id = ID3.getText();
			println(id);
			if (id.contains(paymentOrderId3)) {
				wait.until(ExpectedConditions.elementToBeClickable(ID3));
				ID3.click();
				break; // Assuming you want to click only the first matching element
			}
		}

		WebUI.verifyElementVisible(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordersummarypage'))
		WebUI.delay(2)
		def customername=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordersummarycustemername'))
		println(customername)
		GlobalVariable.ShipHubCustomerName=customername
		WebUI.verifyEqual(customername, GlobalVariable.PaymentCustomerName)
		def email=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordersummaryemail'))
		println(email)
		GlobalVariable.ShipHubEmail=email
		WebUI.verifyEqual(email, GlobalVariable.PaymentEmail)
		def phoneno=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/ordersummaryphonenumber'))
		println(phoneno)
		GlobalVariable.Shiphubphoneno=phoneno
		WebUI.verifyEqual(GlobalVariable.PaymentPhoneNumbers, phoneno)

		List<WebElement> orderElements4 = DriverFactory.getWebDriver().findElements(By.xpath("(//div[@class='card-body'][1]//ul[@class='p-0'])[2]//li"))
		String paymentOrderId4 = GlobalVariable.PaymentDelliveryAddress

		List<String> addressList = []
		for (WebElement ID4 : orderElements4) {
			String dt1=ID4.getText()
			println(dt1)
			if(dt1.contains(paymentOrderId4))
			{

				WebUI.verifyEqual(ID4.getText(), paymentOrderId4)
				addressList.add(dt1)
				break
			}
		}
		GlobalVariable.ShipHubAddress = addressList
	}


	@Keyword
	def VerifyLabelsData() {

		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/Backbutton'))
		WebUI.delay(5)
		List<WebElement> orderRows = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']/tbody/tr[@role='row']"))
		String paymentOrder = GlobalVariable.PaymentOrderID
		//	WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 20) // Adjust the timeout as needed

		for (int rowIndex = 0; rowIndex < orderRows.size(); rowIndex++) {
			WebElement orderRow = orderRows.get(rowIndex)
			WebElement orderLinkElement = orderRow.findElement(By.xpath(".//td[3]//a"))
			String orderId = orderLinkElement.getText()

			if (orderId.contains(paymentOrder)) {
				// Locate the checkbox within the current order row
				String checkboxXPath = "(//table[@id='datatable4']//tr/td[@class='sorting_1']//input)[" + (rowIndex + 1) + "]"
				//WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkboxXPath)))
				WebElement checkbox = DriverFactory.getWebDriver().findElement(By.xpath(checkboxXPath))
				// Perform the click using JavaScript to avoid click interception
				WebUI.delay(5)
				JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getWebDriver();
				js.executeScript("arguments[0].click();", checkbox);

				break // Assuming you want to click only the first matching element
			}
		}

		String mainHandle = DriverFactory.getWebDriver().getWindowHandle()
		println(mainHandle)
		WebUI.comment("Here we Are Creating label using create label option in orde page successfully")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/createlabel'))
		WebUI.delay(8)
		// Get the main window handle
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_ESCAPE)
		robot.keyRelease(KeyEvent.VK_ESCAPE)
		// Get all window handles
		Set<String> wind = DriverFactory.getWebDriver().getWindowHandles()

		println("Total Window Handles: ${wind.size()}")

		Iterator<String> id=wind.iterator()
		while(id.hasNext())
		{
			String st=id.next()
			println(st)
			DriverFactory.getWebDriver().switchTo().window(st)
			if(DriverFactory.getWebDriver().getTitle().equals("InHouse Labels"))
			{

				WebUI.delay(5)
				WebUI.comment("here we are verifying shiphub label data is displaying as per order details data")
				def MylaporeExpresName=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpagemylaporeexpressaddress'))
				println(MylaporeExpresName)

				WebUI.waitForElementPresent(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpageshipmentdate'), 10)
				def shipmentdate=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpageshipmentdate'))
				println(shipmentdate)

				def customername=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpagecustomername'))
				println(customername)
				WebUI.verifyEqual(GlobalVariable.ShipHubCustomerName, customername)
				List<WebElement> orderElements5 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@name='tblInhouseMultiple'][1]//tbody//td/p"))
				String paymentOrderId5 = GlobalVariable.ShipHubAddress

				for (WebElement ID5 : orderElements5) {
					String dt2=ID5.getText()
					println(dt2)
					if(dt2.contains(paymentOrderId5))
					{

						WebUI.verifyEqual(ID5.getText(), paymentOrderId5)
						break
					}
				}

				def phonenomber=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpagephoneno'))
				println(phonenomber)
				WebUI.verifyEqual(GlobalVariable.Shiphubphoneno, phonenomber)

				def website=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpagesite'))
				println(website)

				WebUI.comment("here we are verifying shiphub PickList label data is displaying as per order details data")
				def customername1=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpickliscustomername'))
				println(customername1)
				WebUI.verifyEqual(GlobalVariable.ShipHubCustomerName, customername1)
				List<WebElement> orderElements6 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@name='tblInhouseMultiple'][2]//tbody//td/p"))
				String paymentOrderId6 = GlobalVariable.ShipHubAddress

				for (WebElement ID6 : orderElements6) {
					String dt6=ID6.getText()
					println(dt6)
					if(dt6.contains(paymentOrderId6))
					{

						WebUI.verifyEqual(ID6.getText(), paymentOrderId6)
						break
					}
				}

				def phonenomber1=WebUI.getText(findTestObject('Object Repository/3.OrderShipmentpageObjects/labelpicklistphoneno'))
				println(phonenomber1)
				WebUI.verifyEqual(GlobalVariable.Shiphubphoneno, phonenomber1)

				DriverFactory.getWebDriver().switchTo().window(mainHandle)

			}
		}


	}




	@Keyword
	def VerifyLabelIsCreatednextToOrderIdInOrderSummarypageShipHubSuccessfully() {
		WebUI.comment("Here we Are Verifying Customer Placed Order ID is Displaying In ShipHub with label created next to orderid in ordersummary page Successfully")
		List<WebElement> orderElements3 = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tr/td[3]//a"));
		String paymentOrderId3 = GlobalVariable.PaymentOrderID;
		WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10); // Adjust the timeout as needed

		for (WebElement ID3 : orderElements3) {
			String id = ID3.getText();
			println(id);
			if (id.contains(paymentOrderId3)) {
				wait.until(ExpectedConditions.elementToBeClickable(ID3));
				ID3.click();
				break; // Assuming you want to click only the first matching element
			}
		}
		WebUI.verifyElementVisible(findTestObject('Object Repository/3.OrderShipmentpageObjects/greenlabelcheckmark'))
	}

	@Keyword
	def VerifyMultipleLabelsCreatedUccessfully() {
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/Backbutton'))
		WebUI.delay(5)
		WebUI.comment("Here we Are Verifying Multiple labels is created Successfully")
		String checkboxPath = "//table[@id='datatable4']//tbody//div[@class='checkbox']//input[@type='checkbox']"
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
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/createlabel'))
		WebUI.delay(8)
		// Get the main window handle
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_ESCAPE)
		robot.keyRelease(KeyEvent.VK_ESCAPE)
	}

	@Keyword
	def SelectDriverandClickAssignedDriver() {
		String drivername = GlobalVariable.SelectDriver
		println(drivername)
		WebUI.comment("Here we Are Verifying Selecting drivers and displaying list of orders assigned to that driver")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/selectdriver'))
		WebUI.delay(2)
		List<WebElement> selectdrive = DriverFactory.getWebDriver().findElements(By.xpath("//label[text()]"))

		for (WebElement zon : selectdrive) {
			String zonText = zon.getText().replaceAll("\\s", "").toLowerCase() // Remove spaces and convert to lowercase
			String driverNameFormatted = drivername.replaceAll("\\s", "").toLowerCase() // Remove spaces and convert to lowercase

			if (zonText.contains(driverNameFormatted)) {
				WebUI.delay(2)
				zon.click()
				WebUI.delay(5)
				break
			}
		}
	}



	@Keyword
	def ClickCreateLabelandVerifyPopup() {
		WebUI.delay(2)
		WebUI.comment("Here we Are Verifying after clicking create label the popup is ganarated successfully when we dont select any order")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/createlabel'))
		//WebUI.delay(5)
		WebUI.acceptAlert()

//		Alert alert = WebUI.a
//		String alertText = alert.getText()
//		println(alertText)
//		WebUI.delay(5)
//		alert.accept()
//		//		WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10);
//		//		Alert alt = wait.until(ExpectedConditions.alertIsPresent());
//		//		String st = alt.getText();
//		//		println(st);
//		//		alt.accept()

	}


	@Keyword
	def SelectMultipleOrdersAndClickCreateLabel() {
		WebUI.comment("Here we Are selecting assigned driver and clicking all order assigned to selected driver")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/selectallcheckbox'))

	}

	@Keyword
	def ClickCreateLabelAndVerifyLabelCodes() {

		String mainHandle = DriverFactory.getWebDriver().getWindowHandle()
		println(mainHandle)
		WebUI.comment("Here we Are Creating label using create label option in orde page successfully")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/createlabel'))
		WebUI.delay(8)
		// Get the main window handle
		Robot robot = new Robot()
		robot.keyPress(KeyEvent.VK_ESCAPE)
		robot.keyRelease(KeyEvent.VK_ESCAPE)
		// Get all window handles
		Set<String> wind = DriverFactory.getWebDriver().getWindowHandles()

		println("Total Window Handles: ${wind.size()}")

		Iterator<String> id=wind.iterator()
		while(id.hasNext())
		{
			String st=id.next()
			println(st)
			DriverFactory.getWebDriver().switchTo().window(st)
			if(DriverFactory.getWebDriver().getTitle().equals("InHouse Labels"))
			{
				WebUI.delay(5)
				List<String> codelist = GlobalVariable.RoutesLabelCode;
				println("itemNamesList: " + codelist);

				List<WebElement> code = DriverFactory.getWebDriver().findElements(By.xpath("//span[@id='spnIHLabelCode']"));

				for (WebElement labelcode : code) {
					String label = labelcode.getText();
					println(label);

					for (String codeItem : codelist) {
						if (label.contains(codeItem)) {
							WebUI.verifyEqual(labelcode, codeItem);
							break;
						}
					}

				}
				DriverFactory.getWebDriver().switchTo().window(mainHandle)
			}


		}
	}
	@Keyword
	def VerifyLogOut() {
		WebUI.comment("Here we Are Clicking logout and verifying the shipHub Login Page is displaying successfully")
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/ExpressV2'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/logout'))
		WebUI.delay(2)
		WebUI.click(findTestObject('Object Repository/3.OrderShipmentpageObjects/logoutyes'))

	}
}


