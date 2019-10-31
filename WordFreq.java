// The Word frequency table class
import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Collections;

public class WordFreq {
  private ArrayList<WordTile> wordFrequency;
  private PApplet p;
  private String[] stopwords;

  public WordFreq(PApplet p, String[] tokens) { // Constructor
    this.p = p;
    stopwords = p.loadStrings("stopwords.txt");
    wordFrequency = new ArrayList<WordTile>();

    // compute the word frequency table using tokens
    for (String t : tokens) {
      if (!isStopWord(t)) {
        // if t is not a stopword
        // find if t is in the list wordFrequency
        // if it's, increase the counter
        // otherwise, add to wordFrequency
        int index = search(t, wordFrequency);
        if (index >= 0) {
          wordFrequency.get(index).incr();
        } else {
          wordFrequency.add(new WordTile(p, t));
        }
      }
    } // for loop

    Collections.sort(wordFrequency, Collections.reverseOrder());
  } // Constructor

  // Is word a stopword
  private Boolean isStopWord (String word) {
    for (String stopword : stopwords) {
      if (word.equals(stopword)) {
        return true;
      }
    }
    return false;
  } // isStopWord()

  private int search(String w, ArrayList<WordTile> list) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getWord().equals(w)) {
        return i;
      }
    }
    return -1;
  }

  // Console printout
  public void tabulate(int n) {
    for (int i = 0; i<wordFrequency.size(); i++) {
      System.out.println(wordFrequency.get(i));
    }
    System.out.println("There are "+wordFrequency.size()+" entries");
  } // tabulate

  // Maximum Frequency
  public int maxFreq() {
    int max = 1;
    for (Word w : wordFrequency) {
      if (w.getFreq() > max) {
        max = w.getFreq();
      }
    }
    return max;
  }


  // arrange or map the first N tiles in sketch
  public void arrange(int n) {
    for (int i = 0; i < n; i++) {
      WordTile tile = wordFrequency.get(i);
      tile.setFontSize();

      // Explore the spiral layout
      float cx = p.width/2 - 50;
      float cy = p.height/2;
      float R = 0.0f, dR = 0.2f, theta = 0.0f, dTheta = 0.5f;
      do { // find the next x, y for the tile i in spiral
        float x = cx + R*p.cos(theta);
        float y = cy + R*p.sin(theta);
        tile.setLocation(x, y);
        theta += dTheta;
        R += dR;
      } // until the tile is clear of all previous tiles
      while (!clear(i));

      // tile.setLocation(p.random(p.width), p.random(p.height));
    }
  }

  // Is tile i clear of tiles 0, i-1
  private boolean clear(int n) {
    WordTile tile1 = wordFrequency.get(n);
    for (int i = 0; i < n; i++) {
      WordTile tile2 = wordFrequency.get(i);
      if (tile1.intersect(tile2)) {
        return false;
      }
    } // for loop
    return true;
  }  // clear()

  public void display(int n) {
    for (int i = 0; i < n; i++) {
      WordTile tile = wordFrequency.get(i);
      tile.display();
    }
  }

  // Find out which tile was clicked
  public void interact(float mx, float my) {
    // Find out the tile
    WordTile tile = searchTile(mx, my);
    // Do something on the tile
    if (tile != null) {
      tile.setTileColor(p.color(p.random(255), p.random(255), p.random(255)));
      tile.display();
    }
  }

  private WordTile searchTile(float mx, float my) {
    for (int i = 0; i < wordFrequency.size(); i++) {
      WordTile tile = wordFrequency.get(i);
      if (ptInTile(tile, mx, my)) {
        return tile;
      }
    } // for()
    return null;
  } // searchTile()

  private boolean ptInTile(WordTile t, float x, float y) {
    float x1 = t.getLoc().x;
    float y1 = t.getLoc().y - t.getTileH();
    float x2 = x1 + t.getTileW();
    float y2 = t.getLoc().y;

    return(x >= x1 && x <= x2 && y >= y1 && y <= y2);
  } // ptInTile()
}
