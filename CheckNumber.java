import java.util.*;

public class CheckNumber {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String next = "yes";
      
        while (next.equalsIgnoreCase("yes")) {
            Random rand = new Random();
            int rn = rand.nextInt(10);
            int c = 10;
            System.out.println("Guess the numbers from 0 to 10");
            System.out.println("You have only 10 chances of attempting, so be careful!");
            while (c != 0) {
                System.out.println("Enter your number");
                int n = sc.nextInt();
                if (rn == n) {
                    System.out.println("Equal to the generated number");
                    break;
                } else if (rn < n) {
                    System.out.println("Greater than the generated number");
                } else {
                    System.out.println("Lesser than the generated number");
                }
                c--;
            }
            System.out.println("Your score = " + c);
            System.out.println("Random Integer: " + rn);
            System.out.println("Do you want to play again? YES/NO");
            sc.nextLine(); 

            next = sc.nextLine();
            if (next.equalsIgnoreCase("yes")) {
                System.out.println("START AGAIN");
            } else {
                System.out.println("GAME ENDED");
                next = "no";
            }
        }
    }
}
