package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hammadmansoor.ecommerce.R;

import java.util.List;

import utils.CartItemModel;

public class BagAdapter extends RecyclerView.Adapter<BagAdapter.ViewHolder> {

    private Context context;
    private List<CartItemModel> cartItems;

    public BagAdapter(Context context, List<CartItemModel> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }



    @NonNull
    @Override
    public BagAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BagAdapter.ViewHolder holder, int position) {
        CartItemModel cartItem = cartItems.get(position);
        holder.itemName.setText(cartItem.getProduct().getName());
        holder.itemSize.setText(cartItem.getSize());
        holder.itemColor.setText(cartItem.getColor());
        holder.itemPrice.setText((int) cartItem.getTotalPrice());
        Glide.with(context)
                .load(cartItem.getProduct().getImageUrl())
                .placeholder(R.drawable.newpic)
                .into(holder.itemImage);

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView itemImage;
        TextView  itemName,itemSize,itemColor,itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            itemColor = itemView.findViewById(R.id.itemColour);
            itemSize = itemView.findViewById(R.id.itemSize);
            itemImage = itemView.findViewById(R.id.itemImage);

        }
    }
}
