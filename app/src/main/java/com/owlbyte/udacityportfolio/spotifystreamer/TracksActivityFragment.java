package com.owlbyte.udacityportfolio.spotifystreamer;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.owlbyte.udacityportfolio.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;
import kaaes.spotify.webapi.android.models.Tracks;

/**
 * A placeholder fragment containing a simple view.
 */
public class TracksActivityFragment extends Fragment {

    private CustomListAdapter mCustomAdapter;

    private List<USpotifyObject> listResult;

    public TracksActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_tracks, container, false);

        mCustomAdapter = new CustomListAdapter(new ArrayList<USpotifyObject>());
        mCustomAdapter.SetOnItemClickListener(new CustomListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(
                        getActivity(),
                        "This will play track \"" + mCustomAdapter.get(position).getName() + "\"",
                        Toast.LENGTH_SHORT).
                            show();
            }
        });

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.track_list);
        mLayoutManager = new LinearLayoutManager(getActivity());
        ((LinearLayoutManager)mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mCustomAdapter);

        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            String mArtist = intent.getStringExtra(Intent.EXTRA_TEXT);
            new FetchTracksTask().execute(mArtist);
        }
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null && savedInstanceState.containsKey("artistsKey")) {
            listResult = savedInstanceState.getParcelableArrayList("artistsKey");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        fillSpotifyObjList();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("artistsKey", (ArrayList)listResult);
        super.onSaveInstanceState(outState);
    }

    public class FetchTracksTask extends AsyncTask<String, Void, List<USpotifyObject>> {

        @Override
        protected List<USpotifyObject> doInBackground(String... params) {
            if (params.length == 0) {
                return null;
            }
            SpotifyApi api = new SpotifyApi();
            SpotifyService spotify = api.getService();
            Map q = new HashMap();
            q.put("country", "US");
            Tracks result = spotify.getArtistTopTrack(params[0], q);
            List<USpotifyObject> trackList = new ArrayList<>();
            for (Track track : result.tracks) {
                String smallImage="", largeImage = "";
                if (track.album.images.size() > 0) {
                    smallImage = track.album.images.get(track.album.images.size()-1).url;
                    largeImage = smallImage;
                    for (Image image : track.album.images){
                        if (image.width == 200) {
                            smallImage = image.url;
                        } else if (image.width == 640) {
                            largeImage = image.url;
                        }
                    }
                }
                trackList.add(new USpotifyObject(
                        track.id,
                        track.name,
                        smallImage,
                        largeImage,
                        track.album.name
                ));
            }
            return trackList;
        }

        @Override
        protected void onPostExecute(List<USpotifyObject> result){
            listResult = result;
            fillSpotifyObjList();
        }
    }

    private void fillSpotifyObjList() {
        if (listResult != null) {
            mCustomAdapter.clear();
            if (!listResult.isEmpty()) {
                mCustomAdapter.addAll(listResult);
            } else {
                Toast.makeText(getActivity(), R.string.artist_notfound, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
