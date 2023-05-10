package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysoukhin.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends AppCompatActivity {
    Toolbar reviewToolbar;
    FirebaseDatabase database;
    CircleImageView reviewImg;
    TextView review_text,reviews;
    RatingBar ratingBar;
    EditText reviewEdtText;
    FloatingActionButton submit;
    String productTitle;
    String productImg;
    FirebaseAuth auth;
    Button reviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviewToolbar = findViewById(R.id.review_toolbar);
        setSupportActionBar(reviewToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        reviewBtn = findViewById(R.id.reviewBtn);

        database = FirebaseDatabase.getInstance();
        review_text = findViewById(R.id.review_txt);
        reviews = findViewById(R.id.reviews);
        reviewImg = findViewById(R.id.review_img);
        ratingBar = findViewById(R.id.review_rating);
        reviewEdtText = findViewById(R.id.review_EdtTxt);
        submit = findViewById(R.id.review_submit);
        // get reviews
        loadMyReview();

         reviewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        productTitle = getIntent().getStringExtra("productTitle");
        productImg = getIntent().getStringExtra("productImg");

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SeeReviewsActivity.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (user != null) {
                    // user logged in

                } else {
                    Toast.makeText(getApplicationContext(), "Please first create your account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                String rating = "" + ratingBar.getRating();
                String review = "" + reviewEdtText.getText().toString();
                if(TextUtils.isEmpty(rating)){
                    Toast.makeText(ReviewActivity.this, "Rating is empty", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(review)){
                    reviewEdtText.setError("Review is empty");
                }else{
                    inputData();

                }

            }
        });

    }

    private void loadMyReview() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ratings");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String uid = ""+snapshot.child("uid").getValue();
                    String rating = ""+snapshot.child("rating").getValue();
                    String reviews = ""+snapshot.child("reviews").getValue();
                    String productTitle = ""+snapshot.child("productTitle").getValue();
                    String productImg  = ""+snapshot.child("productImg").getValue();

                    // set values
                    try {
                        float myRating = Float.parseFloat(rating);
                        ratingBar.setRating(myRating);
                        reviewEdtText.setText(reviews);
                        review_text.setText(productTitle);

                    }catch (Exception e){
                        Log.e(this.toString(), e.getMessage().toString());
                    }

                    try {
                        Picasso.get().load(productImg).fit().centerCrop().into(reviewImg);

                    }catch (Exception e){
                        Log.e(this.toString(), e.getMessage().toString());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void inputData() {
        String rating = "" + ratingBar.getRating();
        String review = "" + reviewEdtText.getText().toString();

            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("rating", rating);
            hashMap.put("reviews", review);
            hashMap.put("uid", auth.getUid());
            hashMap.put("productTitle", productTitle);
            hashMap.put("productImg", productImg);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.child("ratings").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ReviewActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ReviewActivity.this, "error", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


