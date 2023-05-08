package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.mysoukhin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ScanQRCodeActivity extends AppCompatActivity {
    private CodeScanner codeScanner;
    private CodeScannerView codeScannerView;
    private TextView textView;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private String UserId;
    private String OrderId;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        mAuth= FirebaseAuth.getInstance();
        CurrentUser = mAuth.getCurrentUser();
        UserId = CurrentUser.getUid();

        //tool bar
        mToolBar = findViewById(R.id.QRScanner_TooBar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle("QR Code Scanner");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        OrderId = getIntent().getStringExtra("OrderId");

        codeScannerView =findViewById(R.id.ScannerView);
        textView = findViewById(R.id.text);
        codeScanner = new CodeScanner(this,codeScannerView);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SearchAboutUser();
                        Vibrator vibrator= (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(1000);
                    }
                });
            }
        });


        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
                textView.setText("please focus camera on QR code");
            }
        });

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withActivity(ScanQRCodeActivity.this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }
            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(ScanQRCodeActivity.this,"Camera Permission is Requested",Toast.LENGTH_LONG).show();
                finish();
            }
            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    private void  SearchAboutUser(){
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference x = root.child("users");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    FirebaseDatabase.getInstance().getReference().child("Order").child(OrderId).child("IsChecked").setValue("true");

                    textView.setText("This Order Received Successfully");
                    Toast.makeText(ScanQRCodeActivity.this,"Order Received Successfully",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    textView.setText("This Delivery doesn't Exist in our System\nTry Again");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        x.addListenerForSingleValueEvent(valueEventListener);

    }
    }
