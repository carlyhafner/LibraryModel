package library;

import java.util.ArrayList;

/**
 * A Library consists of a list of items and a list of patrons
 * who can check out items.
 */
public class Library
{
  /**
   * The items in this library.
   */
  private ArrayList<LibraryItem> items;
  
  /**
   * The list of patrons of this library.
   */
  private ArrayList<LibraryPatron> patrons;
  
  /**
   * Constructs a Library with no books and no patrons.
   */
  public Library()
  {
    items = new ArrayList<LibraryItem>();
    patrons = new ArrayList<LibraryPatron>();
  }
  
  /**
   * Adds an item to this library's list of items.
   * @param item the item to be added
   */
  public void addItem(LibraryItem item)
  {
    items.add(item);
  }
  
  /**
   * Adds a patron to this library's list of patrons.
   * @param patron the patron to be added
   */
  public void addPatron(LibraryPatron patron)
  {
    patrons.add(patron);
  }
  
  /**
   * Returns the patron with the given name
   * @param name the name of the patron to search for
   * @return the patron 
   */
  public LibraryPatron findUser(String name)
  {
    for (LibraryPatron p : patrons)
    {
      if (name.equals(p.getName()))
      {
        return p;
      }
    }
    return null;
  }
  
  /**
   * Returns the item with the given ID number
   * @param idNum the ID number of the item to search for
   * @return the item 
   */
  public LibraryItem findItem(int idNum)
  {
    for (LibraryItem item : items)
    {
      if (item.getItemID() == idNum)
      {
        return item;
      }
    }
    return null;
  }
  
  /**
   * Search the library's collection of for items satisfying the
   * given SearchCondition.
   * @param condition the SearchCondition
   * @return list of items satisfying the condition
   */
  public ArrayList<LibraryItem> search(SearchCondition condition)
  {
    ArrayList<LibraryItem> results = new ArrayList<LibraryItem>();
    for (LibraryItem item : items)
    {
      if (condition.matches(item))
      {
        results.add(item);
      }
    }
    return results;   
  }
  
  /**
   * Returns a string listing all the patrons of the library.
   * @return list of the names of library patrons as a multi-line string
   */
  public String listLibraryPatrons() {
	  String patronString = "";
	  for(LibraryPatron p : patrons) {
		  patronString = patronString + p.getName() + "\n";
	  }
	  return patronString;
  }
  /**
   * Returns a string listing all the items in the library.
   * @return list of the library's items as a multi-line string
   */
  public String listLibraryItems() {
	  String itemString = "";
	  for(LibraryItem i : items) {
		  itemString = itemString + i.toString() + "\n";
	  }
	  return itemString;
  }
  
}
