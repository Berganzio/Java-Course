import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMoviesSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public int getId(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return Integer.parseInt(m.getID());
            }
        }
        System.out.println("No such title found");
        return -1;
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
        for (Movie m : myMovies) {
            String id = m.getID();
            double avg = getAverageById(id, minimalRaters);
            if (avg > 0.0) {
                avgRatings.add(new Rating(id, avg));
            }
        }
        return avgRatings;
    }

    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "ID was not found";
    }
}
