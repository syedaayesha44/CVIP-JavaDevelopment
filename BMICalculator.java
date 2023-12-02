import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your weight in kilograms: ");
        double wt = scanner.nextDouble();
        System.out.print("Enter your height in meters: ");
        double ht = scanner.nextDouble();
        scanner.close();
        double bmi = calculateBMI(wt, ht);
        interpretBMI(bmi);
    }
    private static double calculateBMI(double wt, double ht) {
        return wt / (ht * ht);
    }
    private static void interpretBMI(double bmi) {
        System.out.println("Your BMI is: " + bmi);

        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi < 25) {
            System.out.println("Category: Normal weight");
        } else if (bmi < 30) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obesity");
        }
    }
}
