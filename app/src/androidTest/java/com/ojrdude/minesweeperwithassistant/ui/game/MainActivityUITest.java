package com.ojrdude.minesweeperwithassistant.ui.game;

import android.content.Context;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import com.ojrdude.minesweeperwithassistant.R;
import com.ojrdude.minesweeperwithassistant.espressomatchers.CustomEspressoMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * UI Tests for the MainActivity Class
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void canFlagAllCells(){
        onView(withId(R.id.flagToggleButton)).perform(click());
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int id =getIDForCoordinate(x,y);
                onView(withId(id)).perform(click());
                onView(withId(id)).check(matches(CustomEspressoMatchers.withDrawable(R.drawable.flag)));
            }
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int id =getIDForCoordinate(x,y);
                onView(withId(id)).perform(click());
                onView(withId(id)).check(matches(CustomEspressoMatchers.withDrawable(R.drawable.coveredcell)));
            }
        }

    }

    private int getIDForCoordinate(int x, int y) {
        String id =String.format(Locale.UK, "cell_%d_%d", x, y);
        Context targetContext = InstrumentationRegistry.getTargetContext();
        String packageName = targetContext.getPackageName();
        return targetContext.getResources().getIdentifier(id, "id", packageName);
    }
}
