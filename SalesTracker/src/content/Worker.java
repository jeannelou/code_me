/*
 * Programmer : Jeannelou Turbanos
 * Program    : Worker.java
 * Date       : June 7, 2017
 */
package content;

public class Worker extends Employee {

    private double hoursWorked;
    private double hourlyRate;
    private double pay;

    public Worker(int number) {
        super(number);
    }

    public void setHoursWorked(double hoursWorked) throws Exception {
        if (hoursWorked < 0) {
            throw new Exception(" Invalid negative number");
        }
        this.hoursWorked = hoursWorked;
    }

    public double getHoursWorked() {
        return this.hoursWorked;
    }

    public void setHourlyRate(double hourlyRate) throws Exception {
        if (hourlyRate < 0) {
            throw new Exception("Invalid negative number");
        }
        this.hourlyRate = hourlyRate;
    }

    public double getHourlyRate() {
        return this.hourlyRate;
    }

    public double getPay() {
        calculatePay();
        return this.pay;
    }

    private void calculatePay() {
        this.pay = this.hourlyRate * this.hoursWorked;
    }
}
