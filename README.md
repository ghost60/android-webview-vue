# android-webview-vue
使用vue开发webapp，通过android的webview嵌入到android APP中

# 需要配置的项
在android项目的layout中引入webvie
···
 <WebView
        android:id="@+id/activity_main_webview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layerType="software"/>
 ···
