package com.williamtburch.sora.ecrira;

public class Relationship {

    private String name;
    private Character ch1, ch2;

    public Relationship(Character ch1){
        this.ch1 = ch1;
    }

    public void setCh2(Character ch2) {
        this.ch2 = ch2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getCh2() {
        return ch2;
    }
}
