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

public class DrawerSettings extends RecyclerViewController<Void> implements View.OnClickListener, ViewController.SettingsIntDelegate {

	private SettingsAdapter adapter;

	public DrawerSettings(Context context, Tdlib tdlib) {
		super(context, tdlib);
	}

	@Override public CharSequence getName() {
		return Lang.getString(R.string.DrawerSettings);
	}

	@Override public void onClick(View v) {
		int id = v.getId();
		switch(id) {
			case R.id.btn_contacts:
				ChallegramXSettings.instance().toggleShowDrawerContacts();
        		adapter.updateValuedSettingById(R.id.btn_contacts);
        		break;
        	case R.id.btn_savedMessages:
				ChallegramXSettings.instance().toggleShowDrawerFavourite();
        		adapter.updateValuedSettingById(R.id.btn_savedMessages);
        		break;
        	case R.id.btn_invite:
				ChallegramXSettings.instance().toggleShowDrawerInvites();
        		adapter.updateValuedSettingById(R.id.btn_invite);
        		break;
        	case R.id.btn_help:
				ChallegramXSettings.instance().toggleShowDrawerHelp();
        		adapter.updateValuedSettingById(R.id.btn_help);
        		break;
        	case R.id.btn_night:
				ChallegramXSettings.instance().toggleShowDrawerNightmode();
        		adapter.updateValuedSettingById(R.id.btn_night);
        		break;
		}
	}

	@Override public void onApplySettings (int id, SparseIntArray result) {
    	// Do nothing.
  	}

  	@Override public int getId () {
    	return R.id.DrawerSettings;
  	}

  	@Override protected void onCreateView(Context context, CustomRecyclerView recyclerView) {
  		adapter = new SettingsAdapter(this) {
  			@Override protected void setValuedSetting(ListItem item, SettingView view, boolean isUpdate) {
  				view.setDrawModifier(item.getDrawModifier());
  				switch(item.getId()) {
  					case R.id.btn_contacts:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isDrawerContactsShows(), isUpdate);
            			break;
            		case R.id.btn_savedMessages:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isDrawerFavouriteShows(), isUpdate);
            			break;
            		case R.id.btn_invite:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isDrawerInvitesShows(), isUpdate);
            			break;
            		case R.id.btn_help:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isDrawerHelpShows(), isUpdate);
            			break;
            		case R.id.btn_night:
  						view.getToggler().setRadioEnabled(ChallegramXSettings.instance().isDrawerNightmodeShows(), isUpdate);
            			break;
  				}
  			}
  		};

  		// New ArrayList For Our RecyclerView
  		ArrayList<ListItem> items = new ArrayList<>();

  		items.add(new ListItem(ListItem.TYPE_EMPTY_OFFSET_SMALL));
    	items.add(new ListItem(ListItem.TYPE_DESCRIPTION, 0, 0, R.string.DrawerSettingsDesc));

    	items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_contacts, R.drawable.baseline_perm_contact_calendar_24, R.string.Contacts));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_savedMessages, R.drawable.baseline_bookmark_24, R.string.SavedMessages));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_invite, R.drawable.baseline_person_add_24, R.string.InviteFriends));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_help, R.drawable.baseline_help_24, R.string.Help));
    	items.add(new ListItem(ListItem.TYPE_SEPARATOR));
    	items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_night, R.drawable.baseline_brightness_2_24, R.string.NightMode));
    	items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));



  		adapter.setItems(items, true);
    	recyclerView.setAdapter(adapter);

    }

}