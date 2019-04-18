package vikash.kumar.hurrey_tech_vikash.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreference_Teacher {
    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void saveTeacherTimeTable(Context ctx, String json)
    {
        SharedPreferences.Editor prefsEditor = getSharedPreferences(ctx).edit();
        prefsEditor.putString("MyObject", json);
        prefsEditor.commit();
    }

    public static String getTeacherTimeTable(Context ctx)
    {
        return getSharedPreferences(ctx).getString("MyObject", "");
    }
}
