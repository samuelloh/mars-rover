package com.samuellohjw.marsroverapp.service;

import com.samuellohjw.marsroverapp.exceptions.IllegalRoverPositionException;
import com.samuellohjw.marsroverapp.managers.RoverManager;
import com.samuellohjw.marsroverapp.model.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoverService {
    private static final Logger logger = LoggerFactory.getLogger(RoverService.class);
    private static final RoverManager roverManager = new RoverManager();

    public void createRoverAndExecuteCommands(String startPosition, String commands) {
        try {
            // Create Rover Logic
            Rover rover = createRover(startPosition);

            // Logic for executing Rover Commands logic
            executeCommands(rover, commands);


        } catch (IllegalRoverPositionException e) {
            logger.error(String.format("Error creating rover: %s", e.getMessage()));
            logger.info("Skipping rover and moving on to next");
        }


    }

    private Rover createRover(String startPosition) throws IllegalRoverPositionException {
        Rover rover = roverManager.createRover(startPosition);
        if (roverManager.isExistingRoverInPosition(rover.getPosition())) {
            throw new IllegalRoverPositionException();
        }
        return rover;
    }

    private void executeCommands(Rover rover, String commands) {
        roverManager.executeCommands(rover, commands);
    }

}
