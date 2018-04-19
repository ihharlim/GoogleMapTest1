package com.example.win.googlemaptest1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link testfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link testfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class testfragment extends Fragment {




    public testfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment testfragment.
     */
    // TODO: Rename and change types and number of parameters




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;**/

        View view = inflater.inflate(R.layout.testlayout, container, false);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event




}
