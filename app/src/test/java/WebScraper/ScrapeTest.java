package WebScraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScrapeTest {

    @Test
    void fetchDocumentFromURLTest() {
        Assertions.assertNotNull(Scrape.fetchDocumentFromURL());
    }

    @Test
    void getListOfParkingBaysAllZonesTest() {
        long zonesCollected = Scrape.getListOfParkingBays().stream()
                .map(ParkingBay::getZone)
                .distinct()
                .count();
        Assertions.assertEquals(22, zonesCollected);
    }
    @Test
    void getListOfParkingBaysPatternedMatchedTest() {
        assertEquals(794, Scrape.getListOfParkingBays().size());
    }

    @Test
    void getNumberOfBayTest() {
        Assertions.assertEquals(5, Scrape.getNumberOfBays("Mulholland Drive x 5"));
    }

    @Test
    void checkIfMultipleBaysTest() {
        Assertions.assertTrue(Scrape.checkIfMultipleBays("Mulholland Drive x 5"));
        Assertions.assertFalse(Scrape.checkIfMultipleBays("Upland Way"));
    }

    @Test
    void getCleanStringTest() {
        String input = "Mulholland Drive x 5";
        Assertions.assertEquals("Mulholland Drive", Scrape.getCleanString(input,0));
    }
}