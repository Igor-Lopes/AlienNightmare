package com.mygdx.alienswarm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Instructions implements Screen {
	private Table table = new Table();
	private Texture texture = new Texture(
			Gdx.files.internal("MenuBackground.png"));
	private Image splashImage = new Image(texture);
	private Stage stage = new Stage();
	private SpriteBatch batch = new SpriteBatch();
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	private TextButton buttonPlay = new TextButton("Start Game ", skin),
			menu = new TextButton("Main Menu", skin);
	private Music soundtrack = Gdx.audio.newMusic(Gdx.files
			.internal("menuMusic.wav"));;
			
			Instructions(){
				soundtrack.setLooping(true);
				soundtrack.play();
				
			}

	@Override
	public void show() {
		stage.addActor(splashImage);

		menu.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new MenuScreen());
			}
		});

		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new Splash());
			}

		});
		table.setPosition(0, -250);
		table.add(buttonPlay).height(50).width(300).row();
		table.add(menu).height(50).width(300).row();
		table.setFillParent(true);
		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		batch.draw(texture, 0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void dispose() {
		texture.dispose();
		skin.dispose();
		stage.dispose();

	}

}
