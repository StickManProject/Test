package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class WindowGame extends BasicGame {

	private GameContainer container;
	private Map map=new Map();
	private Joueur joueur = new Joueur(map);
	private Joueur joueur2 = new Joueur(map);

	
	public WindowGame() {
        super("StickMan:: WindowGame");
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    	this.container = container;
    	this.map.init();
    	this.joueur.init();
    	this.joueur2.init();
    	joueur2.setX(300);
    	joueur2.setY(300);
    	
    }
    
    @Override
    public void keyReleased(int key, char c) {
    	this.joueur.setMoving(false);
    	this.joueur2.setMoving(false);
        if (Input.KEY_ESCAPE == key) {
        	container.exit();
        }
        switch(key){
        case Input.KEY_UP: 
          if(joueur.getDirection() == 0) this.joueur.setMoving(false); 
            break;
        case Input.KEY_LEFT: 
          if(joueur.getDirection()== 1) this.joueur.setMoving(false);
            break;
        case Input.KEY_DOWN: 
          if(joueur.getDirection()== 2) this.joueur.setMoving(false);
            break;
        case Input.KEY_RIGHT: 
          if(joueur.getDirection()== 3) this.joueur.setMoving(false);
            break;
        case Input.KEY_Z: 								//haut
            if(joueur2.getDirection() == 0) this.joueur.setMoving(false);
              break;
        case Input.KEY_A: 								//Gauche
        	if(joueur2.getDirection() == 1) this.joueur.setMoving(false);
              break;
        case Input.KEY_S: 								//bas
        	if(joueur2.getDirection() == 2) this.joueur.setMoving(false);
              break;
        case Input.KEY_E: 								//droite
        	if(joueur2.getDirection() == 3) this.joueur.setMoving(false);
              break;
         }
      }

  
    
    @Override
    public void keyPressed(int key, char c) {
    	
        switch (key) {
        
            case Input.KEY_UP:    this.joueur.setDirection(0); this.joueur.setMoving(true); break;
            case Input.KEY_LEFT:  this.joueur.setDirection(1); this.joueur.setMoving(true); this.joueur.setlr(1); break;
            case Input.KEY_DOWN:  this.joueur.setDirection(2); this.joueur.setMoving(true); break;
            case Input.KEY_RIGHT: this.joueur.setDirection(3); this.joueur.setMoving(true); this.joueur.setlr(0); break;
            case Input.KEY_Z:   this.joueur2.setDirection(0); this.joueur2.setMoving(true); break;
            case Input.KEY_A:  this.joueur2.setDirection(1); this.joueur2.setMoving(true); this.joueur2.setlr(1); break;
            case Input.KEY_S:  this.joueur2.setDirection(2); this.joueur2.setMoving(true); break;
            case Input.KEY_E: this.joueur2.setDirection(3); this.joueur2.setMoving(true); this.joueur2.setlr(0); break;
       }
        
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	this.map.render();
    	this.joueur.render(g);
    	this.joueur2.render(g);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    	this.joueur.update(delta);
    	this.joueur2.update(delta);
    		
    }
    

    

    public static void main(String[] args) throws SlickException {
        new AppGameContainer(new WindowGame(), 1024, 768, false).start();
    }
    

}