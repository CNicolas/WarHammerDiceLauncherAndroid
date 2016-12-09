package com.whfrp3.ihm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.TalentsListAdapter;
import com.whfrp3.model.enums.CooldownType;
import com.whfrp3.model.enums.TalentType;
import com.whfrp3.model.player.Talent;
import com.whfrp3.tools.WHFRP3Application;
import com.whfrp3.tools.constants.ITalentsConstants;

import java.util.ArrayList;
import java.util.List;


public class TalentsActivity extends AppCompatActivity {
    private List<Talent> mTalents;
    private TalentType mTalentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talents);

        if (getIntent().getExtras() != null) {
            mTalentType = (TalentType) getIntent().getExtras().getSerializable(ITalentsConstants.TALENT_TYPE_BUNDLE_TAG);
            mTalents = getTalentsByType(createSampleTalentsList(), mTalentType);
        } else {
            mTalentType = TalentType.CAREER;
            mTalents = createSampleTalentsList();
        }

        TalentsListAdapter adapter = new TalentsListAdapter(getLayoutInflater(), mTalents);
        ListView talentsList = (ListView) findViewById(R.id.talents_list);
        talentsList.setAdapter(adapter);

        if (mTalents.isEmpty()) {
            talentsList.setVisibility(View.GONE);
            findViewById(R.id.no_talent_found).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.no_talent_found).setVisibility(View.GONE);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(mTalentType.getLabelId()));

        WHFRP3Application.setActivity(this);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        super.finish();
    }

    //region Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        }
        return true;
    }
    //endregion

    private List<Talent> createSampleTalentsList() {
        List<Talent> res = new ArrayList<>();

        Talent test1 = new Talent();
        test1.setName("Talent 1");
        test1.setDescription("Vous pouvez apprendre et employer des Sorts de Magie Noire et des Sorts du Chaos. Lorsque vous focalisez l'énergie ou que vous lancez un sort, vous bénéficiez d'un {EXPERTISE_DICE} à l'occasion de votre test. Si vous générez un ou plusieurs {EXERTION_FACE} ou {DELAY_FACE} à l'occasion de ces tests, ajoutez {CHAOS_FACE} aux résultats.\n" +
                "À l'occasion de vos tests de Focalisation et l'Art de la magie, {BANE_DICE} {BANE_DICE} Ajoutez {CHAOS_FACE} aux résultats.\n");
        test1.setType(TalentType.REPUTATION);
        test1.setCooldown(CooldownType.NO_COOLDOWN);
        res.add(test1);

        Talent test2 = new Talent();
        test2.setName("Shallya, Déesse de la Guérison et de la Compassion");
        test2.setDescription("Vous pouvez apprendre et employer des Sorts de Magie Noire et des Sorts du Chaos. Lorsque vous focalisez l'énergie ou que vous lancez un sort, vous bénéficiez d'un {EXPERTISE_DICE} à l'occasion de votre test. Si vous générez un ou plusieurs {EXERTION_FACE} ou {DELAY_FACE} à l'occasion de ces tests, ajoutez {CHAOS_FACE} aux résultats.\n" +
                "À l'occasion de vos tests de Focalisation et l'Art de la magie, {BANE_DICE} {BANE_DICE} Ajoutez {CHAOS_FACE} aux résultats.");
        test2.setType(TalentType.AFFINITY);
        test2.setCooldown(CooldownType.NO_COOLDOWN);
        res.add(test2);

        Talent test3 = new Talent();
        test3.setName("Talent 4");
        test3.setDescription("UneDescription !!! Ext^remement longue et tout\n c'est ouf nanpeche");
        test3.setType(TalentType.TACTICS);
        test3.setCooldown(CooldownType.NO_COOLDOWN);
        res.add(test3);

        Talent test4 = new Talent();
        test4.setName("Talent 4");
        test4.setDescription("Vous bénéficiez d'un {FORTUNE_DICE} à l'occasion de vos tests de Charisme, d'Éducation et de Folklore lorsque ces tests concernent des lieux exotiques ou les individus qui en proviennent. Épuiser cette carte vous permet d'ignorer un malus imposé à vos actions Sociales si ce malus est dû à des différences d'ordre culturel.");
        test4.setType(TalentType.AFFINITY);
        test4.setCooldown(CooldownType.SESSION);
        res.add(test4);

        Talent test5 = new Talent();
        test5.setName("Talent 4");
        test5.setDescription("Court");
        test5.setType(TalentType.AFFINITY);
        test5.setCooldown(CooldownType.TALENT);
        res.add(test5);

        Talent test6 = new Talent();
        test6.setName("Talent 6");
        test6.setDescription("Vous bénéficiez d'un {FORTUNE_DICE} à l'occasion de vos tests de Charisme, d'Éducation et de Folklore lorsque ces tests concernent des lieux exotiques ou les individus qui en proviennent. Épuiser cette carte vous permet d'ignorer un malus imposé à vos actions Sociales si ce malus est dû à des différences d'ordre culturel.");
        test6.setType(TalentType.AFFINITY);
        test6.setCooldown(CooldownType.SESSION);
        res.add(test6);

        Talent test7 = new Talent();
        test7.setName("Talent 7");
        test7.setDescription("Court");
        test7.setType(TalentType.AFFINITY);
        test7.setCooldown(CooldownType.TALENT);
        res.add(test7);

        return res;
    }

    private List<Talent> getTalentsByType(List<Talent> talents, TalentType talentType) {
        List<Talent> res = new ArrayList<>();
        for (Talent talent : talents) {
            if (talent.getType() == talentType) {
                res.add(talent);
            }
        }
        return res;
    }
}
