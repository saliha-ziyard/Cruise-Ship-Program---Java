import java.io.*;
import java.util.*;

public class shipp 
{
	//constructor//name of the constructor is same as the name of the class
	//iitialize a Scanner object in the Scanner class
    static Scanner input = new Scanner(System.in);
    static int cabinNum = 0;
	static String cabinName;
    
	//constructor
	//initialize the variables with arrays in Cabin class

	static Cabin[] cruise = new Cabin[12];
	
	static int total = 0;  //used for waiting list
	static int totalExpenses = 0;
	
	static int passengerCount = 0;  //used when deleting 
	
	//constructor
    //initialize the variables with arrays in Passenger class

    static Passenger[] guestNum = new Passenger[12];           
    static Passenger[] firstName = new Passenger[12];
	static Passenger[] surName = new Passenger[12];
	static Passenger[] expenses = new Passenger[12];
	
    //constructor
    //initialize the variables with arrays in WaitingList class
    // WaitingList Class Objects
    static WaitingList CName = new WaitingList();     
	static WaitingList GNum = new WaitingList();
    static WaitingList FName = new WaitingList();  
    static WaitingList SName = new WaitingList();
	static WaitingList EXpenses = new WaitingList();
	


    public static void main(String[] args)   //executed
	{
		// Creating Person class objects 
		for(int x=0; x < cruise.length; x++){   
			cruise[x] = new Cabin();
		}
		for(int x=0; x < guestNum.length; x++){   
			guestNum[x] = new Passenger();
		}
		for(int x=0; x < firstName.length; x++){   
	     	firstName[x] = new Passenger();
		}
		for(int x=0; x < surName.length; x++){   
			surName[x] = new Passenger();
		}
		for(int x=0; x < expenses.length; x++){  
			expenses[x] = new Passenger();
		}
		
		initialise(cruise);
		menu();   // gives the menu
	}

    private static void menu(){   //main method
		System.out.println();
		System.out.println("Welcome to the Cruise please select an option from the menu.");
        System.out.println("__________________________Menu_____________________________");
        System.out.println(" V - View all cabins");
        System.out.println(" A - Add a passenger to a cabin");
        System.out.println(" E - Display empty cabins");
        System.out.println(" D - Delete passenger from a cabin");
        System.out.println(" F - Find a cabin from passenger name");
        System.out.println(" S - Store program data into file");
        System.out.println(" L - Load program data from file");
        System.out.println(" O - View passengers ordered alphabetically by name");
		System.out.println(" T - Expenses per passenger as well as the total expenses of all passengers");
        System.out.println("____________________________________________________________");
        selection();
			
	}

    // menu options selectors
		private static void selection()
		{
			System.out.println();
			System.out.println("Select an option: ");
			String option = input.nextLine(); //reads input including space and places option in next line
			option = option.toUpperCase(); //convert to upper case
			
			while (true){
				if (option.equals("V"))
				{
					ViewAllCabins(
					);
					break;
				} 
				else if (option.equals("A"))
				{
					AddCustomer(cruise);
					break;
				}
				else if (option.equals("E"))
				{
					DisplayEmptyCabins(cruise);
					break;
				}
				else if (option.equals("D"))
				{
					DeleteCustomer(cruise);
					break;
				}
				else if (option.equals("F"))
				{
					FindByName(cruise);
					break;
				}
				else if (option.equals("S"))
				{
					StoreIntoFile(cruise);
					break;
				}
				else if (option.equals("L"))
				{
					LoadFromFile();
					break;
				}
				else if (option.equals("O"))
				{
					OrderAlphabetical(cruise);
					break;
					
				}else if (option.equals("T")){
					System.out.println("Total Expenses of all customers is : "+ totalExpenses);
					break;
				}
				else
				{
					System.out.println("Invalid Input");
					break;
				}
				
			}
        

        // Asking whether user needs to continue
			
			System.out.println();
			System.out.println("Would you like to continue? Y or N: ");
			String continueOrNot = input.next();
			continueOrNot = continueOrNot.toLowerCase();  //converts to lowercase
			
			if (continueOrNot.equals("y"))
			{
				input.nextLine();  
				menu();          //back to menu
			}
			else if (continueOrNot.equals("n"))
			{
				System.out.println("___________________________________________Thank You!!_______________________________________");
			}
			else{			
			System.out.println("Invalid input");
			System.out.println("Would you like to continue? Y or N): ");
			continueOrNot = input.next();
            continueOrNot = continueOrNot.toLowerCase();
            if (continueOrNot.equals("y")) {
				input.nextLine();
				menu();
			}
			else {  // if the input is not y or n the 2nd time it'll say thank you and end the program
				System.out.println("___________________________________________Thank You!!_______________________________________");
			}
			
		
		}
		
    }
		
		
		
		
		//initializing
		private static void initialise(Cabin[] cruise){
			for (int x = 0; x < cruise.length; x++) {
				cruise[x].setName("Empty");  // Emptying the array indexes.
            }
        }
		
		
		//methods to all options in the menu
	
	
	    /*------------------------method if v is selected---------------------------*/
	
