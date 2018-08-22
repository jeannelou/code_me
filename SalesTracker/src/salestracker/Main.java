/*
 * Programmer : Jeannelou Turbanos
 * Program    : Main.java
 * Date       : June 7,2017
 */
package salestracker;

import content.InvalidSalesException;
import content.Salesperson;
import content.Worker;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {

        Scanner scan = new Scanner(System.in);
        ArrayList<Salesperson> salesPersonList = new ArrayList();
        ArrayList<Worker> workerList = new ArrayList();
        boolean isProcess = true;
        double amount = 0;

        while (isProcess) {

            System.out.println("Enter details for: [1] Salesperson"
                    + " [2] Worker ");
            String inputValue = "";
            boolean isInputValid = false;

            while (!isInputValid) {

                inputValue = scan.next();

                if (inputValue.equals("1") || inputValue.equals("2")) {

                    isInputValid = true;

                } else {

                  System.out.println("Invalid input value..");
                  System.out.println("Enter details for:[1] Salesperson "
                          + "[2] Worker ");

                }
            }

            int employeeType = Integer.parseInt(inputValue);
            if (employeeType == 1) {

                try {

                    System.out.println("Enter employee's number: ");
                    int num = scan.nextInt();

                    System.out.println("Enter salesperson name: ");
                    String name = scan.next();

                    System.out.println("Enter sales amount:");
                    amount = scan.nextDouble();

                    Salesperson sp = new Salesperson(num);
                    sp.setName(name);
                    sp.setSales(amount);
                    salesPersonList.add(sp);

                } catch (InvalidSalesException e) {
                    System.out.println(e);
                }

            } else {

                try {

                    System.out.println("Enter employee's number: ");
                    int num = scan.nextInt();

                    System.out.println("Enter worker's name: ");
                    String name = scan.next();

                    System.out.println("Enter worker's hours worked:");
                    double hours = scan.nextDouble();

                    System.out.println("Enter worker's hourly rate:");
                    double rate = scan.nextDouble();

                    Worker wkr = new Worker(num);
                    wkr.setName(name);
                    wkr.setHoursWorked(hours);
                    wkr.setHourlyRate(rate);
                    workerList.add(wkr);

                } catch (Exception e) {
                    System.out.println(e);
                }

            }

           System.out.println(" ");
           System.out.println("Enter another employee:[1]-YES"
                   + " [ANY NUMBER]-NO");

           if (scan.nextInt() != 1) {

                isProcess = false;
            }

        }
        
        
        for (int sub = 0; sub < salesPersonList.size(); sub++) {

            Salesperson one = salesPersonList.get(sub);
            System.out.println("---------------------------------");
            System.out.println(" ");
            System.out.println("Salesperson details:");
            System.out.println("Employee number:" + one.getNumber());
            System.out.println("Name:" + one.getName());
            System.out.println("Sales amount: $"
                    + String.format("%.2f", one.getSales()));
            System.out.println("Commission rate:" + one.getRate());
            System.out.println("Commission amount: $"
                    + String.format("%.2f", one.getCommission()));

        }

        
        for (int sub = 0; sub < workerList.size(); sub++) {

            Worker one = workerList.get(sub);
            System.out.println("---------------------------------");
            System.out.println(" ");
            System.out.println("Worker details:");
            System.out.println("Employee number:" + one.getNumber());
            System.out.println("Name:" + one.getName());
            System.out.println("Number of hours worked:" 
                    + one.getHoursWorked());
            System.out.println("Hourly Rate:$"
                    + String.format("%.2f", one.getHourlyRate()));
            System.out.println("Pay: $" + String.format("%.2f", one.getPay()));
        }

    }

}
