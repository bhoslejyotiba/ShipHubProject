package shipHubModule;

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.driver.DriverFactory;
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable




public class ZonesInShipHubKeywords {

	@Keyword
	def ClickZonesDashboard() {
		WebUI.comment("Here we Are Verifying cliking zones zones dashboard")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/Zones'))
	}

	@Keyword
	def VerifyZonesDashboard() {
		WebUI.comment("Here we Are Verifying cliking zones zones dashboard displayed")
		WebUI.verifyElementVisible(findTestObject('Object Repository/4.ZonesObjects/AddZone'))
	}


	@Keyword
	def CreateZoneUsingAddZoneButton() {
		WebUI.comment("Here we Are Clicking Add zone button")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/AddZone'))
		WebUI.comment("Here we Are entering zone name")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/EnterZoneName'), GlobalVariable.ZoneName)
		WebUI.comment("Here we Are selectting carrier name")
		WebUI.selectOptionByLabel(findTestObject('Object Repository/4.ZonesObjects/SelectCarrier'), GlobalVariable.CarrierName, false)
		WebUI.comment("Here we Are enering shipment fee")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/ShipmentFee'), GlobalVariable.ShipmentFee)
		WebUI.comment("Here we Are entering weight")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/Weight'), GlobalVariable.Weight)
		WebUI.comment("Here we Are enering maximum quantity")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/Maximum QTY'), GlobalVariable.MaximumQuantity)
		WebUI.comment("Here we Are selecting service type")
		WebUI.selectOptionByLabel(findTestObject('Object Repository/4.ZonesObjects/ServiceType'), 'UPS Next Day Air®', false)
		WebUI.comment("Here we Are selecting package")
		WebUI.selectOptionByLabel(findTestObject('Object Repository/4.ZonesObjects/Package'), 'Package', false)
		WebUI.comment("Here we selecting package length")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/PackageSizeLenght'), '12')
		WebUI.comment("Here we selecting package breadth")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/PackageSizeBreadth'), '12')
		WebUI.comment("Here we selecting package hight")
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/PackageSizeHight'), '12')
		WebUI.comment("Here we saving add zone")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/SaveAddButton'))
	}

	@Keyword
	def VerifyZonesCreatedSuccessfully() {
		WebUI.comment("Here we verifying zone is created in successfully ")
		List<WebElement> zones = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']/tbody/tr/td[4]"))

		boolean zoneFound = false
		for (WebElement zone : zones) {
			println(zone.getText())
			if (zone.getText().contains(GlobalVariable.ZoneName)) {
				zoneFound = true
				break
			}
		}

		if (zoneFound) {
			println("Zone is present in dashboard")
		} else {
			println("Zone is not present in dashboard")
		}
	}


