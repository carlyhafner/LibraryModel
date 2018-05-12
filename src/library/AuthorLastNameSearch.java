package library;

import java.util.Scanner;

/**
 * Implementation of SearchCondition that matches items based on the author's
 * last name. Items not authored by a person are never matched.
 * 
 * @author Carly Hafner
 */
public class AuthorLastNameSearch implements SearchCondition
{

	/**
	 * The last name to search for.
	 */
	private final String lastName;
	
	/**
	 * Constructs an AuthorLastNameSearch for the given last name.
	 * 
	 * @param lastName
	 *            the last name to search for
	 */
	public AuthorLastNameSearch(String lastName)
	{
		this.lastName = lastName;
	}

	@Override public boolean matches(LibraryItem item)
	{
		boolean matches = false;
		//String[] tokens = item.getAuthor().split(",");
		//String itemAuthorLastName = tokens[0];
		Scanner s = new Scanner(item.getAuthor());
		s.useDelimiter(",");
		String itemAuthorLastName = s.next();
		if(itemAuthorLastName.equalsIgnoreCase(lastName))
		{
			matches = true;
		}
		s.close();
		return matches;
	}

}
