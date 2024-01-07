package de.tum.cit.ase.maze.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.tum.cit.ase.maze.MazeRunnerGame;
import de.tum.cit.ase.maze.characters.Player;

public class LooseScreen implements Screen {
    private final MazeRunnerGame game;
    private Music backgroundMusic;
    private final BitmapFont font;
    private final Stage stage;
    private float sinusInput;
    private Animation<TextureRegion> sadAnimation;

    public LooseScreen(MazeRunnerGame game){
        this.game = game;
        var camera = new OrthographicCamera();
        camera.zoom = 1.0f;
        font = game.getSkin().getFont("font");
        Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
        stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements
        Table table = new Table(); // Create a table for layout
        table.setFillParent(true); // Make the table fill the stage
        stage.addActor(table); // Add the table to the stage
        Label label1 = new Label("You Loose!",game.getSkin(),"title");

        Label label2 = new Label("Click Space for Main Menu!",game.getSkin());

        table.top().add(label1).padBottom(80).row();
        table.add(label2);

        Image backgroundImage = new Image(new Texture(Gdx.files.internal("LooseBG.png")));
        table.setBackground(backgroundImage.getDrawable());

        loadSadAnimation();

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("LooseWinMusic.wav"));
        backgroundMusic.setVolume(0.8f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        sinusInput +=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            backgroundMusic.dispose();
            game.goToMenu();
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Update the stage
        stage.draw(); // Draw the stage
        TextureRegion currentFrame = sadAnimation.getKeyFrame(sinusInput/4, true);
        game.getSpriteBatch().begin();
        game.getSpriteBatch().draw(currentFrame, ((float) Gdx.graphics.getWidth() / 2)  - (float) currentFrame.getRegionWidth() / 2,
                (((float) Gdx.graphics.getHeight() / 4) -185) - (float) currentFrame.getRegionHeight() / 2,64,128);
        game.getSpriteBatch().end();

    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
      backgroundMusic.dispose();
    }
    public void loadSadAnimation() {
        Texture walkSheet = new Texture(Gdx.files.internal("character.png"));

        int frameWidth = 16;
        int frameHeight = 32;
        int animationFrames = 2;

        // libGDX internal Array instead of ArrayList because of performance
        Array<TextureRegion> walkFrames = new Array<>(TextureRegion.class);

        // Add all frames to the animation
        for (int col = 5; col < animationFrames+5; col++) {
            walkFrames.add(new TextureRegion(walkSheet, col * frameWidth, 0,frameWidth, frameHeight));
        }
        sadAnimation = new Animation<>(0.08f, walkFrames);
    }
}
