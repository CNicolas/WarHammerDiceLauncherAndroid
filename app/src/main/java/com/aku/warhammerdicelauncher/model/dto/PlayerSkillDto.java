package com.aku.warhammerdicelauncher.model.dto;

/**
 * Created by cnicolas on 06/10/2016.
 */

public class PlayerSkillDto implements IDto {
    private SkillDto skill;
    private int expertise;

    public PlayerSkillDto() {
    }

    public PlayerSkillDto(SkillDto skill, int expertise) {
        this.skill = skill;
        this.expertise = expertise;
    }

    //region Get & Set
    public SkillDto getSkill() {
        return skill;
    }

    public void setSkill(SkillDto skill) {
        this.skill = skill;
    }

    public int getExpertise() {
        return expertise;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }
    //endregion

    //region Overrides

    @Override
    public String toString() {
        return "PlayerSkillDto{" +
                "skill=" + skill +
                ", expertise=" + expertise +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerSkillDto that = (PlayerSkillDto) o;

        if (getExpertise() != that.getExpertise()) return false;
        return getSkill() != null ? getSkill().equals(that.getSkill()) : that.getSkill() == null;

    }

    @Override
    public int hashCode() {
        int result = getSkill() != null ? getSkill().hashCode() : 0;
        result = 31 * result + getExpertise();
        return result;
    }
    //endregion
}
