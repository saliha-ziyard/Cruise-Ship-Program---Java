
public class Cabin{
    private String passengerName;   //specify instance variables in the class declaration
	
	// private therefore can only be accessed by the methods of its own class
    //if needs to be accessed by other classes those other classes must use getName, setName methods
    // private is an access specifier
	
	
	
//setting and getting cabin names using passengernNames


//instance methods


	// Default constructor
    public Cabin() {
        passengerName = "Empty";
    }

	// setMethod
    public void setName(String name) {
        this.passengerName = name;
    }

	// getMethod
    public String getName()
    {
        return passengerName;   
    }
}
