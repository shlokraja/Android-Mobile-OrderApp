package unifytech.foodbox;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import unifytech.foodbox.data.FoodItems;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFoodListFragmentInteractionListener}
 * interface.
 */
public class FoodItemList extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnFoodListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    static List<FoodItems.FoodItem> list;

    public FoodItemList() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FoodItemList newInstance(List<FoodItems.FoodItem> listItems) {
        FoodItemList fragment = new FoodItemList();
        list = listItems;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_item_list, container, false);
        // Set the adapter
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_food_item);
        recyclerView.setAdapter(new FoodItemRecyclerViewAdapter(list, mListener));

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFoodListFragmentInteractionListener) {
            mListener = (OnFoodListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFoodListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFoodListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(FoodItems.FoodItem item);
    }
}
