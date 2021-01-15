package com.williamtburch.sora.ecrira;

import java.util.ArrayList;

public class Group {

    private String name;
    private String description;

    private ArrayList<Character> characters;

    public Group(String name){
        this.name = name;

    }

    public void addCharacter(Character character){
        characters.add(character);
    }

    public void removeCharacter(Character character){
        characters.remove(character);
    }

    public int getGroupSize(){
        return characters.size();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
