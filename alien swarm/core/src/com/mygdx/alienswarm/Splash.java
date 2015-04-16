package com.mygdx.alienswarm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.alienswarm.Entities.Aliens;
import com.mygdx.alienswarm.Entities.Bullets;

public class Splash implements Screen {
	// TEXTURES
	// -----------------------------------------------------------------------------
	private Texture gameover_texture = new Texture(
			Gdx.files.internal("GameOver.png"));
	private Texture complete_texture = new Texture(
			Gdx.files.internal("complete.png"));
	private Texture alien_blood = new Texture(
			Gdx.files.internal("alien_blood.png"));
	private Texture texture = new Texture(Gdx.files.internal("bkg_level1.png"));
	private Texture alien_texture = new Texture(Gdx.files.internal("alien.png"));
	Texture bullet_texture = new Texture(Gdx.files.internal("Bullet.png"));
	private Texture life_texture = new Texture(
			Gdx.files.internal("healthKit.png"));
	private Texture spacemarine_texture = new Texture(
			Gdx.files.internal("spacemarine.png"));
	private Texture gun_texture = new Texture(
			Gdx.files.internal("marine_gun.png"));
	private Texture energy_texture = new Texture(
			Gdx.files.internal("energy.png"));
	private Texture magazine_texture = new Texture(
			Gdx.files.internal("magazine.png"));
	private Texture shield_texture = new Texture(
			Gdx.files.internal("shield.png"));
	private Texture blood_texture = new Texture(
			Gdx.files.internal("marine_blood.png"));
	private Texture gate_texture = new Texture(
			Gdx.files.internal("gate.png"));

	// SPRITES
	// ------------------------------------------------------------------------------
	private Sprite shield = new Sprite(shield_texture);
	private Sprite gun = new Sprite(gun_texture);
	private Sprite gameover = new Sprite(gameover_texture);
	private Sprite complete = new Sprite(complete_texture);
	private Sprite life = new Sprite(life_texture);
	private Sprite alien = new Sprite(alien_texture);
	private Sprite magazine = new Sprite(magazine_texture);
	private Sprite energy = new Sprite(energy_texture);
	private Sprite splashImage = new Sprite (texture);
	private Stage stage = new Stage();
	private SpriteBatch batch = new SpriteBatch();
	private Sprite spacemarine = new Sprite(spacemarine_texture);
	private World world = new World(new Vector2(0, 0), true);
	private Body Marinebody;
	private BodyDef MarinebodyDef = new BodyDef();
	private PolygonShape Marineshape = new PolygonShape();
	private FixtureDef MarinefixtureDef = new FixtureDef();
	private Body Alienbody;
	private BodyDef AlienbodyDef = new BodyDef();
	private PolygonShape Alienshape = new PolygonShape();
	private FixtureDef AlienfixtureDef = new FixtureDef();
	Vector2 vAlien = new Vector2();
	private Array<Bullets> bullets;
	private Array<Aliens> aliens;
	private Array<Sprite> blood_trail;
	private Array<Aliens> dropItem;
	private Array <Sprite>  gates;
	private Music soundtrack = Gdx.audio.newMusic(Gdx.files
			.internal("themeMusic.mp3"));
	private Music gunShot = Gdx.audio.newMusic(Gdx.files
			.internal("gunShot.mp3"));
	private Music hurt = Gdx.audio.newMusic(Gdx.files.internal("hurt.wav"));
	private Music alienGrunt = Gdx.audio.newMusic(Gdx.files
			.internal("grunt.wav"));
	private Music emptyGun = Gdx.audio.newMusic(Gdx.files
			.internal("gunClick.wav"));
	private Music gunReload = Gdx.audio.newMusic(Gdx.files
			.internal("gunReload.mp3"));
	private Music pop = Gdx.audio.newMusic(Gdx.files.internal("popItem.mp3"));
	private Music gateM = Gdx.audio.newMusic(Gdx.files.internal("gate.wav"));
	// Fonts--------------------------------------------------------------------------------
	private BitmapFont lifeMessage;
	private BitmapFont energyMessage;
	private BitmapFont clipMessage;
	private BitmapFont ammoMessage;
	private BitmapFont restartMessage;
	private BitmapFont reloadMessage;
	private BitmapFont shieldMessage;
	private BitmapFont buyMessage;
	private BitmapFont firstMessage;
	private BitmapFont gateMessage;
	// TIME + other variales---------------------------------------------------------------------------------
	private long time;
	private long lastSpawn;
	private long frequency;
	private int secondshalf;
	private int GAME_STATE;
	private int PAUSED;
	private int OVER;
	private int RUNNING;
	private int COMPLETED;
	private float MARINE_LIFE;
	private int GUN_CLIP;
	private int EXTRA_CLIPS;
	private int MARINE_ENERGY;
	private int GATES_CLOSED;
	private int EXO_LIFE;
	private boolean GATE1_STATUS;
	private boolean GATE2_STATUS;
	private boolean GATE3_STATUS;
	private boolean GATE4_STATUS;
	float scrollTimer = 0.0f;

