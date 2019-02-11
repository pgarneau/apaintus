package apaintus.models;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TestApplicationPreferences {
    @Test
    public void testApplicationPreferencesCreate(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        ApplicationPreferences test2 = ApplicationPreferences.getInstance();
        assertEquals("ApplicationPreferences is a singleton, both should be the same",test2,test);
    }

    @Test
    public void testApplicationPreferencesSave(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();

        for(Preference pref : Preference.values()){
            test.setPreference(pref,pref.toString());
        }
        test.savePreferences();
    }

    @Test
    public void testApplicationPreferencesLoad(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();
        test.loadPreferences();

        for(Preference pref : Preference.values()){
            System.out.println(pref.toString());
            assertNotNull(test.getPreference(pref));
        }
    }

    @Test
    public void testApplicationPreferencesSetPreferences(){
        ApplicationPreferences applicationPreferencesTest = ApplicationPreferences.getInstance();

        for(Preference pref : Preference.values()){
            applicationPreferencesTest.setPreference(pref,pref.toString());
        }

        String wrongWidthValue = "asdkfh";
        String wrongSavePathValue = "9292";
        String wrongLoadPathValue = "9292";

        String goodWidthValue = "2.0";
        String goodSavePathValue = "";
        String goodLoadPathValue = "9292";

        applicationPreferencesTest.setPreference(Preference.WIDTH,wrongWidthValue);
        assertEquals("Accepted alphabetic values for a numeric-only value",
                "2.0",
                applicationPreferencesTest.getPreference(Preference.WIDTH));

        applicationPreferencesTest.setPreference(Preference.SAVE_PATH,wrongSavePathValue);
        assertEquals("Acceptd a value type other than alphabetic-only",
                goodSavePathValue,
                applicationPreferencesTest.getPreference(Preference.SAVE_PATH));

        applicationPreferencesTest.setPreference(Preference.LOAD_PATH,wrongLoadPathValue);
        assertEquals("Acceptd a value type other than alphabetic-only",
                goodLoadPathValue,
                applicationPreferencesTest.getPreference(Preference.LOAD_PATH));

        //Testing good values.
        applicationPreferencesTest.setPreference(Preference.WIDTH,goodWidthValue);
        assertEquals("did not set the value correctly after a first set",
                "2.0",
                applicationPreferencesTest.getPreference(Preference.WIDTH));
    }

    @Test
    public void testApplicationPreferencesGetPreference(){
        ApplicationPreferences test = ApplicationPreferences.getInstance();

        //Testing on a random pref that does not exist...
        Mockito.when(test.getPreference(Matchers.any(Preference.class)));

        //Testing on prefs that exists...
        String widthValue = test.getPreference(Preference.WIDTH);
        assertNotNull(widthValue);

    }
}
