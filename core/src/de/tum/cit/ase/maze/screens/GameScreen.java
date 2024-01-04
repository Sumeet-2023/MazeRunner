package de.tum.cit.ase.maze.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import de.tum.cit.ase.maze.MapLoader;
import de.tum.cit.ase.maze.MazeRunnerGame;
import de.tum.cit.ase.maze.characters.Player;

/**
 * The GameScreen class is responsible for rendering the gameplay screen.
 * It handles the game logic and rendering of the game elements.
 */
public class GameScreen implements Screen {
    private EscMenuScreen escMenuScreen;
    private boolean isPause = false;
    private Music backgroundMusic;
    private final MazeRunnerGame game;
    private final OrthographicCamera camera;
    private final BitmapFont font;
    private float sinusInput = 0f;
    private MapLoader mapLoader;

    // Attributes to handle player
    private Player player;
    private float player_x;
    private float player_y;
    private Animation<TextureRegion> playerAnimation;
    private boolean isAnimating = false;
    private float animationTime = 0f;
    private TextureRegion defaultFrame;
    /**
     * Constructor for GameScreen. Sets up the camera and font.
     *
     * @param game The main game class, used to access global resources and methods.
     */
    public GameScreen(MazeRunnerGame game) {

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("background.mp3"));
        backgroundMusic.setVolume(0.2f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        escMenuScreen = new EscMenuScreen(game,this);
        this.game = game;
        // Create and configure the camera for the game view
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.zoom = 0.75f;

        // Get the font from the game's skin
        font = game.getSkin().getFont("font");

        // Create a new instance of MapLoader
        mapLoader = new MapLoader(game, sinusInput);

        // Create new player and set starting position and animation.
        player = new Player();
        mapLoader.setPlayerStartingPos();
        player_x = mapLoader.getPlayer_x();
        player_y = mapLoader.getPlayer_y();
        playerAnimation = player.getCharacterRightAnimation();
        defaultFrame = player.getCharacterRight();

    }


    // Screen interface methods with necessary functionality
    @Override
    public void render(float delta) {
        // Check for escape key press to go back to the menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            isPause = !isPause;
            if(isPause){
                pauseGame();
            }
            else {
                resumeGame();
            }
        }
        if(!isPause) {
            ScreenUtils.clear(0, 0, 0, 1); // Clear the screen

            // Handel Player Movements
            handlePlayerEvents();

            camera.update();
            sinusInput += delta;
            mapLoader.setSinusInput(sinusInput);
            game.getSpriteBatch().setProjectionMatrix(camera.combined);


            player.update(Gdx.graphics.getDeltaTime());
            // Rendering the Map
            game.getSpriteBatch().begin();
            mapLoader.loadMap1();
            TextureRegion currentFrame = player.getCurrentAnimationFrame();
            if (currentFrame != null) {
                game.getSpriteBatch().draw(currentFrame, player_x * 32, player_y * 32, 32, 36);
            } else {
                game.getSpriteBatch().draw(defaultFrame, player_x * 32, player_y * 32, 32, 36);
            }
            game.getSpriteBatch().end();
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        backgroundMusic.dispose();
    }
    public void pauseGame(){
        backgroundMusic.pause();
        game.setScreen(escMenuScreen);
    }
    public void resumeGame(){
        backgroundMusic.play();
        isPause=false;
        game.setScreen(this);
    }

    // Additional methods and logic can be added as needed for the game screen

    /**
     * Function to handle all character movements
     */
    public void handlePlayerEvents()
    {
        float animationSpeed = 20;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && !player.isAnimating()) {
            player_x -= animationSpeed * Gdx.graphics.getDeltaTime();
            player.startAnimation(player.getCharacterLeftAnimation());
            defaultFrame = player.getCharacterLeft();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !player.isAnimating()) {
            player_x += animationSpeed * Gdx.graphics.getDeltaTime();
            player.startAnimation(player.getCharacterRightAnimation());
            defaultFrame = player.getCharacterRight();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) && !player.isAnimating()) {
            player_y += animationSpeed * Gdx.graphics.getDeltaTime();
            player.startAnimation(player.getCharacterUpAnimation());
            defaultFrame = player.getCharacterUp();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && !player.isAnimating()) {
            player_y -= animationSpeed * Gdx.graphics.getDeltaTime();
            player.startAnimation(player.getCharacterDownAnimation());
            defaultFrame = player.getCharacterDown();
        }
    }
}
