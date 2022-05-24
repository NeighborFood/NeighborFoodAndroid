package com.epfl.neighborfood.neighborfoodandroid.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.epfl.neighborfood.neighborfoodandroid.models.User;
import com.epfl.neighborfood.neighborfoodandroid.repositories.AuthRepository;
import com.epfl.neighborfood.neighborfoodandroid.repositories.UserRepository;
import com.epfl.neighborfood.neighborfoodandroid.util.ImageUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;


/**
 * The Profile Editing activity ViewModel
 * It serves as the entry point to the models and handles checking the user parameters
 */
public class EditProfileViewModel extends ViewModel {
    private User currentUser;
    private final AuthRepository authRepo;
    private final UserRepository userRepo;

    public EditProfileViewModel(AuthRepository authRepo, UserRepository userRepo) {
        this.authRepo = authRepo;
        this.userRepo = userRepo;

    }

    /**
     * Loads the currently authenticated user from the database
     *
     * @return the task that will contain the currently authenticated user
     */
    public Task<User> loadCurrentUser() {

        if (currentUser == null) {
            return userRepo.getUserById(authRepo.getAuthUser().getId()).addOnSuccessListener(user->currentUser= user);
        }
        return Tasks.forResult(currentUser);
    }

    public User getCurrentUser(){
        return currentUser;
    }


    /**
     * Updates the currently authenticated user with the user passed as parameter, with an upload of the profile picture
     *
     * @param user : The user with updated fields
     * @param filePath : the path of the file
     * @return the task that may complete, fails if:
     * -there's no currently authenticated user
     * -the user attributes to update are not correct
     * -the updates are for a user that is not the currently authenticated user
     */
    public Task<Void> updateUser(User user, String filePath) {
        if (user == null) {
            return Tasks.forException(new IllegalArgumentException("Cannot update null user"));
        }
        if(currentUser == null){
            return Tasks.forException(new IllegalStateException("No authenticated user in the app"));
        }
        //Verify user attributes
        if (!user.getId().equals(currentUser.getId())) {
            return Tasks.forException(new IllegalArgumentException("Cannot update attributes for another user"));
        }
        if(filePath != null){
            return ImageUtil.uploadImage(filePath).continueWithTask(uploadTask->{
              if(uploadTask.isSuccessful()){
                  user.setProfilePictureURI(uploadTask.getResult());
              }
              return userRepo.updateUser(user);
            });
        }
        return userRepo.updateUser(user);
    }
    public Task<Void> updateUser(User user) {
        return updateUser(user, null);
    }

}
