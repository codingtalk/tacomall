<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-15 17:27:57
 * @LastEditTime: 2021-04-15 17:42:39
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/text-previewer/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="text-previewer ql-editor" v-html="content"></div>
</template>

<script>
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';

export default {
  data () {
    return {
    };
  },
  props: {
    content: {
      type: String,
      default () {
        return '';
      }
    }
  }
};
</script>

<style lang="less">
.text-previewer {
    padding: 40px 20px;
}
</style>
