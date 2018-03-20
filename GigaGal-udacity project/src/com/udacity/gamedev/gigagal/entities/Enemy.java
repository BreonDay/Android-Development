package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.udacity.gamedev.gigagal.utils.Assets;
import com.udacity.gamedev.gigagal.utils.Constants;
import com.udacity.gamedev.gigagal.utils.Enums.Direction;
import com.udacity.gamedev.gigagal.utils.Utils;


public class Enemy {

    // TODO: Add a Platform
    private final Platform platform;

    // TODO: Add a Vector2 position
    public Vector2 position;

    private Direction direction;

    // TODO: Add start time
    final long startTime;

    public Enemy(Platform platform) {

        // TODO: Initialize the platform member variable
        this.platform = platform;

        direction = Direction.RIGHT;

        // TODO: Position the enemy at the top left of the platform
        position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);

        // TODO: Set start time
        startTime = TimeUtils.nanoTime();

    }

    public void update(float delta) {
        // TODO: Move the enemy left/right the appropriate amount
        // Using the delta time and the newly created enemy movement speed constant

        switch (direction) {
            case LEFT:
                position.x -= Constants.ENEMY_MOVEMENT_SPEED * delta;
                break;
            case RIGHT:
                position.x += Constants.ENEMY_MOVEMENT_SPEED * delta;
        }

        // TODO: If the enemy is off the left side of the platform, set the enemy moving back to the right
        // Should also probably put the enemy back on the edge of the platform

        // TODO: If the enemy if off the right side of the platform, set the enemy moving back to the left
        if (position.x < platform.left) {
            position.x = platform.left;
            direction = Direction.RIGHT;
        } else if (position.x > platform.right) {
            position.x = platform.right;
            direction = Direction.LEFT;
        }
        // TODO: Figure out where in the bob cycle we're at
        // bobMultiplier = 1 + sin(2 PI elapsedTime / period)
        final float elapsedTime = Utils.secondsSince(startTime);
        final float bobMultiplier = 1 + MathUtils.sin(MathUtils.PI2 * elapsedTime / Constants.ENEMY_BOB_PERIOD);

        // TODO: Set the enemy vertical position
        // y = platformTop + enemyCenter + bobAmplitude * bobMultiplier

        position.y = platform.top + Constants.ENEMY_CENTER.y + Constants.ENEMY_BOB_AMPLITUDE * bobMultiplier;
    }

    public void render(SpriteBatch batch) {

        // TODO: Complete render()
        final TextureRegion region = Assets.instance.enemyAssets.enemy;
        Utils.drawTextureRegion(batch, region, position, Constants.ENEMY_CENTER);
    }
}
