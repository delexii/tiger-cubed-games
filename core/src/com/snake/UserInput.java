package com.snake;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;




public class UserInput {


    // Snake movement controls
    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;
    public int snakeDirection = -1;

    // get user input
    public void userInput(int previoussnakeDirection) {
        boolean lPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean rPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean uPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean dPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if (lPressed) snakeDirection = LEFT;
        if (rPressed) snakeDirection = RIGHT;
        if (uPressed) snakeDirection = UP;
        if (dPressed) snakeDirection = DOWN;

        //prevents reverse commands
        if ((previoussnakeDirection == RIGHT && this.snakeDirection == LEFT)
                || (previoussnakeDirection == DOWN && this.snakeDirection == UP)
                || (previoussnakeDirection == LEFT && this.snakeDirection == RIGHT)
                || (previoussnakeDirection == UP && this.snakeDirection == DOWN)){
        this.snakeDirection = previoussnakeDirection;}

        //prevents reverse command at start of game
        if ((previoussnakeDirection == -1 && this.snakeDirection == DOWN))
        this.snakeDirection = previoussnakeDirection;

    }
}
