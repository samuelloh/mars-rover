package com.samuellohjw.marsroverapp.commandLineRunner;

import com.samuellohjw.marsroverapp.model.RoverArguments;
import com.samuellohjw.marsroverapp.service.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoverCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RoverCommandLineRunner.class);
    private final RoverService roverService;

    public RoverCommandLineRunner(RoverService roverService) {
        this.roverService = roverService;
    }

    @Override
    public void run(String... args) {
        if (args.length < 3) {
            logger.error("Please provide appropriate command-line arguments.\n" +
                    "Usage: <No. of rovers> [<Position> <commands>]\n" +
                    "Example: 2 4,3,N f,b,r,l 2,3,S f,b");
            return;
        }
        ArrayList<RoverArguments> roverList = new ArrayList<>();

        // Validate and store all rover start positions and commands
        try {
            int numRovers = Integer.parseInt(args[0]);

            if ((args.length - 1) != 2 * numRovers) {
                logger.error("The number of remaining arguments is not 2 times the initial number of rovers stated.");
                return;
            }

            for (int i = 0; i < numRovers; i++) {
                String startPosition = args[i * 2 + 1];
                String commands = args[i * 2 + 2];
                RoverArguments roverArg = new RoverArguments(startPosition, commands);
                roverList.add(roverArg);
            }

        } catch (NumberFormatException e) {
            logger.error("The first argument is not a valid integer.");
        } catch (IllegalArgumentException e) {
            logger.error(String.format("Invalid Rover Arguments |%s\n", e.getMessage()));
        }

        for (RoverArguments roverArguments : roverList) {
            roverService.createRoverAndExecuteCommands(roverArguments.getStartPosition(), roverArguments.getCommands());
        }

        // Finally, print all rover positions in the order they were inserted in
        roverService.printFinalPositionsOfAllRovers();
    }
}

