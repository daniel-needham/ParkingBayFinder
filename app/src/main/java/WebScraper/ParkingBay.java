package WebScraper;


public class ParkingBay {
    private final String zone;
    private final String road;
    private double latitude;
    private double longitude;
    private double distanceFromUserKM;

    public ParkingBay(String zone, String road) {
        this.zone = zone;
        this.road = road;
        this.latitude = MapFunctions.getRandomLatitude();
        this.longitude = MapFunctions.getRandomLongitude();
        this.distanceFromUserKM = 0;
    }

    public String getZone() {
        return zone;
    }

    public String getRoad() {
        return road;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistanceFromUserKM() {
        return distanceFromUserKM;
    }

    public void setDistanceFromUserKM(double distanceFromUserKM) {
        this.distanceFromUserKM = distanceFromUserKM;
    }

    @Override
    public String toString() {
        return "ParkingBay{" +
                "zone='" + zone + '\'' +
                ", road='" + road + '\'' +
                ", distance='" + distanceFromUserKM + '\'' +
                '}';
    }
}
