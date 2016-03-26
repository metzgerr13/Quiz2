package com.example.metzger.quiz2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Metzger on 3/26/2016.
 */
public class IncorrectScreen extends Activity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.incorrect_layout );
    }

    public void incorrectContinue( View view )
    {
        if( Round.getInstance().getHead().getNext() == null )
        {
            Round.getInstance().setHead( null );
            Intent intent = new Intent( this, PlayerScreen.class );
            startActivity( intent );
        }
        else
        {
            Round.getInstance().setHead( Round.getInstance().getHead().getNext() );
            Intent intent = new Intent( this, QuestionScreen.class );
            startActivity( intent );
        }
    }
}
