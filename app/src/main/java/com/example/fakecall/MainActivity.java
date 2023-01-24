package com.example.fakecall;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fakecall.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private boolean is_recording = false;
    private MediaRecorder mediaRecorder = null;
    public void record_first_sound(View view){
        if (! self.is_recording) {
            view.setText("Stop");
            switch(view.getId()){
                case R.id.first:{
                    self.mediaRecorder.setOutputFile("first.wave");
                }
                case R.id.second:{
                    self.mediaRecorder.setOutputFile("second.wave");
                }
                case R.id.third: {
                    self.mediaRecorder.setOutputFile("third.wave");
                }
            }
            self.mediaRecorder.prepare();
            self.mediaRecorder.start();
        }
        else{
            self.mediaRecorder.stop();
            findViewById(R.id.first).setText("Record first voice");
            findViewById(R.id.second).setText("Record second voice");
            findViewById(R.id.third).setText("Record third voice");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        self.mediaRecorder = new MediaRecorder();
        self.mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        self.mediaRecorder.setOutputFormat(AudioFormat.ENCODING_PCM_16BIT);
        self.mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        self.mediaRecorder.setAudioChannels(1);
        self.mediaRecorder.setAudioEncodingBitRate(128000);
        self.mediaRecorder.setAudioSamplingRate(48000);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}