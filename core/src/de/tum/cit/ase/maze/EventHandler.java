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

    // Attribute for handling obstacle
    private float timeOnObstacle = 0f;
    private boolean isOnObstacle = false;

    private boolean isOnEnemy = false;
    private boolean isOnHeart1 = false;
    private boolean isOnHeart2 = false;
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
                    player.startAnimation(player.getCharacterLeftAnimation());
            }
            player.setDefaultFrame(player.getCharacterLeft());
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.RIGHT, mapLoader, player.getHasKey())) {
                player.setX(player.getX() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterRightAnimation());
            }
            player.setDefaultFrame(player.getCharacterRight());
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.UP, mapLoader, player.getHasKey())) {
                player.setY(player.getY() + (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterUpAnimation());
            }
            player.setDefaultFrame(player.getCharacterUp());
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (Utils.canCharacterMove(player.getX(), player.getY(), Direction.DOWN, mapLoader, player.getHasKey())) {
                player.setY(player.getY() - (animationSpeed * deltaTime));
                if (!player.isAnimating())
                    player.startAnimation(player.getCharacterDownAnimation());
            }
            player.setDefaultFrame(player.getCharacterDown());
        }
    }

    public void handelKey(HUD hud)
    {
        if (Math.abs(player.getX() -  mapLoader.getKeyX()) < 0.5  && Math.abs(player.getY() - mapLoader.getKeyY()) < 0.5) {
            player.setHasKey(true);
            mapLoader.setDisplayKey(false);
            hud.update();
            hud.animateKeyCollection();
        }
    }
    public void handelHeart(HUD hud)
    {
            if (Math.abs(player.getX() - mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex1()).get(0)) < 0.5 && Math.abs(player.getY() - mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex1()).get(1)) < 0.5 && player.getHeartCount() < 3) {
                player.setHasHeart1(true);
                mapLoader.setDisplayHeart1(false);
                hud.update();
                //hud.animateKeyCollection();
            } else if (Math.abs(player.getX() - mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex2()).get(0)) < 0.5 && Math.abs(player.getY() - mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex2()).get(1)) < 0.5 && player.getHeartCount() < 3) {
                player.setHasHeart2(true);
                mapLoader.setDisplayHeart2(false);
                hud.update();
                //hud.animateKeyCollection();
            }
    }

    public void handelPlayerObstacleInteraction(float deltaTime, HUD hud)
    {
        if (Utils.isObstacle(player.getX(), player.getY(), mapLoader.getObstacleCoordinates())){
            if (!isOnObstacle){
                player.setHeartCount(player.getHeartCount() - 1);
                isOnObstacle = true;
                timeOnObstacle = 0f;
                hud.update();
                hud.animateHeartLoss(2 - player.getHeartCount());
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnObstacle >= 3.0f){
                    player.setHeartCount(player.getHeartCount() - 1);
                    timeOnObstacle = 0f;
                    hud.update();
                    hud.animateHeartLoss(2 - player.getHeartCount());
                }
            }
        }
        else {
            isOnObstacle = false;
            timeOnObstacle = 0f;
        }
    }

    public void handlePlayerEnemyInteraction(float deltaTime, HUD hud)
    {
        if (Utils.isEnemy(player.getX(), player.getY(), mapLoader.getEnemies())){
            if (!isOnEnemy){
                player.setHeartCount(player.getHeartCount() - 1);
                isOnEnemy = true;
                timeOnObstacle = 0f;
                hud.update();
                hud.animateHeartLoss(2 - player.getHeartCount());
            }
            else {
                timeOnObstacle += deltaTime;
                if (timeOnEnemy >= 3.0f){
                    player.setHeartCount(player.getHeartCount() - 1);
                    timeOnEnemy = 0f;
                    hud.update();
                    hud.animateHeartLoss(2 - player.getHeartCount());

                }
            }
        }
        else {
            isOnEnemy = false;
            timeOnEnemy = 0f;
        }
    }
    public void handlePlayerHeartInteraction(HUD hud){
            if (Utils.isHeart(player.getX(), player.getY(), mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex1()))) {
                if (!isOnHeart1) {
                    if (player.getHeartCount() < 3) {
                        player.setHeartCount(player.getHeartCount() + 1);
                        isOnHeart1 = true;
                        hud.update();
                    }
                }
            } else if (Utils.isHeart(player.getX(), player.getY(), mapLoader.getEmptySpaceCoordinate().get(mapLoader.getRandomIndex2()))) {
                if (!isOnHeart2) {
                    if (player.getHeartCount() < 3) {
                        player.setHeartCount(player.getHeartCount() + 1);
                        isOnHeart2 = true;
                        hud.update();
                    }
                }
            }
    }

    public void handelLose()
    {
        if (player.getHeartCount() == 0)
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