package com.siri.comiccharacters;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class HeroListFragment extends Fragment {
    private RecyclerView mHeroRecyclerView;
    private HeroAdapter mAdapter;
    private List<Hero> mHeros = new ArrayList<>();

    public static HeroListFragment newInstance() { return new HeroListFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemsTask().execute();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hero_list, container, false);
        mHeroRecyclerView = (RecyclerView) v.findViewById(R.id.hero_recycler_view);
        mHeroRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter();

        return v;
    }

    private class HeroHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Hero mHero;
        private TextView mNameTextView;
        private TextView mRealNameTextView;
        private ImageView mPhotoImageView;


        public HeroHolder(LayoutInflater inflater, ViewGroup viewGroup) {

            super(inflater.inflate(R.layout.list_hero_card, viewGroup, false));
            itemView.setOnClickListener(this);

            mPhotoImageView = (ImageView) itemView.findViewById(R.id.photo_image_view);
            mNameTextView = (TextView) itemView.findViewById(R.id.name_text_view);
            mRealNameTextView = (TextView) itemView.findViewById(R.id.real_name_text_view);

        }

        public void bind(Hero hero) {

            mHero = hero;
            mNameTextView.setText(mHero.getName());
            mRealNameTextView.setText(mHero.getRealName());
            Picasso.get().load(mHero.getUrl()).resize(400, 420).centerCrop().into(mPhotoImageView);

        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(), mNameTextView + " clicked", Toast.LENGTH_SHORT).show();
//            Store.get(getActivity()).addStudent(mStudent);

            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("NAME", (String) mNameTextView.getText());
            intent.putExtra("REALNAME", (String) mRealNameTextView.getText());
            intent.putExtra("URL", (String) mHero.getUrl());
            startActivity(intent);
        }
    }


    private class HeroAdapter extends RecyclerView.Adapter<HeroHolder> {

        private List<Hero> mHeros;

        public HeroAdapter(List<Hero> heroes) {

            mHeros = heroes;
        }


        @Override
        public HeroHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HeroHolder(layoutInflater, parent);

        }


        @Override
        public void onBindViewHolder(HeroHolder holder, int position) {
            Hero h = mHeros.get(position);
            holder.bind(h);
        }


        @Override
        public int getItemCount() {

            return mHeros.size();
        }

        public void setHeros(List<Hero> heros) {

            mHeros = heros;
        }

    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<Hero>> {
        @Override
        protected List<Hero> doInBackground(Void... params) {
            return new ApiFetch().fetchItems();
        }

        @Override
        protected void onPostExecute(List<Hero> heroes) {
            mHeros = heroes;
//            new HeroAdapter(mHeros);
            setupAdapter();


        }
    }

    private void setupAdapter() {
        if (isAdded()) {
            mHeroRecyclerView.setAdapter(new HeroAdapter(mHeros));
        }
    }




}
