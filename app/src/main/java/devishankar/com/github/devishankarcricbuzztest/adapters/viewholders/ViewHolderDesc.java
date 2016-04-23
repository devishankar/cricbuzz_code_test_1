package devishankar.com.github.devishankarcricbuzztest.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import devishankar.com.github.devishankarcricbuzztest.R;

/**
 * @author Devishankar
 */
public class ViewHolderDesc extends RecyclerView.ViewHolder {
    private TextView tvMatchDesc;

    public ViewHolderDesc(View itemView) {
        super(itemView);
        tvMatchDesc = (TextView) itemView.findViewById(R.id.text);
    }

    public TextView getTvMatchDesc() {
        return tvMatchDesc;
    }

    public void setTvMatchDesc(TextView tvMatchDesc) {
        this.tvMatchDesc = tvMatchDesc;
    }
}
