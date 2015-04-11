package com.mygdx.alienswarm.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullets {
	public int mouseX;
	public int mouseY;
	public float moveX;
	public float moveY;
	public float angle;
	public Sprite bullet;
	public Bullets(int mX, int mY,float bX, float bY,Sprite b) {
		bullet = b;
		mouseX = mX;
		mouseY = mY;
		bullet.setX(bX);
		bullet.setY(bY);
	}
	public Sprite getSprite(){
		return bullet;
	}
	public int getMouseX(){
		return mouseX;
	}
	public int getMouseY(){
		return mouseY;
	}
	public void setMoveX(float mX){
		moveX = mX;
	}
	public void setMoveY(float mY){
		moveY = mY;
	}
	public float getMoveX(){
		return moveX;
	}
	public float getMoveY(){
		return moveY;
	}
}
