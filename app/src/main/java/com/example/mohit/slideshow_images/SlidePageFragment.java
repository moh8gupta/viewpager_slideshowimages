package com.example.mohit.slideshow_images;

/**
 * Created by mohit on 2/17/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SlidePageFragment extends Fragment{
    static TextView tv;
    static int page_pos=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frlayout, container, false);
      //  tv=(TextView)rootView.findViewById(R.id.txtview);
        Bundle b=getArguments();
        //page_pos=b.getInt("pos");
       // tv.setText(MainActivity.str[page_pos]);
        return rootView;
    }
    public static SlidePageFragment newInstance(int position){
        SlidePageFragment instance= new SlidePageFragment();
        Bundle barg=new Bundle();
        barg.putInt("pos", position);
        instance.setArguments(barg);
        return(instance);
    }

}
