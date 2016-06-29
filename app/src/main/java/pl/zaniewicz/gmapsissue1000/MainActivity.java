package pl.zaniewicz.gmapsissue1000;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Geocoder geocoder;
    private final List<PointInformationContainer> realPoints = new ArrayList<>();

    {
        realPoints.add(new PointInformationContainer(52.22889379302686, 21.00770875811577, "Center of Warsaw 1"));
        realPoints.add(new PointInformationContainer(52.236347716326065, 21.013489589095116, "Center of Warsaw 2"));
        realPoints.add(new PointInformationContainer(52.192146, 20.954062, "Still Warsaw but not center"));
        realPoints.add(new PointInformationContainer(52.177918, 21.570944, "Smaller city return properly address"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillAddressesFromGeocoder();
        ListAdapter listAdapter = new ListAdapter(this, R.layout.addresss_item, realPoints);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
    }

    private void fillAddressesFromGeocoder() {
        Locale defaultLocale = Locale.getDefault();
        geocoder = new Geocoder(this, defaultLocale);
        for (PointInformationContainer point : realPoints) {
            Address returnedAddress = getAddressForPointByGeocoder(point.lat, point.lang);
            String textOfAddress = getTextOfAddress(returnedAddress);
            point.addressFromGeocoder = textOfAddress;
        }
    }

    private Address getAddressForPointByGeocoder(double lat, double lang) {
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lang, 1);
            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTextOfAddress(Address addresses) {
        if (addresses == null) {
            return "null address";
        }
        StringBuilder strAddress = new StringBuilder();
        for (int i = 0; i < addresses.getMaxAddressLineIndex(); i++) {
            strAddress.append(addresses.getAddressLine(i)).append(",");
        }
        return strAddress.toString();
    }


}
