package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Snake extends GameObject
{
    Handler handler;
    SoundManager s;
    GameObject MindPlayer;
    float diffX, diffY, distance;
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    Game game;
    Game g;
    
    private BufferedImage[] enemy_image = new BufferedImage[4];
    Animation anim;

    public Snake(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game) 
    {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        /*
        enemy_image[0] = ss.grabImage(1, 3, 32, 32);
        enemy_image[1] = ss.grabImage(2, 3, 32, 32);
        enemy_image[2] = ss.grabImage(3, 3, 32, 32);
        
        anim = new Animation(3, enemy_image[0], enemy_image[1], enemy_image[2]);
        */
        
        enemy_image[0] = ss.grabImage(1, 8, 32, 32);
        enemy_image[1] = ss.grabImage(2, 8, 32, 32);
        enemy_image[2] = ss.grabImage(3, 8, 32, 32);
        enemy_image[3] = ss.grabImage(4, 8, 32, 32);
        
        
      anim = new Animation(4, enemy_image[0], enemy_image[1], enemy_image[2], enemy_image[3]);
        
    
        s = new SoundManager() 
        {
            public void initSounds()
            {
                sounds.add(new Sound("DoorAppears", Sound.getURL("/DoorAppears.wav")));
                sounds.add(new Sound("Hiss", Sound.getURL("/Hiss.wav")));
            }
        };
    }

    public void tick() 
    {
        x += velX;
        y += velY;
        
       // x = game.clamp(0,x,game.WIDTH + 64);
        //y = game.clamp(0,y,game.HEIGHT + 64);
        
        // Picks a random between 0-9
        choose = r.nextInt(10);
        
       // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
        
        // Gets the Block and knows when the enemy intersects with the block
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
                    x += (velX * 4) * -1;   // ricochets off the bounds that the enemy touches
                    y += (velY * 4) * -1;
                        velX *= - 1;
                        velY *= - 1;
                }
                    else if(choose == 0)
                    {
                        velX =  r.nextInt(5 - -4 + -5);
                        velY =  r.nextInt(5 - -4 + -5);
                    }
            }
             
            // This verifies when the bullet kills the enemy
            if(tempObject.getid() ==ID.Bullet)
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
            s.playSound("Hiss");
            handler.removeObject(this);
            int skct = game.getSnakeKillCount();
                skct += 1;
                    game.setSnakeKillCount(skct);
                    
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateSnake(getX(), getY(), ID.SnakeCrate, ss));
                }
        }
     
     if(hp <= 0 && game.LEVEL != 4)
        {
            s.playSound("Hiss");
            handler.removeObject(this);
            int ct = game.getSnake_count();
                ct -= 1;
                    game.setSnake_count(ct);
                    
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateSnake(getX(), getY(), ID.SnakeCrate, ss));
                }
                   
                    if(ct == 0) 
                    {
                	handler.addObject(new SnakeBoss(300, 600,ID.SnakeBoss, handler, ss, game));
                        handler.addObject(new SnakeBoss(1600, 700,ID.SnakeBoss, handler, ss, game));
                        handler.addObject(new SnakeBoss(1400, 200,ID.SnakeBoss, handler, ss, game));
                        handler.addObject(new SnakeBoss(200, 100,ID.SnakeBoss, handler, ss, game));
                        handler.addObject(new SnakeBoss(800, 350,ID.SnakeBoss, handler, ss, game));
                        handler.addObject(new SnakeBoss(800, 800,ID.SnakeBoss, handler, ss, game));
                    }
                 /*   
                if(this.getX() >= game.WIDTH || this.getY() >= game.HIEGHT)
                {
                    handler.removeObject(this);
                        ct -= 1;
                            game.setSnake_count(ct);
                }
                    */
        }
        
        anim.runAnimation();
    }

    public void render(Graphics g) 
    {
        anim.drawAnimation(g, x, y, 0);
        
        /*Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());
        g2d.draw(getBoundsChase());*/
    }
    
    public Rectangle getBoundsChase() 
    {
        return new Rectangle(x - 140, y - 145, 320, 320);
    }

    public Rectangle getBounds() 
    {
        return new Rectangle(x, y, 32, 32);
    }
    
}
