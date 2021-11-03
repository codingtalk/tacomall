<!--
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:26
 * @LastEditTime: 2020-10-25 11:37:06
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-uniapp/components/counter/index.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
    <view class="counter">
        <view class="r-wrap">
            <view class="w-sub">
                <text class="iconfont">&#xe68d;</text>
            </view>
            <view class="w-text">
                <text>3</text>
            </view>
            <view class="w-add">
                <text class="iconfont">&#xe627;</text>
            </view>
        </view>
    </view>
</template>

<script type="text/ecmascript-6">
export default {
    onLoad() {
    }
}
</script>

<style lang="less" scoped>
.counter {
    display: inline-block;
    .r-wrap {
        display: flex;
        height: 50rpx;
        width: 208rpx;
        box-sizing: border-box;
        border: 1rpx solid #cfcfcf;
        .w-sub {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-basis: 50rpx;
            box-sizing: border-box;
            border-right: 1rpx solid #cfcfcf;
            background: #fafafa;
            color: #999;
        }
        .w-text {
            display: flex;
            align-items: center;
            justify-content: center;
            flex: 1;
            text {
                font-size: 28rpx;
                color: #333;
            }
        }
        .w-add {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-basis: 50rpx;
            box-sizing: border-box;
            border-left: 1rpx solid #cfcfcf;
            background: #f4f4f4;
            color: #666;
        }
    }
}
</style>
