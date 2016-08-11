package com.example.angad.interactivestoryapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.angad.interactivestoryapp.R;

public class MainActivity extends AppCompatActivity {
    private EditText mEditText;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.nameText);
        mButton = (Button) findViewById(R.id.startButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditText.getText().toString();
                // Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                startAct(name);
            }
        });


    }

    private void startAct(String name) {
        Intent i = new Intent(this, storyActivity.class);
        i.putExtra(getString(R.string.key_name), name);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mEditText.setText("");
    }
}

