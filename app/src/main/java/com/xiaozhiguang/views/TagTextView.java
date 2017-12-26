package com.xiaozhiguang.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xiaozhiguang.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhiguang on 2017/12/14.
 */

public class TagTextView extends android.support.v7.widget.AppCompatTextView {

    private StringBuffer content_buffer;
    private Context mContext;
    private TextView tv_tag;

    public TagTextView(Context context) {
        super(context);
        mContext = context;
    }

    public TagTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public TagTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 设置标签和文字内容(单个)
     *
     * @param tag     标签内容
     * @param content 标签文字
     */
    public void setSingleTagAndContent(String tag, String content) {
        List<String> tagList = new ArrayList<>();
        tagList.add(tag);
        setMultiTagAndContent(tagList, content);
    }

    /**
     * 设置标签和文字内容(多个)
     *
     * @param tags    标签内容
     * @param content 标签文字
     */
    public void setMultiTagAndContent(List<String> tags, String content) {
        int endIndex = 0;
        int startIndex = 1;
        content_buffer = new StringBuffer();
        for (String item : tags) {
            content_buffer.append(item);
        }
        content_buffer.append(content);
        SpannableString spannableString = new SpannableString(content_buffer);
        for (int i = 0; i < tags.size(); i++) {
            String item = tags.get(i);
            endIndex += item.length();
            //  设置标签的布局
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_textview_tags, null);
            tv_tag = view.findViewById(R.id.tv_tags);
            tv_tag.setText(item);
            //  设置背景样式
            tv_tag.setBackgroundResource(R.drawable.shape_textview_tags_bg);

            Bitmap bitmap = convertViewToBitmap(view);
            Drawable drawable = new BitmapDrawable(bitmap);
            drawable.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());   //  缺少这句的话，不会报错，但是图片不回显示
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);          //  图片将对齐底部边线
            spannableString.setSpan(span, startIndex - 1, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            startIndex += item.length();
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private static Bitmap convertViewToBitmap(View view) {
        view.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}