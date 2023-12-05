package com.samuellohjw.marsroverapp.commandLineRunner;

import com.samuellohjw.marsroverapp.service.RoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    }

}

class RoverArguments {
    private final String startPosition;
    private final String commands;

    private final Set<Character> validDirections = Set.of('N', 'S', 'E', 'W');
    private final String validCommands = "frlb";

    public RoverArguments(String startPosition, String commands) throws IllegalArgumentException {

        String errorStartPosition = checkStartPosition(startPosition);
        if (errorStartPosition != null) {
            throw new IllegalArgumentException(String.format("Invalid Start Position: '%s'\n", errorStartPosition));
        }
        String errorCommand = checkInvalidCommands(commands);
        if (errorCommand != null) {
            throw new IllegalArgumentException(String.format("invalid Rover command: '%s'\n", errorCommand));
        }
        this.startPosition = startPosition;
        this.commands = commands;
    }


    public String getStartPosition() {
        return startPosition;
    }

    public String getCommands() {
        return commands;
    }

    private String checkStartPosition(String startPosition) {
        // Check if the string has length 5 (3 characters separated by comma)
        if (startPosition.length() != 5) {
            return "Start position must have 3 characters separated by commas";
        }

        // Split the string by comma
        String[] parts = startPosition.split(",");
        if (parts.length != 3) {
            return "Start Position must have exactly three parts separated by commas";
        }

        // Check if the first two characters are integers
        try {
            Integer.parseInt(parts[0]);
            Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            return "First two parts must be integers";
        }

        // Check if the third character is a direction
        char direction = parts[2].charAt(0);
        if (parts[2].length() != 1 || !validDirections.contains(direction)) {
            return "Third part must be N, S, W, or E";
        }

        return null;
    }

    private String checkInvalidCommands(String commands) {
        Pattern pattern = Pattern.compile("^[" + validCommands + "]([,][" + validCommands + "])*$");
        Matcher matcher = pattern.matcher(commands);

        if (!matcher.matches()) {
            Matcher invalidMatcher = Pattern.compile("(?<!^)(?!$)[^" + validCommands + ",]").matcher(commands);
            while (invalidMatcher.find()) {
                String invalidCharacter = invalidMatcher.group();
                return "Invalid character: " + invalidCharacter;
            }
        }
        return null;
    }

}