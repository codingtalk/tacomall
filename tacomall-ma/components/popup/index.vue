<!--
 * @Author: 码上talk|RC
 * @Date: 2020-10-25 11:27:27
 * @LastEditTime: 2020-10-29 10:39:21
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-uniapp/components/popup/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
    <view class="popup" :class="{'popup-visible': popupVisible}" @tap.self="close()">
        <view class="popup-bottom" @tap.stop>
            <slot></slot>
        </view>
    </view>
</template>

<script>
export default {
    data() {
        return {
            popupVisible: this.visible
        }
    },
    props: {
        visible: {
            type: Boolean
        }
    },
    methods: {
        open() {
            this.popupVisible = true
        },
        close() {
            this.popupVisible = false
        }
    }
}
</script>

<style lang="less" scoped>
.popup {
    position: fixed;
    top: 0;
    bottom: 0;
    width: 100%;
    z-index: 1000;
    display: none;
    .popup-bottom {
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
    }
    &-visible {
        display: block;
        background: rgba(0, 0, 0, 0.2);
    }
}
</style>