package unifytech.foodbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class OrderBlockedFragment extends Fragment {
    private EditText editOrderNumber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_blocked_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editOrderNumber = (EditText) view.findViewById(R.id.order_number);

        editOrderNumber.setEnabled(false);
    }
}
