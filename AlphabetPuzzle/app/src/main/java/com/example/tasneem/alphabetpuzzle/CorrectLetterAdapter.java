package com.example.tasneem.alphabetpuzzle;

import android.content.Context;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by tasneem on 23/02/2015.
 */
public class CorrectLetterAdapter extends BaseAdapter {
    private Context mContext;
    private Word w;
    private char[] letters;
    public CorrectLetterAdapter(Context c, Word w) {
        mContext = c;
        this.w = w;
        letters = new char[w.length()];
    }

    public int getCount() {
        return w.length();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        //if (convertView == null){
        //    convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem, null);
        //}
        //LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.gridItem);

        //ll.setBackgroundColor(Color.RED);

        //TextView txt= (TextView) convertView.findViewById(R.id.textView1);
        //txt.setText((char)items[position]);
        TextView txt;
        if (convertView == null){
            txt = new TextView(mContext);
            txt.setLayoutParams(new GridView.LayoutParams(85, 85));
            txt.setPadding(8, 8, 8, 8);
        } else {
            txt = (TextView) convertView;
        }


        //LinearLayout ll=(LinearLayout) convertView.findViewById(R.id.gridItem);
        txt.setText("-");
        //ll.setBackgroundColor(Color.RED);
        if ((char)letters[position] != 0) {
            txt.setText(String.format("%s", (char)letters[position]));
        } else {
            txt.setText("-");
        }


        txt.setOnDragListener(new View.OnDragListener() {

            @Override
            public boolean onDrag(View v, DragEvent event) {
                // TODO Auto-generated method stub
                final int action = event.getAction();

                switch(action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;

                    case DragEvent.ACTION_DROP:{

                        View view = (View) event.getLocalState();
                        TextView draggedText = (TextView) view;
                        TextView txt2 = (TextView) v;
                        txt2.setText(draggedText.getText());
//                        ViewGroup owner = (ViewGroup) view.getParent();
//                        owner.removeView(view);
//                        LinearLayout container = (LinearLayout) v;
//                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        return(true);
                    }

                    case DragEvent.ACTION_DRAG_ENDED:{
                        View view = (View) event.getLocalState();
                        view.setVisibility(View.VISIBLE);
                        return(true);

                    }

                    default:
                        break;
                }
                return true;
            }});

        return txt;
    }

}
