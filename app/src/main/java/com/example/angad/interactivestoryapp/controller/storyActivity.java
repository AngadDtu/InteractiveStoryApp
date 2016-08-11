package com.example.angad.interactivestoryapp.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.angad.interactivestoryapp.R;
import com.example.angad.interactivestoryapp.model.Page;
import com.example.angad.interactivestoryapp.model.Story;

public class storyActivity extends AppCompatActivity {
private static final String TAG=storyActivity.class.getSimpleName();
    private String mName;
    private Page mCurrentPage;
    Story mStory=new Story();
    ImageView storyImageView;
    TextView storyTextView;
    Button choiceText1;
    Button choiceText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Intent i=getIntent();
         mName=i.getStringExtra(getString(R.string.key_name));
        if(mName==null){
            mName="Friend";
        }
        Log.i(TAG,mName);
        storyImageView=(ImageView)findViewById(R.id.storyImageView);
        storyTextView=(TextView)findViewById(R.id.storyTextView);
        choiceText1=(Button)findViewById(R.id.choiceButton1);
        choiceText2=(Button)findViewById(R.id.choiceButton2);
        loadPage(0);

    }

    private void loadPage(int choice) {
        mCurrentPage = mStory.getPages(choice);
        storyImageView.setImageResource(mCurrentPage.getImageId());
        String holder = mCurrentPage.getText();
        holder = String.format(holder, mName);
        storyTextView.setText(holder);
        if (mCurrentPage.isFinal()) {
            choiceText1.setVisibility(View.INVISIBLE);
            choiceText2.setText("PLAY AGAIN");
            choiceText2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {
            choiceText1.setText(mCurrentPage.getChoice1().getText());
            choiceText2.setText(mCurrentPage.getChoice2().getText());
            choiceText1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int result1 = mCurrentPage.getChoice1().getNextPage();
                    loadPage(result1);
                }
            });
            choiceText2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int result2 = mCurrentPage.getChoice2().getNextPage();
                    loadPage(result2);
                }
            });

        }
    }
}
