import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class GithubScraper extends ParentScraper{
    //link for website
    String link;
    //how many days posted ago cutoff
    int daysCutoff;

    //constructor
    public GithubScraper(WebDriver driver, String link,int days){
        super(driver, link);
        this.daysCutoff = days;
        this.link = link;
        
    }

    //Goes to job board
    public void findMatchingJobBoard(){
        //Navigates to website
        navigateToWebSite();
    }

    //Access single job posting
    public String getJobs(String xpath){
        //waits until job card visw loaded
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement jobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        //gets days posted ago
        WebElement daysPostedChild = jobCard.findElement(By.xpath("./td[5]"));

        //gets the date of today
        LocalDate today = LocalDate.now();
        String dayOfJobPosted = daysPostedChild.getText()+" 2024";
        LocalDate dateOfJobPosted = StaticMethods.parseStringToDate(dayOfJobPosted);
        //Uses LocalDate and ChronoUnit to calculate the days between current and date job posted
        long daysPostedAgo = ChronoUnit.DAYS.between(dateOfJobPosted, today);

        //sifts
        if(daysPostedAgo>daysCutoff){
            return null;
        }

        //gets child element that contains job title
        WebElement titleChild = jobCard.findElement(By.xpath("./td[2]"));
        //gets company name
        WebElement companyChild;

        if(!jobCard.findElements(By.xpath("./td[1]/strong/a")).isEmpty()){
            companyChild = jobCard.findElement(By.xpath("./td[1]/strong/a"));
            //two variations
        }else{
            companyChild = jobCard.findElement(By.xpath("./td[1]"));
        }

        //gets job location
        WebElement locationChild = jobCard.findElement(By.xpath("./td[3]"));  
        WebElement linkChild = jobCard.findElement(By.xpath("./td[4]/a"));
        String link = linkChild.getAttribute("href");

        //returns
        String jobDescription = "Title: " +  titleChild.getText() + " Company: " + companyChild.getText() + " Location: "+ locationChild.getText() + "Days posted ago: " + daysPostedAgo + "\n" + "link:   " +  link +"\n" +  "--------";
        return jobDescription;
    }

    public Set<String> returnJobs(String xpath){
        Set<String> jobs = new HashSet<>();
        
        for (int i = 1; i < 20; i++) {
            try {
                String jobDescription = getJobs(xpath + "tr[" + i + "]");
                if (jobDescription != null) {
                    jobs.add(jobDescription);
                }
            } catch (Exception e) {
                System.out.println("Error processing job at index " + i + ": " + e.getMessage());
            }
        }
        return jobs;
    }


}
