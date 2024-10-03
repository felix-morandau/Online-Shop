package controller;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DatabaseInfo {
    protected static List<Client> clients = new ArrayList<>();
    protected static List<Admin> admins = new ArrayList<>();
    protected static List<Product> products = new ArrayList<>();
    protected static List<Product> cartProducts = new ArrayList<>();
    protected static Client currentClient;

    public static void addClient(Client client){
        clients.add(client);
    }

    public static void addProduct(Product product){
        products.add(product);
    }

    public static void addProductToCart(Product product){
        cartProducts.add(product);
    }

    public static void addAdmin(Admin admin){
        admins.add(admin);
    }

    public static List<Client> getClients() {
        return clients;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Admin> getAdmins() {
        return admins;
    }

    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        DatabaseInfo.currentClient = currentClient;
    }

    public static List<Product> getCartProducts() {
        return cartProducts;
    }

    public static void setCartProducts(List<Product> cartProducts) {
        DatabaseInfo.cartProducts = cartProducts;
    }

    public static Client retrieveClientByUsername(String username){
        for(Client client : clients){
            if(Objects.equals(client.getUsername(), username)){
                return client;
            }
        }
        return null;
    }

    public static Product getProductById(int id){
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
