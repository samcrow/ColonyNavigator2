package org.samcrow.colonynavigator2;


import org.samcrow.data.provider.ColonyProvider;
import org.samcrow.data.provider.MemoryCardDataProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapActivity;
import com.mapquest.android.maps.MapView;

public class MainActivity extends MapActivity {

	private MapView map;
	
	
	private final ColonyProvider provider = new MemoryCardDataProvider();
	
	private RotatingLocationOverlay locationOverlay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		map = (MapView) findViewById(R.id.map_view);
		map.setBuiltInZoomControls(true);
		map.getController().setCenter(new GeoPoint(31.872176, -109.040983));
		map.getController().setZoom(18);
		
		ColonyOverlay colonyOverlay = new ColonyOverlay(provider.getColonies());
		map.getOverlays().add(colonyOverlay);
		
		locationOverlay = new RotatingLocationOverlay(this, map);
		map.getOverlays().add(locationOverlay);
		locationOverlay.enableMyLocation();
		locationOverlay.enableCompass();
		
		
	}
	
	public void onRotateToggled(View view) {
		boolean enabled = ((ToggleButton) view).isChecked();
		locationOverlay.setRotationEnabled(enabled);
	}

	/* (non-Javadoc)
	 * @see com.mapquest.android.maps.MapActivity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		locationOverlay.disableCompass();
		locationOverlay.disableMyLocation();
	}



	/* (non-Javadoc)
	 * @see com.mapquest.android.maps.MapActivity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		locationOverlay.enableMyLocation();
		locationOverlay.enableCompass();
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	

	/* (non-Javadoc)
	 * @see android.app.Activity#onRestoreInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		map.onRestoreInstanceState(savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		map.onSaveInstanceState(outState);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}



	/* (non-Javadoc)
	 * @see com.mapquest.android.maps.MapActivity#isLocationDisplayed()
	 */
	@Override
	protected boolean isLocationDisplayed() {
		return locationOverlay.isMyLocationEnabled();
	}
}
