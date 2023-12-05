package com.samuellohjw.marsroverapp.model;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoverArguments {
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
