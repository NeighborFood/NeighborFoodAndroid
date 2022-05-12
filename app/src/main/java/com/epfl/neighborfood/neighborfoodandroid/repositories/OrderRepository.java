package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final static String ordersDataCollectionPath = "Orders";
    private final static String buyerIdAttributeName = "buyerId";
    public OrderRepository() {
    }
    /*
     * fetches an order using its id.
     * @param orderId
     * @return task that fails if the database is unreachable
     */
    public Task<Order> getOrderById(String id){
        if (id == null) {
            return Tasks.forException(new IllegalArgumentException("The order ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetch(ordersDataCollectionPath, id).continueWith(task -> {
            if (task.isSuccessful()) {
                return task.getResult().toModel(Order.class);
            }
            return null;
        });
    }
    /*
     * fetches all the orders that the buyer had.
     * @param buyerId
     * @return task that fails if the database is unreachable
     */
    public Task<List<Order>> getAllOrdersByBuyerId(String buyerId) {
        if (buyerId == null) {
            return Tasks.forException(new IllegalArgumentException("The buyer ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetchAllMatchingAttributeValue(ordersDataCollectionPath,buyerIdAttributeName,buyerId).continueWith(t -> {
            ArrayList<Order> res = new ArrayList<>();
            if (t.isSuccessful()) {
                for (DocumentSnapshot m : t.getResult().getDocuments()) {
                    res.add(m.toModel(Order.class));
                }
            }
            return res;
        });
    }
    /*
     * posts an order on the database
     * @param order Order to be posted
     * @return task that fails if the database is unreachable
     */
    public Task<Void> makeOrder(Order order){
        if (order == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot make a null order"));
        }
        return DatabaseFactory.getDependency().add(ordersDataCollectionPath,order)
                .continueWithTask(task ->
                        DatabaseFactory.getDependency().
                                set(ordersDataCollectionPath,task.getResult(),order.copyWithId(task.getResult())));
    }
}
