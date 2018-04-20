package example.com.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by kasundne on 4/4/2018.
 */

public class CustomListAdapter extends ArrayAdapter implements Filterable {

    private List<StateModel> dataList;
    private Context mContext;


    private ListFilter listFilter = new ListFilter();
    private List<StateModel> dataListAllItems;


    public CustomListAdapter(Context context, List<StateModel> storeDataLst/*, OnAutotvItemClick onAutotvItemClick*/) {
        super(context, R.layout.autocomplete_row, storeDataLst);
        dataList = storeDataLst;
        mContext = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String getItem(int position) {
        Log.d("CustomListAdapter", dataList.get(position).getStateName());
        return dataList.get(position).getStateName();
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.autocomplete_row, parent, false);
        }

        TextView textView = view.findViewById(R.id.textview);
        textView.setText(dataList.get(position).getStateName());
        return view;

    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        private Object lock = new Object();

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (lock) {
                    dataListAllItems = new ArrayList<>(dataList);
                }
            }

            if (prefix == null) {
                synchronized (lock) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<StateModel> matchValues = new ArrayList<>();

                for (StateModel dataItem : dataListAllItems) {
                    if (dataItem.getStateName().toLowerCase().contains(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }
                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<StateModel>) results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
