package com.samuellohjw.marsroverapp.model;

import java.util.Set;

public class RoverArguments {
    private final String startPosition;
    private final String commands;

    private final Set<Character> validDirections = Set.of('N', 'S', 'E', 'W');

    public RoverArguments(String startPosition, String commands) throws IllegalArgumentException {

        String errorStartPosition = checkStartPosition(startPosition);
        if (errorStartPosition != null) {
            throw new IllegalArgumentException(String.format("Invalid Start Position: '%s'\n", errorStartPosition));
        }
        String errorCommand = checkInvalidCommands(commands);
        System.out.println("here");
        System.out.println(errorCommand);
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
        String validCommands = "frlb";
        String[] commandArray = commands.split(",");

        for (String command : commandArray) {
            // Check if each character is not in the validCommands
            if (command.length() != 1 || validCommands.indexOf(command.charAt(0)) == -1) {
                return "Invalid character: " + command;
            }
        }

        return null;

    }

}
