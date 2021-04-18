package LockMiniGame;

import StoryBoard.StoryBoard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LockGame extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private static Image lockPickImage;
    private static Image lockHoleImage;
    private static Image lockPadImage;
    private static Image lockBackgroundImage;
    private double currentAngle = 100;
    private double holeAngle = 0;
    private int curPickGuess = 50;
    private boolean spaceHeld = false;
    private int success = 0;
    private boolean lockPicked = false;
    private int target;
    //testint target


    Random rand = new Random();

    private final int[] x = new int[4];
    private final int[] y = new int[4];


    public LockGame(int difficulty) {
        Lock curLock = new Lock(3);
        //Setup a new lockTarget
        curLock.setLockTarget();
        this.target = curLock.getLockTargetLower()+3;


        MediaTracker mt = new MediaTracker(this);
        mt.addImage(lockPickImage, 0);
        try {
            mt.waitForID(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        //New Window
        setTitle("");
        setSize(350, 350);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null); //centers window
        setUndecorated(true); //removes options to resize/exit (cleaner look)
//        getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        setBackground(new Color(0.44f,0.36f,0.28f,0.0f));
//        getRootPane().setOpaque(false);
        imageLoader();
        setVisible(true);





    }

    public void imageLoader() {
        try {
            String testPath = "Assets/lockpick.png";
            lockPickImage = ImageIO.read(getClass().getResourceAsStream(testPath));
            String testPath2 = "Assets/lockhole.png";
            lockHoleImage = ImageIO.read(getClass().getResourceAsStream(testPath2));
            String testPath3 = "Assets/lockbackground2.png";
            lockBackgroundImage = ImageIO.read(getClass().getResourceAsStream(testPath3));
            String testPath4 = "Assets/lockpad.png";
            lockPadImage = ImageIO.read(getClass().getResourceAsStream(testPath4));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        addKeyListener(this);
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){

        BufferedImage bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TRANSLUCENT);

        try{
            animation(bf.getGraphics());
            g.drawImage(bf,0,0,null);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void animation(Graphics g) {
        Graphics2D[] graphics2DS = new Graphics2D[2];
        x[1] = (getWidth() - lockHoleImage.getWidth(this))/2;
        y[1] = (getHeight() - lockHoleImage.getHeight(this))/2;
        y[0] = (getHeight() - lockPickImage.getHeight(this))/2;
        x[2] = (getWidth() - lockBackgroundImage.getWidth(this))/2;
        y[2] = (getHeight() - lockBackgroundImage.getHeight(this))/2;
        x[3] = (getWidth() - lockPadImage.getWidth(this))/2;
        y[3] = (getHeight() - lockPadImage.getHeight(this))/2;
        int xRot = this.getWidth()/2;
        int yRot = this.getHeight()/2;
        graphics2DS[0] = (Graphics2D)g;
        AffineTransform pickXForm;

        if(!spaceHeld){

            super.paint(g);
            AffineTransform porigXform = graphics2DS[0].getTransform();
            g.drawImage(lockBackgroundImage,x[2],y[2], this);
            g.drawImage(lockPadImage,x[3]-25,y[3], this);
            g.drawImage(lockHoleImage,x[1],y[1]+5, this);
            AffineTransform pnewXform = (AffineTransform)(porigXform.clone());
            //center of rotation is center of the panel
            pnewXform.rotate(Math.toRadians(currentAngle), xRot, yRot);
            graphics2DS[0].setTransform(pnewXform);
            //draw image centered in panel
            graphics2DS[0].drawImage(lockPickImage, -10, y[0], this);


        }
        if(spaceHeld){

            graphics2DS[1] = (Graphics2D)g;
            super.paint(g);
            graphics2DS[0].drawImage(lockBackgroundImage,x[2],y[2], this);
            graphics2DS[0].drawImage(lockPadImage,x[3]-25,y[3], this);
            AffineTransform origXform = graphics2DS[1].getTransform();
            AffineTransform newXform = (AffineTransform)(origXform.clone());
            pickXForm = graphics2DS[0].getTransform();
            pickXForm.rotate(Math.toRadians(currentAngle),xRot, yRot);
            graphics2DS[0].setTransform(pickXForm);
            graphics2DS[0].drawImage(lockPickImage, rand.nextInt(3) - 18, rand.nextInt(3) + y[0], this);
            //center of rotation is center of the panel
            newXform.rotate(Math.toRadians(holeAngle), xRot, yRot);
            graphics2DS[1].setTransform(newXform);
            //draw image centered in panel
            graphics2DS[1].drawImage(lockHoleImage, x[1], y[1]+5, this);
            graphics2DS[1].setTransform(origXform);

        }

    }

//    public static void main(String[] args) {
//        LockGame game = new LockGame();
//
//    }

    public void keyPressed(KeyEvent ke) {

        switch (ke.getKeyCode()) {
            case KeyEvent.VK_RIGHT : {
                if (currentAngle < 160) {
                    currentAngle += 2.0;
                }
                repaint();

                if (curPickGuess < 80) {
                    curPickGuess++;
                }
                break;
            }

            case KeyEvent.VK_LEFT : {
                if (currentAngle > 20) {
                    currentAngle -= 2.0;
                }
                repaint();

                if (curPickGuess > 10) {
                    curPickGuess--;
                }
                break;
            }
            case KeyEvent.VK_SPACE : { //need to change locks to either 1 difficulty or code in the different difficulties
                //within this code (success = 55 so easy would be 55 - 10, med 55 - 6, hard 55 - 3,)
                //the lock target should also be called instead of relying on lower bound
                if ((success < 50) && (holeAngle < 110 - Math.abs(2 * (curPickGuess - (target + 3))))) {
                    spaceHeld = true;
                    holeAngle += 2.0;


                    if (curPickGuess >= target -3
                            && curPickGuess <= target + 3) {
                        System.out.println(success);
                        success++;
                    }
                    if (success == 49) {
                        try {
                            java.applet.AudioClip clip =
                                    java.applet.Applet.newAudioClip(
                                            new java.net.URL("file:Rogue-Like-Like/src/LockMiniGame/Assets/unlock.wav"));
                            clip.play();
                        } catch (java.net.MalformedURLException murle) {
                            System.out.println(murle);
                        }
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lockPicked = true;
                        this.dispose();



                    }
                    repaint();

                }
                break;
            }
        }

    }
    public boolean getLockPicked(){

        return lockPicked;
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_SPACE: {

                spaceHeld = false;
                holeAngle = 0;
                success = 0;


                repaint();
                try {
                    java.applet.AudioClip clip =
                            java.applet.Applet.newAudioClip(
                                    new java.net.URL("file:Rogue-Like-Like/src/LockMiniGame/Assets/rough.wav"));
                    clip.play();
                } catch (java.net.MalformedURLException murle) {
                    System.out.println(murle);
                }
                break;
            }


        }
    }
}

