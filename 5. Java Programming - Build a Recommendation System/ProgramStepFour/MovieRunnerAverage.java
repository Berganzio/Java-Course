import javax.crypto.SealedObject;
import java.util.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings();
        System.out.println("read data for " + sr.getRaterSize() + " raters");
        System.out.println("read data for " + sr.getMoviesSize() + " movies");
        
        // print list of movies and their average ratings if they have at least X ratings sorted by averages
        int minimalRaters = 12;
        ArrayList<Rating> avgRatings = sr.getAverageRatings(minimalRaters);
        Collections.sort(avgRatings);
        for (Rating r : avgRatings) {
            System.out.println(r.getValue() + " " + sr.getTitle(r.getItem()));
        }
        System.out.println("found " + avgRatings.size() + " movies");
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        int minimalRaters = 12;
        ArrayList<Rating> ratingList = sr.getAverageRatings(minimalRaters);
        /* String movieTitle = "Vacation";
        for (Rating i : ratingList) {
            if (sr.getTitle(i.getItem()).equals(movieTitle)) {
                System.out.println(movieTitle + " --- average rating: " + i.getValue());
            }
        } */
        // find the total number of movies with a minimum number of ratings
        System.out.println("Total number of movies with " + minimalRaters + " or more ratings: " + ratingList.size());
        // find the name of the movie with the lowest rating in the list of movies with a minimum number of ratings
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            // print the name of the movie and its rating
            System.out.println(sr.getTitle(i.getItem()) + " --- average rating: " + i.getValue());
        }
    }
}