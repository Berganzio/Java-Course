public class PhraseFilter implements Filter {
    private String where;
    private String phrase;
    
    public PhraseFilter(String w, String p) {
        where = w;
        phrase = p;
    }

    public boolean satisfies(QuakeEntry qe) {
        return where.equals("start") && qe.getInfo().startsWith(phrase) ||
               where.equals("end") && qe.getInfo().endsWith(phrase) ||
               where.equals("any") && qe.getInfo().contains(phrase);
    }

    public String getName() {
        return "Phrase";
    }
}
