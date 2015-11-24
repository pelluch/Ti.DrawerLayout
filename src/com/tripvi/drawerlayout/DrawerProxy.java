package com.tripvi.drawerlayout;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.proxy.TiViewProxy;
import org.appcelerator.titanium.util.TiConvert;
import org.appcelerator.titanium.view.TiUIView;

import android.app.Activity;
import android.os.Message;
import android.util.Log;

@Kroll.proxy(creatableInModule = DrawerlayoutModule.class)
public class DrawerProxy extends TiViewProxy {

	private static final int MSG_FIRST_ID = TiViewProxy.MSG_LAST_ID + 1;

	private static final int MSG_TOGGLE_LEFT_VIEW = MSG_FIRST_ID + 100;
	private static final int MSG_TOGGLE_RIGHT_VIEW = MSG_FIRST_ID + 101;
	private static final int MSG_OPEN_LEFT_VIEW = MSG_FIRST_ID + 102;
	private static final int MSG_OPEN_RIGHT_VIEW = MSG_FIRST_ID + 103;
	private static final int MSG_CLOSE_LEFT_VIEW = MSG_FIRST_ID + 104;
	private static final int MSG_CLOSE_RIGHT_VIEW = MSG_FIRST_ID + 105;
	private static final int MSG_ARROW_STATE = MSG_FIRST_ID + 106;

	protected static final int MSG_LAST_ID = MSG_FIRST_ID + 999;
	
	private static final String TAG = "TripviDrawer";
	
	private Drawer drawer;

	public DrawerProxy() {
		super();
	}

	@Override
	public TiUIView createView(Activity activity) {
		drawer = new Drawer(this);
		drawer.getLayoutParams().autoFillsHeight = true;
		drawer.getLayoutParams().autoFillsWidth = true;
		return drawer;
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
		case MSG_TOGGLE_LEFT_VIEW: {
			handleToggleLeftView();
			return true;
		}
		case MSG_OPEN_LEFT_VIEW: {
			handleOpenLeftView();
			return true;
		}
		case MSG_CLOSE_LEFT_VIEW: {
			handleCloseLeftView();
			return true;
		}
		case MSG_TOGGLE_RIGHT_VIEW: {
			handleToggleRightView();
			return true;
		}
		case MSG_OPEN_RIGHT_VIEW: {
			handleOpenRightView();
			return true;
		}
		case MSG_CLOSE_RIGHT_VIEW: {
			handleCloseRightView();
			return true;
		}
		case MSG_ARROW_STATE: {
			handleArrowState((Float) msg.obj);
			return true;
		}
		default: {
			return super.handleMessage(msg);
		}
		}
	}

	private void handleToggleLeftView() {
		drawer.toggleLeftDrawer();
	}

	private void handleOpenLeftView() {
		drawer.openLeftDrawer();
	}

	private void handleCloseLeftView() {
		drawer.closeLeftDrawer();
	}

	private void handleToggleRightView() {
		drawer.toggleRightDrawer();
	}

	private void handleOpenRightView() {
		drawer.openRightDrawer();
	}

	private void handleCloseRightView() {
		drawer.closeRightDrawer();
	}
	
	private void handleArrowState(Float state){
		drawer.setArrowState(state);
	}

	@Kroll.method
	public void toggleLeftWindow(@Kroll.argument(optional = true) Object obj) {
		if (TiApplication.isUIThread()) {
			handleToggleLeftView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_TOGGLE_LEFT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	public void openLeftWindow() {
		if (TiApplication.isUIThread()) {
			handleOpenLeftView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_OPEN_LEFT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	public void closeLeftWindow() {
		if (TiApplication.isUIThread()) {
			handleCloseLeftView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_CLOSE_LEFT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	public void toggleRightWindow(@Kroll.argument(optional = true) Object obj) {
		if (TiApplication.isUIThread()) {
			handleToggleRightView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_TOGGLE_RIGHT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	public void openRightWindow() {
		if (TiApplication.isUIThread()) {
			handleOpenRightView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_OPEN_RIGHT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	public void closeRightWindow() {
		if (TiApplication.isUIThread()) {
			handleCloseRightView();
			return;
		}
		Message message = getMainHandler().obtainMessage(MSG_CLOSE_RIGHT_VIEW);
		message.sendToTarget();
	}

	@Kroll.method
	@Kroll.getProperty
	public boolean getIsLeftDrawerOpen() {
		return drawer.isLeftDrawerOpen();
	}

	@Kroll.method
	@Kroll.getProperty
	public boolean getIsRightDrawerOpen() {
		return drawer.isRightDrawerOpen();
	}

	@Kroll.method
	@Kroll.getProperty
	public boolean getIsLeftDrawerVisible() {
		return drawer.isLeftDrawerVisible();
	}

	@Kroll.method
	@Kroll.getProperty
	public boolean getIsRightDrawerVisible() {
		return drawer.isRightDrawerVisible();
	}

	@Kroll.method
	@Kroll.setProperty
	public void setLeftDrawerWidth(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_LEFT_VIEW_WIDTH, arg);
	}
	
	@Kroll.method
	@Kroll.setProperty
	public void setLeftView(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_LEFT_VIEW, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setRightDrawerWidth(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_RIGHT_VIEW_WIDTH, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setRightView(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_RIGHT_VIEW, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setBackgroundColor(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_BACKGROUND_COLOR, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setToolbarElevation(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_TOOLBAR_ELEVATION, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setCenterView(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_CENTER_VIEW, arg);
	}
	
	@Kroll.method
	public void replaceCenterView(Object arg, boolean backstack) {
		Log.w(TAG, "replaceCenterView is deprecated. Please use setCenterView instead.");
		setPropertyAndFire(Drawer.PROPERTY_CENTER_VIEW, arg);
	}

	@Kroll.method
	@Kroll.setProperty
	public void setDrawerIndicatorEnabled(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_DRAWER_INDICATOR_ENABLED, arg);
	}
    
    	@Kroll.method
    	@Kroll.setProperty
    	public void setDrawerLockMode(Object arg) {
	        setPropertyAndFire(Drawer.PROPERTY_DRAWER_LOCK_MODE, arg);
    	}

	@Kroll.method
	@Kroll.setProperty
	public void setDrawerIndicatorImage(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_DRAWER_INDICATOR_IMAGE, arg);
	}
	
	@Kroll.method
	public void interceptTouchEvent (TiViewProxy view, Boolean disallowIntercept){
		view.getOrCreateView().getOuterView().getParent().requestDisallowInterceptTouchEvent(disallowIntercept);
	}
	
	@Kroll.method
	public void setArrowState (Integer state){
		Message message = getMainHandler().obtainMessage(MSG_ARROW_STATE, TiConvert.toFloat(state, 0));
		message.sendToTarget();
	}
	
	@Kroll.method
	@Kroll.setProperty
	public void setToolbarHidden(Object arg) {
		setPropertyAndFire(Drawer.PROPERTY_HIDE_TOOLBAR, arg);
	}

}
