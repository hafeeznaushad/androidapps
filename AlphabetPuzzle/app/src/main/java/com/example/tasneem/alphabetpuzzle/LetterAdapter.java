package com.example.tasneem.alphabetpuzzle;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

/**
 * Created by tasneem on 23/02/2015.
 */
public class LetterAdapter extends BaseAdapter {
    private Context mContext;
    private Word w;
    private char[] letters;
    private ViewGroup mParentView = null;
    private boolean mJumbled;

    public LetterAdapter(Context c, Word w, boolean mJumbled) {
        mContext = c;
        this.w = w;
        this.mJumbled = mJumbled;
        letters = this.mJumbled
                ? w.getJumbledLetters()
                : w.getWordLetters();

        //Log.d("LetterAdapter", String.format("%s %d %s",this.w, this.w.length(), mJumbled));
    }

    public int getCount() {
        return w.length();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        mParentView = parent;
        //if (convertView == null){
        //    convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem, null);
        //}
        //LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.gridItem);

        //ll.setBackgroundColor(Color.RED);

        //TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        //txt.setText((char)items[position]);
        LetterView txt = null;
        if (convertView == null) {
            txt = new LetterView(mContext);
            txt.setLayoutParams(new GridView.LayoutParams(85, 85));
            txt.setPadding(8, 8, 8, 8);


        } else {
            txt = (LetterView) convertView;
        }

        //Log.d("DragActivity", String.format("%s %s %s", position, letters[position], this));

        txt.mCellNumber = position;
        txt.mGrid = (GridView) mParentView;
        txt.setAllowDrag(mJumbled);
        txt.setAllowDrop(!mJumbled);
        txt.setOnTouchListener ((View.OnTouchListener) mContext);
        txt.setOnClickListener ((View.OnClickListener) mContext);
        txt.setLetter(letters[position]);
        //txt.setOnLongClickListener ((View.OnLongClickListener) mContext);



        //TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        txt.setText(String.format("%s", letters[position]));
//        txt.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent arg1) {
//                // TODO Auto-generated method stub
//                ClipData data = ClipData.newPlainText("", "");
//                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
//                v.startDrag(data, shadow, v, 0);
//                v.setVisibility(View.INVISIBLE);
//                return false;
//            }
//        });
        //LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.gridItem);

        //ll.setBackgroundColor(Color.RED);



//        if (mJumbled) {
//            txt.setText(String.format("%s", (char) letters[position]));
//        } else {
//            txt.setText(String.format("%s", ' '));
//        }

        return txt;
    }
}