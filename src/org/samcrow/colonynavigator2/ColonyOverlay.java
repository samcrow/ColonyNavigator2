package org.samcrow.colonynavigator2;

import org.samcrow.colonynavigator2.data.Colony;
import org.samcrow.colonynavigator2.data.ColonyList;

import android.graphics.Canvas;
import android.graphics.Point;

import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.Overlay;
import com.mapquest.android.maps.Projection;

/**
 * An overlay that displays colonies on the map
 * @author Sam Crow
 *
 */
public class ColonyOverlay extends Overlay {

	/**
	 * The colonies to draw
	 */
	private final ColonyList colonies;
	
	private Point currentScreenCoords = new Point();
	
	public ColonyOverlay(ColonyList colonies) {
		this.colonies = colonies;
	}


	@Override
	public void draw(Canvas canvas, MapView view, boolean shadow) {
		if(shadow) {
			return;
		}
		if(view.getZoomLevel() < 15) {
			return;
		}
		if(colonies == null) {
			System.out.println("Null colonies");
			return;
		}
		if(colonies.size() == 0) {
			System.out.println("No colonies to draw");
			return;
		}
		final Projection projection = view.getProjection();
		
		for(final Colony colony : colonies) {
			//Get the projection of the colony position onto the map view
			GeoPoint point = colony.getLatLon();
			projection.toPixels(point, currentScreenCoords);
			
//			System.out.println("Painting colony at LL "+point.getLatitudeE6()+", "+point.getLongitudeE6()+" mapped to "+currentScreenCoords.x+", "+currentScreenCoords.y);
//			System.out.println("Drawing drawable "+colony.getDrawable().toString());
			
			
			 Overlay.drawAt(canvas, colony.getDrawable(), currentScreenCoords.x, currentScreenCoords.y, false);
		}
		
	}


}
