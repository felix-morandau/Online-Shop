package controller;

import model.Product;
import view.OrderDetails;

import java.util.List;

public class OrderDetailsController {
    private OrderDetails view;

    OrderDetailsController(){
        orderDetailsFunctionality();
    }

    private void orderDetailsFunctionality(){
        view = new OrderDetails(DatabaseInfo.getCartProducts());
        view.setVisible(true);

    }

    private void placeOrder(){

    }
}
