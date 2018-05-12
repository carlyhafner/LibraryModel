package library;

/**
 * Abstraction of a search predicate for library items. Subtypes
 * can customize the nature of the search (e.g., exact title, 
 * title keywords, author's last name, etc.).
 */
public interface SearchCondition
{
  /**
   * Determine whether the given item matches this 
   * search conditions criteria for inclusion.
   * @param item the item to be checked
   * @return true if the item matches this condition's
   *   criteria, false otherwise
   */
  public boolean matches(LibraryItem item);
}
