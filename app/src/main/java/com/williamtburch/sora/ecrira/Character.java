package com.williamtburch.sora.ecrira;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Character {


    private UUID mID;
    private String mFirstName;
    private String mLastName;
    private int mAge;
    private String mImageURI;

    public String getBiography() {
        return mBiography;
    }

    public void setBiography(String biography) {
        mBiography = biography;
    }

    private String mBiography;

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public void addScore(int score){
        mScore += score;
    }


//    private ArrayList<String> questions = new ArrayList<>();
//    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<Interview> interviews = new ArrayList<>();

//    public void addQuestion(String question){
//        questions.add(question);
//    }
//    public void addAnswer(String answer){
//        answers.add(answer);
//    }
//    public String getQuestion(int i){
//        return questions.get(i - 1);
//    }
//    public String getAnswer(int i){
//        return answers.get(i - 1);
//    }

//    public void addInterviews(){
//        for(int n = 0; n < questions.size(); n++){
//
//            interviews.add(new Interview(getQuestion(n + 1), getAnswer(n+1)));
//        }
//    }

    public void addInterview(int position, String question, String answer){
        interviews.add(new Interview(interviews.size(), question, answer));
    }
    public void addInterview(Interview interview){
        interviews.add(interview);
    }

    public Interview getInterview(int position){
        return interviews.get(position);
    }
    public void deleteInterview(int position){
        interviews.remove(position);
    }
    public void editInterview(int position, String question, String answer){
        Interview interview = interviews.get(position);
        interview.setQuestion(question);
        interview.setAnswer(answer);
    }

    public void editInterview(int position, Interview interview){
        Interview mInterview = interviews.get(position -1);
        mInterview.setInterview(interview);
    }

    public ArrayList<Interview> getInterviews(){
        return interviews;
    }

    private int mScore;
    private List<Character> characterShips;


    public boolean isNewForInterviews() {
        return isNewForInterviews;
    }

    public void setNewForInterviews(boolean newForInterviews) {
        isNewForInterviews = newForInterviews;
    }

    private boolean isNewForInterviews;

    private boolean hasImage;


    private String mDescription;
    private String mNickName;
    private String  mGender;

    public Character(){
        mID = UUID.randomUUID();
        hasImage = false;
        mImageURI = "";
        mScore = 0;
    //    isNewForInterviews = true;
        setNewForInterviews(true);


    }

    public Character(UUID id){
        mID = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public int getAge() {
        return mAge;
    }
    public String getAgeAsString(){
        return String.valueOf(mAge);
    }

    public void setAge(int age) {
        mAge = age;
    }
    public void setAgeAsString(String age){mAge = Integer.valueOf(age);}


    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }


    public String getImageURI() {
        return mImageURI;
    }

    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getNickName() {
        return mNickName;
    }

    public void setNickName(String nickName) {
        mNickName = nickName;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public boolean isHasImage() {
        return !(this.getImageURI().equals(""));
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getPhotoFileName(){
        return "IMG_" + getID().toString() + ".jpg";
    }


}
