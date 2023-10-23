package com.hepl.Movies;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlRootElement
public class MovieList {

    @XmlElement
    public ArrayList<Movie> movies = new ArrayList<>();

    public MovieList() {
    }
}