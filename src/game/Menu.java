package game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Menu {
    // private BufferedImage image1 = null;
            SoundManager s;
            Game game;
            JFrame window;
            Container c;
            JPanel titlePanel, startBtnPanel, mainPanel, picturePanelBrain,
                    picturePanelTitle, picturePanelRight, optionBtnPanel, exitBtnPanel;
            
            JLabel titleLabel, pictureLabel, pictureLabelTitle, pictureLabelRight;
            
            // JLabel pictureLabel;
            
            // JButton
            protected JButton button1;
            
            // Creates the ImageIcon, paints Icons from images
            ImageIcon image1, imageTitle, gif1, gif2;
            
            // Icon for gif
            Icon icon;
            
         //   Font titleFont = new Font("Arial", Font.ITALIC, 80);
            Font normalFont1 = new Font("Times New Roman", Font.PLAIN, 50);
            JButton startBtn, optionBtn, exitBtn;  
           
          //  JTextArea mainText;
            
            titleScreenHandler tsHandler = new titleScreenHandler();
            OptionHandler oHandler = new OptionHandler();
            exitHandler eHandler = new exitHandler();
    
    public static void main(String[] args){
        new Menu();
    }
    
    public Menu(){
        
            // Window Initialization
            window = new JFrame();
            window.setSize(1000, 563);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.getContentPane().setBackground(Color.black);
            window.setLayout(null);
            // window.setVisible(true);
            window.setLocationRelativeTo(null);
            window.setResizable(false);
            c = window.getContentPane();
            
            
            // Title Panel Initialization
            picturePanelTitle = new JPanel();
            picturePanelTitle.setBounds(165, 50, 680, 110);
            picturePanelTitle.setBackground(Color.blue);
            
            // Brain image Panel Initialization
            picturePanelBrain = new JPanel();
            picturePanelBrain.setBounds(92, 240, 240, 175);
            picturePanelBrain.setBackground(Color.black);
            
            // Picture Label to display image
            pictureLabelTitle = new JLabel();
            pictureLabel = new JLabel();
            pictureLabelRight = new JLabel();
       
            
            // Gif Panel Initialization
            picturePanelRight = new JPanel();
            picturePanelRight.setBounds(690, 180, 240, 420);
            picturePanelRight.setBackground(Color.black);
            
            // BufferedImageLoader loader = new BufferedImageLoader();
           
            imageTitle = new ImageIcon(".//Rez//TitleImg.png");
            
           // ImageIcon titleImg = new ImageIcon(".//Rez//TitleImg.png");
            image1 = new ImageIcon(".//Rez//brain.png");

            // gif for right side
            gif1 = new ImageIcon(".//Rez//WalkGif4.gif");
            gif2 = new ImageIcon(".//Rez//bgif1.gif");
            
            // JLabel displays the image of title
          //  pictureLabelTitle.setIcon(titleImg);
            pictureLabelTitle.setIcon(imageTitle);
            picturePanelTitle.add(pictureLabelTitle);
            
            
            
            // JLabel displays the image of brain
            pictureLabel.setIcon(image1);
            picturePanelBrain.add(pictureLabel);
            
            
            
           
            // JLabel for Right picture
            pictureLabelRight.setIcon(gif2);
            picturePanelRight.add(pictureLabelRight);
                 //  Icon gif = new ImageIcon(".//Rez//right.gif");
            
            
          /*
            titlePanel = new JPanel();
            titlePanel.setBounds(165, 50, 665, 100);
            titlePanel.setBackground(Color.blue);
            titleLabel = new JLabel("Ludus Mentis");
            titleLabel.setForeground(Color.white);
            titleLabel.setFont(titleFont);
          */
           
            // Start button panel settings
            startBtnPanel = new JPanel();
            startBtnPanel.setBounds(410, 240, 200, 80);
            startBtnPanel.setBackground(Color.black);
            
            // Option button panel settings
            optionBtnPanel = new JPanel();
            optionBtnPanel.setBounds(410, 330, 200, 80);
            optionBtnPanel.setBackground(Color.black);
            
            // Exit button panel settings
            exitBtnPanel = new JPanel();
            exitBtnPanel.setBounds(410, 420, 200, 80);
            exitBtnPanel.setBackground(Color.black);
            
            // Font and text for Start button
            startBtn = new JButton("Play");
            startBtn.setBounds(410, 240, 200, 80);
            startBtn.setBackground(Color.green);
            startBtn.setForeground(Color.black);
            startBtn.setFont(normalFont1);
            
            // Font and text for Option button
            optionBtn = new JButton("Options");
            optionBtn.setBounds(410, 330, 150, 80);
            optionBtn.setBackground(Color.blue);
            optionBtn.setForeground(Color.white);
            optionBtn.setFont(normalFont1);
            
            // Font and text for Exit button
            exitBtn = new JButton("Exit");
            exitBtn.setBounds(410, 420, 150, 80);
            exitBtn.setBackground(Color.red);
            exitBtn.setForeground(Color.white);
            exitBtn.setFont(normalFont1);
            
            
            // Checks if button is being pressed
            startBtn.addActionListener(tsHandler);
            optionBtn.addActionListener(oHandler);
            exitBtn.addActionListener(eHandler);
            
            // Adding to the screen
        //  titlePanel.add(titleLabel);
            startBtnPanel.add(startBtn);
            optionBtnPanel.add(optionBtn);
            exitBtnPanel.add(exitBtn);
        //  c.add(titlePanel);
        //  c.add(titleLabel);
            c.add(startBtnPanel);
            c.add(optionBtnPanel);
            c.add(exitBtnPanel);
            c.add(picturePanelBrain);
            c.add(picturePanelTitle);
            c.add(picturePanelRight);
            window.setVisible(true);
            
                s = new SoundManager() 
                {
                    public void initSounds()
                    {
                        sounds.add(new Sound("MenuMusic", Sound.getURL("/Lightspeed.wav")));
                      //  sounds.add(new Sound("MenuButton", Sound.getURL("/MenuButton.wav")));
                        sounds.add(new Sound("BGLoop2", Sound.getURL("/BGLoop2.wav")));
                        sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
                    }
                };
                
                    s.loopSound("MenuMusic");
        
    }
    
    public void createGameScreen(){
        /*
        titlePanel.setVisible(false);
        startBtnPanel.setVisible(false);
        
        mainPanel = new JPanel();
        mainPanel.setBounds(200, 100, 600, 250);
        mainPanel.setBackground(Color.green);
        c.add(mainPanel);
        
        mainText = new JTextArea();
        mainText.setBounds(100, 100, 600, 250);
        mainText.setBackground(Color.black);
        mainText.setForeground(Color.white);
        mainText.setFont(normalFont1);
        
        // Line wraps the text 
        mainText.setLineWrap(true);
        
        // Adding the text
        mainPanel.add(mainText);
        */
    }

    
    public class titleScreenHandler implements ActionListener{
    
        @Override
        public void actionPerformed(ActionEvent event){
           new game.Game();
           window.setVisible(false);
           
           s.stopSound("MenuMusic");
                
        } 
    }
    
     public class OptionHandler implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent event){
           new game.Options();
           window.setVisible(false);
           
           s.loopSound("MenuMusic");
                
        } 
     }
     
     public class exitHandler implements ActionListener{
         @Override
        public void actionPerformed(ActionEvent event){
           System.exit(0);
           window.setVisible(false);
           
           s.stopSound("MenuMusic");
                
        } 
     }
    
}