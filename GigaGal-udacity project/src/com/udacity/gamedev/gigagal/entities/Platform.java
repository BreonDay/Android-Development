package com.udacity.gamedev.gigagal.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.udacity.gamedev.gigagal.utils.Assets;


public class Platform {

    // TODO: Add members for the platform top, bottom, left edge, right edge, width, and height

    float top;
    float bottom;
    float left;
    float right;


    public Platform(float left, float top, float width, float height) {
        // TODO: Populate the member variables
        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;
    }
    //TODO unnoted change from Shape renderer to Spritebatch
    public void render(SpriteBatch batch) {

        // TODO: Draw a box representing the platform
        float width = right - left;
        float height = top - bottom;

        //TODO unnoted removal of test data


        // TODO: Draw the platform using the NinePatch
        Assets.instance.platformAssets.platformNinePatch.draw(batch, left - 1, bottom - 1, width + 2, height + 2);

    }

}
