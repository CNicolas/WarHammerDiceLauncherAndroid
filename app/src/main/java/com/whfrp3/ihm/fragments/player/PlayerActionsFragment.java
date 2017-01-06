package com.whfrp3.ihm.fragments.player;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.whfrp3.R;
import com.whfrp3.databinding.FragmentPlayerActionsBinding;
import com.whfrp3.ihm.listeners.ActionsHandlers;
import com.whfrp3.tools.WHFRP3Application;

/**
 * Player actions Fragment
 */
public class PlayerActionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPlayerActionsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player_actions, container, false);
        binding.setPlayer(WHFRP3Application.getPlayer());
        binding.setHandlers(new ActionsHandlers());

        SwipeLayout swipeLayout = (SwipeLayout) binding.getRoot().findViewById(R.id.swipe_layout);

        //set show mode.
        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
                Log.e("SWIPELAYOUT", "onClose");
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                Log.e("SWIPELAYOUT", "onStartOpen");
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
                Log.e("SWIPELAYOUT", "onOpen");
            }

            @Override
            public void onStartClose(SwipeLayout layout) {
                Log.e("SWIPELAYOUT", "onStartClose");
            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
                Log.e("SWIPELAYOUT", "onHandRelease");
            }
        });

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.findItem(R.id.action_add).setVisible(true);
    }
}
