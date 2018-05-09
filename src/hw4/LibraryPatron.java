package hw4;

import java.util.ArrayList;

/**
 * A LibraryPatron is a user of a library who can check out
 * library items. At any given time a patron has a list of
 * items currently checked out, and a balance representing
 * fines currently owed (in cents).
 * 
 * @author Carly Hafner
 */
public class LibraryPatron
{

	/**
	 * The name of the patron
	 */
	private String name;
	/**
	 * A list of the patron's checked out items
	 */
	private ArrayList<LibraryItem> itemsCheckedOut;
	/**
	 * The patron's fine balance
	 */
	private int balance;

	/**
	 * Constructs a LibraryPatron with the given name. Initially there are no
	 * items checked out and the balance is zero.
	 * 
	 * @param name
	 *            the new patron's name
	 */
	public LibraryPatron(String name)
	{
		this.name = name;
		itemsCheckedOut = new ArrayList<LibraryItem>();
		balance = 0;
	}

	/**
	 * Checks out an item and adds it to this patrons's list of items. If the
	 * item can be checked out, this method updates the item's status (including
	 * the due date) and then adds it to this patron's list of items.
	 * 
	 * @param item
	 *            the item to be checked out
	 * @param date
	 *            the date on which the item is being checked out
	 * @throws LibraryException
	 *             if the item cannot be checked out for any reason
	 */
	public void borrowItem(LibraryItem item, SimpleDate date) throws LibraryException
	{
		item.checkOutItem(date);
		itemsCheckedOut.add(item);
	}

	/**
	 * Returns an item that this patron currently has checked out and updates
	 * the balance if a fine was owed. If the item can be successfully returned,
	 * this method updates the item's status and removes it from this patron's
	 * list of items.
	 * 
	 * @param item
	 *            the item to be returned
	 * @param date
	 *            the date on which the item is being returned
	 * @throws LibraryException
	 *             if this patron does not have the given item checked out
	 */
	public void bringBackItem(LibraryItem item, SimpleDate date) throws LibraryException
	{
		boolean wasNotReturned = true;
		//iterates through the list of checked out items
		for(int i = 0; i < itemsCheckedOut.size(); i += 1)
		{
			LibraryItem LibItem = itemsCheckedOut.get(i);
			//if the item is found, it is returned
			if (LibItem.getTitle().equals(item.getTitle()) && LibItem.getAuthor().equals(item.getAuthor()))
			{
				int fine = LibItem.returnItem(date);
				balance += fine;
				itemsCheckedOut.remove(LibItem);
				wasNotReturned = false;
			}
		}
		if (wasNotReturned)
		{
			throw new LibraryException();
		}

	}

	/**
	 * Returns the balance currently owed by this patron.
	 * 
	 * @return
	 *         this user's balance
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * Returns the name of this patron.
	 * 
	 * @return
	 *         this patron's name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Makes a payment on this patron's library fines.
	 * 
	 * @param amount
	 *            the amount to be paid, in cents
	 */
	public void payFines(int amount)
	{
		balance = balance - amount;
	}

	/**
	 * Returns a string representation of this patron. The format consists of
	 * multiple lines. The first line is the patron's name. Subsequent lines are
	 * formed from the toString() values of the items currently checked out,
	 * separated by a newline.
	 * 
	 * @return
	 *         representation of this object as a multi-line string
	 */
	public String toString()
	{
		// TODO
		String s = getName() + "\n";
		for (LibraryItem libItem : itemsCheckedOut)
		{
			s = s + libItem.toString() + "\n";
		}
		return s;
	}

}
