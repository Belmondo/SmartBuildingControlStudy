package great.android.cmu.ubiapp.ui.notifications;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import great.android.cmu.ubiapp.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        // Load the Preferences from the XML file
        addPreferencesFromResource(R.xml.app_preferences);
    }

}