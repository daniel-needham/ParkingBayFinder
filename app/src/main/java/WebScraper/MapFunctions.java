package WebScraper;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MapFunctions {

    private static final double maxLat = 50.85141662390403;
    private static final double minLat = 50.81147871003782;
    private static final double maxLong = -0.2494843487998169;
    private static final double minLong = -0.10421393998768937;

    //haversine formula
    public static double distanceBetweenInKM(double lat1, double lon1, double lat2, double lon2) {
        final double circumference = 6372.8; // mean earth circumference
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double x = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double y = 2 * Math.asin(Math.sqrt(x));
        return circumference * y;
    }

    public static List<ParkingBay> sortByClosest(List<ParkingBay> listOfParkingBays, double lat1, double long1) {
        listOfParkingBays.stream()
                .filter((parkingBay) -> parkingBay.getLatitude() != 0 && parkingBay.getLongitude() != 0)
                .forEach(parkingBay -> parkingBay.setDistanceFromUserKM(distanceBetweenInKM(lat1, long1, parkingBay.getLatitude(), parkingBay.getLongitude())));
        return listOfParkingBays.stream()
                .sorted(Comparator.comparing(ParkingBay::getDistanceFromUserKM))
                .toList();

    }

    public static double getRandomLatitude() {
        return (Math.random() * (maxLat - minLat)) + minLat;
    }

    public static double getRandomLongitude() {
        return (Math.random() * (maxLong - minLong)) + minLong;
    }

}
