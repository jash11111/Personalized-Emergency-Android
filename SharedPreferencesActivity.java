package com.google.samples.quickstart.signin;

/**
 * Created by jashpithadia on 4/15/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class SharedPreferencesActivity extends Activity
        implements AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener


{

    private String email;
    private String gender;
    private String JOB;
    private String zodiac;
    public static final String STORAGE_NAME = "MySharedPreferences";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    Button b1;






    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);

        email = "";
        gender = "";
        JOB = "";
        zodiac = "";

        Spinner spinnerZodiac = (Spinner) findViewById(R.id.spinnerZodiac);
        // set a listener on spinner
        spinnerZodiac.setOnItemSelectedListener(this);
        // populate the spinner from data source
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZodiac.setAdapter(adapter);
        RadioGroup radioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        radioGroupGender.setOnCheckedChangeListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        b1=(Button)findViewById(R.id.test);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,Uri.parse("http://www.discordia-inc.co.uk/misc/mbtitest.html"));
                startActivity(i);
            }
        });


    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i)
    {
        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) radioGroup.findViewById(radioButtonId);
        gender = radioButton.getText().toString();
    }


    public void onCheckboxClicked(View view)
    {

        CheckBox st = (CheckBox) findViewById(R.id.student);
        CheckBox unemp = (CheckBox) findViewById(R.id.unemp);
        CheckBox emp = (CheckBox) findViewById(R.id.emp);
        CheckBox home = (CheckBox) findViewById(R.id.home);

        StringBuilder sb = new StringBuilder();
        if (st.isChecked()) {
            sb.append(", " + st.getText());
        }
        if (unemp.isChecked()) {
            sb.append(", " + unemp.getText());
        }
        if (emp.isChecked()) {
            sb.append(", " + emp.getText());
        }
        if (home.isChecked()) {
            sb.append(", " + home.getText());
        }


        if (sb.length() > 0) { // No toast if the string is empty
            // Remove the first comma
            JOB = sb.deleteCharAt(sb.indexOf(",")).toString();
        } else {
            JOB = "";
        }
    }



    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        zodiac = parent.getItemAtPosition(position).toString();
    }

    public void onNothingSelected(AdapterView<?> parent)
    {
        // An interface callback
    }


    public void save(View view)
    {
        email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("gender", gender);
        editor.putString("JOB", JOB);
        editor.putString("zodiac", zodiac);
        editor.apply();
        // A small pop up box that contains a message for a limited amount of time
        Toast.makeText(getApplicationContext(), "You have been saved!", Toast.LENGTH_LONG).show();


        CheckBox st = (CheckBox) findViewById(R.id.student);
        CheckBox unemp = (CheckBox) findViewById(R.id.unemp);
        CheckBox emp = (CheckBox) findViewById(R.id.emp);
        CheckBox home = (CheckBox) findViewById(R.id.home);

        if (st.isChecked()) {
            Intent intent = new Intent(this, Student.class);
            startActivity(intent);
        }

        if (unemp.isChecked()) {
            Intent intent = new Intent(this, UnEmp.class);
            startActivity(intent);
        }


        if (emp.isChecked()) {
            Intent intent = new Intent(this, Emp.class);
            startActivity(intent);
        }


        if (home.isChecked()) {
            Intent intent = new Intent(this, House.class);
            startActivity(intent);
        }


    }




    public void retrieve(View view)
    {
        // Restore preferences
        SharedPreferences sharedPreferences = getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "");
        gender = sharedPreferences.getString("gender", "");
        JOB = sharedPreferences.getString("JOB", "");
        zodiac = sharedPreferences.getString("zodiac", "");
        setUI();
    }




    protected void setUI()
    {

        ((TextView) findViewById(R.id.txtEmail)).setText(email);

        if (gender.equals("Male")) {
            ((RadioButton) findViewById(R.id.radMale)).setChecked(true);
        } else if (gender.equals("Female")) {
            ((RadioButton) findViewById(R.id.radFemale)).setChecked(true);
        }

        if (JOB.contains("Un-employed")) {
            ((CheckBox) findViewById(R.id.unemp)).setChecked(true);
        }
        if (JOB.contains("Employed")) {
            ((CheckBox) findViewById(R.id.emp)).setChecked(true);
        }
        if (JOB.contains("Student")) {
            ((CheckBox) findViewById(R.id.student)).setChecked(true);
        }
        if (JOB.contains("Home-living")) {
            ((CheckBox) findViewById(R.id.home)).setChecked(true);
        }


        Resources resource = getResources();
        String[] zodiacArray = resource.getStringArray(R.array.type);

        for (int i = 0; i < zodiacArray.length; i++) {
            if (zodiacArray[i].equals(zodiac)) {
                ((Spinner) findViewById(R.id.spinnerZodiac)).setSelection(i);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shared_preferences, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SharedPreferences Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.google.samples.quickstart.signin/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }



    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SharedPreferences Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.google.samples.quickstart.signin/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
