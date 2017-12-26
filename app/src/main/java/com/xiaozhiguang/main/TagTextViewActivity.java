package com.xiaozhiguang.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaozhiguang.views.TagTextView;

import java.util.ArrayList;
import java.util.List;

public class TagTextViewActivity extends AppCompatActivity {

    private TagTextView tv_with_tags;
    private TagTextView tv_with_multiple_tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_text_view);
        tv_with_tags = findViewById(R.id.tv_with_tags);
        tv_with_multiple_tags = findViewById(R.id.tv_with_multiple_tags);

        tv_with_tags.setSingleTagAndContent("系列", "这是一个带有tag的TextView");
        List<String> tags = new ArrayList<>();
        tags.add("标签1");
        tags.add("标签2");
        tags.add("标签3");
        tags.add("标签4");
        tv_with_multiple_tags.setMultiTagAndContent(tags, "这是一个带有多个tag的TextView");
    }
}
