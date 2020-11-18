package com.example.pillminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pillminder.GoogleMap.MapActivity;
import com.example.pillminder.NewsFeeds.Newsfeed;
import com.google.firebase.auth.FirebaseAuth;

public class PatientAccount extends AppCompatActivity {
    private Button setreminder;
    private Button openmap;
    private Button news;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_account);

        this.setTitle("My Account");

        firebaseAuth = FirebaseAuth.getInstance();
       setreminder = (Button)findViewById(R.id.reminderBtn);
           openmap = (Button) findViewById(R.id.mapBtn);
           news = (Button) findViewById(R.id.newsfeedBtn);


           news.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(PatientAccount.this, Newsfeed.class);
                   startActivity(intent);
               }
           });



           openmap.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(PatientAccount.this, MapActivity.class);
                   startActivity(intent);
               }
           });


       setreminder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(PatientAccount.this,MainActivity2.class);
               startActivity(intent);
           }
       });
    }

    private  void Logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(PatientAccount.this,PatientmainLogin.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(PatientAccount.this, "Please Logout from current account !", Toast.LENGTH_SHORT).show();
    }
}