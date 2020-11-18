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

public class PatientmainLogin extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;
    private TextView switchuser2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientmain_login);

        this.setTitle("Patient Login");


        Email = (EditText)findViewById(R.id.etEmail);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.patientloginBtn);
        userRegistration = (TextView)findViewById(R.id.tvRegister);
        forgotPassword = (TextView)findViewById(R.id.tvForgotPassword);
        switchuser2 = (TextView)findViewById(R.id.suser2);

        Info.setText("No of attempts remaining:5");

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        switchuser2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientmainLogin.this, JunctionPage.class));
            }
        });

        if(user !=null){
            finish();
            startActivity(new Intent(PatientmainLogin.this, PatientAccount.class));
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientmainLogin.this,PatientLogin.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PatientmainLogin.this,PasswordActivity.class));
            }
        });

    }

    private void validate(String mEmail, String mPassword) {

        progressDialog.setMessage("Please wait until the process completes");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               progressDialog.dismiss();
               Toast.makeText(PatientmainLogin.this,"Successfully Logged in", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(PatientmainLogin.this, PatientAccount.class));
           }else{
               Toast.makeText(PatientmainLogin.this,"Login Failed", Toast.LENGTH_SHORT).show();
               counter--;
               Info.setText("No of attempts remaining: " + counter);
               progressDialog.dismiss();
               if(counter == 0){
                   Login.setEnabled(false);
               }
           }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(PatientmainLogin.this, "Please click switch user !", Toast.LENGTH_SHORT).show();
    }
}