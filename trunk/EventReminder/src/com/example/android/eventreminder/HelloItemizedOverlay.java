package com.example.android.eventreminder;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	private Drawable mdefaultMarker;
	
	public HelloItemizedOverlay(Drawable defaultMarker) {
		  super(boundCenterBottom(defaultMarker));
		  mdefaultMarker = defaultMarker;
		// TODO Auto-generated constructor stub
	}
	
	public void addOverlay(OverlayItem overlay) {
		mOverlays.clear();
	    mOverlays.add(overlay);
	    populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	public HelloItemizedOverlay(Drawable defaultMarker, Context context) 
	{
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		mdefaultMarker = defaultMarker;
	}
	
	//@Override
	//protected boolean onTap(int index) {
		//System.out.println("OnTap");
/*	  OverlayItem item = mOverlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();*/
	//  return true;
//	}
	
	@Override
	public boolean onTap(GeoPoint p, MapView mapView)
	{
		mContext = mapView.getContext();
		System.out.println("OnTap1");
        Toast.makeText(mapView.getContext(), 
                p.getLatitudeE6() / 1E6 + "," + 
                p.getLongitudeE6() /1E6 , 
                Toast.LENGTH_SHORT).show();
		
    	List<Overlay> mapOverlays = mapView.getOverlays();
   	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay( mdefaultMarker );    
	    OverlayItem overlayitem = new OverlayItem(p, "Hi!", "I'm in SFSU!");
	        	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.clear();
	    mapOverlays.add(itemizedoverlay);
	    mapView.invalidate();

		/*AlertDialog.Builder dialog = new AlertDialog.Builder(mapView.getContext());
		dialog.setTitle(overlayitem.getTitle());
		dialog.setMessage(overlayitem.getSnippet());
		dialog.show(); */
	    
	    AlertDialog.Builder locationDialog = new AlertDialog.Builder(mapView.getContext());
	    locationDialog.setMessage("Are you sure you want to save this location?")
	    .setTitle("Are you sure?")
	    .setCancelable(false) 
	    .setPositiveButton("Yes", 
	    new DialogInterface.OnClickListener() { 
	    public void onClick(DialogInterface dialog, int id) {
	    // Perform some action such as saving the location
	    	Intent myIntent = new Intent(mContext, LocationPreferences.class);
	    	mContext.startActivity(myIntent);
	    	dialog.cancel();
	    }
	    })
	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int id) {
	    dialog.cancel(); 
	    }
	    });
	    locationDialog.create().show();
		  return true;
	}
	
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {   
/*        if (event.getAction() == 1) {                
            GeoPoint p = mapView.getProjection().fromPixels(
                (int) event.getX(),
                (int) event.getY());
                Toast.makeText(mapView.getContext(), 
                    p.getLatitudeE6() / 1E6 + "," + 
                    p.getLongitudeE6() /1E6 , 
                    Toast.LENGTH_SHORT).show();
                
        	List<Overlay> mapOverlays = mapView.getOverlays();
       	    HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay( mdefaultMarker );    
    	    OverlayItem overlayitem = new OverlayItem(p, "Hi!", "I'm in SFSU!");
    	        	    
    	    itemizedoverlay.addOverlay(overlayitem);
    	    mapOverlays.clear();
    	    mapOverlays.add(itemizedoverlay);
    	    mapView.invalidate();
    	    
        }                           */ 
        return false;
    }
	
}