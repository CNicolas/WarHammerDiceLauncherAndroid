package com.whfrp3.tools;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TextView;

import com.whfrp3.R;
import com.whfrp3.ihm.adapters.SkillsListAdapter;
import com.whfrp3.ihm.adapters.WeaponsListAdapter;
import com.whfrp3.model.player.Skill;
import com.whfrp3.model.player.inventory.Weapon;
import com.whfrp3.tools.enums.TextIcon;

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
    public static void bindSkills(ListView view, List<Skill> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        SkillsListAdapter adapter = new SkillsListAdapter(inflater, list);
        view.setAdapter(adapter);
    }

    @BindingAdapter("weapons")
    public static void bindWeapons(ListView view, List<Weapon> list) {
        LayoutInflater inflater = (LayoutInflater) WHFRP3Application.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WeaponsListAdapter adapter = new WeaponsListAdapter(inflater, list);
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

            putImageInStringAtPosition(spanStr, drawable(icon.getDrawable()), matcher.start(), matcher.end());
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
        return WHFRP3Application.getResourceString(resourceId);
    }

    /**
     * Return the drawable corresponding to the given resource id.
     *
     * @param resourceId String id.
     * @return Drawable corresponding to the resource id.
     */
    public static Drawable drawable(int resourceId) {
        return WHFRP3Application.getResourceDrawable(resourceId);
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
        putImageInStringAtPosition(res, drawable, res.length() - 3, res.length() - 2);
        return res;
    }

    private static void putImageInStringAtPosition(SpannableString spannableString, Drawable drawable, int startPosition, int endPosition) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

        spannableString.setSpan(imageSpan, startPosition, endPosition, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public static String labelWithColon(String label) {
        return label + " :";
    }
}
