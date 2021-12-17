package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Scene {
	private Background background;
	private ArrayList<GameObject> entities;
	private PlayerCharacter player;
	
	
	public Scene(ArrayList<GameObject> entities, String backgroundPath) {
		this.background = new Background(0,backgroundPath);
		this.entities = entities;
		//addEntity(5,100);
		addEntity(50,100,"block");
		addEntity(200,20,"block");
		addEntity(5,200,"block");
		addEntity(100,22,"block");
		addEntity(player = new PlayerCharacter(100,100,"player"));
		
	}
	
	private void addEntity(PlayerCharacter playerCharacter) {
		entities.add(playerCharacter.getGameObject());
		
	}

	public PlayerCharacter getPlayer() {
		return this.player;
	}
	
	public void checkCollisions() {
		for(int i =0; i<entities.size(); i++) {
			for(int j=(i+1); j<entities.size(); j++) {
				entities.get(i).checkCollision(entities.get(j));
			}
		}
	}
	public Background getBackground() {
		return background;
	} 
	public void addEntity(int x, int y,String name) {
		entities.add(new GameObject(x,y,name));
	}
	
	public void draw(Component c, Graphics g) {
		background.draw(c,g);
		for(GameObject e: entities) {
			e.draw(c,g);
		}
		player.getGameObject().draw(c,g);
		
	}
	public void updatePositions() {
		for(int i =0; i<entities.size(); i++) {
			entities.get(i).move(entities.get(i).xVelocity,entities.get(i).yVelocity);
		}
		
		if(player.getGameObject().y<200) {
			player.getGameObject().yVelocity+=.1;
		}else {
			player.getGameObject().y=200;
		}
		;
	}
}