	Splash() {
		Restart();
	}

	public void Restart() {
		// --------------------------------------------------------------------------
		RUNNING = 1;
		PAUSED = 2;
		OVER = 3;
		COMPLETED = 4;
		GAME_STATE = RUNNING;
		MARINE_LIFE = 100;
		GUN_CLIP = 20;
		EXTRA_CLIPS = 4;
		MARINE_ENERGY = 1000;
		GATES_CLOSED = 0;
		time = 1000000000;
		frequency = 1500000000;
		this.secondshalf = 0;
		EXO_LIFE = 100;
		GATE1_STATUS = false;
		GATE2_STATUS = false;
		GATE3_STATUS = false;
		GATE4_STATUS = false;
		// --------------------------------------------------------------------------
		gun.setPosition(20, 570);
		spacemarine.setPosition(450, 300);
		alien.setPosition(200, 500);
		AlienbodyDef.type = BodyDef.BodyType.DynamicBody;
		AlienbodyDef.position.set(alien.getX(), alien.getY());
		Alienbody = world.createBody(AlienbodyDef);
		Alienshape.setAsBox(alien.getWidth() / 2, alien.getHeight() / 2);
		AlienfixtureDef.shape = Alienshape;
		AlienfixtureDef.density = 1f;

		MarinebodyDef.type = BodyDef.BodyType.DynamicBody;
		MarinebodyDef.position.set(spacemarine.getX(), spacemarine.getY());
		Marinebody = world.createBody(MarinebodyDef);
		Marineshape.setAsBox(spacemarine.getWidth() / 2,
				spacemarine.getHeight() / 2);
		MarinefixtureDef.shape = Marineshape;
		MarinefixtureDef.density = 1f;
		bullets = new Array<Bullets>();
		aliens = new Array<Aliens>();
		blood_trail = new Array<Sprite>();
		dropItem = new Array<Aliens>();
		gates = new Array<Sprite>();
		// -----------------------------------------------------------------------------------
		emptyGun.setLooping(false);
		soundtrack.setLooping(true);
		soundtrack.play();
		gunShot.setLooping(false);
		gunReload.setLooping(false);
		pop.setLooping(false);
		gateM.setLooping(false);
		// -----------------------------------------------------------------------------------
		gateMessage = new BitmapFont();
		gateMessage.setColor(Color.WHITE);
		buyMessage = new BitmapFont();
		buyMessage.setColor(Color.RED);
		lifeMessage = new BitmapFont();
		lifeMessage.setColor(Color.GREEN);
		energyMessage = new BitmapFont();
		energyMessage.setColor(Color.WHITE);
		clipMessage = new BitmapFont();
		clipMessage.setColor(Color.WHITE);
		ammoMessage = new BitmapFont();
		ammoMessage.setColor(Color.WHITE);
		restartMessage = new BitmapFont();
		restartMessage.setColor(Color.WHITE);
		reloadMessage = new BitmapFont();
		reloadMessage.setColor(Color.WHITE);
		shieldMessage = new BitmapFont();
		shieldMessage.setColor(Color.WHITE);
		firstMessage = new BitmapFont();
		firstMessage.setColor(Color.GREEN);
		
		// ------------------------------------------------------------------------------------
	}

