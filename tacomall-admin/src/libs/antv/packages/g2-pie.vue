<template>
  <div class="g2-pie" :id="cId"></div>
</template>

<script>
import { mixins } from '../mixins/index';
import { getEngine } from '@antv/g2';

export default {
  name: 'G2Pie',
  mixins: [mixins],
  props: {
    config: {
      type: Object,
      default () {
        return {
          chart: {
            autoFit: true
          },
          axis: null
        };
      }
    }
  },
  data () {
    return {};
  },
  methods: {
    render (chart) {
      const G = getEngine('canvas');
      chart.tooltip({
        itemTpl: '<li class="g2-tooltip-list-item">{value}人</li>'
      });
      chart.coordinate('theta', {
        radius: this.config.radius,
        innerRadius: this.config.innerRadius
      });
      chart
        .interval()
        .adjust('stack')
        .position('y')
        .color('x', ['#0780cf', '#765005', '#fa6d1d', '#0e2c82', '#b6b51f', '#da1f18', '#701866', '#f47a75', '#009db2', '#024b51', '#0780cf', '#765005'])
        .label('y', {
          layout: [{ type: 'pie-spider' }, { type: 'hide-overlap' }],
          offset: 8,
          labelHeight: 38,
          content: (obj, item) => {
            const group = new G.Group({});
            // 了解 shape 的绘制原理：y0 左下起点 y1 右上起点
            const [y0, y1] = item.y || [0, 0];
            const inRight = y0 < y1;
            const textAlign = inRight ? 'left' : 'right';

            const topFontSize = 12;
            const bottomFontSize = 14;
            group.addShape({
              type: 'text',
              attrs: {
                x: 0,
                y: 0,
                text: obj.x,
                fill: 'rgba(0, 0, 0, 0.65)',
                fontSize: topFontSize,
                textAlign
              }
            });

            group.addShape({
              type: 'text',
              attrs: {
                x: 0,
                y: 4,
                text: obj.y,
                textAlign,
                textBaseline: 'top',
                fill: 'rgba(0, 0, 0, 0.65)',
                fontWeight: 700,
                fontSize: bottomFontSize
              }
            });
            if (!inRight) {
              group.translate(group.getBBox().width, 0);
            }
            group.translate(0, topFontSize);
            return group;
          },
          labelLine: {
            style: {
              lineWidth: 0.5
            }
          }
        });
      // chart.interaction('element-single-selected');
      // chart.removeInteraction('legend-filter');
      chart.interaction('element-active');
      // chart.on('afterrender', () => {
      //   // label 绘制图层
      //   const foregroundGroup = chart.foregroundGroup;
      //   let labelGroup = foregroundGroup.findById('customLabels');
      //   if (labelGroup) {
      //     labelGroup.clear();
      //   } else {
      //     labelGroup = chart.foregroundGroup.addGroup({
      //       capture: false,
      //       id: 'customLabels'
      //     });
      //   }
      //   const offset = 30; // 拐点折线的长度
      //   const textOffset = 8;
      //   const elements = interval.elements;
      //   const coordinate = chart.getCoordinate();
      //   const center = coordinate.getCenter();
      //   const radius = coordinate.getRadius();

      //   const count = elements.length;
      //   let preWidth = 0;
      //   let firstPoint1; // 第一个 label 的第一个点
      //   let firstPoint2; // 第一个 label 的拐点

      //   for (let i = 0; i < count; i++) {
      //     const label = labelGroup.addGroup();
      //     const element = elements[i];
      //     const originData = element.getData();
      //     const mappingData = element.getModel();
      //     if (i === count - 1) {
      //       // 最后一个图形 label 横着长
      //       label.addShape('path', {
      //         attrs: {
      //           path: [
      //             ['M', center.x, center.y],
      //             ['L', center.x + radius + offset, center.y]
      //           ],
      //           stroke: mappingData.color,
      //           lineWidth: 1
      //         }
      //       });
      //       label.addShape('text', {
      //         attrs: {
      //           x: center.x + radius + offset + textOffset,
      //           y: center.y,
      //           text: originData.type + originData.sold,
      //           textBaseline: 'middle',
      //           fill: '#000'
      //         }
      //       });
      //     } else {
      //       const nextElement = elements[i + 1];
      //       const nextBBox = nextElement.getBBox();
      //       const bbox = element.getBBox();
      //       // 第一个点
      //       const width = bbox.maxX - nextBBox.maxX;
      //       const pointRadius = radius - preWidth - (width / 2);
      //       const point1 = Util.polarToCartesian(center.x, center.y, pointRadius, -3 * Math.PI / 8 + (Math.PI / 8) * i);
      //       let point2;
      //       if (i === 0) {
      //         point2 = {
      //           x: bbox.maxX,
      //           y: bbox.minY
      //         };
      //         firstPoint2 = point2;
      //         firstPoint1 = point1;
      //       } else {
      //         point2 = {
      //           x: Math.min(firstPoint2.x + point1.x - firstPoint1.x, elements[0].getBBox().maxX),
      //           y: firstPoint2.y + point1.y - firstPoint1.y
      //         };
      //       }

      //       const point3 = {
      //         x: point2.x + offset,
      //         y: point2.y
      //       };
      //       label.addShape('path', {
      //         attrs: {
      //           path: [
      //             ['M', point1.x, point1.y],
      //             ['L', point2.x, point2.y],
      //             ['L', point3.x, point3.y]
      //           ],
      //           stroke: mappingData.color,
      //           lineWidth: 1
      //         }
      //       });
      //       label.addShape('text', {
      //         attrs: {
      //           x: point3.x + textOffset, // 加个偏移量
      //           y: point3.y,
      //           text: originData.type + ': ' + originData.sold,
      //           textBaseline: 'middle',
      //           fill: '#000'
      //         }
      //       });
      //       preWidth += width;
      //     }
      //   }
      // });
      chart.render();

      // // 监听 element 上状态的变化来动态更新 Annotation 信息
      // chart.on('element:statechange', (ev) => {
      //   const { state, stateStatus, element } = ev.gEvent.originalEvent;

      //   // 本示例只需要监听 active 的状态变化
      //   if (state === 'active') {
      //     const data = element.getData();
      //     if (stateStatus) {
      //       // 更新 Annotation
      //       updateAnnotation(data);
      //     } else {
      //       // 隐藏 Annotation
      //       clearAnnotation();
      //     }
      //   }
      // });
      // // 绘制 annotation
      // let lastItem;
      // function updateAnnotation (data) {
      //   if (data.item !== lastItem) {
      //     chart.annotation().clear(true);
      //     chart
      //       .annotation()
      //       .text({
      //         position: ['50%', '50%'],
      //         content: data.item,
      //         style: {
      //           fontSize: 20,
      //           fill: '#8c8c8c',
      //           textAlign: 'center'
      //         },
      //         offsetY: -20
      //       })
      //       .text({
      //         position: ['50%', '50%'],
      //         content: data.count,
      //         style: {
      //           fontSize: 28,
      //           fill: '#8c8c8c',
      //           textAlign: 'center'
      //         },
      //         offsetX: -10,
      //         offsetY: 20
      //       })
      //       .text({
      //         position: ['50%', '50%'],
      //         content: '台',
      //         style: {
      //           fontSize: 20,
      //           fill: '#8c8c8c',
      //           textAlign: 'center'
      //         },
      //         offsetY: 20,
      //         offsetX: 20
      //       });
      //     chart.render(true);
      //     lastItem = data.item;
      //   }
      // }

      // // 清空 annotation
      // function clearAnnotation () {
      //   chart.annotation().clear(true);
      //   chart.render(true);
      //   lastItem = null;
      // }
    }
  }
};
</script>
<style lang="less" >
.g2-pie{
  height: 100%;
  .g2-tooltip {
    background: #6294F9 !important;
    color: #fff !important;
    box-shadow: #6294F9 0px 0px 10px !important;
    &-list-item {
      text-align: center;
    }
  }
}
</style>
