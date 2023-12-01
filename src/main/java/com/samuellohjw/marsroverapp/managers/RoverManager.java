package com.samuellohjw.marsroverapp.managers;

import com.samuellohjw.marsroverapp.model.Position;
import com.samuellohjw.marsroverapp.model.Rover;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class RoverManager {

    // List of Rovers

    // List of final positions of Rovers after they execute their command
    private final HashSet<Position> roverFinalPositions;

    private final char forward = 'f';
    private final char backward = 'b';
    private final char turnRight = 'r';
    private final char turnLeft = 'l';


    public RoverManager() {
        this.roverFinalPositions = new HashSet<>();
    }

    public Rover createRover(String startPosition) {
        Position position = getPositionFromStartPosition(startPosition);
        return new Rover(position);
    }

    public void executeCommands(Rover rover, String commands) {
        Position position = rover.getPosition();

        boolean stopRover = false;
        for (char command : commands.toCharArray()) {
            if (stopRover) break;

            switch (command) {
                case forward:
                    position.moveForward();
                    if (isExistingRoverInPosition(position)) {
                        position.moveBackward();
                        stopRover = true;
                    }
                    break;
                case backward:
                    position.moveBackward();
                    if (isExistingRoverInPosition(position)) {
                        position.moveBackward();
                        stopRover = true;
                    }
                    break;
                case turnRight:
                    position.turnRight();
                    break;
                case turnLeft:
                    position.turnLeft();
                    break;
            }
        }

        PrintFinalPosition(position);
        addFinalPosition(position);

    }

    public boolean isExistingRoverInPosition(Position position) {
        return roverFinalPositions.contains(position);
    }


    private void addFinalPosition(Position position) {
        this.roverFinalPositions.add(position);
    }

    /**
     * Takes in a start position string and returns a Position class. Note this method
     * assumes the start position string is of the correct format
     *
     * @param startPosition start position string
     * @return Position class with rovers starting position and direction
     */
    private Position getPositionFromStartPosition(String startPosition) {
        String[] tokens = startPosition.split(",");
        int x = Integer.parseInt(tokens[0]);
        int y = Integer.parseInt(tokens[1]);
        String direction = tokens[2];
        return new Position(x, y, direction);
    }

    private void PrintFinalPosition(Position position) {
        System.out.printf("Final Coordinate: %s, %s\n", position.getXString(), position.getYString());
        System.out.printf("Final Direction: %s\n", position.getDirectionString());
    }
}

