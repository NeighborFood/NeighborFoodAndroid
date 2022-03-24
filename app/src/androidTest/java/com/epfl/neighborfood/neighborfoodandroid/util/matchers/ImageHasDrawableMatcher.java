package com.epfl.neighborfood.neighborfoodandroid.util.matchers;

import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;

public class ImageHasDrawableMatcher {
    public static BoundedMatcher<View,ImageView> hasDrawable(){
        return new BoundedMatcher<View, ImageView> (ImageView.class){
            @Override
            public void describeTo(Description description) {
                description.appendText("has Drawable");
            }

            @Override
            protected boolean matchesSafely(ImageView item) {
                return item != null && item.getDrawable()!=null;
            }
        };
    }



}
