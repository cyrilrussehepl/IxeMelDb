package com.hepl.Movies;

public class Actor extends Person {
    public String character;

    public Actor(int id, String name, String character) {
        super(id, name);
        this.character = character;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "character='" + character + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
