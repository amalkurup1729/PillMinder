package com.example.pillminder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

public class JunctionPage extends AppCompatActivity {
    public Button button1,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_junction_page);

        this.setTitle("Junction Page");

        button1 = findViewById(R.id.doctorBtn);
        button2 = findViewById(R.id.patientBtn);
        button3 = findViewById(R.id.ambulancedriverBtn);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JunctionPage.this,DoctorLogin.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JunctionPage.this,PatientmainLogin.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JunctionPage.this,AmbulancedriverLogin.class);
                startActivity(intent);
            }
        });


    }
}