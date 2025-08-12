package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver; // Global -> this will get life inside the if statements base on the browser
								// mentioned in the global resource file
	public LandingPage landingPage;

	public WebDriver InitializeDriver() throws IOException {
		// Properties class is a java class for reading global properties
		Properties prop = new Properties();

		// the System.getPropety("user.dir") -> will get you the path of your current
		// project
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\ResourcesGrid\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");    //can read system level properties, even from maven cmd
	

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\Lindokuhle Makanda\\BrowserDriversGrid\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\Lindokuhle Makanda\\BrowserDriversGrid\\msedgedriver.exe");
			driver = new EdgeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToHashMap(String jsonPath) throws IOException {

		// Reading the json to string
		String jsonContent = FileUtils.readFileToString(new File(jsonPath),
				StandardCharsets.UTF_8);

		// String to HashMap -> Jackson Databind get it from maven and copy it in your
		// POM
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

		// This is how the list is going to be -> {map, map}

	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts =  (TakesScreenshot)driver;
		//File source = ts.getScreenshotAs(OutputType.FILE);
		
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") +"\\Screenshots" +  testCaseName + ".png" );
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") +"\\Screenshots" +  testCaseName + ".png";
	}

	// you need to set this to always run because if you have groups and they see
	// that the BeforeMethod is available but not tagged with their name
	// it will cause failure, also you cannot tag the BeforeMethod with a group
	// name, what if there are other groups, then it means you will have the same
	// issue
	// Hence why use alwaysRun=true
	@BeforeMethod(alwaysRun = true) // LandingPage
	public LandingPage launchApplication() throws IOException {
		driver = InitializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		 return new LandingPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

}
