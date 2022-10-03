package ua.ip.http.cli;

import java.util.Scanner;

public class CliFSM {
    private CliState state;

    private final Scanner scanner;

    public CliFSM() {
        state = new IdleState(this);
        printedStartMessage();
        scanner = new Scanner(System.in);
        startInputLoop();
    }

    public Scanner getScanner(){
        return scanner;
    }

    public void printedStartMessage(){
        System.out.println("Please ,select from the list what you want to do:");
        System.out.println("For action with  user write 'user'");
        System.out.println("For action with  store write 'store'");
        System.out.println("For action with  pet write 'pet'");
        System.out.println("For exit from program 'exit'");

    }
    public void setState(CliState state) {
        this.state = state;
        state.init();
    }

    private void startInputLoop() {

        while (true){
            String command = scanner.nextLine();

            switch (command){
                case "user":
                    workingWithUser();
                    break;
                case "store":
                    workingWithStore();
                    break;
                case "pet":
                    workingWithPet();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    unknown(command);
                    break;
            }
        }
    }

    public void workingWithUser() {
        state.workingWithUser();
    }

    public void workingWithStore() {
        state.workingWithStore();
    }

    public void workingWithPet() {
        state.workingWithPet();
    }

    public void unknown(String cmd){
        state.uknown(cmd);
    }

    public int writeDigit() {
        int digit;
        while (true) {
            try {
                digit = Integer.parseInt(getScanner().nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid values.Write digits");
            }
        }
        return digit;
    }

    public String writeStatus() {
            String status = null;
            while (true) {
                String command = scanner.nextLine();
                switch (command){
                    case "available":
                        status = "available";
                        break;
                    case "pending":
                        status ="pending";
                        break;
                    case "sold":
                        status = "sold";
                        break;
                    default:
                        System.out.println("Incorrect status");
                        break;
            }
            return status;
        }
    }
}
