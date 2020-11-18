package com.example.pillminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorAccount extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_account);

        this.setTitle("My Account");

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.doctormenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.dlogoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(DoctorAccount.this,DoctorLogin.class));

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(DoctorAccount.this, "Please Logout from current account !", Toast.LENGTH_SHORT).show();
    }
}