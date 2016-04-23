package devishankar.com.github.devishankarcricbuzztest.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import devishankar.com.github.devishankarcricbuzztest.R;
import devishankar.com.github.devishankarcricbuzztest.adapters.viewholders.ViewHolderDate;
import devishankar.com.github.devishankarcricbuzztest.adapters.viewholders.ViewHolderDesc;
import devishankar.com.github.devishankarcricbuzztest.adapters.viewholders.ViewHolderItem;
import devishankar.com.github.devishankarcricbuzztest.models.MatchModel;

/**
 * @author Devishankar
 */
public class MatchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = MatchListAdapter.class.getSimpleName();
    private final ArrayList<Object> items;
    private final int DATE = 0, DESC = 1, MATCH = 2;

    public MatchListAdapter(ArrayList<Object> arr) {
        this.items = arr;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case DATE:
                View v1 = inflater.inflate(R.layout.layout_date, parent, false);
                viewHolder = new ViewHolderDate(v1);
                break;
            case DESC:
                View v2 = inflater.inflate(R.layout.layout_desc, parent, false);
                viewHolder = new ViewHolderDesc(v2);
                break;
            case MATCH:
                View v3 = inflater.inflate(R.layout.layout_match, parent, false);
                viewHolder = new ViewHolderItem(v3);
                break;
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case DATE:
                fillDate((ViewHolderDate) holder, position);
                break;
            case DESC:
                fillDesc((ViewHolderDesc) holder, position);
                break;
            case MATCH:
                fillMatch((ViewHolderItem) holder, position);
                break;
        }
    }

    private void fillMatch(ViewHolderItem holder, int position) {
        MatchModel item = (MatchModel) this.items.get(position);
        if (item != null) {
            holder.getTvTeam1().setText(item.getTeamAName());
            holder.getTvTeam2().setText(item.getTeamBName());
            holder.getTvMatchNu().setText(item.getMatchDesc());

            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aaa", Locale.ENGLISH);
            String formattedTime = sdf.format(new Date(item.getStartDate() * 1000));
            holder.getTvMatchTime().setText(formattedTime);

        }
    }

    private void fillDesc(ViewHolderDesc holder, int position) {
        holder.getTvMatchDesc().setText(this.items.get(position).toString());
    }

    private void fillDate(ViewHolderDate holder, int position) {
        if (this.items.get(position) != null) {
            Integer i = (Integer) this.items.get(position);
            Date date = new Date(i * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d", Locale.ENGLISH);
            String formattedDate = sdf.format(date);
            holder.getTvDate().setText(formattedDate);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (this.items.get(position) instanceof Integer) {
            return DATE;
        } else if (this.items.get(position) instanceof String) {
            return DESC;
        } else if (this.items.get(position) instanceof MatchModel) {
            return MATCH;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
