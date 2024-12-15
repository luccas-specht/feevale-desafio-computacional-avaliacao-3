package com.example.avalia3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge;
    private Button buttonAddAndCalculate;
    private LinearLayout linearLayoutAthletes;

    private ArrayList<Athlete> athleteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        buttonAddAndCalculate = findViewById(R.id.buttonAddAndCalculate);
        linearLayoutAthletes = findViewById(R.id.linearLayoutAthletes);

        buttonAddAndCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAthlete();
            }
        });
    }

    private void addAthlete() {
        String name = editTextName.getText().toString().trim();
        String ageText = editTextAge.getText().toString().trim();

        if (name.isEmpty() || ageText.isEmpty()) {
            return;
        }

        int age = Integer.parseInt(ageText);
        int fcm = 220 - age;

        athleteList.add(new Athlete(name, fcm));

        Collections.sort(athleteList, new Comparator<Athlete>() {
            @Override
            public int compare(Athlete o1, Athlete o2) {
                return Integer.compare(o2.getFcm(), o1.getFcm());
            }
        });

        updateAthleteList();
    }

    private void updateAthleteList() {
        linearLayoutAthletes.removeAllViews();

        for (Athlete athlete : athleteList) {
            TextView textView = new TextView(this);
            textView.setText(athlete.getName() + " - FCM: " + athlete.getFcm());
            textView.setTextSize(16);
            linearLayoutAthletes.addView(textView);
        }
    }

    private static class Athlete {
        private final String name;
        private final int fcm;

        public Athlete(String name, int fcm) {
            this.name = name;
            this.fcm = fcm;
        }

        public String getName() {
            return name;
        }

        public int getFcm() {
            return fcm;
        }
    }
}