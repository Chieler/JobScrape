import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DiceScraper extends ParentScraper{
    //String to hold link
    private final String link;
    //Int that will be used to sort and find desired postings
    private int daysAgoPosted;

    //Constructor with fields
    //1. driver => WebDriver
    //2. location => location we want for the job
    //3. jobType => what type of software Job => what if I used an enum here
    //4. link => link to website
    //5. daysCutOff => cutOff mark for days posted
    public DiceScraper(WebDriver driver, String location, String jobType, String link, int daysCutoff){

        //Calls super constructor
        super(driver, location, jobType, link);
        //sets link to field in DiceScraper class to help keep track
        this.link = link;
        //sets daysCutOff value, allowed t be null
        this.daysAgoPosted = daysCutoff;
    }

    //getter for days ago posted
    public int getDaysAgoPosted(){
        return this.daysAgoPosted;
    }
    //setter for daysAgoPosted
    public void setDaysAgoPosted(int days){
        this.daysAgoPosted = days;
    }

    //Method to get to the job board with desired criterias
    public void findMatchingJobBoard(){
        //Navigates to website
        navigateToWebSite();

        //Singles out jobType as an input and sends key
        WebElement jobType = getElementById("typeaheadInput");
        jobType.sendKeys(getJobType());

        //Enters the desired location of the job
        WebElement locationElement = getElementById("google-location-search");
        locationElement.sendKeys(getLocation());

        //Clicks the search key
        WebElement searchKey = getElementById("submitSearch-button");
        searchKey.click();

    }
    
    // public WebElement dropDown(String xpath){
    //     //waits until job card visw loaded
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
    //     WebElement jobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    
    //     //Accesses dropdown
    //     WebElement dropDownElement = getElementByXpath(StaticMethods.diceDropDownXpath);
    //     Select dropdown = new Select(dropDownElement);
    //     //chooses the largest dropdown option
    //     dropdown.selectByIndex(3);
    //     return jobCard;
    // }

    //Takes xpath as input and gets Job Title, Company, and Description
    public String getJobs(String xpath){
        //waits until job card visw loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement jobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    
        //Accesses dropdown
        WebElement dropDownElement = getElementByXpath(StaticMethods.diceDropDownXpath);
        Select dropdown = new Select(dropDownElement);
        //chooses the largest dropdown option
        dropdown.selectByIndex(3);

        //gets days posted ago
        WebElement daysPostedAgoChild = jobCard.findElement(By.xpath("./div/div[2]/div[1]/div[2]/span"));
        String numDays = StaticMethods.returnIntInString(daysPostedAgoChild.getText());
        int num = Integer.parseInt(numDays);

        //sifts
        if(num>daysAgoPosted){
            return null;
        }

        //gets child element that contains job title
        WebElement titleChild = jobCard.findElement(By.xpath("./div/div[1]/div/div[2]/div[1]/h5/a"));
        //gets company name
        WebElement companyChild = jobCard.findElement(By.xpath("./div/div[1]/div/div[2]/div[1]/div/a"));

        //returns
        String jobDescription = "Title: " + titleChild.getText() + " Company: " + companyChild.getText() + " Days posted ago: "+ numDays;
        return jobDescription;

    }
    
    public Set<String> returnJobs(String xpath){
        Set<String> jobs = new HashSet<String>();
        for(int i =1; i<10; i++){
            String jobDescription = getJobs(xpath +"[" + i + "]");
            if(jobDescription!=null){
                System.out.println(jobDescription);
                jobs.add(jobDescription);
            }
        }
        return jobs;   
    }
    
}
