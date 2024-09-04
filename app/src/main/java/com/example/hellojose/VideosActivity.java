package com.example.hellojose;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideosActivity extends AppCompatActivity {

    VideoView videoView1;
    Button btnExit;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_videos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        videoView1 = findViewById(R.id.videoView1);
        btnExit = findViewById(R.id.btnExit);
        webView1 = findViewById(R.id.webView1);

        MediaController mediaController = new MediaController(VideosActivity.this);
        mediaController.setAnchorView(videoView1);
        videoView1.setMediaController(mediaController);
        videoView1.setVideoURI(Uri.parse("https://videos.pexels.com/video-files/27997378/12284785_1440_2560_25fps.mp4"));
        videoView1.start();

//        WebView

        webView1.getSettings().setJavaScriptEnabled(true);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/21abiu5ftMQ?si=-T7iUVopgsKagjEN\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        webView1.loadData(video, "text/html","utf-8");


        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}