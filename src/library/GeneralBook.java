package library;

/**
 * The GeneralBook class represents library items that can be checked out under
 * the following policy:
 * the lending period is 14 days,
 * the item can be renewed at most two times, and
 * the fine is 25 cents per day.
 * 
 * @author Carly Hafner
 * 
 */
public class GeneralBook extends LibraryItem
{

	/**
	 * The amount of time a book may be checked out
	 */
	public static final int LENDING_PERIOD = 14;
	/**
	 * The fine per day in cents
	 */
	public static final int FINE_PER_DAY = 25;
	/**
	 * The number of times an item may be renewed
	 */
	public static final int NUMBER_OF_TIMES_MAY_BE_RENEWED = 2;
	/**
	 * The type of library item
	 */
	private final String type;
	/**
	 * The number of times an item has been renewed
	 */
	private int numberOfTimesRenewed;

	/**
	 * Constructs a book with the given title and author.
	 * 
	 * @param title
	 *            the title for this book
	 * @param author
	 *            the author for this book
	 */
	public GeneralBook(int itemID, String title, String author)
	{
		super(itemID, title, author);
		type = "GENERAL";
		numberOfTimesRenewed = 0;
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
			fine = FINE_PER_DAY * daysOverdue;
		}
		status = false;
		dueDate = null;
		numberOfTimesRenewed = 0;
		return fine;
	}

	@Override public String getType()
	{
		return type;
	}

	@Override public boolean renew()
	{
		boolean renewed = false;
		if (status && numberOfTimesRenewed < NUMBER_OF_TIMES_MAY_BE_RENEWED)
		{
			renewed = true;
			SimpleDate previousDueDate = dueDate;
			dueDate = new SimpleDate(previousDueDate, LENDING_PERIOD);
			numberOfTimesRenewed += 1;
		}

		return renewed;
	}

}