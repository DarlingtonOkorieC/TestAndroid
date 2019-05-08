package com.example.darlington.testandroid;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int presCounter = 0;
    private int maxPresCounter = 6;
    private String[] keys = {"N", "T", "I", "E", "T", "Y", "S"};
    private String textAnswer = "Entity";
    private TextView textScreen, textQuestion, textTitle;
    Animation smallBigFoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        smallBigFoot = AnimationUtils.loadAnimation(this, R.anim.smallbigfoot);

        keys = shuffleArray(keys);

        for (String key : keys){
            addView(((LinearLayout) findViewById(R.id.layoutParent)),
                    key, ((EditText) findViewById(R.id.editText)));

        }
        maxPresCounter = 6;
    }
    private String[] shuffleArray(String[] ar) {

        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i --){
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;

    }

    private void addView(LinearLayout viewParent, final String text, final EditText editText){
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 30;
        final TextView textView = new TextView(this);

        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/FredokaOneRegular.ttf");

        textQuestion = (TextView) findViewById(R.id.textQuestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.textTitle);

        textQuestion.setTypeface(typeface);
        textScreen.setTypeface(typeface);
        textTitle.setTypeface(typeface);

        editText.setTypeface(typeface);
        textView.setTypeface(typeface);

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (presCounter< maxPresCounter){
                    if (presCounter == 0){
                        editText.setText("");
                        editText.setText(String.format("%s%s", editText.getText().toString(), text));
                        textView.startAnimation(smallBigFoot);
                        textView.animate().alpha(0).setDuration(300);
                        presCounter++;

                        if (presCounter == maxPresCounter){
                            dovalidate();
                        }
                    }
                }
            }
        });

        viewParent.addView(textView);


    }
    private void dovalidate(){

        presCounter = 0;
        EditText editText = findViewById(R.id.editText);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        if (editText.getText().toString().equals(textAnswer)){
       //     Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();

            Intent a = new Intent(MainActivity.this, BossAct.class);
            startActivity(a);

            editText.setText("");

        }else{
            Toast.makeText(this, "Not correct", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }
        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key: keys){
            addView(linearLayout, key, editText);
        }

    }

}
