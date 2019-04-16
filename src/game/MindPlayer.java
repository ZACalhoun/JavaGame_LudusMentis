package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

public class MindPlayer extends GameObject {
    
    SoundManager s;
    Handler handler;
    Game game;
    Door door;

    private BufferedImage[] MindPlayer_image = new BufferedImage[6];
    private Stats stats;
    
    Animation anim;
    
    public MindPlayer (int x, int y, ID id, Handler handler, Game game,SpriteSheet ss)
    {
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        stats = new Stats();
        
        MindPlayer_image[0] = ss.grabImage(1, 1, 32, 32);
        MindPlayer_image[1] = ss.grabImage(2, 1, 32, 32);
        MindPlayer_image[2] = ss.grabImage(3, 1, 32, 32);
        MindPlayer_image[3] = ss.grabImage(4, 1, 32, 32);
        MindPlayer_image[4] = ss.grabImage(5, 1, 32, 32);
        MindPlayer_image[5] = ss.grabImage(6, 1, 32, 32);
        
        
        anim = new Animation(6, MindPlayer_image[0], MindPlayer_image[1], MindPlayer_image[2],
                                MindPlayer_image[3], MindPlayer_image[4], MindPlayer_image[5]);   
        
    s = new SoundManager()
    {
        public void initSounds()
        {
            sounds.add(new Sound("Hurt", Sound.getURL("/PlayerHurt.wav")));
            sounds.add(new Sound("GameOver", Sound.getURL("/GameOver.wav")));
            sounds.add(new Sound("ChestOpen", Sound.getURL("/ChestOpen.wav")));
        }
    };
    
    }
    
    private void move(float magX, float magY)
    {
        x += getSpeed() * magX;
        y += getSpeed() * magY;
    }
    
    public float getSpeed()
    {
        return stats.getSpeed();
    }
    
    public void tick() 
    {
        x += getSpeed() * velX;
        y += getSpeed() * velY;
        
        //x = game.clamp(x, 0,game.WIDTH);
        //y = game.clamp(y, 0,game.HEIGHT);
        
        collision();
        
        //movement
        velY = 0;
        velX = 0;
        
        if(handler.isUp() && !handler.isDown()) velY = -5;
            if(handler.isDown() && !handler.isUp()) velY = 5;
                if(handler.isRight() && !handler.isLeft()) velX = 5;
                    if(handler.isLeft() && !handler.isRight()) velX = -5;
                    
                    
                    
                    anim.runAnimation();
    }

    private void collision()
    {
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getid() == ID.Block)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            
            if(tempObject.getid() == ID.Tree)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    x += velX * -1;
                    y += velY * -1;
                }
            }
            
            if(tempObject.getid() == ID.SpiderCrate)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("ChestOpen"); 
                        game.ammo += 10;
                            game.hP += 10;
                                    if(game.hP >= 100)
                                    {
                                        game.hP = 100;
                                    }
                    handler.removeObject(tempObject);
                }
            }
            
            if(tempObject.getid() == ID.SnakeCrate)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("ChestOpen"); 
                        game.ammo += 10;
                            game.hP += 10;
                                if(game.hP >= 100)
                                {
                                    game.hP = 100;
                                }  
                    handler.removeObject(tempObject);
                }
            }
            
            
            if(tempObject.getid() == ID.BugCrate){
                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("ChestOpen"); 
                        game.ammo += 10;
                            game.hP += 10;
                                if(game.hP >= 100)
                                {
                                    game.hP = 100;
                                }        
                    handler.removeObject(tempObject);
                }
            }
  
            if(tempObject.getid() == ID.Enemy)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("Hurt");  
                        game.hP--;
                }
            }
            
            if(tempObject.getid() == ID.Bug)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("Hurt");  
                        game.hP--;
                }
            }
            
            if(tempObject.getid() == ID.SpiderBoss)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("Hurt");  
                        game.hP--;

                }
            }
            
            if(tempObject.getid() == ID.Snake)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    s.playSound("Hurt");  
                        game.hP--;
                }
            }
  
            if(tempObject.getid() == ID.SnakeBoss)
            {
                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    
                    s.playSound("Hurt");
                        game.hP--;
                }
            }
            
            if(tempObject.getid() == ID.Boss)
            {
                
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    
                    s.playSound("Hurt");
                        game.hP--;
                }
            }
            
            /*
            if(tempObject.getid() == ID.SpiderEnemyFollow)
            {
                if(getBounds().intersects(tempObject.getBounds()))
                {
                    game.hP--;
                }
            }
            */
            
            if(tempObject.getid() == ID.Door)
             {
            	if(getBounds().intersects(tempObject.getBounds()))
            	{
                    game.switchLevel();
            	}
             }
            
            
            
            if (game.hP == 0)
            {
                s.playSound("GameOver");
 
                {
                    JOptionPane.showMessageDialog(null, "Game Over! ");
                    System.exit(0);
                    /*int play = JOptionPane.showConfirmDialog(null, 
                            "Play Again", "Again", JOptionPane.YES_NO_OPTION);
                    System.out.print("the value is" +play);

                    if(play == JOptionPane.YES_OPTION)
                    {
                        //game.close();
                        new Menu();
                    }
                    else
                    {
                        System.exit(0);
                    }
                    */
                }
            }
        }
    }
    
    public void render(Graphics g) 
    {
        if(velX == 0 && velY == 0)
           g.drawImage(MindPlayer_image[0], x, y, null); 
            else
                anim.drawAnimation(g, x, y, 0);
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
