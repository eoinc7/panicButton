package com.example.eoin.panicbutton2017;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.view.View.OnLongClickListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    private Button buttonShoot;
    private Button buttonLogout;


    String messageToSend = "Shooter on Campus"; //+ GPS
    String number = "07548136364";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        buttonShoot = (Button) findViewById(R.id.buttonShoot);
        buttonLogout  = (Button) findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);

        buttonShoot.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
// TODO Auto-generated method stub
              //  finish();

                //Toast
               Toast.makeText(getBaseContext(), "Support Alerted", Toast.LENGTH_SHORT).show();

                //Text
                SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);

                /*
                // instantiate the location manager, note you will need to request permissions in your manifest
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // get the last know location from your location manager.
                final Location location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                // now get the lat/lon from the location and do something with it.
                //   nowDoSomethingWith(location.getLatitude(), location.getLongitude());



                Toast.makeText(getBaseContext(),"Location changed : Lat: " +
                                location.getLatitude()+ " Lng: " + location.getLongitude(),
                        Toast.LENGTH_SHORT).show();*/
                return false;
            }
        });
        }



    @Override
    public void onClick(View view) {
        //if logout is pressed
        if (view == buttonLogout) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

    }

}