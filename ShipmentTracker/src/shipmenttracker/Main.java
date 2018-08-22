/*
 * Programmer : Jeannelou Turbanos
 * Program    : Main.java
 * Date       : May 27, 2018
 */
package shipmenttracker;

import content.Shipment;

public class Main {

  /**
  * a method to test the object
  * @param args - command line arguments to be used in the program
  */
  public static void main (String[] args) {
           
      try {
            /** Creating Shipment object shipmentNum to call the getShipmentID() 
             * to print the Shipment ID value.                 * 
             */     
            Shipment shipmentNum  = new Shipment(Integer.parseInt(args[0]));
            System.out.println( " Shipment ID : " + shipmentNum.getShipmentID()); 
                
            /** Creating Shipment object customerNum to call the setcustomerID() - test
             * the customer ID setting and getCustomerID()-to print the valid customer ID. 
             */     
            Shipment customerNum  = new Shipment();
            customerNum.setCustomerID ( Integer.parseInt(args[1]));
            System.out.println( " Customer ID : " + customerNum.getCustomerID()); 
                
            /* Creating Shipment object shipmentWeight to call the setWeight() - test the 
             * weight setting and getWeight() to print the weight value.                 * 
             */       
            Shipment shipmentWeight = new Shipment();
            shipmentWeight.setWeight (Integer.parseInt(args[2]));
            System.out.println( " Weight of the shipment: " + shipmentWeight.getWeight() );
                
            /** Creating Shipment object shipmentMethod to call setMethod()-test the method 
             * setting and getMethod to print the valid method value.                 * 
             */   
            Shipment shipmentMethod = new Shipment();
            shipmentMethod.setMethod (args[3]);
            System.out.println( " Shipment method: " + shipmentMethod.getMethod() );
                
            /** Creating Shipment object shipmentCost to call the setWeight() and setMethod() 
             * to compute and print the total cost of the shipment by calling getCost() method.                 
             */   
            Shipment shipmentCost = new Shipment();
            shipmentCost.setWeight (Integer.parseInt(args[2]));
            shipmentCost.setMethod (args[3]);                
            System.out.println( " The cost is $" + shipmentCost.getCost() );
               
          }
        
      /** Handling exceptions */
      
      // Handling the NumberFormatException  
      catch (NumberFormatException e) {  
          
          System.out.println( " Command line argument is not an integer " );
      }
      // Handling ArrayIndexOutOfBoundsException
      catch (ArrayIndexOutOfBoundsException e) {
          
          System.out.println( " There are not any command line arguments " );
      }
      // Handling all the Exceptions    
      catch (Exception e) {
          
          System.out.println(e);
      }
      // End of the program  
      finally {
          
          System.out.println ( " Program written by : Jeannelou Turbanos (ID #991500811) " );
      }
      
   }
}
   
