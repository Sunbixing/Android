package com.example.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollViewY extends ScrollView {

	GestureDetector gestureDetector;

	@SuppressWarnings("deprecation")
	public ScrollViewY(Context context, AttributeSet attrs) {
		super(context, attrs);
		gestureDetector = new GestureDetector(new Yscroll());
		setFadingEdgeLength(0);
	}

	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev)
				&& gestureDetector.onTouchEvent(ev);
	}

	class Yscroll extends GestureDetector.SimpleOnGestureListener {

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			// ������ָ�����ľ���
			if (Math.abs(distanceY) >= Math.abs(distanceX)) {
				return true;
			}
			return false;
		}
	}
}