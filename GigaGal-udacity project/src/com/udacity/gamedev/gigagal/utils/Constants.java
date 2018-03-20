package com.udacity.gamedev.gigagal.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Constants {


    //WORLD/CAMERA
    // TODO: Set a WORLD_SIZE
    /**
     * We'll draw our sprites at their natural size, so this is the number of pixels of our Pixel
     * art that will fit on the screen. We're going to use this size to initialize both dimensions
     * of an ExtendViewport, and we'll run the game in landscape mode, so this will really end up
     * specifying the height of the world. We recommend 128.
     */
    public static final float WORLD_SIZE = 128;
    // TODO: Add a chase cam move speed constant
    public static final float CHASE_CAM_MOVE_SPEED = 128;
    // TODO: Set the height of the kill plane. Something like -100 works fine.
    public static final float KILL_PLANE = -100;
    // TODO: Set a BACKGROUND_COLOR We recommend Color.SKY
    public static final Color BACKGROUND_COLOR = Color.SKY;
    // TODO: Add constant for acceleration due to gravity
    // Something like 1000 works well.
    public static final float GRAVITY = WORLD_SIZE / 10;


    // the source of alot of my initial confusion this points to essientially an xml spritesheet
    // a pack.atlas contains information about each image on an associated png file gigagal.pack.png
    //the pack.atlas contains the name and location on the sheet for easy unpacking for use
    //it also contains any changes made while packing the images that need to be unchanged
    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    //GIGAGAL
    // TODO: Add a constant for the name of the standing-right and left sprite
    //these are image names in the pack.atlas
    public static final String STANDING_RIGHT = "standing-right";
    public static final String STANDING_LEFT = "standing-left";

    // TODO: Add constants for the jumping-right and jumping-left sprites
    public static final String JUMPING_RIGHT = "jumping-right";
    public static final String JUMPING_LEFT = "jumping-left";

    //TODO add constants for the first frame of gigagals walk animation
    public static final String WALKING_RIGHT_1 = "walk-1-right";
    public static final String WALKING_LEFT_1 = "walk-1-left";

    // TODO: Add constants for walk-2-right and walk-2-left sprite names
    public static final String WALKING_RIGHT_2 = "walk-2-right";
    public static final String WALKING_LEFT_2 = "walk-2-left";

    // TODO: Add constants for walk-3-right and walk-3-left sprite names
    public static final String WALKING_RIGHT_3 = "walk-3-right";
    public static final String WALKING_LEFT_3 = "walk-3-left";

    // TODO: Add constant for walk loop duration
    // Something like 0.25 works well.
    public static final float WALK_LOOP_DURATION = 0.25f;

    // TODO: Define a Vector2 Constant for GigaGal's eye position within her sprites (16, 24)
    public static final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16, 24);

    // TODO: Define a float constant for the height of GigaGal's eye above her feet (16)
    public static final float GIGAGAL_EYE_HEIGHT = 16.0f;

    // TODO: Add constant for GigaGal's stance width
    public static final float GIGAGAL_STANCE_WIDTH = 21.0f;

    public static final float GIGAGAL_HEIGHT = 23.0f;

    // TODO: Add a constant for GigaGal's movement speed
    public static final float GIGAGAL_MOVE_SPEED =WORLD_SIZE / 2;

    // TODO: Add constant for GigaGal's jump speed
    // Something around 250 works well.
    public static final float JUMP_SPEED = 1.5f * WORLD_SIZE;

    // TODO: Add constant for GigaGal's max jump duration
    // Meaning how long you can hold the jump key to continue to jump higher. 0.15 seconds works well
    public static final float MAX_JUMP_DURATION = .1f;

    // TODO: Add Vector2 constant for knockback velocity (200, 200 works well)
    public static final Vector2 KNOCKBACK_VELOCITY = new Vector2(200, 200);

    //PLATFORM
    // TODO: Add String constant for the name of the platform sprite
    public static final String PLATFORM_SPRITE = "platform";

    // TODO: Add a constant holding the size of the stretchable edges in the platform 9 patch
    // (8 pixels)
    public static final int PLATFORM_EDGE = 8;



    // ENEMY
    // TODO: Set constant for the filename of the enemy sprite ("enemy")
    public static final String ENEMY_SPRITE = "enemy";

    // TODO: Set constant for the center of the enemy (14, 22)
    public static final Vector2 ENEMY_CENTER = new Vector2(14, 22);
    // TODO: Add a constant for ENEMY_MOVEMENT_SPEED. 10 works well.
    public static final float ENEMY_MOVEMENT_SPEED = WORLD_SIZE/5;

    // TODO: Add ENEMY_BOB_AMPLITUDE constant! 2 works well.
    public static final float ENEMY_BOB_AMPLITUDE = 2;

    // TODO: Add ENEMY_BOB_PERIOD constant. About 3 seconds works fine.
    public static final float ENEMY_BOB_PERIOD = 3.0f;

    // TODO: Add constant for enemy collision radius (15 is about right)
    public static final float ENEMY_COLLISION_RADIUS = 15;


    // TODO: Note the constants we've added for the bullets
    public static final String BULLET_SPRITE = "bullet";
    public static final Vector2 BULLET_CENTER = new Vector2(3, 2);

    // TODO: Note the constants we've added for the explosions
    public static final String EXPLOSION_LARGE = "explosion-large";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final Vector2 EXPLOSION_CENTER = new Vector2(8, 8);
    public static final float EXPLOSION_DURATION = 0.5f;

    // TODO: Note the constants we've added for the powerups
    public static final String POWERUP_SPRITE = "powerup";
    public static final Vector2 POWERUP_CENTER = new Vector2(7, 5);


}
