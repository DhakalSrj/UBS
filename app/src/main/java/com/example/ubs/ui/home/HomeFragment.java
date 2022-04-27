package com.example.ubs.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.ubs.R;
import com.example.ubs.Users;
import com.example.ubs.databinding.FragmentHomeBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment  {

    private FragmentHomeBinding binding;
    public EditText mSearchField;
    public ImageButton mImageButton;
    public RecyclerView mResultList;
    private FirebaseDatabase database;
    private DatabaseReference mUserDatabase;
    private FirebaseRecyclerAdapter<Users, UserViewHolder> firebaseRecyclerAdapter;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getContext();
        database = FirebaseDatabase.getInstance();
        mUserDatabase = database.getReference("Users");
        mSearchField = (EditText) view.findViewById(R.id.search_field);
        mImageButton = (ImageButton) view.findViewById(R.id.imageButton);
        mResultList = (RecyclerView) view.findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        mResultList.setLayoutManager(layoutManager);
        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText =mSearchField.getText().toString();
                firebaseUserSearch(searchText);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void firebaseUserSearch(String searchText){
        Toast.makeText(context,"Started Search",Toast.LENGTH_LONG).show();
        Query firebaseSearchQuery =mUserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

       FirebaseRecyclerOptions<Users> options =new FirebaseRecyclerOptions.Builder<Users>()
                .setQuery(firebaseSearchQuery,Users.class)
                .build();
         firebaseRecyclerAdapter =new FirebaseRecyclerAdapter<Users,UserViewHolder>(options) {
             @NonNull
             @Override
             public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                 View view = LayoutInflater.from(parent.getContext())
                         .inflate(R.layout.list_layout, parent, false);

                 return new UserViewHolder(view);
             }

            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull Users model) {
                    holder.setDetails(context,model.getName(),model.getStatus(), model.getImage());

            }
        };
        firebaseRecyclerAdapter.startListening();
        firebaseRecyclerAdapter.notifyDataSetChanged();
        mResultList.setAdapter(firebaseRecyclerAdapter);
    }

    //view holder class
    public static class UserViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setDetails(Context ctx, String userName, String userStatus, String userImage){
            TextView user_name =(TextView) mView.findViewById(R.id.name_text);
            TextView user_status= (TextView) mView.findViewById(R.id.status_text);
            CircleImageView user_image =(CircleImageView)  mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            user_status.setText(userStatus);

            Glide.with(ctx).load(userImage).into(user_image);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}