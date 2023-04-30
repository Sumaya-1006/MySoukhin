package com.example.mysoukhin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mysoukhin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends AppCompatActivity {
    Toolbar reviewToolbar;
    FirebaseDatabase database;
    CircleImageView reviewImg;
    TextView review_text,reviews;
    RatingBar ratingBar;
    EditText reviewEdtText;
    FloatingActionButton submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviewToolbar = findViewById(R.id.review_toolbar);
        setSupportActionBar(reviewToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        review_text = findViewById(R.id.review_txt);
        reviews = findViewById(R.id.reviews);
        reviewImg = findViewById(R.id.review_img);
        ratingBar = findViewById(R.id.review_rating);
        reviewEdtText = findViewById(R.id.review_EdtTxt);
        submit = findViewById(R.id.review_submit);

        reviewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}