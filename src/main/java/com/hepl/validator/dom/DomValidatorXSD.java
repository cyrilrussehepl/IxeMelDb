package com.hepl.validator.dom;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;

import com.hepl.validator.Utils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.ListIterator;

public class DomValidatorXSD {
    public static void main(String[] args) throws Exception {
        Schema schema = Utils.loadSchema(Utils.XSD_FILE);
        Document document = Utils.parseXmlDom(Utils.XML_FILE, false);

        try {
            Utils.validateXSD(schema, new DOMSource(document));
        } catch (Exception e) {
            System.out.println("Erreur lors de la validation : " + e.getMessage());
            return;
        }
        System.out.println("Validation OK");

        // Compte nombre balise <certification>PG-13</certification>----------------------------------------------------
        int nbCertifPG13 = 0;
        NodeList certifications = document.getElementsByTagName("certification");

        // Parcours de tous les noeuds certification
        for (int i = 0; i < certifications.getLength(); i++) {
            // Récupération des noeuds enfants certification
            NodeList child = certifications.item(i).getChildNodes();
            // Normalement au nombre de 1 mais pour être sur, parcours dans une boucle
            for (int j = 0; j < child.getLength(); j++) {
                Node node = child.item(j);
                // nodetype 3 = text
                if (node.getNodeType() == 3 && node.getNodeValue().equals("PG-13"))
                    nbCertifPG13++;
            }
        }
        System.out.println("Calcul -> nbCertifPG13 = " + nbCertifPG13);

        // Répertorie les 10 films les mieux notés----------------------------------------------------------------------

        LinkedList<MovieRate> tenBestRatedMovies = new LinkedList<>();

        NodeList movies = document.getElementsByTagName("movie");
        for (int i = 0; i < movies.getLength(); i++) {
            NodeList children = movies.item(i).getChildNodes();
            MovieRate movieRateBuffer = new MovieRate();
            for (int j = 0; j < children.getLength(); j++) {
                Node child = children.item(j);
                if (child.getNodeName().equals("title"))
                    movieRateBuffer.title = child.getChildNodes().item(0).getNodeValue();
                else if (child.getNodeName().equals("voteAverage")) {
                    movieRateBuffer.voteAverage = Float.parseFloat(child.getChildNodes().item(0).getNodeValue());
                    break;
                }
            }

            // Ajoute le film dans la liste chainée si dans le top10
            // Pas d'ajout si note inférieur au 10 déjà présents
            if (tenBestRatedMovies.size() == 10 && tenBestRatedMovies.getLast().voteAverage >= movieRateBuffer.voteAverage)
                continue;

            // Insère le film à sa position dans le classement des 10 premiers
            ListIterator<MovieRate> tenBestListIterator = tenBestRatedMovies.listIterator();
            boolean added = false;
            while (tenBestListIterator.hasNext()) {
                MovieRate movieRate = tenBestListIterator.next();
                if (movieRate.voteAverage < movieRateBuffer.voteAverage) {
                    tenBestListIterator.previous();
                    tenBestListIterator.add(movieRateBuffer);
                    added = true;
                    break;
                }
            }

            // Si pas ajouté alors il arrive en dernier
            if (!added)
                tenBestRatedMovies.addLast(movieRateBuffer);
            // Retire le dernier de la liste si plus de 10 film
            if (tenBestRatedMovies.size() > 10)
                tenBestRatedMovies.removeLast();
        }


        // Affiche titre et note des 10 meilleurs films
        System.out.println("\n10 best rated movies : ");
        for (MovieRate movie : tenBestRatedMovies)
            System.out.println(movie.toString());

    }

}

class MovieRate {
    protected String title;
    protected float voteAverage;

    protected MovieRate() {

    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", voteAverage=" + voteAverage +
                '}';
    }
}
