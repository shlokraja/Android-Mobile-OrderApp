package unifytech.foodbox.adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import unifytech.foodbox.CartViewFragment;
import unifytech.foodbox.R;
import unifytech.foodbox.app.AppController;
import unifytech.foodbox.data.FoodItems;
import unifytech.foodbox.utils.RequestData;
import unifytech.foodbox.viewholders.CartViewHolder;

/**
 * Created by Aman on 29-02-2016.
 */
public class CartViewAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<FoodItems.FoodItem> cartItemList;
    private Context mContext;
    private CartViewFragment.OnCartViewFragmentInteractionListener onCartViewFragmentInteractionListener;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();
    private CartViewFragment cartViewFragment;

    public CartViewAdapter(Context context, List<FoodItems.FoodItem> cartItemList,
                           CartViewFragment.OnCartViewFragmentInteractionListener onCartViewFragmentInteractionListener,
                           CartViewFragment cartViewFragment) {
        this.cartItemList = cartItemList;
        this.mContext = context;
        this.onCartViewFragmentInteractionListener = onCartViewFragmentInteractionListener;
        this.cartViewFragment = cartViewFragment;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, null);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(final CartViewHolder holder, final int position) {
        final FoodItems.FoodItem cartItem = cartItemList.get(position);
        holder.itemName.setText(cartItem.getTitle());
        holder.textRupee.setText("" + cartItem.getMrp());
        holder.imageView.setImageUrl(RequestData.BASE_URL + cartItem.getConcat(), imageLoader);
        holder.textCount.setText("1");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onCartViewFragmentInteractionListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    onCartViewFragmentInteractionListener.onCartListFragmentInteraction(cartItem);
                }
            }
        });

        holder.textPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.valueOf(holder.textCount.getText().toString());
                count += 1;
                holder.textCount.setText(String.valueOf(count));
                holder.textRupee.setText("" + cartItem.getMrp() * count);
                cartViewFragment.totalAmount = cartViewFragment.totalAmount + cartItem.getMrp();
                cartViewFragment.textTotalAmount.setText("Total: ₹ " + cartViewFragment.totalAmount);
            }
        });

        holder.textMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.valueOf(holder.textCount.getText().toString());
                if (count == 1) {
                    return;
                } else {
                    count -= 1;
                    holder.textCount.setText(String.valueOf(count));
                    holder.textRupee.setText("" + cartItem.getMrp() * count);
                    cartViewFragment.totalAmount = cartViewFragment.totalAmount - cartItem.getMrp();

                    cartViewFragment.textTotalAmount.setText("Total: ₹ " + cartViewFragment.totalAmount);
                }
            }
        });

        holder.imageCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppController.cartItems.remove(position);
                notifyDataSetChanged();
                cartViewFragment.totalAmount = cartViewFragment.totalAmount -
                        (cartItem.getMrp() * Integer.valueOf(holder.textCount.getText().toString()));
                cartViewFragment.textTotalAmount.setText("Total: ₹ " + cartViewFragment.totalAmount);
                if (AppController.cartItems.size() == 0) {
                    Snackbar.make(holder.mView, "No more items in your cart.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != cartItemList ? cartItemList.size() : 0);
    }
}
