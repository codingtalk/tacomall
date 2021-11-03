<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:23:29
 * @LastEditTime: 2021-05-20 22:54:05
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin_v2/src/pages/index.vue
 * @Just do what I think it is right
-->
<template>
  <div class="index">
    <div class="i-statistics">
      <div class="s-item">
        <div class="i-header">
          <div class="h-left">
            <span>销售总额</span>
          </div>
        </div>
        <div class="i-main">
          <div class="m-count">
            <span>￥125,754</span>
          </div>
          <div class="m-extra m-extra-sale">
            <div class="mes-item">
              <span>周同比</span>
              <span>12%</span>
              <i class="iconfont icon-shangsheng" style="color:#ff4d4f;"></i>
            </div>
            <div class="mes-item">
              <span>日同比</span>
              <span>17%</span>
              <i class="iconfont icon-xiajiang" style="color:rgb(19, 194, 194);"></i>
            </div>
          </div>
        </div>
        <div class="i-footer">
          <div class="f-left">
            <div class="l-item">
              <span>日销售额</span>
              <span>￥12,439</span>
            </div>
          </div>
          <div class="f-right"></div>
        </div>
      </div>
      <div class="s-item">
        <div class="i-header">
          <div class="h-left">
            <span>访问量</span>
          </div>
        </div>
        <div class="i-main">
          <div class="m-count">
            <span>25,754</span>
          </div>
          <div class="m-extra">
            <antv-line :data="chartStatisticsVisit" :config="{chart: {padding: 0}}"></antv-line>
          </div>
        </div>
        <div class="i-footer">
          <div class="f-left">
            <div class="l-item">
              <span>日访问量</span>
              <span>1,242</span>
            </div>
          </div>
          <div class="f-right"></div>
        </div>
      </div>
      <div class="s-item">
        <div class="i-header">
          <div class="h-left">
            <span>支付笔数</span>
          </div>
        </div>
        <div class="i-main">
          <div class="m-count">
            <span>5,435</span>
          </div>
          <div class="m-extra">
            <antv-interval :data="chartStatisticsVisit" :config="{chart: {padding: 0}}"></antv-interval>
          </div>
        </div>
        <div class="i-footer">
          <div class="f-left">
            <div class="l-item">
              <span>转化率</span>
              <span>60%</span>
            </div>
          </div>
          <div class="f-right"></div>
        </div>
      </div>
      <div class="s-item">
        <div class="i-header">
          <div class="h-left">
            <span>总商品数</span>
          </div>
        </div>
        <div class="i-main">
          <div class="m-count">
            <span>231</span>
          </div>
          <div class="m-extra">
            <antv-line :data="chartStatisticsVisit" :config="{chart: {padding: 0}}"></antv-line>
          </div>
        </div>
        <div class="i-footer">
          <div class="f-left">
            <div class="l-item">
              <span>周同比更新</span>
              <span>12%</span>
              <i class="iconfont icon-shangsheng" style="color:#ff4d4f;"></i>
            </div>
            <div class="l-item">
              <span>日同比更新</span>
              <span>9%</span>
              <i class="iconfont icon-xiajiang" style="color:rgb(19, 194, 194);"></i>
            </div>
          </div>
          <div class="f-right"></div>
        </div>
      </div>
    </div>
    <div class="i-portal">
      <div class="p-item">
        <i class="iconfont icon-yonghu" style="color:rgb(105, 192, 255);"></i>
        <span>用户</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-fenxi" style="color:rgb(255, 156, 110);"></i>
        <span>分析</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-shangpin1" style="color:rgb(92, 219, 211);"></i>
        <span>商品</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-dingdan" style="color:#2d8cf0;"></i>
        <span>订单</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-piaoju" style="rgb(255, 214, 102);"></i>
        <span>票据</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-xiaoxi1" style="color:rgb(255, 156, 110);"></i>
        <span>消息</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-shengqian" style="color:rgb(255, 133, 192);"></i>
        <span>标签</span>
      </div>
      <div class="p-item">
        <i class="iconfont icon-peizhiip" style="color:rgb(255, 156, 110);"></i>
        <span>配置</span>
      </div>
    </div>
    <div class="i-sale-visit">
      <div class="sv-header">
        <div class="h-left">
          <div class="l-tab">
            <div class="t-item t-item-active">
              <span>销售额</span>
            </div>
            <div class="t-item">
              <span>访问量</span>
            </div>
          </div>
        </div>
        <div class="h-right">
          <div class="r-time-type">
            <div class="t-item">
              <span>今日</span>
            </div>
            <div class="t-item">
              <span>今周</span>
            </div>
            <div class="t-item">
              <span>今月</span>
            </div>
            <div class="t-item">
              <span>今年</span>
            </div>
          </div>
          <div class="r-time-selector">
            <el-date-picker
              format="yyyy年MM月dd日"
              type="date"
              placeholder="选择时间"
              style="width: 200px"
            ></el-date-picker>
          </div>
        </div>
      </div>
      <div class="sv-content">
        <div class="c-left">
          <div class="l-tag">
            <span>销售趋势</span>
          </div>
          <div class="l-chart">
            <antv-line
              :data="chartSaleVisit"
              :config="{axis: {
                label: function label(text, index, total) {
                  const textCfg = {};
                  if (index === 0) {
                    textCfg.textAlign = 'left';
                  } else if (index === total - 1) {
                    textCfg.textAlign = 'right';
                  }
                  return textCfg;
                }
              }}"
            ></antv-line>
          </div>
        </div>
        <div class="c-right">
          <div class="r-tag">
            <span>店铺排名</span>
          </div>
          <div class="r-list">
            <div class="l-item">
              <div class="li-left">
                <div class="l-tag l-tag-dot">
                  <span>1</span>
                </div>
                <div class="l-name">
                  <span>塔可商城</span>
                </div>
              </div>
              <div class="li-right">
                <div class="r-data">232,098</div>
              </div>
            </div>
            <div class="l-item">
              <div class="li-left">
                <div class="l-tag l-tag-dot">
                  <span>2</span>
                </div>
                <div class="l-name">
                  <span>lws</span>
                </div>
              </div>
              <div class="li-right">
                <div class="r-data">23,234</div>
              </div>
            </div>
            <div class="l-item">
              <div class="li-left">
                <div class="l-tag l-tag-dot">
                  <span>3</span>
                </div>
                <div class="l-name">
                  <span>测试店铺</span>
                </div>
              </div>
              <div class="li-right">
                <div class="r-data">22,214</div>
              </div>
            </div>
            <div class="l-item">
              <div class="li-left">
                <div class="l-tag">
                  <span>4</span>
                </div>
                <div class="l-name">
                  <span>测试店铺1</span>
                </div>
              </div>
              <div class="li-right">
                <div class="r-data">2,234</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { antvLine, antvInterval } from '@/libs/antv';
