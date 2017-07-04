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

import com.bumptech.glide.Glide;

public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonDownload;
    DownloadManager downloadManager;
    private ImageView imView;
    private TextView dpImageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        buttonDownload=(Button)findViewById(R.id.buttonDownload);
        buttonDownload.setOnClickListener(this);

        imView=(ImageView) findViewById(R.id.imView);
        dpImageName=(TextView) findViewById(R.id.dpImageName);

        Intent i = getIntent();
        String url = i.getStringExtra("urls");
        String name=i.getStringExtra("names");
        Glide.with(DownloadActivity.this).load(url).into(imView);
        dpImageName.setText(name);
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
}
