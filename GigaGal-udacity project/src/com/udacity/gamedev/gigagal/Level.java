package com.udacity.gamedev.gigagal;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.udacity.gamedev.gigagal.entities.Enemy;
import com.udacity.gamedev.gigagal.entities.GigaGal;
import com.udacity.gamedev.gigagal.entities.Platform;
import com.udacity.gamedev.gigagal.utils.Assets;
import com.udacity.gamedev.gigagal.utils.Constants;
import com.udacity.gamedev.gigagal.utils.Utils;

public class Level {
    //TODO unnoted addition of tag and viewport
    public static final String TAG = Level.class.getName();
    private Viewport viewport;

    // TODO: Add a GigaGal member variable
    GigaGal gigaGal;


    // TODO: Add an Array of Platforms
    Array<Platform> platforms;

    // TODO: Add a DelayedRemovalArray of enemies
    private DelayedRemovalArray<Enemy> enemies;


    public Level(Viewport viewport) {

        this.viewport=viewport;


        initDebugLevel();

    }

    public void update(float delta) {
        // TODO: Update GigaGal
        gigaGal.update(delta,platforms);

        // TODO: Update the enemies (doesn't do anything yet)
        for (int i = 0; i < enemies.size; i++) {
            Enemy enemy = enemies.get(i);
            enemy.update(delta);
        }
    }

    public void render(SpriteBatch batch) {
        //TODO unnoted movement of batch begin/end to level render unnoted removal

        // TODO: Render all platforms in the platform array
        for (Platform platform : platforms) {
            platform.render(batch);
        }

        // TODO: Render the enemies
        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }




        // TODO: Render GigaGal
        gigaGal.render(batch);
        // TODO: Test draw the bullet
        Utils.drawTextureRegion(
                batch,
                Assets.instance.bulletAssets.bullet,
                new Vector2(0, 0),
                Constants.BULLET_CENTER
        );
        // TODO: Test draw the powerup
        Utils.drawTextureRegion(
                batch,
                Assets.instance.powerupAssets.powerup,
                new Vector2(20, 0),
                Constants.POWERUP_CENTER
        );
        // TODO: Test draw the first frame of the explosion
        Utils.drawTextureRegion(
                batch,
                Assets.instance.explosionAssets.explosion.getKeyFrame(0),
                new Vector2(40,0),
                Constants.EXPLOSION_CENTER
        );

        // TODO: Test draw the second frame of the explosion
        Utils.drawTextureRegion(
                batch,
                Assets.instance.explosionAssets.explosion.getKeyFrame(Constants.EXPLOSION_DURATION * 0.5f),
                new Vector2(60,0),
                Constants.EXPLOSION_CENTER
        );

        // TODO: Test draw the third frame of the explosion
        Utils.drawTextureRegion(
                batch,
                Assets.instance.explosionAssets.explosion.getKeyFrame(Constants.EXPLOSION_DURATION * 0.75f),
                new Vector2(80, 0),
                Constants.EXPLOSION_CENTER
        );


        //TODO: unnoted removal of batch.end()



    }

    private void initDebugLevel(){

        // TODO: Initialize enemies array
        enemies = new DelayedRemovalArray<Enemy>();

        // TODO: Initialize the platform array
        platforms = new Array<Platform>();


        Platform enemyPlatform = new Platform(75, 90, 100, 65);

        // TODO: Add an enemy sitting on enemyPlatform

        enemies.add(new Enemy(enemyPlatform));

        platforms.add(enemyPlatform);




        // TODO: Add a test platform
        //x,y,width,height
        platforms.add(new Platform(-130, 90, 20, 20));

        platforms.add(new Platform(0, 70, 20, 20));
        platforms.add(new Platform(-20, 50, 20, 20));

        platforms.add(new Platform(-40, 30, 20, 20));
        platforms.add(new Platform(-60, 30, 20, 20));

        platforms.add(new Platform(-80, 50, 20, 20));
        platforms.add(new Platform(-100, 70, 20, 20));

        platforms.add(new Platform(-50, 80, 20, 20));

        platforms.add(new Platform(-20,100, 20, 20));
        platforms.add(new Platform(-80, 100, 20, 20));

        platforms.add(new Platform(30, 90, 20, 20));
        //TODO: Initialize GigaGal make sure shes added after the platforms
        gigaGal = new GigaGal(new Vector2(-60,100),this);

    }

    //TODO unnoted addition of setters and getters
    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public GigaGal getGigaGal() {
        return gigaGal;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void setGigaGal(GigaGal gigaGal) {
        this.gigaGal = gigaGal;
    }



}

