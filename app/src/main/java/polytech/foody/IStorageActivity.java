package polytech.foody;

import android.graphics.Bitmap;

public interface IStorageActivity {
    int REQUEST_MEDIA_WRITE = 1001;
    int REQUEST_MEDIA_READ = 1000;
    void onPictureLoad(Bitmap bitmap);
    Bitmap getPictureToSave();
}
