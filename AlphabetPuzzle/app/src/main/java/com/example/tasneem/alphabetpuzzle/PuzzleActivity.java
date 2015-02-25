package com.example.tasneem.alphabetpuzzle;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;


public class PuzzleActivity extends Activity
        implements View.OnTouchListener, View.OnClickListener //  , AdapterView.OnItemClickListener
{

    private DragController mDragController;   // Object that handles a drag-drop sequence. It intersacts with DragSource and DropTarget objects.
    private DragLayer mDragLayer;             // The ViewGroup within which an object can be dragged.
    private int mImageCount = 0;              // The number of images that have been added to screen.
    //private boolean mLongClickStartsDrag = false;   // If true, it takes a long click to start the drag operation.
    MediaPlayer mp        = null;
    private String[] words;
    public static final boolean Debugging = false;   // Use this to see extra toast messages.

    public PuzzleActivity()
    {
        words = new String[10];
        words[0] = "afia";
        words[1] = "apple";
        words[2] = "ball";
        words[3] = "cat";
        words[4] = "doll";
        words[5] = "plate";
        words[6] = "dora";
        words[7] = "dress";
        words[8] = "fish";
        words[9] = "goat";
    }

    public void managerOfSound(boolean success) {
        if (mp != null) {
            mp.reset();
            mp.release();
        }
        mp = MediaPlayer.create(this, success ? R.raw.cheer : R.raw.ohoh);
        mp.start();
    }

    public void nextWord()
    {
        Random r = new Random();
        Word word = new Word(words[r.nextInt(words.length)]);
        GridView sourceGridView = (GridView) findViewById(R.id.lettersView);
        GridView targetGridView = (GridView) findViewById(R.id.answerView);

        sourceGridView.setNumColumns(word.length());
        targetGridView.setNumColumns(word.length());

        sourceGridView.setAdapter (new LetterAdapter(this, word, true));

        targetGridView.setAdapter (new LetterAdapter(this, word, false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        Log.d("TEST", "Test");
        GridView sourceGridView = (GridView) findViewById(R.id.lettersView);
        GridView targetGridView = (GridView) findViewById(R.id.answerView);
//
        if (sourceGridView == null || targetGridView == null) toast ("Unable to find GridView");
        else {
            nextWord();
//            //Word w = new Word("afia");
//            sourceGridView.setAdapter (new LetterAdapter(this, new Word("afia"), true));
//            targetGridView.setAdapter (new LetterAdapter(this, new Word("afia"), false));
            //gridView.setOnItemClickListener (this);
        }
//
        mDragController = new DragController(this);
        mDragLayer = (DragLayer) findViewById(R.id.dragLayer);
        mDragLayer.setDragController (mDragController);
        mDragLayer.setSourceGridView(sourceGridView);
        mDragLayer.setTargetGridView(targetGridView);

        mDragController.setDragListener (mDragLayer);
      // mDragController.addDropTarget (mDragLayer);
//
//        mDeleteZone = (DeleteZone) findViewById (R.id.delete_zone_view);
//
//        // Give the user a little guidance.
//        Toast.makeText(getApplicationContext(),
//                getResources().getString(R.string.instructions),
//                Toast.LENGTH_LONG).show ();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_puzzle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void onItemClick(AdapterView<?> parent, View v, int position, long id)
    {
        LetterView i = (LetterView) v;
        //trace ("onItemClick in view: " + i.mCellNumber);
    }

    /**
     * Handle a long click.
     * If mLongClick only is true, this will be the only way to start a drag operation.
     *
     * @param v View
     * @return boolean - true indicates that the event was handled
     */


    public void onClick(View v)
    {
            // Tell the user that it takes a long click to start dragging.
            toast ("Clicked.");
    }


    public boolean onLongClick(View v)
    {

            //trace ("onLongClick in view: " + v + " touchMode: " + v.isInTouchMode ());

            // Make sure the drag was started by a long press as opposed to a long click.
            // (Note: I got this from the Workspace object in the Android Launcher code.
            //  I think it is here to ensure that the device is still in touch mode as we start the drag operation.)
            if (!v.isInTouchMode()) {
                toast ("isInTouchMode returned false. Try touching the view again.");
                return false;
            }
            return startDrag (v);

    }


    public void onClickAddImage (View v)
    {
        nextWord();
    }


    @Override protected void onResume() {
        super.onResume();

//        View v  = findViewById (R.id.wglxy_bar);
//        if (v != null) {
//            Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
//            //anim1.setAnimationListener (new StartActivityAfterAnimation (i));
//            v.startAnimation (anim1);
//        }
    }

    /**
     * This is the starting point for a drag operation if mLongClickStartsDrag is false.
     * It looks for the down event that gets generated when a user touches the screen.
     * Only that initiates the drag-drop sequence.
     *
     */

    public boolean onTouch (View v, MotionEvent ev)
    {
        //Log.d("Puzzle Activity", "On Touch");
        boolean handledHere = false;

        final int action = ev.getAction();

        // In the situation where a long click is not needed to initiate a drag, simply start on the down event.
        if (action == MotionEvent.ACTION_DOWN) {
            handledHere = startDrag (v);
            if (handledHere) v.performClick ();
        }

        return handledHere;
    }

    /**
     * Start dragging a view.
     *
     */

    public boolean startDrag (View v)
    {
        Log.d("Puzzle Activity", "Starting Drag");
        DragSource dragSource = (DragSource) v;

        // We are starting a drag. Let the DragController handle it.
        mDragController.startDrag (v, dragSource, dragSource, DragController.DRAG_ACTION_MOVE);

        return true;
    }

    /**
     * Show a string on the screen via Toast.
     *
     * @param msg String
     * @return void
     */

    public void toast (String msg)
    {
        Log.d("DragActivity", "toast");
        Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_SHORT).show ();
    } // end toast

    /**
     * Send a message to the debug log. Also display it using Toast if Debugging is true.
     */

    public void trace (String msg)
    {
        Log.d("DragActivity", msg);
        if (!Debugging) return;
        toast (msg);
    }
}
