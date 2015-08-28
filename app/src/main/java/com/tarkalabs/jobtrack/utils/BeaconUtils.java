package com.tarkalabs.jobtrack.utils;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.utils.UrlBeaconUrlCompressor;

public class BeaconUtils {
    public static String getURL(Beacon beacon) {
        String url = UrlBeaconUrlCompressor.uncompress(beacon.getId1().toByteArray());
        return url;
    }
}
