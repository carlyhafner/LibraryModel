package library;

/**
 * The DVD class represents library items that can be checked out under the
 * following policy: the lending period is 7 days, the item cannot be renewed,
 * and the fine is 75 cents per day.
 * 
 * @author Carly Hafner
 */
public class DVD extends LibraryItem {
	/**
	 * The amount of time a book may be checked out
	 */
	public static final int LENDING_PERIOD = 7;
	/**
	 * The fine per day in cents
	 */
	public static final int FINE_PER_DAY = 75;
	/**
	 * The type of library item
	 */
	private final String type;

	/**
	 * Constructs a DVD with the given title and author (the "author" may be the
	 * studio or director).
	 * 
	 * @param title
	 *            title of the DVD
	 * @param author
	 *            the studio or director of the DVD
	 */
	public DVD(int itemID, String title, String author) {
		super(itemID, title, author);
		type = "DVD";
	}

	/**
	 * Constructs a DVD. The item number is 0. The title and author are null.
	 */
	public DVD() {
		super();
		type = "DVD";
	}

	@Override
	public void checkOutItem(SimpleDate today) throws LibraryException {
		if (status == true) {
			throw new LibraryException();
		} else {
			status = true;
			dueDate = new SimpleDate(today, LENDING_PERIOD);
		}

	}

	@Override
	public int returnItem(SimpleDate today) throws LibraryException {
		int fine = 0;
		if (status == false) {
			throw new LibraryException();
		} else if (dueDate.isBefore(today)) {
			int daysOverdue = dueDate.daysUntil(today);
			fine = FINE_PER_DAY * daysOverdue;
		}
		status = false;
		dueDate = null;
		return fine;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean renew() {
		// DVDs may not be renewed.
		return false;
	}

}
