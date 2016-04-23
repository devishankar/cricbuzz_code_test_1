package devishankar.com.github.devishankarcricbuzztest.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import devishankar.com.github.devishankarcricbuzztest.R;

/**
 * @author Devishankar
 */
public class ViewHolderDate extends RecyclerView.ViewHolder {
    private TextView tvDate;

    public ViewHolderDate(View itemView) {
        super(itemView);
        tvDate = (TextView) itemView.findViewById(R.id.text);
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }
}
