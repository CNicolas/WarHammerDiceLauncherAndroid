package com.whfrp3.tools.enums;

import com.whfrp3.R;

/**
 * Icons can be used in TextView.
 */
public enum TextIcon {

    /**
     * Misfortune dice.
     */
    MISFORTUNE_DICE(R.drawable.ic_fortune_dice),

    /**
     * Fortune dice.
     */
    FORTUNE_DICE(R.drawable.ic_fortune_dice),

    /**
     * Expertise dice.
     */
    EXPERTISE_DICE(R.drawable.ic_fortune_dice),

    /**
     * Characteristic dice.
     */
    CHARACTERISTIC_DICE(R.drawable.ic_fortune_dice),

    /**
     * Conservative dice.
     */
    CONSERVATIVE_DICE(R.drawable.ic_fortune_dice),

    /**
     * Challenge dice.
     */
    CHALLENGE_DICE(R.drawable.ic_fortune_dice),

    /**
     * Boon face.
     */
    BOON_FACE(R.drawable.ic_boon_black_18),

    /**
     * Bane face.
     */
    BANE_FACE(R.drawable.ic_bane_black),

    /**
     * Sucess face.
     */
    SUCCESS_FACE(R.drawable.ic_success_black),

    /**
     * Delay face.
     */
    DELAY_FACE(R.drawable.ic_delay_black),

    /**
     * Exertion face.
     */
    EXERTION_FACE(R.drawable.ic_exertion_black),

    /**
     * Failure face.
     */
    FAILURE_FACE(R.drawable.ic_failure_black),

    /**
     * Sigmar face.
     */
    SIGMAR_FACE(R.drawable.ic_sigmar_black),

    /**
     * Chaos face.
     */
    CHAOS_FACE(R.drawable.ic_chaos_black);

    /**
     * Drawable.
     */
    private int drawable;

    /**
     * Constructor.
     *
     * @param drawable Drawable.
     */
    TextIcon(int drawable) {
        this.drawable = drawable;
    }

    /**
     * Renvoie le drawable associé à la valeur de l'enum.
     *
     * @return Drawable associé à la valeur de l'enum.
     */
    public int getDrawable() {
        return drawable;
    }
}
