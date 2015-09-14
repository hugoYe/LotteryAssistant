package com.hugo.lotteryassistant;

import android.os.Bundle;
import android.support.percent.PercentFrameLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.hugo.basecorelibrary.convenientbanner.CBViewHolderCreator;
import com.hugo.basecorelibrary.convenientbanner.ConvenientBanner;
import com.hugo.basecorelibrary.convenientbanner.ConvenientBanner.Transformer;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener, View.OnClickListener {

    private long mExitTime;
    private ConvenientBanner mConvenientBanner;//顶部广告栏控件
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private List<String> networkImages;
    private String[]
        images =
        {"http://image.baidu.com/detail/newindex?col=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8&pn=0&pid=20967406874&aid=&user_id=274610020&setid=12767&sort=0&newsPn=&star=&fr=&from=1",
         "http://img2.3lian.com/2014/f2/37/d/40.jpg",
         "http://d.3987.com/sqmy_131219/001.jpg",
         "http://img2.3lian.com/2014/f2/37/d/39.jpg",
         "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
         "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
         "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
        };
    //    private ListView listView;
    private ArrayAdapter transformerArrayAdapter;
    private ArrayList<String> transformerList = new ArrayList<String>();

    private TextView mShishicaiTv;
    private TextView mJingqingqidaiTv;
    private TextView mShaihaojiluTv;
    private PercentFrameLayout mShishicaiDetail;
    private boolean mShowShishicaiDetail;
    private TextView mXinjiang;
    private TextView mChongqing;
    private TextView mYazhou;
    private TextView mTianjin;
    private TextView mHeilongjiang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initViews();
        init();

    }

    private void initViews() {
        mConvenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);

        // 切页特效 begin
//        listView = (ListView) findViewById(R.id.listView);
//        transformerArrayAdapter =
//            new ArrayAdapter(this, R.layout.adapter_transformer, transformerList);
//        listView.setAdapter(transformerArrayAdapter);
//        listView.setOnItemClickListener(this);
        // 切页特效 end

        mShishicaiTv = (TextView) findViewById(R.id.tv_shishicai);
        mShishicaiTv.setOnClickListener(this);
        mJingqingqidaiTv = (TextView) findViewById(R.id.tv_jingqingqidai);
        mShaihaojiluTv = (TextView) findViewById(R.id.tv_shaihaojilu);
        mShishicaiDetail = (PercentFrameLayout) findViewById(R.id.pf_shishicai_detail);
        mXinjiang = (TextView) findViewById(R.id.tv_xinjiang);
        mXinjiang.setOnClickListener(this);
        mChongqing = (TextView) findViewById(R.id.tv_chongqing);
        mChongqing.setOnClickListener(this);
        mYazhou = (TextView) findViewById(R.id.tv_yazhou);
        mYazhou.setOnClickListener(this);
        mTianjin = (TextView) findViewById(R.id.tv_tianjin);
        mTianjin.setOnClickListener(this);
        mHeilongjiang = (TextView) findViewById(R.id.tv_heilongjiang);
        mHeilongjiang.setOnClickListener(this);
    }

    private void init() {

//        loadTransformerList();

        //本地图片例子
        //本地图片集合
        for (int position = 0; position < 7; position++) {
            localImages.add(getResId("ic_test_" + position, R.drawable.class));
        }
        mConvenientBanner.setPages(
            new CBViewHolderCreator<LocalImageHolderView>() {
                @Override
                public LocalImageHolderView createHolder() {
                    return new LocalImageHolderView();
                }
            }, localImages)
            //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
            .setPageIndicator(
                new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置翻页的效果，不需要翻页效果可用不设
            .setPageTransformer(Transformer.DefaultTransformer);

//        mConvenientBanner.setManualPageable(false);设置不能手动影响

        //网络加载例子
        //        initImageLoader();
//        networkImages = Arrays.asList(images);
//        mConvenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
//            @Override
//            public NetworkImageHolderView createHolder() {
//                return new NetworkImageHolderView();
//            }
//        }, networkImages).setPageIndicator(
//            new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//            .setPageTransformer(Transformer.DefaultTransformer);
    }

    //初始化网络图片缓存库
    private void initImageLoader() {
        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
            showImageForEmptyUri(R.drawable.ic_default_adimage)
            .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
            getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
            .threadPriority(Thread.NORM_PRIORITY - 2)
            .denyCacheImageMultipleSizesInMemory()
            .diskCacheFileNameGenerator(new Md5FileNameGenerator())
            .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


    private void loadTransformerList() {
        //各种翻页效果
        transformerList.add(Transformer.DefaultTransformer.getClassName());
        transformerList.add(Transformer.AccordionTransformer.getClassName());
        transformerList.add(Transformer.BackgroundToForegroundTransformer.getClassName());
        transformerList.add(Transformer.CubeInTransformer.getClassName());
        transformerList.add(Transformer.CubeOutTransformer.getClassName());
        transformerList.add(Transformer.DepthPageTransformer.getClassName());
        transformerList.add(Transformer.FlipHorizontalTransformer.getClassName());
        transformerList.add(Transformer.FlipVerticalTransformer.getClassName());
        transformerList.add(Transformer.ForegroundToBackgroundTransformer.getClassName());
        transformerList.add(Transformer.RotateDownTransformer.getClassName());
        transformerList.add(Transformer.RotateUpTransformer.getClassName());
        transformerList.add(Transformer.StackTransformer.getClassName());
        transformerList.add(Transformer.ZoomInTransformer.getClassName());
        transformerList.add(Transformer.ZoomOutTranformer.getClassName());

        transformerArrayAdapter.notifyDataSetChanged();
    }

    /**
     * 通过文件名获取资源id 例子：getResId("icon", R.drawable.class);
     */
    public static int getResId(String variableName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(variableName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            return super.onKeyUp(keyCode, event);
        }

        return true;
    }

    // 开始自动翻页
    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        mConvenientBanner.startTurning(5000);
    }

    // 停止自动翻页
    @Override
    protected void onPause() {
        super.onPause();
        //停止翻页
        mConvenientBanner.stopTurning();
    }

    //点击切换效果
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String name = transformerList.get(position);
        Transformer transformer = Transformer.valueOf(name);
        mConvenientBanner.setPageTransformer(transformer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shishicai:
                if (mShowShishicaiDetail) {
                    mJingqingqidaiTv.setVisibility(View.VISIBLE);
                    mShaihaojiluTv.setVisibility(View.VISIBLE);
                    mShishicaiDetail.setVisibility(View.GONE);
                    mShowShishicaiDetail = false;
                } else {
                    mJingqingqidaiTv.setVisibility(View.GONE);
                    mShaihaojiluTv.setVisibility(View.GONE);
                    mShishicaiDetail.setVisibility(View.VISIBLE);
                    mShowShishicaiDetail = true;
                }
                break;

            case R.id.tv_xinjiang:
                Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_chongqing:
                Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_yazhou:
                Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_tianjin:
                Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;

            case R.id.tv_heilongjiang:
                Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
