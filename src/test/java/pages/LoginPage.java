package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css=".menu-title")
    public WebElement Girişyapbutton1Loc;

    @FindBy(css=".dropdown-item.sign-in")
    public WebElement Girişyapbutton2Loc;

    @FindBy(xpath = "//input[@placeholder='E-Posta']")
    public WebElement EpostaLoc;

    @FindBy(xpath = "//input[@placeholder='Şifre']")
    public WebElement SifreLoc;

    @FindBy(css = ".btn.login-btn.form-control")
    public WebElement GirişyapLoginLoc;

    @FindBy(css = "#logged_in_username>a>span")
    public WebElement nameLoc;

    @FindBy(css = ".error-label")
    public WebElement warningmessageLoc;



}
