
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Latonya
 */


public class Explosion extends GameObject{
    
    //fields
    private Handler handler;

    
    public int r;
    public int maxRadius;
    
//contructor
    public Explosion(int x, int y, ID id, int r, int max, SpriteSheet ss){
        super(x, y, id, ss);
        this.r =r;
        maxRadius = max;
   
             //radius around the enemy
    
        x-=r;
        y-=r;
    
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        r ++;
      
        if(r >= maxRadius){
          r = 0;
          maxRadius = 0;
            }
        
   
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(255,255,255,128));
        g.drawOval((int)(x-r),(int)(y-r),r*2, r*2);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }


  
    
}


