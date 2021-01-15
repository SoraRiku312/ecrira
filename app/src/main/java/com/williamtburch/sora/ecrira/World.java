package com.williamtburch.sora.ecrira;

import java.util.UUID;

public class World {


    private UUID mID;
    private String mWorldName;
    private String mDescription;
    private String mImageURI;


    private int mWorldType;
    public static final int TYPE_PLANET = 0;
    public static final int TYPE_COUNTRY = 1;
    public static final int TYPE_STATE = 3;
    public static final int TYPE_CITY = 2;
    public static final int TYPE_UNDEFINED = 999;


    public World(){
        mID = UUID.randomUUID();
        mImageURI = "";
//        mWorldType = TYPE_UNDEFINED;

    }

    public World(UUID id){
        mID = id;
//        mWorldType = TYPE_UNDEFINED;
    }


    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }



    public String getWorldName() {
        return mWorldName;
    }

    public void setWorldName(String worldName) {
        mWorldName = worldName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getImageURI() {
        return mImageURI;
    }

    public void setImageURI(String imageURI) {
        mImageURI = imageURI;
    }

    public int getWorldType() {
        return mWorldType;
    }

    public void setWorldType(int worldType) {
        mWorldType = worldType;
    }










}
