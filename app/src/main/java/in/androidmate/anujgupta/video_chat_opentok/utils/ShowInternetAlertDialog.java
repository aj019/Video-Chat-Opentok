package in.androidmate.anujgupta.video_chat_opentok.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;

/**
 * Created by anujgupta on 12/01/18.
 */

public class ShowInternetAlertDialog {


    public static void noInternet(final Activity context){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("No Internet Connection")
                .setMessage("Would you like to turn on your internet ?")
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                        context.finish();
                    }
                })
                .setPositiveButton("Οκ", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                    }
                });

        builder1.create().show();

    }

}
