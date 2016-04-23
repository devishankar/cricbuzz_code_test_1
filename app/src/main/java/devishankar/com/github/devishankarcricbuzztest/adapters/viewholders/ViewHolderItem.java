package devishankar.com.github.devishankarcricbuzztest.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import devishankar.com.github.devishankarcricbuzztest.R;

/**
 * @author Devishankar
 */
public class ViewHolderItem extends RecyclerView.ViewHolder {
    private TextView tvTeam1;
    private TextView tvTeam2;
    private TextView tvMatchNu;
    private TextView tvMatchTime;

    public ViewHolderItem(View itemView) {

        super(itemView);
        tvTeam1 = (TextView) itemView.findViewById(R.id.tvTeam1);
        tvTeam2 = (TextView) itemView.findViewById(R.id.tvTeam2);
        tvMatchNu = (TextView) itemView.findViewById(R.id.tvMatchNu);
        tvMatchTime = (TextView) itemView.findViewById(R.id.tvMatchTime);
    }

    public TextView getTvTeam2() {
        return tvTeam2;
    }

    public void setTvTeam2(TextView tvTeam2) {
        this.tvTeam2 = tvTeam2;
    }

    public TextView getTvMatchNu() {
        return tvMatchNu;
    }

    public void setTvMatchNu(TextView tvMatchNu) {
        this.tvMatchNu = tvMatchNu;
    }

    public TextView getTvTeam1() {
        return tvTeam1;
    }

    public void setTvTeam1(TextView tvTeam1) {
        this.tvTeam1 = tvTeam1;
    }

    public TextView getTvMatchTime() {
        return tvMatchTime;
    }

    public void setTvMatchTime(TextView tvMatchTime) {
        this.tvMatchTime = tvMatchTime;
    }
}
