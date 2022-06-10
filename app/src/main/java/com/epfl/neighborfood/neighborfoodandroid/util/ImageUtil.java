package com.epfl.neighborfood.neighborfoodandroid.util;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.Map;

/**
 * A utility class that allows image manipulation
 */
public class ImageUtil
{
    public static Intent getGalleryIntent(){
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }
    public static ActivityResultLauncher<Intent> getImagePickerActivityLauncher(ComponentActivity activity, ActivityResultCallback<ActivityResult> callback){
        return activity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                callback
                );

    }
    public  static String getRealPathFromUri(Uri imageUri, Activity activity){
        Cursor cursor = activity.getContentResolver().query(imageUri, null, null, null, null);

        if(cursor==null) {
            return imageUri.getPath();
        }else{
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
    public static Task<String> uploadImage(String imagePath){
        TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        uploadToCloudinary(imagePath,taskCompletionSource);

        return taskCompletionSource.getTask();
    }
    private static void uploadToCloudinary(String filePath, TaskCompletionSource<String> taskCompletionSource) {
        MediaManager.get().upload(filePath).callback(new UploadCallback() {
            @Override
            public void onStart(String requestId) {
            }

            @Override
            public void onProgress(String requestId, long bytes, long totalBytes) {
            }

            @Override
            public void onSuccess(String requestId, Map resultData) {
                taskCompletionSource.setResult(resultData.get("url").toString());
        }

        @Override
        public void onError(String requestId, ErrorInfo error) {
            taskCompletionSource.setException(new RuntimeException(error.toString()));
        }

        @Override
        public void onReschedule(String requestId, ErrorInfo error) {
        }
    }).dispatch();
}


}