export default {
  components: {
    antvLine,
    antvInterval
  },
  data () {
    return {

    };
  },
  computed: {
    chartStatisticsVisit () {
      return [{
        x: '2016-08-08 00:00:00',
        y: 10
      }, {
        x: '2016-08-08 00:10:00',
        y: 22
      }, {
        x: '2016-08-08 00:30:00',
        y: 16
      }, {
        x: '2016-08-09 00:35:00',
        y: 26
      }, {
        x: '2016-08-09 01:00:00',
        y: 12
      }, {
        x: '2016-08-09 01:20:00',
        y: 26
      }, {
        x: '2016-08-10 01:40:00',
        y: 18
      }, {
        x: '2016-08-10 02:00:00',
        y: 26
      }, {
        x: '2016-08-10 02:20:00',
        y: 12
      }];
    },
    chartSaleVisit () {
      return [{
        x: '2016-08-01',
        y: 23
      }, {
        x: '2016-08-02',
        y: 22
      }, {
        x: '2016-08-03',
        y: 23
      }, {
        x: '2016-08-04',
        y: 26
      }, {
        x: '2016-08-05',
        y: 34
      }, {
        x: '2016-08-06',
        y: 26
      }, {
        x: '2016-08-07',
        y: 18
      }, {
        x: '2016-08-08',
        y: 54
      }, {
        x: '2016-08-09',
        y: 12
      }];
    }
  }
};
</script>

