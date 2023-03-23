package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoukhin.R;

public class CheckOutActivity extends AppCompatActivity {
    ImageView skipimg;
    SeekBar seekBar;
    TextView personalInfo,payment,confirm,cashText;
    Button checkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        this.setTitle("CheckOut");

        skipimg = findViewById(R.id.skipImg);
        seekBar = findViewById(R.id.seekbar);
        personalInfo = findViewById(R.id.personalInfo);
        payment = findViewById(R.id.payment);
        confirm = findViewById(R.id.confirm);
        cashText = findViewById(R.id.cashText);
        checkBtn = findViewById(R.id.cashId);

        seekBar.getProgressDrawable().setColorFilter(getResources().getColor(R.color.grey), PorterDuff.Mode.SRC_ATOP);
        seekBar.getThumb().setColorFilter(getResources().getColor(R.color.purple_500), PorterDuff.Mode.SRC_ATOP);

        skipimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddressActivity.class);
                startActivity(intent);
            }
        });

        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckOutActivity.this, "Your ordered is processed now", Toast.LENGTH_SHORT).show();
              //  Intent intent = new Intent(getApplicationContext(),OrderHistory.class);
               // startActivity(intent);
            }
        });

    }
}