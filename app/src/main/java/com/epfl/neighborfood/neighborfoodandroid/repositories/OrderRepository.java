package com.epfl.neighborfood.neighborfoodandroid.repositories;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.DocumentSnapshot;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;
import com.epfl.neighborfood.neighborfoodandroid.models.OrderStatus;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final static String ordersDataCollectionPath = "Orders";
    private final static String buyerIdAttributeName = "buyerId";
    private final static String vendorIdAttributeName = "vendorId";

    public OrderRepository() {
    }

    /*
     * fetches an order using its id.
     * @param orderId
     * @return task that fails if the database is unreachable
     */
    public Task<Order> getOrderById(String id) {
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
     * fetches all the orders made by a certain buyer.
     * @return task that fails if the database is unreachable
     */
    public Task<List<Order>> getAllOrdersByBuyerId(String buyerId) {
        if (buyerId == null) {
            return Tasks.forException(new IllegalArgumentException("The buyer ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetchAllMatchingAttributeValue(ordersDataCollectionPath, buyerIdAttributeName, buyerId).continueWith(t -> {
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
     * fetches all the orders that are still unassigned.
     * @return task that fails if the database is unreachable
     */
    public Task<List<Order>> getAllOrdersMatchingStatus(OrderStatus status) {
        if (status == null) {
            return Tasks.forException(new IllegalArgumentException("The buyer ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetchAllMatchingAttributeValue(ordersDataCollectionPath, "orderStatus", status).continueWith(t -> {
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
     * fetches all the orders posted by a vendor.
     * @return task that fails if the database is unreachable
     */
    public Task<List<Order>> getAllOrdersByVendorId(String vendorId) {
        if (vendorId == null) {
            return Tasks.forException(new IllegalArgumentException("The vendor ID cannot be null"));
        }
        return DatabaseFactory.getDependency().fetchAllMatchingAttributeValue(ordersDataCollectionPath, vendorIdAttributeName, vendorId).continueWith(t -> {
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
     * @return task containing orderId that fails if the database is unreachable
     */
    public Task<String> makeOrder(Order order) {
        if (order == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot make a null order"));
        }
        return DatabaseFactory.getDependency().add(ordersDataCollectionPath, order)
                .continueWith(task -> {
                    order.setOrderId(task.getResult());
                    DatabaseFactory.getDependency().
                            set(ordersDataCollectionPath, task.getResult(), order);
                    return task.getResult();
                });
    }

    /*
     * Updates an existing order with new values
     * @param order Order to be updated
     * @return task containing orderId that fails if the database is unreachable
     */
    public Task<Void> updateOrder(Order order) {
        if (order == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot make a null order"));
        }
        return DatabaseFactory.getDependency().set(ordersDataCollectionPath, order.getOrderId(), order).continueWith(task -> null);
    }
}
