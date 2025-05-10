package edu.pasadena.cs.cs03b;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.*;

public class TestGuessNumberGame {

    @Test
    @DisplayName("Test randomizer with 3 digits")
    public void testRandomizerThreeDigits() {
        int result = GuessNumberGame.randomizer(3);
        assertEquals(3, String.valueOf(result).length(), "Random number should have 3 digits.");
    }

    @Test
    @DisplayName("Test randomizer with 4 digits")
    public void testRandomizerFourDigits() {
        int result = GuessNumberGame.randomizer(4);
        assertEquals(4, String.valueOf(result).length(), "Random number should have 4 digits.");
    }

    @ParameterizedTest
    @CsvSource({
            "1234, 1234, 0",
            "1234, 5678, -1",
            "5678, 1234, 1"
    })
    public void testEqualityCheck(int guess, int randomNumber, int expected) {
        int result = GuessNumberGame.equalityCheck(guess, randomNumber);
        assertEquals(expected, result, "Equality check failed.");
    }
}