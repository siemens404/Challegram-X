// This is a Challegram X source code file.
// Challegram X is not a trademark of Telegram and Telegram X.
// Challegram X is an open and freely distributed modification of Telegram X.

package me.deadlylxrd.challegramx.utils;

import me.deadlylxrd.challegramx.configs.Config;
import org.thunderdog.challegram.R;
import org.thunderdog.challegram.core.Lang;

public class Utils {

	// Get Challegram-X version
	public static String getChgxVersion() {
		String msg = Lang.getString(R.string.ChallegramXVersion, Config.CHALLEGRAMX_VERSION);
		return msg;
	}
}
