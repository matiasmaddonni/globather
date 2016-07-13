package com.example.mmadddoni.globather.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mmadddoni.globather.R;

/**
 * Created by mmadddoni on 12/07/16.
 */
public class WelcomeActivity extends AppCompatActivity {
    private static final String WELCOME_CITY = "WELCOME_CITY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button button = (Button) findViewById(R.id.button_welcome);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = (EditText) findViewById(R.id.edit_welcome);
                String selected = edit.getText().toString();
                if (!selected.isEmpty()) {
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    intent.putExtra(WELCOME_CITY, selected);
                    startActivity(intent);
                } else {
                    //TODO: Show alert
                }
            }
        });
    }
}
