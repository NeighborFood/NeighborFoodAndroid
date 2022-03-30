package com.epfl.neighborfood.neighborfoodandroid.models;

import com.epfl.neighborfood.neighborfoodandroid.R;

import junit.framework.TestCase;


/**
 * Allergen Enum tests
 */
public class AllergenTest extends TestCase {

    public void testGetId() {
        Allergen allergen = Allergen.GLUTEN;
        assertEquals(R.drawable.gluten, allergen.getId());
    }

    public void testGetLabel() {
        Allergen allergen = Allergen.FISH;
        assertEquals("fish", allergen.getLabel());
    }
}
