package main;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {
	private TiledMap map;
	

	public void init() throws SlickException {
		this.map =new TiledMap("src/ressource/map/map.tmx");
	}


	public void render() throws SlickException {
		this.map.render(0, 0,0);
	}
  
	public boolean isCollision(float x, float y) {
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

}