	public boolean checkAliens(Bullets b) {
		boolean collision = false;
		Rectangle r1 = b.getSprite().getBoundingRectangle();
		for (Aliens a : aliens) {
			Rectangle r2 = a.getSprite().getBoundingRectangle();
			if (r1.overlaps(r2)) {
				collision = true;
				float alien_life = a.getLife();
				a.setLife(alien_life - 25);
				Sprite alienBlood = new Sprite(alien_blood);
				alienBlood.setPosition(a.getSprite().getX(), a.getSprite()
						.getY());
				alienBlood.setRotation(90);
				blood_trail.add(alienBlood);
				if (a.getLife() < 0) {
					Sprite item = a.getItem();
					if (a.getItem() != null) {
						item.setPosition(a.getSprite().getX(), a.getSprite()
								.getY());
						dropItem.add(a);
					}
					MARINE_ENERGY += 20;
					aliens.removeValue(a, true);
				}
				bullets.removeValue(b, true);
				break;
			}
		}
		return collision;
	}

	public void checkBullets() {
		for (Bullets b : bullets) {
			checkAliens(b);
		}
	}

	public void checkPlayerItems() {
		for (Aliens a : dropItem) {
			Rectangle r1 = spacemarine.getBoundingRectangle();
			if (a.getItem() != null) {
				Rectangle r2 = a.getItem().getBoundingRectangle();
				if (r1.overlaps(r2)) {
					int item = a.getItemType();
					if (item == 0) {
						EXTRA_CLIPS += 3;
					}
					if (item == 10) {
						MARINE_ENERGY += 100;
					}
					if (item == 2) {
						if (MARINE_LIFE > 60) {
							MARINE_LIFE = 100;
						} else {
							MARINE_LIFE += 40;
						}
					}
					if (item == 4) {
						MARINE_ENERGY += 100;
					}
					if (item == 9) {
						EXO_LIFE += 40;
					}
					pop.play();
					this.dropItem.removeValue(a, true);
				}
			}

		}
	}

	public void checkAliensPlayer() {
		Rectangle r1 = spacemarine.getBoundingRectangle();
		for (Aliens a : aliens) {
			Rectangle r2 = a.getSprite().getBoundingRectangle();
			if (r2.overlaps(r1)) {
				if (EXO_LIFE > 0) {
					EXO_LIFE -= 0.25;
				}
				if (EXO_LIFE == 0) {
					MARINE_LIFE -= 0.25;
				}
				hurt.play();
				alienGrunt.play();
				a.setCanMove(false);
			} else {
				a.setCanMove(true);
			}
		}
	}

	public void moveAlien() {

		for (Aliens a : aliens) {
			Sprite s = a.getSprite();
			float targetX = spacemarine.getX();
			float targetY = spacemarine.getY();
			float spriteX = s.getX();
			float spriteY = s.getY();
			float x2 = s.getX();
			float y2 = s.getY();
			if (a.canMove == true) {
				float angle;
				angle = (float) Math
						.atan2(targetY - spriteY, targetX - spriteX);
				x2 += (float) Math.cos(angle) * 125
						* Gdx.graphics.getDeltaTime();
				y2 += (float) Math.sin(angle) * 125
						* Gdx.graphics.getDeltaTime();
				s.setPosition(x2, y2);
			}
			s.setRotation(getAlienRotation(s, targetX, targetY));
		}
	}

