package com.udacity.gamedev.gigagal.utils;


public class Enums {


    public enum Direction {
        LEFT, RIGHT
    }

    public enum JumpState {JUMPING, FALLING,GROUNDED,RECOILING}

    public enum WalkState {
        NOT_WALKING,
        WALKING,
        STANDING
    }
}
