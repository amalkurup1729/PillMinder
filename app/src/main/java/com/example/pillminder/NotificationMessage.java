package com.example.pillminder;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class NotificationMessage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);

        firebaseAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.tv_message);
        Bundle bundle = getIntent().getExtras();
        textView.setText(bundle.getString("message"));
    }
}