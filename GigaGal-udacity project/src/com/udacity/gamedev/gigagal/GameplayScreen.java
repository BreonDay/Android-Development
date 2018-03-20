package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.udacity.gamedev.gigagal.utils.Assets;
import com.udacity.gamedev.gigagal.utils.ChaseCam;
import com.udacity.gamedev.gigagal.utils.Constants;


public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    // TODO: Add a Level
    Level level;

    // TODO: Add a SpriteBatch
    SpriteBatch batch;
    //TODO unnoted change on there end passing renderer handling to gameplayscreen
    ShapeRenderer renderer;

    // TODO: Add an ExtendViewport unnoted name change
    ExtendViewport gameplayViewport;

    // TODO: Add a ChaseCam
    private ChaseCam chaseCam;



    @Override
    public void show() {
        //TODO unnoted addition and init of asset manager being passed into Assets.instance.init
        AssetManager am = new AssetManager();
        Assets.instance.init(am);



        //TODO unnoted initialization and setting of renderer
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        // TODO: Initalize the SpriteBatch
        batch = new SpriteBatch();

        // TODO: Initialize the viewport
        gameplayViewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        // TODO: Initialize Level unnoted change of viewport constructor added
        level = new Level(gameplayViewport);
        // TODO: Initialize chaseCam, getting the Camera from the Viewport and GigaGal from the Level unnoted change to get
        chaseCam = new ChaseCam(gameplayViewport.getCamera(), level.getGigaGal());

    }

    @Override
    public void resize(int width, int height) {
        // TODO: Update the viewport
       gameplayViewport.update(width, height, true);
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }

    @Override
    public void dispose() {
        // TODO: Dispose of the Assets instance
        Assets.instance.dispose();

        // TODO: Dispose of the SpriteBatch
        batch.dispose();
    }

    @Override
    public void render(float delta) {
        // TODO: Update the ChaseCam
        chaseCam.update(delta);

        // TODO: Call update() on the Level
        level.update(delta);

        // TODO: Apply the viewport
        gameplayViewport.apply();

        // TODO: Clear the screen to the BACKGROUND_COLOR
        Gdx.gl.glClearColor(
                Constants.BACKGROUND_COLOR.r,
                Constants.BACKGROUND_COLOR.g,
                Constants.BACKGROUND_COLOR.b,
                Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



        // TODO: Set the SpriteBatch's projection matrix
        batch.setProjectionMatrix(gameplayViewport.getCamera().combined);

        //TODO: unnoted addition of renderer projection matrix setting unnoted removal of addition
      //  renderer.setProjectionMatrix(gameplayViewport.getCamera().combined);
        // TODO: unnoted removal Begin the SpriteBatch


        // TODO: Draw the standing right AtlasRegion for gigaGal **unnoted removal
        //TextureRegion region = Assets.instance.gigaGalAssets.standingRight;

      /*  batch.draw(
                region.getTexture(),
                50,
                50,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);*/
        //TODO: unnoted removal of batch begin/end then readdition
        batch.begin();
        // TODO: Render the Level unnoted change of declaration model
        level.render(batch);
        // TODO: End the SpriteBatch unnoted removal then unnoted readdition
        batch.end();
    }
}
