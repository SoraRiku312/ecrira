package com.williamtburch.sora.ecrira;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorldListFragment extends Fragment {

    //put constants of world types, world, country, city, etc

    private RecyclerView mWorldRecyclerView;
    private WorldAdapter mWorldAdapter;
    private List<World> worlds;
    private List<World> cityWorlds;
    private List<World> countryWorlds;
    private List<World> worldWorlds;
    private static final String ARG_WORLD_TYPE = "world_type";
    private int type;

    public static WorldListFragment newInstance(int type){
        WorldListFragment fragment = new WorldListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_WORLD_TYPE, type);
        fragment.setArguments(args);
        fragment.type = type;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.new_button:

                World test2 = new World();
                test2.setWorldType(type);
                if(type == 1){
                    test2.setWorldName("Test Country");
                } else if(type == 2){
                    test2.setWorldName("Test City");
                }
                WorldLab.get(getActivity()).addWorld(test2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.fragment_world_list, container,
                false);
        mWorldRecyclerView = (RecyclerView)view.findViewById(R.id.world_list_recycler_view);
        mWorldRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        cityWorlds = new ArrayList<>();
        worldWorlds = new ArrayList<>();
        countryWorlds = new ArrayList<>();

   //     type = (int)getArguments().getInt("ARG_WORLD_TYPE");


        World test2 = new World();
        test2.setWorldType(type);
        if(type == 1){
            test2.setWorldName("Test Country");
        } else if(type == 2){
            test2.setWorldName("Test City");
        }
        WorldLab.get(getActivity()).addWorld(test2);

        updateUI(getArguments().getInt("ARG_WORLD_TYPE"));
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI(type);
    }

    private void updateUI(int type){
        WorldLab worldLab = WorldLab.get(getActivity());
        worlds = worldLab.getWorlds();
        switch(type){
            case World.TYPE_CITY:
                for(World world : worlds){
                    if(world.getWorldType() == World.TYPE_CITY & !cityWorlds.contains(world)) {
                        cityWorlds.add(world);
                    }
                }
                        if(mWorldAdapter == null){
                            mWorldAdapter = new WorldAdapter(cityWorlds);
                            mWorldRecyclerView.setAdapter(mWorldAdapter);
                        } else{
                            mWorldAdapter.setWorlds(cityWorlds);
                            mWorldAdapter.notifyDataSetChanged();
                        }
                break;

            case World.TYPE_COUNTRY:
                for(World world : worlds) {
                    if (world.getWorldType() == World.TYPE_COUNTRY) {
                        countryWorlds.add(world);
                    }
                }
                        if(mWorldAdapter == null){
                            mWorldAdapter = new WorldAdapter(countryWorlds);
                            mWorldRecyclerView.setAdapter(mWorldAdapter);
                        } else{
                            mWorldAdapter.setWorlds(countryWorlds);
                            mWorldAdapter.notifyDataSetChanged();
                        }

                break;

            case World.TYPE_PLANET:
                for(World world : worlds) {
                    if (world.getWorldType() == World.TYPE_PLANET) {
                        worldWorlds.add(world);
                    }
                }
                        if(mWorldAdapter == null){
                            mWorldAdapter = new WorldAdapter(worldWorlds);
                            mWorldRecyclerView.setAdapter(mWorldAdapter);
                        } else{
                            mWorldAdapter.setWorlds(worldWorlds);
                            mWorldAdapter.notifyDataSetChanged();
                }

                break;
        }

//        if(mWorldAdapter == null){
//            mWorldAdapter = new WorldAdapter(worlds);
//            mWorldRecyclerView.setAdapter(mWorldAdapter);
//        } else{
//            mWorldAdapter.setWorlds(worlds);
//            mWorldAdapter.notifyDataSetChanged();
//        }
    }

    private class WorldHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private World mWorld;
        private TextView mWorldName;

        public WorldHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_world, parent, false));
            itemView.setOnClickListener(this);
            mWorldName = (TextView)itemView.findViewById(R.id.world_name);
        }


        @Override
        public void onClick(View view) {
            Intent intent = WorldActivity.newIntent(getActivity(), mWorld.getID());
            startActivity(intent);
        }

        public void bind(World world) {
            mWorld = world;

            mWorldName.setText(mWorld.getWorldName());


        }
    }


    private class WorldAdapter extends RecyclerView.Adapter<WorldHolder>{

        private List<World> mWorlds;

        public WorldAdapter(List<World> worlds){
            mWorlds = worlds;
        }

        @Override
        public WorldHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new WorldHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(WorldHolder holder, int position) {
            World world = mWorlds.get(position);
            holder.bind(world);
        }

        @Override
        public int getItemCount() {
            return mWorlds.size();
        }

        public void setWorlds(List<World> worlds) {
            mWorlds.clear();

            mWorlds = worlds;
        }

    }




    }









