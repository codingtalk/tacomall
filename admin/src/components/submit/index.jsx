
import { defineComponent, ref } from "vue";
import { Button } from "ant-design-vue";
import './__style__/index.less'

export const success = () => <div className="submit_result">
  <i className="iconfont">&#xe604;</i>
  <h3>创建成功</h3>
  <p>提交结果页用于反馈一系列操作任务的处理结果， 如果仅是简单操作，使用 Message 全局提示反馈即可。 本文字区域可以展示简单的补充说明，如果有类似展示 “单据”的需求，下面这个灰色区域可以呈现比较复杂的内容。</p>
  <div className="result_link">
    <a-button type="primary">返回列表</a-button>
    <a-button style="marginLeft:10px;">查看详情</a-button>
    <a-button style="marginLeft:10px;">打印</a-button>
  </div>
</div>

export default defineComponent({
  props: {
    showToolbar: {
      type: Boolean,
      default() {
        return false;
      },
    }
  },
  emits: ['submit'],
  setup(props, { slots, emit }) {
    const step = ref(0)
    const { showToolbar } = props
    function next() {
      step.value++;
    }
    return {
      showToolbar,
      step,
      next,
      slots,
      emit
    }
  },
  render() {
    return <div className="submit">
      {this.step === 0 && <div className="submit_form">
        <div className="form_wrapper">
          {this.slots.default()}
        </div>
      </div>}
      {this.step > 0 && success()}
      {this.showToolbar && <div className="submit_toolbar">
        <div className="toolbar_left"></div>
        <div className="toolbar_right">
          <Button>重置</Button>
          <Button type="primary" style="marginLeft:10px;" onClick={() => { this.emit('submit') }}>提交</Button>
        </div>
      </div>}
      <div className="submit_hint">
        <div className="hint_title">
          <h3>说明：</h3>
        </div>
        <div className="hint_list">
          <div className="list_item">
            <div className="item_label">
              <span>产品SKU</span>
            </div>
            <div className="item_value">
              <p>如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。如果需要，这里可以放一些关于产品的常见问题说明。</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  }
});

