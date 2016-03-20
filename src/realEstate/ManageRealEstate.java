package realEstate;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageRealEstate {

	public static void main(String[] args) {
		//can reference any type of realestate , owned or otherwise
		ArrayList<RealEstate> myRealEstate =	new ArrayList<RealEstate>();
		Scanner userInput = new Scanner(System.in);
		Integer block, lot;
		int choice;
		do{
		   choice = menu();
		   switch (choice){
		   case 1:  //add real estate property
			   RealEstate property = setupRealEstate(userInput);
			   if (property == null){
				   System.out.println("invalid user input couldnt set up property");
			   }
			   else{
			   if (myRealEstate.contains(property)){
				   System.out.println("this property has already been entered " + property.toString());
			   }
			   else{
			      myRealEstate.add(property);
			   }
			   }
			   break;
		   case 2: //list real estate properties
			   System.out.println(myRealEstate);
			   break;
		   case 3:
			       //get new owner information
			    //verify type of real estate
			    System.out.println("Enter block:");
			    block = userInput.nextInt();
			    System.out.println("Enter lot:");
			    lot = userInput.nextInt();
			    String ownFirstName =null , ownLastName= null, ownName=null;
			    
			    String street, state,zipcode,phoneNumber=null;
			    System.out.println("enter type of owner 1. Corporate   2.Individual");
			    int owntype = userInput.nextInt();
			    switch (owntype){
			    case 1:
			    	System.out.println("Enter Company Name:");
			    	 userInput.nextLine();  //flush buffer
			    	ownName = userInput.nextLine();
			    	System.out.println("Phone Number:");
			    	phoneNumber = userInput.next();
			    	break;
			    case 2: 
			    	System.out.println("First Name:");
			    	ownFirstName = userInput.next();
			    	System.out.println("Last Name:");
			    	ownLastName = userInput.next();
			    	break;
			    
			    }
			    
			    //get address information
			    System.out.println("Street:");
			     userInput.nextLine();  //flush the buffer
			    street =  userInput.nextLine();  
			    System.out.println("State");
			    state = userInput.next();
			    System.out.println("Zip:");
			    zipcode = userInput.next();
			    
			    //now search for that real estate
			    boolean found = false;
			    for(RealEstate prop : myRealEstate){
			    	if (prop.getBlock().equals(block) && prop.getLot().equals(lot)){
			    		//found the property, now check if it is an owned property
			    		if (prop instanceof OwnedRealEstate){  //prop "is a" OwnedRealEstate
			    			if (owntype == OwnerType.INDIVIDUAL.ordinal()+1){
			    				((OwnedRealEstate) prop).setOwner(OwnerType.INDIVIDUAL, ownFirstName, ownLastName, street, state, zipcode);
			    			}
			    			else{ //assume owntype == corporate
			    				((OwnedRealEstate) prop).setOwner(OwnerType.CORPORATE, ownName, phoneNumber, street, state, zipcode);
			    				
			    			}
			    		}
			    		else {
			    			System.out.println("Real estate isn't owned , can't set owner");
			    		}
			    		found = true;
			    		break;
			    	}
			    	
			    }
			    if (!found){
			    	System.out.println("cant find property Block " + block + " Lot " + lot);
			    }
			   
			  break;
		   case 4:
			   //verify type of real estate
			    System.out.println("Enter block:");
			    block = userInput.nextInt();
			    System.out.println("Enter lot:");
			    lot = userInput.nextInt();
			    System.out.println("Enter current price");
			    Double price = userInput.nextDouble();
			    found = false;
			    for(RealEstate prop : myRealEstate){
			    	if (prop.getBlock().equals(block) && prop.getLot().equals(lot)){
			    		if (prop instanceof OwnedRealEstate){
			    			//typecast to OwnedRealEstate , in order to be able to access the OwnedRealEstate method, setCurrentPrice
			    			((OwnedRealEstate)prop).setCurrentPrice(price);
			    		}
			    		else{
			    			System.out.println("Cant set price of unowned real estate");
			    		}
			    		found = true;
			    		break;  //took care of request
			    	}
			    	}
			    if (!found){
			    	System.out.println("Can't find Block " + block + " Lot " + lot);
			    }
			    break;
		   case 5:
			   System.out.println("Enter block:");
			   block = userInput.nextInt();
			   System.out.println("Enter lot:");
			   lot = userInput.nextInt();
			   System.out.println("Owner's phone number:");
			   phoneNumber = userInput.next();
			   found  = false;
			   for(RealEstate prop : myRealEstate){
			    	if (prop.getBlock().equals(block) && prop.getLot().equals(lot)){
			    		found = true;
			    		if (prop instanceof OwnedRealEstate){
			    			((OwnedRealEstate)prop).setOwnerPhoneNumber(phoneNumber);
			    			break;  //exit the loop
			    		}
			    		else{
			    			System.out.println("real estate isn't owned - cant set owner data");
			    			break;   //exit the loop
			    		}
			    		
			    		}
			    	}
			   if (!found){
				   System.out.println("cant find Block " + block + " Lot " + lot);
			   }
			   break;
			   
		   case 10:
			   System.out.println("Have a great day... shutting down.....");
			   System.exit(0);
			   
		   }//end switch statement
		    
		
		} while (true);

	}
	
	public static int menu(){
		int choice=0;
		Scanner keyboard = new Scanner(System.in);
		
				        
	   while (choice < 1 || (choice > 5 && choice != 10)){
		   System.out.println("\n1.Add Real Estate Property" +
	                 "\n2. List Real Estate Properties" +
			         "\n3. Change Owner of Property " +
	                 "\n4. Change Price of Property " +
			         "\n5. Change Phone Number of Owner" +
	                
			         "\n10 EXIT Application");
		   choice = keyboard.nextInt();
	   }
	   return choice;
	}
	
  
	private static RealEstate setupRealEstate(Scanner input){
		Integer block, lot;
		System.out.println("Block Number: ");
		block = input.nextInt();
		System.out.println("Lot number:");
		lot = input.nextInt();
		Character response;
		boolean hasOwner;
		System.out.println("Is the real estate owned? Y or N");
		response = input.next().charAt(0);
		int ownerType;
		String firstname,lastname, name, phonenumber;
		if (response.equals('Y') || response.equals('y'))
		{
			hasOwner =true;
			System.out.println("\n1. Individual \n2.Corporation ");
			ownerType = input.nextInt();
			input.nextLine();    //flush the buffer
			
			String street =null, state = null, zip = null;
			
				System.out.println("Enter Owner Address: \nStreet: ");
				
				street = input.nextLine();
				
				System.out.println("State: ");
				state = input.next();
				System.out.println("ZipCode: ");
				zip = input.next();
				
			switch (ownerType){
			case 1:
				System.out.println("First Name:");
				firstname = input.next();
				System.out.println("Last Name:");
				lastname = input.next();
				
				return new OwnedRealEstate ( block, lot,
			   firstname, lastname,	 street, state, zip);
				
				
			case 2:
				System.out.println("Name of Company:");
				input.nextLine(); //flush buffer
				name = input.nextLine();
				System.out.println("Phone Number: ");
				phonenumber = input.next();
				return new OwnedRealEstate(name,  street,   state, zip,
			 phonenumber, block,  lot);
			}
			
			
		}
		
		else{
		
		System.out.println("Real Estate Has Address? Y or N?");
		
		response = input.next().charAt(0);
		boolean hasAddress = false;
		
		String street =null, state = null, zip = null;
		if (response.equals('Y') || response.equals('y'))
		 {
			
			System.out.println("Street: ");
			input.nextLine();  //flush the buffer
			//flush the buffer
			street = input.nextLine();
			System.out.println("State: ");
			state = input.next();
			System.out.println("ZipCode: ");
			zip = input.next();
			hasAddress = true;
			
		 }
		
		if (hasAddress){
		return new RealEstate( block,  lot, street, state,  zip);
		}
		else{
			return new RealEstate(block,lot);
		}
		
		}
		return null;  //shouldnt happen
	}

}
