package pl.zaniewicz.gmapsissue1000;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andrzej on 2016-06-29.
 */
public class ListAdapter extends ArrayAdapter<PointInformationContainer> {

    List<PointInformationContainer> items;

    public ListAdapter(Context context, int resource, List<PointInformationContainer> items) {
        super(context, resource, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.addresss_item, null);
        }
        PointInformationContainer p = getItem(position);
        if (p != null) {
            TextView lat = (TextView) v.findViewById(R.id.lat);
            TextView lang = (TextView) v.findViewById(R.id.lang);
            TextView desc = (TextView) v.findViewById(R.id.desc);
            TextView geo = (TextView) v.findViewById(R.id.geo);
            lat.setText(p.lat + "");
            lang.setText(p.lang + "");
            desc.setText(p.description);
            geo.setText(p.addressFromGeocoder);
        }
        return v;
    }

    @Override
    public int getCount() {
        return items.size();
    }
}
