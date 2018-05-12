package library;

/**
 * Implementation of SearchCondition that matches items based on the occurrence
 * of a keyword in the title.
 * 
 * @author Carly Hafner
 * 
 */
public class TitleKeywordSearch implements SearchCondition
{

	/**
	 * The keyword to search for
	 */
	private final String keyword;
	
	/**
	 * Constructs a TitleKeywordSearch to search for titles with the given
	 * keyword.
	 * 
	 * @param keyword
	 *            the keyword to search for
	 */
	public TitleKeywordSearch(String keyword)
	{
		this.keyword = keyword;
	}

	@Override public boolean matches(LibraryItem item)
	{
		boolean matches = false;
		if(item.getTitle().toLowerCase().contains(keyword.toLowerCase()))
		{
			matches = true;
		}
		return matches;
	}

}
