package david.psmlab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

	boolean buttonPressed = false;
	boolean scroll = false;
	boolean setScroll = true;
	ImageView image;
	VideoView video;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(david.psmlab1.R.layout.activity_main);

	    final GridView gridview = (GridView) findViewById(david.psmlab1.R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));

	    gridview.setOnTouchListener(new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View view, MotionEvent event) {
			    int akcja = event.getAction();
			    switch (akcja) {
				    case MotionEvent.ACTION_UP:
						if (buttonPressed) {
							buttonPressed = false;
							video.stopPlayback();
							video.setVisibility(View.GONE);
							image.setVisibility(View.VISIBLE);
							return true;
						}
						if (scroll) {
							int position = gridview.getFirstVisiblePosition();
							gridview.smoothScrollToPositionFromTop(position, 0, 150);
							scroll = false;
							return true;
						}
						return false;
			    }
			    return false;
		    }
	    });

		gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
				image = (ImageView) view.findViewWithTag("image");
				video = (VideoView) view.findViewWithTag("video");
				image.setVisibility(View.GONE);
				video.setVisibility(View.VISIBLE);
				video.start();
				video.seekTo( 40000 );
				buttonPressed = true;
				return false;
			}
		});

	    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View v,
		                            int position, long id) {
			    /*Toast.makeText(MainActivity.this, "" + position,
					    Toast.LENGTH_SHORT).show();*/

			    Intent in = new Intent(MainActivity.this, ImagePager.class);
			    in.putExtra("id", position);
			    startActivity(in);


			    overridePendingTransition(david.psmlab1.R.anim.fadeout, david.psmlab1.R.anim.fadein);
			    finish();
		    }
	    });

	    gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
		    @Override
		    public void onScrollStateChanged(AbsListView absListView, int state) {
			    // ustawienie grida w taki sposób, aby kafel nie był przecięty w połowie
			    scroll = state != SCROLL_STATE_IDLE;
		    }

		    @Override
		    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

		    }
	    });
    }

	@Override
	public void onBackPressed() {
		finish();
	}

}
