package unifytech.foodbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import unifytech.foodbox.RestaurantList.OnListFragmentInteractionListener;
import unifytech.foodbox.app.AppController;
import unifytech.foodbox.utils.DataItems.DataItem;
import unifytech.foodbox.utils.RequestData;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DataItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RestaurantRecyclerViewAdapter extends RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder> {

    private final List<DataItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    int outletId;

    public RestaurantRecyclerViewAdapter(List<DataItem> items, OnListFragmentInteractionListener listener, int outletId) {
        mValues = items;
        mListener = listener;
        this.outletId = outletId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(holder.mItem.title);
        holder.mThumbnailView.setImageUrl(RequestData.BASE_URL + holder.mItem.thumbnailUrl, imageLoader);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, outletId);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("@Nishanth, count  " + mValues.size());
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final NetworkImageView mThumbnailView;
        public DataItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mThumbnailView = (NetworkImageView) view.findViewById(R.id.thumbnail);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}
