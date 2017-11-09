package main;

import java.awt.Color;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class WindowGame extends BasicGame {

	private GameContainer container=null;
	private TiledMap map;
	private float x = 400, y = 400, widthA=23,heightA=68;;
	private float v = 300, w = 300;
	private int direction = 0;
	private int direction2 = 0;
	private int lr=0;
	private int lr2=0;
	private boolean moving = false;
	private boolean moving2 = false;
	private Animation[] animationsR = new Animation[8];
	private Animation[] animationsL=new Animation[8];
	private Animation[] animations2R = new Animation[8];
	private Animation[] animations2L=new Animation[8];
	
	public WindowGame() {
        super("StickMan:: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
    	this.map = new TiledMap("src/ressource/map/map.tmx");
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
        
        SpriteSheet spriteSheet2 = new SpriteSheet("src/ressource/sprites/ninja.png", 50, 78);
    	this.animations2R[0] = loadAnimation(spriteSheet2, 0, 1, 2); //-D
    	this.animations2R[1] = loadAnimation(spriteSheet2, 0, 1, 1); //-G
    	this.animations2R[2] = loadAnimation(spriteSheet2, 0, 1, 2); //-D
    	this.animations2R[3] = loadAnimation(spriteSheet2, 0, 1, 2); //-D
    	this.animations2R[4] = loadAnimation(spriteSheet2, 6, 7, 4); //-D
        this.animations2R[5] = loadAnimation(spriteSheet2, 0, 7, 1); //Avancer gauche
        this.animations2R[6] = loadAnimation(spriteSheet2, 5, 6, 4); //bas
        this.animations2R[7] = loadAnimation(spriteSheet2, 0, 7, 2); // Avancer droite
        
        this.animations2L[0] = loadAnimation(spriteSheet2, 0, 1, 1); //-G
    	this.animations2L[1] = loadAnimation(spriteSheet2, 0, 1, 1); //-G
    	this.animations2L[2] = loadAnimation(spriteSheet2, 0, 1, 1); //-G
    	this.animations2L[3] = loadAnimation(spriteSheet2, 0, 1, 2); //-D
    	this.animations2L[4] = loadAnimation(spriteSheet2, 2, 3, 4); //-D
        this.animations2L[5] = loadAnimation(spriteSheet2, 0, 7, 1); //Avancer gauche
        this.animations2L[6] = loadAnimation(spriteSheet2, 1, 2, 4); //bas
        this.animations2L[7] = loadAnimation(spriteSheet2, 0, 7, 2); // Avancer droite
    }
   private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 100);
        }
        return animation;
    }
    
    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
        	container.exit();
        }
        switch(key){
        case Input.KEY_UP: 
          if(direction == 0) this.moving = false; 
            break;
        case Input.KEY_LEFT: 
          if(direction == 1) this.moving = false;
            break;
        case Input.KEY_DOWN: 
          if(direction == 2) this.moving = false;
            break;
        case Input.KEY_RIGHT: 
          if(direction == 3) this.moving = false;
            break;
        case Input.KEY_Z: 								//haut
            if(direction2 == 0) this.moving2 = false;
              break;
        case Input.KEY_A: 								//Gauche
            if(direction2 == 1) this.moving2 = false;
              break;
        case Input.KEY_S: 								//bas
            if(direction2 == 2) this.moving2 = false;
              break;
        case Input.KEY_E: 								//droite
            if(direction2 == 3) this.moving2 = false;
              break;
          }
      }

  
    
    @Override
    public void keyPressed(int key, char c) {
    	
        switch (key) {
        
            case Input.KEY_UP:    this.direction = 0; this.moving = true; break;
            case Input.KEY_LEFT:  this.direction = 1; this.moving = true; this.lr=1; break;
            case Input.KEY_DOWN:  this.direction = 2; this.moving = true; break;
            case Input.KEY_RIGHT: this.direction = 3; this.moving = true; this.lr=0; break;
            case Input.KEY_Z:   this.direction2 = 0; this.moving2 = true; break;
            case Input.KEY_A:  this.direction2 = 1; this.moving2 = true; this.lr2=1; break;
            case Input.KEY_S:  this.direction2 = 2; this.moving2 = true; break;
            case Input.KEY_E: this.direction2 = 3; this.moving2 = true; this.lr2=0; break;
        }
        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render(0, 0, 0);
    	if(lr2==0) { g.drawAnimation(animations2R[direction2 + (moving2 ? 4 : 0)], v-44, w-76);}
    	else if(lr2==1) {g.drawAnimation(animations2L[direction2 + (moving2 ? 4 : 0)], v-44, w-76);}
        if(lr==0) { g.drawAnimation(animationsR[direction + (moving ? 4 : 0)], x-44, y-76);}
    	else if(lr==1) {g.drawAnimation(animationsL[direction + (moving ? 4 : 0)], x-44, y-76);}
        if(x<0){
			x=1020;
		}
        if(v<0){
        	v=1020;
        }
        if(x>1020){
			x=0;
		}
        if(v>1020){
        	v=0;
        }
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	 boolean collision=false;
    	 boolean collision2=false;
    	 float futurX = getFuturX(delta);
	     float futurY = getFuturY(delta);
	     float futurX2 = getFuturX2(delta);
	     float futurY2 = getFuturY2(delta);
	     float Gravity=getGravity(delta);
	     float Gravity2=getGravity2(delta);
	        for (int i=0;i<widthA;i++) {
	        	for (int j=0;j<heightA;j++) {
	        		collision = isCollision(this.x, Gravity-j);
	        		collision2= isCollision(this.v, Gravity2-j);
	        		if(collision==true) {break;}
	        		if(collision2==true) {break;}
	        		}
	        	if(collision==true) {break;}
	        	if(collision2==true) {break;}
	        	}
    	 if(collision==false) {
    		 this.y = Gravity;
    	 }
    	 if(collision2==false) {
    		 this.w = Gravity2;
    	 }
    	if (this.moving) {
    		for (int i=0;i<widthA;i++) {
	        	for (int j=0;j<heightA;j++) {
	        		collision = isCollision(futurX-i, futurY-j);
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
		if (this.moving2){
			for (int i=0;i<widthA;i++) {
	        	for (int j=0;j<heightA;j++) {
	        		collision2 = isCollision(futurX2-i, futurY2-j);
	        		if(collision2==true) {break;}
	        		}
	        	if(collision2==true) {break;}
	        	}
	        
	        if (collision2) {
	            this.moving2 = false;
	        } 
	        else {
	            this.v = futurX2;
	            this.w = futurY2;
	        }
    			 	
    		}
    			/*switch (this.direction2){
				 	case 0: this.w -= .2f * delta; break;
		            case 1: this.v -= .2f * delta; break;
		            case 2: this.w += .2f * delta; break;
		            case 3: this.v += .2f * delta; break;
    			}*/
    		
    }
    
    private boolean isCollision(float x, float y) {
    	boolean collision=false;
        int tileW = this.map.getTileWidth();
        int tileH = this.map.getTileHeight();
        int logicLayer = this.map.getLayerIndex("Logic");
        Image tile = this.map.getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
        collision = tile != null;
        if (collision) {
        	org.newdawn.slick.Color color = tile.getColor((int) x % tileW, (int) y % tileH);
            collision = color.getAlpha() > 0;
        }
        return collision;
    }

    private float getFuturX(int delta) {
        float futurX = this.x;
        switch (this.direction) {
        case 1: futurX = this.x - .4f * delta; break;
        case 3: futurX = this.x + .4f * delta; break;
        }
        return futurX;
    }
    
    private float getFuturX2(int delta) {
        float futurX = this.v;
        switch (this.direction2) {
        case 1: futurX = this.v - .4f * delta; break;
        case 3: futurX = this.v + .4f * delta; break;
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
    
    private float getFuturY2(int delta) {
        float futurY = this.w;
        switch (this.direction2) {
        case 0: futurY = this.w - .4f * delta; break;
        case 2: futurY = this.w + .4f * delta; break;
        }
        return futurY;
    }
    
    private float getGravity(int delta) {
    	float Gravity = this.y;
    	Gravity=this.y+.6f*delta;
    	return Gravity;
    }
    
    private float getGravity2(int delta) {
    	float Gravity = this.w;
    	Gravity=this.w+.6f*delta;
    	return Gravity;
    }
    

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 1024, 768, false).start();
    }
    

}