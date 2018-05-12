package library;

/**
 * A LibraryItem represents a resource in a library. It is
 * uniquely identified by an author and title (that is, there
 * are no items with multiple copies). The "author" may be
 * a person or an entity (such as a movie studio). If the
 * author is a person, the author's name must begin with the
 * last name followed by a comma.
 * 
 * @author Carly Hafner
 */
public abstract class LibraryItem
{

	/**
	 * The date the item is due
	 */
	protected int itemID;
	/**
	 * The title of the item
	 */
	protected String title;
	/**
	 * The author of the item
	 */
	protected String author;
	/**
	 * Whether the item is checked out or not: true if checked out, false otherwise
	 */
	protected boolean status;
	/**
	 * The date the item is due
	 */
	protected SimpleDate dueDate;
	
	/**
	 * Checks out this item if possible. Some items do not circulate and
	 * cannot be checked out. In addition, the item cannot be checked out
	 * if it is already checked out.
	 * 
	 * @param today
	 *            the date on which this item is being checked out
	 * @throws LibraryException
	 *             if the item cannot be checked out
	 */
	public abstract void checkOutItem(SimpleDate today) throws LibraryException;

	/**
	 * Returns this item and calculates the fine, if any.
	 * 
	 * @param today
	 *            the date on which the item is being returned
	 * @return the fine owed
	 * @throws LibraryException
	 *             if the item is not currently checked out
	 */
	public abstract int returnItem(SimpleDate today) throws LibraryException;

	/**
	 * Returns a String representation of the type of this item,
	 * such as "GENERAL", "TEXTBOOK", "REFERENCE", or "DVD".
	 * 
	 * @return type of this item
	 */
	public abstract String getType();

	/**
	 * Renews this item if possible. Returns false if the item
	 * is not checked out or cannot be renewed.
	 * 
	 * @return true if the item was renewed, false otherwise
	 */
	public abstract boolean renew();

	/**
	 * Constructs a new LibraryItem with the given title and author.
	 * This constructor may only be invoked by subclasses.
	 * 
	 * @param title
	 *            the title of the item
	 * @param author
	 *            the author of the item
	 */
	protected LibraryItem(int itemID, String title, String author)
	{
		this.itemID = itemID;
		this.title = title;
		this.author = author;
		status = false;
		dueDate = null;
	}

	/**
	 * Determines whether this item is currently checked out.
	 * 
	 * @return true if this item is checked out, false otherwise
	 */
	public boolean isCheckedOut()
	{
		return status;
	}

	/**
	 * Returns the due date for this item if it is currently checked out.
	 * Returns null if the item is not checked out.
	 * 
	 * @return due date for this item
	 */
	public SimpleDate getDueDate()
	{
		return dueDate;
	}

	/**
	 * Returns the title of this item.
	 * 
	 * @return tile of this item
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the author of this item.
	 * 
	 * @return author of this item
	 */
	public String getAuthor()
	{
		return author;
	}
	
	/**
	 * Returns the itemID of this item.
	 * 
	 * @return author of this item
	 */
	public int getItemID()
	{
		return itemID;
	}

	/**
	 * Returns a representation of the state of this object as a
	 * multiline string. The format is:
	 * 
	 * <pre>
	 *   itemID
	 *   	type
	 *   	title
	 *   	author
	 *   	status
	 * </pre>
	 * 
	 * the <tt>type</tt> is normally one of the strings "GENERAL",
	 * "TEXTBOOK", "REFERENCE", or "DVD". The status is either
	 * 
	 * <pre>
	 *   Checked Out: yyyy-mm-dd
	 * </pre>
	 * 
	 * or
	 * 
	 * <pre>
	 *   Checked In
	 * </pre>
	 * 
	 * where "yyyy-mm-dd" is the current due date.
	 * 
	 * @return a string representation of this object
	 */
	public String toString()
	{
		String s = itemID + "\n"
			+ "\t" + getType() + "\n"
			+ "\t" + title + "\n"
			+ "\t" + author + "\n";
		if (isCheckedOut())
		{
			s = s + "\t" + "Checked Out: " + getDueDate().toString();
		}
		else
			s = s + "\t" + "Checked In";
		return s;
	}

}
