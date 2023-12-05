package helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUploadHelper {

    private final Context context;

    public ImageUploadHelper(Context context) {
        this.context = context;
    }

    public Task<String> uploadProductImage(Uri imageUri) {
        final TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();

        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);

            // Convert the Bitmap to a byte array
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] imageData = baos.toByteArray();

            // Generate a unique file name for the image
            String fileName = UUID.randomUUID().toString() + ".jpg";

            // Upload the image to Firebase Storage
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference imageRef = storageRef.child("images/" + fileName);

            UploadTask uploadTask = imageRef.putBytes(imageData);

            // Continue with getting the download URL when the upload is successful
            uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    return imageRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        String downloadUrl = task.getResult().toString();
                        taskCompletionSource.setResult(downloadUrl);
                    } else {
                        // Handle the error
                        taskCompletionSource.setException(task.getException());
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            taskCompletionSource.setException(e);
        }

        return taskCompletionSource.getTask();
    }

    public Task<Bitmap> downloadImage(String imageUrl) {
        final TaskCompletionSource<Bitmap> taskCompletionSource = new TaskCompletionSource<>();

        // Parse the image URL to get the path in Firebase Storage
        Uri uri = Uri.parse(imageUrl);
        String path = uri.getPath();

        // Get a reference to the image in Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference(path);

        // Download the image as a byte array
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(bytes -> {
                    // Convert the byte array to a Bitmap
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    taskCompletionSource.setResult(bitmap);
                })
                .addOnFailureListener(exception -> {
                    // Handle the download failure
                    taskCompletionSource.setException(exception);
                });

        return taskCompletionSource.getTask();
    }
}

