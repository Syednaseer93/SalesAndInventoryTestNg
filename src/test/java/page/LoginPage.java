package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import genericutilities.WebDriverUtility;

public class LoginPage {

	public LoginPage(WebDriver driver){
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="user")
	private WebElement loginUsername;

	@FindBy(name="password")
	private WebElement loginPassword;

	@FindBy(name="btnlogin")
	private WebElement loginButton;


	public void setUsername(String un) {
		loginUsername.sendKeys(un);
	}

	public void setPassword(String pw) {
		loginPassword.sendKeys(pw);
	}

	public void clickLoginButton() {
		loginButton.click();
	}
	public void enterLoginDetailsAndSubmit(String un, String pw, WebDriver driver) {
		loginUsername.sendKeys(un);
		loginPassword.sendKeys(pw);
		loginButton.click();
		WebDriverUtility wu= new WebDriverUtility();
		wu.acceptjSAlert(driver);
		
	}
}
