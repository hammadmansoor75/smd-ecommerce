package adapters;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hammadmansoor.ecommerce.R;

import java.util.List;

import utils.PaymentMethodModel;
import utils.ShippingAddressModel;

public class PaymentMethodsAdapter extends RecyclerView.Adapter<PaymentMethodsAdapter.ViewHolder> {

    private List<PaymentMethodModel> paymentMethods;

    public PaymentMethodsAdapter(List<PaymentMethodModel> paymentMethods){
        this.paymentMethods = paymentMethods;
    }
    @NonNull
    @Override
    public PaymentMethodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_recycler, parent, false);
        return new PaymentMethodsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodsAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "Binding item at position: " + position);
        PaymentMethodModel paymentMethodModel = paymentMethods.get(position);
        Log.d(TAG,paymentMethodModel.toString());
//        Log.d(TAG, paymentMethodModel.getAddressId() + position);
        holder.name.setText(paymentMethodModel.getName());
        holder.cardNumber.setText(paymentMethodModel.getCardNumber());

    }

    @Override
    public int getItemCount() {
        return paymentMethods.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cardNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cardNumber = itemView.findViewById(R.id.cardNumber);
        }
    }
}
