package edu.pasadena.cs.cs03b;
import java.util.Scanner;
import java.util.*;

/*
Requirement: Write a program to generate a random number in m digits that user specified.  
Provide a loop to allow user to guess the generated number guided by range direction.  
For example if user enters m=4 and the user guessed the number as 1234 where less than generated number, 
provide hint to direct user to guess greater number.  
Allow user to buy number of guesses; each guess costs one dollar. 
*/
public class GuessNumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playGame(scanner);
        scanner.close();
    }

    public static void playGame(Scanner scanner) {
        // Declarations
        boolean tryAgain = true;
        int digitsInNum = 0;
        int guess = 0;
        int randomNumber = 0;
        int equalitySwitch;

        // Values to Buy Guesses
        int remainingGuesses = 1;
        int balanceAccrued = 0;

        System.out.println("GUESS THE NUMBER");
        System.out.println("----------------");
        digitsInNum = getNumberOfDigits(scanner);
        
        // Create random number
        randomNumber = randomizer(digitsInNum);
        
        System.out.println("A random number has been chosen!");     
        System.out.println("You have ONE (1) Free Guess...");

        while (tryAgain == true) {
            guess = getUserGuess(scanner);
            remainingGuesses -= 1;

            equalitySwitch = equalityCheck(guess, randomNumber);
            switch (equalitySwitch) {
                case 1:
                    System.out.println("Your guess is too low!");
                    if (remainingGuesses == 0) {
                        tryAgain = handleOutOfGuesses(scanner, remainingGuesses, balanceAccrued);
                        remainingGuesses = getRemainingGuesses();
                        balanceAccrued = getBalanceAccrued();
                    }
                    break;
                case 2:
                    System.out.println("Your guess is too high");
                    if (remainingGuesses == 0) {
                        tryAgain = handleOutOfGuesses(scanner, remainingGuesses, balanceAccrued);
                        remainingGuesses = getRemainingGuesses();
                        balanceAccrued = getBalanceAccrued();
                    }
                    break;
                case 3:     		
                    tryAgain = false;
                    break;
                default: 
                    System.out.println("Error");
                    break;
            }
            if (!tryAgain) break;
        }

        endGame(balanceAccrued);
    }

    public static int getNumberOfDigits(Scanner scanner) {
        System.out.println("How many digits in your number: ");
        int digits = scanner.nextInt();
        scanner.nextLine(); // Clear register
        return digits;
    }

    public static int getUserGuess(Scanner scanner) {
        System.out.println("Guess the number: ");
        int guess = scanner.nextInt();
        scanner.nextLine(); // Clear register
        return guess;
    }

    private static int remainingGuesses = 1;
    private static int balanceAccrued = 0;

    public static boolean handleOutOfGuesses(Scanner scanner, int remainingGuessesTemp, int balanceAccruedTemp) {
        System.out.println("-----------------------");
        System.out.println("You are out of guesses!");
        System.out.println("Would you like to buy more guesses?");
        System.out.println("1. Buy 1 Guess ($1) \n2. Buy 2 guesses ($2) \n3. Buy 3 guesses ($3) \n4. No/Exit Game");
        
        switch (scanner.nextInt()) {
            case 1: 
                remainingGuesses += 1;
                balanceAccrued += 1;
                break;
            case 2: 
                remainingGuesses += 2;
                balanceAccrued += 2;
                break;
            case 3: 
                remainingGuesses += 3;
                balanceAccrued += 3;
                break;
            case 4: 
                System.out.println("You failed to guess the number...\nGoodbye...");
                return false;
            default: 
                System.out.println("Invalid Entry: Self Destruct Commencing in 3...2...1...BOOM");
                break;
        }
        return true;
    }

    public static int getRemainingGuesses() {
        return remainingGuesses;
    }

    public static int getBalanceAccrued() {
        return balanceAccrued;
    }

    public static void endGame(int balanceAccrued) {
        System.out.println("---------------------------------------");
        System.out.println("CONGRATULATIONS YOU GUESSED THE NUMBER!");
        System.out.println("You guessed the number in " + (balanceAccrued + 1) + " attempts!");
        System.out.println("You owe $" + balanceAccrued + " dollars!");
    }
    
    public static int randomizer(int digitsInNumTemp) {
        Scanner userNum = new Scanner(System.in);
        boolean isMoreThanZero = false;
        int min;
        int max;
        int randomNum;

        //Could Probably Make this neater - Boolean is probably unnecessary.
        if (digitsInNumTemp > 0)
            isMoreThanZero = true;
        else 
            isMoreThanZero = false;

        // Make Sure numOfDigits Is Greater Than 0
        while (isMoreThanZero == false) {
            System.out.println("Number of digits must be greater than 0");
            System.out.println("How many digits in your number: ");
            if (userNum.nextInt() > 0)
                isMoreThanZero = true;
            else 
                isMoreThanZero = false;
        }   
        //Creates a range for randomNum	
        min = (int) Math.pow(10, digitsInNumTemp - 1);
        max = (int) (Math.pow(10, digitsInNumTemp) - 1);
        //Declares randomNum using Math.random()
        randomNum = (int)(Math.random() * (max - min + 1)) + min;

        return randomNum;
    }
    
    
    public static int equalityCheck(int guess, int number) {
        if (guess < number)
            return -1;
        else if (guess > number)
            return 1;
        else if (guess == number)
            return 0;
        return 0;
    }
}