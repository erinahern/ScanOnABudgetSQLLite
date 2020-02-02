package com.mad1.scanonabudget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.text.NumberFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    TextView titletextView;
    TextView descriptionTextView;
    private Context Ctx;
    private List<Item> ItemList;
    ScanDB db;
    tab2content tab2content;
    private List<ListTable> ListTableList;
    private List<ListTable> mAdapter;
    tab1content tab1content = new tab1content();
    public ItemAdapter(Context ctx, List<Item> ItemList) {
        this.Ctx = ctx;
        this.ItemList = ItemList;
    }


    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.list_layout, null);

        db = new ScanDB(Ctx);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, final int position) {
        final Item Item = ItemList.get(position);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String cur = currencyFormatter.format(Item.getPrice());
        holder.textViewTitle.setText(cur);
        holder.textViewDescription.setText(Item.getStore());
        if(position==0) {
            //titletextView.setTextColor(360);
            titletextView.setTextColor(Color.parseColor("#000000"));
            titletextView.setText(Item.getName());
            titletextView.setTextSize(23);
            descriptionTextView.setText(Item.getDecription());
           // descriptionTextView.setTextColor(360);
        }else if (position >0){

            descriptionTextView.setMaxHeight(0);
            titletextView.setMaxHeight(0);
        }
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PopupMenu
                PopupMenu popup = new PopupMenu(Ctx, view);
                popup.getMenuInflater().inflate(R.menu.dropdown_menu, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item1:
                                    db.addNewList(Item.getName(), Item.getPrice(), Item.getStore());
                                break;
                            case R.id.item2:
                                //SHARE
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setType("text/plain");
                                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Low Price");
                                shareIntent.putExtra(Intent.EXTRA_TEXT, Item.getName()+"\n\n"+
                                        Item.getDecription()+"\n\n"+ Item.getStore()+"\n\n"+ Item.getPrice());
                                Ctx.startActivity(Intent.createChooser(shareIntent, "Share via"));

                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder{
        public BreakIterator textViewDescription2;
        TextView textViewTitle, textViewDescription;
        ImageView icon;
        RelativeLayout relativeLayout;
        public ItemViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            icon = itemView.findViewById(R.id.menuImageButton);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            titletextView = itemView.findViewById(R.id.titletextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }

}
