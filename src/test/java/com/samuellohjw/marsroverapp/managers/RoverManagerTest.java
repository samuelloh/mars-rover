package com.samuellohjw.marsroverapp.managers;

import com.samuellohjw.marsroverapp.model.Position;
import com.samuellohjw.marsroverapp.model.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverManagerTest {
    private static final int startXCoordinate = 3;
    private static final int startYCoordinate = 4;
    private static final String startDirection = "N";

    private static final String validStartPosition =
            startXCoordinate + "," + startYCoordinate + "," + startDirection;
    private static final Rover validRover = new Rover(new Position(startXCoordinate, startYCoordinate, startDirection));
    private static final String validRoverCommands = "f,f,l,l,b,b,r";

    @Test
    void testCreateRover() {
        RoverManager manager = new RoverManager();
        Rover rover = manager.createRover(validStartPosition);

        assertEquals(rover, validRover);
    }

    @Test
    void testExecuteValidCommands() {
        RoverManager manager = new RoverManager();
        manager.executeCommands(validRover, validRoverCommands);

        assertEquals(validRover.getPosition(), new Position(startXCoordinate, startYCoordinate + 4, "W"));
    }

    @Test
    void testIsExistingRoverInPositionTrue() {
        RoverManager manager = new RoverManager();
        manager.executeCommands(validRover, "");

        assertTrue(manager.isExistingRoverInPosition(validRover.getPosition()));
    }

    @Test
    void testIsExistingRoverInPositionFalse() {
        RoverManager manager = new RoverManager();
        manager.executeCommands(validRover, "");

        assertFalse(manager.isExistingRoverInPosition(new Position(1, 1, "E")));
    }
}