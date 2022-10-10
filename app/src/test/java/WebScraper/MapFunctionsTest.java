package WebScraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MapFunctionsTest {

    FileManager fm;

    @BeforeEach
    void setUp() {
        fm = new FileManager();
        fm.startUp();
    }

    @Test
    void distanceBetweenInKMTest() {
        Assertions.assertEquals(2887.2599506071106,
                MapFunctions.distanceBetweenInKM(36.12, -86.67,33.94,-118.40));
    }

    @Test
    void sortByClosestTest() {
       /* Assertions.assertEquals(Path.of("."), Path.of(".").toAbsolutePath());*/
        Assertions.assertEquals(fm.getListOfParkingBays().size(), MapFunctions.sortByClosest(fm.getListOfParkingBays(),0,0).size() );
    }
}