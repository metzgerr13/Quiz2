package com.example.metzger.quiz2;

/**
 * Created by Metzger on 3/26/2016.
 */
public class QuestionNode
{
    private QuestionNode next;
    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private String correctAnswer;

    public QuestionNode()
    {
        next = null;
        question = "What is the capital of Michigan?";
        answerA = "Mount Clemens";
        answerB = "Detroit";
        answerC = "Lansing";
        answerD = "Grand Rapids";
        correctAnswer = "Lansing";
    }

    public QuestionNode getNext()
    {
        return next;
    }

    public void setNext( QuestionNode next )
    {
        this.next = next;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion( String question )
    {
        this.question = question;
    }

    public String getAnswerA()
    {
        return answerA;
    }

    public void setAnswerA( String answerA )
    {
        this.answerA = answerA;
    }

    public String getAnswerB()
    {
        return answerB;
    }

    public void setAnswerB( String answerB )
    {
        this.answerB = answerB;
    }

    public String getAnswerC()
    {
        return answerC;
    }

    public void setAnswerC( String answerC )
    {
        this.answerC = answerC;
    }

    public String getAnswerD()
    {
        return answerD;
    }

    public void setAnswerD( String answerD )
    {
        this.answerD = answerD;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer( String correctAnswer )
    {
        this.correctAnswer = correctAnswer;
    }
}
