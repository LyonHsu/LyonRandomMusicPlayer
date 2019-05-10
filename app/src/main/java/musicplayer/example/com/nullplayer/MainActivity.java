package musicplayer.example.com.nullplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.VideoView;

public class MainActivity extends Activity {
    String TAG = MainActivity.class.getSimpleName();
    VideoView mVideoView ;
    /**
     * 香港卫视：http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8
     * CCTV1高清：http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
     * CCTV3高清：http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
     * CCTV5高清：http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8
     * CCTV5+高清：http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8
     * CCTV6高清：http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8
     * 苹果提供的测试源（点播）：http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        UtileDialog.showDialog(MainActivity.this,"Error","test1",R.drawable.wrong,false);

        UtileDialog.showDialog(MainActivity.this,"Error","test2",R.drawable.wrong,false);

        UtileDialog.showDialog(MainActivity.this,"Error","test3",R.drawable.wrong,false);
    }

    private void initView() {
        String url="http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
        VideoView videoView=(VideoView)findViewById(R.id.videoView);
        videoView.setVideoPath(url);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if(what==MediaPlayer.MEDIA_ERROR_UNKNOWN //未指定的媒体播放器错误。
                        ||what==MediaPlayer.MEDIA_ERROR_SERVER_DIED //媒体服务器死了。在这种情况下，应用程序必须释放
                        ||what==MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK//视频流，其容器对逐行扫描无效。
                        ||what==MediaPlayer.MEDIA_ERROR_MALFORMED//文件或网络操作错误
                        ||what==MediaPlayer.MEDIA_ERROR_UNSUPPORTED//比特流符合相关的编码标准或文件规范，但 媒体框架不支持该功能。
                        ||what==MediaPlayer.MEDIA_ERROR_TIMED_OUT//超时
                        ||what==MediaPlayer.MEDIA_ERROR_IO//IO刘错误
                        )
                {
                    String errorString = "未指定的媒体播放器错误。";
                    switch (what){
                        case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                            errorString = "未指定的媒体播放器错误。";
                            break;
                        case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                            errorString = "媒体服务器死了。在这种情况下，应用程序必须释放。";
                            break;
                        case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                            errorString = "视频流，其容器对逐行扫描无效。";
                            break;
                        case MediaPlayer.MEDIA_ERROR_MALFORMED:
                            errorString="文件或网络操作错误。";
                            break;
                        case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                            errorString="比特流符合相关的编码标准或文件规范，但 媒体框架不支持该功能。";
                            break;
                        case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                            errorString="連結超时";
                            break;
                        case MediaPlayer.MEDIA_ERROR_IO:
                            errorString="播放器IO错误";
                            break;
                    }
                    UtileDialog.showDialog(MainActivity.this,"Error",errorString,R.drawable.wrong,false);
                    Log.e(TAG,"\n extra is "+extra
                            +"\n what is "+what);
                    return false;
                }
                return true;//如果设置true就可以防止他弹出错误的提示框！
            }
        });

        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what==MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){

                }
                Log.i(TAG,"\n extra is "+extra
                        +"\n what is "+what);
                UtileDialog.showDialog(MainActivity.this,"Error","test4:"+what,R.drawable.wrong,false);
                return false;
            }
        });


    }
}
