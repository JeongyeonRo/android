package com.rgo0517.app0624;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //화면가져오기
        addPreferencesFromResource(R.xml.setting_preference);

        ListPreference keyword_player_list =
                (ListPreference)findPreference("keyword_player_list");
    }
}
