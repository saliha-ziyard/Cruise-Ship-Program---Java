import java.io.*;
import java.util.*;

public class CruiseShip
{
	    // common property of all objects
		static Scanner input = new Scanner(System.in);
		static String[] cruise = new String[12];
		
		static int cabinNum = 0;  
		static String cabinName;
		
		static int passengerCount = 0;  //used when deleting 
		
	public static void main(String[] args)   //executed
	{
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
				ViewAllCabins(cruise);
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
		if (continueOrNot.equals("n"))
		{
			System.out.println("___________________________________________Thank You!!_______________________________________");
		}
		// if something else is entered the program will end
		
	}
	
	//initializing
	private static void initialise(String[] array)
	{
		//emptying the array index
		for (int x = 0; x< array.length; x++)
		{
			array[x] = "Empty";
		}
	}
	
	//methods to all options in the menu
	
	
	/*------------------------method if v is selected---------------------------*/
	
	private static void ViewAllCabins(String[] array)
	{
		System.out.println("_____________________All Cabins___________________________");
		System.out.println();
		for (int x = 0; x < array.length; x++)  
		{
			if (array[x].equals("Empty"))   //check if cabin is empty
			{
				System.out.println("Cabin "+ (x + 1) +" is empty");  //print if empty
			}
			else
			{
				System.out.println("Cabin " + (x + 1) + " is occupied by " + array[x]);   //print if not empty by taking the index
			    
			}
			System.out.println();
		}
		
		System.out.println("____________________________________________________________");
		
	}
	
	
	/*------------------------method if a is selected---------------------------*/
	private static void AddCustomer(String[] array)
	{
		System.out.println("_____________________Add passenger to cabin___________________"); 
		System.out.println();
		System.out.print("Select a cabin number between 1 and 12: ");
		cabinNum = input.nextInt();
		
		
		while (true)
		{
			if (cabinNum >=1 && cabinNum <= 12) //check if cabin number is valid
			{
				break;  
			}
			else
			{
				System.out.println("Invalid Cabin Number");
				System.out.println("Select a cabin number between 1 and 12: ");   //will be printed until user enters correct number
		        cabinNum = input.nextInt();	
			}
		}
		
		//check whether cabins are full
		boolean cabinFull = false;  
		for (String s : array)
		{
			if (s.equals("Empty"))  //if cabin full
			{
				cabinFull = false;
				break;
			}
			else
			{
				cabinFull = true;
			}
		}
		
		
		
		if (!cabinFull) //cabinFull is true
		{
			System.out.print("Enter your name to book cabin " + cabinNum + ": "); //cabin not full
			array[cabinNum - 1] = input.next();  
			
			
			System.out.println();
			System.out.println("________________Passenger details have been added_____________");
			passengerCount++;
		} 
		else
		{
			System.out.println();
			System.out.println("___________________________Cabins are Full____________________");
		}
		
	}
	
	
	
	
	/*------------------------method if e is selected---------------------------*/
	
	private static void DisplayEmptyCabins(String[] array)
	{
		System.out.println("_____________________________Empty Cabins___________________________");
		System.out.println();
		for (int x = 0; x< array.length; x++)
		{
			if (array[x].equals("Empty"))   //check if cabin is empty
			{
				System.out.println("Cabin "+ (x + 1) +" is empty"); //print if empty
			}
		}
		System.out.println();
		System.out.println("____________________________________________________________");
		
	}
	
	/*------------------------method if d is selected---------------------------*/
	
	private static void DeleteCustomer(String[] array)
	{
		System.out.println("_______________________Remove Passenger___________________________");
		System.out.println();
		System.out.print("Enter the cabin from which the passenger should be deleted from: ");
		cabinNum = input.nextInt();
		
		while (cabinNum < 1|| cabinNum >12)  //checks if cabin number entered is valid
		{
			System.out.println("Invalid cabin number. Try again");
			System.out.println("Select a cabin number between 1 and 12: ");
			cabinNum = input.nextInt();
		}
		
		//clearing passenger details
		array[cabinNum - 1] = "Empty";  
		
		if (passengerCount ==0){
			System.out.println("Passengers still not added to the " + cabinNum);
		}
		else{
			System.out.println("Passenger from cabin number " + cabinNum +" has been removed."); //print after clearing passenger details
			System.out.println();
			System.out.println("____________________________________________________________");
		}
	  
		
	}
	
	/*------------------------method if f is selected---------------------------*/
	
	private static void FindByName(String[] array)
	{
		System.out.println("_________________________Find Passenger_______________________");
		System.out.println();
		System.out.print("Enter passenger's name: ");
		cabinName = input.nextLine();
		
		while (true)
		{
			
			if (cabinName.matches("^[a-z A-Z]*$"))  //check whether cabinName has alphabets
				{
					break;
				}
				else
				{
					System.out.println("Invalid Input");
					System.out.println("Enter passenger's name: ");
					cabinName = input.nextLine();
				}
		}
		
		
		boolean found = true;  // initiates boolean value
		for (int x = 0; x< 12; x++)  //Traverse through the array and check for the passenger
		{
			if (array[x].equals(cabinName))
			{
				System.out.println(cabinName + " is in cabin number "+ (x + 1));   //which passenger is in which cabin
				
				found = true;  //then found is true
				break;
			}
			else
			{
				found = false;
			}
		}
		if (!found)  //if found is false
		{
			System.out.println(cabinName+" was not found.");
		}
		System.out.println();
		System.out.println("____________________________________________________________");
	}
	
	/*------------------------method if s is selected---------------------------*/
	
	private static void StoreIntoFile(String[] array)
	{
		 //store data into txt file
		 try
		 {
			FileWriter storeFile = new FileWriter("storeFile.txt");
			storeFile.write("______________________Cruise Data__________________________" + "\n");
			storeFile.write("\n");
			
			for (int x=0; x<12; x++)
			{
				if (array[x].equals("Empty"))
				{
					storeFile.write("____________________________________________________________" + "\n");
					storeFile.write("Cabin number "+ (x + 1) + " is empty.\n");
				}
				else
				{
					storeFile.write("____________________________________________________________"+"\n");
					storeFile.write("Cabin number "+ (x + 1) + " is occupied by "+ array[x] +"\n");
					
				}
				storeFile.write("____________________________________________________________" + "\n");
				storeFile.write("\n");
				
			}
			storeFile.close();
			System.out.println("Successfully stored into a file");
		 }
		 catch (IOException e)
		 {
			 System.out.println("An error occured");
			 e.printStackTrace();
		 }
	}
	
	
	/*------------------------method if l is selected---------------------------*/
	
	private static void LoadFromFile()
	{  
		//loading data of the stores file
		 System.out.println("Loaded data: ");
		 try{
			 File object = new File("storeFile.txt");
			 Scanner reader = new Scanner(object);
			 while (reader.hasNextLine())
			 {
				 String info = reader.nextLine();
				 System.out.println(info);
			 }
			 reader.close();
		 }
		 catch (FileNotFoundException e)
		 {
			  System.out.println("An error occured");
			  e.printStackTrace();
		 }
		  System.out.println("____________________________________________________________");
		 
	}
	
	
	/*------------------------method if o is selected---------------------------*/
	
	private static void OrderAlphabetical(String[] array)
	{
		System.out.println("_______________Alphabatically ordered list of passengers________________");
        System.out.println();
		String[] tempArr = new String[array.length];
		for (int i = 0; i < array.length; i++) //to get array clone without referencing
		{ 
			tempArr[i] = array[i];
		}
		
		//sorting array
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
					if (s.equals(array[y]))
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