<style lang="less">
.index {
  .i-statistics {
    display: flex;

    .s-item {
      flex: 1;
      padding: 14px;
      background: white;

      .i-header {
        display: flex;
        justify-content: space-between;
        padding-bottom: 14px;
        border-bottom: 1px solid #e8eaec;
        .h-left {
          span {
            color: rgba(0, 0, 0, 0.45);
          }
        }
      }

      .i-main {
        height: 100px;
        .m-count {
          span {
            font-size: 30px;
            color: rgba(0, 0, 0, 0.85);
          }
        }
        .m-extra {
          height: 46px;
          margin-top: 10px;
          &-sale {
            display: flex;
            align-items: center;
            .mes-item {
              &:not(:first-child) {
                margin-left: 10px;
              }
            }
          }
        }
      }

      .i-footer {
        display: flex;
        justify-content: space-between;
        padding-top: 14px;
        border-top: 1px solid #e8eaec;
        .f-left {
          display: flex;
          align-items: center;
          .l-item {
            display: flex;
            align-items: center;
          }
        }
      }

      &:not(:first-child) {
        margin-left: 24px;
      }
    }
  }
  .i-portal {
    display: flex;
    padding: 24px 0;
    .p-item {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      flex: 1;
      height: 90px;
      background: white;
      .iconfont {
        font-size: 30px;
      }
      &:hover {
        box-shadow: 0 6px 16px -8px rgba(0, 0, 0, 0.08),
          0 9px 28px 0 rgba(0, 0, 0, 0.05), 0 12px 48px 16px rgba(0, 0, 0, 0.03);
      }
      &:not(:first-child) {
        margin-left: 24px;
      }
    }
  }
  .i-sale-visit {
    background: white;
    .sv-header {
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 57px;
      padding: 0 14px;
      border-bottom: 1px solid #e8eaec;
      .h-left {
        .l-tab {
          display: flex;
          .t-item {
            position: relative;
            display: flex;
            align-items: center;
            height: 57px;
            padding: 0 10px;
            &-active {
              span {
                color: #1890ff;
              }
              &:after {
                position: absolute;
                content: "";
                bottom: -2px;
                left: 0;
                width: 100%;
                height: 2px;
                background: #1890ff;
              }
            }
            &:not(:first-child) {
              margin-left: 10px;
            }
          }
        }
      }
      .h-right {
        display: flex;
        align-items: center;
        .r-time-type {
          display: flex;
          align-items: center;
          .t-item {
            &:not(:first-child) {
              margin-left: 10px;
            }
          }
        }
        .r-time-selector {
          display: flex;
          align-items: center;
          padding-left: 14px;
        }
      }
    }
    .sv-content {
      display: flex;
      padding-bottom: 30px;
      .c-left {
        flex: 1;
        .l-tag {
          display: flex;
          align-items: center;
          height: 70px;
          padding-left: 14px;
        }
        .l-chart {
          height: 300px;
          padding: 0 14px;
        }
      }
      .c-right {
        width: 329px;
        .r-tag {
          display: flex;
          align-items: center;
          height: 70px;
          padding-left: 14px;
        }
        .r-list {
          padding: 0 14px;
          .l-item {
            display: flex;
            align-items: center;
            justify-content: space-between;
            .li-left {
              display: flex;
              align-items: center;
              .l-tag {
                margin-right: 10px;
                &-dot {
                  display: flex;
                  align-items: center;
                  justify-content: center;
                  width: 14px;
                  height: 14px;
                  border-radius: 7px;
                  background: gray;
                  span {
                    color: white;
                  }
                }
              }
            }
            &:not(:first-child) {
              margin-top: 14px;
            }
          }
        }
      }
    }
  }
}
</style>
