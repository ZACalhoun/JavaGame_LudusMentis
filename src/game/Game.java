package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = 1L;
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    private Camera camera;
    private SpriteSheet ss;
    private Spawn spawner;
    SoundManager s;
    
    private BufferedImage level1 = null;
    private BufferedImage level2 = null;
    private BufferedImage level3 = null;
    private BufferedImage arena = null;
    private BufferedImage sprite_sheet = null;
    private BufferedImage floor = null;
            
    public int ammo = 200;
    public int hP = 100;
    private int timer = 0;
    private Random r = new Random();
    public int WAVE = 0;
    
    private int greenValue = 255;
    
    
    public int spiderCount = 20; //20
    public int spiderBossCount = 6; //6
    public int snakeCount = 17;//17
    public int snakeBossCount = 6;//6
    public int bugCount = 29;//29
    public int bossCount = 6;//6
        public int spiderKillCount = 0;
        public int spiderBossKillCount = 0;
        public int snakeKillCount = 0;
        public int snakeBossKillCount = 0;
        public int bugKillCount = 0;
        public int bossKillCount = 0;
           
    public static int LEVEL = 1;
    public final int WIDTH = 1000, HIEGHT = 563;
     
    public Game()
    {    
        handler = new Handler();       
        //Creates a new Window
       new Window(1000, 563, "Ludus Mentis", this);
        
            camera = new Camera(0, 0);
            spawner = new Spawn(handler, this);
            
                    //Loads The Level Image Into The Game
                    BufferedImageLoader loader = new BufferedImageLoader();
                    level1 = loader.loadImage("/Mind1Level.png");
                    level2 = loader.loadImage("/LudusMentisStage.png");
                    level3 = loader.loadImage("/wiz5_level.png");
                    arena = loader.loadImage("/MindArena.png");
  
                        //Adds Mouse Listener For bullet
                        this.addMouseListener(new MouseInput(handler, camera, this, ss));
                        //Adds a Listner for KeyInput for Player Movement
                        this.addKeyListener(new KeyInput(handler));
                        
                                //Loads the level
                                loadLevel(level1);
                                //loadLevelSnake(level2);
                               //loadLevelBugs(level3);
                                  //loadArena(arena);
                         /*        
                        s = new SoundManager()
                        {
                            public void initSounds()
                            {
                                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
                            }
                        };
                     
                        s.loopSound("BGLoop3");
                                  */
                                  
        start();
                     
                    
    }
    //starts the game
    private void start()
    {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }    
    
    //stops the game from running
    private void stop()
    {
        isRunning = false;
        try 
        {
            thread.join();
        } 
        
        catch (InterruptedException ex) 
        {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //used by mincraft to run the game optimally 
    public void run()
    {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
            
            while(isRunning)
            {
                long now = System.nanoTime();
                delta += (now -lastTime) /ns;
                lastTime = now;
                        
                        while (delta >= 1)
                        {
                            tick();
                            delta--;
                        }
                        
                            render();
                            frames++;
                            
                                if(System.currentTimeMillis() - timer > 1000)
                                {
                                    timer += 1000;
                                    frames = 0;
                                }
            }
            
        stop();
    }
    
    
    public void tick()
    {
        for (int i = 0; i < handler.object.size(); i++)
        {
            if(handler.object.get(i).getid() == ID.Player)
            {
                camera.tick(handler.object.get(i));
            }
        }
        
        handler.tick();
        spawner.tick();
    }
    
    //renders everything
    public void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
            if(bs == null)
            {
                this.createBufferStrategy(3);
                return;
            }
            
                Graphics g = bs.getDrawGraphics();
                Graphics2D g2d = (Graphics2D) g;
                
                ////////////////////////////////
                
                g2d.translate(-camera.getX(), -camera.getY());
                
                for(int xx = 0; xx < 30 * 72; xx += 32)
                {
                    for(int yy = 0; yy < 30 * 72; yy += 32)
                    {
                        g.drawImage(floor, xx, yy, null);
                    }
                }
                
                handler.render(g);
               
                g2d.translate(camera.getX(), camera.getY());

                //hp bar for character
                g.setColor(Color.red);
                g.fillRect(5, 5, 200, 32);
                    g.setColor(new Color(100,greenValue=hP*2,0));
                    //g.setColor(Color.green);
                    g.fillRect(5, 5, hP*2, 32);                  
                        g.setColor(Color.black);
                        g.drawRect(5, 5, 200, 32);
                        
                 //Displays characters ammo       
                g.setFont(new Font("Arial", 0, 15));       
                g.setColor(Color.white);
                g.drawString("Ammo: " + ammo, 5, 50);
                
                    if(LEVEL == 1 && spiderCount > 0)
                    {
                        //Displays spider count
                        g.setFont(new Font("Arial", 0, 15));       
                        g.setColor(Color.white);
                        g.drawString("Spider Count: " + spiderCount, 5, 65);
                    }
                    if(LEVEL == 1 && spiderCount <= 0)
                    {
                        //Displays spider boss count
                        g.setFont(new Font("Arial", 0, 15));       
                        g.setColor(Color.white);
                        g.drawString("Spider Boss Count: " + spiderBossCount, 5, 65);
                    }
                        if(LEVEL == 2 && snakeCount > 0)
                        {
                            //Displays snake count
                            g.setFont(new Font("Arial", 0, 15));       
                            g.setColor(Color.white);
                            g.drawString("Snake Count: " + snakeCount, 5, 65);
                        }
                        
                        if(LEVEL == 2 && snakeCount <= 0)
                        {
                            //Displays snake count
                            g.setFont(new Font("Arial", 0, 15));       
                            g.setColor(Color.white);
                            g.drawString("Snake Boss Count: " + snakeBossCount, 5, 65);
                        }
                        
                            if(LEVEL == 3 && bugCount > 0)
                            {
                                //Displays bug count
                                g.setFont(new Font("Arial", 0, 15));       
                                g.setColor(Color.white);
                                g.drawString("Bug Count: " + bugCount, 5, 65);
                            }    
                            if(LEVEL == 3 && bugCount <= 0)
                            {    
                            //Displays boss count
                            g.setFont(new Font("Arial", 0, 15));       
                            g.setColor(Color.white);
                            g.drawString("Bug Boss Count: " + bossCount, 5, 65);
                            }
                            
                            if(LEVEL == 4)
                            {
                                //Displays spider count
                                g.setFont(new Font("Arial", 0, 15));       
                                g.setColor(Color.white);
                                g.drawString("Wave: " + WAVE, 5, 65);
                                //g.drawString("Timer: " + spawner.timeKeep, 5, 80);
                                g.drawString("Spider Kills: " + spiderKillCount, 5, 80);
                                g.drawString("Spider Boss Kills: " + spiderBossKillCount, 5, 95);
                                g.drawString("Snake Kills: " + snakeKillCount, 5, 110);
                                g.drawString("Snake Boss Kills: " + snakeBossKillCount, 5, 125);
                                g.drawString("Bug Kills: " + bugKillCount, 5, 140);
                                g.drawString("Bug Boss Kills: " + bossKillCount, 5, 155);
                            }

                ////////////////////////////////
                    g.dispose();
                    bs.show();
    }
    
    
    //loading level 1 Spider level zc
    private void loadLevel(BufferedImage image)
    {      
        
        //Loads the Sprite Sheet zc
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");
            
        //Creats new sprite sheet and Grabs Images From
        //Sprite Sheet to Create the Floor Texture zc
        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(2, 7, 32, 32);
        
        
        //Takes the Level Image and locates specific Pixels by their RGB #'s
        //To create Objects, Players, Enemies, Walls, etc zc
        int w = image.getWidth();
        int h = image.getHeight();
        
            for(int xx = 0; xx < w; xx++)
            {
                for(int yy = 0; yy < h; yy++)
                {
                    int pixel = image.getRGB(xx, yy);
                        int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                                int blue = (pixel) & 0xff;
                        
                if(red == 255 && green == 0 && blue == 0)
                    handler.addObject(new BlockSpider(xx*32, yy*32, ID.Block, ss));
                    if(blue == 255 && green == 0 && red == 0)
                        handler.addObject(new MindPlayer(xx*32, yy*32, ID.Player, handler, this, ss));
                        if(green == 255 && blue == 0 && red == 0)
                           handler.addObject(new Spider(xx*32, yy*32, ID.Enemy, handler, ss, this));
                            if(green == 255 && blue == 255 && red == 0)
                                handler.addObject(new CrateSpider(xx*32, yy*32, ID.SpiderCrate, ss));      
                                if(green == 255 && blue == 0 && red == 255)
                                    handler.addObject(new Door(xx*32, yy*32, ID.Door, ss));
                //if(green == 220 && red == 200 && blue == 0)
                    //handler.addObject(new SpiderEnemyFollow(xx*32, yy*32, ID.SpiderEnemyFollow, handler, ss, this));
                }
            }
        s = new SoundManager()
        {
            public void initSounds()
            {
                sounds.add(new Sound("BGLoop2", Sound.getURL("/BGLoop2.wav")));
                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
            }
        };
        
        s.loopSound("BGLoop3");    
    }
    
    //loading level 2 Snake level
    private void loadLevelSnake(BufferedImage image)
    {        
         //Loads the Sprite Sheet
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");
            
        //Creats new sprite sheet and Grabs Images From
        //Sprite Sheet to Create the Floor Texture
        
        // treeSS = loader.loadImage("/Tree.png");
        // tss = new TreeSpriteSheet(treeSS);
 
        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(2, 9, 32, 32);
        
        int w = image.getWidth();
        int h = image.getHeight();
        
            for(int xx = 0; xx < w; xx++)
            {
                for(int yy = 0; yy < h; yy++)
                {
                    int pixel = image.getRGB(xx, yy);           // Colors where the objects in the stage loads
                        int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                                int blue = (pixel) & 0xff;
  
                if(red == 255)
                    handler.addObject(new BlockSnake(xx*32, yy*32, ID.Block, ss));
                    
                if(blue == 255 && green == 0 && red == 0)
                    handler.addObject(new MindPlayer(xx*32, yy*32, ID.Player, handler, this, ss));
               
                if(green == 255 && blue == 0)
                    handler.addObject(new Snake(xx*32, yy*32, ID.Enemy, handler, ss, this));
                
                if(blue == 255 & green == 255 && red == 0)
                    handler.addObject(new CrateSnake(xx*32, yy*32, ID.SnakeCrate, ss));
                
                 if(red == 255 && green == 192 && blue == 203)
                    handler.addObject(new Tree(xx*64, yy*64, ID.Tree, ss));
                }
            }
            
        //if loading level before level 1
        //uncomment code below or it will give you an error
        /*    
        s = new SoundManager()
        {
            public void initSounds()
            {
                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
            }
        };
        */
        s.stopSound("BGLoop3");
        s.loopSound("BGLoop3");
            
    }
    
    //loading level 3 Bug level
    private void loadLevelBugs(BufferedImage image)
    { 
    	 //loads the sprite sheet Bugs
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");

    	 	ss = new SpriteSheet(sprite_sheet);      
                floor = ss.grabImage(2, 10, 32, 32);
        
        int w = image.getWidth();
        int h = image.getHeight();
        
            for(int xx = 0; xx < w; xx++)
            {
                for(int yy = 0; yy < h; yy++)
                {
                    int pixel = image.getRGB(xx, yy);
                        int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                                int blue = (pixel) & 0xff;
                        
                if(red == 255 && green == 0 && blue == 0)
                    handler.addObject(new BlockBug(xx*32, yy*32, ID.Block, ss));
                    
                if(blue == 255 && green == 0 && red == 0)
                    handler.addObject(new MindPlayer(xx*32, yy*32, ID.Player, handler, this, ss));
                
                if(blue == 255 & green == 255 && red == 0)
                    handler.addObject(new CrateBug(xx*32, yy*32, ID.BugCrate, ss));
                    
                if(green == 255 && blue == 0 && red == 0)
                    handler.addObject(new Bugs(xx*32, yy*32, ID.Bug, handler, ss, this));              
               
                if(red == 255 && green == 255 & blue == 255)
                   handler.addObject(new Boss(xx*96, yy*96, ID.Boss, handler, ss, this));

                }
            }
            
        //if loading level before level 1
        //uncomment code below or it will give you an error
        /*
        s = new SoundManager()
        {
            public void initSounds()
            {
                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
            }
        };
        */
        s.stopSound("BGLoop3");
        s.loopSound("BGLoop3");
        
            
    }
    
    //loading Arena Level level
    private void loadArena (BufferedImage image)
    { 
        //Loads the Sprite Sheet
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");
            
        //Creats new sprite sheet and Grabs Images From
        //Sprite Sheet to Create the Floor Texture
        ss = new SpriteSheet(sprite_sheet);
        floor = ss.grabImage(2, 7, 32, 32);
        
       
        //Takes the Level Image and locates specific Pixels by their RGB #'s
        //To create Objects, Players, Enemies, Walls, etc
        int w = image.getWidth();
        int h = image.getHeight();
        
            for(int xx = 0; xx < w; xx++)
            {
                for(int yy = 0; yy < h; yy++)
                {
                    int pixel = image.getRGB(xx, yy);
                        int red = (pixel >> 16) & 0xff;
                            int green = (pixel >> 8) & 0xff;
                                int blue = (pixel) & 0xff;
                        
                if(red == 255 && green == 0 && blue == 0)
                    handler.addObject(new BlockSpider(xx*32, yy*32, ID.Block, ss));
                    if(red == 0 && green == 255 && blue == 0)
                        handler.addObject(new BlockSnake(xx*32, yy*32, ID.Block, ss));    
                        if(red == 0 && green == 255 && blue == 255)
                            handler.addObject(new BlockBug(xx*32, yy*32, ID.Block, ss));
                            if(blue == 255 && green == 0 && red == 0)
                                handler.addObject(new MindPlayer(xx*32, yy*32, ID.Player, handler, this, ss));
                }
            }
            
        //if loading level before level 1
        //uncomment code below or it will give you an error
        /*
        s = new SoundManager()
        {
            public void initSounds()
            {
                sounds.add(new Sound("BGLoop2", Sound.getURL("/BGLoop2.wav")));
                sounds.add(new Sound("BGLoop3", Sound.getURL("/BGLoop3.wav")));
            }
        };
          */  
        s.stopSound("BGLoop3");
        s.loopSound("BGLoop2");
    }
    
    
    //switchs level
    public void switchLevel()
    {
        handler.clearlevel();          
        
        switch(this.LEVEL)
        {
            case 1:
                loadLevelSnake(level2);
                        break;      
            case 2:
                loadLevelBugs(level3);
                
                        break;
            case 3:
                loadArena(arena);
                    break;
            case 4:
                System.exit(0);
                    break;
        }
        
        this.LEVEL++;

    }
    
    //gets level
    public int getLevel()
    {
        return LEVEL;
    }
    
    //sets level
    public void setLevel(int lvl)
    {
        LEVEL = lvl;
    }
    
        //gets wave
        public int getWave()
        {
            return WAVE;
        }

        //sets wave
        public void setWave(int wave)
        {
            this.WAVE = wave;
        }
        
        public int getTimer()
        {
            return timer;
        }

        //sets wave
        public void setTimer(int t)
        {
            this.timer = t;
        }
    
        //sets spider enemy count
    public void setSpiderCount(int spider_count)
    {
        spiderCount = spider_count;
    }
    
    //gets spider enemy count
    public int getSpiderCount()
    {
        return spiderCount;
    }
    
    //sets spider boss count
    public void setSpiderBossCount(int spider_Boss_count)
    {
        spiderBossCount = spider_Boss_count;
    }
    
    //gets spider boss count
    public int getSpiderBossCount()
    {
        return spiderBossCount;
    }
    
        //sets snake enemy count
        public void setSnake_count(int snake_count) 
        {
            snakeCount = snake_count;
        }
        
        //gets snake enemy count
        public int getSnake_count() 
        {
            return snakeCount;
        }
        
                //sets snake boss count
        public void setSnakeBoss_count(int snakeboss_count) 
        {
            snakeBossCount = snakeboss_count;
        }
        
        //gets snake boss count
        public int getSnakeBoss_count() 
        {
            return snakeBossCount;
        }
            
            //sets bug enemy count
            public void setBug_count(int bug_count)
            {
                bugCount = bug_count;
            }
            
            //gets bug enemy count
            public int getBug_count()
            {
                return bugCount;
            }
                
                //sets Boss enemy count
                public void setBoss_count(int boss_count)
                {
                    bossCount = boss_count;
                }
                
                //gets boss enemy count
                public int getBoss_count()
                {
                    return bossCount;
                }  
                
   //sets spider enemy kill count
   public void setSpiderKillCount(int spider_Kill_count)
    {
        spiderKillCount = spider_Kill_count;
    }
    
    //gets spider enemy kill count
    public int getSpiderKillCount()
    {
        return spiderKillCount;
    }
    
    //sets spider boss kill count
    public void setSpiderBossKillCount(int spider_Boss_count)
    {
        spiderBossKillCount = spider_Boss_count;
    }
    
    //gets spider boss kill count
    public int getSpiderBossKillCount()
    {
        return spiderBossKillCount;
    }
    
        //sets snake kill count
        public void setSnakeKillCount(int snakekillcount) 
        {
            snakeKillCount = snakekillcount;
        }
        
        //gets snake kill count
        public int getSnakeKillCount() 
        {
            return snakeKillCount;
        }
        
        //sets snake boss kill count
        public void setSnakeBossKillCount(int snakebosskillcount) 
        {
            snakeBossKillCount = snakebosskillcount;
        }
        
        //gets snake boss kill count
        public int getSnakeBossKillCount() 
        {
            return snakeBossKillCount;
        }
            
            //sets bug kill count
            public void setBugKillCount(int bugkillcount)
            {
                bugKillCount = bugkillcount;
            }
            
            //gets bug kill count
            public int getBugKillCount()
            {
                return bugKillCount;
            }
                
                //sets Boss kill count
                public void setBossKillCount(int bosskillcount)
                {
                    bossKillCount = bosskillcount;
                }
                
                //gets boss kill count
                public int getBossKillCount()
                {
                    return bossKillCount;
                }
    
                
    public void setHP(int hp)
    {
        hP = hp;
    }
    
    //gets enemy coount
    public int getHP()
    {
        return hP;
    }
    
    // if x > max width of map, return it to the room width var
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else 
            return var;
    }
    
    private void close()
    {
        //this.dispose();
    }
    /*
    public static void main(String[] args) 
    {
        //Loads a new Game
        new Game();
    }
    */
}