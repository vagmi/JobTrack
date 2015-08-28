package com.tarkalabs.jobtrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.tarkalabs.jobtrack.adapters.PlantFragmentPageAdapter;
import com.tarkalabs.jobtrack.service.BeaconService;
import com.tarkalabs.jobtrack.ui.PlantFragment;
import com.tarkalabs.jobtrack.utils.BeaconUtils;

import org.altbeacon.beacon.Beacon;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;
    private PlantFragment plantFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MenuItem rangingButton;
    private boolean isRanging = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PlantFragmentPageAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);





//        subscribeRanging();
    }

    private void subscribeRanging() {
        startService(new Intent(this, BeaconService.class));
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Beacon nearestBeacon = intent.getExtras().getParcelable("nearest_beacon");
                String url = BeaconUtils.getURL(nearestBeacon);
                int pos = Integer.parseInt(url.substring("http://tarkalabs.com/plant".length()));
                viewPager.setCurrentItem(pos-1);
                Log.i("MainActivity", url);
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, new IntentFilter(BeaconService.RANGING));
        rangingButton.setIcon(R.mipmap.ic_room_black);
        isRanging = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        rangingButton = menu.findItem(R.id.ranging);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.ranging) {
            if(!isRanging) {
                subscribeRanging();
            } else {
                unsubscribeRanging();
                rangingButton.setIcon(R.mipmap.ic_room_white);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribeRanging();
    }

    private void unsubscribeRanging() {
        stopService(new Intent(this, BeaconService.class));
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        isRanging = false;
    }
}