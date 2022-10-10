package WebScraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrape {

    static final String url = "https://www.brighton-hove.gov.uk/streets-motorcycle-bays";


    /**
     * Fetches a jsoup Document object of the website, catching and exception if not found. The document is
     * then returned in an optional
     * @return An optional of a Document containing the entire site
     */
    public static Optional<Document> fetchDocumentFromURL() {
        Optional<Document> page = Optional.empty();
        try {
            page = Optional.of(Jsoup.connect(url)
                    .data("query", "Java")
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .post());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    /**
     *
     * @return
     */
    public static List<ParkingBay> getListOfParkingBays() {
        List<ParkingBay> listOfParkingBays = new LinkedList<>();
        Document webPage = fetchDocumentFromURL().orElseThrow();
        for (int i = 'a'; i <= 'z'; i++) {
            Element parkingZoneElement = webPage.getElementById("tab--zone-" + (char) i);
            Elements liItemsByZone;
            try {
                liItemsByZone = parkingZoneElement.getElementsByTag("li");
            } catch (NullPointerException e) {
                continue;
            }
            for (Element li : liItemsByZone) {
                int numberOfBays = getNumberOfBays(li.text());
                for (int j = 0; j < numberOfBays; j++) {
                    ParkingBay bay = new ParkingBay(Character.toString((char) i), getCleanString(li.text(), j));
                    listOfParkingBays.add(bay);
                }

            }
        }
        return listOfParkingBays;
    }

    /**
     * Parses the number of bays contained in the string
     * @param input A string of the road and number of bays from a <li> tag
     * @return int number of bays specified in the string
     */
    public static int getNumberOfBays(String input) {
        return checkIfMultipleBays(input) ? Integer.parseInt(input.substring(input.length() -1)) : 1;
    }

    /**
     * Returns true if the string contains multiple bays
     * @param input A string of the road and number of bays from a <li> tag
     * @return boolean True if its contains x \d which signifies there are multiple bays
     */
    public static boolean checkIfMultipleBays(String input) {
        Pattern patt = Pattern.compile(" x \\d");
        Matcher matt = patt.matcher(input);
        return matt.find();
    }

    /**
     * Returns the road name cleaned up followed by which number parking bay is applicable
     * @param input A string of the road and number of bays from a <li> tag
     * @param index of the loop which creates multiple ParkingBay object from a single <li> tag
     * @return String without the "x \d" after the road, instead it is numbered appropriately
     */
    public static String getCleanString(String input, int index) {
        input = input.replaceAll(" x \\d", "");
        return index > 0 ? String.format("%s %d",input, index + 1) : input;
    }

}
