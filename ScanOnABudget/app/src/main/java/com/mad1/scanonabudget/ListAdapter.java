package com.mad1.scanonabudget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    TextView titletextView;
    TextView descriptionTextView;
    TextView textViewDescription2;
    private Context Ctx;
    private List<ListTable> ListTableList;
    ScanDB db;
    public ListAdapter(Context ctx, List<ListTable> ListTableList) {
        Ctx = ctx;
        this.ListTableList = ListTableList;
    }


    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.tab2_list_layout, null);

        db = new ScanDB(Ctx);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, final int position) {
        final ListTable ListTable = ListTableList.get(position);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String cur = currencyFormatter.format(ListTable.getItem_price());
        holder.textViewTitle.setText(ListTable.getItem_name());
        holder.textViewDescription.setText(ListTable.getItem_store());
        holder.textViewDescription2.setText(cur);

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PopupMenu
                PopupMenu popup = new PopupMenu(Ctx, view);
                popup.getMenuInflater().inflate(R.menu.dropdown_menu_list, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item1:
                                //DELETE
                                createAlert(ListTable.getList_id(), position);
                                break;
                            case R.id.item2:
                                //SHARE
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setType("text/plain");
                                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Low Price");
                                shareIntent.putExtra(Intent.EXTRA_TEXT, ListTable.getItem_name()+"\n\n"+
                                        ListTable.getItem_store()+"\n\n"+ ListTable.getItem_price());
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
        return ListTableList.size();
    }


    class ListViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTitle, textViewDescription, textViewDescription2;
        ImageView icon;
        RelativeLayout relativeLayout;
        public ListViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            icon = itemView.findViewById(R.id.menuImageButton);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
            titletextView = itemView.findViewById(R.id.titletextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            textViewDescription2 = itemView.findViewById(R.id.textViewDescription2);
        }
    }

    public void createAlert(final int i, final int j){
        AlertDialog.Builder builder = new AlertDialog.Builder(Ctx);
        builder.setCancelable(true);
        builder.setTitle("Deleting item");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE
                        db.deleteList(i);
                        ListTableList.remove(j);
                        notifyItemRemoved(j);
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(Ctx, "Delete Successful", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //DO NOTHING
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
