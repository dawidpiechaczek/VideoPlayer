package david.psmlab1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;


public class PageAdapter extends PagerAdapter {
	Context mContext;
	LayoutInflater mLayoutInflater;
	public static boolean state = true;

	public PageAdapter(Context context) {
		mContext = context;
		mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return StaticData.videos.length;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View itemView = mLayoutInflater.inflate(david.psmlab1.R.layout.pager_layout, container, false);

		SurfaceView sv = (SurfaceView) itemView.findViewById(david.psmlab1.R.id.surfaceView);
		Drawable img = ResourcesCompat.getDrawable(container.getResources(), StaticData.images[position], null);
		sv.setBackground(img);

		sv.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

		container.addView(itemView);
		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((LinearLayout) object);
	}

	// references to our images

}
