package hw4;

/**
 * The TextBook class represents library items that can be checked out under the
 * following policy:
 * the lending period is 2 days,
 * the item cannot be renewed, and
 * the fine is 50 cents per day for the first seven days that the item is late,
 * and is one dollar per day thereafter.
 * 
 * @author Carly Hafner
 */
public class TextBook extends LibraryItem
{
	/**
	 * The amount of time a book may be checked out
	 */
	public static final int LENDING_PERIOD = 2;
	/**
	 * The number of days before the fine increases
	 */
	public static final int NUMBER_OF_DAYS_BEFORE_FINE_INCREASES = 7;
	/**
	 * The original fine per day in cents
	 */
	public static final int FINE_PER_DAY_BEFORE_FINE_INCREASES = 50;
	/**
	 * The increased fine per day in cents
	 */
	public static final int FINE_PER_DAY_AFTER_FINE_INCREASES = 100;
	/**
	 * The type of library item
	 */
	private final String type;

	/**
	 * Constructs a book with the given title and author.
	 * 
	 * @param title
	 *            the title for this book
	 * @param author
	 *            the author for this book
	 */
	public TextBook(int itemID, String title, String author)
	{
		super(itemID, title, author);
		type = "TEXTBOOK";
	}

	@Override public void checkOutItem(SimpleDate today) throws LibraryException
	{
		if (status == true)
		{
			throw new LibraryException();
		}
		else
		{
			status = true;
			dueDate = new SimpleDate(today, LENDING_PERIOD);
		}
	}

	@Override public int returnItem(SimpleDate today) throws LibraryException
	{
		int fine = 0;
		if (status == false)
		{
			throw new LibraryException();
		}
		else if (dueDate.isBefore(today))
		{
			int daysOverdue = dueDate.daysUntil(today);
			if (daysOverdue <= NUMBER_OF_DAYS_BEFORE_FINE_INCREASES)
			{
				fine = FINE_PER_DAY_BEFORE_FINE_INCREASES * daysOverdue;
			}
			else
			{
				fine = FINE_PER_DAY_BEFORE_FINE_INCREASES * NUMBER_OF_DAYS_BEFORE_FINE_INCREASES;
				fine += FINE_PER_DAY_AFTER_FINE_INCREASES * (daysOverdue - NUMBER_OF_DAYS_BEFORE_FINE_INCREASES);
			}

		}
		status = false;
		dueDate = null;
		return fine;
	}

	@Override public String getType()
	{
		return type;
	}

	@Override public boolean renew()
	{
		// Text books may not be renewed.
		return false;
	}
}
