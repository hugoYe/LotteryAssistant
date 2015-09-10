package com.hugo.lotteryassistant;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hugo.basecorelibrary.convenientbanner.CBPageAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by
 */
public class NetworkImageHolderView implements CBPageAdapter.Holder<String> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, String data) {
        imageView.setImageResource(R.drawable.ic_default_adimage);
        ImageLoader.getInstance().displayImage(data, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }
}
