package com.pevel.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   Button gobutton;
   TextView result;
   TextView textview;
   TextView timetext;
   ConstraintLayout gamelayout;
   int score=0;
   int ques=0;
   Button button0;
   Button button1;
   Button button2;
   Button button3;
   Button playagain;
   TextView scoretext;
   int locationofcorrectanswer;
   ArrayList<Integer>answer=new ArrayList<Integer>();
    public void start(View view)
    {

        gobutton.setVisibility(View.INVISIBLE);
        gamelayout.setVisibility(View.VISIBLE);
        playagain(findViewById(R.id.timetext));
    }
    public void newquestion()
    {

        Random rand=new Random();
        int a=rand.nextInt(101);
        int b=rand.nextInt(101);
        textview.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationofcorrectanswer=rand.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++)
        {
            if(i==locationofcorrectanswer)
                answer.add(a+b);
            else
            {  int wronganswer=rand.nextInt(200);
                while(wronganswer==a+b)
                    wronganswer=rand.nextInt(200);
                answer.add(wronganswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationofcorrectanswer).equals(view.getTag().toString())) {
            score++;
            result.setText("Correct!");
        } else {
            result.setText("Wrong!");
        }
        ques++;
        scoretext.setText(Integer.toString(score)+"/"+Integer.toString(ques));
        newquestion();
    }
public void playagain(View view){
 playagain.setVisibility(View.INVISIBLE);
 score=0;
 ques=0;
 timetext.setText("30s");
 result.setText("");
 scoretext.setText(Integer.toString(score)+"/"+Integer.toString(ques));
 newquestion();
    new CountDownTimer(30100,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timetext.setText(String.valueOf(millisUntilFinished/1000)+"s");
        }

        @Override
        public void onFinish() {
            result.setText("Game Over!");
            timetext.setText("0s");
            playagain.setVisibility(View.VISIBLE);
        }
    }.start();
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gobutton=findViewById(R.id.gobutton);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        textview=findViewById(R.id.questext);
        scoretext=findViewById(R.id.scoretext);
        result=findViewById(R.id.result);
        timetext=findViewById(R.id.timetext);
        playagain=findViewById(R.id.playagain);
        gamelayout=findViewById(R.id.gamelayout);
        gobutton.setVisibility(View.VISIBLE);
        gamelayout.setVisibility(View.INVISIBLE);

    }
}
