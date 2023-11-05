package com.hepl.movieXMLTranslator;

import com.hepl.movieXMLTranslator.Movies.*;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class translatorMoviesTxtToXmlApp {
    public static final String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    private static final String DELIMITER = "‣";


    public static void main(String[] args) throws Exception {
        MovieList movieList = new MovieList();
        Movie movieBuffer;
        BufferedReader reader;

        // Ouverture fichier
        try {
            reader = new BufferedReader(new FileReader(RESOURCES_PATH + "\\1000movies.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // Lecture des films
        while ((movieBuffer = readMovie(reader)) != null)
            movieList.movie.add(movieBuffer);

        movieList.count = movieList.movie.size();

        // Fermeture fichier
        reader.close();

        jaxbObjectToXML(movieList);
    }

    // Permet de récupérer à l'aide d'un BufferedReader le prochain film contenu dans le fichier,
    // renvoie un objet Movie contenant toutes les infos du film lues
    private static Movie readMovie(BufferedReader reader) throws Exception {
        Movie movie = new Movie();
        int i = 0;
        String line = reader.readLine();
        if (line == null || line.isEmpty())
            return null;
        String[] blocs = line.split(DELIMITER);

        for (Movie.MovieFields field : Movie.MovieFields.values()) {
            if (i == blocs.length)
                break;

            if (blocs[i].isEmpty()) {
                if (field == Movie.MovieFields.ID)
                    break;
                else continue;
            }

            switch (field) {
                case ID -> movie.identifier = Integer.parseInt(blocs[i]);
                case TITLE -> movie.title = blocs[i];
                case ORIGINAL_TITLE -> movie.originalTitle = blocs[i];
                case RELEASED_DATE -> movie.releaseDate = blocs[i];
                case STATUS -> movie.status = blocs[i];
                case VOTE_AVERAGE -> movie.voteAverage = Float.parseFloat(blocs[i]);
                case VOTE_COUNT -> movie.voteCount = Integer.parseInt(blocs[i]);
                case RUNTIME -> movie.runtime = Integer.parseInt(blocs[i]);
                case CERTIFICATION -> movie.certification = blocs[i];
                case POSTER_PATH -> movie.posterPath = blocs[i];
                case BUDGET -> movie.budget = Integer.parseInt(blocs[i]);
                case TAGLINE -> movie.tagline = blocs[i];
                case GENRES -> movie.genre = convertBlocToGenres(blocs[i]);
                case DIRECTORS -> movie.director = convertBlocToDirectors(blocs[i]);
                case ACTORS -> movie.actor = convertBlocToActors(blocs[i]);
            }
            i++;
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
            try {
                genres.add(new Genre(Integer.parseInt(genreFields[0]), genreFields[1]));

            } catch (NumberFormatException ignored) {

            }
        }

        return genres;
    }

    // Conversion depuis un bloc (String correspondant à un champ du film en train d'être lu)
    // vers une liste d'objet Person → correspond aux directeurs
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
                actors.add(new Actor(Integer.parseInt(actorFields[0]), actorFields[1]));
            } catch (NumberFormatException ignored) {

            }
        }
        return actors;
    }

    private static void jaxbObjectToXML(MovieList movie) {
        try {
            Marshaller jaxbMarshaller = JAXBContext.newInstance(MovieList.class).createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // écrit contenu du fichier xml que l'on va créer dans un String
            // et on y ajoute un doctype qui spécifie le fichier dtd associé
            StringWriter xmlStringWriter = new StringWriter();
            jaxbMarshaller.marshal(movie, xmlStringWriter);
            String doctype = "<!DOCTYPE movieList SYSTEM 'movies.dtd'>";
            String xmlWithDoctype = xmlStringWriter.toString().replaceFirst("yes", "no");
            xmlWithDoctype = xmlWithDoctype.replaceFirst(">", ">\n" + doctype);

            // écrit le string dans le fichier xml
            BufferedWriter writer = new BufferedWriter(new FileWriter(RESOURCES_PATH + "\\movies.xml"));
            writer.write(xmlWithDoctype);
            writer.flush();
            writer.close();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while trying to write content in movies.xml");
        }
    }
}
