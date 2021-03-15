package com.liuyao.design_patterns.builder;

public class Person {

    private int id;
    private String name;

    private int age;
    private String loc;
    private char sex;

    private int weight;
    private int height;
    private String hair;
    private String face;

    private Person() { }

    public static class PersonBuilder {
        Person p = new Person();

        public PersonBuilder buildBasie(int id, String name) {
            p.id = id;
            p.name = name;
            return this;
        }

        public PersonBuilder buildBody(int weight, int height) {
            p.weight = weight;
            p.height = height;
            return this;
        }

        public PersonBuilder buildAge(int age) {
            p.age = age;
            return this;
        }

        public PersonBuilder buildLoc(String loc) {
            p.loc = loc;
            return this;
        }

        public PersonBuilder buildSex(char sex) {
            p.sex = sex;
            return this;
        }

        public PersonBuilder buildFace(String face) {
            p.face = face;
            return this;
        }

        public PersonBuilder buildHair(String hair) {
            p.hair = hair;
            return this;
        }

        public Person build() {
            return p;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }
}
