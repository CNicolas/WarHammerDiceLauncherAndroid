package com.whfrp3.tools.enums;

import android.text.style.ImageSpan;

import com.whfrp3.R;

/**
 * Icons can be used in TextView.
 */
public enum TextIcon {

    /**
     * Misfortune dice.
     */
    MISFORTUNE_DICE(R.drawable.ic_misfortune_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Fortune dice.
     */
    FORTUNE_DICE(R.drawable.ic_fortune_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Expertise dice.
     */
    EXPERTISE_DICE(R.drawable.ic_expertise_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Characteristic dice.
     */
    CHARACTERISTIC_DICE(R.drawable.ic_characteristic_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Conservative dice.
     */
    CONSERVATIVE_DICE(R.drawable.ic_conservative_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Reckless dice.
     */
    RECKLESS_DICE(R.drawable.ic_reckless_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Challenge dice.
     */
    CHALLENGE_DICE(R.drawable.ic_challenge_dice, ImageSpan.ALIGN_BASELINE),

    /**
     * Boon face.
     */
    BOON_FACE(R.drawable.ic_boon_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Bane face.
     */
    BANE_FACE(R.drawable.ic_bane_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Sucess face.
     */
    SUCCESS_FACE(R.drawable.ic_success_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Delay face.
     */
    DELAY_FACE(R.drawable.ic_delay_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Exertion face.
     */
    EXERTION_FACE(R.drawable.ic_exertion_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Failure face.
     */
    FAILURE_FACE(R.drawable.ic_failure_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Sigmar face.
     */
    SIGMAR_FACE(R.drawable.ic_sigmar_black_16, ImageSpan.ALIGN_BOTTOM),

    /**
     * Chaos face.
     */
    CHAOS_FACE(R.drawable.ic_chaos_black_16, ImageSpan.ALIGN_BOTTOM);

    /**
     * Drawable.
     */
    private final int drawable;

    /**
     * Icon alignment.
     */
    private final int alignment;

    /**
     * Constructor.
     *
     * @param drawable  Drawable.
     * @param alignment Icon alignment.
     */
    TextIcon(int drawable, int alignment) {
        this.drawable = drawable;
        this.alignment = alignment;
    }

    /**
     * Return icon drawable.
     *
     * @return Icon drawable.
     */
    public int getDrawable() {
        return drawable;
    }

    /**
     * Return icon alignment.
     *
     * @return Icon alignment.
     */
    public int getAlignment() {
        return alignment;
    }
}
