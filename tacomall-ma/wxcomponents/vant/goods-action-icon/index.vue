<template>
<uni-shadow-root class="vant-goods-action-icon-index"><van-button square :id="id" size="large" :lang="lang" :loading="loading" :disabled="disabled" :open-type="openType" :business-id="businessId" custom-class="van-goods-action-icon" :session-from="sessionFrom" :app-parameter="appParameter" :send-message-img="sendMessageImg" :send-message-path="sendMessagePath" :show-message-card="showMessageCard" :send-message-title="sendMessageTitle" @click="onClick" @error="bindError" @contact="bindContact" @opensetting="bindOpenSetting" @getuserinfo="bindGetUserInfo" @getphonenumber="bindGetPhoneNumber" @launchapp="bindLaunchApp">
  <van-icon v-if="icon" :name="icon" :dot="dot" :info="info" class="van-goods-action-icon__icon" custom-class="icon-class"></van-icon>
  <slot v-else name="icon"></slot>
  <text class="text-class">{{ text }}</text>
</van-button></uni-shadow-root>
</template>

<script>
import VanIcon from '../icon/index.vue'
import VanButton from '../button/index.vue'
global['__wxVueOptions'] = {components:{'van-icon': VanIcon,'van-button': VanButton}}

global['__wxRoute'] = 'vant/goods-action-icon/index'
import { VantComponent } from '../common/component';
import { link } from '../mixins/link';
import { button } from '../mixins/button';
import { openType } from '../mixins/open-type';
VantComponent({
  classes: ['icon-class', 'text-class'],
  mixins: [link, button, openType],
  props: {
    text: String,
    dot: Boolean,
    info: String,
    icon: String,
    disabled: Boolean,
    loading: Boolean,
  },
  methods: {
    onClick(event) {
      this.$emit('click', event.detail);
      this.jumpLink();
    },
  },
});
export default global['__wxComponents']['vant/goods-action-icon/index']
</script>
<style platform="mp-weixin">
@import '../common/index.css';.van-goods-action-icon{display:-webkit-flex!important;display:flex!important;-webkit-flex-direction:column;flex-direction:column;-webkit-justify-content:center!important;justify-content:center!important;line-height:1!important;border:none!important;font-size:10px!important;font-size:var(--goods-action-icon-font-size,10px)!important;color:#646566!important;color:var(--goods-action-icon-text-color,#646566)!important;min-width:48px;min-width:var(--goods-action-icon-width,48px);height:50px!important;height:var(--goods-action-icon-height,50px)!important}.van-goods-action-icon__icon{display:-webkit-flex;display:flex;margin:0 auto 5px;color:#323233;color:var(--goods-action-icon-color,#323233);font-size:18px;font-size:var(--goods-action-icon-size,18px)}
</style>