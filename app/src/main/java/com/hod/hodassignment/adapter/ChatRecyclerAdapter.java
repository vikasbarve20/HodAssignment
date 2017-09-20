package com.hod.hodassignment.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hod.hodassignment.R;
import com.hod.hodassignment.database.DatabaseConstants;
import com.hod.hodassignment.model.ChatModel;

import java.util.ArrayList;

/**
 * Created by vikas.sunil.barve on 9/20/2017.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.CustomViewHolder>{

    private ArrayList<ChatModel> chatModelArrayList;
    private Context context = null;

    public ChatRecyclerAdapter(Context context,ArrayList<ChatModel> chatModelArrayList) {
        this.chatModelArrayList = chatModelArrayList;
        this.context = context;
    }

    @Override
    public ChatRecyclerAdapter.CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_chat, viewGroup, false);

        ChatRecyclerAdapter.CustomViewHolder viewHolder = new ChatRecyclerAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ChatRecyclerAdapter.CustomViewHolder customViewHolder, int i) {
            ChatModel chatModel = chatModelArrayList.get(i);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) customViewHolder.text_view_message.getLayoutParams();

        if(DatabaseConstants.CUSTOMER_ID_CONSTANT == chatModel.getSenderId()) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            }
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            customViewHolder.text_view_message.setBackgroundResource(R.drawable.rounded_corner_sender);
        }else {
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            customViewHolder.text_view_message.setBackgroundResource(R.drawable.rounded_corner_receiver);
        }
        customViewHolder.text_view_message.setLayoutParams(params);
        customViewHolder.text_view_message.setText(chatModel.getMessage());
    }

    @Override
    public int getItemCount() {
        return (null != chatModelArrayList ? chatModelArrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView text_view_message;
        public CustomViewHolder(View view) {
            super(view);
            this.text_view_message = (TextView) view.findViewById(R.id.text_view_message);
        }
    }

}
