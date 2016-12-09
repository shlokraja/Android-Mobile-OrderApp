package unifytech.foodbox.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import unifytech.foodbox.AreaListDialogFragment;
import unifytech.foodbox.R;


public class DialogListAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;
    //    private MultiSelectValue multiSelectValue;
    private ArrayList<String> valueList;
    ArrayList<Integer> keyList;
    private Context context;
    private AreaListDialogFragment.OnItemClickListener listener;

    public DialogListAdapter(Context context, ArrayList<String> valuesArray, AreaListDialogFragment.OnItemClickListener listener ,
                             ArrayList<Integer> keyList) {
        super(context, R.layout.item_autocomplete, valuesArray);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        valueList = valuesArray;
        this.keyList = keyList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_autocomplete, parent, false);
            viewHolder.textValue = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textValue.setText(valueList.get(pos));

        viewHolder.textValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    if (!keyList.isEmpty()) {
                        listener.onClick(valueList.get(pos), keyList.get(pos));
                    } else {
                        listener.onClick(valueList.get(pos), 0);
                    }
                }
            }
        });

        return convertView;
    }


    private static class ViewHolder {
        TextView textValue;
        int id;
    }

    public interface OnItemClickListener {
        void onItemClick(String value);
    }
}
