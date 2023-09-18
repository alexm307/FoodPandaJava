package com.company.BusinessLayer;
import com.company.DataLayer.BusinessFileWriter;
import com.company.DataLayer.Serializator;
import com.company.PresentationLayer.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The DeliveryService class, implementing the interface which holds all client or admin operations.
 * @invariant isWellFormed
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    private List<MenuItem> menuItems;
    private ArrayList<Order> orders;
    private HashMap<Order,ArrayList<MenuItem>> orderArrayListHashMap;
    private ArrayList<Account> accounts;
    private LoginGUI loginGUI;
    private ClientGUI clientGUI;
    private AdministratorGUI administratorGUI;
    private EmployeeGUI employeeGUI;

    public DeliveryService () {
        menuItems = new ArrayList<MenuItem>();
        orders = new ArrayList<Order>();
        orderArrayListHashMap = new HashMap<>();
        accounts = new ArrayList<Account>();
    }

    /**
     * Method that starts up the entire system.
     * @pre deliveryService != null
     * @pre accounts != null
     * @post accounts.size() > 0
     * @post employeeGUI != null
     * @post loginGUI != null
     * @post clientGUI != null
     * @post adminGUI != null
     *
     */
    public void start() {
        assert (accounts != null);
        Account admin = new Account("admin", "1"); admin.setID(-1);
        Account user = new Account("user", "1"); user.setID(1);
        Account employee = new Account("employee", "1"); employee.setID(0);
        accounts.add(employee);
        accounts.add(user);
        accounts.add(admin);
        for (Account account: accounts)
            System.out.println(account.getUsername() + " " + account.getPassword());
        for (Order order: orders)
            System.out.println(order);
        this.loginGUI = new LoginGUI(this);
        loginGUI.setVisible(true);
        this.employeeGUI = new EmployeeGUI(this);
        this.clientGUI = new ClientGUI(this);
        this.administratorGUI = new AdministratorGUI(this);
        this.addObserver(employeeGUI);
    }

    /**
     * @pre orders != null
     * @post @nochange
     * @return all existent orders
     */
    public ArrayList<Order> getOrders() {
        assert wellFormedForOthers();
        return this.orders;
    }

    /**
     * @pre menuItems != null
     * @post @nochange
     * @return all existent items
     */
    public List<MenuItem> getFullMenu() {
        assert wellFormedForOthers();
        return this.menuItems;
    }

    /**
     * Goes back to login screen
     * @post loginGUI.isVisible == true
     */
    public void goBack() {
        assert wellFormedForOthers();
        this.loginGUI.setVisible(true);
        this.employeeGUI.setVisible(false);
        this.administratorGUI.setVisible(false);
        this.clientGUI.setVisible(false);
        assert wellFormedForOthers();
    }

    /**
     * @pre orderArrayListHashMap != null
     * @post @nochange
     * @return all existent hashmap items
     */
    public HashMap<Order, ArrayList<MenuItem>> getHashMap () {
        assert wellFormedForOthers();
        return this.orderArrayListHashMap;
    }

    /**
     * @pre accounts != null
     * @post @nochange
     * @return all existent accounts
     */
    public ArrayList<Account> getAccounts() {
        assert wellFormedForOthers();
        return accounts;
    }
    @Override
    public boolean modifyMenuItem(MenuItem updatedMenuItem, MenuItem menuItem) {
        assert wellFormedForOthers();
                if (updatedMenuItem.getCalories() >= 0)
                    menuItem.setCalories(updatedMenuItem.getCalories());
                if (updatedMenuItem.getFat() >= 0)
                    menuItem.setFat(updatedMenuItem.getFat());
                if (updatedMenuItem.getPrice() >= 0)
                    menuItem.setPrice(updatedMenuItem.getPrice());
                if (updatedMenuItem.getProtein() >= 0)
                    menuItem.setProtein(updatedMenuItem.getProtein());
                if (updatedMenuItem.getRating() >=0 && updatedMenuItem.getRating() <= 5)
                    menuItem.setRating(updatedMenuItem.getRating());
                if (updatedMenuItem.getSodium() >= 0)
                    menuItem.setSodium(updatedMenuItem.getSodium());
    return true;
    }
    @Override
    public void createMenuItem(MenuItem newMenuItem) {
        assert wellFormedForOthers();
        menuItems.add(newMenuItem);
    }
    @Override
        public void importMenuItems() {
        assert wellFormedForOthers();
            List<MenuItem> inputList = new ArrayList<>();

            try{
                File inputF = new File("products.csv");
                InputStream inputFS = new FileInputStream(inputF);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
                inputList = br.lines().skip(1).map((item1) -> {
                    String []fields = item1.split(",");
                    BaseProduct baseProduct = new BaseProduct(fields[0], Double.parseDouble(fields[1]), Integer.parseInt(fields[2]),
                            Integer.parseInt(fields[3]), Integer.parseInt(fields[4]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                    return  baseProduct;
                }).collect(Collectors.toList());
                br.close();
            } catch (IOException e) {
                System.out.println("An error ocurred when importing menuItems!");
            }

            this.menuItems = inputList;
            this.eliminateDuplicates();
            assert wellFormedForOthers();
        }
    @Override
    public boolean deleteMenuItems(List<MenuItem> itemsToBeDeleted) {
        assert wellFormedForOthers();
        for (MenuItem menuItem : itemsToBeDeleted) {
            if (!menuItems.remove(menuItem))
                return false;
        }
        return true;
    }
    @Override
    public void createCompositeItem(List<MenuItem> menuItems, String name) {
        assert wellFormedForOthers();
        CompositeProduct compositeProduct = new CompositeProduct(name, menuItems);
        compositeProduct.computeProperties();
        this.menuItems.add(compositeProduct);
        assert wellFormedForOthers();
    }

    /**
     * Well formed method
     * @return whether the class is well formed
     */
    public boolean wellFormedForOthers() {
        return this.orders != null && this.menuItems != null && this.clientGUI != null && this.loginGUI != null
                && this.administratorGUI != null && this.employeeGUI != null && this.accounts != null;
    }
    @Override
    public void createOrder(List<MenuItem> orderedList, int clientID) {
        assert wellFormedForOthers();
        Order order = new Order();
        ArrayList<MenuItem> orderItems = new ArrayList<>(orderedList);
        order.setOrderID(orders.size() + 1);
        order.setClientID(clientID);
        order.setOrderDate(LocalDateTime.now());
        System.out.println("New order created at hour " + order.getOrderDate().getHour() + " at day of month " + order.getOrderDate().getDayOfMonth());
        this.orders.add(order);
        setChanged();
        notifyObservers(order);
        orderArrayListHashMap.put(order, orderItems);
        assert wellFormedForOthers();
    }
    //@Override
    //public void notifyObservers (Object arg) {
    //    setChanged();
    //    super.notifyObservers(arg);
    //}
    @Override
    public List<MenuItem> searchByCriteria(int selection, String dataToSearch) {
        assert wellFormedForOthers();
        List<MenuItem> finalList = null;
        System.out.println(dataToSearch + "   " + selection);
        switch (selection) {
            case 0:
                finalList = this.menuItems.stream().filter(item -> item.getName().contains(dataToSearch)).collect(Collectors.toList());
                break;
            case 1:
                finalList = this.menuItems.stream().filter(item -> item.getRating() == Double.parseDouble(dataToSearch)).collect(Collectors.toList());
                break;
            case 2:
                finalList = this.menuItems.stream().filter(item -> item.getCalories() == Integer.parseInt(dataToSearch)).collect(Collectors.toList());
                break;
            case 3:
                finalList = this.menuItems.stream().filter(item -> item.getProtein() == Integer.parseInt(dataToSearch)).collect(Collectors.toList());
                break;
            case 4:
                finalList = this.menuItems.stream().filter(item -> item.getFat() == Integer.parseInt(dataToSearch)).collect(Collectors.toList());
                break;
            case 5:
                finalList = this.menuItems.stream().filter(item -> item.getSodium() == Integer.parseInt(dataToSearch)).collect(Collectors.toList());
                break;
            case 6:
                finalList = this.menuItems.stream().filter(item -> item.getPrice() == Integer.parseInt(dataToSearch)).collect(Collectors.toList());
                break;
            default:
                return null;
        }
        return finalList;
    }
    @Override
    public void register(String username, String password) {
        assert wellFormedForOthers();
        Account newAccount = new Account(username, password);
        newAccount.setID(accounts.size() + 1);
        accounts.add(newAccount);
        System.out.println(accounts.get(accounts.size() - 1).getUsername() + " just made a new account!");
        assert wellFormedForOthers();
    }
    @Override
    public boolean login(String username, String password) {
        assert wellFormedForOthers();
        for (Account account: this.accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                loginGUI.setVisible(false);
                if (account.getID() == 0)
                    this.employeeGUI.setVisible(true);
                else if (account.getID() > 0) {
                    this.clientGUI.setClientAccount(account);
                    this.clientGUI.setVisible(true);
                }
                else
                    if (account.getID() < 0)
                        this.administratorGUI.setVisible(true);
                return true;
            }
            else {
                System.out.println(username + " " + password);
            }
        }
        return false;
    }
    @Override
    public void generateTimeIntervalReport(int startHour, int endHour) {
        assert wellFormedForOthers();
        List<Order> filteredOrders = new ArrayList<>(this.orders);
        for (Order order : orders)
            System.out.println(order);
        BusinessFileWriter.generateFirst(filteredOrders.stream().filter(item -> item.getOrderDate().getHour() > startHour && item.getOrderDate().getHour() < endHour)
                .collect(Collectors.toList()));
        assert wellFormedForOthers();
    }
    @Override
    public void generateSpecifiedTimesReport(int times) {
        assert wellFormedForOthers();
        List <MenuItem> allItems = new ArrayList<>();
        orders.forEach(o -> allItems.addAll(orderArrayListHashMap.get(o)));       //a list of all items
        BusinessFileWriter.generateSecond(allItems.stream().filter(o -> Collections.frequency(allItems, o) >= times).collect(Collectors.toList()));
        assert wellFormedForOthers();
    }

    /**
     * Calculates price of order
     * @pre order != null
     * @param order the order
     * @return the total price
     * @post @nochange
     */
    public int calculateOrderPrice(Order order) {
        assert wellFormedForOthers();
        int price = 0;
        for (MenuItem menuItem : orderArrayListHashMap.get(order))
            price += menuItem.getPrice();
        assert wellFormedForOthers();
        return price;
    }
    @Override
    public void generateClientsReport(int times, int minPrice) {
        assert wellFormedForOthers();
        List<Order> filteredOrders = orders.stream().filter(o -> calculateOrderPrice(o) > minPrice).collect(Collectors.toList());
        Map<Integer, Long> clientsAndOrders = filteredOrders.stream().collect(Collectors.groupingBy(Order::getClientID, Collectors.counting()));
        List<Account> finalClients = new ArrayList<>();
        for (Account account: accounts)
            if (clientsAndOrders.containsKey(account.getID())) {
                if (clientsAndOrders.get(account.getID()) > times)
                    finalClients.add(account);
            }
        BusinessFileWriter.generateThird(finalClients);
        assert wellFormedForOthers();
    }
    @Override
    public void generateDayReport(int dayOfMonth) {
        assert wellFormedForOthers();
        ArrayList<MenuItem> repo3MenuItems = new ArrayList<>();
        List<Order> selectedOrders = orders.stream().filter(order -> order.getOrderDate().getDayOfMonth() == dayOfMonth).collect(Collectors.toList());
        for (Order order : selectedOrders) {
            ArrayList<MenuItem> currentList = orderArrayListHashMap.get(order);
            if (currentList.size() == 0)
                System.out.println("sth wrong");
            repo3MenuItems.addAll(currentList);
        }
        Map<String, Long> finalList = repo3MenuItems.stream().collect(Collectors.groupingBy(MenuItem::getName , Collectors.counting()));
        List<String> toRepo = new ArrayList<>();
        for (MenuItem menuItem : repo3MenuItems) {
            if (finalList.containsKey(menuItem.getName()))
                System.out.println(menuItem);
                toRepo.add( new String(menuItem.getName() + " " + finalList.get(menuItem.getName())));
        }
        BusinessFileWriter.generateFourth(toRepo);
        assert wellFormedForOthers();
    }

    /**
     * Saves data
     * @post @nochange
     */
    public void save() {
        assert wellFormedForOthers();
        Serializator.serialize(this);
        assert wellFormedForOthers();
    }

    /**
     * Eliminates duplicate products
     * @pre menuItems != null
     */
    public void eliminateDuplicates () {
        assert wellFormedForOthers();
        for (int i = 1; i < this.menuItems.size(); i++)
            for (int j = 0; j < i; j++)
                if (this.menuItems.get(i).getName().equals(this.menuItems.get(j).getName())) {
                    this.menuItems.remove(j);
                    j--;
                }
        assert wellFormedForOthers();
    }
}