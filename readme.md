# 什么是TagTextView？

1. TagTextView是一个带标签的TextView，继承自TextView

2. TagTextView可以实现在一段文字头部或者尾部显示标签

3. TagTextView也可以实现在一段文字上显示一个或者多个标签

4. TagTextView更可以添加标签到你想要的任意位置

5. TagTextView还能添加图片作为标签显示

6. TagTextView同时还支持自定义布局和样式，非常灵活


![avatar](images/screen.png)

# 如何使用TagTextView

### 下载libs库然后引用

* [点击下载libs库](https://github.com/xiaozhiguang/TagTextView/archive/master.zip)

### 拷贝相关代码到自己的项目

> 拷贝项目中的文件及相关资源

* [TagTextView.java](libs/src/main/java/com/xiaozhiguang/views/TagTextView.java)             // 自定义的TagTextView

* [activity_tag_text_view.xml](libs/src/main/res/layout/layout_textview_tags.xml)            // tag的布局文件

* [shape_textview_tags_bg.xml](libs/src/main/res/drawable/shape_textview_tags_bg.xml)        // tag的样式文件

> 在你的布局中引用自定义view

```
    <com.xiaozhiguang.views.TagTextView
        android:id="@+id/tv_with_tags"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:lineSpacingExtra="2dp"
        android:text="这是一个带tag的TextView"
        android:textColor="#000000"
        android:textSize="16sp" />
```

> 在你的Activity中初始化TagTextView
```
    private TagTextView tv_with_tags;
    
    tv_with_tags = findViewById(R.id.tv_with_tags);
```

> 给TagTextView设置标签和内容和样式等相关属性

```
        //  头部标签
        tv_with_tags.setSingleTagAndContent("标签", "这是一个带有tag的TextView");
        
        //  尾部标签
        tv_with_single_tags_end.setTagsIndex(TagTextView.TAGS_INDEX_AT_END);
        tv_with_single_tags_end.setSingleTagAndContent("尾部Tags", "这是一个尾部带有tag的TextView");
        
        //  多个标签
        List<String> tags = new ArrayList<>();
        tags.add("标签1");
        tags.add("标签2");
        tags.add("标签3");
        tags.add("标签4");
        tv_with_multiple_tags.setMultiTagAndContent(tags, "这是一个带有多个tag的TextView");
        
        //  设置标签的字体大小和颜色
        tv_with_single_tags_anyway.setTagTextSize(12);
        tv_with_single_tags_anyway.setTagTextColor("#FF0000");
        
        //  自定义标签样式
        tv_with_single_tags_anyway.setTagsBackgroundStyle(R.drawable.shape_tags_bg);
        
        //  自定义位置的标签
        tv_with_single_tags_anyway.setTagAnyway(4, 10, "这是一个可以自己设置标签位置的TextView");
        
        //  图片标签
        tv_with_image_tags.setTagImageStart(this, R.mipmap.icon_close, " 这是一个添加图片tag的TextView", 20, 20);
        
```
# Tips

1. 小提示：自带的ImageSpan显示出来的标签可能会出现与文字内容布局中的情况，所以采用了自定义CenterImageSpan的方法来达到标签与文字内容居住的效果。

2. TagTextView还有很多的地方可以拓展，大家有兴趣的可以试一试，另外大家有什么不懂的地方可以提问到[issues](https://github.com/xiaozhiguang/TagTextView/issues), 我会尽量解决您的问题。