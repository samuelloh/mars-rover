package com.samuellohjw.marsroverapp.commandLineRunner;

import com.samuellohjw.marsroverapp.model.RoverArguments;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class RoverArgumentsTest {

    private final String validStartPositionString1 = "3,4,N";
    private final String validStartPositionString2 = "4,-1,W";
    private final String validStartPositionString3 = "10,10,E";
    private final String validStartPositionString4 = "0,0,S";

    String[] validStartPositionStrings = {
            validStartPositionString1, validStartPositionString2, validStartPositionString3, validStartPositionString4};
    private final String invalidStartPositionString1 = "n,3,N";
    private final String invalidStartPositionString2 = "4,3,H";
    private final String invalidStartPositionString3 = "2.3,2,S";
    private final String invalidStartPositionString4 = "1,3,1,N";

    String[] invalidStartPositionStrings = {
            invalidStartPositionString1, invalidStartPositionString2,
            invalidStartPositionString3, invalidStartPositionString4};
    private final String validCommands1 = "f,r,b,l";
    private final String validCommands2 = "f,f,f,f,b,l";
    private final String validCommands3 = "f";
    private final String validCommands4 = "r,l,r,l";

    String[] validCommands = {
            validCommands1, validCommands2,
            validCommands3, validCommands4};

    private final String invalidCommands1 = "1,f,r,l";
    private final String invalidCommands2 = "d,r,f";
    private final String invalidCommands3 = "12";
    private final String invalidCommands4 = "ft,b,r,l";

    String[] invalidCommands = {
            invalidCommands1, invalidCommands2,
            invalidCommands3, invalidCommands4};

    @Test
    void RoverArgumentsConstructorWithValidStartPositionAndValidCommands() {
        for (int i = 0; i < validStartPositionStrings.length; i++) {
            try {
                new RoverArguments(validStartPositionStrings[i], validCommands[i]);
            } catch (Exception e) {
                fail("Exception occurred in case " + i + 1 + ": " + e.getMessage());
            }
        }

    }

    @Test
    void RoverArgumentsConstructorWithInvalidStartPositionAndValidCommands() {
        for (int i = 0; i < invalidStartPositionStrings.length; i++) {
            int index = i;
            assertThrows(IllegalArgumentException.class,
                    () -> new RoverArguments(invalidStartPositionStrings[index], validCommands[index]));
        }
    }


    @Test
    void RoverArgumentsConstructorWithValidStartPositionAndInvalidCommands() {
        for (int i = 0; i < validStartPositionStrings.length; i++) {
            int index = i;
            assertThrows(IllegalArgumentException.class,
                    () -> new RoverArguments(validStartPositionStrings[index], invalidCommands[index]));
        }
    }

    @Test
    void RoverArgumentsConstructorWithInvalidStartPositionAndInvalidCommands() {
        for (int i = 0; i < invalidStartPositionStrings.length; i++) {
            int index = i;
            assertThrows(IllegalArgumentException.class,
                    () -> new RoverArguments(invalidStartPositionStrings[index], invalidCommands[index]));
        }
    }
}