package com.example.jakers.dissertation_work;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jakers on 30/11/2017.
 * Things to do:
 * Get all information from the database, update fields in the created fragment.
 */

public class homework_fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment
        return inflater.inflate(R.layout.homework_fragment, container, false);
    }
}
