package com.hod.hodassignment.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.hod.hodassignment.R;
import com.hod.hodassignment.Utility.AnimButton;
import com.hod.hodassignment.adapter.ChatRecyclerAdapter;
import com.hod.hodassignment.database.DatabaseConstants;
import com.hod.hodassignment.database.DatabaseHandler;
import com.hod.hodassignment.model.ChatModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimButton animButton;
    private EditText txtInput;
    private RecyclerView recycler_view;
    private ArrayList<ChatModel> chatModelArrayList;
    private RelativeLayout relative_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getViews();
    }

    private void getViews() {
        animButton = (AnimButton) findViewById(R.id.animButton);
        animButton.setOnClickListener(this);
        txtInput = (EditText) findViewById(R.id.txtInput);

        txtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text =  s.toString().trim();
                animButton.goToState(text.length() == 0 ? AnimButton.FIRST_STATE : AnimButton.SECOND_STATE);
            }
        });
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        final  LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recycler_view.setLayoutManager(linearLayoutManager);
        chatModelArrayList = DatabaseHandler.getInstance().getCustomerChat(getApplicationContext());
        ChatRecyclerAdapter chatRecyclerAdapter = new ChatRecyclerAdapter(this, chatModelArrayList);
        recycler_view.setAdapter(chatRecyclerAdapter);
        relative_layout = (RelativeLayout)findViewById(R.id.relative_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animButton:
                String message = txtInput.getText().toString().trim();

                if(message.length() > 0) {
                    // for sender
                    ChatModel chatModel1 = new ChatModel();
                    chatModel1.setSenderId(DatabaseConstants.SENDER_ID_CONSTANT);
                    chatModel1.setReceiverId(DatabaseConstants.RECEIVER_ID_CONSTANT);
                    chatModel1.setMessage(message);
                    DatabaseHandler.getInstance().insertCustomerChat(this, chatModel1);

                    // for receiver random message generation
                    ChatModel chatModel2 = new ChatModel();
                    chatModel2.setSenderId(DatabaseConstants.RECEIVER_ID_CONSTANT);
                    chatModel2.setReceiverId(DatabaseConstants.SENDER_ID_CONSTANT);
                    chatModel2.setMessage("Random Message Reply : "+message);
                    DatabaseHandler.getInstance().insertCustomerChat(this, chatModel2);

                    chatModelArrayList.clear();
                    chatModelArrayList = DatabaseHandler.getInstance().getCustomerChat(getApplicationContext());
                    ChatRecyclerAdapter chatRecyclerAdapter = new ChatRecyclerAdapter(this, chatModelArrayList);
                    recycler_view.setAdapter(chatRecyclerAdapter);

                    txtInput.setText("");
                    animButton.goToState(txtInput.getText().toString().length() == 0 ? AnimButton.FIRST_STATE : AnimButton.SECOND_STATE);
                }else {
                    Snackbar.make(relative_layout, "Please enter valid message", Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
