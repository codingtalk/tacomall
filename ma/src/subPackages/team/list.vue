<template>
  <view class="page team">
    <view class="team_summary">
      <view class="summary_item">
        <view class="item_value">
          <text>{{getVal('countInvite')}}</text>
        </view>
        <view class="item_label">
          <text>直接</text>
        </view>
      </view>
      <view class="summary_item">
        <view class="item_value">
          <text>{{getVal('countSubInvite')}}</text>
        </view>
        <view class="item_label">
          <text>间接</text>
        </view>
      </view>
    </view>
    <view class="team_search">
      <view class="search_input">
        <text class="iconfont">&#xe8b9;</text>
        <input type="text" placeholder="昵称/邀请码">
      </view>
      <view class="search_btn">
        <text>搜索</text>
      </view>
    </view>
    <view class="team_cols">
      <view class="cols_item">
        <text>昵称</text>
      </view>
      <view class="cols_item">
        <text>邀请码</text>
      </view>
      <view class="cols_item">
        <text>直接</text>
      </view>
      <view class="cols_item">
        <text>间接</text>
      </view>
      <view class="cols_item">
        <text>下一级</text>
      </view>
    </view>
    <view class="team_list">
      <cut-page-loader :config="{
            entity: 'Member',
            action: 'page',
            enablePull: true
          }" :form="form" :form-pre-hook="() => ({ query: { inviteMemberId: member?.id } })">
          <template v-slot="{ pageData }">
            <view class="list_item" :key="key" v-for="(item, key) in pageData">
              <view class="item_col item_col_nickname">
                <text>{{item.nickname}}</text>
              </view>
              <view class="item_col item_col_lower">
                <text>--</text>
              </view>
              <view class="item_col item_col_sub_lower">
                <text>{{item._raw.countInvite}}</text>
              </view>
              <view class="item_col item_col_sub_lower">
                <text>{{item._raw.countSubInvite}}</text>
              </view>
              <view class="item_col item_col_next">
                <text>查看</text>
              </view>
            </view>
          </template>
          </cut-page-loader>
    </view>
  </view>
</template>

<script setup>
import { reactive } from 'vue';
import { checkToken, pullDownRefresh, pageLoad } from "@/composables/page";
import { storeApp } from "@/composables/store";

const { member } = storeApp()
const { queryInfo, getVal } = pageLoad('team', {}, () => { })
const form = reactive({})
</script>


<style lang="less">
@import url("../../assets/style/var.less");
@import url("../../assets/style/mixins.less");
@import url("./__style__/index.less");
</style>
