package david.psmlab1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import static david.psmlab1.R.id.viewPager;

public class ImagePager extends AppCompatActivity implements SurfaceHolder.Callback {

	private final static int MAX_VOLUME = 100;
	public static SurfaceHolder surfaceHolder;
	public static SurfaceView surfaceView;
	public static View itemView;
	public final ImagePager me = this;
	Handler handler;

	private MediaPlayer player;
	AudioManager audio;
	private int currentImg;
	SeekBar sb;
	SurfaceView sv;
	SurfaceHolder holder;
	ViewPager globalVp;

	int pausePos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(david.psmlab1.R.layout.activity_image_pager);

		player = new MediaPlayer();
		audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
		setUpButton();

		int liczba = getIntent().getExtras().getInt("id");
		PageAdapter pa = new PageAdapter(this);
		PageFragmentAdapter pfa = new PageFragmentAdapter(getSupportFragmentManager());

		final ViewPager vp = (ViewPager) findViewById(viewPager);
		globalVp = vp;

		vp.setAdapter(pfa);
		vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				player.seekTo(0);
				player.stop();
				pausePos = 0;
				//playMusic(position);
				currentImg = position;
				setUpButton();
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		vp.setCurrentItem(liczba);

		currentImg = liczba;

		sb = (SeekBar) findViewById(david.psmlab1.R.id.seekBar);
		sb.setProgress(0);

		sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (player != null && fromUser) {
					player.seekTo(progress);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}

	public void back(View v) {
		pauseMusic();
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
	}

	public void play(View v) {
		playMusic();
		setUpButton();
	}

	public void pause(View v) {
		pauseMusic();
		setUpButton();
	}

	public void playMusic() {
		player.release();
		player = MediaPlayer.create(this, StaticData.videos[currentImg]);
		setUpHolderView();
		player.start();
		player.seekTo(pausePos);
		sb.setMax(player.getDuration());
		sb.setProgress(pausePos);
		setTimer();
	}


	private void setTimer() {
		handler = new Handler();
		ImagePager.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				if (player != null) {
					int mCurrentPosition = player.getCurrentPosition();
					sb.setProgress(mCurrentPosition);
				}
				handler.postDelayed(this, 1000);
			}
		});

	}

	private void clearTimer() {

	}

	public void pauseMusic() {
		if (player != null) {
			//pausePos = sb.getProgress();
			pausePos = player.getCurrentPosition();
			player.pause();
			clearTimer();
		}
	}

	private void setUpHolderView() {
		SurfaceView surface =  (SurfaceView) findViewById(1000000 + currentImg);
		surface.setBackground(null);
		SurfaceHolder holder = surface.getHolder();
		holder.setFormat(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		player.setDisplay(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
	                           int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		surfaceHolder = holder;
		//player.setDisplay(holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBackPressed() {
		pauseMusic();
		Intent in = new Intent(this, MainActivity.class);
		startActivity(in);
		finish();
	}

/*
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
			if (sb.getProgress() > 0) {
				sb.setProgress(sb.getProgress() - 1);
			}
		} else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
			if (sb.getProgress() < sb.getMax()) {
				sb.setProgress(sb.getProgress() + 1);
			}
		}
		return true;
	}
*/

	private void setUpButton() {
		Button button = (Button) findViewById(david.psmlab1.R.id.button);
		try {
			if (player.isPlaying()) {
				button.setBackground(ContextCompat.getDrawable(this,R.drawable.play_on));
			} else {
                button.setBackground(ContextCompat.getDrawable(this,R.drawable.play));
			}
		} catch (Exception e) {

		}
	}

	public void setSurface(SurfaceView sv) {
		this.sv = sv;
	}

}
