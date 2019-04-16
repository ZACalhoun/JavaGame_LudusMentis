package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

//spider class zc
public class Spider extends GameObject
{
    Handler handler;
    Game game;
    SoundManager s;
    GameObject MindPlayer;
    float diffX, diffY, distance;
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    
    private BufferedImage[] enemy_image = new BufferedImage[4];
    Animation anim;

    
    
    public Spider(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) 
    {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        enemy_image[0] = ss.grabImage(1, 5, 32, 32);
        enemy_image[1] = ss.grabImage(2, 5, 32, 32);
        enemy_image[2] = ss.grabImage(3, 5, 32, 32);
        enemy_image[2] = ss.grabImage(1, 5, 32, 32);
        
     
        anim = new Animation(3, enemy_image[0], enemy_image[1], enemy_image[2]);
        
        //sounds for spider and door to next level
        s = new SoundManager() 
        {
            public void initSounds()
            {
                sounds.add(new Sound("Squish", Sound.getURL("/Squish.wav")));
                sounds.add(new Sound("DoorAppears", Sound.getURL("/DoorAppears.wav")));
                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
            }
        };
    }
    
//animates spider,controls movement, hp, and sound zc
    public void tick() 
    {
        x += velX;
        y += velY;
        
        //x = game.clamp(0,x,game.WIDTH + 64);
        //y = game.clamp(0,y,game.HEIGHT + 64);
        
        choose = r.nextInt(10);
        
        // Checks if it gets the player object zc
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
        
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Player)
            {
                //alters x, y postioning to 'chase' the player when inside getboundschase zc
                if(getBoundsChase().intersects(tempObject.getBounds()))
                {
                    diffX = x - MindPlayer.getX() - 8;
                    diffY = y - MindPlayer.getY() - 8;
                    distance = (float)Math.sqrt((x - MindPlayer.getX())*(x-MindPlayer.getX()) + (y - MindPlayer.getY())*(y - MindPlayer.getY()));

                    velX = (int)(-1.0/distance * diffX *3);
                    velY = (int)(-1.0/distance * diffY *3);
                }
            }
            
            //keeps spider from going through blocks 'kinda' zc
            if(tempObject.getid() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    x += (velX * 4) * -1;
                    y += (velY * 4) * -1;
                        velX *= -1;
                        velY *= -1;
                }
                    else if(choose == 0)
                    {
                        velX =  r.nextInt(4 - -5 + -4);
                        velY =  r.nextInt(4 - -5 + -4);
                    }
            }
            
            //if spider is hit by bullet lose hp zc
            if(tempObject.getid() == ID.Bullet)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    hp -= 50;
                    handler.removeObject(tempObject);   
                }
            }
            
        }
       
        if(hp <=0 && game.LEVEL == 4)
        {   
            handler.removeObject(this);
            s.playSound("Squish");
            int skct = game.getSpiderKillCount();
                skct += 1;
                    game.setSpiderKillCount(skct);
        }
     
     if(hp <= 0 && game.LEVEL != 4)
        {
            handler.removeObject(this); 
            s.playSound("Squish");
            int ct = game.getSpiderCount();
                ct -= 1;
                    game.setSpiderCount(ct);
                           
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateSpider(getX(), getY(), ID.SpiderCrate, ss));
                }
         
            if(ct == 0)
            { 
                handler.addObject(new SpiderBoss(500, 600,ID.SpiderBoss, handler, ss, game));
                handler.addObject(new SpiderBoss(1500, 800,ID.SpiderBoss, handler, ss, game));
                handler.addObject(new SpiderBoss(1300, 50,ID.SpiderBoss, handler, ss, game));
                handler.addObject(new SpiderBoss(200, 100,ID.SpiderBoss, handler, ss, game));
                handler.addObject(new SpiderBoss(100, 450,ID.SpiderBoss, handler, ss, game));
                handler.addObject(new SpiderBoss(400, 900,ID.SpiderBoss, handler, ss, game));
            }
            
            /*
            if(this.getX() >= game.WIDTH || this.getY() >= game.HIEGHT)
            {
                handler.removeObject(this);
                    ct -= 1;
                        game.setSpiderCount(ct);
            }
            */
        }
        
        anim.runAnimation();
    }

    //draws spider to game zc
    public void render(Graphics g) 
    {
        if(hp <= 0)
        {
            g.drawImage(enemy_image[0], x, y, null);
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
    
    //chases character if within these boundries zc
    public Rectangle getBoundsChase() 
    {
        return new Rectangle(x - 140, y - 145, 320, 320);
    }
    
    //used for hit marker of spider as well as to damage PC zc
    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, 32, 32);
    }
    
}
