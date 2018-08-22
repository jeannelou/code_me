/**
 * Programmer : Jeannelou Turbanos
 * Program    : Shipment.java
 * Date       : May 27, 2018
 */

package content;


public class Shipment {
    
   /** private instance variables */
    
   // @ var shipmentID - ID number of the shipment
   private int shipmentID;
   
   // @ var customerID - ID number of the customer
   private int customerID;
   
   // @ var weight - weight of the shipment
   private int weight ;
   
   // @ var method - the shipping method
   private String method;
   
    // @ var cost - cost of the shipping
   private double cost;
      
    
   /** Constractor without parameter */
   public Shipment() {
      
   }
    
   /**
    * Constructor that set shipmentID
    * @param shipmentID a local variable
    */
    public Shipment ( int shipmentID ) {
        
        this.shipmentID = shipmentID;        
    }

    /** A method that returns the value of the shipmentID */
    public int getShipmentID () {
        
        return this.shipmentID; 
    }
    
    /** A method that set the customer valid ID by calling the
     * binarySearch() method to check if the ID is valid or not.
     * 
     * @param customerID - a local variable that need to be set
     * @throws Exception - if the customerID is invalid
     */
    public void setCustomerID (int customerID) throws Exception {
       
        if (binarySearch ( customerID ) == true) {
            
            this.customerID = customerID;
        }
        else {
            
            throw new Exception ("Invalid customer ID");
        }
    }      	   
   
    /** return the value of customerID */
    public int getCustomerID() {
       
      return this.customerID;
    }
    
   /** A boolean binarySearch() method is used to check
     * if the given customerID is in the array of valid
     * ID's stored.
     * 
     */
    public boolean binarySearch(int customerID) {
       
        int [] validCustomerID = { 101, 103, 106, 109 , 111 }; 
        int size = validCustomerID.length;    
        
        /** low is the variable that tells us where the beginning of the 
         *  remaining list is, and we give it an initial value of 0. 
         */
        int low = 0;
        
        /** high is the variable that tells us where the end of the remaining 
         *  list is, and we give it an initial value of the last thing 
         *  in the list.
         */
        int high = size - 1;
        
         
        while(high >= low) {
           //middle - we calculate so we can divide the list into two pieces.
           int middle = (low + high) / 2;
           // found the valid customer ID , so return true
           if(validCustomerID[middle] == customerID) {
                 
               return true;
           }
           /** If the ID in the middle of the list is less than our 
            *  customerID,we should look for the key in the top half 
            *  of the list, so we calculate a new value for low.
            */
           if( validCustomerID[middle] < customerID ) {
                 
               low = middle + 1;
           }
             
           /** If the item in the middle of the list is greater than our key, 
            *  we should look for the key in the bottom half of the list, 
            *  so we calculate a new value for high.
            */
          if( validCustomerID[middle] > customerID ) {
                  
               high = middle - 1;
           }
       }
          return false;
   }
    
    /* 
     * A method that sets the weight of the shipping that throws Exception
     * if not meeting the weight's criteria
     * @ param weight - a local variable that represents the weight of the
     * the shipping
     */
    public void setWeight ( int weight ) throws Exception {
           
       if ( weight <= 0 || weight >= 100 )
            
            throw new Exception ( " Weight is not between 0 and 100 " );
        
         this.weight = weight;
  
    }
    
    /** A method that returns the value of the shipping's weight */    
    public int getWeight () {
        return this.weight;
    } 
    
    /* A method that set the method's value that throws Exception 
     * if the value is invalid
     * @ param method- a local variable that represents the method
     *   of the shipping
     */
    public void setMethod ( String method ) throws Exception {    
        
        char letter = method.charAt(0);
        switch ( letter ) {
            
            case 'A':
            case 'a' : this.method="Air";
                       break;
            case 'T':
            case 't':  this.method= "Truck";
                       break;
            case 'M':
            case 'm':  this.method="Mail";
                       break;
            default :          
                      throw new Exception( " Invalid Method Requested " );
        }       
                      
    }
    
    /** A method that returns the value of the method */
    public String getMethod () {
        
        return this.method;
    }
    
    /** A method that returns the value of the cost and calls the 
     * the calculateCost method for the cost calculation
     */
    public double getCost () {
        
        calculateCost();
        return this.cost;
    }
    
    /** A private method that calculate the cost of the given weight and 
     * method's type with different rates
     */
    private void calculateCost () {
        
       if ( this.weight >= 1 && this.weight <= 10 ) {
           
           switch ( this.method ) {
                case "Air":   this.cost = this.weight * 4.00;
                              break;
                case "Truck": this.cost = this.weight * 3.00;
                              break;
                case "Mail":  this.cost = this.weight * 2.00;
                              break;
            }                  
       }
            
      else if ( this.weight <= 20 ) {           
                        
             switch ( this.method ) {
                case "Air":   this.cost = this.weight * 3.00 ;
                              break;
                case "Truck": this.cost = this.weight * 2.45 ;
                              break;
                case "Mail":  this.cost = this.weight * 1.75 ;
                              break;
            }                     
       }   
        
       else { 
                
            switch ( this.method ) {
                case "Air":   this.cost =  this.weight * 2.50 ;
                              break;
                case "Truck": this.cost =  this.weight * 1.95 ;
                              break;
                case "Mail":  this.cost =  this.weight * 1.55 ;
                              break;
             }           
        }
        
    }           
    
}
