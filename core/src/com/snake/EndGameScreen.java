package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class EndGameScreen extends ScreenAdapter {

    final  Snake game;

    Rectangle gameOver;
    Texture gameOverImage;
    private Stage stage;
    OrthographicCamera camera;
    int score;

    // sound
    Music gameOverMusic;



    public EndGameScreen(Snake game, int score) {
        this.game = game;
        this.score = score;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);

        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("gameOver.wav"));
        gameOverMusic.setLooping(true);
        gameOverMusic.setVolume(0.1F);
    

        gameOverImage = new Texture(Gdx.files.internal("game_over.png"));
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);


        gameOver = new Rectangle();
        gameOver.x = 1920 / 2 - 388 / 2;
        gameOver.y = 650;
        gameOver.width = 388;
        gameOver.height = 314;

        // STOP SOUND WHILE TESTING

    }

    @Override
    public void show() {

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(false);
        stage.addActor(table);


        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextButton tryAgain = new TextButton("Try Again", skin);
        TextButton exit = new TextButton("Exit", skin);


        table.row().pad(300, 0, 10, 0);
        table.add(tryAgain).prefHeight(100).prefWidth(300);
        table.row().pad(10, 0, 10, 0);
        table.add(exit).prefHeight(100).prefWidth(300);


        tryAgain.addListener(new ChangeListener() {
            @Override

            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                gameOverMusic.stop();
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }


    @Override
    public void render(float delta) {
    Gdx.gl.glClearColor(0f, 0f, 0f, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);


    stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f ));
    stage.draw();

    game.batch.begin();
    game.batch.draw(gameOverImage, gameOver.x, gameOver.y, gameOver.width, gameOver.height);
    game.scorefont.draw(game.batch, "Your Final Score: " + score, gameOver.x + 30, gameOver.y - 60);
    game.font.draw(game.batch, "COPYRIGHT 2021 Tiger Cubed Games", 1600, 30);
    game.batch.end();


        // TO STOP SOUND WHILE TESTING COMMENT OUT BELOW LINE
         gameOverMusic.play();

    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    stage.dispose();

        gameOverMusic.dispose();

    }
}
