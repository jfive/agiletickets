package br.com.caelum.agiletickets.controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: rodrigoalmeida
 * Date: 06/04/13
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class LoginPageObject {


    private final WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    private void acessarHome() {
        driver.get("http://online.caelum.com.br");
    }


    public void login(String login, String senha) {
        acessarHome();
        driver.findElement(By.name("email")).sendKeys("chibata@caelum.com.br");
        driver.findElement(By.name("password")).sendKeys("123");
        driver.findElement(By.className("form-login")).submit();
    }

    public List<WebElement> getMessageErro(){
        return driver.findElement(By.className("errors")).findElements(By.tagName("li"));
    }

}
