package br.com.leandroap.democontextactionbar.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by leandro on 26/09/15.
 */
public class SelectionAdapter extends ArrayAdapter<String> {

    private HashMap<Integer, Boolean> mSelection = new HashMap<>();

    private Context context;

    public SelectionAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);

        this.context = context;
    }

    public void setNewSelection(int position, boolean value){
        mSelection.put(position, value);
        notifyDataSetChanged();
    }

    public boolean isPositionChecked(int position){
        Boolean result = mSelection.get(position);

        return  result == null ? false : result;
    }

    public Set<Integer> getCurrentCheckedPosition() {
        return mSelection.keySet();
    }

    public void removeSelection(int position) {
        mSelection.remove(position);
        notifyDataSetChanged();
    }

    public void clearSelection() {
        mSelection = new HashMap<Integer, Boolean>();
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = super.getView(position, convertView, parent);
        v.setBackgroundColor(context.getResources().getColor(android.R.color.background_light));

        if(mSelection.get(position) != null){
            v.setBackgroundColor(context.getResources().getColor(android.R.color.holo_blue_light));
        }

        return v;

    }










}
