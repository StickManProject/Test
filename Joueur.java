package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;


public class Joueur {
	private Map map;
	private float x = 900, y = 200, widthA=30,heightA=68; //position du joueur et taille ==> ce qui permet de calculer ca HITBOX
	private float wHit=40, hHit=30; // taille de la hitbox de l'arme
	private int direction = 0; //direction de notre personnage 0:haut/ 1:gauche /2:bas /3:droite
	private boolean moving = false; // notre personnage bouge ?
	private Animation[] animationsR = new Animation[12]; //tableau des animations droite
	private Animation[] animationsL=new Animation[12];//tableau des animations gauche
	private int lr; //notre personnage regarde a gauche(1) ou droite(0) ?
	private String name;
	private float vY=0; //acceleration verticale
	private float vX=0; // acceleration horizontale
	private float jump=2; // nombre de jump consecutif dans les air
	private boolean atk =false; // on attaque ?
	private int timeAtk=0; // delaie avant la prochaine attaque
	private boolean falling; // noter personnage tombe ?
	private boolean life=true; //IT's ALIVE !
	private int num=1; // numero du joueur 
	private int deathTimer; //delai avant de reaparaitre
	
	
	public Joueur (Map map, String name){
		this.map=map;
		this.name=name;
	}
	
	public Joueur (Map map){
		this.map=map;
	}
	
