/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
/**
 *
 * @author Latonya
 */
public class BlockBug extends GameObject
{
    
    private BufferedImage block_image;
    
    public BlockBug(int x, int y, ID id, SpriteSheet ss)
    {
        super(x, y, id, ss);
            block_image = ss.grabImage(1, 10, 32, 32);
       
    }
    
    public void tick()
    {
        
    }
    
    public void render(Graphics g)
    {
        g.drawImage(block_image, x, y, null);
       /* 
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());
        */
    }
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 32, 32);
    }

}
