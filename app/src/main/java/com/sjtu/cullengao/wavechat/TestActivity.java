package com.sjtu.cullengao.wavechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.sjtu.cullengao.wavechat.Tools.Doppler;

/**
 * Created by CullenGao on 4/20/16.
 */
public class TestActivity extends AppCompatActivity {

    private ToggleButton toggleButton;

    private Doppler doppler;

    public boolean gestureOn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        doppler = new Doppler();

        addListenerOnButton();
    }


    public void addListenerOnButton() {
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton2);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("NULL");

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toggleButton.isChecked()) {
                    start();
                } else {
                    stop();
                }
            }
        });
    }

    public void start() {
        Log.i("STATE", "START");
        final TextView textView = (TextView) findViewById(R.id.textView);

        gestureOn = true;

        doppler.start();

        doppler.removeReadCallback();
        doppler.setOnGestureListener(new Doppler.OnGestureListener() {
            @Override
            public void onPush() {
                Log.i("STATE", "PUSH");
                if (gestureOn) {
//                    textView.setTextColor(0xFF0000);
                    textView.setText("PUSH");
                }
            }

            @Override
            public void onPull() {
                Log.i("STATE", "PULL");
                if (gestureOn) {
//                    textView.setTextColor(0x008080);
                    textView.setText("PULL");
                }
            }

            @Override
            public void onTap() {
                Log.i("STATE", "TAP");
                if (gestureOn) {
//                    textView.setTextColor(0xFFFF00);
                    textView.setText("TAP");
                }
            }

            @Override
            public void onDoubleTap() {
                Log.i("STATE", "TAP * 2");
                if (gestureOn) {
//                    textView.setTextColor(0x0000FF);
                    textView.setText("TAP * 2");
                }
            }

            @Override
            public void onNothing() {
                if (gestureOn) {
//                    textView.setTextColor(0xC0C0C0);
                    textView.setText("START");
                }
                else {
                    Log.i("STATE", "NOTHING");
                }
            }
        });
    }

    public void stop() {
        Log.i("STATE", "STOP");

        gestureOn = false;
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("STOP");
        doppler.removeReadCallback();
    }
}