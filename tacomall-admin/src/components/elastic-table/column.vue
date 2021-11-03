<!--
 * @Author: 码上talk|RC
 * @Date: 2021-07-01 08:16:33
 * @LastEditTime: 2021-08-24 15:58:17
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/components/elastic-table/column.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<script>
export default {
  props: {
    conf: {
      type: Object,
      default () {
        return {};
      }
    }
  },
  render: function (createElement) {
    const { isPopover = true, fixed, prop, label, width, VNodes = [] } = this.conf;
    let options = {
      props: {
        align: 'center',
        isPopover,
        fixed,
        prop,
        label,
        width
      }
    };
    if (Array.isArray(VNodes) && VNodes.length) {
      options = Object.assign(options, {
        scopedSlots: {
          default: props => {
            return VNodes.map(VNode => VNode({ h: createElement, props }));
          }
        }
      });
    }
    if (typeof VNodes === 'function') {
      options = Object.assign(options, {
        scopedSlots: {
          default: props => {
            const HVNodes = VNodes(props).map(VNode => VNode({ h: createElement, props }))
            if (fixed === 'right' && isPopover) {
              return [HVNodes.slice(0, 1), createElement('el-popover', { props: { placement: 'bottom', trigger: 'hover' }, style: { marginLeft: '10px' } }, [
                HVNodes.slice(1, HVNodes.length),
                createElement('el-button', { slot: 'reference', props: { size: 'mini' }, }, '更多')
              ])];
            }
            return HVNodes
          }
        }
      });
    }
    return createElement(
      'el-table-column', options
    );
  }
};
</script>
