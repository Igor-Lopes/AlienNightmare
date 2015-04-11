package com.mygdx.alienswarm.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Aliens {
	public float targetX;
	public float targetY;
	public float moveX;
	public float moveY;
	public float angle;
	public float life;
	public float speed;
	public Sprite alien;
	public Texture item_texture;
	public Sprite item;
	public int item_type;
	public boolean canMove;
	public Aliens(float l, float s, float tX, float tY,float aX, float aY,Sprite b) {
		canMove = true;
		speed = s;
		life = l;
		alien = b;
		targetX = tX;
		targetY = tY;
		alien.setX(aX);
		alien.setY(aY);
		int random = MathUtils.random(0,12);
		if(random == 0){
			item_texture = new Texture(Gdx.files.internal("magazine.png"));
			item = new Sprite(item_texture);
		}
		if(random == 10){
			item_texture = new Texture(Gdx.files.internal("energy.png"));
			item = new Sprite(item_texture);
		}
		if(random == 2){
			item_texture = new Texture(Gdx.files.internal("healthKit.png"));
			item = new Sprite(item_texture);
		}
		
		if(random == 4){
			item_texture = new Texture(Gdx.files.internal("energy.png"));
			item = new Sprite(item_texture);
		}
		if(random == 9){
			item_texture = new Texture(Gdx.files.internal("shield.png"));
			item = new Sprite(item_texture);
		}
		item_type = random;
	}
	public int getItemType(){
		return item_type;
	}
	public Sprite getItem(){
		return item;
	}
	public void setSpeed(float s){
		speed = s;
	}
	public float getSpeed(){
		return speed;
	}
	public void setCanMove(boolean b){
		canMove = b;
	}
	public void setLife(float l){
		life = l;
	}
	public float getLife(){
		return life;
	}
	public Sprite getSprite(){
		return alien;
	}
	public float getTargetX(){
		return targetX;
	}
	public float getTargetY(){
		return targetY;
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