	@Keyword
	def ClickOnZoneToViewZoneDetails() {
		WebUI.comment("Here we verifying zone is created successfully ")
		List<WebElement> zones1 = DriverFactory.getWebDriver().findElements(By.xpath("//a[text()]"))

		for (WebElement zone1 : zones1) {

			if (zone1.getText().contains(GlobalVariable.ZoneName)) {
				WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10);
				wait.until(ExpectedConditions.visibilityOf(zone1));

				JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver();
				jsExecutor.executeScript("arguments[0].click();", zone1);

				WebUI.comment("Clicked on zone: " +zone1);
				break // No need to continue looping if the zone is found and clicked
			}
		}
	}

	@Keyword
	def VerifyViewZonesDashboard() {
		WebUI.comment("Here we Are Verifying view zone dashboard displayed")
		WebUI.verifyElementPresent(findTestObject('Object Repository/4.ZonesObjects/ViewZoneDashBoard'), 0)
	}

	@Keyword
	def AddZipCodeUsingAddZipButton() {
		WebUI.comment("Here we Are Clicking On Add Zip Code")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/AddZipCode'))

		List<String> names = GlobalVariable.CityName

		println("citynames: " + names)

		List<WebElement> cityname = DriverFactory.getWebDriver().findElements(By.xpath("//div[@id='divSearchResult']//a"))
		int total=cityname.size();
		for(WebElement values : cityname) {
			String dates1= values.getText().trim();
			println(dates1)
			if(names.contains(dates1)) {


				WebElement addToCartButton = values.findElement(By.xpath(".//ancestor::div[contains(@id,'Aheading')]//button[text()='Add']"))
				WebUI.comment("here we adding zip code")
				addToCartButton.click()
			}
		}
		WebUI.comment("here we are clicking on save zope code")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/SaveZipCode'))
	}

	@Keyword
	def VerifyZipCodeAddedToZoneSuccessfully() {
		WebUI.comment("Here we clicking on zone to verify zipcode")
		WebUI.delay(5)
		List<WebElement> zones1 = DriverFactory.getWebDriver().findElements(By.xpath("//a[text()]"))

		for (WebElement zone1 : zones1) {

			if (zone1.getText().contains(GlobalVariable.ZoneName)) {
				WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10);
				wait.until(ExpectedConditions.visibilityOf(zone1));

				JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver();
				jsExecutor.executeScript("arguments[0].click();", zone1);

				WebUI.comment("Clicked on zone: " +zone1);
				break // No need to continue looping if the zone is found and clicked
			}
		}

		List<String> City1 = GlobalVariable.CityName

		println("citynames: " + City1)


		List<WebElement> city = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tbody//td[3]"))
		for(WebElement name:city) {
			String date= name.getText().trim()
			println(date)
			if(City1.contains(date)) {
				println("both are erqual test case is pass")
			}
			else {
				println("test case is fail")
			}
		}
	}
	@Keyword
	def VerifyFilterandClearButtonworkedSuccessfully() {
		WebUI.comment("Here we Are Verifying filter and clear button work successfully")
		WebUI.delay(5)
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/FilerZipCode'), GlobalVariable.FilterZipCode)
		WebUI.delay(3)
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/Filterstate'), GlobalVariable.FilterState)
		WebUI.delay(3)
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/FilterCity'), GlobalVariable.FilterCity)
		WebUI.delay(3)
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/Filterbutton'))
		def text=WebUI.getText(findTestObject('Object Repository/4.ZonesObjects/VerifyFilteredZipcodeText'))
		println(text)
		WebUI.verifyEqual(GlobalVariable.FilterZipCode, text)
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/Clearfilterbutton'))
	}

	@Keyword
	def VerifySearchBoxFieldworkedSuccessfully() {
		WebUI.comment("Here we Are Verifying Search box work successfully")
		WebUI.delay(2)
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/FilterSerchBox'), GlobalVariable.FilterZipCode)
		WebUI.comment("Here we Are Verifying Search box with zip code successfully")
		def text1=WebUI.getText(findTestObject('Object Repository/4.ZonesObjects/VerifyFilteredZipcodeText'))
		println(text1)
		WebUI.verifyEqual(GlobalVariable.FilterZipCode, text1)
		WebUI.clearText(findTestObject('Object Repository/4.ZonesObjects/FilterSerchBox'))
		Mobile.delay(5)
		WebUI.setText(findTestObject('Object Repository/4.ZonesObjects/FilterSerchBox'), GlobalVariable.FilterCity)
		Mobile.delay(2)
		WebUI.comment("Here we Are Verifying Search box with city name successfully")
		def text2=WebUI.getText(findTestObject('Object Repository/4.ZonesObjects/VerifyFilterCityName'))
		println(text2)
		WebUI.verifyEqual(GlobalVariable.FilterCity, text2)
		WebUI.clearText(findTestObject('Object Repository/4.ZonesObjects/FilterSerchBox'))
		Mobile.delay(5)
	}

	@Keyword
	def VerifyEditZoneDashboard() {
		WebUI.comment("Here we Are updating zone")
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/Edit zone'))
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/uodate zone'))
	}

	@Keyword
	def ClickDeletZipCodeUsingDeletButtonSuccessfully() {
		List<WebElement> zones1 = DriverFactory.getWebDriver().findElements(By.xpath("//a[text()]"))

		for (WebElement zone1 : zones1) {

			if (zone1.getText().contains(GlobalVariable.ZoneName)) {
				WebDriverWait wait = new WebDriverWait(DriverFactory.getWebDriver(), 10);
				wait.until(ExpectedConditions.visibilityOf(zone1));

				JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverFactory.getWebDriver();
				jsExecutor.executeScript("arguments[0].click();", zone1);

				WebUI.comment("Clicked on zone: " +zone1);
				break // No need to continue looping if the zone is found and clicked
			}
		}
		WebUI.delay(5)
		WebUI.comment("Here we Are Verifying Zip code should delet using delet button displayed")
		List<WebElement> city = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tbody//td[3]"))
		for(WebElement name:city) {
			String date= name.getText().trim()
			println(date)
			if(date.contains(GlobalVariable.FilterCity)) {
				String deleteButtonXPath = "//table[@id='datatable4']//tbody//td[3][text()='" + GlobalVariable.FilterCity + "']/following-sibling::td//button[text()=' Delete']"

				WebElement deleteButton = DriverFactory.getWebDriver().findElement(By.xpath(deleteButtonXPath))
				WebUI.delay(5)
				deleteButton.click()
				WebUI.click(findTestObject('Object Repository/4.ZonesObjects/DeletAlertYes'))
				Mobile.delay(5)
				break
			}
		}
	}

	@Keyword
	def VerifyZipCodeDeletedFromZoneSuccessfullyt() {
		WebUI.comment("Verifying zipcode is deleted from zones dashboard successfully")

		List<String> cityNames = GlobalVariable.CityName
		String deletedZipCode = GlobalVariable.FilterCity

		boolean zipCodeDeleted = true // Flag to track verification status

		List<WebElement> cityElements = DriverFactory.getWebDriver().findElements(By.xpath("//table[@id='datatable4']//tbody//td[3]"))

		for (WebElement cityElement : cityElements) {
			String cityName = cityElement.getText().trim()

			if (cityName.equals(deletedZipCode)) {
				zipCodeDeleted = false // If the deleted zip code is found, set verification flag to false
				break
			}
		}

		if (zipCodeDeleted) {
			WebUI.comment("Verification: Zip code is deleted from zones dashboard successfully")
		} else {
			WebUI.comment("Verification: Zip code is still present in zones dashboard")
			// You can also log an error here or perform other actions if needed
		}
	}


	@Keyword
	def ClickDeletButtonafromZonesDashboard() {
		WebUI.comment("Here we Are deleting zone")
		WebUI.delay(5)
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/DeleteZone'))
		Mobile.delay(2)
		WebUI.click(findTestObject('Object Repository/4.ZonesObjects/DeletZoneYes'))
		Mobile.delay(5)
	}

	@Keyword
	def verifyZoneDeletedfromZoneDashboardSuccessfully() {
		WebUI.comment("Verifying zone deleted from zone dashboard")
		List<WebElement> zones1 = DriverFactory.getWebDriver().findElements(By.xpath("//a[text()]"))

		for (WebElement zone1 : zones1) {
			if (zone1.getText().equals(GlobalVariable.ZoneName)) {
				WebUI.verifyElementNotPresent(zone1, FailureHandling.CONTINUE_ON_FAILURE, "Zone is still present in zone dashboard")
				break
			}
		}
	}
}










