<template>
    <view class="cell">
        <view class="c-left">
            <text>{{title}}</text>
        </view>
        <view class="c-right">
            <slot></slot>
            <text v-if="value">{{value}}</text>
            <text class="iconfont" v-if="isLink">&#xe93d;</text>
        </view>
    </view>
</template>

<script>
    export default{
        props: ['title', 'value', 'isLink']
    }
</script>

<style lang="less">
    .cell{
        position: relative;
        display: flex;
        justify-content: space-between;
        align-items: center;
        min-height: 133rpx;
        .c-left{
            display: flex;
            align-items: center;
            text{
                font-size: 30rpx;
                color:#333;
            }
        }
        .c-right{
            display: flex;
            align-items: center;
            font-size: 30rpx;
            color:#999;
        }
        &::before {
            position: absolute;
            content: '';
            top: 0;
            left: 0;
            width: 100%;
            height: 0;
            border-top: 1px solid #e8eaec;
            transform: scaleY(0.5);
        }
    }
</style>
