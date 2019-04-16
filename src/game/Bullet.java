package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bullet extends GameObject
{
    private SpriteSheet ss;
    private BufferedImage sprite_sheet = null;
    private Handler handler;
    
   private BufferedImage[] bullet_image = new BufferedImage[6];
   Animation anim;
    
    
    public Bullet(int x, int y, ID id, Handler handler, int mx, int my, SpriteSheet ss) 
    {
        super(x, y, id, ss);
        this.handler = handler;
        
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");

        ss = new SpriteSheet(sprite_sheet);
        
        velX = (mx - x) / 10;
        velY = (my - y) / 10;
        
        
        bullet_image[0] = ss.grabImage(1, 24, 64, 64);
        bullet_image[1] = ss.grabImage(3, 24, 64, 64);
        bullet_image[2] = ss.grabImage(5, 24, 64, 64);
        bullet_image[3] = ss.grabImage(7, 24, 64, 64);
        bullet_image[4] = ss.grabImage(9, 24, 64, 64);
        bullet_image[5] = ss.grabImage(11, 24, 64, 64);
        
        anim = new Animation(6, bullet_image[0],  bullet_image[1],  bullet_image[2],
                                bullet_image[3],  bullet_image[4],  bullet_image[5]);   
        
        
        anim.runAnimation();
    }

    public void tick() 
    {
        x +=velX;
        y += velY;
        
        if(velX == 0 && velY == 0)
            handler.removeObject(this);
        
        //if(velX < 0 && velY < 0)
            
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    handler.removeObject(this);  
                }
            }
        }
    }

    public void render(Graphics g) 
    {
       //g.drawImage(bullet_image[5], x, y, null);
        g.setColor(Color.green);
        g.fillOval(x, y, 8, 8);
    }

    public Rectangle getBounds() 
    {
        return new Rectangle (x, y, 32, 32);
    }

    
}
