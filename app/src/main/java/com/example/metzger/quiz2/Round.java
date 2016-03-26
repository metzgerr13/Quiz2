package com.example.metzger.quiz2;

/**
 * Created by Metzger on 3/26/2016.
 */
public class Round
{
    private static Round instance;
    private static QuestionNode head;

    public static void initInstance()
    {
        if( instance == null )
        {
            instance = new Round();
        }
    }

    private Round()
    {
        head = new QuestionNode();
        QuestionNode end;
        QuestionNode trail = head;

        for( int i = 0; i < 5; i++ )
        {
            end = new QuestionNode();
            trail.setNext( end );
            trail = end;
        }
    }

    public static Round getInstance()
    {
        return instance;
    }

    public QuestionNode getHead()
    {
        return head;
    }

    public void setHead( QuestionNode head )
    {
        this.head = head;
    }
}
