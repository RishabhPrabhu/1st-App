package com.example.f1.picado;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.provider.MediaStore.Files.FileColumns;
import android.widget.Toast;

import com.example.f1.picado.Data.TaskData;
import com.example.f1.picado.Model.Tasks;

import java.io.File;
import java.io.IOException;

import me.drakeet.materialdialog.MaterialDialog;

public class take_note extends AppCompatActivity {

    private EditText title;
    private EditText description;
    private EditText contact_no;
    private ImageView contact;
    private ImageView gallery;
    private ImageView camera;
    private ImageView imagePreview;
    private LinearLayout todo;
    private LinearLayout anniversary;
    private LinearLayout birthday;
    private LinearLayout event;
    private LinearLayout bill;
    private LinearLayout alarm_layout;
    private LinearLayout repeat_layout;
    private TextView ringtone;
    private TextView vibrate;
    private TextView time;
    private TextView todo_text;
    private TextView birthday_text;
    private TextView anniversary_text;
    private TextView event_text;
    private TextView bill_text;
    private TextView repeat_daily;
    private TextView repeat_weekly;
    private TextView repeat_monthly;
    private TextView repeat_yearly;
    private Switch alarm_switch;
    private Switch repeat_switch;

    private TaskData taskData;
    private Tasks tasks;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private File fileUri;

    static final int REQUEST_SELECT_PHONE_NUMBER = 1;
    public static final int MEDIA_TYPE_IMAGE = 3;

    private static final String IMAGE_DIRECTORY_NAME = "Pic A Do";

    private static final int PICK_IMAGE = 2;
    private Bitmap bitmap;

    private boolean todo_counter = false;
    private boolean anniversary_counter = false;
    private boolean birthday_counter = false;
    private boolean event_counter = false;
    private boolean bill_counter = false;
    private boolean repeat_daily_counter = false;
    private boolean repeat_weekly_counter = false;
    private boolean repeat_monthly_counter = false;
    private boolean repeat_yearly_counter = false;
    //AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_note);

        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        contact_no = (EditText) findViewById(R.id.contact_no);

        camera = (ImageView) findViewById(R.id.camera);
        gallery = (ImageView) findViewById(R.id.gallery);
        contact = (ImageView) findViewById(R.id.contact);
        imagePreview = (ImageView) findViewById(R.id.imagePreview);

        todo = (LinearLayout) findViewById(R.id.todo);
        anniversary = (LinearLayout) findViewById(R.id.anniversary);
        birthday = (LinearLayout) findViewById(R.id.birthday);
        event = (LinearLayout) findViewById(R.id.event);
        bill = (LinearLayout) findViewById(R.id.bill);
        alarm_layout = (LinearLayout) findViewById(R.id.alarm_layout);
        repeat_layout = (LinearLayout) findViewById(R.id.repeat_layout);

        ringtone = (TextView) findViewById(R.id.ringtone);
        vibrate = (TextView) findViewById(R.id.vibrate);
        time = (TextView) findViewById(R.id.time);
        todo_text = (TextView) findViewById(R.id.todo_text);
        birthday_text = (TextView) findViewById(R.id.birthday_text);
        bill_text = (TextView) findViewById(R.id.bill_text);
        anniversary_text = (TextView) findViewById(R.id.anniversary_text);
        event_text = (TextView) findViewById(R.id.event_text);
        repeat_daily = (TextView) findViewById(R.id.repeat_daily);
        repeat_weekly = (TextView) findViewById(R.id.repeat_weekly);
        repeat_monthly = (TextView) findViewById(R.id.repeat_monthly);
        repeat_yearly = (TextView) findViewById(R.id.repeat_yearly);

        alarm_switch = (Switch) findViewById(R.id.alarm_switch);
        repeat_switch = (Switch) findViewById(R.id.repeat_switch);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasks = new Tasks(take_note.this);

        //click listener
        gallery.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImageFromGallery();
            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                fileUri = take_note.this.getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

