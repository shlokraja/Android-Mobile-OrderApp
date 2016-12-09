package unifytech.foodbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentOptionsFragment extends Fragment {
    private LinearLayout layputCreditCards;
    private TextView textCredit, card1, card2, card3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_options, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        layputCreditCards = (LinearLayout) view.findViewById(R.id.layout_credit_cards);
        textCredit = (TextView) view.findViewById(R.id.text_credit);
        card1 = (TextView) view.findViewById(R.id.card1);
        card2 = (TextView) view.findViewById(R.id.card2);
        card3 = (TextView) view.findViewById(R.id.card3);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBlockedFragment orderBlockedFragment = new OrderBlockedFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, orderBlockedFragment).commit();
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBlockedFragment orderBlockedFragment = new OrderBlockedFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, orderBlockedFragment).commit();
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderBlockedFragment orderBlockedFragment = new OrderBlockedFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.fragment_container, orderBlockedFragment).commit();
            }
        });

        textCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layputCreditCards.getVisibility() == View.VISIBLE) {
                    layputCreditCards.setVisibility(View.GONE);
                } else {
                    layputCreditCards.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
