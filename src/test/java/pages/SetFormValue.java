package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class SetFormValue extends BasePage{
	
	
	public SetFormValue(WebDriver driver) {
		super(driver);	
	}

	
	@FindBy(id="name")
	WebElement userName;
	
	@FindBy(id="organizationName")
	WebElement organizationName;
	
	@FindBy(id="contactNumber")
	WebElement contactNumber;
	
	@FindBy(id="officialEmailId")
	WebElement officialEmailId;
	
	@FindBy(id="organizationSize")
	WebElement organizationSize;
	
	@FindBy(id="interestedIn")
	WebElement interestedIn;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement submitBtn;
	
	@FindBy(xpath="//div[contains(text(),'THANK YOU')]")
	WebElement thankyouText;
	
	@FindBy(xpath="//span[@class='nav-interact']")
	WebElement clickForCorporateBtn;
	
	@FindBy(partialLinkText="Wellness Plans")
	WebElement wellnessBtn;
	
	public SetFormValue setValue;
	
	public void clickForCorporate() {
		log.info("<--------------------- Click the Form Button --------------------->");
		clickForCorporateBtn.click();	
		wellnessBtn.click();
	}
	
	
	public void setName(String name) {
		log.info("<--------------------- Setup the Name --------------------->");
		userName.sendKeys(name);
	}
	
	public void setOrgName(String orgName) {
		log.info("<--------------------- Setup the Organization Name --------------------->");
		organizationName.sendKeys(orgName);
	}
	
	public void setPhoneNo(String phoneNo) {
		log.info("<--------------------- Setup the Phone Number --------------------->");
		contactNumber.sendKeys(phoneNo);
	}
	public void setEmailId(String orgEmail) {
		log.info("<--------------------- Setup the Email ID --------------------->");
		officialEmailId.sendKeys(orgEmail);
	}
	public void setOrgSize(String orgSize) {
		log.info("<--------------------- Setup the Organization Size --------------------->");
		organizationSize.click();
		new Select(organizationSize).selectByVisibleText(orgSize);
	}
	
	public void setInterest(String interest) {
		log.info("<--------------------- Setup the Interest --------------------->");
		interestedIn.click();
		new Select(interestedIn).selectByVisibleText(interest);
	}
	

	public String clickSubmitBtn() {
		String status = "";
		
			log.info("<--------------------- Setup the Click Button --------------------->");
			boolean btnState = submitBtn.isEnabled();
		
			if(btnState) {
				submitBtn.click();			
				status=getStatus();		
			}else {
				System.out.println("\n --------------- Button is Disabled --------------- \n");
				System.out.println("Enter Valid Data then button will Enabled for Submit the Form");
				status = "Button is Disabled";		
			}
			
			return status;
		
		
		
	}
	
	public String getStatus() {
		log.info("<--------------------- Setup the Get Status --------------------->");
		System.out.println("\n\t --------------- Submitted Successfully --------------- \n");
		System.out.println("Successfull Booking : "+thankyouText.getText());
		return thankyouText.getText();
	}
}
