import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String studentId;
    String name;
    double feesPaid;
    double totalFees;

    public Student(String studentId, String name, double totalFees) {
        this.studentId = studentId;
        this.name = name;
        this.totalFees = totalFees;
        this.feesPaid = 0.0;
    }
}

class FeesManagementSystem {
    List<Student> students;

    public FeesManagementSystem() {
        students = new ArrayList<>();
    }

    public void addStudent(String studentId, String name, double totalFees) {
        Student student = new Student(studentId, name, totalFees);
        students.add(student);
    }

    public void displayStudentDetails() {
        for (Student student : students) {
            System.out.println("Student ID: " + student.studentId);
            System.out.println("Name: " + student.name);
            System.out.println("Total Fees: $" + student.totalFees);
            System.out.println("Fees Paid: $" + student.feesPaid);
            System.out.println("------------------------------");
        }
    }

    public void recordPayment(String studentId, double paymentAmount) {
        for (Student student : students) {
            if (student.studentId.equals(studentId)) {
                double remainingFees = student.totalFees - student.feesPaid;
                if (paymentAmount <= remainingFees) {
                    student.feesPaid += paymentAmount;
                    System.out.println("Payment recorded successfully.");
                } else {
                    System.out.println("Payment exceeds remaining fees. Please enter a valid amount.");
                }
                return;
            }
        }
        System.out.println("Student not found with ID: " + studentId);
    }
}

public class Main {
    public static void main(String[] args) {
        FeesManagementSystem feesManagementSystem = new FeesManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Display Student Details");
            System.out.println("3. Record Payment");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Total Fees: ");
                    double totalFees = scanner.nextDouble();
                    feesManagementSystem.addStudent(studentId, name, totalFees);
                    break;
                case 2:
                    feesManagementSystem.displayStudentDetails();
                    break;
                case 3:
                    System.out.print("Enter Student ID for Payment: ");
                    String paymentStudentId = scanner.next();
                    System.out.print("Enter Payment Amount: ");
                    double paymentAmount = scanner.nextDouble();
                    feesManagementSystem.recordPayment(paymentStudentId, paymentAmount);
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
