package com.hepl.movieXMLTranslator.Movies;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    @XmlAttribute
    public int identifier;
    public String title;
    public String originalTitle;
    public Date releaseDate;
    public String status;
    public float voteAverage;
    public int voteCount;
    public int runtime;
    public String certification;
    public String posterPath;
    public int budget;
    public String tagline;
    public ArrayList<Genre> genre;
    public ArrayList<Person> director;
    public ArrayList<Actor> actor;

    public enum MovieFields {
        ID,
        TITLE,
        ORIGINAL_TITLE,
        RELEASED_DATE,
        STATUS,
        VOTE_AVERAGE,
        VOTE_COUNT,
        RUNTIME,
        CERTIFICATION,
        POSTER_PATH,
        BUDGET,
        TAGLINE,
        GENRES,
        DIRECTORS,
        ACTORS
    }

    @Override
    public String toString() {
        return "id : " + identifier + "\n"
                + "title : " + title + "\n"
                + "originalTitle : " + originalTitle + "\n"
                + "releaseDate : " + releaseDate + "\n"
                + "status : " + status + "\n"
                + "voteAverage : " + voteAverage + "\n"
                + "voteCount : " + voteCount + "\n"
                + "runtime : " + runtime + "\n"
                + "certification : " + certification + "\n"
                + "posterPath : " + posterPath + "\n"
                + "budget : " + budget + "\n"
                + "tagline : " + tagline + "\n"
                + "genres : " + "\n"
                + "directors : " + "\n"
                + "actors : " + "\n";
    }
}
