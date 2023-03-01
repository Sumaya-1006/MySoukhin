package com.example.mysoukhin.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mysoukhin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    ImageView forward1,forward2,forward3,forward4,forward5, forward6, new_camera;
    TextView profile_name,profile_email;
    CircleImageView circleImage;
    private final int GALARY_PICK=1;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private String UserId;
    private StorageReference mStorageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        circleImage = view.findViewById(R.id.circleImage);
        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);

        forward1 = view.findViewById(R.id.forward1);
        forward2 = view.findViewById(R.id.forward2);
        forward3 = view.findViewById(R.id.forward3);
        forward4 = view.findViewById(R.id.forward4);
        forward5 = view.findViewById(R.id.forward5);
        forward6 = view.findViewById(R.id.forward6);
        new_camera = view.findViewById(R.id.new_cameraId);

        mAuth=FirebaseAuth.getInstance();
        CurrentUser =mAuth.getCurrentUser();
        UserId =CurrentUser.getUid();
        mStorageReference= FirebaseStorage.getInstance().getReference();

        getUserProfileData();

        new_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadImage();
            }
        });

         forward5.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getContext(),ShippingAddressActivity.class);
                 startActivity(intent);

             }
         });

        forward6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getContext(), "You are successfully Logout", Toast.LENGTH_SHORT).show();
              // Intent intent = new Intent(getContext(),LogoutActivity.class);
              // startActivity(intent);
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.custom_dialog);
                Button logout = dialog.findViewById(R.id.logOutId);
                Button cancel = dialog.findViewById(R.id.cancelId);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                    }
                });

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(), "You are successfully Logout", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(),LoginActivity.class);
                        startActivity(intent);

                    }
                });
                dialog.show();

            }
        });



        return view;
    }

    private void getUserProfileData() {
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference m = root.child("users").child(UserId);
        ValueEventListener valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String Name = snapshot.child("Name").getValue().toString();
                    String Image = snapshot.child("Image").getValue().toString();
                    profile_name.setText(Name);
                    profile_email.setText(CurrentUser.getEmail().toString());

                    if (Image.equals("default")) {
                        Picasso.get().load(R.drawable.profile).into(circleImage);
                    } else
                        Picasso.get().load(Image).placeholder(R.drawable.profile).into(circleImage);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        };
        m.addListenerForSingleValueEvent(valueEventListener);
    }


    private void loadImage() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "SELECT IMAGE"), GALARY_PICK);
        }
    }
