package br.com.caelum.agiletickets.controllers;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 15:51
 * To change this template use File | Settings | File Templates.
 */
public class OnlineTest {

    private FirefoxDriver driver;

    @BeforeClass
    public void abrirBrowser() {
        driver = new FirefoxDriver();
    }


    @Test
    public void verificaSenhaInvalida() {

        LoginPageObject loginPage = new LoginPageObject(driver);

        loginPage.login("chibata@caelum.com.br","123");
        Assert.assertTrue(loginPage.getMessageErro().get(0).getText().contains("inválida!"));

    }

    @AfterClass
    private void fecharBrowser() {
        driver.close();
    }


}
