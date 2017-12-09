package main;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MapGameState extends BasicGameState {
	public static final int ID = 2;
	public static GameContainer container;
	public static String name1 = "0";
	public static String name2 = "0";
	private Map map=new Map();
	private Joueur joueur = new Joueur(map,name1); 
	private Joueur joueur2 = new Joueur(map,name2);
	private int[] respawnX= {100,100,512,512,900,900}; //tableau des position de respawn
	private int[] respawnY= {200,600,480,100,200,600};

@Override
   public void init(GameContainer container, StateBasedGame game) throws SlickException {
   	this.container = container;
   	this.map.init();
   	this.joueur.init();
   	this.joueur2.init(); //on initialise le 2eme joueur
   	joueur2.setX(300);
   	joueur2.setY(300);
   	joueur2.setNum(2);
    Music background = new Music("src/ressource/sound/1.ogg");
    background.loop();
    	
    }

@Override
public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
	this.map.render();
	this.joueur.render(g);
	this.joueur2.render(g);  // on va chercher l'affichage des joueurs
	/*if(joueur.getX()<0){
		this.joueur.setX(1020);
	}
    if(joueur.getX()>1020){
		this.joueur.setX(0);
	}*/
}

@Override
public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
	this.joueur.update(delta); //mise a jour des etats des joueurs
	this.joueur2.update(delta);
	dead(joueur2.isLife(),joueur2.getDeathTimer(),2); //on regarde si le joueur et mort
	joueur2.setDeathTimer(joueur2.getDeathTimer()-1); //  diminution du timer de mort
	dead(joueur.isLife(),joueur.getDeathTimer(),1);
	joueur.setDeathTimer(joueur.getDeathTimer()-1);
	

		
}

@Override
public void keyReleased(int key, char c) {
    if (Input.KEY_ESCAPE == key) {
    	container.exit();
    }
    if(key==Input.KEY_UP || key==Input.KEY_DOWN || key==Input.KEY_RIGHT || key==Input.KEY_LEFT) {this.joueur.setvX(0);} // si les keys sont release on passe l'acceleration a 0
    if(key==Input.KEY_Z || key==Input.KEY_Q || key==Input.KEY_S || key==Input.KEY_D) {this.joueur2.setvX(0);}
    switch(key){
    case Input.KEY_UP: 
    	if(joueur.getDirection() == 1) {this.joueur.setvX(-500f);break;}  // tout ce qui suit permet la cumulation des touches (plus ou moins bien)
      	if(joueur.getDirection()==3) { this.joueur.setvX(500f);break;}
      	if(joueur.getDirection()==0) {joueur.setMoving(false);break;}
    case Input.KEY_LEFT: 
    	if(joueur.getDirection()== 1) {this.joueur.setMoving(false);break;}
      	if(joueur.getDirection()==3) { this.joueur.setvX(500f);break;}
    case Input.KEY_DOWN: 
    	if(joueur.getDirection()== 2) {this.joueur.setMoving(false);break;}
      	if(joueur.getDirection()==1) { this.joueur.setvX(-500f);break;}
      	if(joueur.getDirection()==3) { this.joueur.setvX(500f);break;}
    case Input.KEY_RIGHT: 
    	if(joueur.getDirection()== 3) {this.joueur.setMoving(false);break;}
      	if(joueur.getDirection()==1) { this.joueur.setvX(-500f);break;}
    case Input.KEY_Z: 								//haut
    	if(joueur2.getDirection() == 1) {this.joueur2.setvX(-500f);break;}
        if(joueur2.getDirection()==3) { this.joueur2.setvX(500f);break;}
        if(joueur2.getDirection()==0) {joueur2.setMoving(false);break;}
    case Input.KEY_Q: 								//Gauche
    	if(joueur2.getDirection()== 1) {this.joueur2.setMoving(false);break;}
        if(joueur2.getDirection()==3) { this.joueur2.setvX(500f);break;}
          break;
    case Input.KEY_S: 								//bas
    	 if(joueur2.getDirection()== 2) {this.joueur2.setMoving(false);break;}
         if(joueur2.getDirection()==1) { this.joueur2.setvX(-500f);break;}
         if(joueur2.getDirection()==3) { this.joueur2.setvX(500f);break;}
    case Input.KEY_D: 								//droite
    	 if(joueur2.getDirection()== 3) {this.joueur2.setMoving(false);break;}
         if(joueur2.getDirection()==1) { this.joueur2.setvX(-500f);break;}
         
   case Input.KEY_A: 
    	if(joueur2.getLr()==1) {joueur2.setvX(-300f);break;}
    	else{ joueur2.setvX(300f);break;}
    	
   case Input.KEY_M: 
   		if(joueur.getLr()==1) {joueur.setvX(-300f);break;}
   		else{ joueur.setvX(300f);break;}
         
   
     }
  }



