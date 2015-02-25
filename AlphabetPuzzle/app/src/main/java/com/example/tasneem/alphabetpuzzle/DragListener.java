package com.example.tasneem.alphabetpuzzle;

import android.view.View;

/**
 * Created by tasneem on 23/02/2015.
 */
public interface DragListener {

        /**
         * A drag has begun
         *
         * @param source An object representing where the drag originated
         * @param info The data associated with the object that is being dragged
         * @param dragAction The drag action: either {@link DragController#DRAG_ACTION_MOVE}
         *        or {@link DragController#DRAG_ACTION_COPY}
         */
        public void onDragStart(DragSource source, Object info, int dragAction);

        /**
         * The drag has eneded
         */
        public void onDragEnd();

        //public void onDropCompleted(View mDragSource, View dropTarget);

}
