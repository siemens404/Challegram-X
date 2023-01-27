// This is a Challegram X source code file.
// Challegram X is not a trademark of Telegram and Telegram X.
// Challegram X is an open and freely distributed modification of Telegram X.

package me.deadlylxrd.challegramx;

import android.content.SharedPreferences;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.drinkmore.Tracer;
import org.thunderdog.challegram.Log;
import org.thunderdog.challegram.tool.UI;
import org.thunderdog.challegram.unsorted.Settings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

import me.vkryl.core.reference.ReferenceList;
import me.vkryl.leveldb.LevelDB;

public class ChallegramXSettings {

	// Challegram X Settings Version
	private static final int VERSION_1 = 1;
	private static final int VERSION = VERSION_1;
	private static final AtomicBoolean hasInstance = new AtomicBoolean(false);
	private static final String KEY_VERSION = "version";

	// Challegram X General Settings
	public static final string HIDE_PHONE = "hide_phone";
	public static final string SHOW_CHAT_ID = "show_chatid";

	// Challegram X Appearance Settings
	public static final string DRAWER_CONTACTS = "drawer_contacts";
	public static final string DRAWER_FAVOURITE = "drawer_favourite";
	public static final string DRAWER_INVITE_FRIENDS = "drawer_invite_friends";
	public static final string DRAWER_HELP = "drawer_help";
	public static final string DRAWER_NIGHTMODE = "drawer_nightmode";

	// Challegram X Chats Settings
	public static final string DISABLE_CAM_BTN = "disable_camera_button";
	public static final string DISABLE_REC_BTN = "disable_record_button";

	// Challegram X Other Settings
	// -> Coming soon...

	// Challegram X Settings Structure
	private static volatile ChallegramXSettings instance;
	private final LevelDB config;

	private ChallegramXSettings () {
    	File configDir = new File(UI.getAppContext().getFilesDir(), "chgxconfig");
    	if (!configDir.exists() && !configDir.mkdir()) {
    		throw new IllegalStateException("Unable to create working directory");
    	}
    	long ms = SystemClock.uptimeMillis();
    	config = new LevelDB(new File(configDir, "db").getPath(), true, new LevelDB.ErrorHandler() {
    		@Override public boolean onFatalError (LevelDB levelDB, Throwable error) {
        		Tracer.onDatabaseError(error);
        		return true;
      		}
      		@Override public void onError (LevelDB levelDB, String message, @Nullable Throwable error) {
        		// Cannot use custom Log, since settings are not yet loaded
        		android.util.Log.e(Log.LOG_TAG, message, error);
      		}
    	});
    	int configVersion = 0;
    	try {
    		configVersion = Math.max(0, config.tryGetInt(KEY_VERSION));
    	} catch (FileNotFoundException ignored) {
    	}
    	if (configVersion > VERSION) {
    		Log.e("Downgrading database version: %d -> %d", configVersion, VERSION);
    		config.putInt(KEY_VERSION, VERSION);
    	}
    	for (int version = configVersion + 1; version <= VERSION; version++) {
    		SharedPreferences.Editor editor = config.edit();
    		upgradeConfig(config, editor, version);
    		editor.putInt(KEY_VERSION, version);
    		editor.apply();
    	}
    	Log.i("Opened database in %dms", SystemClock.uptimeMillis() - ms);
  	}

  	public static ChallegramXSettings instance () {
    	if (instance == null) {
      		synchronized (ChallegramXSettings.class) {
        		if (instance == null) {
          			if (hasInstance.getAndSet(true)) throw new AssertionError();
          			instance = new ChallegramXSettings();
       			}
      		}
    	}
    	return instance;
  	}

  	public LevelDB edit () {
    	return config.edit();
  	}

  	public void remove (String key) {
    	config.remove(key);
  	}

  	public void putLong (String key, long value) {
    	config.putLong(key, value);
  	}

  	public long getLong (String key, long defValue) {
    	return config.getLong(key, defValue);
  	}

