package com.epfl.neighborfood.neighborfoodandroid.repositories;

import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.Is.is;



import com.epfl.neighborfood.neighborfoodandroid.database.DatabaseFactory;
import com.epfl.neighborfood.neighborfoodandroid.database.dummy.DummyDatabase;
import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.google.android.gms.tasks.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;


public class UserRepositoryTest {
    UserRepository repo = new UserRepository();
    @Before
    public void setUp() throws Exception {
        DatabaseFactory.setDependency(DummyDatabase.getInstance());
    }

    @After
    public void cleanup() {
        DummyDatabase.getInstance().reset();

    }
    @Test
    public void getUserByIdfailsWithNull(){
        Task res = repo.getUserById(null);
        assertThat(res.getException().getClass(),is(IllegalArgumentException.class));
    }
    @Test
    public void getUserByIdWorks(){
        String dummyPresentId = "1";
        Task<User> res = repo.getUserById(dummyPresentId);
        assertThat(res.isSuccessful(),is(true));
        assertThat(res.getResult().getId(), is(dummyPresentId));
    }

    @Test
    public void getUserByIdTaskFailsWithFakeId(){
        String dummyFakeId = "100";
        Task<User> res = repo.getUserById(dummyFakeId);
        assertThat(res.getException().getClass(), is(NoSuchElementException.class));
    }
    @Test
    public void updateUserWorks(){
        String dummyPresentId = "1";
        User old = repo.getUserById(dummyPresentId).getResult();
        User fakeUser = new User(old.getId(),"a","b","c");
        Task<Void> res = repo.updateUser(fakeUser);
        assertThat(res.isSuccessful(), is(true));
        assertThat(repo.getUserById(dummyPresentId).getResult().toString(), is(fakeUser.toString()));
    }
    @Test
    public void updateUserIdfailsWithNull(){
        Task<Void> res = repo.updateUser(null);
        assertThat(res.getException().getClass(),is(IllegalArgumentException.class));
    }

    @Test
    public void deleteWorks(){
        String dummyPresentId = "1";
        repo.deleteUser(dummyPresentId);
        assertThat(repo.getUserById(dummyPresentId).isSuccessful(),is(true));
    }

    @Test
    public void deleteFailsWithNull(){
        Task<Void> res = repo.deleteUser("1");
        assertThat(res.getException().getClass(),is(IllegalArgumentException.class));
    }


}