	public void init() throws SlickException {
    	SpriteSheet spriteSheet = new SpriteSheet("src/ressource/sprites/ninja.png", 50, 78);
    	this.animationsR[0] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[1] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsR[2] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[3] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsR[4] = loadAnimation(spriteSheet, 0, 7, 2); //-D
        this.animationsR[5] = loadAnimation(spriteSheet, 0, 7, 1); //Avancer gauche
        this.animationsR[6] = loadAnimation(spriteSheet, 5, 6, 4); //bas
        this.animationsR[7] = loadAnimation(spriteSheet, 0, 7, 2); // Avancer droite
        this.animationsR[8] = loadAnimation(spriteSheet, 4, 5, 3);
        this.animationsR[9] = loadAnimation(spriteSheet, 6, 7, 4); //-D
        
        this.animationsL[0] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[1] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[2] = loadAnimation(spriteSheet, 0, 1, 1); //-G
    	this.animationsL[3] = loadAnimation(spriteSheet, 0, 1, 2); //-D
    	this.animationsL[4] = loadAnimation(spriteSheet, 0, 7, 1); //-D
        this.animationsL[5] = loadAnimation(spriteSheet, 0, 7, 1); //Avancer gauche
        this.animationsL[6] = loadAnimation(spriteSheet, 1, 2, 4); //bas
        this.animationsL[7] = loadAnimation(spriteSheet, 0, 7, 2); // Avancer droite
        this.animationsL[8] = loadAnimation(spriteSheet, 3, 4, 3);
    	this.animationsL[9] = loadAnimation(spriteSheet, 2, 3, 4); //-D
	}
	
	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) { //on charge nos sprites
        Animation animation = new Animation();
        for (int x = startX; x < endX; x++) {
            animation.addFrame(spriteSheet.getSprite(x, y), 80);
        }
        return animation;
    }
	
	 public void render(Graphics g) throws SlickException {
		 if(atk && timeAtk>0) { //animations quand on attaque
	    		switch(lr) {
	    			case 0: g.drawAnimation(animationsR[8],x-35,y-71);break;
	    			case 1: g.drawAnimation(animationsL[8],x-35,y-71);break;
	    		}
	    	
	    	}
	    	else if(falling) { //animations quand on tombe
	    		switch(lr) {
	    			case 0: g.drawAnimation(animationsR[9],x-35,y-71);break;
	    			case 1: g.drawAnimation(animationsL[9],x-35,y-71);break;
	    		}	
	    	}
	    	
	    	else {	//le reste du temps
		        if(lr==0) { g.drawAnimation(animationsR[direction + (moving ? 4 : 0)], x-35, y-71);}
		    	else if(lr==1) {g.drawAnimation(animationsL[direction + (moving ? 4 : 0)], x-35, y-71);}
	    	}
	        if(this.x<=4) {this.x=1010;}
	        if(this.x>=1012) {this.x=10;}// quand on sort de la map nous teleporte a l'opposé.
	        if(this.y >=765){this.y=40;}
	        if(this.y<=39) {this.y=760;}
	        timeAtk-=1;
	    }
	
	 public void update(int delta) throws SlickException {
		 boolean collision=false;
		 float delta_sec=(delta*0.001f);
    	 float futurX = getFuturX(delta_sec); //prevision de notre prochain deplacement (permet de mettre en place les collisions.)
	     float futurY = getFuturY(delta_sec);
	     
	     /*if(atk && timeAtk>0) {
	    	 float hitX=this.x;
	    	 float hitY=this.y-40;
	    	// boolean a = MapGameState.hitStrike(hitX,hitY,this.num,wHit,hHit,lr);
	    	 
	     }*/
	     
	     collision=map.isCollision(futurX+10,futurY); //reset doublejump si le joueur touche le sol
	     if (collision) {
	    	 this.jump=2;
	     }
	     //collision=false;
	     
	     for (int i=0;i<widthA;i++) {
	    	 collision=map.isCollision(this.x-i,futurY-70); //stop le saut quand le plafond est touché
	    	 if(collision) {
	    		 //falling=true;
	    		 this.vY=0.4f;
	    	 }
	     }
	     //collision=false;
	     
	     for (int i=0;i<widthA;i++) {
	        	for (int j=0;j<heightA;j++) {
	        		collision = map.isCollision(this.x-i,futurY-j); //chute
	        		if(collision==true) {break;}
	        		}
	        	if(collision) {
	        		this.falling=false;
	        		break;
	        	}
	        }
	    if (collision==false) {this.y=futurY;this.falling=true;}
 	 	
	   
	    
    	if ((this.moving)){	
	        for (int i=0;i<widthA;i++) {
	        	for (int j=0;j<heightA;j++) {
	        		collision = map.isCollision(futurX-i, futurY-j);//deplacement en l'air
	        		if(collision) {break;}
	        		}
	        	if(collision) {break;}
	        	}
	        
	        if (collision) {
	            this.moving = false;

	        } 
	        else {
	            this.x = futurX;
	            this.y = futurY;
	        }
	    this.moving=true;	    
        }
        
    	 if ((this.moving)){
     		
 	        for (int i=0;i<widthA;i++) {
 	        	for (int j=0;j<heightA;j++) {
 	        		collision = map.isCollision(futurX-i,this.y-j); //deplacement au sol
 	        		if(collision) {break;}
 	        		}
 	        	if(collision) {break;}
 	        	}
 	        
 	        if (collision) {
 	            this.moving = false;
 	        } 
 	        else {
 	            this.x = futurX;
 	        }
         }
    }
	 
	private float getFuturX(float delta) { //fonction qui nous deplace sur l'axe X
        float futurX = this.x;
        switch (this.direction) {
        case 1: futurX = this.x - 100f * delta; break; // si on va a gauche 
        case 3: futurX = this.x + 100f * delta; break; // si on va a droite
        }
        return futurX+(vX*delta);
    }
	
	private float getFuturY(float delta) { //fonction qui nous deplace sur l'axe Y
		float futurY = this.y;
        switch (this.direction) {
        	case 0: futurY =this.y -vY*delta; break; // si on saute
        	case 2:futurY =this.y +300f*delta; break; // si on tombe
        }
        futurY+=525f*delta-(this.vY*delta); //gravité
        if (this.vY>0) {this.vY-=1.2;} // diminution de l'aceleration du saut avec le temps
        return futurY;
    }
	
	 // toute une ribambelles de getters and setters.
	  public float getX() { return x; }
	  public void setX(float x) { this.x = x; }
	  public float getY() { return y; }
	  public void setY(float y) { this.y = y; }
	  public int getDirection() { return direction; }
	  public void setDirection(int direction) { this.direction = direction; }
	  public boolean isMoving() { return moving; }
	  public void setMoving(boolean moving) { this.moving = moving; }


	public float getvY() {
		return vY;
	}

	public void setvY(float vY) {
		this.vY = vY;
	}

	public float getvX() {
		return vX;
	}

	public void setvX(float vX) {
		this.vX = vX;
	}

	public float getJump() {
		return jump;
	}

	public void setJump(float jump) {
		this.jump = jump;
	}
	  
public boolean isAtk() {
	return atk;
}

public void setAtk(boolean atk) {
	this.atk = atk;
}

public int getTimeAtk() {
	return timeAtk;
}

public void setTimeAtk(int timeAtk) {
	this.timeAtk = timeAtk;
}

public int getLr() {
	return lr;
}

public void setLr(int lr) {
	this.lr = lr;
}

public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

public float getwHit() {
	return wHit;
}

public void setwHit(float wHit) {
	this.wHit = wHit;
}

public float gethHit() {
	return hHit;
}

public void sethHit(float hHit) {
	this.hHit = hHit;
}

public boolean isLife() {
	return life;
}

public void setLife(boolean life) {
	this.life = life;
}

public int getDeathTimer() {
	return deathTimer;
}

public void setDeathTimer(int deathTimer) {
	this.deathTimer = deathTimer;
}




}
