package com.mygdx.alienswarm;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {

	private Stage stage = new Stage();
	private Table table = new Table();
	private SpriteBatch batch = new SpriteBatch();
	private Texture texture = new Texture(Gdx.files.internal("bkg_menu.png"));
	private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
	private Music soundtrack = Gdx.audio.newMusic(Gdx.files
			.internal("menuMusic.wav"));;
	private TextButton buttonPlay = new TextButton("Play", skin),
			buttonHelp = new TextButton("How to Play / Story", skin),
			buttonExit = new TextButton("Exit", skin);

	MenuScreen() {
		soundtrack.setLooping(true);
		soundtrack.play();
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
	}

	@Override
	public void show() {
		// The elements are displayed in the order you add them.
		// The first appear on top, the last at the bottom.
		buttonPlay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new Splash());
			}
		});
		buttonExit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
				// or System.exit(0);
			}
		});
		buttonHelp.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				((Game) Gdx.app.getApplicationListener())
						.setScreen(new Instructions());
				// or System.exit(0);
			}
		});

		table.add(buttonPlay).height(50).width(300).row();
		table.add(buttonHelp).height(50).width(300).row();
		table.add(buttonExit).height(50).width(300).row();

		table.setFillParent(true);
		stage.addActor(table);

		Gdx.input.setInputProcessor(stage);
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
		stage.dispose();
		skin.dispose();
	}

}