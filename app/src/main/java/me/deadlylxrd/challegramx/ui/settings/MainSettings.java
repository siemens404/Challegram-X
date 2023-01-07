// This is a Challegram X source code file.
// Challegram X is not a trademark of Telegram and Telegram X.
// Challegram X is an open and freely distributed modification of Telegram X.

package me.deadlylxrd.challegramx.ui.settings;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;

import org.thunderdog.challegram.R;
import org.thunderdog.challegram.component.base.SettingView;
import org.thunderdog.challegram.core.Lang;
import org.thunderdog.challegram.navigation.SettingsWrapBuilder;
import org.thunderdog.challegram.navigation.ViewController;
import org.thunderdog.challegram.telegram.Tdlib;
import org.thunderdog.challegram.telegram.TdlibUi;
import org.thunderdog.challegram.tool.Strings;
import org.thunderdog.challegram.ui.ListItem;
import org.thunderdog.challegram.ui.RecyclerViewController;
import org.thunderdog.challegram.ui.SettingsAdapter;
import org.thunderdog.challegram.unsorted.Settings;
import org.thunderdog.challegram.util.AppUpdater;
import org.thunderdog.challegram.v.CustomRecyclerView;

import java.util.ArrayList;

import me.deadlylxrd.challegramx.ChallegramXSettings;
import me.deadlylxrd.challegramx.configs.Config;

public class MainSettings extends RecyclerViewController<Void> implements View.OnClickListener, ViewController.SettingsIntDelegate {

	private SettingsAdapter adapter;

	public MainSettings(Context context, Tdlib tdlib) {
		super(context, tdlib);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override public CharSequence getName() {
		return Lang.getString(R.string.ChallegramXSettings);
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		switch(id) {
			case R.id.btn_generalSettings:
				navigateTo(new GeneralSettings(context, tdlib));
				break;
			case R.id.btn_appearanceSettings:
				navigateTo(new AppearanceSettings(context, tdlib));
				break;
			case R.id.btn_chatsSettings:
				navigateTo(new ChatsSettings(context, tdlib));
				break;
			case R.id.btn_otherSettings:
				navigateTo(new OtherSettings(context, tdlib));
				break;
			case R.id.btn_telegramChannel: {
				tdlib.ui().openUrl(this, Config.CHALLEGRAMX_CHANNEL, new TdlibUi.UrlOpenParameters().forceInstantView());
        		break;
			}
			case R.id.btn_telegramChat: {
				tdlib.ui().openUrl(this, Config.CHALLEGRAMX_CHAT, new TdlibUi.UrlOpenParameters().forceInstantView());
				break;
			}
			case R.id.btn_sourceCode: {
				tdlib.ui().openUrl(this, Config.CHALLEGRAMX_SOURCE, new TdlibUi.UrlOpenParameters().forceInstantView());
				break;
			}
			case R.id.btn_developer: {
				tdlib.ui().openUrl(this, Config.CHALLEGRAMX_DEV, new TdlibUi.UrlOpenParameters().forceInstantView());
				break;
			}
			case R.id.btn_donate: {
				tdlib.ui().openUrl(this, Config.DONATE, new TdlibUi.UrlOpenParameters().forceInstantView());
				break;
			}

		}
	}

	@Override public void onApplySettings (int id, SparseIntArray result) {
    	// Do nothing.
  	}

  	@Override public int getId () {
    	return R.id.ChallegramXSettings;
  	}

  	@Override protected void onCreateView(Context context, CustomRecyclerView recyclerView) {
  		adapter = new SettingsAdapter(this) {
  			@Override protected void setValuedSetting(ListItem item, SettingView view, boolean isUpdate) {
  				view.setDrawModifier(item.getDrawModifier());
  				switch(item.getId()) {
  					case R.id.btn_telegramChannel:
  						view.setData(R.string.TelegramChannelDesc);
  						break;
  					case R.id.btn_telegramChat:
  						view.setData(R.string.TelegramChatDesc);
  					case R.id.btn_sourceCode:
  						view.setData(R.string.SourceCodeDesc);
  						break;
  					case R.id.btn_developer:
  						view.setData(R.string.DeveloperDesc);
  						break;
  					case R.id.btn_donate:
  						view.setData(R.string.DonateDesc);
  						break;

  				}
  			}
  		};

  		// New ArrayList For Our RecyclerView
  		ArrayList<ListItem> items = new ArrayList<>();

  		items.add(new ListItem(ListItem.TYPE_EMPTY_OFFSET_SMALL));
    	items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.ChallegramXSettings));

    	// Challegram X Settings Sections
    	items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    	items.add(new ListItem(ListItem.TYPE_SETTING, R.id.btn_generalSettings, R.drawable.baseline_widgets_24, R.string.GeneralSettings));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_SETTING, R.id.btn_appearanceSettings, R.drawable.baseline_palette_24, R.string.AppearanceSettings));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_SETTING, R.id.btn_chatsSettings, R.drawable.baseline_chat_bubble_24, R.string.ChatsSettings));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_SETTING, R.id.btn_otherSettings, R.drawable.baseline_extension_24, R.string.OtherSettings));
    	items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));

    	items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.ChallegramXLinks));

    	// Challegram X Links
    	items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    	items.add(new ListItem(ListItem.TYPE_VALUED_SETTING_COMPACT, R.id.btn_telegramChannel, R.drawable.baseline_help_24, R.string.TelegramChannel));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_VALUED_SETTING_COMPACT, R.id.btn_telegramChat, R.drawable.baseline_forum_24, R.string.TelegramChat));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));

    	items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.ChallegramXInfo))

    	// Challegram X Dev Stuff
    	items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    	items.add(new ListItem(ListItem.TYPE_VALUED_SETTING_COMPACT, R.id.btn_developer, R.drawable.baseline_code_24, R.string.Developer));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_VALUED_SETTING_COMPACT, R.id.btn_sourceCode, R.drawable.baseline_github_24, R.string.SourceCode));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_VALUED_SETTING_COMPACT, R.id.btn_donate, R.drawable.baseline_paid_24, R.string.Donate));
    	items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));

    	adapter.setItems(items, true);
    	recyclerView.setAdapter(adapter);

  	}

}