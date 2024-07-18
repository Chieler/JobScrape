import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class StaticMethods {
    public static LocalDate parseStringToDate(String dateString) throws DateTimeParseException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
        LocalDate parsedDate = LocalDate.parse(dateString, formatter);

        return parsedDate;
    }

    public static String returnIntInString(String x){
       return x.replaceAll("[^0-9]","");
    }
    public static String diceDropDownXpath = "/html/body/dhi-js-dice-client/div/dhi-search-page-container/dhi-search-page/div/div[2]/dhi-search-page-results/div/div[3]/js-search-display/div/div[4]/div[2]/form/select";
}
