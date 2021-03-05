package com.example.talk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.listviewtest.R;
import com.example.listviewtest.ViewHolder;

import java.util.List;

public class MsgAdapter extends ArrayAdapter<Msg> {
    public int resourceId;

    public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Msg msg = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }
        if (msg.getType() == Msg.TYPE_RECEIVED) {
// 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == Msg.TYPE_SENT) {
// 如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }


    public class ViewHolder  {
        @BindView(R.id.left_layout)
        LinearLayout leftLayout;
        @BindView(R.id.right_layout)
        LinearLayout rightLayout;
        @BindView(R.id.left_msg)
        TextView leftMsg;
        @BindView(R.id.right_msg)
        TextView rightMsg;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);//此处进行绑定
        }

    }
}
