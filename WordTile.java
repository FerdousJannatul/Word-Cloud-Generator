// A graphical tile containing a word and additional attributes
import processing.core.PApplet;
import processing.core.PVector;

public class WordTile extends Word {
  private PApplet p;
  private PVector loc;   // The bottom left corner of the tile (x, y)
  private float tileW, tileH;   // width and height of tile
  private int tileColor;   // fil color of word
  private float tileFS = 24;   // the font size of tile, default is 24

  public WordTile (PApplet p, String newWord) { // Constructor
    super(newWord);
    this.p = p;
    loc = new PVector(0, 0);
    tileColor = p.color(0);
    setSize();
  } 

  public void setLocation(float x, float y) {
    loc.x = x;
    loc.y = y;
  }

  public void setFontSize() {
    tileFS = p.map(freq, 1, 35, 10, 120);
    setSize();
  }

  private void setSize() {
    p.textSize(tileFS);
    tileW = p.textWidth(word);
    tileH = p.textAscent();
  }
  
  public void setTileColor(int c){
    tileColor = c;  
  }
  
  public PVector getLoc(){
    return loc;
  }
  
  public float getTileW(){
    return tileW;
  }
  
  public float getTileH(){
    return tileH;
  }

  public void display() {
    p.fill(tileColor);
    p.textSize(tileFS);
    p.text(word, loc.x, loc.y);
  }

  public boolean intersect(WordTile t2) {
    float left1 = loc.x;   // the first tile bounding box
    float right1 = loc.x + tileW;
    float top1 = loc.y - tileH;
    float bot1 = loc.y;

    float left2 = t2.loc.x;     // the second tile bounding box
    float right2 = left2 + t2.tileW;
    float top2 = t2.loc.y - t2.tileH;
    float bot2 = t2.loc.y;

    // testing intersection
    return !(left1 > right2 || left2 > right1 || top1 > bot2 || top2 > bot1);
  } // intersect()
} // Class WordTile