	public void moveBullet() {
		for (Bullets b : bullets) {
			Sprite bullet = b.getSprite();
			int x = (int) bullet.getX();
			int y = (int) bullet.getY();
			int mX = b.getMouseX();
			int mY = b.getMouseY();

			float x2;
			float y2;
			x2 = b.bullet.getX();
			y2 = b.bullet.getY();
			float mouseX = b.getMouseX();
			float mouseY = b.getMouseY();
			float spriteX = bullet.getX();
			float spriteY = bullet.getY();
			float angle;
			if (x != mX || y != mY) {
				angle = (float) Math.atan2(mouseY - spriteY, mouseX - spriteX);
				if (x2 != mX) {
					x2 += (float) Math.cos(angle) * 300
							* Gdx.graphics.getDeltaTime();
				}
				if (y2 != mY) {
					y2 += (float) Math.sin(angle) * 300
							* Gdx.graphics.getDeltaTime();
				}
				bullet.setPosition(x2, y2);
			}
			if (x == mX || y == mY) {
				bullets.removeValue(b, true);
			}
		}
	}


	public void checkGates() {
		if (spacemarine.getX() == 66 && spacemarine.getY() >= 200
				&& spacemarine.getY() <= 436 && GATE1_STATUS == false) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.G) == true
					&& MARINE_ENERGY >= 1000) {
				GATE1_STATUS = true;
				MARINE_ENERGY -= 1000;
				GATES_CLOSED++;
				Sprite gate = new Sprite(gate_texture);
				gate.setRotation(180);
				gate.setPosition(-40, 160);
				gates.add(gate);
				gateM.play();
			}
		}
		if (spacemarine.getX() >= 274 && spacemarine.getX() <= 682
				&& spacemarine.getY() == 528 && GATE2_STATUS == false) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.G) == true
					&& MARINE_ENERGY >= 1000) {
				GATE2_STATUS = true;
				MARINE_ENERGY -= 1000;
				GATES_CLOSED++;
				Sprite gate = new Sprite(gate_texture);
				gate.setRotation(-90);
				gate.setPosition(450, 450);
				gates.add(gate);
				gateM.play();
			}
		}
		if (spacemarine.getX() == 854 && spacemarine.getY() >= 160
				&& spacemarine.getY() <= 404 && GATE4_STATUS == false) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.G) == true
					&& MARINE_ENERGY >= 1000) {
				GATE4_STATUS = true;
				MARINE_ENERGY -= 1000;
				GATES_CLOSED++;
				Sprite gate = new Sprite(gate_texture);
				gate.setRotation(-180);
				gate.setPosition(900, 150);
				gates.add(gate);
				gateM.play();
			}
		}
		if (spacemarine.getX() >= 266 && spacemarine.getX() <= 694
				&& spacemarine.getY() == 36 && GATE3_STATUS == false) {
			if (Gdx.input.isKeyJustPressed(Input.Keys.G) == true
					&& MARINE_ENERGY >= 1000) {
				GATE3_STATUS = true;
				MARINE_ENERGY -= 1000;
				GATES_CLOSED++;
				Sprite gate = new Sprite(gate_texture);
				gate.setRotation(90);
				gate.setPosition(450, -130);
				gates.add(gate);
				gateM.play();
			}
		}
	}

	public void spawnAlien() {
		float aX, aY;
		int position = MathUtils.random(0, 4);
		if (position == 0 && GATE1_STATUS == false) { // GATE 1
			aX = 68;
			aY = 284;
			Sprite alien_sprite = new Sprite(alien_texture);
			float targetX = spacemarine.getX();
			float targetY = spacemarine.getY();
			alien_sprite.setX(aX);
			alien_sprite.setY(aY);
			float angleA = getAlienRotation(alien_sprite, spacemarine.getX(),
					spacemarine.getY());
			alien_sprite.setRotation(angleA);

			Aliens new_alien = new Aliens(100, 100, targetX, targetY, aX, aY,
					alien_sprite);
			aliens.add(new_alien);
		}
		if (position == 1 && GATE2_STATUS == false) { // GATE 2
			aX = 496;
			aY = 528;
			Sprite alien_sprite = new Sprite(alien_texture);
			float targetX = spacemarine.getX();
			float targetY = spacemarine.getY();
			alien_sprite.setX(aX);
			alien_sprite.setY(aY);
			float angleA = getAlienRotation(alien_sprite, spacemarine.getX(),
					spacemarine.getY());
			alien_sprite.setRotation(angleA);

			Aliens new_alien = new Aliens(100, 100, targetX, targetY, aX, aY,
					alien_sprite);
			aliens.add(new_alien);
		}
		if (position == 2 && GATE3_STATUS == false) { // GATE 3
			aX = 476;
			aY = 36;
			Sprite alien_sprite = new Sprite(alien_texture);
			float targetX = spacemarine.getX();
			float targetY = spacemarine.getY();
			alien_sprite.setX(aX);
			alien_sprite.setY(aY);
			float angleA = getAlienRotation(alien_sprite, spacemarine.getX(),
					spacemarine.getY());
			alien_sprite.setRotation(angleA);

			Aliens new_alien = new Aliens(100, 100, targetX, targetY, aX, aY,
					alien_sprite);
			aliens.add(new_alien);
		}
		if (position == 3 && GATE4_STATUS == false) { // GATE 4
			aX = 852;
			aY = 296;
			Sprite alien_sprite = new Sprite(alien_texture);
			float targetX = spacemarine.getX();
			float targetY = spacemarine.getY();
			alien_sprite.setX(aX);
			alien_sprite.setY(aY);
			float angleA = getAlienRotation(alien_sprite, spacemarine.getX(),
					spacemarine.getY());
			alien_sprite.setRotation(angleA);

			Aliens new_alien = new Aliens(100, 100, targetX, targetY, aX, aY,
					alien_sprite);
			aliens.add(new_alien);
		}
		if (GATES_CLOSED < 3) {
			if (position == 0 && GATE1_STATUS == true) {
				spawnAlien();
			}
			if (position == 1 && GATE2_STATUS == true) {
				spawnAlien();
			}
			if (position == 2 && GATE3_STATUS == true) {
				spawnAlien();
			}
			if (position == 3 && GATE4_STATUS == true) {
				spawnAlien();
			}
			if (position == 4 && GATE3_STATUS == true) {
				spawnAlien();
			}
		}

		lastSpawn = TimeUtils.nanoTime();
	}

	public void spawnBullet() {
		Sprite bullet_sprite = new Sprite(bullet_texture);
		float angle = getBulletRotation();
		int mX = Gdx.input.getX();
		int mY = 677 - Gdx.input.getY();
		float bulletX;
		float bulletY;
		float offsetX = 20;
		float offsetY = 110;
		bulletX = spacemarine.getX()
				+ (float) (offsetX * Math.cos(Math.toRadians(angle)) - offsetY
						* Math.sin(Math.toRadians(angle)));
		bulletY = spacemarine.getY()
				+ (float) (offsetX * Math.sin(Math.toRadians(angle)) + offsetY
						* Math.cos(Math.toRadians(angle)));
		bullet_sprite.setPosition(bulletX, bulletY);
		bullet_sprite.setRotation(angle);
		//Bullets bullet = new Bullets(mX, mY, bulletX, bulletY, bullet_sprite);
		Bullets bullet = new Bullets(mX, mY, spacemarine.getX(), spacemarine.getY(), bullet_sprite);
		bullets.add(bullet);
	}

	public float getBulletRotation() {
		float angle = 0;
		float mouseX = 0;
		float mouseY = 0;
		mouseX = Gdx.input.getX();
		mouseY = 677 - Gdx.input.getY();
		angle = (float) Math.toDegrees(Math.atan2(mouseX - spacemarine.getX(),
				mouseY - spacemarine.getY())) * -1;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public float rotateMarine() {
		float angle = 0;
		float mouseX = 0;
		float mouseY = 0;
		mouseX = Gdx.input.getX();
		mouseY = 677 - Gdx.input.getY();
		angle = (float) Math.toDegrees(Math.atan2(mouseX - spacemarine.getX(),
				mouseY - spacemarine.getY()));
		if (angle < 0)
			angle += 360;
		spacemarine.setRotation(angle * -1);
		return angle;
	}

	public float getAlienRotation(Sprite s, float marineX, float marineY) {
		float angle = 0;
		float targetX = 0;
		float targetY = 0;
		targetX = marineX;
		targetY = marineY;
		angle = (float) Math.toDegrees(Math.atan2(targetX - s.getX(), targetY
				- s.getY()));
		if (angle < 0)
			angle += 360;
		s.setRotation(angle * -1);
		return angle * -1;
	}

	public void cleanBullets() {
		for (Bullets b : bullets) {
			Sprite s = b.getSprite();

		}
	}

	public void buyItem() {

		if (Gdx.input.isKeyPressed(Input.Keys.NUM_1) == true) {
			if (MARINE_ENERGY >= 400 && MARINE_LIFE < 100) {
				MARINE_ENERGY -= 400;
				if (MARINE_LIFE >= 60) {
					MARINE_LIFE = 100;
				} else {
					MARINE_LIFE += 40;
				}
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_2) == true) {
			if (MARINE_ENERGY >= 300) {
				MARINE_ENERGY -= 300;
				this.EXTRA_CLIPS += 3;
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.NUM_3) == true) {
			if (MARINE_ENERGY >= 300) {
				MARINE_ENERGY -= 300;
				this.EXO_LIFE += 30;
			}
		}
	}

	@Override
	public void render(float delta) {
		 System.out.println(spacemarine.getY());
		
	/*	scrollTimer+= Gdx.graphics.getDeltaTime();
		 if(scrollTimer>1.0f)
	         scrollTimer = 0.0f;
		this.splashImage.setU(this.scrollTimer);
		this.splashImage.setU2(this.scrollTimer + 1);
		*/
		if (Gdx.input.isKeyJustPressed(Input.Keys.P) == true
				&& GAME_STATE == PAUSED) {
			GAME_STATE = RUNNING;
		}
		if (MARINE_LIFE <= 0) {
			GAME_STATE = OVER;
			gameover.setPosition(250, 250);
			batch.begin();
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			spacemarine.draw(batch);
			for (Aliens a : aliens) {
				Sprite alien = a.getSprite();
				alien.draw(batch);
			}
			gameover.draw(batch);
			restartMessage.draw(batch, "PRESS R TO RESTART THE GAME", 400, 300);
			if (Gdx.input.isKeyPressed(Input.Keys.R) == true) {
				Restart();
			}

			batch.end();
		}
		if (this.GATES_CLOSED == 4) {
			GAME_STATE = OVER;
			complete.setPosition(250, 250);
			batch.begin();
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			
			spacemarine.draw(batch);
			for (Aliens a : aliens) {
				Sprite alien = a.getSprite();
				alien.draw(batch);
			}
			complete.draw(batch);
			restartMessage.draw(batch, "PRESS R TO RESTART THE GAME", 400, 250);
			if (Gdx.input.isKeyPressed(Input.Keys.R) == true) {
				Restart();
			}

			batch.end();
		}

		if (GAME_STATE == RUNNING) {
			if (TimeUtils.nanoTime() - lastSpawn > frequency) {
				//spawnAlien();
				this.secondshalf++;
			}
				
			float angle = rotateMarine();
			checkAliensPlayer();
			checkBullets();
			cleanBullets();
			checkPlayerItems();
			checkGates();
			moveAlien();
			moveBullet();
			buyItem();
			Gdx.gl.glClearColor(0, 0, 0, 0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			world.step(Gdx.graphics.getDeltaTime(), 6, 2);
			if (Gdx.input.isKeyPressed(Input.Keys.W) == true){
					//&& spacemarine.getY() < 528) {
				
				
				if(spacemarine.getY() < 540){
					Marinebody.setTransform(spacemarine.getX(),
							spacemarine.getY() + 4, angle);
				}
				
				if(this.splashImage.getY() > (-1512+680))
					this.splashImage.setY(this.splashImage.getY()-4);
				
				//System.out.println(splashImage.getY());
				
			}
			if (Gdx.input.isKeyPressed(Input.Keys.S) == true){
					//&& spacemarine.getY() > 36) {
				if(spacemarine.getY() > 30){
					Marinebody.setTransform(spacemarine.getX(),
							spacemarine.getY() - 4, angle);
				}
				
				if(this.splashImage.getY() < 0)
					this.splashImage.setY(this.splashImage.getY()+4);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.D) == true) {
				if(spacemarine.getX() < 852){
					Marinebody.setTransform(spacemarine.getX() + 4,
							spacemarine.getY(), angle);
				}
				if(this.splashImage.getX()>-985)
					this.splashImage.setX(this.splashImage.getX()-4);
			}
			if (Gdx.input.isKeyPressed(Input.Keys.A) == true) {
					if(spacemarine.getX() > 70) {
						Marinebody.setTransform(spacemarine.getX() - 4,
								spacemarine.getY(), angle);
					}
					if(this.splashImage.getX()<0)
				this.splashImage.setX(this.splashImage.getX()+4);
			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) == true) {
				if (GUN_CLIP >= 1) {
					spawnBullet();
					gunShot.stop();
					gunShot.play();
					GUN_CLIP--;
				} else {
					emptyGun.stop();
					emptyGun.play();
				}

			}
			if (Gdx.input.isKeyJustPressed(Input.Keys.R) == true) {
				if (EXTRA_CLIPS >= 1 && GUN_CLIP < 20) {
					gunReload.play();
					EXTRA_CLIPS--;
					GUN_CLIP = 20;
				}
			}
			spacemarine.setPosition(Marinebody.getPosition().x,
					Marinebody.getPosition().y);
			batch.begin();
		//	batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
					//Gdx.graphics.getHeight());
			this.splashImage.draw(batch);
			for (Sprite g : gates) {
				g.draw(batch);
			}

			for (Sprite blood : blood_trail) {
				blood.draw(batch);
			}
			for (Aliens d : dropItem) {
				Sprite s = d.getItem();
				s.draw(batch);
			}
			for (Bullets b : bullets) {
				Sprite bullet = b.getSprite();
				bullet.draw(batch);
			}
			spacemarine.draw(batch);			
			gun.draw(batch);
			life.setPosition(690, 590);
			lifeMessage.draw(batch, "" + (int) MARINE_LIFE, 750, 620);
			life.draw(batch);
			if (GUN_CLIP == 0) {
				reloadMessage.draw(batch, "PRESS R TO RELOAD", 400, 350);
			}
			if (EXTRA_CLIPS == 0) {
				buyMessage.draw(batch,
						"PRESS 2 TO BUY AMMO FOR 300 OF YOUR ENERGY", 350, 250);
			}
			if (MARINE_LIFE < 30) {
				buyMessage.draw(batch,
						"PRESS 1 TO BUY A HEALTH KIT FOR 400 OF YOUR ENERGY",
						350, 250);
			}
			shield.setPosition(870, 590);
			shieldMessage.draw(batch, " " + EXO_LIFE, 920, 620);
			shield.draw(batch);
			energy.setPosition(800, 600);
			energy.draw(batch);
			energyMessage.draw(batch, " " + (int) MARINE_ENERGY, 830, 620);
			clipMessage.draw(batch, " " + GUN_CLIP, 110, 628);
			magazine.setPosition(150, 595);
			ammoMessage.draw(batch, " " + EXTRA_CLIPS, 200, 628);
			magazine.draw(batch);
			if (this.secondshalf <= 6) {
				firstMessage
						.draw(batch,
								"-Captain: John, you need to close all the gates and lock yourself in the sector! Once you have locked yourself, we will distract them and rescue you!",
								50, 150);
			}
			// alien.draw(batch);
			
			for (Aliens a : aliens) {
				Sprite alien = a.getSprite();
				alien.draw(batch);
			}
	
			
			batch.end();
			// stage.act();
			// stage.draw();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	//	stage.addActor(splashImage);

/*		splashImage.addAction(Actions.sequence(Actions.alpha(0),
				Actions.fadeIn(0.5f), Actions.delay(2),
				Actions.run(new Runnable() {
					@Override
					public void run() {
						((Game) Gdx.app.getApplicationListener())
								.setScreen(new Splash());
					}
				})));
				*/
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		texture.dispose();
		stage.dispose();
	}
}