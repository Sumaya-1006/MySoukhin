package com.example.mysoukhin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mysoukhin.R;
import com.example.mysoukhin.models.UploadModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

public class AddProductActivity extends AppCompatActivity {
    Button upload, history;
    ImageView imgProduct;
    MaterialButton btnSubmit, chooseImage;
    EditText name, productType;
    static Uri imgUri;
    private StorageReference mStorageRef;
    private StorageTask mUploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        upload = findViewById(R.id.uploadId);
        history = findViewById(R.id.historyId);
        imgProduct = findViewById(R.id.imgProduct);
        btnSubmit = findViewById(R.id.btnSubmit);
        chooseImage = findViewById(R.id.btnChooseImg);

        name = findViewById(R.id.name_Text);
        productType = findViewById(R.id.TypeEditText);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload.setVisibility(View.VISIBLE);
                history.setVisibility(View.VISIBLE);

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadHistory.class);
                startActivity(intent);
                history.setVisibility(View.VISIBLE);
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference("uploads");

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage();
            }
        });

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUploadTask != null && mUploadTask.isInProgress())
                    Toast.makeText(getApplicationContext(), "Upload Is In Progress", Toast.LENGTH_SHORT).show();
                else if (name.getText().toString().isEmpty() || productType.getText().toString().isEmpty() || imgUri == null) {
                    Toast.makeText(getApplicationContext(), "Empty Cells", Toast.LENGTH_SHORT).show();
                } else {
                    uploadData();
                    Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UploadHistory.class);
                    startActivity(intent);
                }

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    public void uploadData() {
        if (name.getText().toString().isEmpty() || productType.getText().toString().isEmpty() || imgUri == null) {
            Toast.makeText(getApplicationContext(), "Empty Cells", Toast.LENGTH_SHORT).show();
        } else {
            uploadImage();
        }
    }

    public void uploadImage() {
        if (imgUri != null) {
            StorageReference fileReference = mStorageRef.child(name.getText().toString()+" ." +getFileExtension(imgUri));
            mUploadTask = fileReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful()) ;
                    Uri downloadUrl = urlTask.getResult();

                    UploadModel product = new UploadModel(
                            productType.getText().toString().trim(),
                            name.getText().toString().trim()
                            , downloadUrl.toString()
                    );

                    DatabaseReference z = FirebaseDatabase.getInstance().getReference()
                            .child("uploads")
                           // .child(productType.getText().toString())
                           // .child(imgProduct.toString())
                            .child(name.getText().toString());

                    z.setValue(product);
                   Intent intent = new Intent(getApplicationContext(),UploadHistory.class);
                    intent.putExtra("productImage",product.getImg());

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
               // Log.e(this.toString(), e.getMessage().toString());
                imgProduct.setImageResource(R.drawable.grocery);
            }
        }
    }


}
