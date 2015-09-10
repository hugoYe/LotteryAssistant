package com.hugo.lotteryassistant;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hugo.basecorelibrary.convenientbanner.CBPageAdapter;

/**
 *
 */
public class LocalImageHolderView implements CBPageAdapter.Holder<Integer> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, final int position, Integer data) {
        imageView.setImageResource(data);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "点击了第" + position + "个", Toast.LENGTH_SHORT)
                    .show();
            }
        });
    }
}
