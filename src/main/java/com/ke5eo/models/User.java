package com.ke5eo.models;

public class User {
    private String name;
    private Integer age;

    public User(String fullName, Integer age) {
        this.name = fullName;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
