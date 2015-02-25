package com.example.tasneem.alphabetpuzzle;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tasneem on 23/02/2015.
 */
public class LetterView extends TextView
implements DropTarget, DragSource
{

    public int mCellNumber = -1;
    public GridView mGrid;
    private boolean mAllowDrag;
    private boolean mAllowDrop;
    private char letter;
    public LetterView (Context context) {
        super (context);
    }
    public LetterView (Context context, AttributeSet attrs) {
        super (context, attrs);
    }
    public LetterView (Context context, AttributeSet attrs, int style) {
        super (context, attrs, style);

    }


    public boolean allowDrag () {
        // There is something to drag if the cell is not empty.
        return mAllowDrag;
    }

    public void setLetter(char c) {
        letter = c;
    }

    public char getLetter() {
        return letter;
    }

    public void setDragController (DragController dragger)
    {
        // Do nothing. We do not need to know the controller object.
    }


    public void onDropCompleted (View target, boolean success)
    {
        // If the drop succeeds, the image has moved elsewhere.
        // So clear the image cell.
        if (success) {
            setAllowDrag(false);
            setText("-");
        }

        PuzzleActivity activity = (PuzzleActivity) getContext();
        activity.managerOfSound(success);
    }

/**
 */
// DropTarget interface implementation

    /**
     * Handle an object being dropped on the DropTarget.
     * This is the where the drawable of the dragged view gets copied into the ImageCell.
     *
     * @param source DragSource where the drag started
     * @param x X coordinate of the drop location
     * @param y Y coordinate of the drop location
     * @param xOffset Horizontal offset with the object being dragged where the original
     *          touch happened
     * @param yOffset Vertical offset with the object being dragged where the original
     *          touch happened
     * @param dragView The DragView that's being dragged around on screen.
     * @param dragInfo Data associated with the object being dragged
     *
     */
    public void onDrop(DragSource source, int x, int y, int xOffset, int yOffset,
                       DragView dragView, Object dragInfo)
    {
        // Mark the cell so it is no longer empty.
        //mEmpty = false;
        //int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
        //setBackgroundResource (bg);

        // The view being dragged does not actually change its parent and switch over to the ImageCell.
        // What we do is copy the drawable from the source view.
        LetterView sourceView = (LetterView) source;
        this.setText(sourceView.getText());
        this.setBackgroundColor(getResources().getColor(R.color.red));

        //Drawable d = sourceView.getDrawable ();
        //if (d != null) {
        //   this.setImageDrawable (d);
        //}

        // toast ("onDrop cell " + mCellNumber);

    }

    /**
     * React to a dragged object entering the area of this DropSpot.
     * Provide the user with some visual feedback.
     */
    public void onDragEnter(DragSource source, int x, int y, int xOffset, int yOffset,
                            DragView dragView, Object dragInfo)
    {
        //int bg = mEmpty ? R.color.cell_empty_hover : R.color.cell_filled_hover;
        //setBackgroundResource (bg);
    }

    /**
     * React to something being dragged over the drop target.
     */
    public void onDragOver(DragSource source, int x, int y, int xOffset, int yOffset,
                           DragView dragView, Object dragInfo)
    {
    }

    /**
     * React to a drag
     */
    public void onDragExit(DragSource source, int x, int y, int xOffset, int yOffset,
                           DragView dragView, Object dragInfo)
    {
        //int bg = mEmpty ? R.color.cell_empty : R.color.cell_filled;
        //setBackgroundResource (bg);
    }

    /**
     * Check if a drop action can occur at, or near, the requested location.
     * This may be called repeatedly during a drag, so any calls should return
     * quickly.
     *
     * @param source DragSource where the drag started
     * @param x X coordinate of the drop location
     * @param y Y coordinate of the drop location
     * @param xOffset Horizontal offset with the object being dragged where the
     *            original touch happened
     * @param yOffset Vertical offset with the object being dragged where the
     *            original touch happened
     * @param dragView The DragView that's being dragged around on screen.
     * @param dragInfo Data associated with the object being dragged
     * @return True if the drop will be accepted, false otherwise.
     */
    public boolean acceptDrop(DragSource source, int x, int y, int xOffset, int yOffset,
                              DragView dragView, Object dragInfo)
    {
        // An ImageCell accepts a drop if it is empty and if it is part of a grid.
        // A free-standing ImageCell does not accept drops.
        LetterView view = (LetterView) source;

        return mAllowDrop && view.getLetter() == getLetter();
    }

    /**
     * Estimate the surface area where this object would land if dropped at the
     * given location.
     *
     * @param source DragSource where the drag started
     * @param x X coordinate of the drop location
     * @param y Y coordinate of the drop location
     * @param xOffset Horizontal offset with the object being dragged where the
     *            original touch happened
     * @param yOffset Vertical offset with the object being dragged where the
     *            original touch happened
     * @param dragView The DragView that's being dragged around on screen.
     * @param dragInfo Data associated with the object being dragged
     * @param recycle {@link android.graphics.Rect} object to be possibly recycled.
     * @return Estimated area that would be occupied if object was dropped at
     *         the given location. Should return null if no estimate is found,
     *         or if this target doesn't provide estimations.
     */
    public Rect estimateDropLocation(DragSource source, int x, int y, int xOffset, int yOffset,
                                     DragView dragView, Object dragInfo, Rect recycle)
    {
        return null;
    }

/**
 */
// Other Methods

    /**
     * Return true if this cell is empty.
     * If it is, it means that it will accept dropped views.
     * It also means that there is nothing to drag.
     *
     * @return boolean
     */

    public void setAllowDrop(boolean v)
    {
        mAllowDrop = v;
    }

    public void setAllowDrag(boolean v)
    {
        mAllowDrag = v;
    }

    /**
     * Call this view's onClick listener. Return true if it was called.
     * Clicks are ignored if the cell is empty.
     *
     * @return boolean
     */

    public boolean performClick ()
    {
    //    if (!mEmpty) return super.performClick ();
        return false;
    }

    /**
     * Call this view's onLongClick listener. Return true if it was called.
     * Clicks are ignored if the cell is empty.
     *
     * @return boolean
     */

    public boolean performLongClick ()
    {
    //    if (!mEmpty) return super.performLongClick ();
        return false;
    }

    /**
     * Show a string on the screen via Toast if DragActivity.Debugging is true.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast

}
