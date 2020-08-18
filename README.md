# V6DeviceTest
ZARA - 下一代前端框架（主标题）
基于 Web Components 并支持 IE8（简单介绍）

目录
生态
必须收藏的资源
快速入门
安装
用法
贡献者们
维护者
感谢
License
生态
→ 生态学习路线图

项目	描述
ZARA-docs	官方文档
ZARA
直接使用 ZARA 开发小程序！！！
特性
小巧的尺寸
支持 TypeScript
必须收藏的资源
你必须收藏 ES6 Spread Operator 技巧
使用 requestIdleCallback
快速入门
安装
$ npm i zara-cli -g               # install cli
$ omi init my-app     # init project, you can also exec 'omi init' in an empty folder
$ cd my-app           # please ignore this command if you executed 'omi init' in an empty folder
$ npm start                      # develop
$ npm run build                  # release
复制代码
目录说明:

├─ config
├─ public
├─ scripts
├─ src
│  ├─ assets
│  ├─ elements    //存放所有 custom elements
│  ├─ store       //存放所有页面的 store
│  ├─ admin.js    //入口文件，会 build 成  admin.html
│  └─ index.js    //入口文件，会 build 成  index.html
复制代码
用法
<template>
  <Slider v-model="value" range />
</template>
<script>
export default {
  data() {
    return {
      value: [20, 50]
    };
  }
};
</script>
复制代码
→ simple demo

贡献者们
维护者
yoki
感谢
README-templete
License
MIT
