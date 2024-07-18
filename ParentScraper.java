import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParentScraper {

    //driver to scrape info
    WebDriver driver;

    //link to hold link
    public final String Link;
    //String to hold starting location
    String location;

    //String endLocation
    String jobType;


    //Initalizes driver, location, and link to scrape
    public ParentScraper(WebDriver driver, String location, String jobType, String link){
        if(location==null){
            throw new IllegalArgumentException("Job location error");
        }else if(jobType ==null){
            throw new IllegalArgumentException("jobType error");
        }else if(link ==null){
            throw new IllegalArgumentException("Link location error");

        }else if(driver ==null){
            throw new IllegalArgumentException("Driver error");
        }else{
            Link = link;
            this.driver = driver;
            this.location = location;
            this.jobType = jobType;  
        }

    }
    //Constructor #2, taking only driver and link
    public ParentScraper(WebDriver driver, String link){
        if(driver==null){
            throw new IllegalArgumentException("Driver error");
        }else if(link ==null){
            throw new IllegalArgumentException("Link location error");
        }else{
            this.driver = driver;
            this.Link = link;
        }
    }
    //checks if element exists by id
    public WebElement getElementById(String elementToCheck){
        try{
            WebElement element = driver.findElement(By.id(elementToCheck));
            return element;
        }catch(NoSuchElementException e){
            System.out.println("Element not found by id");
            return null;
        }
    }
    //checks exists by className
    public WebElement getElementByClass(String elementToCheck){
        try{
            WebElement element = driver.findElement(By.className(elementToCheck));
            return element;
        }catch(NoSuchElementException e){
            System.out.println("Element not found by class");
            return null;
        }
    }

    public WebElement getElementByCss(String elementToCheck){
        try{
            WebElement element = driver.findElement(By.cssSelector(elementToCheck));
            return element;
        }catch(NoSuchElementException e){
            System.out.println("Element not found by css");
            return null;
        }  
    }

    public WebElement getElementByXpath(String elementToCheck){
        try{
            WebElement element = driver.findElement(By.xpath(elementToCheck));
            return element;
        }catch(NoSuchElementException e){
            System.out.println("Element not found by xpath");
            return null;
        }  
    }

    public String getLocation(){
        return this.location;
    }

    public String getJobType(){
        return this.jobType;
    }

    public void setLocation(String locationToSet){
        this.location = locationToSet;
    }

    public void setJobType (String jobTypeToSet){
        this.jobType = jobTypeToSet;
    }

    //method to navigate to desired website
    public void navigateToWebSite(){
        driver.get(Link);
    }
    //method for navigating back
    public void navigateBack(){
        driver.navigate().back();
    }

    //refreshes page
    public void refresh(){
        driver.navigate().refresh();;
    }
}
