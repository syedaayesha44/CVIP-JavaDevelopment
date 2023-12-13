import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ATMFrame extends Frame implements ActionListener {

    private TextField amountField;
    private Button withdrawButton;
    private Button depositButton;
    private Button checkBalanceButton;
    private Label resultLabel;

    private ATM atm;

    public ATMFrame() {
        super("ATM");

        atm = new ATM(new BankAccount(1000.0)); // Initial balance

        // Create a label for the heading
        Label headingLabel = new Label("User Account Details:");
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Create a label to instruct the user
        Label enterAmountLabel = new Label("Enter the amount:");
        enterAmountLabel.setFont(new Font("Arial", Font.BOLD, 40));

        // Create the amount field
        amountField = new TextField("Enter amount", 20); // Increased the size to 20
        amountField.setFont(new Font("Arial", Font.PLAIN, 40)); // Set font size
        amountField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (amountField.getText().equals("Enter amount")) {
                    amountField.setText(""); // Clear the default text when the user starts typing
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (amountField.getText().isEmpty()) {
                    amountField.setText("Enter amount");
                }
            }
        });

        // Create a panel for the buttons
        Panel buttonPanel = new Panel();

        // Create labels for the operation buttons
        Label withdrawLabel = new Label("Withdraw");
        Label depositLabel = new Label("Deposit");
        Label checkBalanceLabel = new Label("Check Balance");

        // Create the withdraw button
        withdrawButton = new Button("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 24));
        withdrawButton.addActionListener(this);

        // Create the deposit button
        depositButton = new Button("Deposit");
        depositButton.setFont(new Font("Arial", Font.BOLD, 24));
        depositButton.addActionListener(this);

        // Create the check balance button
        checkBalanceButton = new Button("Check Balance");
        checkBalanceButton.setFont(new Font("Arial", Font.BOLD, 24));
        checkBalanceButton.addActionListener(this);

        // Add labels and buttons to the button panel
        buttonPanel.add(withdrawLabel);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(depositLabel);
        buttonPanel.add(depositButton);
        buttonPanel.add(checkBalanceLabel);
        buttonPanel.add(checkBalanceButton);

        // Create the result label
        resultLabel = new Label("");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 50));

        // Set the layout of the frame using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add the components to the frame using GridBagConstraints
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(headingLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset grid width
        add(enterAmountLabel, gbc);

        gbc.gridy = 2;
        add(amountField, gbc);

        gbc.gridy = 3;
        add(buttonPanel, gbc);

        gbc.gridy = 4;
        add(resultLabel, gbc);

        // Pack the frame and make it visible
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == withdrawButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                double withdrawalAmount = atm.withdraw(amount);

                if (withdrawalAmount > 0) {
                    resultLabel.setText("Withdrawal successful. You have withdrawn Rs." + withdrawalAmount);
                } else {
                    resultLabel.setText("Withdrawal failed. Insufficient balance.");
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            } finally {
                amountField.setText("Enter amount");
            }
        } else if (source == depositButton) {
            try {
                double amount = Double.parseDouble(amountField.getText());
                atm.deposit(amount);
                resultLabel.setText("Deposit successful. You have deposited Rs." + amount);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter a valid number.");
            } finally {
                amountField.setText("Enter amount");
            }
        } else if (source == checkBalanceButton) {
            double balance = atm.checkBalance();
            resultLabel.setText("Your balance is Rs." + balance);
        }
    }

    public static void main(String[] args) {
        new ATMFrame();
    }
}

class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double withdraw(double amount) {
        if (bankAccount.getBalance() < amount) {
            System.out.println("Insufficient balance. You cannot withdraw more than Rs." + bankAccount.getBalance());
            return 0.0;
        }

        bankAccount.withdraw(amount);
        System.out.println("Withdrawal successful. You have withdrawn Rs." + amount);
        return amount;
    }

    public void deposit(double amount) {
        bankAccount.deposit(amount);
        System.out.println("Deposit successful. You have deposited Rs." + amount);
    }

    public double checkBalance() {
        return bankAccount.getBalance();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }
}
