package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.utils.Assets;
import com.udacity.gamedev.gigagal.utils.Constants;
import com.udacity.gamedev.gigagal.Level;
import com.udacity.gamedev.gigagal.utils.Enums;
import com.udacity.gamedev.gigagal.utils.Enums.Direction;
import com.udacity.gamedev.gigagal.utils.Enums.JumpState;
import com.udacity.gamedev.gigagal.utils.Enums.WalkState;

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();
    // TODO: Add a spawnLocation Vector2
    Vector2 spawnLocation;

    // TODO: Add a position unnoted change must be made public for the camera to follow her
    public Vector2 position;
    // TODO: Add a Vector2 for GigaGal's velocity
    Vector2 velocity;

    // TODO: Add Vector2 to hold GigaGal's postion last frame accidentally added early in the notes
    Vector2 lastFramePosition;


    //TODO: unnoted change and addition of level variable
    Level level;


    // TODO: DO THIS FIRST!!! Create an enum called Facing, with LEFT and RIGHT members
    enum Facing {
        LEFT, RIGHT
    }




    // TODO: Add a JumpState
    JumpState jumpState;

    // TODO: Add WalkState member
    WalkState walkState;

    // TODO: Add a Facing member variable (defined below)
    Facing facing;

    // TODO: Add a long for jumpStartTime
    long jumpStartTime;

    // TODO: Add a walkStartTime
    long walkStartTime;



    // TODO: Make the constructor take a spawn position
    public GigaGal(Vector2 spawnLocation,Level level) {
        this.level=level;

        // TODO: Set the spawnlocation based on spawnLn
        this.spawnLocation = spawnLocation;

        // TODO: Create a new Vector2s for position, lastFramePosition, and velocity
        position = new Vector2();
        lastFramePosition = new Vector2();
        velocity = new Vector2();
        // TODO: Call init()
        // can be used to reinitialize on death
        init();
    }

    public void init()
    {
        // TODO: Set GG back to her spawnLocation
        position.set(spawnLocation);

        // TODO: Set lastFramePosition
        lastFramePosition.set(spawnLocation);

        // TODO: Set velocity to zero
        velocity.setZero();

        // TODO: Set jumpState to FALLING
        jumpState = JumpState.FALLING;

        // TODO: Set Facing to RIGHT
        facing = Facing.RIGHT;

        // TODO: Set walkState to STANDING
        walkState = WalkState.STANDING;
    }
    // Note that we're now passing in the platform array to GigaGal's update method
    public void update(float delta, Array<Platform> platforms) {
        // TODO: Initialize a new Vector2 for lastFramePosition
        lastFramePosition.set(position);

        // TODO: Accelerate GigaGal down unnoted change to just Constants.GRAVITY
        // Multiple delta by the acceleration due to gravity and subtract it from GG's vertical velocity
        velocity.y -= Constants.GRAVITY;

        // TODO: Apply GigaGal's velocity to her position
        // Vector2.mulAdd() is very convenient.
        //essentially its saying multiply velocity*delta and add it to position
        position.mulAdd(velocity, delta);

        // TODO: If GigaGal is below the kill plane, call init()
        if (position.y < Constants.KILL_PLANE) {
            init();
        }


        if (jumpState != JumpState.JUMPING) {
            // TODO: If GigaGal is not RECOILING, set FALLING jump state
            if (jumpState != JumpState.RECOILING) {
                jumpState = JumpState.FALLING;
            }

            for (Platform platform : platforms) {
                if (landedOnPlatform(platform)) {

                    // TODO: If true, set jumpState to GROUNDED
                    jumpState = JumpState.GROUNDED;

                    // TODO: Zero vertical velocity
                    velocity.y = 0;

                    // TODO: Zero horizontal velocity
                    velocity.x = 0;

                    // TODO: Make sure GigaGal's feet aren't sticking into the platform
                    position.y = platform.top + Constants.GIGAGAL_EYE_HEIGHT;
                }
            }
        }

        // TODO: Define GigaGal bounding rectangle
        // Use GigaGal's constants for height and stance width

        Rectangle gigaGalBounds = new Rectangle(
                position.x - Constants.GIGAGAL_STANCE_WIDTH / 2,
                position.y - Constants.GIGAGAL_EYE_HEIGHT,
                Constants.GIGAGAL_STANCE_WIDTH,
                Constants.GIGAGAL_HEIGHT);

        for (Enemy enemy : level.getEnemies()) {
            // TODO: Define enemy bounding rectangle
            // You'll want to define an enemy collision radius constant
            Rectangle enemyBounds = new Rectangle(
                    enemy.position.x - Constants.ENEMY_COLLISION_RADIUS,
                    enemy.position.y - Constants.ENEMY_COLLISION_RADIUS,
                    2 * Constants.ENEMY_COLLISION_RADIUS,
                    2 * Constants.ENEMY_COLLISION_RADIUS
            );

            // TODO: If GigaGal overlaps an enemy, log the direction from which she hit it
            // Use Rectangle.overlaps() to make it easy!

            if (gigaGalBounds.overlaps(enemyBounds)) {
                if (position.x < enemy.position.x) {
                    recoilFromEnemy(Direction.LEFT);
                } else {
                    recoilFromEnemy(Direction.RIGHT);
                }
            }

        }

        // TODO: Use Gdx.input.isKeyPressed() to check if the left/right arrow key is pressed
        // TODO: If so, call moveLeft()/moveRight()
        // Move left/right
        // TODO: Disable left/right movement if RECOILING
        if (jumpState != JumpState.RECOILING)
        {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                moveLeft(delta);
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                moveRight(delta);
            } else
            {
                // TODO: Set walkState to STANDING
                walkState = WalkState.STANDING;
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            // TODO: Handle jump key
            // Add a switch statement. If the jump key is pressed and GG is GROUNDED, then startJump()
            // If she's JUMPING, then continueJump()
            // If she's falling, then don't do anything
            switch (jumpState) {
                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();
                    break;
            }
        } else {
            // TODO: If the jump key wasn't pressed, endJump()
            endJump();
        }

    }


    boolean landedOnPlatform(Platform platform) {
        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        // TODO: First check if GigaGal's feet were above the platform top last frame and below the platform top this frame
        if (lastFramePosition.y - Constants.GIGAGAL_EYE_HEIGHT >= platform.top &&
                position.y - Constants.GIGAGAL_EYE_HEIGHT < platform.top) {

            // TODO: If so, find the position of GigaGal's left and right toes
            float leftFoot = position.x - Constants.GIGAGAL_STANCE_WIDTH / 2;
            float rightFoot = position.x + Constants.GIGAGAL_STANCE_WIDTH / 2;

            // TODO: See if either of GigaGal's toes are on the platform
            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);

            // TODO: See if GigaGal is straddling the platform
            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }

        // TODO: Return whether or not GigaGal had landed on the platform
        return leftFootIn || rightFootIn || straddle;
    }

    private void moveLeft(float delta) {
        // TODO: If we're GROUNDED and not WALKING, save the walkStartTime
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }
        // TODO: Set walkState to WALKING
        walkState = WalkState.WALKING;
        // TODO: Update facing direction
        facing = Facing.LEFT;
        // TODO: Move GigaGal left by delta * movement speed
        position.x -= delta * Constants.GIGAGAL_MOVE_SPEED;
    }

    private void moveRight(float delta) {
        // TODO: If we're GROUNDED and not WALKING, save the walkStartTime
        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) {
            walkStartTime = TimeUtils.nanoTime();
        }

        // TODO: Set walkState to WALKING
        walkState = WalkState.WALKING;
        // TODO: Update facing direction
        facing = Facing.RIGHT;
        // TODO: Same for moving GigaGal right
        position.x += delta * Constants.GIGAGAL_MOVE_SPEED;
    }

    private void startJump() {
        // TODO: Set jumpState to JUMPING
        jumpState = JumpState.JUMPING;
        // TODO: Set the jump start time
        // Using TimeUtils.nanoTime()
        jumpStartTime = TimeUtils.nanoTime();
        // TODO: Call continueJump()
        continueJump();
    }

    private void continueJump() {
        // TODO: First, check if we're JUMPING, if not, just return
        if (jumpState == JumpState.JUMPING) {
            // TODO: Find out how long we've been jumping
            float jumpDuration = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);
            // TODO: If we have been jumping for less than the max jump duration, set GG's vertical speed to the jump speed constant
            // TODO: Else, call endJump()
            if (jumpDuration < Constants.MAX_JUMP_DURATION) {
                velocity.y = Constants.JUMP_SPEED;
            } else {
                endJump();
            }
        }
    }

    private void endJump() {
        // TODO: If we're JUMPING, now we're FALLING
        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }
    }

    private void recoilFromEnemy(Direction direction) {

        // TODO: Set RECOILING jump state
        jumpState = JumpState.RECOILING;

        // TODO: Set GigaGal's vertical speed
        velocity.y = Constants.KNOCKBACK_VELOCITY.y;

        // TODO: Set GigaGal's horizontal speed (in the correct direction)
        if (direction == Direction.LEFT) {
            velocity.x = -Constants.KNOCKBACK_VELOCITY.x;
        } else {
            velocity.x = Constants.KNOCKBACK_VELOCITY.x;
        }
    }

    public void render(SpriteBatch batch) {

        // TODO: Draw GigaGal's standing-right sprite at position - GIGAGAL_EYE_POSITION
        TextureRegion region = Assets.instance.gigaGalAssets.standingRight;

        // TODO: Select the correct sprite based on facing, jumpState, and walkState
        if (facing == Facing.RIGHT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingRight;
        } else if (facing == Facing.RIGHT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingRight;
        } else if (facing == Facing.RIGHT && walkState == WalkState.WALKING) {

            // TODO: Calculate how long we've been walking in seconds
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);

            // TODO: Select the correct frame from the walking right animation
            region = Assets.instance.gigaGalAssets.walkingRightAnimation.getKeyFrame(walkTimeSeconds);
        } else if (facing == Facing.LEFT && jumpState != JumpState.GROUNDED) {
            region = Assets.instance.gigaGalAssets.jumpingLeft;
        } else if (facing == Facing.LEFT && walkState == WalkState.STANDING) {
            region = Assets.instance.gigaGalAssets.standingLeft;
        } else if (facing == Facing.LEFT && walkState == WalkState.WALKING) {

            // TODO: Calculate how long we've been walking in seconds
            float walkTimeSeconds = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);

            // TODO: Select the correct frame from the walking left animation
            region = Assets.instance.gigaGalAssets.walkingLeftAnimation.getKeyFrame(walkTimeSeconds);
        }

        batch.draw(
                region.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
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
                false);
    }
    public Vector2 getPosition() {
        return position;
    }
}

