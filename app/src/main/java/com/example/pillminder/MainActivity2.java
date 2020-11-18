package com.example.pillminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;


import com.example.pillminder.Adapter.EventAdapter;
import com.example.pillminder.Database.DatabaseClass;
import com.example.pillminder.Database.EntityClass;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    Button createEvent;
    EventAdapter eventAdapter;
    RecyclerView recyclerView;
    DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        this.setTitle("Reminders");

        firebaseAuth = FirebaseAuth.getInstance();

        createEvent = findViewById(R.id.btn_createEvent);
        recyclerView = findViewById(R.id.recyclerview);
        createEvent.setOnClickListener(this);
        databaseClass = DatabaseClass.getDatabase(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter() {
        List<EntityClass> classList = databaseClass.EventDao().getAllData();
        eventAdapter = new EventAdapter(getApplicationContext(),classList);
        recyclerView.setAdapter(eventAdapter);

    }


    @Override
    public void onClick(View v) {
        if (v ==createEvent)
        {
            goToCreateEventActivity();
        }
    }

    private void goToCreateEventActivity() {
        Intent intent= new Intent(getApplicationContext(),CreateEvent.class);
        startActivity(intent);
    }
}