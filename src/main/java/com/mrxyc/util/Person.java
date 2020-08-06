package com.mrxyc.util;

import java.util.HashMap;
import java.util.Map;

public class Person {

    private String name;
    private Map<String, String> likes = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, String> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", likes=" + likes +
                '}';
    }
}
