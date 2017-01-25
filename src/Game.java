
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Tmister911
 */
public class Game extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;

    //The Font
    Font letterFont = new Font("Arial", Font.BOLD, 42);

    //Array of falling 
    Rectangle[] boxesW = new Rectangle[4];
    Rectangle[] boxesA = new Rectangle[4];
    Rectangle[] boxesS = new Rectangle[4];
    Rectangle[] boxesD = new Rectangle[4];

    //The WIDTH/HEIGHT of the box
    int boxWidth = 50;
    //The Space between TWO Different boxes 
    int boxSpace = 25;
    //The Score counting variable
    int score = 0;

    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)
    @Override
    public void paintComponent(Graphics g) {
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAwME DRAWING GOES HERE
        //Background
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //The line to check detection
        g.setColor(Color.green);
        g.fillRect(0, 550, WIDTH, 30);

        //Draw Score         
        //The Font for Letters 
        g.setFont(letterFont);
        g.setColor(Color.BLACK);
        g.drawString("" + score, WIDTH / 2, 100);
        //Get a random number to start the boxes at
        int randomNum = ThreadLocalRandom.current().nextInt(625, 925);
        //Set initial random values for Boxes to be drawn at  
        for (int i = 0; i < 4; i++) {
            boxesW[i] = new Rectangle(100, randomNum, boxWidth, boxWidth);
            boxesA[i] = new Rectangle(100, randomNum, boxWidth, boxWidth);
            boxesS[i] = new Rectangle(200, randomNum, boxWidth, boxWidth);
            boxesD[i] = new Rectangle(300, randomNum, boxWidth, boxWidth);
        }
        //Draw Boxes that falling
        for (int i = 0; i < boxesW.length; i++) {
            g.drawRect(boxesW[i].x, boxesW[i].y , boxesW[i].height, boxesW[i].width);
            g.drawRect(boxesA[i].x, boxesA[i].y , boxesA[i].height, boxesA[i].width);
            g.drawRect(boxesS[i].x, boxesS[i].y , boxesS[i].height, boxesS[i].width);
            g.drawRect(boxesD[i].x, boxesD[i].y , boxesD[i].height, boxesD[i].width);
            // GAME DRAWING ENDS HERE
        }
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime = 0;
        long deltaTime;
        int falling = 5;
        // boolean for destroying 
        boolean broke = false;
        // the main game loop section
        // game will end if you set done = false;
        boolean done = false;

        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            for (int i = 0; i < 10; i++) {
                boxesW[i].y = boxesW[i].y - falling;
                boxesA[i].y = boxesA[i].y - falling;
                boxesS[i].y = boxesS[i].y - falling;
                boxesD[i].y = boxesD[i].y - falling;
           
                if (boxesW[i].y < -25 || boxesA[i].y < -25 ||boxesS[i].y < -25 || boxesD[i].y < -25){    
                    //redraw the past boxes
                }
     }
// GAME LOGIC ENDS HERE 
        // update the drawing (calls paintComponent)
        repaint();

        // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
        // USING SOME SIMPLE MATH
        deltaTime = System.currentTimeMillis() - startTime;
        try {
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
                Thread.sleep(1);
            } else {
                // sleep to make up the extra time
                Thread.sleep(desiredTime - deltaTime);
            }
        } catch (Exception e) {
        };
    }
    }


/*
 * 
 *
 *
 * @param args the command line arguments
 */
public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");
        // creates an instance of my game
        Game game = new Game();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);
        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        //Key Listener
        frame.addKeyListener(game);
        // starts my game loop
        game.run();

    }

    @Override
        public void keyPressed(KeyEvent ke) {
        boolean broke = false;
        int key = ke.getKeyCode();
        int max = 900;
        int min = 625;
        while (broke = false) {
            for (int i = 0; i < 10; i++) {
                int randNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                //W key recognition and action 
                if (key == KeyEvent.VK_W && boxesW[i].y < 75) {
                    boxesW[i].y = randNum;
                } else if (key == KeyEvent.VK_W && boxesW[i].y > 75) {
                    broke = true;
                }
                //A recognition
                if (key == KeyEvent.VK_A && boxesA[i].y < 75) {
                    boxesA[i].y = randNum;
                } else if (key == KeyEvent.VK_A && boxesA[i].y > 75) {
                    broke = true;
                }
                //S key recognition
                if (key == KeyEvent.VK_S && boxesS[i].y < 75) {
                    boxesS[i].y = randNum;
                } else if (key == KeyEvent.VK_S && boxesS[i].y > 75) {
                    broke = true;
                }
                //D key recognition
                if (key == KeyEvent.VK_D && boxesD[i].y < 75) {
                    boxesD[i].y = randNum;
                    
                } else if (key == KeyEvent.VK_D && boxesD[i].y > 75) {
                    broke = true;

                }

            }

        }
    }

    @Override
        public void keyTyped(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
