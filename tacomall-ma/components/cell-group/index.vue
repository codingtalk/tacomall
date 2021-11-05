<template>
    <view class="cell-group">
        <view class="g-header">
            <view class="h-left">
                <text>{{title}}</text>
            </view>
        </view>
        <view class="g-content">
            <slot></slot>
        </view>
    </view>
</template>

<script>
    export default{
        props: ['title']
    }
</script>

<style lang="less">
    .cell-group{
        padding:0 34rpx;
        margin-bottom: 20rpx;
        background: white;
        .g-header{
            display: flex;
            justify-content: space-between;
            height: 100rpx;
            .h-left{
                display: flex;
                flex-direction: column;
                justify-content: flex-end;
                text{
                    padding-bottom: 20rpx;
                    color:#666;
                }
            }
        }
    }
</style>
