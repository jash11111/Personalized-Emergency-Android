package com.google.samples.quickstart.signin;

/**
 * Created by jashpithadia on 4/26/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public ListViewAdapter(Context context, String[] values) {
        super(context, R.layout.student, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.student, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
        textView.setText(values[position]);

        // Change icon based on name
        String s = values[position];

        System.out.println(s);
//"University Police", "Sexual Assault Services", "Environmental Office", "Facilities Management","Report Incident"};

        if (s.equals("University Police")) {
            imageView.setImageResource(R.drawable.call);
        } else if (s.equals("Sexual Assault Services")) {
            imageView.setImageResource(R.drawable.call);
        } else if (s.equals("Environmental Office")) {
            imageView.setImageResource(R.drawable.call);
        } else if (s.equals("Facilities Management")) {
            imageView.setImageResource(R.drawable.call);
        }
            else if (s.equals("Report Incident")) {
            imageView.setImageResource(R.drawable.x);
        }
        return rowView;
    }
}