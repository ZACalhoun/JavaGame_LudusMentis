package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tree extends GameObject
{

    private BufferedImage tree_image;
    
    public Tree(int x, int y, ID id, SpriteSheet ss) 
    {
        super(x, y, id, ss);
    //    this.handler = handler;
        tree_image = ss.grabImage(1, 11, 64, 64);
    }
    

    public void tick() 
    {

    }

    public void render(Graphics g) 
    {
        g.drawImage(tree_image, x, y, null);
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, 64, 64);
    }

    
}
