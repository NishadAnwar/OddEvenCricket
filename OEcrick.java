import java.util.Random;
import java.util.Scanner;

class OddEvenGame {
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    private int generateRandom(int n) {
        return random.nextInt(n) + 1;
    }

    private boolean toss(char tossChoice) {
        System.out.print("Enter your number for the toss (1 to 10): ");
        int toss = scanner.nextInt();
        int tossResult = generateRandom(10) + toss;
        if (tossChoice == 'o') {
            if (tossResult % 2 != 0) {
                System.out.println("You won the toss.");
                return true;
            } else {
                System.out.println("You lost the toss.");
                return false;
            }
        } else if (tossChoice == 'e') {
            if (tossResult % 2 == 0) {
                System.out.println("You won the toss.");
                return true;
            } else {
                System.out.println("You lost the toss.");
                return false;
            }
        } else {
            System.out.println("Invalid choice. Enter 'o' or 'e'.");
            return false;
        }
    }

    private int battingUser() {
        int runs = 0;
        System.out.println("\nYou are batting now.");
        while (true) {
            int compilerNum = generateRandom(10);
            System.out.print("Enter a number (1 to 10): ");
            int run = scanner.nextInt();
            run = Math.min(run, 10);
            if (compilerNum == run) break;
            runs += run;
            System.out.println("You scored: " + run + " runs. Total runs: " + runs);
        }
        return runs;
    }

    private int battingCompiler() {
        int runs = 0;
        System.out.println("\nCompiler is batting now.");
        while (true) {
            int compilerRun = generateRandom(10);
            System.out.print("Enter a number (1 to 10): ");
            int run = scanner.nextInt();
            run = Math.min(run, 10);
            if (compilerRun == run) break;
            runs += compilerRun;
            System.out.println("Compiler scored: " + compilerRun + " runs. Total runs: " + runs);
        }
        return runs;
    }

    private void tossVictoryUser(int[] scores) {
        System.out.println("\nYou won the toss. Choose:");
        System.out.println("1 = Batting\n2 = Bowling");
        System.out.print("What do you want: ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("You chose to bat first.");
            scores[0] = battingUser();
            System.out.println("The target score is: " + scores[0]);
            scores[1] = battingCompiler();
            System.out.println("Total runs scored by the compiler: " + scores[1]);
        } else if (choice == 2) {
            System.out.println("You chose to bowl first.");
            scores[1] = battingCompiler();
            System.out.println("The target score is: " + scores[1]);
            scores[0] = battingUser();
            System.out.println("Total runs scored by you: " + scores[0]);
        } else {
            System.out.println("Invalid choice. Enter 1 or 2.");
        }
    }

    private void tossVictoryCompiler(int[] scores) {
        int compilerCall = generateRandom(2);
        if (compilerCall == 1) {
            System.out.println("\nCompiler chose to bat first. You will bowl now.");
            scores[1] = battingCompiler();
            System.out.println("The target score is: " + scores[1]);
            scores[0] = battingUser();
            System.out.println("Total runs scored by you: " + scores[0]);
        } else {
            System.out.println("\nCompiler chose to bowl first. You will bat now.");
            scores[0] = battingUser();
            System.out.println("The target score is: " + scores[0]);
            scores[1] = battingCompiler();
            System.out.println("Total runs scored by the compiler: " + scores[1]);
        }
    }

    public void playGame() {
        System.out.println("Welcome to the Odd-Even Cricket Game!");
        System.out.print("Do you want to play the odd/even game of cricket (y/n): ");
        char start = scanner.next().charAt(0);

        while (start != 'n') {
            System.out.print("\nReady for the toss (y/n): ");
            char choice1 = scanner.next().charAt(0);
            if (choice1 == 'y') {
                System.out.print("Do you want to choose odd or even (o = odd, e = even): ");
                char tossChoice = scanner.next().charAt(0);
                boolean result = toss(tossChoice);

                int[] scores = new int[2]; // [userRuns, compilerRuns]
                if (result) {
                    tossVictoryUser(scores);
                } else {
                    tossVictoryCompiler(scores);
                }

                if (scores[0] > scores[1]) {
                    System.out.println("Congratulations! You won the game.");
                } else if (scores[0] < scores[1]) {
                    System.out.println("Compiler won the game. Better luck next time!");
                } else {
                    System.out.println("It's a draw.");
                }
            } else if (choice1 == 'n') {
                System.out.println("Okay, exiting the game.");
                break;
            } else {
                System.out.println("Invalid choice. Enter 'y' or 'n'.");
            }

            System.out.print("\nDo you want to play again (y/n): ");
            start = scanner.next().charAt(0);
        }
    }
}

public class OddEvenCricket {
    public static void main(String[] args) {
        OddEvenGame game = new OddEvenGame();
        game.playGame();
    }
}
