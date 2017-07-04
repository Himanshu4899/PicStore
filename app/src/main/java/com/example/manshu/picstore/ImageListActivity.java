package com.example.manshu.picstore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private List<ImageUpload> imgList;
    private ListView lv;
    private ImageListAdpater adapter;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        imgList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listViewImage);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait loading list image");
        progressDialog.show();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        final String em = firebaseUser.getEmail();
 //       System.out.print(em);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(UploadActivity.FB_DATABASE_PATH);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ImageUpload img = snapshot.getValue(ImageUpload.class);
                    System.out.println(img.getEmail());
                        if(img.getEmail().equals(em)) {
                            imgList.add(img);
                        }



                }

                adapter = new ImageListAdpater(ImageListActivity.this,R.layout.image_item,imgList);

                lv.setAdapter(adapter);

               lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        Intent intent = new Intent(getApplicationContext(),DownloadActivity.class);
                       String url = imgList.get(i).getUrl();
                        String name = imgList.get(i).getName();

                        intent.putExtra("urls",url);
                        intent.putExtra("names",name);
                        startActivity(intent);

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressDialog.dismiss();
            }
        });


    }
}
