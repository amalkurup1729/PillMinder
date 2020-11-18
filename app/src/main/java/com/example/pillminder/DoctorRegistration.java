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

public class DoctorRegistration extends AppCompatActivity {

    private EditText mdFullName, mdEmail, mdPassword, mdPhone;
    private Button dregbutton;
    private TextView duserLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);
        setupUIViews();

        this.setTitle("Doctor Register");

        firebaseAuth = FirebaseAuth.getInstance();

        dregbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    String user_email = mdEmail.getText().toString().trim();
                    String user_password = mdPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(DoctorRegistration.this, "Registeration Successful", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(DoctorRegistration.this, DoctorAccount.class));
                            }else{
                                Toast.makeText(DoctorRegistration.this, "Registeration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        duserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorRegistration.this, DoctorLogin.class));
            }
        });
    }

    private void setupUIViews() {
        mdFullName = (EditText) findViewById(R.id.dfullName);
        mdEmail = (EditText) findViewById(R.id.dEmail);
        mdPassword = (EditText) findViewById(R.id.dpassword);
        mdPhone = (EditText) findViewById(R.id.dphone);
        dregbutton = (Button) findViewById(R.id.doctorregisterBtn);
        duserLogin = (TextView) findViewById(R.id.dcreateText);
    }

    private Boolean validate() {
        Boolean result = false;

        String name = mdFullName.getText().toString();
        String email = mdEmail.getText().toString();
        String password = mdPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }

}