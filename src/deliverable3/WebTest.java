package deliverable3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;



public class WebTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	// Start at the home page for Hoodpopper for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	/**
	 * 1.
	 * As a user/Ruby programmer,
	 * I would like to tokenize my Ruby code,
	 * So that I can know what the result is after tokenized
	 * @author Zhuoqun Wang
	 *
	 */	
	
	// Given that my code is "a = 1"
	// When I click Tokenize button
	// Then I should see that it contains the tokens
	// "on_ident", "on_sp", "on_op", "on_int"
	@Test
	public void testTokenizeHas() {
		System.out.println("contains on_sp, on_int?");
		try {
			driver.findElement(By.id("code_code")).sendKeys("a = 1");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])"));
			System.out.println(btn.getAttribute("value"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("on_ident"));
			assertTrue(codetxt.contains("on_sp"));
			assertTrue(codetxt.contains("on_op"));
			assertTrue(codetxt.contains("on_int"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that my code is "a=b"
	// When I click Tokenize button
	// Then I should NOT see that it contains 
	// "on_int" and "on_sp"
	@Test
	public void testTokenizeNotHas() {

		System.out.println("contains on_sp, on_int?");
		try {
			driver.findElement(By.id("code_code")).sendKeys("a=b");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertFalse(codetxt.contains("on_sp"));
			assertFalse(codetxt.contains("on_int"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
		
	/**
	 * 2.
	 * As a user/Ruby programmer,
	 * I would like to parse my Ruby code,
	 * So that I can know what the result is after parsed
	 * @author Zhuoqun Wang
	 *
	 */	
	
	// Given that my code is "a = 1+2"
	// When I click Parse button
	// Then I should see that it contains
	// "a", "1", and "1"
	@Test
	public void testParseAssign() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("a = 1+2");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("a"));
			assertTrue(codetxt.contains("1"));
			assertTrue(codetxt.contains("+"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that my code is "a = 1"
	// When I click Parse button
	// Then I should NOT see that it contains Whitespace
	// like "on_dent", "on_int" and "="
	@Test
	public void testParseAssignHasNoWhite() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("a = 1");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertFalse(codetxt.contains("on_ident"));
			assertFalse(codetxt.contains("on_int"));
			assertFalse(codetxt.contains("="));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
		
	// Given that my code is "puts 'Zhuoqun is a genius '"
	// When I click Parse button
	// Then I should see that it contains
	// "puts", and "Zhuoqun is a genius"
	@Test
	public void testParsePuts() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("puts \"Zhuoqun is a genius \"");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("puts"));
			assertTrue(codetxt.contains("Zhuoqun is a genius"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that my code is "puts 'a = 1'"
	// When I click Parse button
	// Then I should see that it contains
	// "puts", and "a = 1"
	@Test
	public void testParsePutsEqual() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("puts \"a = 1 \"");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("puts"));
			assertTrue(codetxt.contains("="));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	
	/**
	 * 3.
	 * As a user/Ruby programmer,
	 * I would like to compile my Ruby code,
	 * So that I can know what the result is after compiled
	 * @author Zhuoqun Wang
	 *
	 */	
	
	// Given that my code is "a = 1+2*1/2-1"
	// When I click Parse button
	// Then I should see that it contains
	// "opt_plus", "opt_minus", "opt_div", "opt_mult"
	@Test
	public void testCompileAssignHasOprt() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("a = 1+2*1/2-1");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("opt_plus"));
			assertTrue(codetxt.contains("opt_minus"));
			assertTrue(codetxt.contains("opt_div"));
			assertTrue(codetxt.contains("opt_mult"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that my code is "a + b"
	// When I click Parse button
	// Then I should Not see that it contains
	// "a", "b"
	@Test
	public void testCompileAssignHasValue() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("a + b");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("a"));
			assertTrue(codetxt.contains("b"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	
	// Given that my code is "puts "a = a + 1""
	// When I click Parse button
	// Then I should see that it contains
	// "putstring", and "a = a + 1" 
	@Test
	public void testCompilePuts() {
		try {
			driver.findElement(By.id("code_code")).sendKeys("puts \"a = a + 1\"");

			WebElement btn = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
			btn.click();
			WebElement code = driver.findElement(By.tagName("code"));
			String codetxt = code.getText();
			assertTrue(codetxt.contains("putstring"));
			assertTrue(codetxt.contains("a = a + 1"));
			assertFalse(codetxt.contains("a= a + 1"));
		} catch (NoSuchElementException nseex) {
			fail();
		}
	}
	

