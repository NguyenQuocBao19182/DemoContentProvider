package com.example.administrator.democontentprovider.Screen;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.administrator.democontentprovider.Object.Contact;
import com.example.administrator.democontentprovider.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    ArrayList<Contact> mListContact = new ArrayList<>();
    private final int REQUEST_READ_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        displayRecyclerView();
        checkAndRequestPermissions();
    }

    private void checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[] { Manifest.permission.READ_CONTACTS }, REQUEST_READ_CONTACT);
            }
            return;
        }
        loadContact();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyler_view);
    }

    private void displayRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        //StaggeredGridLayoutManager(int spanCount, int orientation)
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        Contact_Adapter contactAdapter = new Contact_Adapter(this, mListContact);
        mRecyclerView.setAdapter(contactAdapter);
    }

    private void loadContact() {
        // Đối số đầu tiên của query là URI
        Cursor mCursor =
                getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        null, null, null);

        if (mCursor != null) {
            //Nếu Cursor không null, di chuyển cursor đến hàng(row) tiếp theo
            while (mCursor.moveToNext()) {
                mListContact.add(new Contact(mCursor.getString(mCursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)), mCursor.getString(
                        mCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))));
            }
            mCursor.close();
        }
    }
}
