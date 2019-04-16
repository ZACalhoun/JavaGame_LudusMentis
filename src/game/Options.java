package game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Options 
{
    private Handler handler;
    SoundManager s;
    Game game;
    JFrame window;
    Container c;
    JPanel titlePanel, howToBtnPanel, mainPanel, soundBtnPanel, backBtnPanel;
    JLabel titleLabel;

    Font titleFont = new Font("Arial", Font.ITALIC, 80);
    Font normalFont1 = new Font("Times New Roman", Font.PLAIN, 50);
    JButton htBtn, soundBtn, backBtn; 

    JTextArea mainText;

    howToHandler htHandler = new howToHandler();
    soundHandler sHandler = new soundHandler();
    backBtnHandler bHandler = new backBtnHandler();




public Options(){

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
    titlePanel = new JPanel();
    titlePanel.setBounds(165, 50, 665, 100);
    titlePanel.setBackground(Color.blue);
    titleLabel = new JLabel("Option Menu");
    titleLabel.setForeground(Color.white);
    titleLabel.setFont(titleFont);

    // How To button panel settings
    howToBtnPanel = new JPanel();
    howToBtnPanel.setBounds(410, 240, 200, 80);
    howToBtnPanel.setBackground(Color.black);

    // Sound button panel settings
    soundBtnPanel = new JPanel();
    soundBtnPanel.setBounds(410, 330, 200, 80);
    soundBtnPanel.setBackground(Color.black);

    // Exit button panel settings
    backBtnPanel = new JPanel();
    backBtnPanel.setBounds(410, 420, 200, 80);
    backBtnPanel.setBackground(Color.black);

    // Font and text for How To button
    htBtn = new JButton("How to");
    htBtn.setBounds(410, 240, 200, 80);
    htBtn.setBackground(Color.green);
    htBtn.setForeground(Color.black);
    htBtn.setFont(normalFont1);

    // Font and text for Option button
    soundBtn = new JButton("Sound");
    soundBtn.setBounds(410, 330, 150, 80);
    soundBtn.setBackground(Color.blue);
    soundBtn.setForeground(Color.white);
    soundBtn.setFont(normalFont1);

    // Font and text for Exit button
    backBtn = new JButton("Back");
    backBtn.setBounds(410, 420, 150, 80);
    backBtn.setBackground(Color.red);
    backBtn.setForeground(Color.white);
    backBtn.setFont(normalFont1);


    // Checks if button is being pressed
    htBtn.addActionListener(htHandler);
    soundBtn.addActionListener(sHandler);
    backBtn.addActionListener(bHandler);

    // Adding to the screen
    titlePanel.add(titleLabel);
    howToBtnPanel.add(htBtn);
    soundBtnPanel.add(soundBtn);
    backBtnPanel.add(backBtn);
    c.add(titlePanel);
    //c.add(titleLabel);
    c.add(howToBtnPanel);
    c.add(soundBtnPanel);
    c.add(backBtnPanel);
    window.setVisible(true);

        /*
        s = new SoundManager() 
        {
        public void initSounds()
        {
            sounds.add(new Sound("MenuButton", Sound.getURL("/MenuButton.wav")));
        }
        };
        */
        //s.loopSound("MenuMusic");

}

public void howToScreen(){

    titlePanel.setVisible(false);
    howToBtnPanel.setVisible(false);
    soundBtnPanel.setVisible(false);

    mainPanel = new JPanel();
    mainPanel.setBounds(200, 100, 600, 250);
    mainPanel.setBackground(Color.green);
    c.add(mainPanel);

    mainText = new JTextArea("WASD Controls, Destroy All Enemies, Brain POWER!");
    mainText.setBounds(100, 100, 600, 250);
    mainText.setBackground(Color.black);
    mainText.setForeground(Color.white);
    mainText.setLineWrap(true);
    mainText.setFont(normalFont1);

    // Line wraps the text 
    mainText.setLineWrap(true);

    // Adding the text
    mainPanel.add(mainText);

}

public void soundScreen()
{

    titlePanel.setVisible(false);
    howToBtnPanel.setVisible(false);
    soundBtnPanel.setVisible(false);

    mainPanel = new JPanel();
    mainPanel.setBounds(200, 100, 600, 100);
    mainPanel.setBackground(Color.green);
    c.add(mainPanel);

    mainText = new JTextArea("Sound adjustments to come..");
    mainText.setBounds(100, 100, 600, 250);
    mainText.setBackground(Color.black);
    mainText.setForeground(Color.white);
    mainText.setLineWrap(true);
    mainText.setFont(normalFont1);

    // Line wraps the text 
    mainText.setLineWrap(true);

    // Adding the text
    mainPanel.add(mainText);

}

public class howToHandler implements ActionListener{
@Override
public void actionPerformed(ActionEvent event){
howToScreen();


// s.stopSound("MenuMusic");

} 
}

public class soundHandler implements ActionListener{
@Override
public void actionPerformed(ActionEvent event){
soundScreen();


// s.stopSound("MenuMusic");

} 
}

public class backBtnHandler implements ActionListener{
@Override
public void actionPerformed(ActionEvent event)
{
    window.setVisible(false);
    new game.Menu();
    //s.stopSound("MenuMusic");
} 
}

}