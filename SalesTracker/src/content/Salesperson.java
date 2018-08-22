/*
 * Programmer : Jeannelou Turbanos
 * Program    : Salesperson.java
 * Date       : June 7, 2017
 */
package content;

public class Salesperson extends Employee {

    private double sales;
    private double rate;
    private double commission;

    public Salesperson(int number) {

        super(number);
    }

    public void setSales(double sales) throws InvalidSalesException {

        if (sales < 0 || sales > 2000.00) {
            throw new InvalidSalesException();
        }

        this.sales = sales;
    }

    public double getSales() {
        return this.sales;
    }

    public void Rate() {

        if (sales <= 100.00) {
            this.rate = 0.05;
        } else if (sales <= 1000.00) {
            this.rate = 0.07;
        } else {
            this.rate = 0.09;
        }

    }

    public double getRate() {
        Rate();
        return this.rate;
    }

    public double getCommission() {
        calculateCommission();
        return this.commission;
    }

    private void calculateCommission() {
        if (sales <= 100.00) {
            this.commission = this.sales * 0.05;
        } else if (sales <= 1000.00) {
            this.commission = this.sales * 0.07;
        } else {
            this.commission = this.sales * 0.09;
        }

    }

}
