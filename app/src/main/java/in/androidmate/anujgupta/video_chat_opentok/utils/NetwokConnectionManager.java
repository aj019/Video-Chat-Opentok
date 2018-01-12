package in.androidmate.anujgupta.video_chat_opentok.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by anujgupta on 12/01/18.
 */

public class NetwokConnectionManager {
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 0;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = NetwokConnectionManager.getConnectivityStatus(context);
        String status = null;
        if (conn == NetwokConnectionManager.TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == NetwokConnectionManager.TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == NetwokConnectionManager.TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    public static boolean isOnline(Context context) {

        int connectivityStatus = NetwokConnectionManager.getConnectivityStatus(context);

        return (connectivityStatus == NetwokConnectionManager.TYPE_WIFI ||
                connectivityStatus == NetwokConnectionManager.TYPE_MOBILE);
    }
}