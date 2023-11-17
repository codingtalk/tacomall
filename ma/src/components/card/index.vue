<template>
  <view class="card">
    <view class="card_info">
      <view class="info_item" :key="key" v-for="(item, key) in data">
        <view class="item_label">
          <text>{{ item.label }}</text>
        </view>
        <view class="item_value">
          <text>{{ item.value }}</text>
        </view>
      </view>
    </view>
  </view>
</template>


<script setup>
const props = defineProps({
  data: {
    type: Array,
    default () {
      return []
    }
  }
})
</script>

<style lang="less">
@import url("../../assets/style/var.less");
@import url("../../assets/style/mixins.less");
.card{
  padding: 10rpx 20rpx;
  margin: 0 20rpx 20rpx 20rpx;
  border-radius: 20rpx;
  background: #FFF;
  .card_info{
    .info_{
      &item{
        height: 90rpx;
        .flex(@a: center, @j: space-between);
        .item_{
          &label{
          }
          &value{
          }
        }
      }
    }
  }
}
</style>