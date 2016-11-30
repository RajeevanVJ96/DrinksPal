package project.hci.hciproject;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import project.hci.hciproject.realm.Drink;

/**
 * Created by jake on 11/29/16.
 */

public class ListDrinksAdapter extends RecyclerView.Adapter<ListDrinksAdapter.ViewHolder> {

    static int selectedPos;

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.item_name);
        }
    }

    // Store a member variable for the contacts
    private List<Drink> mItems;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor

    public ListDrinksAdapter(Context context, List<Drink> items) {
        mItems = items;
        mContext = context;
    };

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ListDrinksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_contact, parent, false);

        // Return a new holder instance
        ListDrinksAdapter.ViewHolder viewHolder = new ListDrinksAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListDrinksAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.itemView.setSelected(selectedPos == position);

        Drink contact = mItems.get(position);

        viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                notifyItemChanged(selectedPos);
                selectedPos = position;
                notifyItemChanged(selectedPos);

            }
        });


        // Set item views based on your views and data model
        String result = contact.getDrink_name() + "   £"+ String.valueOf(contact.getPrice());
        TextView textView = viewHolder.nameTextView;
        textView.setText(result);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mItems.size();
    }
}