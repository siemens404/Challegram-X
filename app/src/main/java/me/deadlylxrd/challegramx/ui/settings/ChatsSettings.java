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

public class ChatsSettings extends RecyclerViewController<Void> implements View.OnClickListener, ViewController.SettingsIntDelegate {

	private SettingsAdapter adapter;

	public ChatsSettings(Context context, Tdlib tdlib) {
		super(context, tdlib);
	}

	@Override public CharSequence getName() {
		return Lang.getString(R.string.ChatsSettings);
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		switch(id) {
			case R.id.btn_disableCamBtn:
				ChallegramXSettings.instance().toggleDisableCamBtn();
				adapter.updateVauledSettingById(R.id.btn_disableCamBtn);
				break;
			case R.id.btn_disableRecBtn:
				ChallegramXSettings.instance().toggleDisableRecBtn();
				adapter.updateVauledSettingById(R.id.btn_disableRecBtn);
				break;
		}
	}

	@Override public void onApplySettings (int id, SparseIntArray result) {
    	// Do nothing.
  	}

  	@Override public int getId () {
    	return R.id.ChatsSettings;
  	}

  	@Override protected void onCreateView(Context context, CustomRecyclerView recyclerView) {
  		adapter = new SettingsAdapter(this) {
  			@Override protected void setValuedSetting(ListItem item, SettingView view, boolean isUpdate) {
  				view.setDrawModifier(item.getDrawModifier());
  				switch(item.getId()) {
  					case R.id.btn_disableCamBtn:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isCamBtnDisabled(), isUpdate);
  						break;
  					case R.id.btn_disableRecBtn:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isRecBtnDisabled(), isUpdate);
  						break;
  				}
  			}
  		};

  		// New ArrayList For Our RecyclerView
  		ArrayList<ListItem> items = new ArrayList<>();

  		items.add(new ListItem(ListItem.TYPE_EMPTY_OFFSET_SMALL));
    	items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.ChatsSettings));

    	items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_disableCamBtn, 0, R.string.DisableCameraButton));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR_FULL));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_disableRecBtn, 0, R.string.DisableRecordButton));
    	items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));

  		adapter.setItems(items, true);
    	recyclerView.setAdapter(adapter);

    }

}