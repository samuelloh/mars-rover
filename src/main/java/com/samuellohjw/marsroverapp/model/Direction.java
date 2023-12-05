package com.samuellohjw.marsroverapp.model;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    // Method to get the next direction when turning clockwise by 90 degrees
    public Direction clockwise() {
        return values()[(ordinal() + 1) % values().length];
    }

    // Method to get the next direction when turning anticlockwise by 90 degrees
    public Direction anticlockwise() {
        int newIndex = ordinal() - 1;
        return values()[(newIndex >= 0) ? newIndex : values().length - 1];
    }
}
