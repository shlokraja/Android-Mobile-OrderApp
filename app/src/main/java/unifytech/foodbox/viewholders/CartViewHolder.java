package unifytech.foodbox.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import unifytech.foodbox.R;

/**
 * Created by Andriod on 29-02-2016.
 */
public class CartViewHolder extends RecyclerView.ViewHolder {
    public NetworkImageView imageView;
    public TextView itemName, textRupee, textMinus, textCount, textPlus;
    public View mView;
    public ImageView imageCross;

    public CartViewHolder(View view) {
        super(view);
        mView = view;
        this.imageView = (NetworkImageView) view.findViewById(R.id.image_item);
        this.itemName = (TextView) view.findViewById(R.id.text1);
        this.textRupee = (TextView) view.findViewById(R.id.text_cost);
        this.textMinus = (TextView) view.findViewById(R.id.minus);
        this.textCount = (TextView) view.findViewById(R.id.count);
        this.textPlus = (TextView) view.findViewById(R.id.plus);
        this.imageCross = (ImageView) view.findViewById(R.id.text_cross);
    }
}
