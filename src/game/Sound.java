package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound 
{
    private static Sound staticSound = new Sound();
    
   public String name;
   public AudioClip sound;
   
   //grabs the url
   private Sound(){}
    
   
   //gives sound name and supplies said sound with a url
    public Sound(String name, URL url)
    {
        this.name = name;
        try
        {
          sound =  Applet.newAudioClip(url);
        }catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
    
    //plays sound on own thread
    public void play()
    {
        new Thread(new Runnable() {
            
            public void run()
            {
                if(sound != null)
                    sound.play();
            }
        }).start();
    }
        
        //loops sound on own thread
        public void loop()
        {
            new Thread(new Runnable() {

                public void run()
                {
                    if(sound != null)
                        sound.loop();
                }
            }).start();
        }
        
            //stops sound
            public void stop()
            {
                if(sound != null)
                    sound.stop();
            }
            
    //gets url
    public static URL getURL(String fileName)
    {
        return staticSound.getClass().getResource(fileName);
    }
}
