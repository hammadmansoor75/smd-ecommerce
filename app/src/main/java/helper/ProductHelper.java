package helper;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hammadmansoor.ecommerce.AdminAddProduct;

import utils.ProductModel;

public class ProductHelper {

    public static void uploadProduct(ProductModel productModel) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("products").add(productModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {

            }
        });
    }


}
