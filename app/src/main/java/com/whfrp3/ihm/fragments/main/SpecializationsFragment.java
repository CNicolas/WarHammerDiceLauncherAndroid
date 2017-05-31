package com.whfrp3.ihm.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.SpecializationsListAdapter;
import com.whfrp3.ihm.fragments.dialog.SpecializationSearchDialogFragment;
import com.whfrp3.model.Specialization;
import com.whfrp3.tools.BindingUtils;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.List;

public class SpecializationsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_specializations, container, false);

        List<Specialization> specializations = SpecializationHelper.getInstance().getSpecializations();

        ListView specializationsListView = (ListView) rootView.findViewById(R.id.specializations_list);
        specializationsListView.setAdapter(new SpecializationsListAdapter(inflater, specializations));

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
        getActivity().setTitle(BindingUtils.string(R.string.page_specializations));
    }

    private void openTalentSearchDialog() {
        SpecializationSearchDialogFragment dialog = new SpecializationSearchDialogFragment();
        dialog.show(getActivity().getSupportFragmentManager(), "SpecializationSearchDialogFragment");
    }
}
