package com.example.pillminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class DoctorLogin extends AppCompatActivity {

    private EditText dEmail;
    private EditText dPassword;
    private TextView dInfo;
    private Button dLogin;
    private int counter = 5;
    private TextView duserRegistration;
    private TextView switchuser1;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView dforgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        this.setTitle("Doctor Login");

        dEmail = (EditText)findViewById(R.id.etdEmail);
        dPassword = (EditText)findViewById(R.id.etdPassword);
        dInfo = (TextView)findViewById(R.id.tvdInfo);
        dLogin = (Button)findViewById(R.id.doctorloginBtn);
        switchuser1 = (TextView)findViewById(R.id.suser1);
        duserRegistration = (TextView)findViewById(R.id.tvdRegister);
        dforgotPassword = (TextView)findViewById(R.id.tvdForgotPassword);

        dInfo.setText("No of attempts remaining:5");

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if(user !=null){
            finish();
            startActivity(new Intent(DoctorLogin.this, DoctorAccount.class));
        }

        dLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(dEmail.getText().toString(), dPassword.getText().toString());
            }
        });

        switchuser1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(DoctorLogin.this,JunctionPage.class));
            }
        });

        duserRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorLogin.this,DoctorRegistration.class));
            }
        });

        dforgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorLogin.this,PasswordActivity.class));
            }
        });
    }

    private void validate(String mdEmail, String mdPassword){

        progressDialog.setMessage("Please wait until the process completes");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mdEmail,mdPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(DoctorLogin.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DoctorLogin.this, PatientAccount.class));
                }else{
                    Toast.makeText(DoctorLogin.this,"Login Failed", Toast.LENGTH_SHORT).show();
                    counter--;
                    dInfo.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0){
                        dLogin.setEnabled(false);
                    }
                }

            }
        });



    }

    @Override
    public void onBackPressed() {
        Toast.makeText(DoctorLogin.this, "Please click switch user !", Toast.LENGTH_SHORT).show();
    }
}