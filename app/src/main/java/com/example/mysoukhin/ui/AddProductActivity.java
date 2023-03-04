package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.ProductModel;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.nio.charset.StandardCharsets;

public class AddProductActivity extends AppCompatActivity {
    Button upload,history;
    ImageView imgProduct;
    Spinner spinner;
    MaterialButton btnSubmit,add;
    TextInputEditText name,productType,price,oldPrice,fabric;
    private TextInputLayout nameLayout, productTypeLayout, priceLayout, expDateLayout,fabricationLayout;
    String category;
    Uri imgUri;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        getSupportActionBar().setTitle("Upload ProductModel");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        upload = findViewById(R.id.uploadId);
        history = findViewById(R.id.historyId);
        imgProduct = findViewById(R.id.imgProduct);
        spinner = findViewById(R.id.spinner);
        btnSubmit = findViewById(R.id.btnSubmit);
        add = findViewById(R.id.btnChooseImg);

        name = findViewById(R.id.editTextProductName);
        productType = findViewById(R.id.editTextProductType);
        price = findViewById(R.id.editTextProductPrice);
        oldPrice = findViewById(R.id.editTextProductExpire);
        fabric = findViewById(R.id.editTextProductFabrication);

        nameLayout = findViewById(R.id.editTextProductNameLayout);
        productTypeLayout = findViewById(R.id.editTextProductTypeLayout);
        priceLayout = findViewById(R.id.editTextProductPriceLayout);
        expDateLayout = findViewById(R.id.editTextProductExpire);
        fabricationLayout = findViewById(R.id.editTextProductFabricationLayout);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.VISIBLE);
                history.setVisibility(View.GONE);


            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),UploadHistory.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.productstypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       mStorageRef = FirebaseStorage.getInstance().getReference("products");


        nameLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (name.getText().toString().trim().isEmpty()) {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError("Please Enter Product Name");
                } else {
                    nameLayout.setErrorEnabled(false);
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (name.getText().toString().trim().isEmpty()) {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError("Please Enter Product Name");
                } else {
                    nameLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productTypeLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (productType.getText().toString().trim().isEmpty()) {
                    productTypeLayout.setErrorEnabled(true);
                    productTypeLayout.setError("Please Enter Product Name");
                } else {
                    productTypeLayout.setErrorEnabled(false);
                }
            }
        });

        productType.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (productType.getText().toString().trim().isEmpty()) {
                    productTypeLayout.setErrorEnabled(true);
                    productTypeLayout.setError("Please Enter Product Name");
                } else {
                    productTypeLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        priceLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (price.getText().toString().trim().isEmpty()) {
                    priceLayout.setErrorEnabled(true);
                    priceLayout.setError("Please Enter Product Name");
                } else {
                    priceLayout.setErrorEnabled(false);
                }
            }
        });

        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (price.getText().toString().trim().isEmpty()) {
                    priceLayout.setErrorEnabled(true);
                    priceLayout.setError("Please Enter Product Name");
                } else {
                    priceLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        fabricationLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (price.getText().toString().trim().isEmpty()) {
                    priceLayout.setErrorEnabled(true);
                    priceLayout.setError("Please Enter Product Name");
                } else {
                    priceLayout.setErrorEnabled(false);
                }
            }
        });

        fabric.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (fabric.getText().toString().trim().isEmpty()) {
                    fabricationLayout.setErrorEnabled(true);
                    fabricationLayout.setError("Please Enter Product Name");
                } else {
                    fabricationLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        expDateLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (oldPrice.getText().toString().trim().isEmpty()) {
                    expDateLayout.setErrorEnabled(true);
                    expDateLayout.setError("Please Enter Product Name");
                } else {
                    expDateLayout.setErrorEnabled(false);
                }
            }
        });

        oldPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (oldPrice.getText().toString().trim().isEmpty()) {
                    expDateLayout.setErrorEnabled(true);
                    expDateLayout.setError("Please Enter Product Name");
                } else {
                    expDateLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress())
                    Toast.makeText(getApplicationContext(), "Upload Is In Progress", Toast.LENGTH_SHORT).show();
                else if (name.getText().toString().isEmpty() || productType.getText().toString().isEmpty() || price.getText().toString().isEmpty() || oldPrice.getText().toString().isEmpty() || fabric.getText().toString().isEmpty() || imgUri == null){
                    Toast.makeText(getApplicationContext(), "Empty Cells", Toast.LENGTH_SHORT).show();
                }
                else{
                    uploadData();
                    Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    public void uploadData() {
        if (name.getText().toString().isEmpty() || productType.getText().toString().isEmpty() || price.getText().toString().isEmpty() || oldPrice.getText().toString().isEmpty() || fabric.getText().toString().isEmpty() || imgUri == null) {
            Toast.makeText(getApplicationContext(), "Empty Cells", Toast.LENGTH_SHORT).show();
        } else {
            uploadImage();
        }
    }

    public void uploadImage() {
        if (imgUri != null) {
            StorageReference fileReference = mStorageRef.child(name.getText().toString() + "." + getFileExtension(imgUri));
            mUploadTask = fileReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) ;
                    Uri downloadUrl = urlTask.getResult();
                    ProductModel product = new ProductModel(
                            productType.getText().toString().trim(),
                            price.getText().toString().trim(),
                            oldPrice.getText().toString().trim(),
                            fabric.getText().toString().trim(),downloadUrl.toString()
                            );

                    DatabaseReference z = FirebaseDatabase.getInstance().getReference()
                            .child("product")
                            .child(category)
                            .child(name.getText().toString());
                    z.setValue(product);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public void openImage() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i, ProfileFragment.GALARY_PICK);
    }

    public String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProfileFragment.GALARY_PICK && resultCode == Activity.RESULT_OK && data.getData() != null && data != null) {
            imgUri = data.getData();
            try {
                Picasso.get().load(imgUri).fit().centerCrop().into(imgProduct);
            } catch (Exception e) {
                Log.e(this.toString(), e.getMessage().toString());
            }
        }
    }
    }
