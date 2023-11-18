package com.example.wmhanaasri.loginTest;

import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import com.example.wmhanaasri.Login.LoginActivity;
import com.example.wmhanaasri.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4ClassRunner.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testEmailAndPasswordFieldsArePresent() {
        Espresso.onView(ViewMatchers.withId(R.id.etEmail)).check(matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.etPassword)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testLoginButtonIsDisabledInitially() {
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).check(matches(isEnabled()));
    }

    @Test
    public void testLoginButtonIsEnabledAfterValidInput() {
        String validEmail = "test@example.com";
        String validPassword = "password123";

        // Mengisi field email dan password dengan input yang valid
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.replaceText(validEmail), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.replaceText(validPassword), ViewActions.closeSoftKeyboard());

        // Memeriksa apakah tombol login sudah diaktifkan setelah memasukkan input yang valid
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).check(matches(isEnabled()));
    }

    @Test
    public void testLoginButtonIsDisabledAfterInvalidInput() {
        String invalidEmail = "invalidemail";
        String invalidPassword = "pass"; // Password kurang dari 8 karakter

        // Mengisi field email dan password dengan input yang tidak valid
        Espresso.onView(ViewMatchers.withId(R.id.etEmail))
                .perform(ViewActions.replaceText(invalidEmail), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.etPassword))
                .perform(ViewActions.replaceText(invalidPassword), ViewActions.closeSoftKeyboard());

        // Memeriksa apakah tombol login tetap dinonaktifkan setelah memasukkan input yang tidak valid
        Espresso.onView(ViewMatchers.withId(R.id.btnLogin)).check(matches(isEnabled()));
    }
}
