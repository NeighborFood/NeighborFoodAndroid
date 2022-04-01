package com.epfl.neighborfood.neighborfoodandroid.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.adapters.OrderListAdapter;
import com.epfl.neighborfood.neighborfoodandroid.models.Meal;
import com.epfl.neighborfood.neighborfoodandroid.models.Order;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class OrderHistoryActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<Order> orderArrayList = new ArrayList<>();
    private OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        int[] imageId = {R.drawable.poulet, R.drawable.couscous, R.drawable.paella,
                R.drawable.fondue, R.drawable.salade, R.drawable.soupe, R.drawable.tarte,R.drawable.tarte,R.drawable.tarte,R.drawable.tarte};

        String[] mealsName = {"Poulet au miel",
                "Couscous aux légumes",
                "Paella aux crevettes",
                "Fondue Moitié-Moitié",
                "Salade légère",
                "Soupe à l'oignon",
                "Tarte aux pommes",
                "Tarte aux pommes",
                "Tarte aux pommes",
                "Tarte aux pommes"};

        String[] mealsShortDes = {"Un délicieux poulet au miel",
                "Un couscous comme à la maison",
                "Une paella traditionnelle",
                "Une bonne fondue",
                "Une salade à la tomate",
                "De la soupe à l'oignon",
                "Une bonne tarte aux pommes",
                "Une bonne tarte aux pommes",
                "Une bonne tarte aux pommes",
                "Une bonne tarte aux pommes"};

        String[] mealsLongDes = {"Vous ne pourrez pas résister à ce savoureux poulet",
                "Ce couscous me fait penser à celui que me faisait mon grand-père",
                "Recette de paella directement d'Italie !",
                "blabla fondue",
                "blabla salade",
                "blabla soupe",
                "blabla tarte",
                "blabla tarte",
                "blabla tarte",
                "blabla tarte"};
        boolean[] status = {false, false, true, true, true, true, true,true,true,true};
        String[] orderMealPrep = {"Vendor A",
                "Vendor B",
                "Vendor C",
                "Vendor D",
                "Vendor E",
                "Vendor F",
                "Vendor H",
                "Vendor H",
                "Vendor H",
                "Vendor H"
        };


        for (int i = 0; i < imageId.length; i++) {
            Date date = new GregorianCalendar(2022, Calendar.FEBRUARY, 1 + i).getTime();
            Meal meal = new Meal(mealsName[i], mealsShortDes[i], mealsLongDes[i], imageId[i]);
            orderArrayList.add(new Order(meal, date, status[i], orderMealPrep[i]));
        }

        listView = (ListView) findViewById(R.id.order_list_view);
        orderListAdapter = new OrderListAdapter(this, orderArrayList);
        listView.setAdapter(orderListAdapter);

    }

}