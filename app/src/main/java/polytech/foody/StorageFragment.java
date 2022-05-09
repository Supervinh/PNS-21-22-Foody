package polytech.foody;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalTime;

public class StorageFragment extends Fragment {
    private IStorageActivity activity;

    private Button buttonSave;
    private String pictureName;
    private String directoryName;


    public StorageFragment() {
    }

    @SuppressLint("ValidFragment")
    public StorageFragment(IStorageActivity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_storage, container, false);
        pictureName = "repas" + LocalTime.now() + ".jpg";
        ContextWrapper contextWrapper = new ContextWrapper(getContext());
        directoryName = contextWrapper.getDir("imageDir", ContextWrapper.MODE_PRIVATE).getPath();

        buttonSave = rootView.findViewById(R.id.button_save);
        setDisableSaveButton();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            IStorageActivity.REQUEST_MEDIA_WRITE);
                } else {
                    Bitmap picture = activity.getPictureToSave();
                    if (picture != null) {
                        saveToInternalStorage(picture);
                        setDisableSaveButton();
                    }
                }

            }
        });

        return rootView;
    }

    public void saveToInternalStorage(Bitmap picture) {
        /*ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, pictureName);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/*");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, directoryName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        */
        File file = new File(directoryName, pictureName);
        File folder = new File("/storage/emulated/0/Foody");
        folder.mkdir();
        file = Environment.getExternalStoragePublicDirectory("Foody");
        //Log.d("Test", String.valueOf(file));
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file + "/" + pictureName);
            picture.compress(Bitmap.CompressFormat.PNG, 90, fos);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void setDisableSaveButton() {
        buttonSave.setEnabled(false);
    }

    public void setEnableSaveButton() {
        buttonSave.setEnabled(true);
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }
}
