package com.example.darlington.testandroid;

import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class BossAct extends AppCompatActivity {
    private TextView textScreen, textQuestion, textTitle, textBtn;
    private ImageView bigBoss;
    Animation smallToBig;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boss);

        smallToBig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/FredokaOneRegular.ttf");

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textBtn = (TextView) findViewById(R.id.textbtn);
        textTitle = (TextView) findViewById(R.id.textTitle);
        bigBoss = (ImageView) findViewById(R.id.bigboss);
        bigBoss.startAnimation(smallToBig);


        textTitle.setTypeface(typeface);
        textQuestion.setTypeface(typeface);
        textBtn.setTypeface(typeface);
        textScreen.setTypeface(typeface);



    }
}
