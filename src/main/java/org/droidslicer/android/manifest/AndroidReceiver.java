package org.droidslicer.android.manifest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.ibm.wala.types.TypeReference;

public class AndroidReceiver extends AndroidAppComponent implements AndroidComponentWithIntentFilter
{
	private final Collection<AndroidIntentFilter> mIntentFilters = new ArrayList<AndroidIntentFilter>();
	public AndroidReceiver(TypeReference typeRef)
	{
		super(typeRef);
	}
	public void addIntentFilter(AndroidIntentFilter filter)
	{
		mIntentFilters.add(filter);
	}
	public Iterator<AndroidIntentFilter> intentFilterIterator()
	{
		return mIntentFilters.iterator();
	}
}
