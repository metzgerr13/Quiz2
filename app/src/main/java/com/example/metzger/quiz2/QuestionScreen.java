package com.example.metzger.quiz2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Metzger on 3/26/2016.
 */
public class QuestionScreen extends Activity
{
    TextView questionText;
    TextView answerA;
    TextView answerB;
    TextView answerC;
    TextView answerD;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_layout);

        questionText = (TextView) findViewById( R.id.textQuestion );
        answerA = (TextView) findViewById( R.id.answerA );
        answerB = (TextView) findViewById( R.id.answerB );
        answerC = (TextView) findViewById( R.id.answerC );
        answerD = (TextView) findViewById( R.id.answerD );
        Round.initInstance();

        questionText.setText( Round.getInstance().getHead().getQuestion() );
        answerA.setText( Round.getInstance().getHead().getAnswerA() );
        answerB.setText( Round.getInstance().getHead().getAnswerB() );
        answerC.setText( Round.getInstance().getHead().getAnswerC() );
        answerD.setText( Round.getInstance().getHead().getAnswerD() );
    }

    public void onAnswer( View view )
    {
        String buttonText;
        buttonText = ((Button) view).getText().toString();

        if( buttonText.equals(Round.getInstance().getHead().getCorrectAnswer()) )
        {
            Intent intent = new Intent( this, CorrectScreen.class );
            startActivity( intent );
        }
        else
        {
            Intent intent = new Intent( this, IncorrectScreen.class );
            startActivity( intent );
        }
    }
}
