package com.whfrp3.ihm.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.databinding.FragmentAdventureBinding;
import com.whfrp3.ihm.listeners.PlayerEditTextWatcher;
import com.whfrp3.ihm.listeners.StanceChangeListener;
import com.whfrp3.tools.BindingContext;
import com.whfrp3.tools.PlayerContext;
import com.whfrp3.tools.constants.IPlayerConstants;
import com.whfrp3.tools.enums.PlayerInformation;
import com.whfrp3.tools.helpers.OnPlayerUpdateListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * The AdventureFragment.
 */
public class AdventureFragment extends Fragment implements OnPlayerUpdateListener {
    private DiscreteSeekBar mPlayerStance;
    private BindingContext mBindingContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        long startTime = System.currentTimeMillis();

        View mRootView = inflater.inflate(R.layout.fragment_adventure, container, false);
        mPlayerStance = (DiscreteSeekBar) mRootView.findViewById(R.id.player_stance);
        EditText playerWoundsView = (EditText) mRootView.findViewById(R.id.player_wounds);
        TextView currentStanceTextView = (TextView) mRootView.findViewById(R.id.currentStance);

        onPlayerUpdate();

        mPlayerStance.setOnProgressChangeListener(new StanceChangeListener(getActivity(), currentStanceTextView));
        playerWoundsView.addTextChangedListener(new PlayerEditTextWatcher(PlayerInformation.WOUNDS));

        PlayerContext.registerPlayerUpdateListener(this);


        //region BINDING
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mBindingContext = (BindingContext) bundle.getSerializable(IPlayerConstants.BINDING_CONTEXT_KEY);
        } else {
            mBindingContext = new BindingContext(false);
        }

        FragmentAdventureBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_adventure, container, false);
        binding.setPlayer(PlayerContext.getPlayerInstance());
//        binding.setBindingContext(mBindingContext);
        //endregion


        long difference = System.currentTimeMillis() - startTime;
        Log.d("AdventureFragment", String.format("%d = %d", startTime, difference));
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        onPlayerUpdate();
    }

    @Override
    public void onPlayerUpdate() {
        mPlayerStance.setMin(-1 * PlayerContext.getPlayerInstance().getMax_conservative());
        mPlayerStance.setMax(PlayerContext.getPlayerInstance().getMax_reckless());
    }
}
