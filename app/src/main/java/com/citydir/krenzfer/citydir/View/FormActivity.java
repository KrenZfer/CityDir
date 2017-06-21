package com.citydir.krenzfer.citydir.View;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.citydir.krenzfer.citydir.Constants;
import com.citydir.krenzfer.citydir.Models.Makanan;
import com.citydir.krenzfer.citydir.Models.Penginapan;
import com.citydir.krenzfer.citydir.Models.TempatAbstract;
import com.citydir.krenzfer.citydir.Models.Wisata;
import com.citydir.krenzfer.citydir.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

    EditText nama;
    EditText deskripsi;
    Spinner spinnerCategory;
    Button buttonAdd;
    Button btnImage;
    Toolbar toolbar;
    boolean isAdd = true;
    boolean isUploadSuccess;
    private DatabaseReference mRef;
    StorageReference storageReference;
    FirebaseStorage storage;
    StorageMetadata metadata;
    Uri imagefile;
    String DownloadUri;
    Intent intent;
    Double lat, lon;
    double percent;
    final int RESULT_LOAD_IMAGE = 245;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        metadata = new StorageMetadata.Builder().setContentType("image/jpeg").build();

        nama = (EditText) findViewById(R.id.editTextNama);
        deskripsi = (EditText) findViewById(R.id.editTextDesc);
        spinnerCategory = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> dataString = new ArrayList<>();

        intent = getIntent();
        lat = intent.getDoubleExtra("latitude", 0);
        lon = intent.getDoubleExtra("longitude", 0);
        dataString.add("Kuliner");
        dataString.add("Wisata");
        dataString.add("Penginapan");

        ArrayAdapter<String> adapterString = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataString);
        adapterString.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(adapterString);


        buttonAdd = (Button) findViewById(R.id.btnAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

        btnImage = (Button) findViewById(R.id.btnImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addImage();
            }
        });

    }

    private void addImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "choose picture"), RESULT_LOAD_IMAGE);
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    //method for upload file
    public void uploadFile(final TempatAbstract tempat){
        if(imagefile != null){
            final ProgressDialog progress = new ProgressDialog(this);
            progress.setTitle("Upload Image");
            progress.show();

            StorageReference ref = storageReference.child(Constants.STORAGE_PATH_UPLOADS
                    + System.currentTimeMillis() + "." + getFileExtension(imagefile));

            ref.putFile(imagefile)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progress.dismiss();
                            isUploadSuccess = true;
                            //noinspection VisibleForTests,ConstantConditions
                            DownloadUri = taskSnapshot.getDownloadUrl().toString();
                            if(isAdd) {
                                tempat.setNamaTempat(nama.getText().toString());
                                tempat.setRating(0);
                                tempat.setDescription(deskripsi.getText().toString());
                                tempat.setLatitude(lat);
                                tempat.setLongitude(lon);
                                tempat.setImageURL(DownloadUri);
//                                tempat.setuID(mRef.push().getKey());
                                mRef.push().setValue(tempat);
                            }
                            new DatabaseReference.CompletionListener(){

                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    isAdd = false;
                                    Toast.makeText(FormActivity.this,
                                            "ADD DATA ERROR " + databaseError,
                                            Toast.LENGTH_LONG)
                                            .show();
                                }
                            };
                            Toast.makeText(FormActivity.this,
                                    "ADD DATA SUCCESS",
                                    Toast.LENGTH_LONG)
                                    .show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progress.dismiss();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //noinspection VisibleForTests
                            percent = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progress.setMessage("Upload" + ((int)percent) + " %");
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Image File is Not Found or Error", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data && null != data.getData()){
            imagefile = data.getData();
        }
    }

    public void add() {
        TempatAbstract tempat = new Makanan();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        if(spinnerCategory.getSelectedItem().toString().equals("Kuliner")){
            mRef = mDatabase.getReference().child(Constants.FIREBASE_CHILD_TEMPAT_KULINER);
            tempat = new Makanan();
        }else if(spinnerCategory.getSelectedItem().toString().equals("Wisata")){
            mRef = mDatabase.getReference().child(Constants.FIREBASE_CHILD_TEMPAT_WISATA);
            tempat = new Wisata();
        }else if(spinnerCategory.getSelectedItem().toString().equals("Penginapan")){
            mRef = mDatabase.getReference().child(Constants.FIREBASE_CHILD_TEMPAT_PENGINAPAN);
            tempat = new Penginapan();
        }else{
            isAdd = false;
        }

        //method to upload file
        uploadFile(tempat);
    }
}
