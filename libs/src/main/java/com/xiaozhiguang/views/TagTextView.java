package com.xiaozhiguang.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xiaozhiguang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhiguang on 2017/12/14.
 */

public class TagTextView extends android.support.v7.widget.AppCompatTextView {

    public static int TAGS_INDEX_AT_START = 0;
    public static int TAGS_INDEX_AT_END = 1;

    private int tagsLayoutID = R.layout.layout_textview_tags;
    private int tagsBackgroundStyle = R.drawable.shape_textview_tags_bg;

    private StringBuffer content_buffer;
    private Context mContext;
    private TextView tv_tag;

    private int tagsIndex = 0;  //  默认标签在开始的位置

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
     * 设置标签的布局
     * @param tagsLayoutID
     */
    public void setTagsLayoutID(int tagsLayoutID) {
        this.tagsLayoutID = tagsLayoutID;
    }

    /**
     *  设置标签的背景样式
     * @param tagsBackgroundStyle
     */
    public void setTagsBackgroundStyle(int tagsBackgroundStyle) {
        this.tagsBackgroundStyle = tagsBackgroundStyle;
    }

    /**
     * 设置标签是在头部还是尾部
     * @param tagsIndex
     */
    public void setTagsIndex(int tagsIndex) {
        this.tagsIndex = tagsIndex;
    }

    /**
     * 设置标签和文字内容(单个)
     *
     * @param tag     标签内容
     * @param content 标签文字
     */
    public void setSingleTagAndContent(@NonNull String tag, String content) {
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
    public void setMultiTagAndContent(@NonNull List<String> tags, String content) {
        if (tagsIndex == TAGS_INDEX_AT_START) {
            setTagStart(tags, content);
        } else {
            setTagEnd(tags, content);
        }
    }

    /**
     * 标签显示在头部位置
     *
     * @param tags    标签内容
     * @param content 标签文字
     */
    public void setTagStart(List<String> tags, String content) {
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
            View view = LayoutInflater.from(mContext).inflate(tagsLayoutID, null);
            tv_tag = view.findViewById(R.id.tv_tags);
            tv_tag.setText(item);
            //  设置背景样式
            tv_tag.setBackgroundResource(tagsBackgroundStyle);

            Bitmap bitmap = convertViewToBitmap(view);
            Drawable drawable = new BitmapDrawable(bitmap);
            drawable.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());

            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, startIndex - 1, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            startIndex += item.length();
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 标签显示在头部位置
     *
     * @param tags    标签内容
     * @param content 标签文字
     */
    public void setTagEnd(List<String> tags, String content) {
        content_buffer = new StringBuffer(content);
        for (String item : tags) {
            content_buffer.append(item);
        }
        SpannableString spannableString = new SpannableString(content_buffer);
        for (int i = 0; i < tags.size(); i++) {
            String item = tags.get(i);
            //  设置标签的布局
            View view = LayoutInflater.from(mContext).inflate(tagsLayoutID, null);
            tv_tag = view.findViewById(R.id.tv_tags);
            tv_tag.setText(item);
            //  设置背景样式
            tv_tag.setBackgroundResource(tagsBackgroundStyle);

            Bitmap bitmap = convertViewToBitmap(view);
            Drawable drawable = new BitmapDrawable(bitmap);
            drawable.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());

            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            spannableString.setSpan(span, content_buffer.length() - item.length(), content_buffer.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        setText(spannableString);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    /**
     * 指定位置设置标签
     *
     * @param start   开始位置从0开始
     * @param end     结束位置长度-1
     * @param content 文字内容
     */
    public void setTagAnyway(int start, int end, String content) {
        SpannableString spannableString = new SpannableString(content);
        //  设置标签的布局
        View view = LayoutInflater.from(mContext).inflate(tagsLayoutID, null);
        String item = content.substring(start, end);
        tv_tag = view.findViewById(R.id.tv_tags);
        tv_tag.setText(item);
        //  设置背景样式
        tv_tag.setBackgroundResource(tagsBackgroundStyle);

        Bitmap bitmap = convertViewToBitmap(view);
        Drawable drawable = new BitmapDrawable(bitmap);
        drawable.setBounds(0, 0, tv_tag.getWidth(), tv_tag.getHeight());

        ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        spannableString.setSpan(span, start, end, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
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