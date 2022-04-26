package polytech.foody;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class PictureFragment extends Fragment {
    private ImageView imageView;

    public PictureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_picture, container, false);

        imageView = rootView.findViewById(R.id.photoView);

        rootView.findViewById(R.id.buttonImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA},
                            IPictureActivity.REQUEST_CAMERA);
                } else {
                    takePicture();
                }

            }
        });

        return rootView;
    }

    public void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent, IPictureActivity.REQUEST_CAMERA);
    }

    public void setImage(Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }
}
