package in.androidmate.anujgupta.video_chat_opentok.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by anujgupta on 10/01/18.
 */

public class Typefacer {

    public static Typeface getBold(Context context){

        Typeface tfRegular = Typeface.createFromAsset(context.getAssets(),"fonts/HelveticaNeue-Bold.otf");
        return tfRegular;
    }

    public static Typeface getBoldItalic(Context context){

        Typeface tfRegular = Typeface.createFromAsset(context.getAssets(),"fonts/HelveticaNeue-BoldItalic.otf");
        return tfRegular;
    }


    public static Typeface getLight(Context context){

        Typeface tfRegular = Typeface.createFromAsset(context.getAssets(),"fonts/HelveticaNeue-Light.otf");
        return tfRegular;
    }

    public static Typeface getRegular(Context context){

        Typeface tfRegular = Typeface.createFromAsset(context.getAssets(),"fonts/HelveticaNeue.otf");
        return tfRegular;
    }


}
