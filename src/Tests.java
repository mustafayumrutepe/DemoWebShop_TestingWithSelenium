import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
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

        WaitAndQuit();
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
}
