package com.team9.spda_team9.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.fragment.app.ListFragment;

import com.team9.spda_team9.R;


public class MyCategoryList extends ListFragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Categories, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {

        if(position==0){
            Intent dating = new Intent(view.getContext(), TopicDisplay.class);
            startActivity(dating);
        }

        /*
        Intent intent = new Intent(view.getContext(), TopicDisplay.class);
        String[] message = getResources().getStringArray(R.array.Categories);
        String itemClicked = message[position];
        intent.putExtra("selected", itemClicked);
        startActivity(intent);

         */
    }
}