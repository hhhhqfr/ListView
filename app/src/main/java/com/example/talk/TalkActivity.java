package com.example.talk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.listviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class TalkActivity extends Activity {
    @BindView(R.id.msg_list_view)
    ListView msgListView;
    @BindView(R.id.input_text)
    EditText inputText;
    @BindView(R.id.send)
    Button send;
    MsgAdapter adapter;
    private ArrayList<Msg> msgList = new ArrayList<Msg>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.talk_layout);
        ButterKnife.bind(this);
        initMsgs();
        adapter = new MsgAdapter(TalkActivity.this, R.layout.msg_item,msgList);
        msgListView.setAdapter(adapter);
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

    @OnClick(R.id.send)
    public void onClick() {
        String content = inputText.getText().toString();
        if (!"".equals(content)) {
            Msg msg = new Msg(content, Msg.TYPE_SENT);
            msgList.add(msg);
            adapter.notifyDataSetChanged(); // 当有新消息时，刷新ListView中的显示
            msgListView.setSelection(msgList.size()); // 将ListView定位到最后一行
            inputText.setText(""); // 清空输入框中的内容
        }
    }
}