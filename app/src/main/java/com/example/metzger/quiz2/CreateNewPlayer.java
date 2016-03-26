package com.example.metzger.quiz2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Metzger on 3/26/2016.
 */
public class CreateNewPlayer extends Activity
{
    EditText playerName;
    boolean driver;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView(R.layout.create_new_player_layout);

        playerName = (EditText) findViewById( R.id.playerNameText );
    }

    public void returnToHomepage( View view )
    {
        String nameText;

        if( R.id.availableDriver == 1 )
        {
            driver = true;
        }
        else
        {
            driver = false;
        }

        nameText = playerName.getText().toString();

        Player.initInstance();
        Player.getInstance().setName( nameText );
        Player.getInstance().setAvailableDriver( driver );
        Intent intent = new Intent( this, PlayerScreen.class );

        startActivity(intent);
    }
}
