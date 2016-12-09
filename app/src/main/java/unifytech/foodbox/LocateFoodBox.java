package unifytech.foodbox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import unifytech.foodbox.utils.RequestData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LocateFoodBox.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LocateFoodBox#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocateFoodBox extends Fragment implements AreaListDialogFragment.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mPrevCity;

    private OnFragmentInteractionListener mListener;

    private TextView editCity, editArea, editStore;

    String[] city = {"Chennai", "Bangalore"};
    ArrayList<String> strings;

    private Button mContinue;

    private static LocateFoodBox fragment;

    private int mKey;

    public LocateFoodBox() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LocateFoodBox newInstance() {
        fragment = new LocateFoodBox();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static LocateFoodBox getInstance() {
        if (fragment == null) {
            return newInstance();
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_locate_foodbox, container, false);
        mContinue = (Button) view.findViewById(R.id.btn_location_continue);
        editCity = (TextView) view.findViewById(R.id.city);
        editArea = (TextView) view.findViewById(R.id.area);
        editStore = (TextView) view.findViewById(R.id.store);

        editCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleCheckListDialog("Select City", editCity, null);
            }
        });

        editArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleCheckListDialog("Select Area", editArea, null);
            }
        });
        editStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleCheckListDialog("Select Store", editStore, editArea.getText().toString());
            }
        });
        mContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean cancel = false;
                String city = editCity.getText().toString();
                String area = editArea.getText().toString();
                String store = editStore.getText().toString();

                View focusView = null;
                if (TextUtils.isEmpty(city)) {
                    editCity.setError(getString(R.string.error_field_required));
                    focusView = editCity;
                    cancel = true;
                }
                if (TextUtils.isEmpty(area)) {
                    editArea.setError(getString(R.string.error_field_required));
                    focusView = editArea;
                    cancel = true;
                }
                if (TextUtils.isEmpty(store)) {
                    editStore.setError(getString(R.string.error_field_required));
                    focusView = editStore;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {

                    RequestData requestData = RequestData.getInstance(getActivity());
                    requestData.request(String.valueOf(mKey));
                     /*   RestaurantList restaurantList = RestaurantList.newInstance();
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", mKey);
                        restaurantList.setArguments(bundle);
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.fragment_container, restaurantList).commit();*/
                    //}
                }

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(String value, int key) {

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void showSingleCheckListDialog(String dialogTitle, final TextView textView, String text) {
        AreaListDialogFragment areaListDialogFragment = AreaListDialogFragment.newInstance(dialogTitle, text);
        areaListDialogFragment.setOnItemClickListener(new AreaListDialogFragment.OnItemClickListener() {
            @Override
            public void onClick(String value, int key) {
                mKey = key;
                textView.setText(value);
                if (RequestData.sOutletData == null || (mPrevCity != null && mPrevCity != value && textView.getId() == R.id.city)) {
                    if (editCity.getText().toString().equalsIgnoreCase("Bangalore")) {
                        RequestData.getInstance(getActivity()).getOutlets("BN");
                    } else if (editCity.getText().toString().equalsIgnoreCase("Chennai")) {
                        RequestData.getInstance(getActivity()).getOutlets("CH");
                    }
                }
                mPrevCity = editCity.getText().toString();
            }
        });
        areaListDialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }

}