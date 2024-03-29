package com.example.quizapp;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView questionTextView;
    TextView totalquestionTextView;
    Button ansa,ansb,ansc,ansd;
    Button submit;
    int score=0;
    int totalQuestion = quesans.questions.length;
    int currentQuestionidx=0;
    String selectedans="";

   @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalquestionTextView = findViewById(R.id.totalquestions);
        questionTextView = findViewById(R.id.question);
        ansa=findViewById(R.id.ansa);
        ansb=findViewById(R.id.ansb);
        ansc=findViewById(R.id.ansc);
        ansd=findViewById(R.id.ansd);
        submit=findViewById(R.id.submit);


        ansa.setOnClickListener(this);
        ansb.setOnClickListener(this);
        ansc.setOnClickListener(this);
        ansd.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalquestionTextView.setText("Total Questions : "+totalQuestion);

        loadNewQuestion();

    }
    private void loadNewQuestion(){
        if(currentQuestionidx==totalQuestion){
            finishQuiz();

            return;
        }
        questionTextView.setText(quesans.questions[currentQuestionidx]);
        ansa.setText(quesans.choices[currentQuestionidx][0]);
        ansb.setText(quesans.choices[currentQuestionidx][1]);
        ansc.setText(quesans.choices[currentQuestionidx][2]);
        ansd.setText(quesans.choices[currentQuestionidx][3]);
        selectedans="";

    }

    private void finishQuiz(){
        String passStatus;
        if(score >= totalQuestion*0.6){

            passStatus = "Passed";
        }else{
            passStatus ="Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz(){
        score =0;
        currentQuestionidx=0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view){
        ansa.setBackgroundColor(Color.WHITE);
        ansb.setBackgroundColor(Color.WHITE);
        ansc.setBackgroundColor(Color.WHITE);
        ansd.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view ;

        if(clickedButton.getId() == R.id.submit){
            if(!selectedans.isEmpty()){
                if(selectedans.equals(quesans.Correct_ans[currentQuestionidx])==true){
                    score++;
                }else{
                    clickedButton.setBackgroundColor(Color.YELLOW);
                }
                currentQuestionidx++;
                loadNewQuestion();
            }else{
            }
        }
        else

    {
        selectedans = clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.BLUE);
    }
    }
}