package com.example.administrator.democontentprovider.Screen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.democontentprovider.Object.Contact;
import com.example.administrator.democontentprovider.R;
import java.util.ArrayList;

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Contact> mListConTact;

    public Contact_Adapter(Context context, ArrayList<Contact> listConTact) {
        mContext = context;
        mListConTact = listConTact;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return mListConTact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewName, mTextViewPhone;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_Name);
            mTextViewPhone = itemView.findViewById(R.id.textview_phone);
        }

        public void setData(int position) {
            Contact contact = mListConTact.get(position);
            mTextViewName.setText(contact.getName());
            mTextViewPhone.setText(contact.getPhoneNumber());
        }
    }
}