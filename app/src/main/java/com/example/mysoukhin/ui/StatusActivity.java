package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mysoukhin.R;

public class StatusActivity extends AppCompatActivity {
    ImageView status;
    TextView text_Views,text_Views2;
    Button statusBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        toolbar = findViewById(R.id.status_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        text_Views2 = findViewById(R.id.text_Views2);
        String text = "Your Order is Completed. Please Check the Delivery Status at Pending Order Tracking Pages.";
        SpannableString ss = new SpannableString(text);

     /*   ForegroundColorSpan fcs = new ForegroundColorSpan(Color.BLACK);
        ss.setSpan(fcs,58,71, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_Views2.setText(ss);*/

        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
        ss.setSpan(styleSpan,61,74, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_Views2.setText(ss);

        status = findViewById(R.id.image_status);
        text_Views = findViewById(R.id.text_Views);
        statusBtn = findViewById(R.id.status_btn);

        statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}