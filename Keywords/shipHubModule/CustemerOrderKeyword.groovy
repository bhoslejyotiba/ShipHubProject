package shipHubModule

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class CustemerOrderKeyword {

	@Keyword
	def CreateCustemerOrder() {

		WebUI.openBrowser(GlobalVariable.CustomerSiteURL)
		WebUI.maximizeWindow()
		//WebUI.navigateToUrl(GlobalVariable.CustomerSiteURL)
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/EnterZipCoder'), GlobalVariable.ZipCode)

		WebUI.delay(5)

		List<WebElement> options = DriverFactory.getWebDriver().findElements(By.xpath("//div[@class='toppings-sec']//div[@class='row']//div//button"))
		int count=options.size();

		for(WebElement value : options)
		{
			String dates= value.getText()
			println(dates)
			if(dates.contains(GlobalVariable.SelectDate))
			{
				value.click()
				break;
			}
		}

		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/ContinueButton'))

		List<String> itemNamesList = GlobalVariable.IteamsNames

		println("itemNamesList: " + itemNamesList)

		List<WebElement> options1 = DriverFactory.getWebDriver().findElements(By.xpath("//div[@class='row products-scroll-height']//div[@class='card product']//div[@class='card-body']//a"))
		int count1=options1.size();
		for(WebElement value1 : options1)
		{
			String dates1= value1.getText().trim();
			println(dates1)
			if(itemNamesList.contains(dates1))
			{

				WebElement addToCartButton = value1.findElement(By.xpath(".//ancestor::div[@class='card-body']//button[text()='add to cart']"))
				WebUI.delay(5)
				addToCartButton.click()

			}
		}
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/ClickAddedIteamCart'))
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/ContinueButton'))

		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/FirstName'), GlobalVariable.FirstName)
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/LastName'), GlobalVariable.LastName)
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/EmailId'), GlobalVariable.Email)
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/PhoneNumber'), GlobalVariable.PhoneNumber)
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/Address'), GlobalVariable.Address)
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/BillingCheckbox'))
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/ContinuePayment'))
		WebUI.delay(10)
		DriverFactory.getWebDriver().switchTo().frame('payFields-iframe-number')
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/Debit Creditcard field'), GlobalVariable.DebitCreditCard)
		DriverFactory.getWebDriver().switchTo().defaultContent()
		DriverFactory.getWebDriver().switchTo().frame('payFields-iframe-name')
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/CardHolderName'), GlobalVariable.CardHolderName)
		DriverFactory.getWebDriver().switchTo().defaultContent()
		DriverFactory.getWebDriver().switchTo().frame('payFields-iframe-expiration')
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/ExpiryDate'), GlobalVariable.ExpiryDate)
		DriverFactory.getWebDriver().switchTo().defaultContent()
		DriverFactory.getWebDriver().switchTo().frame('payFields-iframe-cvv')
		WebUI.setText(findTestObject('Object Repository/1.CustemerOrderObjects/CVV'), GlobalVariable.CVV)
		DriverFactory.getWebDriver().switchTo().defaultContent()
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/Accept refund policy'))
		WebUI.click(findTestObject('Object Repository/1.CustemerOrderObjects/Pay'))

		def OrderNo=WebUI.getText(findTestObject('Object Repository/1.CustemerOrderObjects/OrderNo'))
		GlobalVariable.PaymentOrderID=OrderNo
		println(OrderNo)
		def customername=WebUI.getText(findTestObject('Object Repository/1.CustemerOrderObjects/CustomerName'))
		GlobalVariable.PaymentCustomerName=customername
		println(customername)
		def DelliveryAddress=WebUI.getText(findTestObject('Object Repository/1.CustemerOrderObjects/DeliverryAddress'))
		GlobalVariable.PaymentDelliveryAddress=DelliveryAddress
		println(DelliveryAddress)
		def phonno=WebUI.getText(findTestObject('Object Repository/1.CustemerOrderObjects/Phonenotext'))
		String phoneNumber = phonno.split(",")[0].trim();
		GlobalVariable.PaymentPhoneNumbers=phoneNumber
		println(phonno)
		def email=WebUI.getText(findTestObject('Object Repository/1.CustemerOrderObjects/EmailText'))
		GlobalVariable.PaymentEmail=email
		println(email)

	}
}
