package com.xiaozhiguang.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xiaozhiguang.views.TagTextView;

import java.util.ArrayList;
import java.util.List;

public class TagTextViewActivity extends AppCompatActivity {

    private TagTextView tv_with_tags;
    private TagTextView tv_with_multiple_tags;
    private TagTextView tv_with_single_tags_end;
    private TagTextView tv_with_single_tags_anyway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_text_view);

        tv_with_tags = findViewById(R.id.tv_with_tags);
        tv_with_multiple_tags = findViewById(R.id.tv_with_multiple_tags);
        tv_with_single_tags_end = findViewById(R.id.tv_with_single_tags_end);
        tv_with_single_tags_anyway = findViewById(R.id.tv_with_single_tags_anyway);

        //  头部标签
        tv_with_tags.setSingleTagAndContent("标签", "这是一个带有tag的TextView");

        //  多个标签
        List<String> tags = new ArrayList<>();
        tags.add("标签1");
        tags.add("标签2");
        tags.add("标签3");
        tags.add("标签4");
        tv_with_multiple_tags.setMultiTagAndContent(tags, "这是一个带有多个tag的TextView");

        //  尾部标签
        tv_with_single_tags_end.setTagsIndex(TagTextView.TAGS_INDEX_AT_END);
        tv_with_single_tags_end.setSingleTagAndContent("尾部Tags", "这是一个尾部带有tag的TextView");

        //  自定义布局
        tv_with_single_tags_anyway.setTagsLayoutID(R.layout.layout_tags);

        //  自定义标签样式
        tv_with_single_tags_anyway.setTagsBackgroundStyle(R.drawable.shape_tags_bg);

        //  自定义位置的标签
        tv_with_single_tags_anyway.setTagAnyway(4, 10, "这是一个可以自己设置标签位置的TextView");
    }
}
