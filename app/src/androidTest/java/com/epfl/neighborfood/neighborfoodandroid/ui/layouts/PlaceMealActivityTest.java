package com.epfl.neighborfood.neighborfoodandroid.ui.layouts;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import android.Manifest;
import android.view.View;
import android.widget.DatePicker;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.GrantPermissionRule;

import com.epfl.neighborfood.neighborfoodandroid.R;
import com.epfl.neighborfood.neighborfoodandroid.ui.activities.PlaceMealActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PlaceMealActivityTest {
   @Rule
    public ActivityScenarioRule<PlaceMealActivity> testRule = new ActivityScenarioRule<>(PlaceMealActivity.class);

    @Rule
    public GrantPermissionRule permissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);

    private View decorView;

    @Before
    public void setUp(){
        Intents.init();
        testRule.getScenario().onActivity(new ActivityScenario.ActivityAction<PlaceMealActivity>() {
            @Override
            public void perform(PlaceMealActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }
    @After
    public void cleanup() {
        Intents.release();
    }
    /*
    @Test
    public void testCompleteMeal() {
        onView(ViewMatchers.withId(R.id.meal_name)).perform(typeText("pizza"));
        onView(withId(R.id.mealDesc)).perform(typeText("pizza italienne"));
        onView(withId(R.id.textPrice)).perform(typeText("6 chf"));
        onView(withId(R.id.DateText)).perform(typeText("17/03/2022"));
        onView(withId(R.id.TimeText)).perform(typeText("10:00"));
        onView(withId(R.id.TimeText)).check(matches(withText("10:00")));
        onView(withId(R.id.FishIcon)).perform(click());
        String expectedToast = "Allergens are: Fish";
        onView(withId(R.id.ConfirmationButton)).perform(click());
        onView(withText(expectedToast)).inRoot(withDecorView(not(decorView)));
    }

     */
    /*
    @Test
    public void testAddAndDeleteAllergen() {
        //add
        onView(withId(R.id.FishIcon)).perform(click());
        //delete
        onView(withId(R.id.FishIcon)).perform(click());

        onView(withId(R.id.CheeseIcon)).perform(click());
        onView(withId(R.id.ConfirmationButton)).perform(click());
        String expectedToast = "Allergens are: Cheese";
        onView(withText(expectedToast)).inRoot(withDecorView(not(decorView)));
    }

     */

    /*
    @Test
    public void testDateSelection() {
        //add
        onView(withId(R.id.CalendarButton)).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2022,11,21));
        onView(withId(R.id.DateText)).check(matches(withText("21/11/2022")));
    }
     */




    /*public void getImageIntent(){
        Bitmap icon = BitmapFactory.decodeResource(
                InstrumentationRegistry.getTargetContext().getResources(),
                R.mipmap.ic_launcher);
        Intent resultIntent = new Intent();
        resultIntent.putExtra("data", icon);
        Instrumentation.ActivityResult result = new Instrumentation.ActivityResult(Activity.RESULT_OK, resultIntent);
    }
    @Test
    public void checkGalleryIntent(){
        Instrumentation res = InstrumentationRegistry.getInstrumentation();
        URI resultedIntent = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+"://"+
                                        res.decoget(R.drawable.ic_launcher_background));
        onView(withId(R.id.addPictureButton)).perform(click());
        intended(hasAction(Intent.ACTION_PICK));
        intended(hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
        //inte
    }*/

}