@Override
public void keyPressed(int key, char c) {
	
    switch (key) {
    
        case Input.KEY_UP:    this.joueur.setDirection(0); this.joueur.setMoving(true);if(joueur.getJump()>0) {this.joueur.setvY(650f);}joueur.setJump(joueur.getJump()-1); break; //mise a jour des etats des joueurs en fonction des touches pressed
        case Input.KEY_LEFT:  this.joueur.setDirection(1); this.joueur.setMoving(true); this.joueur.setLr(1); this.joueur.setvX(-500f); break;
        case Input.KEY_DOWN:  this.joueur.setDirection(2); this.joueur.setMoving(true); break;
        case Input.KEY_RIGHT: this.joueur.setDirection(3); this.joueur.setMoving(true); this.joueur.setLr(0);this.joueur.setvX(500f); break;
        case Input.KEY_Z:   this.joueur2.setDirection(0); this.joueur2.setMoving(true);if(joueur2.getJump()>0) {this.joueur2.setvY(650f);}joueur2.setJump(joueur2.getJump()-1); break;
        case Input.KEY_Q:  this.joueur2.setDirection(1); this.joueur2.setMoving(true); this.joueur2.setLr(1); this.joueur2.setvX(-500f); break;
        case Input.KEY_S:  this.joueur2.setDirection(2); this.joueur2.setMoving(true); break;
        case Input.KEY_D: this.joueur2.setDirection(3); this.joueur2.setMoving(true); this.joueur2.setLr(0); this.joueur2.setvX(500f); break;
        case Input.KEY_A: joueur2.setAtk(true);if(joueur2.getTimeAtk()<-300) {joueur2.setTimeAtk(100);}hitStrike(joueur2.getX(),joueur2.getY(),2,joueur2.gethHit(),joueur2.getwHit(),joueur2.getLr());break; 
        case Input.KEY_M: joueur.setAtk(true);if(joueur.getTimeAtk()<-300) {joueur.setTimeAtk(100);}hitStrike(joueur.getX(),joueur.getY(),1,joueur.gethHit(),joueur.getwHit(),joueur.getLr());break;
   }
    
    
}

@Override
public int getID() {
	return ID;
}


public void hitStrike(float hitX,float hitY,int numJ,float wHit,float hHit, int lr) { //fonction qui regarde si notre attaque touche
	boolean hit=false;
	float foeX,foeY;
	switch(numJ){ // on regade quelle joueur a attaqué
		case(1):
			foeX=joueur2.getX();
			foeY=joueur2.getY(); //recupere la position de l'autre joueur
			if(lr==0) { //atk  direction droite
				hitX=hitX; // points de depart de notre hitbox
				hitY=hitY-40;
				for(int i=0;i<hHit;i++) {     //calcul de la hitbox de notre arme.
		    		 for (int j=0;j<80;j++) {
		    			 if((hitX+j>foeX-30 && hitX+j<foeX)&&(hitY-i<foeY && hitY-i>foeY-70)) { //on regarde si un point de la hitbox de noter arme appartient au rectangle de la hitbox enemie
		    				 hit=true;break;
		    			 }
		    		 }
				}
			}
			if(lr==1) { //atk  direction gauche
				hitX=hitX-30;
				hitY=hitY-40;
				for(int i=0;i<hHit;i++) {
		    		 for (int j=0;j<80;j++) {
		    			if((hitX-j>foeX-30 && hitX-j<foeX)&&(hitY-i<foeY && hitY-i>foeY-70)) {
		    				 hit=true;break;
		    			 }
		    		 }
				}
			}
			
		case(2): //si le joueur2 attaque
			foeX=joueur.getX();
			foeY=joueur.getY();
			if(lr==0) { //atk  direction droite
				hitX=hitX; // points de depart de notre hitbox
				hitY=hitY-40;
				for(int i=0;i<hHit;i++) {     //calcul de la hitbox de notre arme.
		    		 for (int j=0;j<80;j++) {
		    			 if((hitX+j>foeX-30 && hitX+j<foeX)&&(hitY-i<foeY && hitY-i>foeY-70)) { //on regarde si un point de la hitbox de noter arme appartient au rectangle de la hitbox enemie
		    				 hit=true;break;
		    			 }
		    		 }
				}
			}
			if(lr==1) { //atk  direction gauche
				hitX=hitX-30;
				hitY=hitY-40;
				for(int i=0;i<hHit;i++) {
		    		 for (int j=0;j<80;j++) {
		    			if((hitX-j>foeX-30 && hitX-j<foeX)&&(hitY-i<foeY && hitY-i>foeY-70)) {
		    				 hit=true;break;
		    			 }
		    		 }
				}
			}
			
			
			
		}
	if(hit&&numJ==1) {/*joueur2.setX(500);joueur2.setY(500);*/joueur2.setLife(false);joueur2.setDeathTimer(150);} // si l'attauque touche  place la variable de vie a'false' et lance un deathtimer pour le joueur touché
	if(hit&&numJ==2) {/*joueur2.setX(500);joueur2.setY(500);*/joueur.setLife(false);joueur.setDeathTimer(150);}
	}

public void dead(boolean life, int dT,int numJ) { //fonction qui fait reaparaitre le joueur si il set mort
	Random rand = new Random();
	int alea = rand.nextInt(5 - 0 + 1) + 0; // position du respawn sera choisi aleatoirement
	if(life==false && dT<=0 &&numJ==2) { //verification que le joueur et mort sont deathtimer et terminé et quel joueur c'est.
		joueur2.setX(respawnX[alea]);joueur2.setY(respawnY[alea]);//position du respawn
		joueur2.setLife(true); // RESURECTION!!
	}
		
	if(life==false && dT<=0 &&numJ==1) {
		joueur.setX(respawnX[alea]);joueur.setY(respawnY[alea]);
		joueur.setLife(true);
	}
	
  }
}
