package com.snake;

import com.badlogic.gdx.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class EndGameScreen extends ScreenAdapter {

    final  Snake game;



    public EndGameScreen(Snake game) {
        this.game = game;
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.83f, 0.13f, 0.18f, 1);

        game.batch.begin();
        game.font.draw(game.batch, "Game Over !!", 950, 860);
        game.font.draw(game.batch, "Do you want to play again ??", 900, 720);
        game.font.draw(game.batch, "Press Space if Yes or ESC for Quit", 880, 550);
        game.font.draw(game.batch, "COPYRIGHT 2021 Tiger Cubed Games", 1600, 20);
        game.batch.end();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if(keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                } else if(keyCode == Input.Keys.ESCAPE){
                    Gdx.app.exit();
                }
                return true;
            }
       });
    }


    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}
