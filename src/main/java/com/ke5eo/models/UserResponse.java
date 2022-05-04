package com.ke5eo.models;

import java.util.ArrayList;

public class UserResponse {

    private ArrayList<UserData> results = new ArrayList <> ();


    public ArrayList<UserData> getResults() {
        return results;
    }

    public void setResults(ArrayList<UserData> results) {
        this.results = results;
    }
}
