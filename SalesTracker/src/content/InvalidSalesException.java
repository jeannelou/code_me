/*
 * Programmer : Jeannelou Turbanos
 * Program    : InvalidSalesException.java
 * Date       : June 7, 2017
 */
package content;

public class InvalidSalesException extends Exception {

    public InvalidSalesException() {
        super(" Sales is not between 0 and $2,000.00");
    }

}
