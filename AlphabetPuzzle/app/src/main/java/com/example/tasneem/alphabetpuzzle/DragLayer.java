package com.example.tasneem.alphabetpuzzle;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by tasneem on 23/02/2015.
 */
public class DragLayer extends RelativeLayout
        implements DragListener {

    DragController mDragController;
    GridView mSourceGridView;
    GridView mTargetGridView;

    /**
     * Used to create a new DragLayer from XML.
     *
     * @param context The application's context.
     * @param attrs The attribtues set containing the Workspace's customization values.
     */
    public DragLayer (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDragController(DragController controller) {
        mDragController = controller;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return mDragController.dispatchKeyEvent(event) || super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragController.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return mDragController.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return mDragController.dispatchUnhandledMove(focused, direction);
    }

    /**
     * Get the value of the GridView property.
     *
     * @return GridView
     */

    public GridView getSourceGridView ()
    {
        //if (mGridView == null) {}
        return mSourceGridView;
    } // end getGridView

    public GridView getTargetGridView ()
    {
        //if (mGridView == null) {}
        return mTargetGridView;
    } // end getGridView


    /**
     * Set the value of the GridView property.
     *
     * @param newValue GridView
     */

    public void setSourceGridView (GridView newValue)
    {
        mSourceGridView= newValue;
    } // end setGridView


    public void setTargetGridView (GridView newValue)
    {
        mTargetGridView= newValue;
    } // end setGridView

/* end Property GridView */

/**
 */
// DragListener Interface Methods

    /**
     * A drag has begun.
     *
     * @param source An object representing where the drag originated
     * @param info The data associated with the object that is being dragged
     * @param dragAction The drag action: either {@link DragController#DRAG_ACTION_MOVE}
     *        or {@link DragController#DRAG_ACTION_COPY}
     */

    public void onDragStart(DragSource source, Object info, int dragAction)
    {
        // We are starting a drag.
        // Build up a list of DropTargets from the child views of the GridView.
        // Tell the drag controller about them.

        if (mTargetGridView != null) {
            int numVisibleChildren = mTargetGridView.getChildCount();
            for ( int i = 0; i < numVisibleChildren; i++ ) {
                DropTarget view = (DropTarget) mTargetGridView.getChildAt (i);
                mDragController.addDropTarget (view);
            }
        }

    }

    /**
     * A drag-drop operation has eneded.
     */

    public void onDragEnd()
    {
        mDragController.removeAllDropTargets ();
    }

/**
 */
// Other Methods

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        // if (!DragActivity.Debugging) return;
        Toast.makeText (getContext (), msg, Toast.LENGTH_SHORT).show ();
    } // end toast


}
