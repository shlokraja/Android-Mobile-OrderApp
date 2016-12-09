package unifytech.foodbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

import unifytech.foodbox.adapter.DialogListAdapter;
import unifytech.foodbox.utils.RequestData;


public class AreaListDialogFragment extends DialogFragment {
    private HashSet<String> mArrayListValues = new HashSet<>();
    private ArrayList<Integer> mKeyList = new ArrayList<Integer>();
    private static String sDialogTitle;
    private OnItemClickListener mItemClickListener;
    private ListView mList;
    private TextView mTextTitle;
    private static String sTextViewData;
    private DialogListAdapter mAdapter;

    public static AreaListDialogFragment newInstance(String title, String text) {
        AreaListDialogFragment areaListDialogFragment = new AreaListDialogFragment();
        areaListDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        sDialogTitle = title;
        sTextViewData = text;
        return areaListDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_list, container, false);
        mList = (ListView) v.findViewById(R.id.list);
        mTextTitle = (TextView) v.findViewById(R.id.dialog_title);
        mTextTitle.setText(sDialogTitle);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (sDialogTitle.contains("Area")) {
            if (RequestData.sOutletData != null) {
                int size = RequestData.sOutletData.size();
                for (int i = 0; i < size; i++) {
                    int key = RequestData.sOutletData.keyAt(i);
                    RequestData.OutletInfo outletInfo = RequestData.sOutletData.get(key);
                    mArrayListValues.add(outletInfo.address);
                }
            }

        } else if (sDialogTitle.contains("Store")) {
            if (RequestData.sOutletData != null) {

                int size = RequestData.sOutletData.size();
                for (int i = 0; i < size; i++) {
                    int key = RequestData.sOutletData.keyAt(i);
                    RequestData.OutletInfo outletInfo = RequestData.sOutletData.get(key);
                    if (sTextViewData.equalsIgnoreCase(outletInfo.address)) {
                        mKeyList.add(key);
                        mArrayListValues.add(outletInfo.outletName);
                    }
                }
            }
        } else {
            mArrayListValues.add("Chennai");
            mArrayListValues.add("Bangalore");
        }
        mAdapter = new DialogListAdapter(getActivity(), new ArrayList<String>(mArrayListValues), new OnItemClickListener() {
            @Override
            public void onClick(String value, int key) {
                dismiss();
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(value, key);
                }
            }
        }, mKeyList);
        mList.setAdapter(mAdapter);
    }

    public interface OnItemClickListener {
        void onClick(String value, int key);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
