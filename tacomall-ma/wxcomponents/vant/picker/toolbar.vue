<template>
<uni-shadow-root class="vant-picker-toolbar"><template v-if="wxTemplateName === 'toolbar'">
  <view v-if="showToolbar" class="van-picker__toolbar toolbar-class">
    <view class="van-picker__cancel" hover-class="van-picker__cancel--hover" hover-stay-time="70" data-type="cancel" @click="_$self.$parent[('emit')]($event)">
      {{ cancelButtonText }}
    </view>
    <view v-if="title" class="van-picker__title van-ellipsis">{{
      title
    }}</view>
    <view class="van-picker__confirm" hover-class="van-picker__confirm--hover" hover-stay-time="70" data-type="confirm" @click="_$self.$parent[('emit')]($event)">
      {{ confirmButtonText }}
    </view>
  </view>
</template></uni-shadow-root>
</template>

<script>

global['__wxRoute'] = 'vant/picker/toolbar'

Component({})

export default global['__wxComponents']['vant/picker/toolbar']
</script>
<style platform="mp-weixin">

</style>