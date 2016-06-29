package pl.zaniewicz.gmapsissue1000;

/**
 * Created by Andrzej on 2016-06-29.
 */
public class PointInformationContainer {
    public double lat, lang;
    public String description;
    public String addressFromGeocoder;

    public PointInformationContainer(double lat, double lang, String description) {
        this.lat = lat;
        this.lang = lang;
        this.description = description;
    }
}