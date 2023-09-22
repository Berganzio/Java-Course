import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String id = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genre = record.get("genre");
            String director = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie movie = new Movie(id, title, year, genre, director, country, poster, minutes);
            movies.add(movie);
        }
        return movies;
    }

    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of movies: " + movies.size());
        /* for (Movie movie : movies) {
            System.out.println(movie);
        } */
        int comedyCount = 0;
        int longCount = 0;
        for (Movie movie : movies) {
            if (movie.getGenres().contains("Comedy")) {
                comedyCount++;
            }
            if (movie.getMinutes() > 150) {
                longCount++;
            }
        }
        System.out.println("Number of comedy movies: " + comedyCount);
        System.out.println("Number of long movies: " + longCount);

        HashMap<String, Integer> directorCount = new HashMap<String, Integer>();
        for (Movie movie : movies) {
            String[] directors = movie.getDirector().split(",");
            for (String director : directors) {
                director = director.trim();
                if (directorCount.containsKey(director)) {
                    // if director already exists in the map, increment the count
                    directorCount.put(director, directorCount.get(director) + 1);
                } else {
                    directorCount.put(director, 1);
                }
            }
        }
        int maxCount = 0;
        for (String director : directorCount.keySet()) {
            if (directorCount.get(director) > maxCount) {
                maxCount = directorCount.get(director);
            }
        }
        System.out.println("Maximum number of movies by any director: " + maxCount);
        ArrayList<String> maxDirectors = new ArrayList<String>();
        for (String director : directorCount.keySet()) {
            if (directorCount.get(director) == maxCount) {
                maxDirectors.add(director);
            }
        }
        System.out.println("Directors with maximum number of movies: " + maxDirectors);
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            String raterId = record.get("rater_id");
            String movieId = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));
            boolean raterExists = false;
            for (Rater rater : raters) {
                if (rater.getID().equals(raterId)) {
                    raterExists = true;
                    rater.addRating(movieId, rating);
                }
            }
            if (!raterExists) {
                Rater rater = new EfficientRater(raterId);
                rater.addRating(movieId, rating);
                raters.add(rater);
            }
        }
        return raters;
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Number of raters: " + raters.size());
        /* for (Rater rater : raters) {
            System.out.println(rater.getID() + " " + rater.numRatings());
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String item : itemsRated) {
                System.out.println(item + " " + rater.getRating(item));
            }
        } */

        // find the maximum number of ratings by any rater
        int maxRatings = 0;
        String raterName = "";
        for (Rater rater : raters) {
            if (rater.numRatings() > maxRatings) {
                maxRatings = rater.numRatings();
                raterName = rater.getID();
            }
        }
        System.out.println("Maximum number of ratings by any rater: " + maxRatings + " by the rater #" + raterName);

        String raterId = "193";
        for (Rater rater : raters) {
            if (rater.getID().equals(raterId)) {
                maxRatings = rater.numRatings();
            }
        }
        System.out.println("Maximum number of ratings by rater #" + raterId + ": " + maxRatings);

        int numRatersWithMaxRatings = 0;
        for (Rater rater : raters) {
            if (rater.numRatings() == maxRatings) {
                numRatersWithMaxRatings++;
            }
        }
        System.out.println("Number of raters with maximum number of ratings: " + numRatersWithMaxRatings);

        String movieId = "1798709";
        int numRatingsForMovie = 0;
        for (Rater rater : raters) {
            if (rater.hasRating(movieId)) {
                numRatingsForMovie++;
            }
        }
        System.out.println("Number of ratings for movie " + movieId + ": " + numRatingsForMovie);

        ArrayList<String> uniqueMovies = new ArrayList<String>();
        for (Rater rater : raters) {
            ArrayList<String> itemsRated = rater.getItemsRated();
            for (String item : itemsRated) {
                if (!uniqueMovies.contains(item)) {
                    uniqueMovies.add(item);
                }
            }
        }
        System.out.println("Number of unique movies rated: " + uniqueMovies.size());
    }
}
