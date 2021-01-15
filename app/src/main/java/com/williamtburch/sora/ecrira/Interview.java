package com.williamtburch.sora.ecrira;

public class Interview {


    private String mQuestion;
    private String mAnswer;

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    public void setInterview(Interview interview){
        mQuestion = interview.getQuestion();
        mAnswer = interview.getAnswer();
    }

    private int mPosition ;

    public Interview(int position, String question, String answer){
        mQuestion = question;
        mAnswer = answer;
        mPosition = position;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }


    public void editQuestion(int position, String question){
        mPosition = position;
        mQuestion = question;
    }

    public void editAnswer(int position, String answer){
        mPosition = position;
        mAnswer = answer;
    }
}


