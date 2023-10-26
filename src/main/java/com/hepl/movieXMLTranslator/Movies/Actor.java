package com.hepl.movieXMLTranslator.Movies;

public class Actor extends Person {
    public String character = null;

    public Actor(int id, String name, String character) {
        super(id, name);
        this.character = character;
    }

    public Actor(int id, String name){
        super(id, name);
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
