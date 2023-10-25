package com.hepl.movieXMLTranslator.Movies;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

@XmlRootElement
public class MovieList {
    @XmlElement
    public ArrayList<Movie> movie = new ArrayList<>();
    @XmlAttribute
    public int count;

    public MovieList() {
    }
}
