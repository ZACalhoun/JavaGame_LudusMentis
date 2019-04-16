
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
public class Bugs extends GameObject{
    
    private Handler handler;
    private Game game;
    SoundManager s;
    GameObject MindPlayer;
    float diffX, diffY, distance;
    Random r = new Random();
     
    int choose = 0;
    int hp = 100;
    
    private BufferedImage enemy_image[] = new BufferedImage[5];
     
    Animation anim;
    
    public Bugs(int x, int y, ID id, Handler handler, SpriteSheet ss, Game game){
        super(x, y, id, ss);
        this.handler = handler;
        this.game = game;
        
        enemy_image[0] = ss.grabImage(3, 11, 64, 64);
        enemy_image[1] = ss.grabImage(5, 11, 64, 64);
        enemy_image[2] = ss.grabImage(7, 11, 64, 64);
        enemy_image[3] = ss.grabImage(9, 11, 64, 64);
        enemy_image[4] = ss.grabImage(11, 11, 64, 64);

        anim = new Animation(5, enemy_image[0], enemy_image[1], enemy_image[2],enemy_image[3],enemy_image[4] );
        
        s = new SoundManager()
    	{
            public void initSounds()
            {
    	 	sounds.add(new Sound("Explosion", Sound.getURL("/Explosion.wav")));
            }
    	};
    }
    //movements of enemy
    public void tick(){
        x += velX;
        y += velY;
       
        //x = game.clamp(0,x,990);
        //y = game.clamp(0,y,500);
        
        choose = r.nextInt(10);//randomly makes our choose number 1-9
        // **CHASE AI**
        
        // Checks if it gets the player object
        for(int i = 0; i < handler.object.size(); i++) 
        {
            if(handler.object.get(i).getid() == ID.Player) MindPlayer = handler.object.get(i);
        }
	// System.out.println(XorY);

        //how it reacts with the block object
        
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
                    x += (velX*4) * -1;
                    y += (velY*4) * -1;
                    velX *= -1;
                    velY *= -1;
                }
                    else if(choose == 0)
                    {
                        velX =(r.nextInt(4 - -5) + -4);
                        velY =(r.nextInt(4 - -5) + -4);
                    }
        }//how it reacts with the bullet object
            if(tempObject.getid() == ID.Bullet){
                if(getBounds().intersects(tempObject.getBounds())){
                    hp -= 50;
                    handler.removeObject(tempObject);                  
                }
            }  
          
    }
     anim.runAnimation();
     
     if(hp <=0 && game.LEVEL == 4)
        {
          handler.removeObject(this);
          
         //plays and removes explosion 
         handler.addObject(new Explosion(getX(),getY(),ID.Explosion, 10, 32, ss));
            s.playSound("Explosion");
                handler.removeObject(this);
                
          
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateBug(getX(), getY(), ID.BugCrate, ss));
                }     
            
            int bkct = game.getBugKillCount();
                bkct += 1;
                    game.setBugKillCount(bkct);
        }
     
     if(hp <= 0 && game.LEVEL != 4)
     {
         //removes enemy when health <=0                      
         handler.removeObject(this);
         
         //plays and removes explosion 
         handler.addObject(new Explosion(getX(),getY(),ID.Explosion, 10, 32, ss));
            s.playSound("Explosion");
                handler.removeObject(this);  
         
         // enemy count code
        int ct = game.getBug_count();
        ct -= 1;
        game.setBug_count(ct);
        
            int rDrop;
            rDrop = r.nextInt(10);
                if(rDrop == 6 || rDrop == 9)
                {
                    handler.addObject(new CrateBug(getX(), getY(), ID.BugCrate, ss));
                }
        
                    if(ct == 0)
                    {
                        handler.addObject(new Boss(500, 600,ID.Boss, handler, ss, game));
                        handler.addObject(new Boss(1200, 800,ID.Boss, handler, ss, game));
                        handler.addObject(new Boss(1300, 400,ID.Boss, handler, ss, game));
                        handler.addObject(new Boss(200, 100,ID.Boss, handler, ss, game));
                        handler.addObject(new Boss(800, 400,ID.Boss, handler, ss, game));
                        handler.addObject(new Boss(400, 900,ID.Boss, handler, ss, game));

                    }
                    /*
            if(this.getX() >= game.WIDTH || this.getY() >= game.HIEGHT)
            {
                handler.removeObject(this);
                    ct -= 1;
                        game.setBug_count(ct);
            }
                    */
     }
    
}
       
    public void render(Graphics g)
    {
        anim.drawAnimation(g, x, y, 0);
        /*
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.green);
        g2d.draw(getBounds());
        //g2d.draw(getBoundsChase());
        */
        
    }
    
    public Rectangle getBoundsChase() 
    {
        return new Rectangle(x - 140, y - 145, 320, 320);
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 35, 39);
    }
}
