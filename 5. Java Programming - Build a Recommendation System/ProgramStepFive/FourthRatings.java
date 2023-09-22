import java.util.*;

public class FourthRatings {

    public FourthRatings() {
        // default constructor
        this("ratings.csv");
    }

    public FourthRatings(String ratingsfile) {
        RaterDatabase.initialize(ratingsfile);
    }

    public int getRaterSize() {
        return RaterDatabase.size();
    }

    public double getAverageById(String id, int minimalRaters) {
        double total = 0.0;
        int count = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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

    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0.0;
        ArrayList<String> myRatings = me.getItemsRated();
        for (String id : myRatings) {
            if (r.hasRating(id)) {
                double myRating = me.getRating(id) - 5;
                double rRating = r.getRating(id) - 5;
                dotProduct += myRating * rRating;
            }
        }
        return dotProduct;
    }


    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                double dotProduct = dotProduct(me, r);
                if (dotProduct > 0) {
                    similarities.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);

        for (String movieID : MovieDatabase.filterBy(new TrueFilter())) {
            int numRatings = 0;
            double totalWeight = 0.0;

            for (int i = 0; i < numSimilarRaters; i++) {
                Rating simRating = similarities.get(i);
                String raterID = simRating.getItem();
                double weight = simRating.getValue();
                Rater r = RaterDatabase.getRater(raterID);

                if (r.hasRating(movieID)) {
                    numRatings++;
                    totalWeight += weight * r.getRating(movieID);
                }
            }
            
            if (numRatings >= minimalRaters) {
                weightedRatings.add(new Rating(movieID, totalWeight / numRatings));
            }
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> weightedRatings = new ArrayList<Rating>();
        ArrayList<Rating> similarities = getSimilarities(id);

        for (String movieID : MovieDatabase.filterBy(filterCriteria)) {
            int numRatings = 0;
            double totalWeight = 0.0;

            for (int i = 0; i < numSimilarRaters; i++) {
                Rating simRating = similarities.get(i);
                String raterID = simRating.getItem();
                double weight = simRating.getValue();
                Rater r = RaterDatabase.getRater(raterID);

                if (r.hasRating(movieID)) {
                    numRatings++;
                    totalWeight += weight * r.getRating(movieID);
                }
            }
            
            if (numRatings >= minimalRaters) {
                weightedRatings.add(new Rating(movieID, totalWeight / numRatings));
            }
        }
        
        Collections.sort(weightedRatings, Collections.reverseOrder());
        return weightedRatings;
    }
}
