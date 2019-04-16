package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 *
 * @author Latonya
 */
public class Boss extends GameObject{
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    //constructor
    
    SoundManager s;
    private Handler handler;
    private Game game;
    private Stats stats;
    GameObject MindPlayer;
    float diffX, diffY, distance;
    public static final float DAMPING = 0.5f;
    
    Random r = new Random();
    int choose = 0;
    int hp = 100;
    
    private BufferedImage boss_image[] = new BufferedImage[4];
    
    Animation anim;
  
    protected void Look()
    {
     //   handler.object = game.sphereCollide(x, y, 128);
        for(int i = 0; i < handler.object.size(); i++)
        {
            if(handler.object.get(i).getid()== ID.Player){
                System.out.println("here I am");
            }
        }
    }
    public Boss(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game){
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        boss_image[0] = ss.grabImage(1, 13, 96, 96);
        boss_image[1] = ss.grabImage(4, 13, 96, 96);
        boss_image[2] = ss.grabImage(7, 13, 96, 96);
        boss_image[3] = ss.grabImage(10, 13, 96, 96);

        
        anim = new Animation(4, boss_image[0], boss_image[1], boss_image[2],boss_image[3]);
         
        s = new SoundManager()
    	{
            public void initSounds()
            {
    	 	sounds.add(new Sound("Explosion", Sound.getURL("/Explosion.wav")));
                sounds.add(new Sound("DoorAppears", Sound.getURL("/DoorAppears.wav")));
            }
    	};
    }
    
      public void tick(){
        x += velX;
        y += velY;
        
        //x = game.clamp(0,x,game.WIDTH + 64);
        //y = game.clamp(0,y,game.HEIGHT + 64);
        
        choose = r.nextInt(10);//randomly makes our choose number 1-9
        
        // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
        
        for(int i = 0; i < handler.object.size(); i++){
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
            
            if(tempObject.getid() == ID.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    x += (velX*5) * -1;
                    y += (velY*5) * -1;
                    velX *= -1;
                    velY *= -1;
                }else if(choose == 0){
            velX =(r.nextInt(4 - -4) + -4);
            velY =(r.nextInt(4 - -4) + -4);
            }
        }   
            
            /*if(tempObject.getid() == ID.Player)
            {
                if(getBoundsChase().intersects(tempObject.getBounds()))
                {
                    float speedX = (tempObject.getX() - x);
                    float speedY = (tempObject.getY() - y);

                   float maxSpeed = (1f * DAMPING);
                    
                    if(speedX > maxSpeed)
                        speedX = maxSpeed;
                    if(speedX < -maxSpeed)
                        speedX = -maxSpeed;
                    
                    if(speedY > maxSpeed)
                        speedY = maxSpeed;
                    if(speedY < -maxSpeed)
                        speedY = -maxSpeed;
                    
                    x = (int) (x + speedX);
                    y = (int) (x + speedY);  
                }
            }
            */
       


//how boss reacts with bullet
            if(tempObject.getid() == ID.Bullet)
            {
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 25;
                    handler.removeObject(tempObject);
                    
                }
            }
    }
     anim.runAnimation();
     
     
     if(hp <=0 && game.LEVEL == 4)
        {        
            //remove enemy when health<=0
            handler.removeObject(this);
            
            //plays and removes explosion 
            s.playSound("Explosion");
            handler.removeObject(this);
            int bkct = game.getBossKillCount();
                bkct += 1;
                    game.setBossKillCount(bkct);
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateBug(getX(), getY(), ID.BugCrate, ss));
                }
        }
     
     if(hp <= 0 && game.LEVEL != 4)
     {  
        //remove enemy when health<=0
         handler.removeObject(this);  
         
         //plays and removes explosion 
         handler.addObject(new Explosion(getX(),getY(),ID.Explosion, 10, 32, ss));
            s.playSound("Explosion");
                handler.removeObject(this);
                
        // enemy count code
        int bct = game.getBoss_count();
        bct -= 1;
        game.setBoss_count(bct);
        
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateBug(getX(), getY(), ID.BugCrate, ss));
                }
        
        if(bct == 0) 
        {
            s.playSound("DoorAppears");
            handler.addObject(new Door(getX(), getY(), ID.Door, ss));
        }
        /*
            if(this.getX() >= game.WIDTH || this.getY() >= game.HIEGHT)
            {
                handler.removeObject(this);
                    bct -= 1;
                        game.setBoss_count(bct);
            }
        */
     }
        //game.checkEnd();
    
}
 
      
    public Stats getStats()
    {
        return stats;
    }
   
    public void render(Graphics g){
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
              return new Rectangle(x - 120, y - 120, 320, 320);
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x, y, 84, 78);
    }
}