package adapters;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.hammadmansoor.ecommerce.R;

import java.util.List;

import utils.ShippingAddressModel;

public class ShippingAddressAdapter extends RecyclerView.Adapter<ShippingAddressAdapter.ViewHolder> {

    private List<ShippingAddressModel> shippingAddresses;
    private OnItemClickListener onItemClickListener;


    public ShippingAddressAdapter(List<ShippingAddressModel> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shipping_address_recycler, parent, false);
        return new ViewHolder(view);
    }


    public interface OnItemClickListener {
        void onItemClick(ShippingAddressModel address);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "Binding item at position: " + position);
        ShippingAddressModel address = shippingAddresses.get(position);
        Log.d(TAG, address.getAddressId() + position);
        holder.username.setText(address.getUsername());
        String fullAddress = address.getAddress()+ ", " + address.getCity() + ", " + address.getState()+ ", " + address.getCountry() + ".";
        holder.address.setText(fullAddress);
        holder.checkBox.setVisibility(View.VISIBLE);

        holder.editAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(address);
                }
            }
        });
        // Bind other address details if available
    }

    @Override
    public int getItemCount() {
        return shippingAddresses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,address,editAddress;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            address = itemView.findViewById(R.id.address);
            checkBox = itemView.findViewById(R.id.checkBox);
            editAddress = itemView.findViewById(R.id.editAddress);
            // Initialize other TextViews or views here
        }
    }
}