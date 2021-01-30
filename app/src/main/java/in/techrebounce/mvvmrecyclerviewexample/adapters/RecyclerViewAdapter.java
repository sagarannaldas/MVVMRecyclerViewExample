package in.techrebounce.mvvmrecyclerviewexample.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import in.techrebounce.mvvmrecyclerviewexample.MainActivity;
import in.techrebounce.mvvmrecyclerviewexample.R;
import in.techrebounce.mvvmrecyclerviewexample.models.NicePlaces;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<NicePlaces> mNicePlaces = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext, List<NicePlaces> mNicePlaces ) {
        this.mContext = mContext;
        this.mNicePlaces = mNicePlaces;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mNicePlaces.get(position).getImageUrl())
                .into(holder.circleImageView);

        holder.place.setText(mNicePlaces.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView circleImageView;
        private TextView place;
        private ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circular_view);
            place = itemView.findViewById(R.id.textview_place);
            constraintLayout = itemView.findViewById(R.id.layout_list_item);
        }
    }
}