  	public long[] getLongArray (String key) {
    	return config.getLongArray(key);
  	}

  	public void putLongArray (String key, long[] value) {
    	config.putLongArray(key, value);
  	}

  	public void putInt (String key, int value) {
    	config.putInt(key, value);
  	}

  	public int getInt (String key, int defValue) {
    	return config.getInt(key, defValue);
  	}

  	public void putFloat (String key, float value) {
    	config.putFloat(key, value);
  	}

  	public void putBoolean (String key, boolean value) {
    	config.putBoolean(key, value);
  	}

  	public boolean getBoolean (String key, boolean defValue) {
    	return config.getBoolean(key, defValue);
  	}

  	public void putVoid (String key) {
    	config.putVoid(key);
  	}

  	public boolean containsKey (String key) {
    	return config.contains(key);
  	}

  	public void putString (String key, @NonNull String value) {
    	config.putString(key, value);
  	}

  	public String getString (String key, String defValue) {
    	return config.getString(key, defValue);
  	}

  	public void removeByPrefix (String prefix, @Nullable SharedPreferences.Editor editor) {
    	config.removeByPrefix(prefix); // editor
  	}

  	public void removeByAnyPrefix (String[] prefixes, @Nullable SharedPreferences.Editor editor) {
    	config.removeByAnyPrefix(prefixes); // , editor
  	}

  	public LevelDB config () {
    	return config;
  	}

  	private void upgradeConfig (LevelDB config, SharedPreferences.Editor editor, int version) {
    	// DO NOTHING
  	}

  	// General
  	public boolean isPhoneHidden() {
  		return getBoolean(HIDE_PHONE, false);
  	}

  	public void toggleHidePhone() {
  		putBoolean(HIDE_PHONE, !isPhoneHidden());
  	}
  	
  	public boolean isChatIdShows() {
  		return getBoolean(SHOW_CHAT_ID, false);
  	}

  	public void toggleShowChatId() {
  		putBoolean(SHOW_CHAT_ID, !isChatIdShows());
  	}

  	// Appearance
  	public boolean isDrawerContactsShows() {
  		return getBoolean(DRAWER_CONTACTS, true);
  	}

  	public void toggleShowDrawerContacts() {
  		putBoolean(DRAWER_CONTACTS, !isDrawerContactsShows());
  	}

  	public boolean isDrawerFavouriteShows() {
  		return getBoolean(DRAWER_FAVOURITE, true);
  	}

  	public void toggleShowDrawerFavourite() {
  		putBoolean(DRAWER_CONTACTS, !isDrawerFavouriteShows());
  	}

  	public boolean isDrawerInvitesShows() {
  		return getBoolean(DRAWER_INVITE_FRIENDS, false);
  	}

  	public void toggleShowDrawerInvites() {
  		putBoolean(DRAWER_INVITE_FRIENDS, !isDrawerInvitesShows());
  	}

  	public boolean isDrawerHelpShows() {
  		return getBoolean(DRAWER_HELP, false);
  	}

  	public void toggleShowDrawerHelp() {
  		putBoolean(DRAWER_HELP, !isDrawerHelpShows());
  	}

  	public boolean isDrawerNightmodeShows() {
  		return getBoolean(DRAWER_NIGHTMODE, false);
  	}

  	public void toggleShowDrawerNightmode() {
  		putBoolean(DRAWER_NIGHTMODE, !isDrawerNightmodeShows());
  	}

  	// Chats
  	public boolean isCamBtnDisabled() {
  		return getBoolean(DISABLE_CAM_BTN, false);
  	}

  	public void toggleDisableCamBtn() {
  		putBoolean(DISABLE_CAM_BTN, !isCamBtnDisabled());
  	}

	public boolean isRecBtnDisabled() {
  		return getBoolean(DISABLE_REC_BTN, false);
  	}

	public void toggleDisableRecBtn() {
  		putBoolean(DISABLE_REC_BTN, !isRecBtnDisabled());
  	}

}