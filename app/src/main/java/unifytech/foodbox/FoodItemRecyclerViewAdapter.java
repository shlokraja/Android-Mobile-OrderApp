package unifytech.foodbox;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import unifytech.foodbox.app.AppController;
import unifytech.foodbox.data.DummyContent.DummyItem;
import unifytech.foodbox.data.FoodItems;
import unifytech.foodbox.utils.RequestData;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link FoodItemList.OnFoodListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class FoodItemRecyclerViewAdapter extends RecyclerView.Adapter<FoodItemRecyclerViewAdapter.ViewHolder> {

    private final List<FoodItems.FoodItem> mValues;
    private final FoodItemList.OnFoodListFragmentInteractionListener mListener;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public FoodItemRecyclerViewAdapter(List<FoodItems.FoodItem> items, FoodItemList.OnFoodListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.itemImage.setImageUrl(RequestData.BASE_URL + holder.mItem.getConcat(), imageLoader);
        holder.mContentView.setText(mValues.get(position).getTitle());
        holder.mTextCost.setText("₹ "+mValues.get(position).getMrp());

        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(holder.mView, "Item added to your cart.", Snackbar.LENGTH_SHORT).show();
                AppController.getInstance().cartItems.add(holder.mItem);
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView btnAddToCart;
        public NetworkImageView itemImage;
        public TextView mContentView, mTextCost;
        public FoodItems.FoodItem mItem;
        public Button btnBuyNow;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            itemImage = (NetworkImageView) view.findViewById(R.id.item_image);
            mContentView = (TextView) view.findViewById(R.id.content);
            btnAddToCart = (ImageView) view.findViewById(R.id.image_cart);
            btnBuyNow = (Button) view.findViewById(R.id.btn_buy_now);
            mTextCost = (TextView) view.findViewById(R.id.text_cost);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
