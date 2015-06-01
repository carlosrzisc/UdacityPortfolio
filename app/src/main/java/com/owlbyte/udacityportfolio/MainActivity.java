package com.owlbyte.udacityportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchSpotifyApp(View view) {
        Toast.makeText(getApplication(),"This button will launch my Spotify app",Toast.LENGTH_SHORT).show();
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
