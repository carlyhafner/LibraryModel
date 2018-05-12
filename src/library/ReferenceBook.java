package library;

/**
 * The ReferenceBook class represents library items that cannot be checked out
 * at all.
 * 
 * @author Carly Hafner
 */
public class ReferenceBook extends LibraryItem {

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
	public ReferenceBook(int itemID, String title, String author) {
		super(itemID, title, author);
		type = "REFERENCE";
	}

	/**
	 * Constructs a reference book. The item number is 0. The title and author are
	 * null.
	 */
	public ReferenceBook() {
		super();
		type = "REFERENCE";
	}

	/**
	 * This method always throws LibraryException.
	 */
	@Override
	public void checkOutItem(SimpleDate today) throws LibraryException {
		// Reference books may not be checked out.
		throw new LibraryException();

	}

	/**
	 * This method always throws LibraryException.
	 */
	@Override
	public int returnItem(SimpleDate today) throws LibraryException {
		// Reference books may not be checked out, and therefore may not be
		// returned.
		throw new LibraryException();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean renew() {
		// Reference books may not be renewed.
		return false;
	}
}
