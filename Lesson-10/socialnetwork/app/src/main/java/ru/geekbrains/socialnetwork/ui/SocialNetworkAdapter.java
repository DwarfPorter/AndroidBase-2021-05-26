package ru.geekbrains.socialnetwork.ui;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

import ru.geekbrains.socialnetwork.R;
import ru.geekbrains.socialnetwork.data.CardData;
import ru.geekbrains.socialnetwork.data.CardsSource;

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkAdapter.ViewHolder> {

    private final static String TAG = "SocialNetwork";
    private CardsSource dataSource;
    private final Fragment fragment;
    private OnItemClickListener itemClickListener;
    private int menuPosition;

    public SocialNetworkAdapter(Fragment fragment){
        this.fragment = fragment;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        Log.d(TAG, "onCreateViewHolder");
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialNetworkAdapter.ViewHolder holder,
                                 int position) {
        holder.setData(dataSource.getCardData(position));
        Log.d(TAG, String.format("onBindViewHolder - %d", position));
    }

    @Override
    public int getItemCount() {
        return dataSource.getSize();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public int getMenuPosition(){
        return menuPosition;
    }

    public void setDataSource(CardsSource dataSource) {
        this.dataSource = dataSource;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;
        private AppCompatImageView image;
        private CheckBox like;
        private TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.imageView);
            like = itemView.findViewById(R.id.like);
            date = itemView.findViewById(R.id.date);

            registerContextMenu(itemView);

            image.setOnLongClickListener(v -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    menuPosition = getLayoutPosition();
                    itemView.showContextMenu(10, 10);
                }
                return true;
            });

            image.setOnClickListener(v -> {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(v, getAdapterPosition());
                }
            });
        }

        private void registerContextMenu(@NotNull View itemView) {
            if (fragment != null) {
                itemView.setOnLongClickListener(v -> {
                    menuPosition = getLayoutPosition();
                    return false;
                });
                fragment.registerForContextMenu(itemView);
            }
        }

        public void setData(CardData data){
            title.setText(data.getTitle());
            description.setText(data.getDescription());
            like.setChecked(data.isLike());
            image.setImageResource(data.getPicture());
            date.setText(new SimpleDateFormat("dd-MM-yy").format(data.getDate()));
        }
    }
}
