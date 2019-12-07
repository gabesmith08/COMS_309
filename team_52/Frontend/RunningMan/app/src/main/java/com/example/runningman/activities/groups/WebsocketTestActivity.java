package com.example.runningman.activities.groups;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.runningman.R;
import com.example.runningman.model.DataHolder;
import com.example.runningman.websockets.Message;
import com.example.runningman.websockets.MessagesListAdapter;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class WebsocketTestActivity extends AppCompatActivity {

    Button btnSend;
    EditText inputMsg;
    TextView t1;

    private WebSocketClient cc;

    private String lastMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_websocket_test);
        btnSend = findViewById(R.id.btnSend);
        inputMsg = findViewById(R.id.inputMsg);
        t1 = findViewById(R.id.tx1);
        ListView listViewMessages = findViewById(R.id.list_view_messages);

        // Getting the person name from DataHolder
        DataHolder appData = DataHolder.getInstance();
        final String name = appData.getFirstName() + "_" + appData.getLastName();

        final List<Message> listMessages = new ArrayList<>();

        // Chat messages list adapter
        MessagesListAdapter adapter = new MessagesListAdapter(this, listMessages);

        listViewMessages.setAdapter(adapter);

        Draft[] drafts = {new Draft_6455()};

        /*
          If running this on an android device, make sure it is on the same network as your
          computer, and change the ip address to that of your computer.
          If running on the emulator, you can use localhost.
         */
        String w = "ws://cs309-pp-2.misc.iastate.edu:8080/websocket/" + name;

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w), drafts[0]) {
                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = t1.getText().toString();
                    //t1.setText("hello world");
                    //Log.d("first", "run() returned: " + s);
                    //s=t1.getText().toString();
                    //Log.d("second", "run() returned: " + s);
                    t1.setText(String.format("%s%s\n\n", s, message));

                    boolean mIsSelf = false;
                    try {
                        if (message.split(":")[1].contains(lastMsg)) {
                            mIsSelf = true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Message m = new Message(message.split(":")[0], message.split(":")[1], mIsSelf);
                    listMessages.add(m);
                    new Handler(Looper.getMainLooper()).post(new Runnable(){
                        @Override
                        public void run() {

                            if (inputMsg.length() > 0) {
                                inputMsg.getText().clear();
                            }
                        }
                    });

                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception:", e.toString());
                }
            };
        } catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage());
            e.printStackTrace();
        }
        cc.connect();

//            }
//        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cc.send(inputMsg.getText().toString());
                } catch (Exception e) {
                    Log.d("ExceptionSendMessage:", e.getMessage());
                }
                lastMsg = inputMsg.getText().toString();

                new Handler(Looper.getMainLooper()).post(new Runnable(){
                    @Override
                    public void run() {
                        if (inputMsg.length() > 0) {
                            inputMsg.getText().clear();
                        }
                    }
                });


            }
        });
    }

}
