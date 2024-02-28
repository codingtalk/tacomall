<template>
  <view class="calendar">
    <view class="calendar_week">
      <view class="week_item">
        <text>Sun</text>
      </view>
      <view class="week_item">
        <text>Mon</text>
      </view>
      <view class="week_item">
        <text>Tue</text>
      </view>
      <view class="week_item">
        <text>Wed</text>
      </view>
      <view class="week_item">
        <text>Thu</text>
      </view>
      <view class="week_item">
        <text>Fri</text>
      </view>
      <view class="week_item">
        <text>Sat</text>
      </view>
    </view>
    <view class="calendar_day">
      <slot :chunkDays="chunkDays"></slot>
    </view>
  </view>
</template>

<script setup>
import { ref, onMounted } from "vue";
import _ from 'lodash';
import { getChunkDates } from 'codingtalk-uni-toolkit'
import { timeFormat } from "@/utils/filter";

let chunkDays = ref([]);

onMounted(() => {
  chunkDays.value = _.chunk(getChunkDates(timeFormat(undefined, "YYYY-MM-" + 1)), 7);
})


</script>

<style lang="less">

.calendar {
  .calendar_week {
    .flex();

    .week_item {
      .flex(@j: center);
      flex: 1;

      text {
        font-size: 28rpx;
        color: @text-color;
      }
    }
  }
}
</style>