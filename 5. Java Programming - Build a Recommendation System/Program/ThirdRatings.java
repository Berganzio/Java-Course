import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }

    public double getAverageById(String id, int minimalRaters) {
        double total = 0.0;
        int count = 0;
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                total += r.getRating(id);
                count++;
            }
        }
        if (count >= minimalRaters) {
            return total / count;
        }
        return 0.0;
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String id : movies) {
            double avg = getAverageById(id, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(id, avg));
            }
        }
        return avgRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> avgRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String id : movies) {
            double avg = getAverageById(id, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(id, avg));
            }
        }
        return avgRatings;
    }
}