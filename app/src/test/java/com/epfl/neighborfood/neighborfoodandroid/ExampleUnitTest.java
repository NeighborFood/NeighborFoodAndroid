package com.epfl.neighborfood.neighborfoodandroid;

import org.junit.Test;

import static org.junit.Assert.*;

import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        /*
        DummyDatabase db = new DummyDatabase();
        try {
            db.persist();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("map.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Map anotherMap = (Map) ois.readObject();
            ois.close();
            System.out.println(((User)anotherMap.get("1")).getEmail());
            //System.out.println(anotherMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        /*
        Map map = new HashMap();
        map.put("1",new Integer(1));
        map.put("2",new Integer(2));
        map.put("3",new Integer(3));
        try {
            FileOutputStream fos = new FileOutputStream("map.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

         */
    }
}