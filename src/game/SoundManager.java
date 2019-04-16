package game;

import java.util.ArrayList;

public abstract class SoundManager 
{
    public ArrayList<Sound> sounds = new ArrayList<Sound>();
    
    public SoundManager()
    {
        initSounds();
    }
    
    public abstract void initSounds();
    
    
    //adds new sounds
    public void addSound(Sound sound)
    {
        sounds.add(sound);
    }
    
        //removes sounds
        public void removeSound(Sound sound)
        {
            sounds.remove(sound);
        }
    
        
    //plays sound
    public void playSound(String name)
    {
        for(Sound s : sounds)
            if(s.name.equals(name))
            {
                s.play();
            }
    }
    
        //loops sound
        public void loopSound(String name)
        {
            for(Sound s : sounds)
                if(s.name.equals(name))
                {
                    s.loop();
                }
        }
        
            //stops sound
            public void stopSound(String name)
            {
            for(Sound s : sounds)
                if(s.name.equals(name))
                {
                    s.stop();
                }
            }
            
                //stops all sounds
                public void stopAllSounds()
                {
                    for(Sound s : sounds)
                        s.stop();
                }
                
                
}
