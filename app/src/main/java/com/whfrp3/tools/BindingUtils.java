package com.whfrp3.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.whfrp3.ihm.adapters.PlayerSkillsListAdapter;
import com.whfrp3.ihm.adapters.PlayerTalentsSeparatedListAdapter;
import com.whfrp3.ihm.adapters.WeaponsListAdapter;
import com.whfrp3.model.player.PlayerSkill;
import com.whfrp3.model.player.PlayerTalent;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.tools.enums.TextIcon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Binding utils.
 */
public abstract class BindingUtils {

    //region Conversion
    @BindingAdapter("android:text")
    public static void setText(TextView view, int value) {
        String res = value == 0 ? "" : String.valueOf(value);
        if (!res.equals(view.getText().toString())) {
            view.setText(res);
        }
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view) {
        String content = null;
        try {
            content = view.getText().toString();
            return Integer.parseInt(content);
        } catch (NumberFormatException nfe) {
            Log.e("BindingModel", "Bad content '" + content + "'", nfe);
        }
        return 0;
    }
    //endregion

    //region Custom binding attributes
    @BindingAdapter("skills")
    public static void bindSkills(ListView view, List<PlayerSkill> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PlayerSkillsListAdapter adapter = new PlayerSkillsListAdapter(inflater, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("weapons")
    public static void bindWeapons(ListView view, List<Weapon> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WeaponsListAdapter adapter = new WeaponsListAdapter(inflater, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("playerTalents")
    public static void bindPlayerTalents(ListView view, List<PlayerTalent> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Collections.sort(list, new Comparator<PlayerTalent>() {
            @Override
            public int compare(PlayerTalent playerTalent, PlayerTalent otherTalent) {
                return playerTalent.getTalent().compareTo(otherTalent.getTalent());
            }
        });

        List<PlayerTalent> equipped = new ArrayList<>();
        List<PlayerTalent> unequipped = new ArrayList<>();
        for (PlayerTalent playerTalent : list) {
            if (playerTalent.isEquipped()) {
                equipped.add(playerTalent);
            } else {
                unequipped.add(playerTalent);
            }
        }

        PlayerTalentsSeparatedListAdapter adapter = new PlayerTalentsSeparatedListAdapter(inflater, equipped, unequipped);
        view.setAdapter(adapter);
    }

    @BindingAdapter("android:typeface")
    public static void setTypeface(TextView v, String style) {
        switch (style) {
            case "bold":
                v.setTypeface(null, Typeface.BOLD);
                break;
            default:
                v.setTypeface(null, Typeface.NORMAL);
                break;
        }
    }

    @BindingAdapter("textWithIcons")
    public static void bindTextIcons(TextView view, String text) {
        SpannableString spanStr = new SpannableString(text);

        Pattern pattern = Pattern.compile("\\{([A-Z_]+)\\}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            TextIcon icon = TextIcon.valueOf(matcher.group(1));

            putImageInStringAtPosition(spanStr, drawable(icon.getDrawable()), matcher.start(), matcher.end(), icon.getAlignment());
        }

        view.setText(spanStr);
    }
    //endregion

    /**
     * Return the string corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return String corresponding to the resource id.
     */
    public static String string(int resourceId) {
        if (resourceId != 0) {
            return WHFRP3Application.getResourceString(resourceId);
        } else {
            return null;
        }
    }

    /**
     * Return the drawable corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return Drawable corresponding to the resource id.
     */
    public static Drawable drawable(int resourceId) {
        if (resourceId != 0) {
            return WHFRP3Application.getResourceDrawable(resourceId);
        } else {
            return null;
        }
    }

    /**
     * Return the drawable corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return Drawable corresponding to the resource id.
     */
    public static int color(int resourceId) {
        return WHFRP3Application.getResourceColor(resourceId);
    }

    public static SpannableString labelDrawableWithColon(String label, Drawable drawable) {
        SpannableString res = new SpannableString(label + "   :");
        putImageInStringAtPosition(res, drawable, res.length() - 3, res.length() - 2, ImageSpan.ALIGN_BOTTOM);
        return res;
    }

    private static void putImageInStringAtPosition(SpannableString spannableString, Drawable drawable, int startPosition, int endPosition, int alignment) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        ImageSpan imageSpan = new ImageSpan(drawable, alignment);

        spannableString.setSpan(imageSpan, startPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static String labelWithColon(String label) {
        return label + " :";
    }
}
