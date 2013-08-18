package org.samcrow.colonynavigator2.data;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class ColonyDrawable extends Drawable {

	/**
	 * Background shape alpha (transparency), 0-255
	 */
	private static final int BG_ALPHA = 100;
	/**
	 * Background color for non-focus, non-visited colonies
	 */
	private static final int BG_NORMAL_COLOR = Color.argb(BG_ALPHA / 2, 100, 100, 100); // gray
	/**
	 * Background color for focus colonies
	 */
	private static final int BG_FOCUS_COLOR = Color.argb(BG_ALPHA, 115, 140, 255); // blue
	/**
	 * Background color for visited colonies, both focus and non-focus
	 */
	private static final int BG_VISITED_COLOR = Color.argb(BG_ALPHA, 77, 240, 101); // green
	
	/**
	 * Background circle radius
	 */
	private static final int BG_RADIUS = 20;
	
	/**
	 * Colony location point color
	 */
	private static final int POINT_COLOR = Color.BLACK;
	/**
	 * Colony number label color
	 */
	private static final int LABEL_COLOR = Color.BLACK;
	/**
	 * Colony location circle radius
	 */
	private static final int POINT_RADIUS = 3;
	
	private final Paint paint = new Paint();
	
	private FontMetrics metrics = new FontMetrics();
	
	private final Colony colony;
	
	public ColonyDrawable(Colony colony) {
		this.colony = colony;
		
		paint.setAntiAlias(true);
		metrics = paint.getFontMetrics();
	}

	@Override
	public void draw(Canvas canvas) {
		
		//Big background circle
		int backgroundColor;
		if(colony.isVisited()) {
			backgroundColor = BG_VISITED_COLOR;
		}
		else {
			if(colony.isFocusColony()) {
				backgroundColor = BG_FOCUS_COLOR;
			}
			else {
				backgroundColor = BG_NORMAL_COLOR;
			}
		}
		//Set paint to fill only, with the required color
		paint.setColor(backgroundColor);
		paint.setStyle(Style.FILL);
		
		//Draw the background at the center position
		canvas.drawCircle(0, 0, BG_RADIUS, paint);
		
		//Draw colony location point
		paint.setColor(POINT_COLOR);
		canvas.drawCircle(0, 0, POINT_RADIUS, paint);
		
		//Draw colony number
		paint.setColor(LABEL_COLOR);
		canvas.drawText(String.valueOf(colony.getId()), 5, metrics.descent, paint);
	}

	@Override
	public int getOpacity() {
		return PixelFormat.TRANSLUCENT;
	}

	@Override
	public void setAlpha(int alpha) {
		//ignore

	}

	@Override
	public void setColorFilter(ColorFilter cf) {
		//ignoring

	}

}
