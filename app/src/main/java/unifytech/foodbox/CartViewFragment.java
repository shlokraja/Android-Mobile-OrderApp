package unifytech.foodbox;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import unifytech.foodbox.adapter.CartViewAdapter;
import unifytech.foodbox.app.AppController;
import unifytech.foodbox.data.FoodItems;

/**
 * Created by Andriod on 29-02-2016.
 */
public class CartViewFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private CartViewAdapter cartViewAdapter;
    private List<FoodItems.FoodItem> cartItems;
    private OnCartViewFragmentInteractionListener onCartViewFragmentInteractionListener;
    public TextView textTotalAmount;
    public int totalAmount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_view, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cart_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        textTotalAmount = (TextView) view.findViewById(R.id.total);

        cartItems = AppController.getInstance().cartItems;
        cartViewAdapter = new CartViewAdapter(getActivity(), cartItems, onCartViewFragmentInteractionListener, this);
        mRecyclerView.setAdapter(cartViewAdapter);
        final Button placeOrder = (Button) view.findViewById(R.id.btn_place_order);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppController.cartItems.size() == 0) {
                    Snackbar.make(placeOrder, "There is no item in your cart.", Snackbar.LENGTH_LONG).show();

                } else {
                    Login login = new Login();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.addToBackStack(null);
                    transaction.replace(R.id.fragment_container, login).commit();
                }
            }
        });
        setTotalAmount();
    }

    public void setTotalAmount() {
        int total = 0;
        for (int i = 0; i < AppController.cartItems.size(); i++) {
            FoodItems.FoodItem foodItem = AppController.cartItems.get(i);
            total = total + foodItem.getMrp();
        }
        totalAmount = total;
        textTotalAmount.setText("Total: ₹ " + total);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnCartViewFragmentInteractionListener) {
            onCartViewFragmentInteractionListener = (OnCartViewFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFoodListFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onCartViewFragmentInteractionListener = null;
    }


    public interface OnCartViewFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCartListFragmentInteraction(FoodItems.FoodItem item);
    }
}
