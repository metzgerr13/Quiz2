package com.example.metzger.quiz2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Metzger on 3/26/2016.
 */
public class PlayerScreen extends Activity
{
    TextView playerName;
    TextView currentScore;
    TextView lifetimeScore;
    String name;
    Integer curScore;
    Integer lifeScore;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_screen_layout);

        playerName = (TextView) findViewById( R.id.playerName );
        currentScore = (TextView) findViewById( R.id.currentScore );
        lifetimeScore = (TextView) findViewById( R.id.lifetimeScore );

        name = Player.getInstance().getName();
        curScore = Player.getInstance().getCurrentScore();
        lifeScore = Player.getInstance().getLifetimeScore();

        playerName.setText( name );
        currentScore.setText( curScore.toString() );
        lifetimeScore.setText( lifeScore.toString() );
    }

    public void playerNextPage( View view )
    {
        String buttonText;
        buttonText = ((Button) view).getText().toString();
        if( buttonText.equals( "Begin/Continue Round" ) )
        {
            Intent intent = new Intent( this, QuestionScreen.class );
            startActivity( intent );
        }
        else
        {
            Intent intent = new Intent( this, ShopScreen.class );
            startActivity( intent );
        }
    }
}
