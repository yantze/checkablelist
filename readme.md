# Checkable ListView

- 原生 ListView， 可重用 ItemView
- 简单的示例
- 够用就好

## 出现 ListView 混乱的原因
- Android 应用，有应用内部进程和渲染进程
- ListView 默认是会重用 ItemView， 所以渲染进程会重复让checkbox widget 显示出来
- 需要对每次渲染 checkbox 的时候进行预设, 具体查看 getView (app/src/main/java/com/vastiny/checkablelist/MainActivity.java#L84) 方法
