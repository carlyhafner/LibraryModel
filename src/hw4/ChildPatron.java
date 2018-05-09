package hw4;

/**
 * A ChildPatron is a user of a library who can check out library items. At any
 * given time a patron has a list of items currently checked out, and a balance
 * representing fines currently owed (in cents). A ChildPatron is not allowed to
 * check anything out if any library fines are owed.
 * 
 * @author Carly Hafner
 */
public class ChildPatron extends LibraryPatron
{

	/**
	 * Constructs a ChildPatron with the given name. Initially there are no
	 * items checked out and the balance is zero.
	 * 
	 * @param name
	 *            the new patron's name
	 */
	public ChildPatron(String name)
	{
		super(name);
	}

	/**
	 * Checks out an item and adds it to this patrons's list of items, but only
	 * if the patron does not owe any library fines. If the item can be checked
	 * out, this method updates the item's status (including the due date) and
	 * then adds it to this patron's list of items.
	 */
	public void borrowItem(LibraryItem item, SimpleDate date) throws LibraryException
	{
		if (getBalance() == 0)
		{
			super.borrowItem(item, date);
		}
		else
		{
			throw new LibraryException();
		}
	}

}
