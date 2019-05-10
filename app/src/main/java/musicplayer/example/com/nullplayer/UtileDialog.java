package musicplayer.example.com.nullplayer;

import android.content.Context;
import android.util.Log;

public class UtileDialog {
    static String TAG = UtileDialog.class.getSimpleName();
    static AleartDialogUtile dialog ;
    static int countTime = 0;
    public static void showDialog(Context context, String title, String msg, int image, boolean autoDismiss){
        if(dialog==null){
            dialog = new AleartDialogUtile(context);
            countTime=0;
        }
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setImage(context,image);
        dialog.setAutoDismiss(autoDismiss);
        countTime++;
        Log.d(TAG,"dialog.show() countTime:"+countTime);
        ToastUtile.showText(context,"dialog.show() countTime:"+countTime);
        dialog.show();
    }
}
