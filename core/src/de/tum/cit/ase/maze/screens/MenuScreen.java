    package de.tum.cit.ase.maze.screens;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.audio.Music;
    import com.badlogic.gdx.graphics.GL20;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.scenes.scene2d.Actor;
    import com.badlogic.gdx.scenes.scene2d.Stage;
    import com.badlogic.gdx.scenes.scene2d.ui.Image;
    import com.badlogic.gdx.scenes.scene2d.ui.Label;
    import com.badlogic.gdx.scenes.scene2d.ui.Table;
    import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
    import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
    import com.badlogic.gdx.utils.viewport.ScreenViewport;
    import com.badlogic.gdx.utils.viewport.Viewport;
    import de.tum.cit.ase.maze.DesktopFileChooser;
    import de.tum.cit.ase.maze.FileChooser;
    import de.tum.cit.ase.maze.FileChooserCallBack;
    import de.tum.cit.ase.maze.MazeRunnerGame;

    /**
     * The MenuScreen class is responsible for displaying the main menu of the game.
     * It extends the LibGDX Screen class and sets up the UI components for the menu.
     */
 public class MenuScreen implements Screen {
     private final Stage stage;

     /**
      * Constructor for MenuScreen. Sets up the camera, viewport, stage, and UI elements.
      *
      * @param game The main game class, used to access global resources and methods.
      */
     public MenuScreen(MazeRunnerGame game) {
         var camera = new OrthographicCamera();
         camera.zoom = 1.0f; // Set camera zoom for a closer view


         Viewport viewport = new ScreenViewport(camera); // Create a viewport with the camera
         stage = new Stage(viewport, game.getSpriteBatch()); // Create a stage for UI elements

         Table table = new Table(); // Create a table for layout
         table.setFillParent(true); // Make the table fill the stage
         stage.addActor(table); // Add the table to the stage
         Image backgroundImage = new Image(new Texture(Gdx.files.internal("MainMenuImage.jpg")));
         table.setBackground(backgroundImage.getDrawable());
         // Add a label as a title
         table.add(new Label("MAZE RUNNER", game.getSkin(), "title")).padBottom(80).row();
         // Create and add a button to go to the game screen
         TextButton startGame = new TextButton("Start", game.getSkin());
         table.add(startGame).width(300).pad(10).row();
         TextButton selectMap = new TextButton("Select Map", game.getSkin());
         table.add(selectMap).width(300).pad(10).row();
         TextButton upload = new TextButton("Upload Map", game.getSkin());
         table.add(upload).width(300).pad(10).row();
         TextButton credits = new TextButton("Credits", game.getSkin());
         table.add(credits).width(300).pad(10).row();

        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) { game.goToGame("maps//level-1.properties");
            }
        });
        selectMap.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToSelectMap();
            }
        });
        upload.addListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {
                 showFileChooser(new FileChooserCallBack() {
                     @Override
                     public void onFileChosen(String filePath) {
                         game.goToGame(filePath);
                     }
                 });
          }
         });

        credits.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.goToCredits();

            }
        });
    }

        /**
         * Opens a file chooser dialog to allow the user to select a map file for uploading.
         * When a file is chosen, the provided callback is invoked with the chosen file path.
         *
         * @param callBack The callback to handle the chosen file path.
         */
    public void showFileChooser(FileChooserCallBack callBack)
     {
         // Create a file chooser instance
            FileChooser fileChooser = new DesktopFileChooser();

         // Open the file chooser dialog, and handle the chosen file in the callback
            fileChooser.chooseFile(callBack);
     }

    @Override
    public void dispose() {
        // Dispose of the stage when screen is disposed
        stage.dispose();

    }

     @Override
     public void show() {
         // Set the input processor so the stage can receive input events
         Gdx.input.setInputProcessor(stage);
     }
     @Override
     public void render(float delta) {
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear the screen
         stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f)); // Update the stage
         stage.draw(); // Draw the stage
     }
     @Override
     public void resize(int width, int height) {
         stage.getViewport().update(width, height, true); // Update the stage viewport on resize
     }

     @Override
     public void hide() {
         Gdx.input.setInputProcessor(null);
     }
     @Override
     public void pause() {
     }

     @Override
     public void resume() {
     }
 }


