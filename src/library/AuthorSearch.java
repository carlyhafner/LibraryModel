
package library;

/**
 * Implementation of SearchCondition that matches items
 * based on the author.
 */
public class AuthorSearch implements SearchCondition
{
  /**
   * The name we are searching for.
   */
  private final String author;
  
  /**
   * Constructs an AuthorSearch for the given name.
   * @param author the name to search for
   */
  public AuthorSearch(String author)
  {
    this.author = author;
  }
  
  @Override
  public boolean matches(LibraryItem item)
  {
    return author.equalsIgnoreCase(item.getAuthor());
  }

}
