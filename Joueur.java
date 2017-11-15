package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Joueur {
	private Map map;
	private float x = 400, y = 400, widthA=23,heightA=68;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animationsR = new Animation[8];
	private Animation[] animationsL=new Animation[8];
	private int lr=0;
	
	public Joueur (Map map){
		this.map=map;
	}
	
	public void init() throws SlickException {
    	SpriteSheet spriteSheet = new SpriteSheet("src/ressource/sprites/ninja.png", 50, 78);
    	this.animationsR[0] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[1] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsR[2] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[3] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[4] = loadAnimation(spriteSheet, 6, 7, 4); //-D
        this.animationsR[5] = loadAnimation(spriteSheet, 0, 7, 1); //Avancer gauche
        this.animationsR[6] = loadAnimation(spriteSheet, 5, 6, 4); //bas
        this.animationsR[7] = loadAnimation(spriteSheet, 0, 7, 2); // Avancer droite
        
        this.animationsL[0] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[1] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[2] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[3] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsL[4] = loadAnimation(spriteSheet, 2, 3, 4); //-D
        this.animationsL[5] = loadAnimation(spriteSheet, 0, 7, 1); //Avancer gauche
        this.animationsL[6] = loadAnimation(spriteSheet, 1, 2, 4); //bas
        this.animationsL[7] = loadAnimation(spriteSheet, 0, 7, 2); // Avancer droite
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
	
	 public void render(Graphics g) throws SlickException {
	        if(lr==0) { g.drawAnimation(animationsR[direction + (moving ? 4 : 0)], x-44, y-76);}
	    	else if(lr==1) {g.drawAnimation(animationsL[direction + (moving ? 4 : 0)], x-44, y-76);}
	        if(x<0){
				x=1020;
			}
	        if(x>1020){
				x=0;
			}
	    }
	
	 public void update(int delta) throws SlickException {
	    	 float futurX = getFuturX(delta);
		     float futurY = getFuturY(delta);
		     boolean collision=this.map.isCollision(futurX, futurY);
		     float Gravity=getGravity(delta);
		        for (int i=0;i<widthA;i++) {
		        	for (int j=0;j<heightA;j++) {
		        		collision = this.map.isCollision(this.x-i, Gravity-j);
		        		if(collision==true) {break;}
		        	}
		        	if(collision==true) {break;}
		        }
	    	 if(collision==false) {
	    		 this.y = Gravity;
	    	 }
	    	if (this.moving) {
	    		for (int i=0;i<widthA;i++) {
		        	for (int j=0;j<heightA;j++) {
		        		collision = this.map.isCollision(futurX-i, futurY-j);
		        		if(collision==true) {break;}
		        		}
		        	if(collision==true) {break;}
		        	}
		        if (collision) {
		            this.moving = false;
		        } 
		        else {
		            this.x = futurX;
		            this.y = futurY;
		        }
	    	}
	    }
	 
	private float getFuturX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
        case 1: futurX = this.x - .4f * delta; break;
        case 3: futurX = this.x + .4f * delta; break;
        }
        return futurX;
    }
	
	private float getFuturY(int delta) {
        float futurY = this.y;
        switch (this.direction) {
        case 0: futurY = this.y - .4f * delta; break;
        case 2: futurY = this.y + .4f * delta; break;
        }
        return futurY;
    }
	
	private float getGravity(int delta) {
    	float Gravity = this.y;
    	Gravity=this.y+.6f*delta;
    	return Gravity;
    }
	
	  public float getX() { return x; }
	  public void setX(float x) { this.x = x; }
	  public float getY() { return y; }
	  public void setY(float y) { this.y = y; }
	  public int getDirection() { return direction; }
	  public void setDirection(int direction) { this.direction = direction; }
	  public boolean isMoving() { return moving; }
	  public void setMoving(boolean moving) { this.moving = moving; }
	  public int getlr(){return lr;}
	  public void setlr(int lr){this.lr=lr;}
}
