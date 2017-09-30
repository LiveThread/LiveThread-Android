package guru.nickthompson.livethread.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import guru.nickthompson.livethread.R;
import guru.nickthompson.livethread.adapters.SubredditAdapter;
import guru.nickthompson.redditapi.Subreddit;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SelectSubredditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SelectSubredditFragment extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    private List<Subreddit> subreddits;
    private ListView mListView;

    public SelectSubredditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Setup the suggested subreddits.
     */
    private void setupSuggestedSubreddits() {
        subreddits = new ArrayList<Subreddit>();
        /// TODO: populate
        subreddits.add(new Subreddit("WorldNews", "Major news from around the world"));
        subreddits.add(new Subreddit("NFL", "NFL takes on Reddit"));
        subreddits.add(new Subreddit("AskReddit", "What does reddit think?"));

        SubredditAdapter adapter = new SubredditAdapter(getContext(), subreddits);
        mListView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_subreddit, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        view.findViewById(R.id.b_select_subreddit).setOnClickListener(this);

        // create list view of suggested subs
        mListView = (ListView) getView().findViewById(R.id.lv_select_subreddit_popular);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Subreddit subreddit = subreddits.get(position);
                mListener.onSubredditClick(subreddit.getName());
            }
        });
        setupSuggestedSubreddits();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_select_subreddit:
                EditText etSubreddit = (EditText) getView().findViewById(R.id.et_select_subreddit);
                String subredditName = etSubreddit.getText().toString();

                if (subredditName.equals("")) {
                    Toast.makeText(getContext(), "Enter a valid subreddit.", Toast.LENGTH_SHORT).show();
                    return;
                }
                mListener.onSubredditClick(subredditName);

                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        /**
         * Open the subreddit.
         *
         * @param subredditName name of the subreddit.
         */
        void onSubredditClick(String subredditName);
    }
}