import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

class Product{
    String name;
    int quantity;
    double price;

    public Product(String name,int quantity,double price){
        this.name=name;
        this.quantity=quantity;
        this.price=price;

    }
}


public class IMS {
    private Map<String,LinkedList<Product>> inventory;
    public IMS(){
        this.inventory=new HashMap<>();
        
    }
    public void addProduct(String productName ,int quantity,double price){
        Product newProduct=new Product(productName, quantity, price);
        if(inventory.containsKey(productName)){
            inventory.get(productName).add(newProduct);
        }
        else{
            LinkedList<Product>productList=new LinkedList<>();
            productList.add(newProduct);
            inventory.put(productName,productList);
        }
        System.out.println("product added to the inventory: "+productName);
    }
    public void sellProduct(String productName,int quantity){
        if(inventory.containsKey(productName)){
            LinkedList<Product> productList= inventory.get(productName);
            if(!productList.isEmpty()){
                Product product=productList.getFirst();
                if(product.quantity>=quantity){
                    product.quantity-=quantity;
                    System.out.println(quantity+" " + productName+"(s) sold. Total cost:$"+(quantity*product.price));
                    if(product.quantity==0){
                        productList.removeFirst();
                        if(productList.isEmpty()){
                            inventory.remove(productName);
                        }
                        
                    }
                    else{
                        System.out.println("Not enought stock available for "+productName);
                    }
                }
                else{
                    System.out.println(productName+" not found int the inventory...");
                }
            }
        }

    }
    public void displayInventory() {
        System.out.println("Inventory:");
        for (Map.Entry<String, LinkedList<Product>> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Product product : entry.getValue()) {
                System.out.println("  Quantity: " + product.quantity + ", Price: $" + product.price);
            }
        }
    }
    public static void main(String[] args) {
        IMS inventorySystem = new IMS();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Product\n2. Sell Product\n3. Display Inventory\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String addProductName = scanner.next();
                    System.out.print("Enter quantity to add: ");
                    int addQuantity = scanner.nextInt();
                    System.out.print("Enter price per unit: $");
                    double addPrice = scanner.nextDouble();
                    inventorySystem.addProduct(addProductName, addQuantity, addPrice);
                    break;

                case 2:
                    System.out.print("Enter product name: ");
                    String sellProductName = scanner.next();
                    System.out.print("Enter quantity to sell: ");
                    int sellQuantity = scanner.nextInt();
                    inventorySystem.sellProduct(sellProductName, sellQuantity);
                    break;

                case 3:
                    inventorySystem.displayInventory();
                    break;

                case 4:
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    
}