		private static void ViewAllCabins(Cabin[] cruise) {
			System.out.println("_____________________All Cabins___________________________");
			
			for (int x = 0; x < 12; x++) {        //visits all elements in the array cruise, guestNum etc..
				if(cruise[x].getName().equals("Empty")){  //check if cabin is empty 
					System.out.println("Cabin number " + (x+1) + " is empty");  //print if empty
				}else {
					System.out.println("Cabin number " + (x + 1) + " is occupied by " + cruise[x].getName());  //from cabin , print if not empty by taking the index  //get the name from index position x of array named cruise.
					System.out.println("Number of passenger's in the Cabin: " + guestNum[x].getGuestNum());  //from passenger
					System.out.println("Passenger's Full Name: " + firstName[x].getFirstName() + " " + surName[x].getSurName());  //from passenger
					System.out.println("Expenses include: " + expenses[x].getExpenses());  //from passenger
					
					
				}
				System.out.println();
			}
			System.out.println("____________________________________________________________");
		}
		
		
		/*------------------------method if a is selected---------------------------*/

		private static void AddCustomer(Cabin[] cruise) {
			System.out.println("_____________________Add Customer to cabin_____________________");
			System.out.println();
			System.out.print("Select a cabin number between 1 and 12: ");
			cabinNum = input.nextInt();
			
			while (true) 
			{
				if (cabinNum >=1 && cabinNum <= 12)  //check if cabin number is valid
				{
					break;
				}
				else 
				{
					System.out.println("Invalid Cabin number");
					System.out.print("Select a cabin number between 1 and 12: ");   //will be printed until user enters correct number
					cabinNum = input.nextInt();
				}
			}
			
			System.out.println("Enter the passenger details for Cabin number - " + cabinNum);
			System.out.print("Enter a name to book the Cabin: ");
			String cabinName = input.next();  //common name
			
			System.out.print("Enter number of  passengers to stay: ");
			int gNum = input.nextInt(); //from passenger
			
			while(gNum > 3){
			
				System.out.println("Invalid input. Max number of passengers is 3 and make sure you enter an integer value");
				System.out.println("Enter number of guests going to stay: ");
				gNum = input.nextInt();	
		    }  
			
			String fName;
			String sName;
			int exp;
			
			 int i = 1; 
			 do{
				System.out.print("Enter the First Name of the passenger " + i +": ");
				fName = input.next();

				System.out.print("Enter the Surname of the passenger " + i +": ");
				sName = input.next();
				
				
				System.out.print("Enter the expenses of  passenger " + i +": ");
				exp = input.nextInt();
				
				totalExpenses = exp + totalExpenses;
				
				i++;
			}
			while (i<=gNum);
			
			

			// Checking whether cabins are full or not.
		   boolean cabinFull = false;  
		   for (Cabin s : cruise)
			{
				if (s.getName().equals("Empty"))  //if cabin full
				{
					cabinFull = false;
					break;
				}
				else
				{
					cabinFull = true;
				}
			}
			
			if(!cabinFull){   //cabinFull is true
			
			
				// Using the setMethods to set the values in arrays.
				cruise[cabinNum - 1].setName(cabinName);  //from cabin
				guestNum[cabinNum - 1].setGuestNum(gNum);   //below all form passenger
				firstName[cabinNum - 1].setFirstName(fName);
				surName[cabinNum - 1].setSurName(sName);
				expenses[cabinNum - 1].setExpenses(exp);
				
				
				System.out.println();
				System.out.println("________________Passenger details have been added_____________");
				passengerCount++;
			}else
			
			{
				
				System.out.println();
				System.out.println("Cabins are Full");
				
				
				// Using enqueue methods to add values to the queues.
				CName.enQueueString(cabinName);  //waitingList and cabin
				GNum.enQueueNumber(gNum);   //waitingList and passenger
				FName.enQueueString(fName);
				SName.enQueueString(sName);
				EXpenses.enQueueNumber(exp);
				
				total++;
				
				System.out.println();  
				System.out.println("_____________Passenger has been Added to Waiting List___________");
			}
			System.out.println();  //start a new line at the end
		}
		
