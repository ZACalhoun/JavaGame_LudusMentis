package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MouseInput extends MouseAdapter 
{
    private Handler handler;
    private Camera camera;
    private Game game;
    private SpriteSheet ss;
    private BufferedImage sprite_sheet = null;
    SoundManager s;
    
    public MouseInput(Handler handler, Camera camera, Game game, SpriteSheet ss)
    {
        this.handler = handler;
        this.camera = camera;
        this.game = game;
        this.ss = ss;
        
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");

        ss = new SpriteSheet(sprite_sheet);
        
        s = new SoundManager() 
        {
            public void initSounds()
            {
                sounds.add(new Sound("MagicBullet", Sound.getURL("/MagicBullet.wav")));
            }
        };
    }
    
    public void mousePressed(MouseEvent e)
    {
        
        int mx = (int) (e.getX() + camera.getX());
        int my = (int) (e.getY() + camera.getY());
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Player && game.ammo >= 1)
            {       
                handler.addObject(new Bullet(tempObject.getX() + 16,tempObject.getY() + 24, ID.Bullet, handler, mx, my, ss));
                    game.ammo--;
                        s.playSound("MagicBullet");
            }
        }
    }
}
