package com.ojm.flashcardapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ojm.flashcardapp.Cards.Deck;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Omar on 4/2/2016.
 */
public class DeckListAdapter extends RecyclerView.Adapter<DeckListAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private List<Deck> deckArray = Collections.emptyList();
    private Context mContext;

    public DeckListAdapter(Context context, List<Deck>deckArray){
        inflater = LayoutInflater.from(context);

        this.mContext = context;
        this.deckArray = deckArray;
    }

    @Override
    public DeckListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.deck_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DeckListAdapter.ViewHolder holder, int position) {
        final Deck currentDeck = deckArray.get(position);

        holder.rowTitleTextView.setText(currentDeck.getDeckName());
        holder.rowDescriptionTextView.setText(currentDeck.getDescription());

    }

    @Override
    public int getItemCount() {
        return deckArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.rowTitleTextView) TextView rowTitleTextView;
        @Bind(R.id.rowDescriptionTextView) TextView rowDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), DeckOptionsActivity.class);
            intent.putExtra("DECK_ID", deckArray.get(getAdapterPosition()).getDeckId());
            mContext.startActivity(intent);
        }
    }
}
