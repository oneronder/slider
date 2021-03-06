package slider.image.shelly.com.slider.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import slider.image.shelly.com.slider.R;
import slider.image.shelly.com.slider.model.SlideShow;

/**
 * Created by chandresh.pancholi on 27/12/15.
 */
public class InteractiveArrayAdapter extends ArrayAdapter<SlideShow> {

    private final List<SlideShow> list;
    private final Activity context;
    int selectedIndex = -1;

    public InteractiveArrayAdapter(Activity context, List<SlideShow> list) {
        super(context, R.layout.grouprow, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected RadioButton checkbox;
        protected TextView text;

    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            view = inflator.inflate(R.layout.grouprow, null);
            final ViewHolder viewHolder = new ViewHolder();
            viewHolder.checkbox = (RadioButton) view.findViewById(R.id.checkbox);
            viewHolder.checkbox.setTag(list.get(position));//check box
            viewHolder.text = (TextView) view.findViewById(R.id.shwtitle);
            view.setTag(viewHolder);

        } else {
            view = convertView;
            ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.checkbox.setChecked(list.get(position).isSelected());

        if (position % 2 == 1) { //alternate row color in list view
            view.setBackgroundColor(Color.rgb(238, 238, 238));
        } else {
            view.setBackgroundColor(Color.rgb(255, 255, 255));
        }
        StringBuffer result = new StringBuffer();// for using arrayList adapter
        // to get name and description of slide show
        result.append(list.get(position).getshowName());
        if (!list.get(position).getshowDescription().isEmpty())
            result.append(" - " + list.get(position).getshowDescription());
        holder.text.setText(result.toString());
        if (list.get(position).getId() == -1) {
            //if it is the dummy value of the list then hide the check box
            holder.checkbox.setVisibility(View.GONE);
        } else {
            holder.checkbox.setVisibility(View.VISIBLE);
        }
        return view;
    }
}
