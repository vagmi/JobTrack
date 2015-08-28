package com.tarkalabs.jobtrack.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


public class BeaconService extends Service implements BeaconConsumer {

    public static final String TAG = "BEACON_SERVICE";
    public static final String RANGING = "RANGING";
    private BeaconManager beaconManager;
    private Region allBeaconsRegion;
    private Context baseContext;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service created");
        baseContext = getBaseContext();
        beaconManager = BeaconManager.getInstanceForApplication(baseContext);
        BeaconParser urlParser = new BeaconParser().
                setBeaconLayout("s:0-1=feaa,m:2-2=10,p:3-3:-41,i:4-20");
        beaconManager.getBeaconParsers().add(urlParser);
        beaconManager.bind(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Start command received");
        beaconManager.setBackgroundMode(false);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "on destroy received");
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new MyRangingNotifier());
        allBeaconsRegion = new Region("all-beacons-region", null, null, null);
        try {
            beaconManager.startRangingBeaconsInRegion(allBeaconsRegion);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private  class MyRangingNotifier implements RangeNotifier {

        public static final String PREFIX = "http://tarkalabs.com/";
        private Beacon nearestBeacon;

        @Override
        public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {

            int beaconCount = collection.size();
            if(beaconCount > 0) {
                ArrayList<Beacon> beacons = new ArrayList<>();

                for(Beacon beacon : collection) {
                    if (beacon.getServiceUuid() == 0xfeaa && beacon.getBeaconTypeCode() == 0x10) {
                        String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
                        if(url.startsWith("http://tarkalabs.com/plant")) {
                            String roomName = url.substring(PREFIX.length(), url.length() - 1);
                            Log.i(TAG, "found beacon " + url + " " + beacon.getDistance() + " meters away.");
                            beacons.add(beacon);
                        }
                    }
                }
                Collections.sort(beacons, new Comparator<Beacon>() {
                    @Override
                    public int compare(Beacon lhs, Beacon rhs) {
                        Double lhsDistance = new Double(lhs.getDistance());
                        Double rhsDistance = new Double(rhs.getDistance());
                        return lhsDistance.compareTo(rhsDistance);
                    }
                });
                if(beacons.size() > 0) {
                    nearestBeacon = beacons.get(0);
                } else {
                    nearestBeacon = null;
                }
            }
            if(nearestBeacon != null) {
                Intent intent = new Intent(RANGING);
                intent.putExtra("nearest_beacon", nearestBeacon);
                LocalBroadcastManager.getInstance(getBaseContext()).sendBroadcast(intent);
            }
        }
    }

}
