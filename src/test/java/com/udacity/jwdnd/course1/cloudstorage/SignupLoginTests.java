package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pageobjects.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pageobjects.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SignupLoginTests {

	String firstName = "Dahi";
	String lastName = "Nemutlu";
	String username = "dnemutlu";
	String password = "fXKuVtZvB5ZUFq4p";

	@LocalServerPort
	private int port;

	protected static WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	// Write a Selenium test that verifies that the home page is not accessible without logging in.
	public void testAccessWithoutLogin() {
		driver.get("http://localhost:" + this.port + "/home");
		assertFalse(driver.getTitle() == "Home");
		assertEquals("Login - SuperDuperDrive", driver.getTitle());
	}

	@Test
	// Write a Selenium test that signs up a new user, logs that user in, verifies that they can access the home page,
	// then logs out and verifies that the home page is no longer accessible.
	public void testSignupLoginLogout() throws InterruptedException {
		signup();
		login();
		assertEquals("Home - SuperDuperDrive", driver.getTitle());
		logout();
		assertEquals("Login - SuperDuperDrive", driver.getTitle());
	}

	public void signup(){
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName,lastName,username,password);
	}

	public void login(){
		driver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(username,password);
	}

	public void logout(){
		driver.get("http://localhost:" + this.port + "/home");
		HomePage homePage = new HomePage(driver);
		homePage.logout();
	}

	public HomePage signupAndLogin(){
		signup();
		login();
		return new HomePage(driver);
	}
}
