package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.Model.AnonymousChat;
import com.example.myapplication.Model.Chat;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class AnonymousMessageAdapter extends RecyclerView.Adapter<AnonymousMessageAdapter.ViewHolder>{

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    private Context mContext;
    private List<AnonymousChat> mChat;
    private String imageurl;

    FirebaseUser fuser;

    public AnonymousMessageAdapter(Context mContext, List<AnonymousChat> mChat, String imageurl)
    {
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public AnonymousMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == MSG_TYPE_RIGHT)
        {
            View view = LayoutInflater.from(mContext).inflate(R.layout.anonymous_chat_right,parent,false);
            return new AnonymousMessageAdapter.ViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(mContext).inflate(R.layout.anonymous_chat_left,parent,false);
            return new AnonymousMessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AnonymousMessageAdapter.ViewHolder holder, int position) {

        AnonymousChat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());

        holder.profile_image.setImageResource(R.mipmap.ic_launcher);

        holder.txt_time.setText(chat.getTime());
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_time;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txt_time = itemView.findViewById(R.id.txt_time);
            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if(mChat.get(position).getSender().equals(fuser.getUid()))
        {
            return MSG_TYPE_RIGHT;
        }
        else
        {
            return MSG_TYPE_LEFT;
        }
    }
}
