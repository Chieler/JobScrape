import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main{

    public static void main(String[] args) {
        //Initiates Driver and its settings
        AppConfig.init();
        WebDriver driver = AppConfig.getDrive();

        //Dice Scraper/////////////////////////////////
        DiceScraper diceScraper = new DiceScraper(driver, "San Francisco", "Software Engineer", "https://www.dice.com/", 4);

        //Modify the the last argument of constructor for daysCutOff for jobs

        diceScraper.findMatchingJobBoard();
        //without specific job card id so the method can modify it, below is an example of job card with id #1
        ///html/body/dhi-js-dice-client/div/dhi-search-page-container/dhi-search-page/div/div[2]/dhi-search-page-results/div/div[3]/js-search-display/div/div[3]/dhi-search-cards-widget/div/dhi-search-card[1]
        //Stores info in jobsSet
        Set<String> diceJobs  = diceScraper.returnJobs("/html/body/dhi-js-dice-client/div/dhi-search-page-container/dhi-search-page/div/div[2]/dhi-search-page-results/div/div[3]/js-search-display/div/div[3]/dhi-search-cards-widget/div/dhi-search-card");
        //Set that stores jobs scraped from dice
        for (String job : diceJobs) {  
            System.out.println(job);
        }
        //Dice Scraper////////////////////////////////

        //GithubScraper///////////////////////////////
        GithubScraper gitScraper = new GithubScraper(driver, "https://github.com/SimplifyJobs/Summer2025-Internships", 2);
        
        //Modify the the last argument of constructor for daysCutOff for jobs

        gitScraper.findMatchingJobBoard();
        Set<String> gitJobs = gitScraper.returnJobs("/html/body/div[1]/div[4]/div/main/turbo-frame/div/div/div/div[2]/div[1]/react-partial/div/div/div[3]/div[2]/div/div[2]/article/markdown-accessiblity-table/table/tbody/");
        //Set that stores job scraped from PittCSC from github
        for (String job : gitJobs) {  
            System.out.println(job);
        }
        //GithubScraper///////////////////////////////

    }
 

}