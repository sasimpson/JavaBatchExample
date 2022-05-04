package com.ke5eo.models;

public class UserData {
    private String cell;
    Dob DobObject;
    Name NameObject;


    // Getter Methods

    public Dob getDob() {
        return DobObject;
    }

    public Name getName() {
        return NameObject;
    }

    // Setter Methods

    public void setDob(Dob dobObject) {
        this.DobObject = dobObject;
    }

    public void setName(Name nameObject) {
        this.NameObject = nameObject;
    }

    public String getFullName() {  return this.NameObject.getFirst() + " " + this.NameObject.getLast(); }
}

