// Main.java (outside any package)
import com.ecommerce.Product;
import com.ecommerce.Customer;
import com.ecommerce.ShoppingCart;
import com.ecommerce.orders.Order;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Build a simple catalog
        List<Product> catalog = buildCatalog();

        // Demonstrate browsing: print catalog
        System.out.println("=== Product Catalog ===");
        for (Product p : catalog) {
            System.out.println(p);
        }
        System.out.println();

        // Create a customer (showing use of Customer class from com.ecommerce)
        Customer cust = new Customer("CUST100", "Umar Baloch");
        System.out.println("Created customer: " + cust);
        System.out.println();

        // Interactive demo (simple, keyboard-driven)
        Scanner sc = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            System.out.println("Choose an action: [1] Browse [2] Add to cart [3] View cart [4] Place order [5] Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.println("--- Catalog ---");
                        for (int i = 0; i < catalog.size(); i++) {
                            System.out.printf("%d) %s%n", i + 1, catalog.get(i));
                        }
                        break;
                    case "2":
                        System.out.print("Enter product number to add: ");
                        int pid = Integer.parseInt(sc.nextLine().trim()) - 1;
                        System.out.print("Enter quantity: ");
                        int qty = Integer.parseInt(sc.nextLine().trim());
                        if (pid < 0 || pid >= catalog.size()) {
                            System.out.println("Invalid product number.");
                        } else {
                            Product toAdd = catalog.get(pid);
                            cust.addToCart(toAdd, qty); // validation lives in Customer/ShoppingCart
                            System.out.println("Added to cart: " + toAdd.getName() + " x" + qty);
                        }
                        break;
                    case "3":
                        System.out.println("--- Cart contents ---");
                        ShoppingCart cart = cust.getCart();
                        System.out.println(cart);
                        break;
                    case "4":
                        try {
                            Order order = cust.placeOrder(); // builds Order from cart and clears cart
                            System.out.println("Order placed successfully!");
                            System.out.println(order.summary());
                        } catch (IllegalStateException ise) {
                            System.out.println("Cannot place order: " + ise.getMessage());
                        }
                        break;
                    case "5":
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException iae) {
                System.out.println("Input error: " + iae.getMessage());
            }
            System.out.println();
        }

        sc.close();
        System.out.println("Demo finished.");
    }

    private static List<Product> buildCatalog() {
        List<Product> list = new ArrayList<>();
        list.add(new Product("P001", "Wireless Mouse", 19.99));
        list.add(new Product("P002", "Bluetooth Headphones", 49.99));
        list.add(new Product("P003", "USB-C Charger", 15.50));
        list.add(new Product("P004", "HDMI Cable", 8.25));
        return list;
    }
}

