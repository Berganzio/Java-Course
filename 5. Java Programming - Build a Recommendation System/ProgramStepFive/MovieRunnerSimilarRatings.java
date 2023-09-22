
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings("data/ratings.csv");
        System.out.println("read data for " + fr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = fr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        } */
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        System.out.println("read data for " + fr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new YearAfterFilter(year));
        filterCriteria.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        } */
    }

    public void printSimilarRatings() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(raterID, numSimilarRaters, minimalRaters);
        System.out.println("found " + similarRatings.size() + " movies");
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        String genre = "Mystery";
        Filter filterCriteria = new GenreFilter(genre);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println("found " + similarRatings.size() + " movies");
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        String directors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        Filter filterCriteria = new DirectorsFilter(directors);
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println("found " + similarRatings.size() + " movies");
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new GenreFilter(genre));
        filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println("found " + similarRatings.size() + " movies");
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
            System.out.println("\t" + MovieDatabase.getMinutes(r.getItem()) + " minutes");
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new YearAfterFilter(year));
        filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> similarRatings = fr.getSimilarRatingsByFilter(raterID, numSimilarRaters, minimalRaters, filterCriteria);
        System.out.println("found " + similarRatings.size() + " movies");
        for (Rating r : similarRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getMinutes(r.getItem()));
        }
    }
}
