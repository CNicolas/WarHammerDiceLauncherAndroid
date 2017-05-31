package com.whfrp3.model.player;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.whfrp3.model.Skill;
import com.whfrp3.tools.helpers.SkillHelper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Skill of a player.
 */
public class PlayerSkill extends BaseObservable implements Serializable {

    //region Properties

    /**
     * Technical identifier of the associated skill.
     */
    private final long skillId;

    /**
     * Associated skill.
     */
    private transient Skill skill;

    /**
     * Level of the associated skill.
     */
    private int level;

    private boolean specialized;

    //endregion

    //region Constructors

    /**
     * Constructor without skill level.
     *
     * @param skill Associated skill.
     */
    public PlayerSkill(Skill skill) {
        this.skillId = skill.getId();
        this.skill = skill;
        this.level = 0;
    }

    /**
     * Constructor with skill level.
     *
     * @param skill Associated skill.
     * @param level Level of the associated skill.
     */
    public PlayerSkill(Skill skill, int level) {
        this.skillId = skill.getId();
        this.skill = skill;
        this.level = level;
    }

    //endregion

    //region Main methods

    /**
     * Fill transient fields after an unserialization.
     */
    public void fillTransientFields() {
        this.skill = SkillHelper.getInstance().getSkill(skillId);
    }

    //endregion

    //region Get & Set

    public long getSkillId() {
        return skillId;
    }

    public Skill getSkill() {
        return skill;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Bindable
    public boolean isSpecialized() {
        return specialized;
    }

    public void setSpecialized(boolean specialized) {
        this.specialized = specialized;
    }

    //endregion

    //region Overrides

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSkill other = (PlayerSkill) o;

        return new EqualsBuilder()
                .append(skillId, other.skillId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(skillId)
                .toHashCode();
    }

    //endregion
}