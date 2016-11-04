package com.whfrp3.model.player;

import android.databinding.ObservableDouble;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.whfrp3.model.IModel;
import com.whfrp3.tools.constants.IPlayerConstants;

public class PlayerV2 implements IModel, IPlayerConstants {
    public ObservableField<String> name;
    public ObservableField<String> race;
    public ObservableInt age;
    public ObservableDouble size;
    public ObservableField<String> description;
    public ObservableField<String> career;
    public ObservableInt rank;
    public ObservableInt experience;
    public ObservableInt max_experience;
    public ObservableInt wounds;
    public ObservableInt max_wounds;
    public ObservableInt corruption;
    public ObservableInt max_corruption;
    public ObservableInt reckless;
    public ObservableInt max_reckless;
    public ObservableInt conservative;
    public ObservableInt max_conservative;
    public ObservableInt money_brass;
    public ObservableInt money_silver;
    public ObservableInt money_gold;
    private int id;


    public PlayerV2() {
        name = new ObservableField<>();
        race = new ObservableField<>();
        age = new ObservableInt();
        size = new ObservableDouble();
        description = new ObservableField<>();

        career = new ObservableField<>();
        rank = new ObservableInt();
        experience = new ObservableInt();
        max_experience = new ObservableInt();
        wounds = new ObservableInt();
        max_wounds = new ObservableInt();
        corruption = new ObservableInt();
        max_corruption = new ObservableInt();
        reckless = new ObservableInt();
        max_reckless = new ObservableInt();
        conservative = new ObservableInt();
        max_conservative = new ObservableInt();
    }

    public PlayerV2(Player player) {
        this();

        name.set(player.getName());
        race.set(player.getRace());
        age.set(player.getAge());
        size.set(player.getSize());
        description.set(player.getDescription());

        career.set(player.getCareer());
        rank.set(player.getRank());
        experience.set(player.getExperience());
        max_experience.set(player.getMax_experience());
        wounds.set(player.getWounds());
        max_wounds.set(player.getMax_wounds());
        corruption.set(player.getCorruption());
        max_corruption.set(player.getMax_corruption());
        reckless.set(player.getReckless());
        max_reckless.set(player.getMax_reckless());
        conservative.set(player.getConservative());
        max_conservative.set(player.getMax_conservative());
    }

    public Player getPlayer() {
        Player player = new Player();

        player.setName(name.get());
        player.setRace(race.get());
        player.setAge(age.get());
        player.setSize(size.get());
        player.setDescription(description.get());
        player.setCareer(career.get());
        player.setRank(rank.get());
        player.setExperience(experience.get());
        player.setMax_experience(max_experience.get());
        player.setWounds(wounds.get());
        player.setMax_wounds(max_wounds.get());
        player.setCorruption(corruption.get());
        player.setMax_corruption(max_corruption.get());
        player.setReckless(reckless.get());
        player.setMax_reckless(max_reckless.get());
        player.setConservative(conservative.get());
        player.setMax_conservative(max_conservative.get());

        return player;
    }

    @Override
    public int getId() {
        return id;
    }
}
