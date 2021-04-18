package Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;


public class MapWindow extends JFrame {
    private static Image mapImage;
    private static Image rogueImage;
    private static MapWindow map;
    private int rogX = 250;
    private int rogY = 700;
    private int[] guardX = new int[10];
    private int[] guardY = new int[10];
    private int guardCounter;
    private int[] cords = new int[2];
    private boolean verticalFirst = true;
    Random rand = new Random();
    private boolean tempBool = true;
    private boolean stop = false;


    public MapWindow(){
        setResizable(false);
        setSize(1000,766); //specific size does matter because we are
        // referencing these dimensions when determine player position
        imageLoader();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = getSize();
        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
        if(screenSize.width < 1400)
            setLocation(20,0);
        else if (screenSize.width < 1600)
            setLocation(100,25);
        else setLocation(windowX, windowY);
        setUndecorated(true); //removes options to resize/exit (cleaner look)
        setVisible(true);


    }

    public void setRoguePos(String position, String oldPosition) throws InterruptedException {

            updateCords(oldPosition, "Player", 99);
            updateCords(position, "Player", 99);
//            System.out.println("Cord X: " + cords[0]);
//            System.out.println("Rog X: " + rogX);
//            System.out.println("Cord Y: " + cords[1]);
//            System.out.println("Cord Y: " + rogY);
            step();
    }
    public void setGuardPos(int x, int y, int guardID){
        guardX[guardID] = x;
        guardY[guardID] = y;
    }
    private void step() throws InterruptedException {
        if (!tempBool) {
            if (rogX != cords[0] || rogY != cords[1]) {
                if (rogX > cords[0]) {
                    rogX--;
                }
                if (rogX < cords[0]) {
                    rogX++;
                } else {
                    if (rogY > cords[1]) {
                        rogY--;

                    }
                    if (rogY < cords[1]) {
                        rogY++;
                    }
                }
                repaint();
                Thread.sleep(30);
                step();
            }
        }
        if (tempBool) {
            if (rogX != cords[0] || rogY != cords[1]) {
                if (rogY > cords[1]) {
                    rogY--;
                    repaint();
                }
                if (rogY < cords[1]) {
                    rogY++;
                    repaint();
                } else {
                    if (rogX > cords[0]) {
                        rogX--;
                        repaint();
                    }
                    if (rogX < cords[0]) {
                        rogX++;
                        repaint();
                    }

                }
            }
        }

    }
//    public boolean getStop(){
//        return stop;
//    }
//
//    public void setStop(){
//        stop = false;
//    }



    public void display(Graphics g){

        int x = (getWidth() - mapImage.getWidth(this)) / 2;
        int y = (getHeight() - mapImage.getHeight(this)) / 2;
        g.drawImage(mapImage, x, y, this);
        g.drawImage(rogueImage, rogX , rogY, this);
        for(int i = 0; i < 8; i++){
            g.drawImage(rogueImage, guardX[i] + rand.nextInt(20)-10, guardY[i] + rand.nextInt(20)-10, this);
        }


    }
    public void imageLoader() {
        try {
            String testPath = "Assets/map_1.png";
            mapImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(testPath)));
            String testPath1 = "Assets/rogue.png";
            rogueImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(testPath1)));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void paint(Graphics g){
        BufferedImage bf = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);

        try{
            display(bf.getGraphics());
            g.drawImage(bf,0,0,null);
        }catch(Exception ignored) {

        }



    }

//    public void setVerticalFirst(boolean bool){
//        tempBool = bool;
//    }


    public void updateCords(String room, String entityType, int id){//Finds pixel coordinates based on room position and repaints image

        switch(room){ //Here based on the room it will assign x and y values
            case "frontDoor":
                cords[0]= 250;
                cords[1]= 700;
                break;

            case "Hallway1South":
                cords[0]= 250;
                cords[1]= 670;
                break;

            case "Hallway1East":
                cords[0]= 395;
                cords[1]= 525;
                break;

            case "Hallway1West":
                cords[0]= 94;
                cords[1]= 525;
                break;

            case "Hallway1North":
                cords[0]= 248;
                cords[1]= 375;
                break;

            case "Hallway1Center":
                cords[0]= 250;
                cords[1]= 527;
                break;

            case "SittingRoomSouth":
                cords[0]= 45;
                cords[1]= 597;
                break;

            case "SittingRoomNorth":
                cords[0]= 42;
                cords[1]= 477;
                break;

            case "Lounge":
                cords[0]= 464;
                cords[1]= 522;
                break;

            case "FeastHallBlue":
                cords[0]= 158;
                cords[1]= 645;
                break;

            case "FeastHallRed":
                cords[0]= 246;
                cords[1]= 429;
                break;

            case "Door1":
                cords[0]= 250;
                cords[1]= 359;
                break;

            case "Hallway2South":
                cords[0]= 250;
                cords[1]= 275;
                break;

            case "Hallway2East":
                cords[0]= 381;
                cords[1]= 210;
                break;

            case "Hallway2CenterSouth":
                cords[0]= 250;
                cords[1]= 149;
                break;

            case "Hallway2CenterNorth":
                cords[0]= 250;
                cords[1]= 88;
                break;

            case "Hallway2West":
                cords[0]= 125;
                cords[1]= 182;
                break;

            case "Kitchen":
                cords[0]= 54;
                cords[1]= 98;
                break;

            case "StorageRoomEast":
                cords[0]= 332;
                cords[1]= 42;
                break;

            case "StorageRoomWest":
                cords[0]= 191;
                cords[1]= 47;
                break;

            case "PrayerRoomEast":
                cords[0]= 312;
                cords[1]= 207;
                break;

            case "PrayerRoomWest":
                cords[0]= 173;
                cords[1]= 209;
                break;

            case "Door2":
                cords[0]= 407;
                cords[1]= 123;
                break;

            case "Hallway3Entry":
                cords[0]= 572;
                cords[1]= 129;
                break;

            case "Hallway3North":
                cords[0]= 726;
                cords[1]= 126;
                break;

            case "Hallway3West":
                cords[0]= 629;
                cords[1]= 230;
                break;

            case "Hallway3East":
                cords[0]= 825;
                cords[1]= 209;
                break;

            case "Chapel":
                cords[0]= 725;
                cords[1]= 471;
                break;

            case "GuardRoom":
                cords[0]= 714;
                cords[1]= 250;
                break;

            case "ServantsQuartersEast":
                cords[0]= 821;
                cords[1]= 80;
                break;

            case "ServantsQuartersWest":
                cords[0]= 578;
                cords[1]= 80;
                break;

            case "SouthBedroom":
                cords[0]= 920;
                cords[1]= 314;
                break;

            case "NorthBedRoom":
                cords[0]= 891;
                cords[1]= 134;
                break;

            case "Door3":
                cords[0]= 930;
                cords[1]= 83;
                break;

            case "TreasureRoom":
                cords[0]= 930;
                cords[1]= 47;
                break;


            default:
                cords[0]= 722;
                cords[1]= 578;
                break;
        }
        if(entityType.equals("Guard")){//Here if the object is a guard it will offset the cords
            //cords[0] -= 8;
            cords[1] -= 8;
            setGuardPos(cords[0], cords[1], id);
        }

        //paintEntity(cords, id);
        //map.displayEnitity(cords, id);
        //MapWindow.setRoguePos();
        //MapWindow.displayEnitity();
    }
















}