package com.dev407.officedictionary2.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev407.officedictionary2.R;
import com.dev407.officedictionary2.models.dummy.DummyContent;
import com.dev407.officedictionary2.models.dummy.DummyContent.DummyItem;
import com.dev407.officedictionary2.views.adapters.TrendingRecyclerViewAdapter;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnTrendingFragmentInteractionListener}
 * interface.
 */
public class TrendingFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_SECTION_NUMBER = "section_number";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnTrendingFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TrendingFragment() {
    }

    /**
     * Supply no of columns to be used for new instance.
     *
     * @param columnCount Number of columns in the grid. If 1, its linear layout
     * @return Trending Fragment
     */
    public static TrendingFragment newInstance(int columnCount) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * newInstance Overloaded function, to add section number too
     *
     * @param columnCount Number of columns in the grid. If 1, its linear layout
     * @param sectionNumber section number of the fragment
     * @return Trending Fragment
     */
    public static TrendingFragment newInstance(int columnCount, int sectionNumber) {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new TrendingRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTrendingFragmentInteractionListener) {
            mListener = (OnTrendingFragmentInteractionListener) context;
            // Send the event to the host activity
            mListener.onTrendingFragmentInteraction(new DummyItem("1trending", "content1trending", "details1"));

        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTrendingFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTrendingFragmentInteractionListener {
        // TODO: Update argument type and name
        void onTrendingFragmentInteraction(DummyItem item);
    }
}
