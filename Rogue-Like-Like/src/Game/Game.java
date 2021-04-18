package Game;


import Guard.Guard;
import LockMiniGame.LockGame;
import Map.MapWindow;
import Player.Player;
import StoryBoard.StoryBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Game {

    private final Container sbCon;
    private final JPanel pnlTitle;
    private final JPanel pnlStart;
    private JLabel lpLabelNumber;
    private final JButton[] choiceArray = new JButton[4];
    private JTextArea txtArea;
    private final Font fontReg = new Font("Times New Roman", Font.PLAIN, 20);
    private String[] choiceStrings;
    private int presses = 1;
    private int curLock = 0;

    Guard[] guards = new Guard[8];
    StoryBoard sb = new StoryBoard();
    MainMenu mainMenu = new MainMenu();
    Action action = new Action();
//    Animate animate = new Animate("Hallway1South");
    LockGame[] lockGames = new LockGame[3]; //creates all the locks array[size] is number of locks in game
    MapWindow map = new MapWindow();
    Player rogue = new Player("Test Name", "frontDoor");


    //Constructor
    public Game(){
        JFrame sbFrame = new JFrame();
        sbFrame.setSize(300, 766);
        sbFrame.setUndecorated(true);
        sbFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sbFrame.getContentPane().setBackground(Color.black);
        sbFrame.setLayout(null);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = sbFrame.getSize();
        int windowX = Math.max(0, (screenSize.width  - windowSize.width ) / 2);
        int windowY = Math.max(0, (screenSize.height - windowSize.height) / 2);
        sbFrame.setLocation((int)(windowX+(windowX*.90)), windowY);
        this.sbCon = sbFrame.getContentPane();

        pnlTitle = new JPanel();
        pnlTitle.setBounds(0, 75 , 300, 150);
        pnlTitle.setBackground(Color.black);
        JLabel lblTitle = new JLabel("Rogue-Like-Like");
        lblTitle.setForeground(Color.white);
        Font fontTitle = new Font("Times New Roman", Font.BOLD, 36);
        lblTitle.setFont(fontTitle);

        pnlStart = new JPanel();
        pnlStart.setBounds(100, 400, 100, 120);
        pnlStart.setBackground(Color.black);

        JButton btnStart = new JButton("BEGIN");
        btnStart.setBackground(Color.black);
        btnStart.setForeground(Color.white);
        btnStart.setFont(fontReg);
        btnStart.addActionListener(mainMenu);
        btnStart.setFocusPainted(false);

        pnlTitle.add(lblTitle);
        pnlStart.add(btnStart);

        sbCon.add(pnlTitle);
        sbCon.add(pnlStart);

        sbFrame.setVisible(true);

        for(int i = 0; i < lockGames.length; i++) {
            lockGames[i] = new LockGame(i+1);
            lockGames[i].setVisible(false);
        }
        for(int i = 0; i < guards.length; i++){
            //Here are the patrol Paths
            String[][] patrolPaths = {
                    {"Hallway1North", "Hallway1West", "Hallway1South", "Hallway1East", "Hallway1Center",
                            "Hallway1West", "Hallway1South", "Hallway1East"},

                    {"SittingRoomNorth", "Hallway1West", "Hallway1Center", "Hallway1East", "Hallway1Lounge",
                            "Hallway1East", "Hallway1South", "Hallway1West", "SittingRoomSouth"},

                    {"Hallway2CenterNorth", "Hallway2West", "Hallway2West", "Kitchen", "Hallway2West",
                            "Hallway2CenterNorth", "Hallway2East"},

                    {"Hallway2South", "Hallway2CenterSouth", "Hallway2East", "Hallway2East", "Hallway2South",
                            "Hallway2West", "Hallway2CenterSouth", "Hallway2South"},

                    {"GuardRoom", "GuardRoom", "Hallway3West", "Hallway3North", "Hallway3Entry", "Hallway3North",
                            "Hallway3East", "Hallway3North", "Hallway3West", "GuardRoom"},

                    {"GuardRoom", "GuardRoom", "GuardRoom", "Hallway3West", "Hallway3North", "ServantsQuartersEast",
                            "ServantsQuartersEast", "ServantsQuartersEast", "Hallway3North", "Hallway3West"},

                    {"GuardRoom", "GuardRoom", "GuardRoom", "GuardRoom", "Hallway3West", "Chapel", "Hallway3East",
                            "Chapel", "Hallway3West"},

                    {"GuardRoom", "GuardRoom", "GuardRoom", "GuardRoom", "GuardRoom", "Hallway3West",
                            "Hallway3North", "Hallway3East", "Chapel", "HallwayWest"}
            };
            guards[i] = new Guard(patrolPaths[i]);
        }

    }
    //Setup the main view and player init
    public void createMainView(){
        pnlTitle.setVisible(false);
        pnlStart.setVisible(false);

        JPanel pnlText = new JPanel();
        pnlText.setBounds(25, 100, 280, 300);
        pnlText.setBackground(Color.black);
        sbCon.add(pnlText);
        txtArea = new JTextArea("");
        txtArea.setBounds(0, 0, 250, 300);
        txtArea.setBackground(Color.black);
        txtArea.setForeground(Color.white);
        txtArea.setFont(fontReg);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setEditable(false);

        pnlText.add(txtArea);

        JPanel pnlChoices = new JPanel();
        pnlChoices.setBounds(20, 450, 250, 200);
        pnlChoices.setBackground(Color.black);
        pnlChoices.setLayout(new GridLayout(4,1));
        sbCon.add(pnlChoices);

        for(int i = 0; i < choiceArray.length; i++){
            choiceArray[i] = new JButton("Choice " + (i+1));
            choiceArray[i].setBackground(Color.black);
            choiceArray[i].setForeground(Color.white);
            choiceArray[i].setFont(fontReg);
            choiceArray[i].setFocusPainted(false);
            choiceArray[i].addActionListener(action);
//            choiceArray[i].addActionListener(animate);
            choiceArray[i].setActionCommand("btn"+(i+1));
            choiceArray[i].setContentAreaFilled(false); //disables highlight
            pnlChoices.add(choiceArray[i]);
        }

        JPanel pnlPlayer = new JPanel();
        pnlPlayer.setBounds(15, 5, 250, 50);
        pnlPlayer.setBackground(Color.black);
        pnlPlayer.setLayout(new GridLayout(1,2));
        sbCon.add(pnlPlayer);
        JLabel lpLabel = new JLabel("Lock Picks:");
        lpLabel.setFont(fontReg);
        lpLabel.setForeground(Color.white);
        pnlPlayer.add(lpLabel);
        lpLabelNumber = new JLabel();
        lpLabelNumber.setFont(fontReg);
        lpLabelNumber.setForeground(Color.white);
        pnlPlayer.add(lpLabelNumber);
        setup();
        updateScreen();

    }
    public void setup(){
        int lockPickCount = 1;
        sb.frontDoor();
        lpLabelNumber.setText(""+ lockPickCount);
        updateText();

    }
    //Updates to screen after each turn

    public void updateText(){
        choiceStrings = sb.getChoices();
        for(int i = 0; i < choiceArray.length; i++) {
            choiceArray[i].setText(choiceStrings[i]);
        }
    }
    //todo:
    public void updateScreen(){
        map.updateCords(rogue.getCurrentPosition(), "Player", 99);
        for(int i = 0; i < guards.length; i++){
            guards[i].advancePatrolStep();
            map.updateCords(guards[i].getCurrentPosition(),"Guard", i);
            map.repaint();
        }
        System.out.println();


    }

    public static void main(String[] args) {
        new Game();
    }
    public class MainMenu implements ActionListener{

        public void actionPerformed(ActionEvent event){

            createMainView();
        }
    }
    public class Action implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            String choiceMade = event.getActionCommand();
//            timer.addActionListener(animate);

            switch (sb.getCurPos()) {
                case "frontDoor":
                    switch (choiceMade) {
                        case "btn1":
//                            map.setStop();
//                            animate.rogPos = "Hallway1South";
//                            map.setVerticalFirst(true);
//                                new Timer(10, new ActionListener(){
//                                    public void actionPerformed(ActionEvent e){
//                                        map.setRoguePos("Hallway1South", rogue.getCurrentPosition());
//                                        rogue.setPlayerPosition("Hallway1South");
//                                    }
//                                }).start();
//                            ActionListener listener = new ActionListener(){
//                                public void actionPerformed(ActionEvent e){
//                                        System.out.println("Timer fired!");
//                                        map.setRoguePos("Hallway1South", rogue.getCurrentPosition());
//                                        rogue.setPlayerPosition("Hallway1South");
//                                        System.out.println(map.getStop());
//                                        if(map.getStop() == true){
//                                            timer.stop();
//                                        }
//
//                                }
//                            };
//
//                            timer.addActionListener(listener);
//                            timer.start();
                            break;
                        case "btn2":
                            sb.beforeLock();
                            break;
                        case "btn3":
//                            map.setStop();
//                            animate.rogPos = "Hallway1East";
                            break;
                    }
                    break;
                case "beforeLock":
                    if ("btn1".equals(choiceMade)) {
                        sb.curLock();
                        lockGames[curLock].setVisible(true);
                    }
                    break;
                case "curLock":
                    switch (choiceMade) {
                        case "btn1":
                            break;
                        case "btn2":
                            break;
                        case "btn3":
                        case "btn4":
                            if (lockGames[curLock].getLockPicked()) {
                                switch (curLock) {
                                    case 0:
//                                        afterLock1();
                                        break;
                                    case 1:
//                                        afterLock2();
                                        break;
                                    case 2:
//                                        winGame();
                                        break;
                                }
                                curLock++;
                            } else { //anti cheat maybe have guards adv their turn if pressed too much
                                presses++;
                                sb.curLock();

                            }
                            break;

                    }
                    break;
            }
//            timer.start();
            updateText();
            updateScreen();

        }
    }
//    public class Animate implements ActionListener {
//        private String rogPos;
//        public Animate(String rogPos){
//            this.rogPos = rogPos;
//
//
//        }
//
//        public void actionPerformed(ActionEvent e) {
//        if(map.getStop() == true){
//            return;
//        }
//            System.out.println(map.getStop());
//            System.out.println("Timer fired!");
//            System.out.println(rogPos);
//            System.out.println(rogue.getCurrentPosition());
//            map.setRoguePos(this.rogPos, rogue.getCurrentPosition());
//            rogue.setPlayerPosition(rogPos);
//            System.out.println(map.getStop());
//
//         if (map.getStop() == true) {
//                timer.restart();
//                timer.stop();
//
//            }
//
//        }
//    }


}

