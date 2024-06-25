import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class Tests extends BaseDriver{

    Actions actions=new Actions(driver);

    @Test
    public void Register(){

        WebElement register= driver.findElement(By.linkText("Register"));
        register.click();

        WebElement gender= driver.findElement(By.id("gender-male"));
        gender.click();

                actions.keyDown(Keys.TAB).sendKeys("Beybun")
                       .keyDown(Keys.TAB).sendKeys("Kulilk")
                       .keyDown(Keys.TAB).sendKeys("bk02@gmail.com")
                       .keyDown(Keys.TAB).sendKeys("111111")
                       .keyDown(Keys.TAB).sendKeys("111111")
                       .keyDown(Keys.TAB).keyDown(Keys.ENTER)
                        .build().perform();

        WebElement verification=driver.findElement(By.xpath("//*[contains( text(),'Your registration completed')] "));
        Assert.assertTrue("The message you were looking for was not found",verification.getText().toLowerCase().contains("completed"));

        WaitAndQuit();
    }


    @Test
    public void NegativeRegister(){

        WebElement register= driver.findElement(By.linkText("Register"));
        register.click();

        WebElement gender= driver.findElement(By.id("gender-male"));
        gender.click();

        actions.keyDown(Keys.TAB).sendKeys("Beybun")
                .keyDown(Keys.TAB).sendKeys("Kulilk")
                .keyDown(Keys.TAB).sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .keyDown(Keys.TAB).sendKeys("111111")
                .keyDown(Keys.TAB).keyDown(Keys.ENTER)
                .build().perform();

        WebElement verification=driver.findElement(By.xpath("//*[text()='The specified email already exists']"));
        Assert.assertTrue("The message you were looking for was not found",verification.getText().toLowerCase().contains("already exists"));

        WaitAndQuit();
    }

    @Test
    public void LogIn(){
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();

        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();

        WebElement verification= driver.findElement(By.linkText("bk02@gmail.com"));
        Assert.assertTrue("You are not log in",verification.getText().contains("bk02@gmail.com"));

        //WaitAndQuit();
    }

    @Test
    public void LogOut(){
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();

        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();

        WebElement logout= driver.findElement(By.linkText("Log out"));
        logout.click();

        List<WebElement> verification= driver.findElements(By.linkText("bk02@gmail.com"));
        Assert.assertFalse("You are not log out",verification.size()>0);
        WaitAndQuit();
    }

    @Test
    public void NegativeLogIn() throws AWTException {
        Robot robot=new Robot();
        List<WebElement> verification= driver.findElements(By.linkText("bk02@gmail.com"));
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        actions.sendKeys("")
                .keyDown(Keys.TAB).sendKeys("")
                .build().perform();
        WebElement loginBtn1= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn1.click();
        Assert.assertFalse("You are log in",verification.size()>0);

        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("")
                .build().perform();
        WebElement loginBtn2= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn2.click();
        Assert.assertFalse("You are log in",verification.size()>0);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);

        actions.keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();
        WebElement loginBtn3= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn3.click();
        Assert.assertFalse("You are log in",verification.size()>0);

        actions.sendKeys("mm@gmail.com")
                .keyDown(Keys.TAB).sendKeys("22")
                .build().perform();
        WebElement loginBtn4= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn4.click();
        Assert.assertFalse("You are log in",verification.size()>0);

        WaitAndQuit();
    }

    @Test
    public void PlacingAnOrder(){
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();

        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();

        WebElement product= driver.findElement(By.linkText("Build your own computer"));
        product.click();
        WebElement hdd= driver.findElement(By.id("product_attribute_16_3_6_18"));
        hdd.click();
        WebElement addToCart= driver.findElement(By.id("add-to-cart-button-16"));
        addToCart.click();
        WebElement ShoppingCart=driver.findElement(By.partialLinkText("Shopping"));
        ShoppingCart.click();

        WebElement country=driver.findElement(By.id("CountryId"));
        Select CountryMenu=new Select(country);
        CountryMenu.selectByVisibleText("Canada");

        WebElement state=driver.findElement(By.id("StateProvinceId"));
        Select StateMenu=new Select(state);
        StateMenu.selectByVisibleText("Nova Scotia");

        WebElement agree= driver.findElement(By.id("termsofservice"));
        agree.click();

        WebElement checkout= driver.findElement(By.id("checkout"));
        checkout.click();

        WebElement country_2= driver.findElement(By.id("BillingNewAddress_CountryId"));
        Select CountryMenu_2=new Select(country_2);
        CountryMenu_2.selectByVisibleText("Canada");

        WebElement city= driver.findElement(By.id("BillingNewAddress_City"));
        city.sendKeys("Semsur");

        actions.keyDown(Keys.TAB).sendKeys("şoreş sok. rizgar apt. no:21")
                .keyDown(Keys.TAB).keyDown(Keys.TAB).sendKeys("02")
                .keyDown(Keys.TAB).sendKeys("123")
                .keyDown(Keys.TAB).keyDown(Keys.TAB).keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();

        WebElement VerifyingAdress= driver.findElement(By.xpath("//option[contains(text(),'Semsur')]"));
        Assert.assertTrue("Adres doğrulanamadı",VerifyingAdress.getText().contains("Semsur"));

        WebElement PickUpInStore= driver.findElement(By.id("PickUpInStore"));
        PickUpInStore.click();

        actions.keyDown(Keys.TAB).keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();

        WebElement cntnBtn= driver.findElement(By.xpath("//input[@value='Continue'][@class='button-1 payment-method-next-step-button']"));
        wait.until(ExpectedConditions.elementToBeClickable(cntnBtn));
        cntnBtn.click();

       WebElement PaymentMethod= driver.findElement(By.xpath("//*[text()='You will pay by COD']"));
       Assert.assertTrue("COD ödeme yöntemi olarak seçilmedi",PaymentMethod.getText().contains("COD"));

        actions.keyDown(Keys.TAB).keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();

        WebElement unitPrice= driver.findElement(By.className("product-unit-price"));
        WebElement totalPrice= driver.findElement(By.className("product-subtotal"));

        Assert.assertEquals(unitPrice.getText(),totalPrice.getText());

       WaitAndQuit();
    }
    @Test
    public void AnsweringSurvey(){
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();
        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();
        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();


        WebElement poll= driver.findElement(By.id("pollanswers-2"));
        poll.click();
        actions.keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();
        List<WebElement> SurveyResults= driver.findElements(By.cssSelector("[class='poll-results']>li"));
        for (WebElement e : SurveyResults)
            System.out.println("e.getText() = " + e.getText());

        Assert.assertTrue("Survey response failed",SurveyResults.get(1).getText().contains("Good"));

        WaitAndQuit();
    }


    @Test
    public void Negative_CouponsGiftCards(){
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();
        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();
        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();


        WebElement topMenu= driver.findElement(By.xpath("//*[contains(text(),'Computers')]"));
        actions.moveToElement(topMenu).build().perform();

        WebElement accessories= driver.findElement(By.xpath("//*[contains(text(),'Accessories')]"));
        actions.moveToElement(accessories).click().build().perform();

        WebElement addMT_toCart= driver.findElement(By.xpath("(//*[@class='button-2 product-box-add-to-cart-button'])[4]"));
        addMT_toCart.click();

        WebElement ShoppingCart=driver.findElement(By.partialLinkText("Shopping"));
        ShoppingCart.click();

        WebElement coupon= driver.findElement(By.name("discountcouponcode"));
        coupon.sendKeys("2121");

        actions.keyDown(Keys.TAB).keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

        WebElement giftCard= driver.findElement(By.name("giftcardcouponcode"));
        giftCard.sendKeys("224");

        actions.keyDown(Keys.TAB).keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();

        WebElement termsOfService=driver.findElement(By.id("termsofservice"));
        termsOfService.click();
        WebElement checkout=driver.findElement(By.id("checkout"));
        checkout.click();
        WebElement continueBtn=driver.findElement(By.cssSelector("[type='button'][title='Continue']"));
        continueBtn.click();

        WebElement PickUpInStore= driver.findElement(By.id("PickUpInStore"));
        PickUpInStore.click();

        actions.keyDown(Keys.TAB).keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();

        WebElement creditCard= driver.findElement(By.id("paymentmethod_2"));
        creditCard.click();

        actions.keyDown(Keys.TAB).keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();
        OptionalWait(2);
        actions.keyDown(Keys.TAB).keyDown(Keys.TAB).keyDown(Keys.TAB)
                .sendKeys("22")
                .build().perform();

        actions.keyDown(Keys.TAB).keyDown(Keys.TAB).keyDown(Keys.TAB).keyDown(Keys.TAB).keyDown(Keys.TAB)
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER)
                .build().perform();

        OptionalWait(2);
        WebElement errorMessage= driver.findElement(By.className("message-error"));
        Assert.assertTrue("Payment information is valid",errorMessage.getText().toLowerCase().contains("wrong"));

        WaitAndQuit();
    }


    @Test
    public void DownloadingTheInvoiceToTheComputer() throws AWTException {
        WebElement login= driver.findElement(By.linkText("Log in"));
        login.click();

        actions.sendKeys("bk02@gmail.com")
                .keyDown(Keys.TAB).sendKeys("111111")
                .build().perform();

        WebElement loginBtn= driver.findElement(By.cssSelector("input[value='Log in']"));
        loginBtn.click();

        WebElement myAccout= driver.findElement(By.linkText("bk02@gmail.com"));
        myAccout.click();

        WebElement Orders= driver.findElement(By.linkText("Orders"));
        Orders.click();

        WebElement details= driver.findElement(By.cssSelector("[value='Details']"));
        details.click();

        WebElement print= driver.findElement(By.linkText("Print"));
        print.click();
        OptionalWait(1);

        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        StringSelection invoice = new StringSelection("invoice");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(invoice, null);

        OptionalWait(1);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);

        WaitAndQuit();
    }
}
