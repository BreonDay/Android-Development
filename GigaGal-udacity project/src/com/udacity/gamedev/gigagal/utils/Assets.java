package com.udacity.gamedev.gigagal.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;


/**
 * TODO: Check out how the Assets singleton works
 * <p>
 * This utility class holds onto all the assets used in GigaGal. It's a singleton, so the
 * constructor is private, and a single instance is created when the class is loaded. That way all
 * the entities that make up GigaGal can access their sprites in the same place, and no work loading
 * up textures is repeated.
 * <p>
 * Each entity gets its own inner class to hold its assets. Below you'll complete the GigaGalAssets
 * class by finding up the standing-right AtlasRegion within the TextureAtlas loaded in init() .
 */

public class Assets implements Disposable, AssetErrorListener {

    public static final String TAG = Assets.class.getName();

    public static final Assets instance = new Assets();

    // TODO: Add instances of BulletAssets, ExplosionAssets, and PowerupAssets
    // Define those classes below
    public BulletAssets bulletAssets;
    public ExplosionAssets explosionAssets;
    public PowerupAssets powerupAssets;

    public GigaGalAssets gigaGalAssets;

    // TODO: Add EnemyAssets member (defined at the bottom of the file. Start there)
    public EnemyAssets enemyAssets;

    // TODO: Add a PlatformAssets member
    public PlatformAssets platformAssets;

    private AssetManager assetManager;



    private Assets() {
    }

    public void init(AssetManager assetManager) {
        // the asset manager Loads and stores assets like textures, bitmapfonts, tile maps,
        // sounds, music and so on.
        //in this case we are loading the atlas region
        this.assetManager = new AssetManager();
        assetManager.setErrorListener(this);
        //loads the /images.gigaGal.pack.atlas as a type texture atlas
        // which is a usable unpacked atlas
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS);
        gigaGalAssets = new GigaGalAssets(atlas);

        // TODO: Initialize platformAssets, passing in the atlas
        platformAssets = new PlatformAssets(atlas);
        // TODO: Initialize enemyAssets
        enemyAssets = new EnemyAssets(atlas);

        // TODO: Initialize bulletAssets, explosionAssets, and powerupAssets
        bulletAssets = new BulletAssets(atlas);
        explosionAssets = new ExplosionAssets(atlas);
        powerupAssets = new PowerupAssets(atlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, "Couldn't load asset: " + asset.fileName, throwable);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    //class to handle gigagals animations and sprites
    public class GigaGalAssets {
      //list of nessacary variables
        // TODO: Add a AtlasRegion to hold the standing-right sprite
        public final AtlasRegion standingRight;

        // TODO: Add another AtlasRegion for the standing-left sprite
        public final AtlasRegion standingLeft;

        // TODO: Add AtlasRegions for jumping-left and jumping-right
        public final AtlasRegion jumpingLeft;
        public final AtlasRegion jumpingRight;

        // TODO: Add AtlasRegions for walkingLeft and walkingRight sprites
        public final AtlasRegion walkingLeft;
        public final AtlasRegion walkingRight;

        // TODO: Add animations for walking left and walking right
        public final Animation walkingLeftAnimation;
        public final Animation walkingRightAnimation;
        // constructor where variables are initialized
        public GigaGalAssets(TextureAtlas atlas) {
            // TODO: Use atlas.findRegion() to initialize the standing right AtlasRegion
            standingRight = atlas.findRegion(Constants.STANDING_RIGHT);

            // TODO: Find jumping-left and jumping-right
            jumpingLeft = atlas.findRegion(Constants.JUMPING_LEFT);
            jumpingRight = atlas.findRegion(Constants.JUMPING_RIGHT);

            // TODO: Find the standing-left AtlasRegion the png extension is assumed
            standingLeft = atlas.findRegion(Constants.STANDING_LEFT);

            // TODO: Find walkingLeft and walkingRight regions
            walkingLeft = atlas.findRegion(Constants.WALKING_LEFT_2);
            walkingRight = atlas.findRegion(Constants.WALKING_RIGHT_2);

            // TODO: Create an Array of AtlasRegions to hold the walking left frames
            Array<AtlasRegion> walkingLeftFrames = new Array<AtlasRegion>();

            // TODO: Add the proper frames to the array
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_1));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_2));
            walkingLeftFrames.add(atlas.findRegion(Constants.WALKING_LEFT_3));

            // TODO: Create the walking left animation
            walkingLeftAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingLeftFrames, Animation.PlayMode.LOOP);

            // TODO: Do the same with the walking right frames
            Array<AtlasRegion> walkingRightFrames = new Array<AtlasRegion>();
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_1));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_2));
            walkingRightFrames.add(atlas.findRegion(Constants.WALKING_RIGHT_3));
            walkingRightAnimation = new Animation(Constants.WALK_LOOP_DURATION, walkingRightFrames, Animation.PlayMode.LOOP);
        }
    }

    public class PlatformAssets {

        // TODO: Add a NinePatch member
        public final NinePatch platformNinePatch;

        public PlatformAssets(TextureAtlas atlas) {

            // TODO: Find the AtlasRegion holding the platform
            AtlasRegion region = atlas.findRegion(Constants.PLATFORM_SPRITE);

            // TODO: Turn that AtlasRegion into a NinePatch using the edge constant you defined
            int edge = Constants.PLATFORM_EDGE;
            platformNinePatch = new NinePatch(region, edge, edge, edge, edge);
        }
    }

    public class EnemyAssets {

        // TODO: Add AtlasRegion for the enemy
        public final AtlasRegion enemy;

        public EnemyAssets(TextureAtlas atlas) {
            // TODO: Save the enemy sprite
            enemy = atlas.findRegion(Constants.ENEMY_SPRITE);
        }
    }

    public class BulletAssets {

        // TODO: Add an AtlasRegion to hold the bullet sprite
        public final AtlasRegion bullet;

        public BulletAssets(TextureAtlas atlas) {
            // TODO: Find the bullet atlas region
            bullet = atlas.findRegion(Constants.BULLET_SPRITE);
        }

    }

    public class ExplosionAssets {

        // TODO: Add an Animation
        public final Animation explosion;

        public ExplosionAssets(TextureAtlas atlas) {

            // TODO: Populate the explosion animation
            // First find the appropriate AtlasRegions
            // Then pack them into an animation with the correct frame duration

            Array<AtlasRegion> explosionRegions = new Array<AtlasRegion>();
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_LARGE));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_MEDIUM));
            explosionRegions.add(atlas.findRegion(Constants.EXPLOSION_SMALL));

            explosion = new Animation(Constants.EXPLOSION_DURATION / explosionRegions.size,
                    explosionRegions, Animation.PlayMode.NORMAL);
        }
    }

    public class PowerupAssets {

        // TODO: Add an AtlasRegion to hold the powerup sprite
        public final AtlasRegion powerup;

        public PowerupAssets(TextureAtlas atlas) {
            // TODO: Find the powerup atlas region
            powerup = atlas.findRegion(Constants.POWERUP_SPRITE);
        }
    }

}
