package com.example.pc.voice;


import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /*private static final int REQUEST_CODE = 0;
  private DevicePolicyManager mDPM;
  private ComponentName mAdminName;


  private TextView txtSpeechInput;
  private ImageButton btnSpeak;
  private final int REQ_CODE_SPEECH_INPUT = 100;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      try {
          // Initiate DevicePolicyManager.
          mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
          mAdminName = new ComponentName(this, DeviceAdminDemo.class);

          if (!mDPM.isAdminActive(mAdminName)) {
              Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
              intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
              intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
              startActivityForResult(intent, REQUEST_CODE);
          } else {
              // mDPM.lockNow();
              // Intent intent = new Intent(MainActivity.this,
              // TrackDeviceService.class);
              // startService(intent);

          }
      } catch (Exception e) {
          e.printStackTrace();
      }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);

      if (REQUEST_CODE == requestCode) {
          Intent intent = new Intent(MainActivity.this, TService.class);
          startService(intent);


      }

      switch (requestCode) {
          case REQ_CODE_SPEECH_INPUT: {
              if (resultCode == RESULT_OK && null != data) {

                  ArrayList<String> result = data
                          .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                  txtSpeechInput.setText(result.get(0));
              }
              break;
          }

      }
  }*/
  private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    private final int REQ_CODE_SPEECH_INPUT = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar
        //getActionBar().hide();

        ActionBar ab = getActionBar();
        if (ab != null)
            ab.hide();
        else {
            ab = getActionBar();
            if (ab != null) ab.hide();
        }

        btnSpeak.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }


    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}