                // start the image capture Intent
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectContact();
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the textview color
                //todo_text.setTextColor(take_note.this.getResources().getColor(R.color.colorPrimaryLight));
                todo_counter = !todo_counter;
                isActive(1);
            }
        });
        anniversary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the textview color
                //todo_text.setTextColor(take_note.this.getResources().getColor(R.color.colorPrimaryLight));
                anniversary_counter = !anniversary_counter;
                isActive(2);
            }
        });
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the textview color
                //todo_text.setTextColor(take_note.this.getResources().getColor(R.color.colorPrimaryLight));
                birthday_counter = !birthday_counter;
                isActive(3);
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the textview color
                //todo_text.setTextColor(take_note.this.getResources().getColor(R.color.colorPrimaryLight));
                event_counter = !event_counter;
                isActive(4);
            }
        });
        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change the textview color
                //todo_text.setTextColor(take_note.this.getResources().getColor(R.color.colorPrimaryLight));
                bill_counter = !bill_counter;
                isActive(5);
            }
        });
        alarm_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    alarm_layout.setVisibility(View.VISIBLE);
                else
                    alarm_layout.setVisibility(View.GONE);
            }
        });

        ringtone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
            });
        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });
        repeat_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    repeat_layout.setVisibility(View.VISIBLE);
                else
                    repeat_layout.setVisibility(View.GONE);
            }
        });
        repeat_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_daily_counter = !repeat_daily_counter;
                isActiveRepeat(1);
            }
        });
        repeat_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_weekly_counter = !repeat_weekly_counter;
                isActiveRepeat(2);
            }
        });
        repeat_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_monthly_counter = !repeat_monthly_counter;
                isActiveRepeat(3);
            }
        });
        repeat_yearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_yearly_counter = !repeat_yearly_counter;
                isActiveRepeat(4);
            }
        });


    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFileUri(int type) {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created 8images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) if (!mediaStorageDir.mkdirs()) {
            Log.d("MyCameraApp", "failed to create directory");
        }
        return mediaStorageDir;
    }

    public void selectContact() {
        // Start an activity for the user to pick a phone number from contacts
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_SELECT_PHONE_NUMBER);
        }
    }

    public void selectImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public void isActiveRepeat(int type_id) {
        switch (type_id) {
            case 1:
                if (repeat_daily_counter) {
                    repeat_daily.setTextColor(0xFFFBC02D);
                } else {
                    repeat_daily.setTextColor(0xFF212121);
                }
                repeat_weekly.setTextColor(0xFF212121);
                repeat_monthly.setTextColor(0xFF212121);
                repeat_yearly.setTextColor(0xFF212121);
                break;
            case 2:
                repeat_daily.setTextColor(0xFF212121);
                if (repeat_weekly_counter) {
                    repeat_weekly.setTextColor(0xFFFBC02D);
                } else {
                    repeat_weekly.setTextColor(0xFF212121);
                }
                repeat_monthly.setTextColor(0xFF212121);
                repeat_yearly.setTextColor(0xFF212121);
                break;
            case 3:
                repeat_daily.setTextColor(0xFF212121);
                repeat_weekly.setTextColor(0xFF212121);
                if (repeat_monthly_counter) {
                    repeat_monthly.setTextColor(0xFFFBC02D);
                } else {
                    repeat_monthly.setTextColor(0xFF212121);
                }
                repeat_yearly.setTextColor(0xFF212121);
                break;
            case 4:
                repeat_daily.setTextColor(0xFF212121);
                repeat_weekly.setTextColor(0xFF212121);
                repeat_monthly.setTextColor(0xFF212121);
                if (repeat_yearly_counter) {
                    repeat_yearly.setTextColor(0xFFFBC02D);
                } else {
                    repeat_yearly.setTextColor(0xFF212121);
                }
                break;
            default:
                Toast.makeText(getApplicationContext(),
                        "Error in setting repeat status", Toast.LENGTH_SHORT)
                        .show();

        }
    }


    public void isActive(int type_id) {
        switch (type_id) {
            case 1:
                if (todo_counter)
                    todo_text.setTextColor(0xFFFFFFFF);
                else
                    todo_text.setTextColor(0xFF212121);
                anniversary_text.setTextColor(0xFF212121);
                birthday_text.setTextColor(0xFF212121);
                event_text.setTextColor(0xFF212121);
                bill_text.setTextColor(0xFF212121);
                break;
            case 2:
                todo_text.setTextColor(0xFF212121);
                if (anniversary_counter)
                    anniversary_text.setTextColor(0xFFFFFFFF);
                else
                    anniversary_text.setTextColor(0xFF212121);
                birthday_text.setTextColor(0xFF212121);
                event_text.setTextColor(0xFF212121);
                bill_text.setTextColor(0xFF212121);
                break;
            case 3:
                todo_text.setTextColor(0xFF212121);
                anniversary_text.setTextColor(0xFF212121);
                if (birthday_counter)
                    birthday_text.setTextColor(0xFFFFFFFF);
                else
                    birthday_text.setTextColor(0xFF212121);
                event_text.setTextColor(0xFF212121);
                bill_text.setTextColor(0xFF212121);
                break;
            case 4:
                todo_text.setTextColor(0xFF212121);
                anniversary_text.setTextColor(0xFF212121);
                birthday_text.setTextColor(0xFF212121);
                if (event_counter)
                    event_text.setTextColor(0xFFFFFFFF);
                else
                    event_text.setTextColor(0xFF212121);
                bill_text.setTextColor(0xFF212121);
                break;
            case 5:
                todo_text.setTextColor(0xFF212121);
                anniversary_text.setTextColor(0xFF212121);
                birthday_text.setTextColor(0xFF212121);
                event_text.setTextColor(0xFF212121);
                if (bill_counter)
                    bill_text.setTextColor(0xFFFFFFFF);
                else
                    bill_text.setTextColor(0xFF212121);
                break;
            default:
                Toast.makeText(getApplicationContext(),
                        "Error in setting type", Toast.LENGTH_SHORT)
                        .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PHONE_NUMBER && resultCode == RESULT_OK) {
            contact_no.setVisibility(View.VISIBLE);
            // Get the URI and query the content provider for the phone number
            Uri contactUri = data.getData();
            Log.v("rishabh", contactUri.toString());
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(contactUri, projection,
                    null, null, null);
            // If the cursor returned is valid, get the nameif (cursor != null && cursor.moveToFirst()) {
            if (cursor != null && cursor.moveToFirst()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameIndex);
                // Do something with the name
                title.setText(name);
            }
            String[] projection1 = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
            cursor = getContentResolver().query(contactUri, projection1,
                    null, null, null);
            // If the cursor returned is valid, get the phone number
            if (cursor != null && cursor.moveToFirst()) {
                int numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberIndex);
                // Do something with the phone number
                contact_no.setText(number);
            }
        }

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                //Setting the Bitmap to ImageView
                imagePreview.setImageBitmap(bitmap);
                imagePreview.setVisibility(View.VISIBLE);

            } catch (IOException e) {
                // failed to show image
                Toast.makeText(getApplicationContext(),
                        "Error", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(this,
                        "Process cancelled by user", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Error", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void previewCapturedImage() {
        try {
            // hide video preview

            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();
            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);

            //Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 300, 300, true);

            imagePreview.setImageBitmap(bitmap);

            imagePreview.setVisibility(View.VISIBLE);
        } catch (NullPointerException e) {


            Toast.makeText(getApplicationContext(),
                    "Error", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.take_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.save) {/*
                //alert dialog box
                new MaterialDialog.Builder(this)
                        .title(R.string.title)
                        .content(R.string.content)
                        .positiveText(R.string.agree)
                        .negativeText(R.string.disagree)
                        .show();*/


            //start all notes activity
            startActivity(new Intent(take_note.this, ViewAllNotes.class));
        }
        if (id == R.id.reset) {
            Intent intent = new Intent(take_note.this, take_note.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}

