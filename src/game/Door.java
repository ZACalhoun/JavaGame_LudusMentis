
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 *
 * @author Latonya
 */
public class Door extends GameObject{
    
    Handler handler;
    private BufferedImage[] door_image = new BufferedImage[4];
    
    Animation anim;
    
    public Door(int x, int y, ID id, SpriteSheet ss){
        super(x, y, id, ss);
        
        /*
        door_image[0] = ss.grabImage(1, 22, 96, 96);
        door_image[1] = ss.grabImage(4, 22, 96, 96);
        door_image[2] = ss.grabImage(7, 22, 96, 96);
        door_image[3] = ss.grabImage(10, 22, 96, 96);
       */
        
        door_image[0] = ss.grabImage(1, 22, 64, 64);
        door_image[1] = ss.grabImage(3, 22, 64, 64);
        door_image[2] = ss.grabImage(5, 22, 64, 64);
        door_image[3] = ss.grabImage(7, 22, 64, 64);
        
        anim = new Animation(4,door_image[0],door_image[1],door_image[2],door_image[3]);

        
// collision();
       //anim.runAnimation();
    }
    
    public void tick()
    {
        //collision();
    }
    
    public void render(Graphics g)
    {
        g.drawImage(door_image[1], x, y, null);
        //anim.runAnimation();
    }
    
    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Player)
            {
                if(getBoundsBig().intersects(tempObject.getBounds()))
                {
                    anim.runAnimation();
                }
            }
 
        }
    }
    
    
    public Rectangle getBoundsBig()
    {
        return new Rectangle(x, y, 96, 96);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
}
