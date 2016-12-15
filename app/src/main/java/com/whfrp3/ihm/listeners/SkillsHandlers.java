package com.whfrp3.ihm.listeners;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.whfrp3.R;
import com.whfrp3.model.Skill;
import com.whfrp3.model.Specialisation;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.helpers.SpecializationHelper;

import java.util.List;

public class SkillsHandlers {
    public void openSpecializations(Skill skill) {
        List<Specialisation> specialisations = SpecializationHelper.getInstance().getSpecialisationsBySkillId(skill.getId());

        String[] names = SpecializationHelper.getInstance().getSpecializationsName(specialisations).toArray(new String[]{});

        AlertDialog.Builder builder = new AlertDialog.Builder(WHFRP3Application.getActivity());
        builder.setTitle(skill.getName());
        builder.setItems(names, null);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
