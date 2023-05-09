package com.example.mysoukhin.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.mysoukhin.R;
import com.example.mysoukhin.models.ProfileModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    public static final int GALARY_PICK = 200;
    ImageView forward1, forward2, forward3, forward4, forward5, forward6, new_camera;
    TextView profile_name, profile_email;
    CircleImageView circleImage;
    ImageView back;
    private FirebaseAuth mAuth;
    private FirebaseUser CurrentUser;
    private String UserId;
    private StorageReference mStorageReference;
    private  Uri imgUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        circleImage = view.findViewById(R.id.circleImage);
        profile_name = view.findViewById(R.id.profile_name);
        profile_email = view.findViewById(R.id.profile_email);
        back = view.findViewById(R.id.backId);

        forward1 = view.findViewById(R.id.forward1);
        forward2 = view.findViewById(R.id.forward2);
        forward3 = view.findViewById(R.id.forward3);
        forward4 = view.findViewById(R.id.forward4);
        forward5 = view.findViewById(R.id.forward5);
        forward6 = view.findViewById(R.id.forward6);
        new_camera = view.findViewById(R.id.new_cameraId);

        mAuth = FirebaseAuth.getInstance();
        CurrentUser = mAuth.getCurrentUser();
        UserId = CurrentUser.getUid();
        mStorageReference = FirebaseStorage.getInstance().getReference();

        getUserProfileData();

        new_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),MainActivity.class));
            }
        });

        forward1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PendingOrder.class);
                startActivity(intent);
            }
        });

        forward5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShippingAddressActivity.class);
                startActivity(intent);

            }
        });

        forward2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrderHistory.class);
                startActivity(intent);
            }
        });

        forward3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TokenActivity.class);
                startActivity(intent);
            }
        });

        forward4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TokenHistory.class);
                startActivity(intent);
            }
        });

        forward6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(getString(R.string.default_web_client_id))
                                .requestEmail()
                                .build();

                        GoogleSignInClient gsc = GoogleSignIn.getClient(getContext(), gso);
                        gsc.signOut();

                        Toast.makeText(getContext(), "You are successfully Logout", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);

                    }
                });
                dialog.show();

            }
        });

        return view;
    }

    private void getUserProfileData() {
        ProfileModel model = new ProfileModel();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference m = root.child("users").child(UserId);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    String Image = snapshot.child("image").getValue().toString();

                    GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getContext());
                    String name = signInAccount.getDisplayName();
                    String email = signInAccount.getEmail();

                    profile_name.setText(name);
                    profile_email.setText(email);
                    FirebaseDatabase database1 = FirebaseDatabase.getInstance();
                    database1.getReference().child("users").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getContext(), "added", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });

                    if (Image.equals("default")) {
                        Picasso.get().load(R.drawable.profile).into(circleImage);
                    } else
                        Picasso.get().load(Image).placeholder(R.drawable.profile).into(circleImage);
                }

                }
                @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        m.addListenerForSingleValueEvent(valueEventListener);
    }


    private void loadImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECT IMAGE"), GALARY_PICK);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProfileFragment.GALARY_PICK && resultCode == Activity.RESULT_OK && data.getData() != null && data != null) {
            imgUri = data.getData();
            circleImage.setImageURI(imgUri);

            try {
                Picasso.get().load(imgUri). fit().centerCrop().into(circleImage);
                UploadImageInStorageDataBase(imgUri);
            } catch (Exception e) {
                Log.e(this.toString(), e.getMessage().toString());
            }
        }
    }

    private void UploadImageInStorageDataBase(Uri resultUri){
        final StorageReference FilePath =mStorageReference.child("users_image").child(UserId+"jpg");

        FilePath.putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                FilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(final Uri uri) {
                        DatabaseReference mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(UserId);
                        mUserDatabase.child("image").setValue(uri.toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Picasso.get().load(uri.toString()).placeholder(R.drawable.profile).into(circleImage);


                            }
                        });
                    }
                });
            }
        });

    }

}



