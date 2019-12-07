package com.example.runningman.activities.groups;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.runningman.R;
import com.example.runningman.model.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * group JSON object (POST/PUT)
 * <p>
 * {
 * "userids" : [] (just ints, not objects of users, use this for number of users(size of list returned)),
 * "name" : "",
 * "bio" : "".
 * "zipcode" : int,
 * "photo" : [array of bites]
 * }
 */


public class CreateGroupActivity extends AppCompatActivity implements
        View.OnClickListener {

    public static final String TAG = CreateGroupActivity.class.getSimpleName();

    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Button submitButton;
    Button galleryButton;
    TextView groupName, groupBio, groupZip;
    ImageView groupImage2;
    Bitmap bitmap;
    Drawable drawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        submitButton = findViewById(R.id.createGroupSubmitButton);
        groupImage2 = findViewById(R.id.groupImage2);

        galleryButton = findViewById(R.id.galleryButton);
        galleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            groupImage2.setImageURI(imageUri);

//            try {
//                //InputStream inputStream = getContentResolver().openInputStream(imageUri);
//                //drawable = Drawable.createFromStream(inputStream, imageUri.toString() );
//
//            } catch (FileNotFoundException e) {
//                //drawable = getResources().getDrawable(R.drawable.nav_groups);
//
//            }
        }
    }


    @Override
    public void onClick(View v) {

        if (v == submitButton) {
            try {
                //int user_id = DataHolder.getInstance().getUserId();

                groupName = findViewById(R.id.groupNameInput);
                String name = groupName.getText().toString();
                groupBio = findViewById(R.id.groupBioInput);
                String bio = groupBio.getText().toString();
                groupZip = findViewById(R.id.groupZipInput);
                String zip = groupZip.getText().toString();

                JSONObject group = new JSONObject();
                group.put("name", name);
                group.put("bio", bio);
                group.put("zipcode", zip);

                // create bitmap for byte array to send chosen group image to server

                    ImageView imageView = findViewById(R.id.groupImage2);
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] imageInByte = baos.toByteArray();
                    //String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

                //Log.d(TAG, "Response is: " + byteArray.toString());

                group.put("bytes", imageInByte.toString());


                String addUrl = "http://cs309-pp-2.misc.iastate.edu:8080/addgroup";
                JsonObjectRequest postRequest = new JsonObjectRequest
                        (Request.Method.POST, addUrl, group, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d(TAG, "Got a good response");
                                Intent intent = new Intent(CreateGroupActivity.this, CreateGroupConfirmationActivity.class);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, error.getMessage());
                            }
                        }) {
                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                        String json = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            json = new String(
                                    response.data,
                                    StandardCharsets.UTF_8
                            );
                        }

                        if (json.length() == 0) {
                            return Response.success(
                                    null,
                                    HttpHeaderParser.parseCacheHeaders(response)
                            );
                        } else {
                            return super.parseNetworkResponse(response);
                        }

                    }
                };
                MySingleton.getInstance(CreateGroupActivity.this).addToRequestQueue(postRequest);

            } catch (JSONException e) {
                Toast.makeText(CreateGroupActivity.this, "JSON ERROR", Toast.LENGTH_LONG).show();
                Log.d(TAG, e.getLocalizedMessage());
            }


        }
    }
}

