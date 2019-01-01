package com.translate.adapterFile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.translate.R;
import com.translate.model.Part;

import java.util.List;

public class PartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Part> partList;

    public PartAdapter(Context context, int layout, List<Part> partList) {
        this.context = context;
        this.layout = layout;
        this.partList = partList;
    }

    @Override
    public int getCount() {
        return partList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private  class ViewHolder{
        TextView textViewId;
        TextView textViewName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(layout,null);
            viewHolder.textViewId=convertView.findViewById(R.id.nameidpart);
            viewHolder.textViewName=convertView.findViewById(R.id.namepart);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Part part=partList.get(position);
        viewHolder.textViewId.setText(part.getId()+"");
        viewHolder.textViewName.setText(part.getName());
        return convertView;
    }
}
