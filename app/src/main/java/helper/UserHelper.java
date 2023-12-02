package helper;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import utils.UserModel;

public class UserHelper {

    static FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public static void getUserData(String userId, final OnUserDataListener listener) {
        firestore.collection("users")
                .document(userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // User data found, create a User object and notify the listener
                            UserModel user = new UserModel(document.getString("name"),document.getString("email"),document.getString("date"),userId);
                            listener.onUserDataLoaded(user);
                        } else {
                            // User data not found
                            Log.d(TAG, "No such document");
                            listener.onUserDataNotFound();
                        }
                    } else {
                        // Error getting user data
                        Log.d(TAG, "Error getting document", task.getException());
                        listener.onUserDataError(task.getException());
                    }
                });
    }

    public interface OnUserDataListener {
        void onUserDataLoaded(UserModel document);

        void onUserDataNotFound();

        void onUserDataError(Exception e);
    }

}
