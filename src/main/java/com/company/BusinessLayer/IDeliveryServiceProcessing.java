package com.company.BusinessLayer;

import java.util.List;

/**
 * The interface for processing actions in the delivery service.
 * @invariant wellFormedForOthers()
 */
public interface IDeliveryServiceProcessing {

    public boolean wellFormedForOthers ();

    /**
     * Modify a menu item
     * @pre menuItem != null && updatedMenuItem != null
     * @param updatedMenuItem the new item data
     * @param menuItem the item
     * @return whether the operation was a success
     */
    public boolean modifyMenuItem(MenuItem updatedMenuItem, MenuItem menuItem);

    /**
     * Creates a new menu item
     * @pre newMenuItem != null
     * @param newMenuItem the new item
     * @post menuItems.contains(newMenuItem)
     */
    public void createMenuItem(MenuItem newMenuItem);

    /**
     * Creates a composite menu item
     * @pre menuItems != null && !name.equals("")
     * @param menuItems the items to be combined
     * @param name name of new item
     */
    public void createCompositeItem(List<MenuItem> menuItems, String name);

    /**
     * Imports the menu items
     * @pre menuItems != null
     * @post menuItems.size() > 0
     */
    public void importMenuItems();

    /**
     * Deletes the selected menu items
     * @pre itemList.size() > 0 && itemlist != null
     * @param itemList list of items to delete
     * @return whether it was a success
     */
    public boolean deleteMenuItems(List<MenuItem> itemList);

    /**
     * Generates the time interval report
     * @pre startHour >0 && endHour > 0
     * @param startHour starting hour
     * @param endHour ending hour
     */
    public void generateTimeIntervalReport(int startHour, int endHour);

    /**
     * Generates the report for items ordered a specified number of times
     * @pre times > 0
     * @param times minimum number of times
     */
    public void generateSpecifiedTimesReport(int times);

    /**
     * Generates loyal clients report
     * @pre times > 0 && minPrice > 0
     * @param times minimum ordering times
     * @param minPrice minimal price paid
     */
    public void generateClientsReport(int times, int minPrice);

    /**
     * Generates report for a particular day
     * @pre dayOfMonth > 0 && dayOfMonth < 32
     * @param dayOfMonth the selected day
     */
    public void generateDayReport(int dayOfMonth);

    /**
     * Method for creating an order
     * @pre orderedList.size() > 0 && accounts.contain(clientID)
     * @param orderedList list of products ordered
     * @param clientID id of client making order
     * @post orders.contains(newOrder)
     */
    public void createOrder(List<MenuItem> orderedList, int clientID);

    /**
     * Method for searching an item based on a criterium
     * @pre selection >= 0 && selection <= 6 && !dataToSearch.equals("")
     * @param selection selected criterium
     * @param dataToSearch particular search data
     * @return the filtered items found
     */
    public List<MenuItem> searchByCriteria(int selection, String dataToSearch);

    /**
     * Method for registering a new client.
     * @pre !username.equals("") && !password.equals("")
     * @param username username of new client
     * @param password password of new client
     * @post accounts.contains(newClient)
     */
    public void register (String username, String password);

    /**
     * Method for logging in.
     * @pre !username.equals("") && !password.equals("")
     * @param username username of user
     * @param password password of user
     */
    public boolean login (String username, String password);
}
