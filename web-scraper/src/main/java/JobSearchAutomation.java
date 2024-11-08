import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class JobSearchAutomation {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() throws InterruptedException, IOException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        
        // Initialize ChromeDriver
        driver = new ChromeDriver();

        // Navigate to the Indeed URL
        driver.navigate().to("https://www.indeed.com/");

        // Maximize the browser window
        driver.manage().window().maximize();
        
     // Create a Scanner object to prompt for user input
        Scanner myObj = new Scanner(System.in);
        System.out.println("What jobs are you looking for ? ");
        String job_Search = myObj.nextLine();
        WebElement searchField = driver.findElement(By.id("text-input-what"));
        searchField.sendKeys(job_Search);
        searchField.sendKeys(Keys.RETURN);
        
     // Set up CSV Writer outside the loop to avoid overwriting
        FileWriter csvWriter = new FileWriter(job_Search.replace(" ", "") + ".csv");
        csvWriter.append("JobTitle,JobCompanyName,JobLocation\n");
        
        for (int j = 1; j < 6; j++) {
        	System.out.println("Scraping page"+j+".........");
        	List<WebElement> jobCards = driver.findElements(By.xpath("//div[@id='mosaic-provider-jobcards']//a[@data-mobtk]")); // Assuming job cards are links with a specific attribute
        	int totcount = jobCards.size();
        	System.out.println("Total Job Cards Found: " + totcount);

        	for (int i = 0; i < totcount; i++) {
        	    IndeedJob iJob = new IndeedJob();

        	    WebElement jobCard = jobCards.get(i);

        	    // Extract the job title within each job card
        	    try {
        	        WebElement jobTitle = jobCard.findElement(By.xpath(".//span[contains(@id, 'jobTitle')]"));
        	        String titleText = jobTitle.getText().replace(",", " ").replace("\n", " ");
        	        //System.out.println("Job Title : " + titleText);
        	        iJob.setJobTitle(titleText);
        	    } catch (NoSuchElementException e) {
        	        System.out.println("Job title not found for job card " + (i + 1));
        	    }

        	    // Extract the company name within each job card
        	    try {
        	        WebElement jobCompName = jobCard.findElement(By.xpath("//*[@id=\"mosaic-provider-jobcards\"]/ul/li["+i+"]/div/div/div/div/div/div/table/tbody/tr/td[1]/div[2]/div/div[1]/span"));
        	        String compNameText = jobCompName.getText().replace(",", " ").replace("\n", " ");
        	        //System.out.println("Job Company Name : " + compNameText);
        	        iJob.setJobCompName(compNameText);
        	    } catch (NoSuchElementException e) {
        	        System.out.println("Company name not found for job card " + (i + 1));
        	    }
        	    // Extract the company location within each job card
        	    try {
        	        WebElement jobLocation = jobCard.findElement(By.xpath("//*[@id=\"mosaic-provider-jobcards\"]/ul/li["+Integer.toString(i)+"]/div/div/div/div/div/div/table/tbody/tr/td[1]/div[2]/div/div[2]"));
        	        String locationText = jobLocation.getText().replace(",", " ").replace("\n", " ");
        	        //System.out.println("Job Location : " + locationText);
        	        iJob.setJobLocation(locationText);
        	    } catch (NoSuchElementException e) {
        	        System.out.println("Location not found for job card " + (i + 1));
        	    }

        	    // You can now add `iJob` to a list or process it further. 
        	 // Write to CSV
                csvWriter.append(iJob.getJobTitle()).append(",")
                         .append(iJob.getJobCompName()).append(",")
                         .append(iJob.getJobLocation()).append("\n");
        	}

	        Thread.sleep(5);
	        WebElement pagei = driver.findElement(By.xpath("//*[@id=\"jobsearch-JapanPage\"]/div/div[5]/div/div[1]/nav/ul/li[6]/a"));
	        pagei.click();
        }
     // Close resources
        csvWriter.flush();
        csvWriter.close();
        //driver.quit();
    }
    

}
