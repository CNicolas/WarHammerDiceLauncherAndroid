package com.whfrp3.ihm.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.TalentTypesListAdapter;
import com.whfrp3.ihm.fragments.dialog.TalentSearchDialogFragment;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.tools.BindingUtils;

import java.util.List;

public class TalentTypesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_talents_type, container, false);

        List<TalentType> talentTypesList = TalentType.getDisplayableTypes();

        TalentTypesListAdapter adapter = new TalentTypesListAdapter(inflater, talentTypesList);
        ListView talentsList = (ListView) rootView.findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);

        FloatingActionButton searchButton = (FloatingActionButton) rootView.findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTalentSearchDialog();
            }
        });

        setHasOptionsMenu(true);

        return rootView;
    }

    //region Menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_search) {
            openTalentSearchDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(BindingUtils.string(R.string.page_talents));
    }

    private void openTalentSearchDialog() {
        TalentSearchDialogFragment dialog = new TalentSearchDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "TalentSearchDialogFragment");
    }
}
