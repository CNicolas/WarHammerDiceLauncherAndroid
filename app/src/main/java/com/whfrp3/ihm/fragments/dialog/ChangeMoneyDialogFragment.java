package com.whfrp3.ihm.fragments.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

import com.whfrp3.BR;
import com.whfrp3.R;
import com.whfrp3.databinding.DialogMoneyBinding;
import com.whfrp3.ihm.fragments.player.AdventureFragment;
import com.whfrp3.model.enums.MoneyType;
import com.whfrp3.model.player.Money;
import com.whfrp3.tools.WHFRP3Application;

/**
 * Fragment of the dialog used to change the player's money.
 */
public class ChangeMoneyDialogFragment extends DialogFragment {

    /**
     * Operation code of the dialog.
     */
    private int operationCode;

    /**
     * Amount of gold coin entered by the user.
     */
    private int goldAmount;

    /**
     * Amount of silver coin entered by the user.
     */
    private int silverAmount;

    /**
     * Amount of brass coin entered by the user.
     */
    private int brassAmount;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Configure binding
        DialogMoneyBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_money, null, false);
        binding.setViewModel(this);

        int okBtnLabelId = (operationCode == AdventureFragment.ADD_MONEY) ? R.string.btn_add : R.string.btn_remove;

        builder.setView(binding.getRoot());
        builder.setTitle(okBtnLabelId);
        builder.setPositiveButton(okBtnLabelId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Money money = WHFRP3Application.getPlayer().getMoney();

                if (operationCode == AdventureFragment.ADD_MONEY) {
                    money.addMoney(goldAmount, MoneyType.GOLD);
                    money.addMoney(silverAmount, MoneyType.SILVER);
                    money.addMoney(brassAmount, MoneyType.BRASS);
                } else if (operationCode == AdventureFragment.REMOVE_MONEY) {
                    try {
                        money.removeMoney(goldAmount, MoneyType.GOLD);
                        money.removeMoney(silverAmount, MoneyType.SILVER);
                        money.removeMoney(brassAmount, MoneyType.BRASS);
                    } catch (IllegalArgumentException e) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle(R.string.error_title);
                        builder.setMessage(e.getMessage());
                        builder.setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Nothing to do...
                            }
                        });
                        builder.create().show();
                    }
                }

                WHFRP3Application.getPlayer().notifyPropertyChanged(BR.money);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ChangeMoneyDialogFragment.this.getDialog().cancel();
            }
        });


        return builder.create();
    }

    //region Get & Set

    public int getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(int operationCode) {
        this.operationCode = operationCode;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public int getSilverAmount() {
        return silverAmount;
    }

    public void setSilverAmount(int silverAmount) {
        this.silverAmount = silverAmount;
    }

    public int getBrassAmount() {
        return brassAmount;
    }

    public void setBrassAmount(int brassAmount) {
        this.brassAmount = brassAmount;
    }

    //endregion
}
