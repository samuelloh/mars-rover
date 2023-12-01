package com.samuellohjw.marsroverapp.model;

public class Position {

    private int x;
    private int y;

    private Direction direction;

    public String getXString() {
        return String.valueOf(x);
    }

    public String getYString() {
        return String.valueOf(y);
    }

    public String getDirectionString() {
        return switch (this.direction) {
            case NORTH -> "North";
            case SOUTH -> "South";
            case EAST -> "East";
            case WEST -> "West";
        };
    }

    public Position(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = parseDirection(direction);
    }

    private Direction parseDirection(String direction) {
        return switch (direction) {
            case "N" -> Direction.NORTH;
            case "S" -> Direction.SOUTH;
            case "E" -> Direction.EAST;
            case "W" -> Direction.WEST;
            default -> null;
        };
    }

    public void moveForward() {
        switch (this.direction) {
            case NORTH -> this.y++;
            case SOUTH -> this.y--;
            case EAST -> this.x++;
            case WEST -> this.x--;
        }
    }

    public void moveBackward() {
        switch (this.direction) {
            case NORTH -> this.y--;
            case SOUTH -> this.y++;
            case EAST -> this.x--;
            case WEST -> this.x++;
        }
    }

    public void turnRight() {
        this.direction = direction.clockwise();
    }

    public void turnLeft() {
        this.direction = direction.anticlockwise();
    }

    @Override
    public boolean equals(Object obj) {
        // We are only concerned for the x,y coordinates, not the direction
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }

    @Override
    public String toString() {
        return "Position: ( x-coord: " + x + ", y-coord: " + y + ")";
    }


}
