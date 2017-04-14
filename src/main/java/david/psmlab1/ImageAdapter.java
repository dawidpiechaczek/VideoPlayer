package david.psmlab1;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class ImageAdapter extends BaseAdapter {
	private Context mContext;

	public ImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		return StaticData.images.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return StaticData.images[position];
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		RelativeLayout layout = new RelativeLayout(mContext);
		layout.setGravity(Gravity.CENTER);

		// if it's not recycled, initialize some attributes
		ImageView imageView = new ImageView(mContext);
		imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 570));
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setPadding(0,0,0,0);
		imageView.setImageResource(StaticData.images[position]);
		imageView.setTag("image");

		VideoView videoView = new VideoView(mContext);
		Uri adres = Uri.parse("android.resource://" + mContext.getPackageName() + "/" + (StaticData.videos[position]));
		videoView.setVideoURI(adres);
		videoView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		videoView.setMinimumHeight(570);
		videoView.setVisibility(View.GONE);
		videoView.setTag("video");

		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
				mediaPlayer.setVolume(0f, 0f);
				mediaPlayer.setLooping(true);
			}
		});

		layout.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 570));
		layout.addView(imageView);
		layout.addView(videoView);

		return layout;
	}
}