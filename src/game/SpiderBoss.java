package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SpiderBoss extends GameObject
{
    Handler handler;
    Game game;
    SoundManager s;
    GameObject MindPlayer;
    float diffX, diffY, distance;
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    
    
    private BufferedImage[] Boss_image = new BufferedImage[10];
    Animation anim;

    

    public SpiderBoss(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) 
    {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        Boss_image[0] = ss.grabImage(1, 16, 96, 96);
        Boss_image[1] = ss.grabImage(4, 16, 96, 96);
        Boss_image[2] = ss.grabImage(7, 16, 96, 96);
        Boss_image[3] = ss.grabImage(10, 16, 96, 96);
        Boss_image[4] = ss.grabImage(13, 16, 96, 96);
        Boss_image[5] = ss.grabImage(1, 19, 96, 96);
        Boss_image[6] = ss.grabImage(4, 19, 96, 96);
        Boss_image[7] = ss.grabImage(7, 19, 96, 96);
        Boss_image[8] = ss.grabImage(10, 19, 96, 96);
        Boss_image[9] = ss.grabImage(13, 19, 96, 96);
        
        anim = new Animation(11, Boss_image[0], Boss_image[1], Boss_image[2],
        Boss_image[3], Boss_image[4], Boss_image[5], Boss_image[6], Boss_image[7],
        Boss_image[8], Boss_image[9]);
        
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
        x += velX;
        y += velY;
        
        //x = game.clamp(0,x,game.WIDTH + 64);
        //y = game.clamp(0,y,game.HEIGHT + 64);
        
        choose = r.nextInt(10);
        
        // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
        
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
                    hp -= 25;
                    handler.removeObject(tempObject);   
                }
            }
            
        }
        
        
                
        if(hp <=0 && game.LEVEL == 4)
        {
            s.playSound("Squish");
            handler.removeObject(this);
            int spbkct = game.getSpiderBossKillCount();
                spbkct += 1;
                    game.setSpiderBossKillCount(spbkct);
                    
                int rDrop;
                rDrop = r.nextInt(10);
                    if(rDrop == 6 || rDrop == 9)
                    {
                        handler.addObject(new CrateSpider(getX(), getY(), ID.SpiderCrate, ss));
                    }
        }
     
        if(hp <= 0 && game.LEVEL != 4)
        {
            s.playSound("Squish");
            int ct = game.getSpiderBossCount();
                ct -= 1;
                    game.setSpiderBossCount(ct);     
            
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateSpider(getX(), getY(), ID.SpiderCrate, ss));
                }
                    
            if(game.spiderBossCount == 0)
            {
                handler.addObject(new Door(getX(), getY(), ID.Door, ss));
                s.playSound("DoorAppears");
                
            }
            handler.removeObject(this);
            /*
            if(this.getX() >= game.WIDTH || this.getY() >= game.HIEGHT)
            {
                handler.removeObject(this);
                    ct -= 1;
                        game.setSpiderBossCount(ct);
            }
            */
        }
        anim.runAnimation();
    }

    public void render(Graphics g) 
    {
        if(hp <= 0)
        {
            g.drawImage(Boss_image[0], x, y, null);
        }
        else
            anim.drawAnimation(g, x, y, 0);
        /*
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());
        g2d.draw(getBoundsChase());
        */
    }
    
    public Rectangle getBoundsChase() 
    {
            if(game.LEVEL == 4)
            {
                return new Rectangle(x - 420, y - 420, 960, 960);
            }
            else
              return new Rectangle(x - 100, y - 100, 320, 320);
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x+16, y+16, 77, 77);
    }
    
}

