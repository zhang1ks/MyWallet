package com.example.zhang1ks.mywallet;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

/**
 * Created by yuge on 11/14/2016.
 */

public class TabFg1 extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fg1_tab, container, false);

        //Set LongClickListener and DragListener to all image views of payment cards
        view.findViewById(R.id.card1).setOnLongClickListener(listenClick);
        view.findViewById(R.id.card1).setOnDragListener(listenDrag);
        view.findViewById(R.id.card2).setOnLongClickListener(listenClick);
        view.findViewById(R.id.card2).setOnDragListener(listenDrag);
        view.findViewById(R.id.card3).setOnLongClickListener(listenClick);
        view.findViewById(R.id.card3).setOnDragListener(listenDrag);
        view.findViewById(R.id.card4).setOnLongClickListener(listenClick);
        view.findViewById(R.id.card4).setOnDragListener(listenDrag);
        view.findViewById(R.id.card5).setOnLongClickListener(listenClick);
        view.findViewById(R.id.card5).setOnDragListener(listenDrag);

        return view;
    }

    //Define LongClickListener
    View.OnLongClickListener listenClick = new View.OnLongClickListener()
    {
        @Override
        public boolean onLongClick(View v)
        {

            ClipData data = ClipData.newPlainText("","");
            DragShadow dragShadow = new DragShadow(v);//Shadow generated once long pressed.

            v.startDrag(data, dragShadow, v, 0);

            return false;
        }
    };

    //Define DragListener
    View.OnDragListener listenDrag = new View.OnDragListener() {

        @Override
        public boolean onDrag(View v, DragEvent event)
        {
            int dragEvent = event.getAction();

            switch (dragEvent)
            {
                //Once an item dragged...log it.
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.i("Drag Event", "Entered");
                    break;

                //Cancel drag operation and log it.
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.i("Drag Event", "Exited");
                    break;

                //Drag an item onto another and drop to replace it.
                //Make dragged item and target item take the place of each other.
                case DragEvent.ACTION_DROP:
                    ImageView target = (ImageView) v;

                    ImageView dragged = (ImageView) event.getLocalState();

                    Drawable target_draw = target.getDrawable();
                    Drawable dragged_draw = dragged.getDrawable();

                    dragged.setImageDrawable(target_draw);
                    target.setImageDrawable(dragged_draw);

                    break;

            }

            return true;
        }
    };

    //Build the shadow animation while an item is dragged
    private class DragShadow extends View.DragShadowBuilder
    {
        ColorDrawable greyBox;

        public DragShadow(View view)
        {
            super(view);
            greyBox = new ColorDrawable(Color.LTGRAY);
        }

        @Override
        public void onDrawShadow(Canvas canvas)
        {
            greyBox.draw(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize,
                                           Point shadowTouchPoint)
        {
            View v = getView();

            int height =  v.getHeight();
            int width =  v.getWidth();

            greyBox.setBounds(0, 0, width, height);

            //Shadow has the same size of dragged item.
            shadowSize.set(width, height);

            //Set touch point of the shadow to its center
            shadowTouchPoint.set(width/2, height/2);
        }
    }

}