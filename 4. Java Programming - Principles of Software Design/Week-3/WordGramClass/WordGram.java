import java.util.Arrays;

public class WordGram {
    private String[] myWords;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt " + index);
        }
        return myWords[index];
    }
    
    public int length(){
        // return the length of the WordGram
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k = 0; k < myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        // return true if this WordGram is the same as other
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;
    }

    public WordGram shiftAdd(String word) { 
        WordGram out = new WordGram(myWords, 0, myWords.length);
        // shift all words one towards 0 and add word at the end. 
        // you'll lose the first word
        for (int i = 0; i < out.length() - 1; i++) {
            out.myWords[i] = out.myWords[i + 1]; // shift all words one towards 0
        }

        out.myWords[out.length() - 1] = word; // add word to the end
        return out;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}