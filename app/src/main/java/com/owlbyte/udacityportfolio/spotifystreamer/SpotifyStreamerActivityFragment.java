package com.owlbyte.udacityportfolio.spotifystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.owlbyte.udacityportfolio.R;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Artist;
import kaaes.spotify.webapi.android.models.ArtistsPager;

/**
 * A placeholder fragment containing a simple view.
 */
public class SpotifyStreamerActivityFragment extends Fragment implements View.OnKeyListener {

    private static final String LOG_TAG = SpotifyStreamerActivityFragment.class.getName();

    private CustomListAdapter mCustomAdapter;

    public SpotifyStreamerActivityFragment() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        View rootView = inflater.inflate(R.layout.fragment_spotify_streamer, container, false);
        rootView.findViewById(R.id.txt_search_artist).setOnKeyListener(this);

        mCustomAdapter = new CustomListAdapter(new ArrayList<USpotifyObject>());
        mCustomAdapter.SetOnItemClickListener(new CustomListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TracksActivity.class);
                USpotifyObject mSObject = mCustomAdapter.get(position);
                intent.putExtra(Intent.EXTRA_TEXT, "" + mSObject.getId());
                startActivity(intent);
            }
        });
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.artist_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCustomAdapter);

        return rootView;
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view.getId() == R.id.txt_search_artist && keyEvent.getAction() == KeyEvent.ACTION_UP) {
            String inputSearch = String.valueOf(((EditText) view).getText());
            Log.d(LOG_TAG, "Input search: " + inputSearch);
            if (!inputSearch.isEmpty()) {
                new FetchArtistsTask().execute(inputSearch);
            } else {
                mCustomAdapter.clear();
            }
        }
        return false;
    }

    public class FetchArtistsTask extends AsyncTask<String, Void, List<USpotifyObject>> {

        @Override
        protected List<USpotifyObject> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();
            ArtistsPager results = spotify.searchArtists(params[0]);
            List<USpotifyObject> artistList = new ArrayList<>();
            for (Artist artist : results.artists.items) {
                String smallImage="";
                if (artist.images.size() > 0) {
                    // Getting image with lowest resolution just to keep it lightweight and faster
                    smallImage = artist.images.get(artist.images.size()-1).url;
                }
                artistList.add(new USpotifyObject(
                        artist.id,
                        artist.name,
                        smallImage
                ));
            }
            return artistList;
        }

        @Override
        protected void onPostExecute(List<USpotifyObject> result){
            mCustomAdapter.clear();
            mCustomAdapter.addAll(result);
        }
    }
}