		/*------------------------method if e is selected---------------------------*/

		private static void DisplayEmptyCabins(Cabin[] cruise) {
			System.out.println("_____________________________Empty Cabins___________________________");
			for (int x = 0; x < cruise.length; x++) {
				
				if (cruise[x].getName().equals("Empty"))  //check if cabin is empty
				{  
					System.out.println("Cabin number " + (x + 1) + " is empty"); //print if empty
				}
			}

			System.out.println("____________________________________________________________");
		}
		
		
		/*------------------------method if d is selected---------------------------*/

		private static void DeleteCustomer(Cabin[] cruise) {
			
			System.out.println("_______________________Remove Passenger___________________________");
			System.out.println();
			System.out.print("Enter the cabin number from which the passenger should be deleted from: ");
			cabinNum = input.nextInt();
			
			// checking the input
			while (cabinNum < 1 || cabinNum > 12) {
				
				System.out.println("Invalid Cabin number. Try again");
				System.out.print("Select a cabin number between 1 and 12: ");
				cabinNum = input.nextInt();
			}
			
			// Deleting the passenger details
			
			//If the elements in the array are not in any particular order, simply overwrite the element to be removed with the last element of the array, then decrement the current-Size variable. 
			
			
			cruise[cabinNum - 1].setName("Empty");
			guestNum[cabinNum-1].setGuestNum(0);
			firstName[cabinNum -1].setFirstName("Empty");
			surName[cabinNum -1].setSurName("Empty");
			expenses[cabinNum-1].setExpenses(0);
			
			
			if (passengerCount ==0){
			System.out.println("Passengers still not added to cabin " + cabinNum);
		    }
			
			else if (total == 0){ // total means total number of customers in waiting list
				System.out.println("Passenger from cabin number " + cabinNum +" has been removed.");
		    }
			else{
			
				// Adding new passenger details from waiting list.
				// Using dequeue method and adding the returned value.
				cruise[cabinNum - 1].setName(CName.deQueueString());
				guestNum[cabinNum - 1].setGuestNum(GNum.deQueueNumber());
				firstName[cabinNum - 1].setFirstName(FName.deQueueString());
				surName[cabinNum - 1].setSurName(SName.deQueueString());
				expenses[cabinNum - 1].setExpenses(EXpenses.deQueueNumber());
			
		
				System.out.println("Passenger deleted from the cabin number " + cabinNum + ".");
				System.out.println("New passenger has been added to the cabin " + cabinNum + "from the waiting list.");
			}
			
			System.out.println();
			System.out.println("____________________________________________________________");

		}
		
		/*------------------------method if f is selected---------------------------*/

