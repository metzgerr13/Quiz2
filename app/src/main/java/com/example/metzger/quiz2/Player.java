package com.example.metzger.quiz2;

import android.app.Application;

/**
 * Created by Metzger on 3/26/2016.
 */
public class Player
{
    private static Player instance;
    private static int id;
    private static String name;
    private static boolean availableDriver;
    private static int currentScore;
    private static int lifetimeScore;

    public static void initInstance()
    {
        if ( instance == null )
        {
            instance = new Player();
        }
    }

    private Player()
    {
        id = 0;
        name = "tempName";
        currentScore = 0;
        lifetimeScore = 0;
        availableDriver = false;
    }

    public static Player getInstance()
    {
        return instance;
    }

    public int getId()
    {
        return id;
    }

    public void setId( int id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public boolean getAvailableDriver()
    {
        return availableDriver;
    }

    public void setAvailableDriver( boolean driver )
    {
        availableDriver = driver;
    }

    public int getCurrentScore()
    {
        return currentScore;
    }

    public void setCurrentScore( int score )
    {
        currentScore = score;
    }

    public int getLifetimeScore()
    {
        return lifetimeScore;
    }

    public void setLifetimeScore( int score )
    {
        lifetimeScore = score;
    }
}
