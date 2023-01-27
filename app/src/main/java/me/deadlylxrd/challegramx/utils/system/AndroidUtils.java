// This is a Challegram X source code file.
// Challegram X is not a trademark of Telegram and Telegram X.
// Challegram X is an open and freely distributed modification of Telegram X.

package me.deadlylxrd.challegramx.utils.system;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.Objects;

public class AndroidUtils {

  // Getting the global context through reflection to use context on application initialization
  @NonNull
  public static Context getGlobalContext() {
    try {
      return ReflectionUtils.invokeStaticMethod("android.app.AppGlobals", "getInitialApplication");
    } catch (Exception e) {
      Log.d("GlobalContext", "Error while fetching context via refl");
    }
    return Objects.requireNonNull(LifecycleUtils.getCurrentActivity());
  }

}