		private static void FindByName(Cabin[] cruise) {
			System.out.println("_______________________Find Passenger_______________________");
			System.out.println();
			System.out.print("Enter passenger's name: ");
			String cabinName = input.nextLine();
			
			while (true) {
				
				if (cabinName.matches("^[a-z A-Z]*$")) //check whether cabinName has alphabets
				{
					break;
					
				} 
				else {
					System.out.println("Invalid Input");
					System.out.print("Enter passenger's name: ");
					cabinName = input.nextLine();
				}
			}
			
			//Traverses through the array and checks for the passenger name.
			boolean found = true;  // initiates boolean value
			for (int x = 0; x< 12; x++) {
				if (cabinName.equals(cruise[x].getName())) {  //from cabin
					System.out.println(cabinName + " is in cabin number "+ (x + 1));
					System.out.println("Number of Passengers in the cabin: " + guestNum[x].getGuestNum()); 
					System.out.println("Passenger's Full Name: " + firstName[x].getFirstName()+ " " + surName[x].getSurName());
					System.out.println("Cabin Expenses include: " + expenses[x].getExpenses()); 
						
					found = true;  //then found is true
					break;
					
				} else {
					found = false;
				}
			}
			
			
			if (!found) //if found is false
			{
				System.out.println("Could't find a cabin booked under the name "+ cabinName + ".");
			}
			System.out.println();
			System.out.println("____________________________________________________________");
		}
		
		
		/*------------------------method if s is selected---------------------------*/

		public static void StoreIntoFile(Cabin[] cruise) 
		{
			// Storing data in a text file
			try {
				FileWriter storeFile = new FileWriter("storeFile.txt");  
				storeFile.write("______________________Cruise Data__________________________" + "\n");
				storeFile.write("\n");

				for(int x = 0; x < 12; x++) 
				{
					if (cruise[x].equals("Empty")) 
					{
						storeFile.write("____________________________________________________________" + "\n");
						storeFile.write("Cabin number " + (x + 1) + " is empty \n");

					}
					else{
						storeFile.write("____________________________________________________________"+ "\n");
						storeFile.write("Cabin number " + (x + 1) + " is occupied by " + cruise[x].getName() + "\n");
						storeFile.write("Passenger's Full Name: " + firstName[x].getFirstName() + " " + surName[x].getSurName() +"\n");
						storeFile.write("Number of Passengers in the Cabin: " + guestNum[x].getGuestNum()  +"\n");
						storeFile.write("Cabin Expenses include: " + expenses[x].getExpenses());
					}
					storeFile.write("____________________________________________________________" + "\n");
					storeFile.write("\n");
				}
				storeFile.close();
				System.out.println("Successfully stored into a file.");
				
			} 
			
			catch (IOException e) 
			{
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		
		/*------------------------method if l is selected---------------------------*/

		public static void LoadFromFile() 
		{
			// Loading the data from the Stored file
			System.out.println("Loaded data: ");
			
			try {
				File object = new File("storeFile.txt");  //construct a File object with the name of the file
				Scanner reader = new Scanner(object);   //use the File object to construct a Scanner object   //This Scanner object reads text from the file
				while (reader.hasNextLine()) 
				{
					String info = reader.nextLine();
					System.out.println(info);
				}
				reader.close();
				
			} 
			catch (FileNotFoundException e) 
			{
				System.out.println("An error occurred");
				e.printStackTrace();
			}
			System.out.println("____________________________________________________________ ");

		}
		
		/*------------------------method if o is selected---------------------------*/

		private static void OrderAlphabetical(Cabin[] cruise)
		{
			System.out.println("_____________Alphabatically ordered list of passengers______________");
			System.out.println();
			String[] tempArr = new String[cruise.length];
			for (int i = 0; i < cruise.length; i++) //to get array clone without referencing
			{ 
				tempArr[i] = cruise[i].getName();
			}
			
			//sorting 
			String temp;
			for (int j = 0; j < tempArr.length; j++) 
			{
				for (int i = 1; i < tempArr.length; i++) 
				{
					if (tempArr[i - 1].compareToIgnoreCase(tempArr[i]) > 0) 
					{
						temp = tempArr[i - 1];
						tempArr[i - 1] = tempArr[i];
						tempArr[i] = temp;
					}
				}
			}
			
			//leaving the empty cabins and printing cabins with passengers only
			for (String s : tempArr)
			{
				if (!s.equals("Empty"))
				{
					for (int y = 0; y < tempArr.length; y++)
					{
						if (s.equals(cruise[y].getName()))
						{
							System.out.println(s + " has occupied cabin "+ (y+1));
						}
					}
				}
			}
			System.out.println();
			System.out.println("____________________________________________________________");
		}
}	
	