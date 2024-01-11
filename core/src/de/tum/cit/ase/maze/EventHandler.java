package de.tum.cit.ase.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import de.tum.cit.ase.maze.characters.Player;

import java.lang.invoke.MutableCallSite;

public class EventHandler {

    private Player player;
    private MapLoader mapLoader;
    private MazeRunnerGame game;
    private Music backgroundMusic;

    // Attribute for heart count
    private int heartCount = 3;

    // Attribute for handling obstacle
    private float timeOnObstacle = 0f;
    private boolean isOnObstacle = false;

    private boolean isOnEnemy = false;
    private float timeOnEnemy = 0f;
    public EventHandler(Player player, MapLoader mapLoader, MazeRunnerGame game, Music backgroundMusic) {
        this.player = player;
        this.mapLoader = mapLoader;
        this.game = game;
        this.backgroundMusic = backgroundMusic;
    }

    public void handlePlayerMovements()
    {
        float animationSpeed = 3;
        float deltaTime = Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.LEFT, mapLoader, player.getHasKey())) {
                player.setX(player.getX() - (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacter4LeftAnimation());
            }
            player.setDefaultFrame(player.getCharacter4Left());
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.RIGHT, mapLoader, player.getHasKey())) {
                player.setX(player.getX() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacter4RightAnimation());
            }
            player.setDefaultFrame(player.getCharacter4Right());
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.UP, mapLoader, player.getHasKey())) {
                player.setY(player.getY() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacter4UpAnimation());
            }
            player.setDefaultFrame(player.getCharacter4Up());
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.DOWN, mapLoader, player.getHasKey())) {
                player.setY(player.getY() - (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacter4DownAnimation());
            }
            player.setDefaultFrame(player.getCharacter4Down());
        }
    }

    public void handelKey()
    {
        if (Math.abs(player.getX() -  mapLoader.getKeyX()) < 0.5  && Math.abs(player.getY() - mapLoader.getKeyY()) < 0.5) {
            player.setHasKey(true);
            mapLoader.setDisplayKey(false);
        }
    }

    public void handelPlayerObstacleInteraction(float deltaTime)
    {
        if (Utils.isObstacle(player.getX(), player.getY(), mapLoader.getObstacleCoordinates())){
            if (!isOnObstacle){
                heartCount--;
                isOnObstacle = true;
                timeOnObstacle = 0f;
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnObstacle >= 3.0f){
                    heartCount--;
                    timeOnObstacle = 0f;
                }
            }
        }
        else {
            isOnObstacle = false;
            timeOnObstacle = 0f;
        }
    }

    public void handlePlayerEnemyInteraction(float deltaTime)
    {
        if (Utils.isEnemy(player.getX(), player.getY(), mapLoader.getEnemies())){
            if (!isOnEnemy){
                heartCount--;
                isOnEnemy = true;
                timeOnObstacle = 0f;
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnEnemy >= 3.0f){
                    heartCount--;
                    timeOnEnemy = 0f;
                }
            }
        }
        else {
            isOnEnemy = false;
            timeOnEnemy = 0f;
        }
    }

    public void handelLose()
    {
        if (heartCount == 0)
        {
            game.goToLooseScreen();
            backgroundMusic.dispose();
        }
    }

    public void handelWin()
    {
        if (Utils.isDoor(player.getX(), player.getY(), mapLoader.getDoorCoordinates()) && player.getHasKey())
        {
            game.goToWinScreen();
            backgroundMusic.dispose();
        }
    }
}
