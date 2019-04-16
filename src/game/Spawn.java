package game;

import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JOptionPane;

public class Spawn 
{
    Game game;
    Handler handler;
    SpriteSheet ss;
    private BufferedImage sprite_sheet = null;
    
    public int timeKeep = 0;
    private Random r = new Random();
    
    
        
    public Spawn(Handler handler, Game game)
    {
        this.handler = handler;
        this.game = game;
        
        BufferedImageLoader loader = new BufferedImageLoader();
        sprite_sheet = loader.loadImage("/MOTMSS.png");

        ss = new SpriteSheet(sprite_sheet);
        
        
    }
    
    public void tick()
    {  
        if(game.getSpiderKillCount() == 5 && game.getSpiderBossKillCount() == 8 &&
            game.getSnakeKillCount() == 5 && game.getSnakeBossKillCount() == 8 && 
                game.getBugKillCount() == 5 && game.getBossKillCount() == 8)
                {
                    timeKeep++;
                        if(timeKeep >= 20)
                        {
                            JOptionPane.showMessageDialog(null, "Congragulations you have conquered fear itself!");
                            System.exit(0);
                        }
                }
        
        if(game.LEVEL == 4 && game.getWave() != 10)
        {
            timeKeep++;
        }
        
        if(timeKeep >= 200)
        {
            timeKeep = 0;
                int wv = game.getWave();
                    wv += 1;
                        game.setWave(wv);
                        
                        //handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                        //handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                        //handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                        //handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                        //handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));              
                        //handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                        
                        //handler.addObject(new CrateSpider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderCrate, ss));
                        //handler.addObject(new CrateSnake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeCrate, ss));
                        //handler.addObject(new CrateBug(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.BugCrate, ss));
                        
                        if(game.getWave() == 1)
                        {
                             handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                             handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                        }
                        
                        if(game.getWave() == 2)
                        {
                            handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                            handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game)); 
                        }
                        
                        if(game.getWave() == 3)
                        {
                             handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                             handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                        }
                        
                            if(game.getWave() == 4)
                            {
                                handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                handler.addObject(new CrateSpider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderCrate, ss));

                                
                            }
                            if(game.getWave() == 5)
                            {
                                handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                handler.addObject(new CrateSnake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeCrate, ss));
                            }
                            
                            if(game.getWave() == 6)
                            {
                                handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                handler.addObject(new CrateBug(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.BugCrate, ss));
                            }
                            
                                if(game.getWave() == 7)
                                {
                                    handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                    handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                    handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                    handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                    handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                    
                                }
                                if(game.getWave() == 8)
                                {
                                    handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                    handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                    handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                    handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                    handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game)); 
                                }
                                if(game.getWave() == 9)
                                {
                                    handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                    handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                    handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                    handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                    handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                }
                                
                                    if(game.getWave() == 10)
                                    {
                                      handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));
                                      handler.addObject(new SpiderBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SpiderBoss, handler, ss, game));                                    
                                      handler.addObject(new Spider(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                        handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                        handler.addObject(new SnakeBoss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.SnakeBoss, handler, ss, game));
                                        handler.addObject(new Snake(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                            handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                            handler.addObject(new Boss(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Boss, handler, ss, game));
                                            handler.addObject(new Bugs(r.nextInt(game.WIDTH)+ 200, r.nextInt(game.HIEGHT) + 200, ID.Enemy, handler, ss, game));
                                    }
                                    
                                  
        }
    }
    
    
    
}
