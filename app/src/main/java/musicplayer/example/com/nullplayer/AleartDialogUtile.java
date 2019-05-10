package musicplayer.example.com.nullplayer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AleartDialogUtile extends Dialog {
    String TAG = AleartDialogUtile.class.getSimpleName();
    private static Dialog alertDialog = null;// 对话框窗体级变量
    private Handler dismissHandler = new Handler();
    Button tvOkButton;
    ImageView imageView;
    TextView tvMessage;
    public AleartDialogUtile( @NonNull Context context) {
        super(context);
        init(context);
    }

    public AleartDialogUtile( @NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected AleartDialogUtile( @NonNull Context context, boolean cancelable,  @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    public void init(Context context){
        setContentView(R.layout.alert_message);

        tvMessage = (TextView) findViewById(R.id.textView);
        tvMessage.setVisibility(View.GONE);
        tvOkButton = (Button) findViewById(R.id.okButton);
        tvOkButton.setFocusable(true);
        tvOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);
    }

    public void setAutoDismiss(boolean autoDismiss){
        if (autoDismiss) {
            show(1000*5);
            tvOkButton.setVisibility(View.GONE);
        } else {
            tvOkButton.setVisibility(View.VISIBLE);
        }
    }



    public void setMessage(String message){
        tvMessage.setText(message);
        tvMessage.setVisibility(View.VISIBLE);
    }

    public void setImage(Context context,int image){
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageDrawable(context.getResources().getDrawable(image));
    }

    public void show(int milli){
        dismissHandler.postDelayed(dismissRunnable,milli);
    }

    private Runnable dismissRunnable = new Runnable() {
        @Override
        public void run() {
            try{
                dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
}
