package devishankar.com.github.devishankarcricbuzztest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import devishankar.com.github.devishankarcricbuzztest.R;
import devishankar.com.github.devishankarcricbuzztest.adapters.MatchListAdapter;
import devishankar.com.github.devishankarcricbuzztest.models.MatchModel;


public class MatchFragment extends Fragment {
    private static final String ARG_CONTENT = "map";
    private static final String ARG_TITLE = "title";
    private static final String TAG = MatchFragment.class.getSimpleName();


    public MatchFragment() {
    }

    public static MatchFragment newInstance(String cate, LinkedHashMap<Integer, ArrayList<MatchModel>> content) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TITLE, cate);
        args.putSerializable(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cate, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "Title " + getArguments().getString(ARG_TITLE));
            RecyclerView rv = (RecyclerView) view.findViewById(R.id.rvMatch);
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(manager);

            LinkedHashMap<Integer, ArrayList<MatchModel>> matchMap = (LinkedHashMap<Integer, ArrayList<MatchModel>>) getArguments().getSerializable(ARG_CONTENT);

            ArrayList<Object> arr = new ArrayList<>();
            if (matchMap != null) {
                HashMap<Object, Boolean> temp = new HashMap<>();
                for (Map.Entry<Integer, ArrayList<MatchModel>> item : matchMap.entrySet()) {
                    ArrayList<MatchModel> items = item.getValue();
                    Collections.sort(items);

                    Date date = new Date(item.getKey() * 1000);
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d", Locale.ENGLISH);
                    String formattedDate = sdf.format(date);

                    if (temp.get(formattedDate) == null) {
                        temp.put(formattedDate, true);
                        arr.add(item.getKey());
                    }

                    for (int i = 0; i < items.size(); i++) {
                        if (temp.get(formattedDate + "_" + items.get(i).getSeriesId()) == null) {
                            temp.put(formattedDate + "_" + items.get(i).getSeriesId(), true);
                            arr.add(items.get(i).getSeriesDesc());
                        }
                        arr.add(items.get(i));
                    }
                }
                Log.d(TAG, "arr size " + arr.size());
                Log.d(TAG, "arr string " + arr.toString());
            }

            MatchListAdapter adapter = new MatchListAdapter(arr);
            rv.setAdapter(adapter);
            rv.setHasFixedSize(true);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
