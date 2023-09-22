import java.util.*;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 35;
        ArrayList<Rating> avgRatings = tr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        } */
    }

    public void printAverageRatingsByYearAfter() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 20;
        int year = 2000;
        Filter filterCriteria = new YearAfterFilter(year);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        } */
    }

    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 20;
        String genre = "Comedy";
        Filter filterCriteria = new GenreFilter(genre);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        } */
    }

    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        Filter filterCriteria = new MinutesFilter(minMinutes, maxMinutes);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        } */
    }

    public void printAverageRatingsByDirectors() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 4;
        String directors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        Filter filterCriteria = new DirectorsFilter(directors);
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        } */
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new YearAfterFilter(year));
        filterCriteria.addFilter(new GenreFilter(genre));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        } */
    }

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        System.out.println("read data for " + tr.getRaterSize() + " raters");

        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");

        int minimalRaters = 3;
        int minMinutes = 90;
        int maxMinutes = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        AllFilters filterCriteria = new AllFilters();
        filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        filterCriteria.addFilter(new DirectorsFilter(directors));
        ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(minimalRaters, filterCriteria);
        Collections.sort(avgRatings);
        System.out.println("found " + avgRatings.size() + " movies");
        /* for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        } */
    }

    public void test() {
        System.out.println("printAverageRatings() calling");
        printAverageRatings();
        System.out.println("printAverageRatingsByYearAfter() calling");
        printAverageRatingsByYearAfter();
        System.out.println("printAverageRatingsByGenre() calling");
        printAverageRatingsByGenre();
        System.out.println("printAverageRatingsByMinutes() calling");
        printAverageRatingsByMinutes();
        System.out.println("printAverageRatingsByDirectors() calling");
        printAverageRatingsByDirectors();
        System.out.println("printAverageRatingsByYearAfterAndGenre() calling");
        printAverageRatingsByYearAfterAndGenre();
        System.out.println("printAverageRatingsByDirectorsAndMinutes() calling");
        printAverageRatingsByDirectorsAndMinutes();
    }
}