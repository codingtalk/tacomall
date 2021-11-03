<template>
    <view>
        <uni-ec-canvas
          class="uni-ec-canvas"
          id="line-chart"
          ref="canvas"
          canvas-id="lazy-load-chart"
          :ec="ec"
        ></uni-ec-canvas>
    </view>
</template>

<script>
import uniEcCanvas from '@/components/uni-ec-canvas/uni-ec-canvas'
export default {
  data () {
    return {
      ec: {
        lazyLoad:true,
        option: {
          title: {
            text: ''
          },
          tooltip: {
            trigger: 'axis',
            formatter: '{b}\r\n{c0}人',
            axisPointer: {
              type: 'line',
              axis: 'x',
              label: {
                backgroundColor: '#000000'
              }
            }
          },
          grid: {
            left: '6%',
            right: '6%',
            top: '6%',
            bottom: '6%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['2-12', '2-14', '2-16', '2-18', '2-20', '2-22', '2-24'],
            axisLine: {
              // y轴
              show: false
            },
            axisTick: {
              // y轴刻度线
              show: false
            },
            splitLine: {
              // 网格线
              show: false
            }
          },
          yAxis: {
            type: 'value',
            axisLine: {
              // y轴
              show: false
            },
            axisTick: {
              // y轴刻度线
              show: false
            },
            splitLine: {
              // 网格线
              show: false
            }
          },
          series: [
            {
              name: '浏览量',
              type: 'line',
              smooth: true,
              lineStyle: {
                color: '#EF5959'
              },
              data: [120, 132, 101, 134, 90, 230, 210]
            }
          ]
        }
      }
    }
  },
  onReady () {
    setTimeout(()=>{
      console.log(this)
      console.log(this.$refs)
        this.$refs['canvas'].init()
        console.log('延迟加载了')
    },1000) // 两秒之后延迟加载

    setTimeout(()=>{
      this.ec.option.series[0].data = [1, 2, 3, 4, 5, 6, 7]
      // 如果是data数组内的数据 记得用$set
      console.log('数据更改了')
    },2000)
  },
  components: {
    uniEcCanvas
  }
}
</script>
<style scoped>
.uni-ec-canvas{
    width: 750upx;
    height: 750upx;
    display:block;
}
</style>
