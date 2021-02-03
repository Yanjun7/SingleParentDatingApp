package com.team9.spda_team9.forum;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.team9.spda_team9.R;

public class ForumFragment extends Fragment implements View.OnClickListener {

    private ForumViewModel forumViewModel;
    Button r1,r2,r3,r4,r5,r6;
    Button l1,l2,l3,l4,l5,l6;
    Button[] buttons;
    Button[] lbuttons;
    boolean[] selected = new boolean[6];
    LinearLayout posts;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forumViewModel =
                new ViewModelProvider(this).get(ForumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_forum, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        TabLayout tabLayout = root.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("Personalized"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    for(int i =0;i<6;i++){

                        buttons[i].setVisibility(View.VISIBLE);
                        lbuttons[i].setVisibility(View.VISIBLE);

                    }
                }
                if(tab.getPosition()==1){
                    //NestedScrollView nsv =  root.findViewById(R.id.NSV);
                    //nsv.setVisibility(View.VISIBLE);
                    for(int i =0;i<6;i++){

                        buttons[i].setVisibility(View.GONE);
                        lbuttons[i].setVisibility(View.GONE);

                    }
                }
                if(tab.getPosition()==2){
                    for(int i =0;i<6;i++){
                        if(selected[i] == false){
                            buttons[i].setVisibility(View.GONE);
                            lbuttons[i].setVisibility(View.GONE);
                        }else{
                            buttons[i].setVisibility(View.VISIBLE);
                            lbuttons[i].setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    postInvisible();
                    for(int i =0;i<6;i++){

                        buttons[i].setVisibility(View.VISIBLE);
                        lbuttons[i].setVisibility(View.VISIBLE);

                    }
                }
                if(tab.getPosition()==1){
                    //NestedScrollView nsv =  root.findViewById(R.id.NSV);
                    //nsv.setVisibility(View.VISIBLE);
                    postInvisible();
                    topicInvisible();
                }
                if(tab.getPosition()==2){
                    postInvisible();
                    for(int i =0;i<6;i++){
                        if(selected[i] == false){
                            buttons[i].setVisibility(View.GONE);
                            lbuttons[i].setVisibility(View.GONE);
                        }else{
                            buttons[i].setVisibility(View.VISIBLE);
                            lbuttons[i].setVisibility(View.VISIBLE);
                        }
                    }
                }

            }
        });

        r1 = root.findViewById(R.id.r1);
        r2 = root.findViewById(R.id.r2);
        r3 = root.findViewById(R.id.r3);
        r4 = root.findViewById(R.id.r4);
        r5 = root.findViewById(R.id.r5);
        r6 = root.findViewById(R.id.r6);
        buttons = new Button[]{r1,r2,r3,r4,r5,r6};

        l1 = root.findViewById(R.id.l1);
        l2 = root.findViewById(R.id.l2);
        l3 = root.findViewById(R.id.l3);
        l4 = root.findViewById(R.id.l4);
        l5 = root.findViewById(R.id.l5);
        l6 = root.findViewById(R.id.l6);
        lbuttons = new Button[]{l1,l2,l3,l4,l5,l6};

        r1.setOnClickListener(this);
        l1.setOnClickListener(this);
        r2.setOnClickListener(this);
        r3.setOnClickListener(this);
        r4.setOnClickListener(this);
        r5.setOnClickListener(this);
        r6.setOnClickListener(this);

        posts = root.findViewById(R.id.posts);



        return root;

    }

    @Override
    public void onClick(View v){
        for(int i =0;i<6;i++){
            if(buttons[i] == v){
                if(selected[i] == false){
                    selected[i] = true;
                    buttons[i].setBackgroundColor(Color.RED);
                }else{
                    selected[i] = false;
                    buttons[i].setBackgroundColor(Color.GREEN);
                }
            }
        }
        if(v == l1){
            Intent intent = new Intent(getContext(), TopicDisplay.class);
            getContext().startActivity(intent);

        }

    }
    public void topicInvisible(){
        for(int i =0;i<6;i++){
            buttons[i].setVisibility(View.GONE);
            lbuttons[i].setVisibility(View.GONE);
        }
    }
    public void postVisible(){
        topicInvisible();
        posts.setVisibility(View.VISIBLE);
    }
    public void postInvisible(){
        posts.setVisibility(View.GONE);
    }
}