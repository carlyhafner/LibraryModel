package hw4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu
{

	public static void main(String[] args)
	{
		
		boolean continueMenu = true;
		Library myLibrary = new Library();
		Scanner sc = new Scanner(System.in);
		while(continueMenu) {
			//print the menu
			System.out.println(
				"Welcome to Carly's Library\n"
				+ "Please select an option from the following list by entering the corresponding number.\n"
				+ "Main options:\n"
				+ "\t" + "1) Checkout book\n"
				+ "\t" + "2) Return book\n"
				+ "\t" + "3) Renew book\n"
				+ "\t" + "4) Pay fines\n"
				+ "Search options:\n"
				+ "\t" + "5) Search by title keyword\n"
				+ "\t" + "6) Search by author\n"
				+ "\t" + "7) Search by author's last name\n"
				+ "Cataloging options:\n"
				+ "\t" + "8) Get information about types of library item\n"
				+ "\t" + "9) Add a general book\n"
				+ "\t" + "10) Add a textbook\n"
				+ "\t" + "11) Add a reference book\n"
				+ "\t" + "12) Add a DVD\n"
				+ "\t" + "13) List all items\n"
				+ "Patron management options:\n"
				+ "\t" + "14) Add an adult patron\n"
				+ "\t" + "15) Add a child patron\n"
				+ "\t" + "16) List all patrons\n"
				+ "Library management options:\n"
				+ "\t" + "17) Load library from file\n"
				+ "\t" + "18) Save library to file (Coming soon!)\n"
				+ "\t" + "19) QUIT\n"			
				);
			//get input
			int menuOption = 0;
			try {
				menuOption =sc.nextInt();
			}catch(InputMismatchException e) {
				sc.next();
			}
			switch(menuOption) {
			//Main options
			case 1: //checkout book
				System.out.println("Enter the name of the patron checking out.");
				sc.nextLine();
				String patronCheckingOutName = sc.nextLine();
				LibraryPatron patronCheckingOut = myLibrary.findUser(patronCheckingOutName);
				if(patronCheckingOut == null) {
					System.out.println("ACTION FAILED. The patron was not found.\n");
					break;
				}
				System.out.println("Enter the item's ID number.");
				int checkoutItemID = 0;
				try {
					checkoutItemID = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("ACTION FAILED. Item ID number expected.\n");
					break;
				}
				LibraryItem itemCheckingOut = myLibrary.findItem(checkoutItemID);
				if (itemCheckingOut == null) {
					System.out.println("ACTION FAILED. The item was not found.\n");
					break;
				}
				System.out.println("Enter date in form yyyy-mm-dd.");
				sc.nextLine();
				String dateCheckingOutString = sc.nextLine();
				String[] tokensCheckingOut = dateCheckingOutString.split("-");
				int yearCheckingOut = 0;
				int monthCheckingOut = 0;
				int dayCheckingOut = 0;
				try {
					yearCheckingOut = Integer.parseInt(tokensCheckingOut[0]);
					monthCheckingOut = Integer.parseInt(tokensCheckingOut[1]);
					dayCheckingOut = Integer.parseInt(tokensCheckingOut[2]);
				}catch(NumberFormatException e) {
					System.out.println("ACTION FAILED. Bad date entry.\n");
					break;
				}
				SimpleDate dateCheckingOut = new SimpleDate(yearCheckingOut,monthCheckingOut,dayCheckingOut);
				try {
					patronCheckingOut.borrowItem(itemCheckingOut, dateCheckingOut);
				}catch(LibraryException e) {
					System.out.println("ACTION FAILED. Item could not be checked out.\n");
					break;
				}
				System.out.println("The item was successfully checked out.");
				System.out.println(myLibrary.findItem(checkoutItemID).toString() + "\n");
				break;
			case 2: //return book
				System.out.println("Enter the name of the patron returning an item.");
				sc.nextLine();
				String patronReturningName = sc.nextLine();
				LibraryPatron patronReturning = myLibrary.findUser(patronReturningName);
				if(patronReturning == null) {
					System.out.println("ACTION FAILED. The patron was not found.\n");
					break;
				}
				System.out.println("Enter the item's ID number.");
				int returningItemID = 0;
				try {
					returningItemID = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("ACTION FAILED. Item ID number expected.\n");
					break;
				}
				LibraryItem itemReturning = myLibrary.findItem(returningItemID);
				if (itemReturning == null) {
					System.out.println("ACTION FAILED. The item was not found.\n");
					break;
				}
				System.out.println("Enter date in form yyyy-mm-dd.");
				sc.nextLine();
				String dateReturningString = sc.nextLine();
				String[] tokensReturning = dateReturningString.split("-");
				int yearReturning = 0;
				int monthReturning = 0;
				int dayReturning = 0;
				try {
					yearReturning = Integer.parseInt(tokensReturning[0]);
					monthReturning = Integer.parseInt(tokensReturning[1]);
					dayReturning = Integer.parseInt(tokensReturning[2]);
				}catch(NumberFormatException e) {
					System.out.println("ACTION FAILED. Bad date entry.\n");
					break;
				}
				SimpleDate dateReturning = new SimpleDate(yearReturning,monthReturning,dayReturning);
				try {
					patronReturning.bringBackItem(itemReturning, dateReturning);
				}catch(LibraryException e) {
					System.out.println("ACTION FAILED. Item could not be returned.\n");
					break;
				}
				System.out.println("The item was successfully returned.");
				System.out.println(myLibrary.findItem(returningItemID).toString() + "\n");
				break;
			case 3: //renew book
				System.out.println("Enter the item's ID number.");
				int renewingItemID = 0;
				try {
					renewingItemID = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("ACTION FAILED. Item ID number expected.\n");
					break;
				}
				LibraryItem itemRenewing = myLibrary.findItem(renewingItemID);
				if (itemRenewing == null) {
					System.out.println("ACTION FAILED. The item was not found.\n");
					break;
				}
				if(itemRenewing.renew()) {
					System.out.println("The item was successfully renewed.");
				} else {
					System.out.println("ACTION FAILED. Item could not be renewed.\n");
				}
				break;
			case 4: //pay fines
				//TODO
				System.out.println("Enter the name of the patron paying fines.");
				sc.nextLine();
				String patronPayingFinesName = sc.nextLine();
				LibraryPatron patronPayingFines = myLibrary.findUser(patronPayingFinesName);
				if(patronPayingFines == null) {
					System.out.println("ACTION FAILED. The patron was not found.\n");
					break;
				}
				System.out.println("Patron's current fines: " + patronPayingFines.getBalance());
				System.out.println("Enter the amount to pay.");
				int paymentAmount = 0;
				try {
					paymentAmount = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("ACTION FAILED. Number input expected.\n");
					break;
				}
				patronPayingFines.payFines(paymentAmount);
				System.out.println("Amount paid: " + paymentAmount);
				System.out.println("Patron's current fines: " + patronPayingFines.getBalance());
				break;
			//Search options
			case 5: //search by title keyword
				Menu.doTitleKeywordSearch(sc, myLibrary);
				
				break;
			case 6: //search by author
				Menu.doAuthorSearch(sc, myLibrary);
				
				break;
			case 7: //search by author's last name
				Menu.doAuthorLastNameSearch(sc, myLibrary);
				
				break;
			//Cataloging options
			case 8: //Get information about types of library item
				System.out.println(Menu.getInformationAboutLibraryItems());
				break;
			case 9: //Add a general book
				System.out.println("Please enter the title of a general book to be added to the collection.");
				sc.nextLine();
				String generalBookTitle = sc.nextLine();
				System.out.println("Please enter the author (in the form Last, First).");
				String generalBookAuthor = sc.nextLine();
				System.out.println("Please enter the item ID number for the item. This should be a unique 4-digit number.");
				int generalBookItemID = sc.nextInt();
				GeneralBook generalBook = new GeneralBook(generalBookItemID, generalBookTitle, generalBookAuthor);
				myLibrary.addItem(generalBook);
				System.out.println("The following book has been added to the library:");
				System.out.println(generalBook.toString() + "\n");
				break;
			case 10: //Add a textbook
				System.out.println("Please enter the title of a textbook to be added to the collection.");
				sc.nextLine();
				String textBookTitle = sc.nextLine();
				System.out.println("Please enter the author (in the form Last, First).");
				String textBookAuthor = sc.nextLine();
				System.out.println("Please enter the item ID number for the item. This should be a unique 4-digit number.");
				int textBookItemID = sc.nextInt();
				TextBook textBook = new TextBook(textBookItemID, textBookTitle, textBookAuthor);
				myLibrary.addItem(textBook);
				System.out.println("The following book has been added to the library:");
				System.out.println(textBook.toString() + "\n");
				break;
			case 11: //Add a reference book
				System.out.println("Please enter the title of a reference book to be added to the collection.");
				sc.nextLine();
				String referenceBookTitle = sc.nextLine();
				System.out.println("Please enter the author (in the form Last, First).");
				String referenceBookAuthor = sc.nextLine();
				System.out.println("Please enter the item ID number for the item. This should be a unique 4-digit number.");
				int referenceBookItemID = sc.nextInt();
				ReferenceBook referenceBook = new ReferenceBook(referenceBookItemID, referenceBookTitle, referenceBookAuthor);
				myLibrary.addItem(referenceBook);
				System.out.println("The following book has been added to the library:");
				System.out.println(referenceBook.toString() + "\n");
				break;
			case 12: //Add a DVD
				System.out.println("Please enter the title of a DVD to be added to the collection.");
				sc.nextLine();
				String dvdTitle = sc.nextLine();
				System.out.println("Please enter the author [director] (in the form Last, First).");
				String dvdAuthor = sc.nextLine();
				System.out.println("Please enter the item ID number for the item. This should be a unique 4-digit number.");
				int dvdItemID = sc.nextInt();
				DVD dvd = new DVD(dvdItemID, dvdTitle, dvdAuthor);
				myLibrary.addItem(dvd);
				System.out.println("The following book has been added to the library:");
				System.out.println(dvd.toString() + "\n");
				break;
			case 13: //List all items
				System.out.println("The library currently contains the following books:\n" + myLibrary.listLibraryItems() +"\n");
				break;
			//Patron management options
			case 14: //Add an adult patron
				System.out.println("Please enter the name of the adult patron (first and last).");
				sc.nextLine();
				String adultName = sc.nextLine();
				LibraryPatron adult= new LibraryPatron(adultName);
				myLibrary.addPatron(adult);
				System.out.println("The adult patron has been added to the library.\n");
				break;
			case 15: //Add a child patron
				System.out.println("Please enter the name of the child patron (first and last).");
				sc.nextLine();
				String childName = sc.nextLine();
				ChildPatron child= new ChildPatron(childName);
				myLibrary.addPatron(child);
				System.out.println("The child patron has been added to the library.\n");
				break;
			case 16: //List all patrons
				System.out.println("The library currently contains the following patrons:\n" + myLibrary.listLibraryPatrons() +"\n");
				break;
			//Library management options
			case 17: //Load library from file
				System.out.println("Loading library from file...");
				Menu.loadLibraryFromFile(myLibrary);
				System.out.println("Library has been loaded.\n");
				break;
			case 18: //Save library to file
				//TODO
				System.out.println("Thank you for your interest! This feature is coming soon!\n");
				break;
			case 19: //Quit
				boolean gotValidQuitInput = false;
				while (!gotValidQuitInput) {
					System.out.println("Are you sure you want to quit? You will lose any unsaved data.\n"
							+ "\t" + "1) Quit\n"
							+ "\t" + "2) Go back to the menu\n");
					int quitInput = 0;
					try{
						quitInput = sc.nextInt();
					}catch(InputMismatchException e) {
						System.out.println("INVALID INPUT\nPlease enter a valid response.\n");
						sc.nextLine();
						continue;
					}
					switch(quitInput) {
					case 1: //quit
						gotValidQuitInput = true; //exits the quit case
						continueMenu = false; //exits the menu
						break;
					case 2: //go back to the menu
						gotValidQuitInput = true; //exits the quit case
						break;
					default:
						System.out.println("INVALID INPUT\nPlease enter a valid response.\n");
					}
				}
				break;
			default:
				System.out.println("INVALID INPUT\nPlease enter a number corresponding with a menu item.\n");
			}
		}
		sc.close();
		System.out.println("Thank you for using Carly's Library.\nGoodbye.");
	}

	private static ArrayList<LibraryItem> doAuthorLastNameSearch(Scanner scan, Library library)
	{
		System.out.println("Enter last name for author search.");
		scan.nextLine();
		String authorLastName = scan.nextLine();
		System.out.println(authorLastName);
		AuthorLastNameSearch authorLastNameCondition = new AuthorLastNameSearch(authorLastName);
		ArrayList<LibraryItem> authorLastNameResultList = library.search(authorLastNameCondition);
		if(authorLastNameResultList.isEmpty()){
			System.out.println("No items matched your search.\n");
		}else {
			System.out.println("Items matching your search:");
			System.out.println(Menu.listSearchResults(authorLastNameResultList) + "\n");
		}
		return authorLastNameResultList;
	}

	private static ArrayList<LibraryItem> doTitleKeywordSearch(Scanner scan, Library library)
	{
		System.out.println("Enter keyword for title keyword search.");
		scan.nextLine();
		String keyword = scan.nextLine();
		TitleKeywordSearch titleKeywordCondition = new TitleKeywordSearch(keyword);
		ArrayList<LibraryItem> titleKeywordResultList = library.search(titleKeywordCondition);
		if(titleKeywordResultList.isEmpty()){
			System.out.println("No items matched your search.\n");
		}else {
			System.out.println("Items matching your search:");
			System.out.println(Menu.listSearchResults(titleKeywordResultList) + "\n");
		}
		return titleKeywordResultList;
	}

	private static ArrayList<LibraryItem> doAuthorSearch(Scanner scan, Library library)
	{
		System.out.println("Enter full name (Last, First) for author search.");
		scan.nextLine();
		String authorName = scan.nextLine();
		AuthorSearch authorCondition = new AuthorSearch(authorName);
		ArrayList<LibraryItem> authorResultList = library.search(authorCondition);
		if(authorResultList.isEmpty()){
			System.out.println("No items matched your search.\n");
		}else {
			System.out.println("Items matching your search:");
			System.out.println(Menu.listSearchResults(authorResultList) + "\n");
		}
		return authorResultList;
	}

	private static String listSearchResults(ArrayList<LibraryItem> resultList)
	{
		String resultString = "";
		//for(LibraryItem item : resultList) {
		//	resultString += item.toString() + "\n";
		//}
		for(int i = 0; i < resultList.size(); i++) {
			resultString += "Result " + i + "\n" + resultList.get(i).toString() + "\n";
		}
		return resultString;
	}

	private static void loadLibraryFromFile(Library library)
	{
		//Load patrons from files
		Menu.addLibraryPatronFromFile(library, "LibraryPatronFile.txt");
		Menu.addChildPatronFromFile(library, "ChildPatronFile.txt");
		//Load library items from files
		Menu.addGeneralBookFromFile(library, "GeneralBookFile.txt");
		Menu.addTextBookFromFile(library, "TextBookFile.txt");
		Menu.addReferenceBookFromFile(library, "ReferenceBookFile.txt");
		Menu.addDVDFromFile(library, "DVDFile.txt");
	}

	private static String getInformationAboutLibraryItems()
	{
		String str = ""
				+ "About GENERAL books:\n"
				+ "\t" + "Fine per day (in cents): " + GeneralBook.FINE_PER_DAY + "\n"
				+ "\t" + "Lending Period (in days): " + GeneralBook.LENDING_PERIOD + "\n"
				+ "\t" + "Number of times this type of item can be renewed: " + GeneralBook.NUMBER_OF_TIMES_MAY_BE_RENEWED + "\n"
				+ "About TEXTBOOKS:\n"
				+ "\t" + "Initial fine per day (in cents): " + TextBook.FINE_PER_DAY_BEFORE_FINE_INCREASES + "\n"
				+ "\t" + "Number of days before fine increases: " + TextBook.NUMBER_OF_DAYS_BEFORE_FINE_INCREASES + "\n"
				+ "\t" + "Fine per day after fine increases: " + TextBook.FINE_PER_DAY_AFTER_FINE_INCREASES + "\n"
				+ "\t" + "Lending Period (in days): " + TextBook.LENDING_PERIOD + "\n"
				+ "\t" + "Textbooks may not be renewed." + "\n"
				+ "About REFERENCE books:" + "\n"
				+ "\t" + "Reference books may not be checked out." + "\n"
				+ "About DVDs"
				+ "\t" + "Fine per day (in cents): " + DVD.FINE_PER_DAY + "\n"
				+ "\t" + "Lending Period (in days): " + DVD.LENDING_PERIOD + "\n"
				+ "\t" + "DVDs may not be renewed." + "\n\n";
		return str;
		
	}
	private static void addChildPatronFromFile(Library library, String fileName)
	{
		String line = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine()) != null) {
				library.addPatron(new ChildPatron(line));
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
	}

	private static void addLibraryPatronFromFile(Library library, String fileName)
	{
		String line = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((line = br.readLine()) != null) {
				library.addPatron(new LibraryPatron(line));
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
	}

	private static void addDVDFromFile(Library library, String fileName)
	{
		String str = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((str = br.readLine()) != null) {
				String[] tokens = str.split("/");
				library.addItem(new DVD(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));				
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
	}

	private static void addReferenceBookFromFile(Library library, String fileName)
	{
		String str = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((str = br.readLine()) != null) {
				String[] tokens = str.split("/");
				library.addItem(new ReferenceBook(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));				
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
		
	}

	private static void addTextBookFromFile(Library library, String fileName)
	{
		String str = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((str = br.readLine()) != null) {
				String[] tokens = str.split("/");
				library.addItem(new TextBook(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));				
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}
	}

	private static void addGeneralBookFromFile(Library library, String fileName)
	{
		String str = null;
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			while((str = br.readLine()) != null) {
				String[] tokens = str.split("/");
				library.addItem(new GeneralBook(Integer.parseInt(tokens[0]), tokens[1], tokens[2]));				
			}
			br.close();
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file: " + fileName);
		}
		catch(IOException ex) {
			System.out.println("Error reading file: " + fileName);
		}		
	}

}
