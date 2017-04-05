package com.vastiny.checkablelist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Message> messages = new ArrayList<>();
        for (int i =0; i< 40; i++) {
            messages.add(new Message("hello #" + i, "2017"));
        }

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListAdapter(this, messages));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                checkBox.setChecked(!checkBox.isChecked());
            }
        });

    }


    public class ListAdapter extends ArrayAdapter<Message> {
        private LayoutInflater li;

        /**
         * Constructor
         *
         * @param context  The current context.
         * @param objects  The objects to represent in the ListView.
         */
        public ListAdapter(@NonNull Context context, @NonNull List<Message> objects) {
            super(context, 0, objects);
            li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * Get a View that displays the data at the specified position in the data set. You can either
         * create a View manually or inflate it from an XML layout file. When the View is inflated, the
         * parent View (GridView, ListView...) will apply default layout parameters unless you use
         * {@link android.view.LayoutInflater#inflate(int, android.view.ViewGroup, boolean)}
         * to specify a root view and to prevent attachment to the root.
         *
         * @param position The position of the item within the adapter's data set of the item whose view
         *        we want.
         * @param convertView The old view to reuse, if possible. Note: You should check that this view
         *        is non-null and of an appropriate type before using. If it is not possible to convert
         *        this view to display the correct data, this method can create a new view.
         *        Heterogeneous lists can specify their number of view types, so that this View is
         *        always of the right type (see {@link #getViewTypeCount()} and
         *        {@link #getItemViewType(int)}).
         *        converView 是可以复用的 itemView, 下面的方法中，提供了可以重复使用 itemView 的方法
         * @param parent The parent that this view will eventually be attached to
         * @return A View corresponding to the data at the specified position.
         */
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Message message = getItem(position);

            ViewHolder holder;
            if (convertView == null) {
                convertView = li.inflate(R.layout.row_item_message, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(R.id.holder, holder);
            } else {
                holder = (ViewHolder) convertView.getTag(R.id.holder);
            }

            holder.textView.setText(message.getMessage());

            final ListView lv = (ListView) parent;
            holder.checkBox.setChecked(lv.isItemChecked(position));
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lv.setItemChecked(position, !lv.isItemChecked(position));
                }
            });

            return convertView;
        }

        private class ViewHolder {
            public ViewHolder(View root) {
                textView = (TextView) root.findViewById(R.id.textView);
                layout = (LinearLayout) root.findViewById(R.id.itemMessage);
                checkBox = (CheckBox) root.findViewById(R.id.checkBox);
            }

            public TextView textView;
            public LinearLayout layout;
            public CheckBox checkBox;
        }
    }



}
