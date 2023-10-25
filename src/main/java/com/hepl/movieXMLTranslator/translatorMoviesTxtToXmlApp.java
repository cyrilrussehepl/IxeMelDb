package com.hepl.movieXMLTranslator;

import com.hepl.movieXMLTranslator.Movies.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class translatorMoviesTxtToXmlApp {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    private static final char DELIMITER = '‣';


    public static void main(String[] args) throws Exception {
        MovieList movieList = new MovieList();
        Movie movieBuffer;
        FileReader reader;

        // Ouverture fichier
        try {
            reader = new FileReader(RESOURCES_PATH + "\\1000movies.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Lecture des films
        while ((movieBuffer = readMovie(reader)) != null)
            movieList.movie.add(movieBuffer);

        movieList.count = movieList.movie.size();

        jaxbObjectToXML(movieList);

        // Fermeture fichier
        reader.close();
    }

    // Permet de récupérer à l'aide d'un FileReader le prochain film contenu dans le fichier,
    // renvoie un objet Movie contenant toutes les infos du film lues
    private static Movie readMovie(FileReader reader) throws Exception {
        String bloc;
        Movie movie = new Movie();

        for (Movie.MovieFields field : Movie.MovieFields.values()) {
            bloc = getNextBloc(reader);
            if (bloc == null) return null;
            if (bloc.isEmpty()) {
                if (field == Movie.MovieFields.ID) return null;
                else continue;
            }

            switch (field) {
                case ID -> movie.identifier = Integer.parseInt(bloc);
                case TITLE -> movie.title = bloc;
                case ORIGINAL_TITLE -> movie.originalTitle = bloc;
                case RELEASED_DATE -> movie.releaseDate = new SimpleDateFormat("yyyy-mm-dd").parse(bloc);
                case STATUS -> movie.status = bloc;
                case VOTE_AVERAGE -> movie.voteAverage = Float.parseFloat(bloc);
                case VOTE_COUNT -> movie.voteCount = Integer.parseInt(bloc);
                case RUNTIME -> movie.runtime = Integer.parseInt(bloc);
                case CERTIFICATION -> movie.certification = bloc;
                case POSTER_PATH -> movie.posterPath = bloc;
                case BUDGET -> movie.budget = Integer.parseInt(bloc);
                case TAGLINE -> movie.tagline = bloc;
                case GENRES -> movie.genre = convertBlocToGenres(bloc);
                case DIRECTORS -> movie.director = convertBlocToDirectors(bloc);
                case ACTORS -> movie.actor = convertBlocToActors(bloc);
            }
        }
        return movie;
    }

    // Conversion depuis un bloc (String correspondant à un champ du film en train d'être lu)
    // vers une liste d'objet Genre
    private static ArrayList<Genre> convertBlocToGenres(String bloc) {
        ArrayList<Genre> genres = new ArrayList<>();

        String[] genresString = bloc.split("‖");
        for (String genre : genresString) {
            String[] genreFields = genre.split("․");
            genres.add(new Genre(Integer.parseInt(genreFields[0]), genreFields[1]));
        }

        return genres;
    }

    // Conversion depuis un bloc (String correspondant à un champ du film en train d'être lu)
    // vers une liste d'objet Person -> correspond aux directeurs
    private static ArrayList<Person> convertBlocToDirectors(String bloc) {
        ArrayList<Person> directors = new ArrayList<>();

        String[] directorsString = bloc.split("‖");
        for (String director : directorsString) {
            String[] directorFields = director.split("․");
            directors.add(new Person(Integer.parseInt(directorFields[0]), directorFields[1]));
        }

        return directors;
    }

    // Conversion depuis un bloc (String correspondant à un champ du film en train d'être lu)
    // vers une liste d'objet Actor
    private static ArrayList<Actor> convertBlocToActors(String bloc) {
        ArrayList<Actor> actors = new ArrayList<>();

        String[] actorsString = bloc.split("‖");
        for (String actor : actorsString) {
            String[] actorFields = actor.split("․");
            try {
                actors.add(new Actor(Integer.parseInt(actorFields[0]), actorFields[1], actorFields[2]));
            } catch (IndexOutOfBoundsException e) {
                actors.add(new Actor(Integer.parseInt(actorFields[0]), actorFields[1], null));
            }
        }
        return actors;
    }

    // A partir d'un Filereader, lis un "bloc" correspondant à un champ d'un film,
    // délimité par ce char : '‣'
    private static String getNextBloc(FileReader reader) throws IOException {
        int c;
        String bloc = new String("");
        while ((c = reader.read()) != (int) DELIMITER && c != -1 && c != (int) '\n') {
            bloc += (char) c;
        }
        return bloc;
    }

    private static void jaxbObjectToXML(MovieList movie) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(MovieList.class);
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //Store XML to File
            File file = new File(RESOURCES_PATH + "\\movies.xml");
            //Writes XML file to file-system
            jaxbMarshaller.marshal(movie, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
