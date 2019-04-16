package game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class GameObject 
{
    protected int x, y;
    protected float velX = 0, velY = 0;
    protected int SX, SY;
    protected ID id;
    protected Handler handler;
    protected SpriteSheet ss;
    
        public GameObject (int x, int y, ID id, SpriteSheet ss)
        {
            this.x = x;
            this.y = y;    
            this.id = id;
            this.ss = ss;
        }
        
        public abstract void tick();
        public abstract void render (Graphics g);
        public abstract Rectangle getBounds();
        
        public ID getid() 
        {
            return id;
        }
    
        public void setid(ID id)
        {
            this.id = id;
        }
        
            public int getX() 
            {
                return x;
            }
    
            public void setX(int x)
            {
                this.x = x;
            }
            
                public int getY() 
                {
                    return y;
                }
    
                public void setY(int y)
                {
                    this.y = y;
                }
            
                    public float getVelX()
                    {
                        return velX;
                    }
                    
                    public void setVelX(float velX)
                    {
                        this.velX = velX;
                    }
                    
                    
                        public float getVelY()
                        {
                            return velY;
                        }
                    
                        public void setVelY(float velY)
                        {
                            this.velY = velY;
                        }    
                                            
                            public float getSX()
                            {
                                return SX;
                            }

                            public void setSX(int SX)
                            {
                                this.SX = SX;
                            }
                        
                                public float getSY()
                                {
                                    return SY;
                                }

                                public void setSY(int SY)
                                {
                                    this.SY = SY;
                                }
}