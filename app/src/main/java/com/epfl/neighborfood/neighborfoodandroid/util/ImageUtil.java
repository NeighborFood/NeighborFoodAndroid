package com.epfl.neighborfood.neighborfoodandroid.util;

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