package pages;

import org.junit.Assert;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;

public class Actions extends BrowserUtils {
    LoginPage loginPage = new LoginPage();

    public void login(String email, String password) {
        loginPage.EpostaLoc.sendKeys(ConfigurationReader.get(email));
        BrowserUtils.waitFor(1);
        loginPage.SifreLoc.sendKeys(ConfigurationReader.get(password));
        BrowserUtils.waitFor(1);
        loginPage.GirişyapLoginLoc.click();
    }

    public  void login2(String email, String password){
        loginPage.EpostaLoc.sendKeys(email);
        BrowserUtils.waitFor(1);
        loginPage.SifreLoc.sendKeys(password);
        BrowserUtils.waitFor(1);
        loginPage.GirişyapLoginLoc.click();

    }

    public void assertLogin(){
        String expectedName= "Mustafa Aluç";
        Assert.assertEquals("Verify userName",expectedName,loginPage.nameLoc.getText());
    }

    public void assertFalseLogin(){
        String expectedmessage="Bilinmeyen kullanıcı veya eşleşmeyen şifre.";
        Assert.assertEquals(expectedmessage,loginPage.warningmessageLoc.getText());
    }

    public void assertEmptyLogin(){
        String expectedmessage="Bu alanın doldurulması zorunludur.";
        Assert.assertEquals(expectedmessage,loginPage.warningmessageLoc.getText());
    }

}