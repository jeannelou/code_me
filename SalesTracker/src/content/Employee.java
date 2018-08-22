/*
 * Programmer : Jeannelou Turbanos
 * Program    : Employee.java
 * Date       : June 7, 2018
 */
package content;

public class Employee {

    private int employeeNumber;
    private String name;

    public Employee(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getNumber() {
        return this.employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

}
