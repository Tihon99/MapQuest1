package com.example.mapquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final int LOCATION_PERMISSION = 1001;

    Button showMapButton, questButton;
    TextView latText, lonText, timeText;

    LocationManager locationManager;
    Location location;

    boolean flagQuest = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LifeActivity", "Create" + flagQuest);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        showMapButton = findViewById(R.id.toMapButton);
        lonText = findViewById(R.id.lon);
        latText = findViewById(R.id.lat);
        timeText = findViewById(R.id.timeText);
        questButton = findViewById(R.id.quest_button);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        questButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagQuest = true;
                ArrayList<Integer> partNovel = new ArrayList<>();
                partNovel.add(1);

                Intent intent = new Intent(MainActivity.this, NovelActivity.class);
                intent.putExtra("PART", partNovel);
                startActivity(intent);
            }
        });


        showMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> partNovel = new ArrayList<>();
                partNovel.add(2);

                Intent intent = new Intent(MainActivity.this, NovelActivity.class);
                intent.putExtra("PART", partNovel);
                startActivity(intent);
               /* if(location != null) {
                    intent.putExtra("latitude", location.getLatitude());
                    intent.putExtra("longitude", location.getLongitude());
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Местоположение на определено",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("LifeActivity", "Start: " + flagQuest);
        Intent intent = getIntent();
        flagQuest = intent.getBooleanExtra("flag", false );
        Log.d("LifeActivity", "Start: " + flagQuest +" " + intent.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        showMapButton.setClickable(flagQuest);
        Log.d("LifeActivity", "Resume" + flagQuest);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeActivity", "Pause" + flagQuest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeActivity", "Stop" + flagQuest);
    }
}