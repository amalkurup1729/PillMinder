package com.example.pillminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PatientLogin extends AppCompatActivity {

    private EditText mFullName, mEmail, mPassword, mPhone;
    private Button regbutton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();

        regbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(validate()){
              String user_email = mEmail.getText().toString().trim();
              String user_password = mPassword.getText().toString().trim();

              firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          Toast.makeText(PatientLogin.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                          startActivity( new Intent(PatientLogin.this, PatientAccount.class));
                      }else{
                          Toast.makeText(PatientLogin.this, "Registeration Failed", Toast.LENGTH_SHORT).show();
                      }

                  }
              });
            }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientLogin.this, PatientmainLogin.class));
            }
        });

    }

    private void setupUIViews() {
        mFullName = (EditText) findViewById(R.id.fullName);
        mEmail = (EditText) findViewById(R.id.Email);
        mPassword = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone);
        regbutton = (Button) findViewById(R.id.patientregisterBtn);
        userLogin = (TextView) findViewById(R.id.createText);
    }

    private Boolean validate() {
        Boolean result = false;

        String name = mFullName.getText().toString();
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
          return result;
    }
}