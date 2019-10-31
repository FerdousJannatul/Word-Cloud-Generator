// Each word is a pair: the word, and its frequency

public class Word implements Comparable<Word> {
  protected String word;
  protected int freq;

  public Word(String newWord) { // Constructor
    word = newWord;
    freq = 1;
  }

  public String getWord() {
    return word;
  } // getWord()

  public int getFreq() {
    return freq;
  } // getFreq()

  public void incr() {
    freq++;
  } // incr()

  // print representation of Word objects
  public String toString() {
    return "<"+word+", "+freq+">";
  }

  public int compareTo(Word w) {
    return freq - w.freq;
  }
} // Class Word
