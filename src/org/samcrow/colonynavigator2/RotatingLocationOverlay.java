package org.samcrow.colonynavigator2;

import android.content.Context;

import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;

/**
 * An overlay that rotates the map to keep the map north aligned with true north
 * @author Sam Crow
 *
 */
public class RotatingLocationOverlay extends MyLocationOverlay {

	private final MapView map;
	
	private boolean rotationEnabled = false;
	
	public RotatingLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		map = mapView;
	}

	/* (non-Javadoc)
	 * @see com.mapquest.android.maps.MyLocationOverlay#onSensorChanged(int, float[])
	 */
	@Override
	public void onSensorChanged(int sensor, float[] values) {
		super.onSensorChanged(sensor, values);
		if(rotationEnabled) {
			//Value 0 of values is the heading, from -180 to 180
			map.getController().setMapRotation(-values[0]);
		}
	}
	
	/**
	 * Determine if this instance should rotate the map view to keep map north matching physical true north
	 * @param enabled
	 */
	public void setRotationEnabled(boolean enabled) {
		rotationEnabled = enabled;
	}

}
