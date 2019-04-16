package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SpiderEnemyFollow extends GameObject
{
    Handler handler;
    Game game;
    SoundManager s;
    GameObject MindPlayer;
    float diffX;
    float diffY;
    float distance;
    
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    
    
    private BufferedImage[] enemy_image = new BufferedImage[4];
    Animation anim;

    

    public SpiderEnemyFollow(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) 
    {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        enemy_image[0] = ss.grabImage(1, 5, 32, 32);
        enemy_image[1] = ss.grabImage(2, 5, 32, 32);
        enemy_image[2] = ss.grabImage(3, 5, 32, 32);
        enemy_image[2] = ss.grabImage(1, 5, 32, 32);
        
        anim = new Animation(3, enemy_image[0], enemy_image[1], enemy_image[2]);
        
        s = new SoundManager() 
        {
            public void initSounds()
            {
                sounds.add(new Sound("Squish", Sound.getURL("/Squish.wav")));
                sounds.add(new Sound("DoorAppears", Sound.getURL("/DoorAppears.wav")));
            }
        };
        
        
    }

    public void tick() 
    {
        // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
        
        x += velX;
        y += velY;
        
        choose = r.nextInt(10);
        
       /*
        diffX = x - MindPlayer.getX() - 8;
        diffY = y - MindPlayer.getY() - 8;
        distance = (float)Math.sqrt((x - MindPlayer.getX())*(x-MindPlayer.getX()) + (y - MindPlayer.getY())*(y - MindPlayer.getY()));
        
        velX = (int)(-1.0/distance * diffX *3);
        velY = (int)(-1.0/distance * diffY *3);
        */
       
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Player)
            {
                //alters x, y postioning to 'chase' the player when inside getboundschase
                if(getBoundsChase().intersects(tempObject.getBounds()))
                {
                    diffX = x - MindPlayer.getX() - 8;
                    diffY = y - MindPlayer.getY() - 8;
                    distance = (float)Math.sqrt((x - MindPlayer.getX())*(x-MindPlayer.getX()) + (y - MindPlayer.getY())*(y - MindPlayer.getY()));

                    velX = (int)(-1.0/distance * diffX *3);
                    velY = (int)(-1.0/distance * diffY *3);
                }
            }
            
            if(tempObject.getid() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    x += (velX * 5) * -1;
                    y += (velY * 5) * -1;
                        velX *= -1;
                        velY *= -1;
                }
                
                    else if(choose == 0)
                    {
                        velX =  r.nextInt(5 - -5 + -5);
                        velY =  r.nextInt(5 - -5 + -5);
                    }
                
            }
            
            if(tempObject.getid() == ID.Bullet)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    hp -= 50;
                    handler.removeObject(tempObject);   
                }
            }
            
        }
       
        if(hp <= 0)
        {
            s.playSound("Squish");
            
            int ct = game.getSpiderCount();
                ct -= 1;
                    game.setSpiderCount(ct);
                    
            handler.removeObject(this);
        }
        
        anim.runAnimation();
    }

    public void render(Graphics g) 
    {
        if(hp <= 0)
        {
            g.drawImage(enemy_image[0], x, y, null);
        }
        else
            anim.drawAnimation(g, x, y, 0);
      
      Graphics2D g2d = (Graphics2D) g;
      g.setColor(Color.green);
      g2d.draw(getBoundsChase());
        
    }

    public Rectangle getBoundsChase() 
    {
        return new Rectangle(x - 250, y - 200, 500, 500);
    }
    
    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, 32, 32);
    }
    
}



