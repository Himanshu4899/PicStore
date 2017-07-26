package com.example.manshu.picstore;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Random;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonDownload;
    DownloadManager downloadManager;
    private ImageView imView;
    private TextView dpImageName;
    private TextView txtSex;
    private TextView txtage;
    private TextView txtsym1;
    private TextView percent;
    private TextView caution;
    private Button buttonConsult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        buttonDownload=(Button)findViewById(R.id.buttonDownload);
        buttonDownload.setOnClickListener(this);
        buttonConsult = (Button)findViewById(R.id.buttonConsult);


        imView=(ImageView) findViewById(R.id.imView);
        dpImageName=(TextView) findViewById(R.id.dpImageName);
        txtSex =(TextView) findViewById(R.id.sex);
        txtage =(TextView) findViewById(R.id.age);
        txtsym1 =(TextView) findViewById(R.id.sym1);
        percent =(TextView) findViewById(R.id.percent);
        caution =(TextView) findViewById(R.id.caution);

        Intent i = getIntent();
        String url = i.getStringExtra("urls");
        String name=i.getStringExtra("names");
        String age = i.getStringExtra("age");
        String sex = i.getStringExtra("sex");
        String sym1 = i.getStringExtra("sym1");
        Random r = new Random();
        int n = r.nextInt(40)+60;

        Glide.with(DownloadActivity.this).load(url).into(imView);
        dpImageName.setText(name);
        txtage.setText(age);
        txtSex.setText(sex);
        txtsym1.setText(sym1);
        String s = Integer.toString(n);
        percent.setText(s);
        if(n<70){
            caution.setText("Low Chances, Of Type-A");
        }
        else if(n>=70 && n<80){
            caution.setText("Moderate Chances of Type-B");
        }
        else {
            caution.setText("Highly contious about Type-C, Immediate Consultant suggested");
        }

    }

    @Override
    public void onClick(View view) {

        if(view==buttonDownload) {
            downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
            Intent i = getIntent();
            String url = i.getStringExtra("urls");
            Uri uri=Uri.parse(url);
            DownloadManager.Request request=new DownloadManager.Request(uri);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            Long reference=downloadManager.enqueue(request);
        }

    }

    public void ConsultDoc(View v){
        Toast.makeText(DownloadActivity.this,"Reached",Toast.LENGTH_LONG).show();

        Intent i = new Intent(DownloadActivity.this, DoctorList.class);
        startActivity(i);
    }

}
