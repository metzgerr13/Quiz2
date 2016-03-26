package com.example.metzger.quiz2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Metzger on 3/26/2016.
 */
public class SetupScreen extends Activity
{
    int players;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.setup_layout );
    }

    public void numberOfPlayers( View view )
    {
        String buttonText;
        buttonText = ((Button) view).getText().toString();

        switch( buttonText )
        {
            case "Two Players":
                players = 2;
                break;
            case "Three Players":
                players = 3;
                break;
            case "Four Players":
                players = 4;
                break;
            case "Five Players":
                players = 5;
                break;
            case "Six Players":
                players = 6;
                break;
        }

        Intent intent = new Intent( this, PlayerScreen.class );
        intent.putExtra( "Players", players );
        startActivity( intent );
    }
}