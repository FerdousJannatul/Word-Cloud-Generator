// Using Arraylist for word frequencies
// eliminating stop words
// using the wordFreq class

String inputTextFile = "Obama.txt";
WordFreq table;
int N = 150;
PFont fnt;

void setup() {
  String[] fileContents = loadStrings(inputTextFile);
  String rawText = join(fileContents, " ").toLowerCase();
  String[] tokens;
  String delimiters = " ,./?<>;:'\"[{]}\\|=+-_()*&^%$#@!~";
  tokens = splitTokens(rawText, delimiters);
  println(tokens.length + " tokens found in file: "+ inputTextFile);

  // Create the word Frequency table
  table = new WordFreq(this, tokens);
  println("Max frequency: "+table.maxFreq());

  // display words
  size(800, 800);
  fnt = createFont("Times New Roman", 120);
  textFont(fnt);
  textSize(24);
  table = new WordFreq(this, tokens);
  table.arrange(N);
}

void draw() {
  background(255);
  table.display(N);
  table.tabulate(N);
}

//Bonus: The start of buinding interaction
void mousePressed() {
  table.interact(mouseX, mouseY);
}
