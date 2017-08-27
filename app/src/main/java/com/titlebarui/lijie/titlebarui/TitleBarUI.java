package com.titlebarui.lijie.titlebarui;

/**
 * Created by lijie on 2017/08/27.
 */

/**
 * TitleBarUI
 */
        import android.content.Context;
        import android.util.AttributeSet;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.FrameLayout;
        import android.widget.ImageView;
        import android.widget.TextView;

public class TitleBarUI extends FrameLayout implements OnClickListener {

    private ImageView zuobianImageView;
    private ImageView rightImageView;
    private TextView zuobianTextView;
    private TextView zhongjianTextView;
    private TextView youbianTextView;
    private ImageView rightImageView2;
    private TitleBarListener listener;
    private ImageView zuosanjiao;

    public TitleBarUI(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.ui_titlebar, this);
        init();
    }

    public void setListener(TitleBarListener listener) {
        this.listener = listener;
    }

    public void init() {
        zuobianImageView = (ImageView) findViewById(R.id.zuobianImageView);
        zuobianTextView = (TextView) findViewById(R.id.zuobianTextView);
        zhongjianTextView = (TextView) findViewById(R.id.zhongjianTextView);
        zuosanjiao = (ImageView) findViewById(R.id.zuosanjiao);
        youbianTextView = (TextView) findViewById(R.id.youbianTextView);
        rightImageView2 = (ImageView) findViewById(R.id.rightImageView2);//右边往左第二个图片
        rightImageView = (ImageView) findViewById(R.id.rightImageView);

        zuobianImageView.setOnClickListener(this);
        zuobianTextView.setOnClickListener(this);
        youbianTextView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
        rightImageView2.setOnClickListener(this);
        zuosanjiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.youbianTextView:
                listener.youbian();
                break;
            case R.id.zuobianImageView:
                listener.zuobian();
                break;
            case R.id.zuosanjiao://
                listener.fenzu(v);
                break;
            case R.id.rightImageView2:// 点击添加该图片进入添加提醒事务板页面
                listener.youbian();
                break;
            case R.id.rightImageView:// 点击添加该图片进入查询页面
                listener.youbianImage1();
                break;
            case R.id.zuobianTextView:// 点击左边文字
                listener.zuobianTextView(v);
                break;
            default:
                break;
        }
    }

    // 设置左边的Imageview
    public void setLeftImage(int resid) {
        zuobianImageView.setImageResource(resid);
    }

    // 设置左边的iamgeview的隐藏
    public void setLeftImageInvisibility() {
        zuobianImageView.setVisibility(View.GONE);
    }

    // 设置youbian的Imageview
    public void setRightImage(int resid) {
        rightImageView.setImageResource(resid);
    }

    // 设置右边的iamgeview的隐藏
    public void setRightImageInvisibility() {
        rightImageView.setVisibility(View.GONE);
    }

    // 设置左边textview文字
    public void setLeftText(String text) {
        zuobianTextView.setText(text);
    }

    // 设置左边的textview的隐藏
    public void setLeftTextInvisibility() {
        zuobianTextView.setVisibility(View.GONE);
    }

    // 设置中间的textview的文字
    public void setzhongjianText(String title) {
        zhongjianTextView.setText(title);
    }

    // 设置右边textview文字
    public void setRightText(String right) {
        youbianTextView.setVisibility(View.VISIBLE);
        youbianTextView.setText(right);
    }

    // 设置右边textview的背景
    public void setRightTextViewImage(int resid) {
        youbianTextView.setVisibility(View.VISIBLE);
        youbianTextView.setText("");
        youbianTextView.setBackgroundResource(resid);
    }
    //设置三角图片
    public void setSanjiao(int sanjiao){
        zuosanjiao.setImageResource(sanjiao);
    }
    //设置右边第二个图片
    public void setRightSecond(int rightimg2){
        rightImageView2.setImageResource(rightimg2);
    }

}
