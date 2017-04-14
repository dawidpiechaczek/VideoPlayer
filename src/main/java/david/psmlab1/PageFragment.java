package david.psmlab1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;


public class PageFragment extends android.support.v4.app.Fragment {
	// Store instance variables
	private int page;
	private SurfaceView sv;

	// newInstance constructor for creating fragment with arguments
	public static PageFragment newInstance(int page) {
		PageFragment fragmentFirst = new PageFragment();
		Bundle args = new Bundle();
		args.putInt("page", page);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("page", 0);
	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(david.psmlab1.R.layout.pager_layout, container, false);

		sv = (SurfaceView) view.findViewById(david.psmlab1.R.id.surfaceView);
		Drawable img = ResourcesCompat.getDrawable(container.getResources(), StaticData.images[page], null);
		sv.setBackground(img);
		sv.setId(1000000 + page);
		return view;
	}
}