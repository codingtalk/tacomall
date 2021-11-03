<!--
 * @Author: 码上talk|RC
 * @Date: 2021-04-15 16:29:12
 * @LastEditTime: 2021-05-05 10:12:56
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/components/text-editor/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="text-editor">
    <quill-editor
      ref="textEditor"
      :options="editorOption"
      :content="content"
      @change="onEditorChange($event)"
    ></quill-editor>
  </div>
</template>

<script>
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';

import { quillEditor, Quill } from 'vue-quill-editor';

import { OssLib } from '@/utils/oss';
import { model } from '@/model';

const { Oss } = model.collection;

export default {
  components: {
    quillEditor
  },
  props: {
    content: ''
  },
  data () {
    return {
      ossLib: new OssLib(),
      html: '',
      editorOption: {
        placeholder: '编辑文章内容',
        modules: {
          toolbar: [
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题字体
            [{ font: [] }], // 字体
            ['bold', 'italic', 'underline', 'strike'], // 切换按钮
            [{ align: [] }], // 对齐方式
            [{ list: 'ordered' }, { list: 'bullet' }], // 有序/无序列表
            [{ color: [] }, { background: [] }], // 主题默认下拉，使用主题提供的值
            ['clean'], // 清除格式
            ['image', 'link'] // 图片 / 链接 / 视频
          ]
        }
      },
      quill: {
        fn: {
          imgUpload: () => {
            this.ossLib.uploadLocalFile().then(res => {
              const { callbackData } = res;
              const { status, data } = callbackData;
              if (status) {
                const selection = this.$refs.textEditor.quill.getSelection();
                this.$refs.textEditor.quill.insertEmbed(selection !== null ? selection.index : 0, 'image', new Oss(data).fullUrl(), Quill.sources.USER);
                this.$refs.textEditor.quill.setSelection(selection.index + 1);
              }
            });
          }
        }
      }
    };
  },
  methods: {
    initEvent () {
      this.$refs.textEditor.quill.getModule('toolbar').addHandler('image', this.quill.fn.imgUpload);
    },
    onEditorChange ({ html }) {
      this.html = html;
      this.$emit('change', this.html);
    }
  },
  mounted () {
    this.initEvent();
  }
};
</script>
