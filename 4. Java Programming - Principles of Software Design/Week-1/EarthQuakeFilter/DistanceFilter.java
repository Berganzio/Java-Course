public class DistanceFilter implements Filter {
    private Location location;
    private double maxDistance;

    public DistanceFilter(Location loc, double max) {
        location = loc;
        maxDistance = max;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maxDistance;
    }

    public String getName() {
        return "Distance";
    }
}
