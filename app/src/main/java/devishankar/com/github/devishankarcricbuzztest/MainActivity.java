package devishankar.com.github.devishankarcricbuzztest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import devishankar.com.github.devishankarcricbuzztest.adapters.CategoriesPagerAdapter;
import devishankar.com.github.devishankarcricbuzztest.fragments.MatchFragment;
import devishankar.com.github.devishankarcricbuzztest.models.MatchModel;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TabLayout tabs;
    private ViewPager vpCategories;
    private CategoriesPagerAdapter adapter;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Schedules");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        tabs = (TabLayout) findViewById(R.id.tabs);
        vpCategories = (ViewPager) findViewById(R.id.vpCategories);
        adapter = new CategoriesPagerAdapter(getSupportFragmentManager());
        vpCategories.setAdapter(adapter);
        try {
            processJson(getScheduleJson());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processJson(String json) throws JSONException {
        JSONObject sch = new JSONObject(json);
        JSONArray list = sch.optJSONArray("list");
        if (list != null) {
            for (int i = 0; i < list.length(); i++) {
                LinkedHashMap<Integer, ArrayList<MatchModel>> matchMap = new LinkedHashMap<>();

                JSONObject item = list.getJSONObject(i);

                String cate = item.optString("category");

                JSONArray innerList = item.getJSONArray("list");

                for (int j = 0; j < innerList.length(); j++) {
                    JSONObject obj = innerList.getJSONObject(j);
                    MatchModel model = new MatchModel();
                    int matchId = obj.getInt("id");
                    model.setId(matchId);
                    model.setMatchDesc(obj.getString("matchDesc"));
                    int sid = obj.getInt("seriesId");
                    model.setSeriesId(sid);
                    model.setSeriesDesc(obj.getString("seriesDesc"));
                    model.setCategory(obj.getString("category"));
                    model.setStatus(obj.getString("status"));
                    int date = obj.getInt("startDate");
                    model.setStartDate(date);
                    model.setTeamAName(obj.getString("teamAName"));
                    model.setTeamAShortName(obj.getString("teamAShortName"));
                    model.setTeamBName(obj.getString("teamBName"));
                    model.setTeamBShortName(obj.getString("teamBShortName"));
                    model.setTeamAImageId(obj.getInt("teamAImageId"));
                    model.setTeamBImageId(obj.getInt("teamBImageId"));
                    model.setIsPrevDay(obj.getBoolean("isPrevDay"));
                    if (!matchMap.containsKey(date)) {
                        ArrayList<MatchModel> m = new ArrayList<>();
                        m.add(model);
                        matchMap.put(date, m);
                    } else {
                        matchMap.get(date).add(model);
                    }
                }
                Log.d(TAG, "map " + matchMap);
                adapter.addFragment(MatchFragment.newInstance(cate, matchMap), cate);
            }
            adapter.notifyDataSetChanged();
            tabs.setupWithViewPager(vpCategories);
        } else {
            showSnackBar(getString(R.string.msg_no_result));
        }
        dismissDialog();
    }

    private String getScheduleJson() throws IOException {
        showLoadingDialog();
        InputStream is = getResources().openRawResource(R.raw.schedule);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            showSnackBar(getString(R.string.msg_something_wrong));
            e.printStackTrace();
        } finally {
            is.close();
        }

        return writer.toString();
    }

    private void showLoadingDialog() {
        try {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(getString(R.string.msg_please_wait));
            mDialog.setIndeterminate(true);
            mDialog.setCancelable(false);
            mDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissDialog() {
        if (mDialog != null && mDialog.isShowing())
            mDialog.dismiss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            showSnackBar(getString(R.string.msg_settings));
            return true;
        } else if (id == R.id.action_calender) {
            showSnackBar(getString(R.string.msg_calender));
            return true;
        } else if (id == android.R.id.home) {
            showSnackBar(getString(R.string.msg_back));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showSnackBar(String msg) {
        View view = findViewById(R.id.coordinate_layout);
        if (view != null)
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

}
