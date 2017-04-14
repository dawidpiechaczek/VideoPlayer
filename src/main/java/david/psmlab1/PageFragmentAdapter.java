package david.psmlab1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class PageFragmentAdapter extends FragmentPagerAdapter {
	private static int NUM_ITEMS = StaticData.videos.length;

	public PageFragmentAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	// Returns total number of pages
	@Override
	public int getCount() {
		return NUM_ITEMS;
	}

	// Returns the fragment to display for that page
	@Override
	public Fragment getItem(int position) {
		return PageFragment.newInstance(position);
	}

	// Returns the page title for the top indicator
	@Override
	public CharSequence getPageTitle(int position) {
		return "Page " + position;
	}
}