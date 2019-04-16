/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;
    
    public SmartEnemy(int x, int y, ID id, Handler handler, SpriteSheet ss) {
        super(x, y, id, ss);
        this.handler = handler;
        
        
        // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getid() == ID.Player) player = handler.object.get(i);
        }
    }

    
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        
        
        // difference on x axis
       
        if (player.getX() > x) velX = 2;
        else if (player.getX() < x) velX = -2;
        if (player.getY() > y) velY = 1;
        else if (player.getY() < y) velY = -1;
       
        /*
        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.hypot(x - player.getX(), y - player.getY()); 
        
        velX = (int) ((-1/0 / distance) * diffX * 3);
        velY = (int) ((-1/0 / distance) * diffY * 3);
        
        
        if(y <= 0 || y >= BurgerDreamzGame.HEIGHT - 32) velY *= -2;
        if(x <= 0 || x >= BurgerDreamzGame.WIDTH - 16) velX *= -2;
        */
    }
    
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect(x, y, 16, 16);
    }
    
}

