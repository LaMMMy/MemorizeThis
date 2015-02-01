package bensonius.com.memorizethis;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;


public class BensoniusAdapter extends RecyclerView.Adapter<BensoniusAdapter.MyViewHolder> {
    List<Information> listData = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public BensoniusAdapter(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.listData = data;
    }

    public void delete(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        Log.d("BENSON", "onCreateViewHolder called");

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)  {
        Information current = listData.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            delete(getPosition());
            Toast.makeText(context, "Item at " + getPosition() + " deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
