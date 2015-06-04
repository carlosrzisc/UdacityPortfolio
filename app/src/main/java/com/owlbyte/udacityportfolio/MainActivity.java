package com.owlbyte.udacityportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.owlbyte.udacityportfolio.spotifystreamer.SpotifyStreamerActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSpotifyApp(View view) {
        Intent intent = new Intent(this, SpotifyStreamerActivity.class);
        startActivity(intent);
    }

    public void launchScoreApp(View view) {
        Toast.makeText(getApplication(),"This button will launch my Score app",Toast.LENGTH_SHORT).show();
    }

    public void launchLibraryApp(View view) {
        Toast.makeText(getApplication(),"This button will launch my Library app",Toast.LENGTH_SHORT).show();
    }

    public void launchBuildItBigger(View view) {
        Toast.makeText(getApplication(),"This button will launch my Build it bigger app",Toast.LENGTH_SHORT).show();
    }

    public void launchXYZReaderApp(View view) {
        Toast.makeText(getApplication(),"This button will launch my XYZ Reader app",Toast.LENGTH_SHORT).show();
    }

    public void launchCapstoneApp(View view) {
        Toast.makeText(getApplication(),"This button will launch my Capstone app",Toast.LENGTH_SHORT).show();
    }
}
