<!--
 * @Author: 码上talk|RC
 * @Date: 2021-05-01 20:38:35
 * @LastEditTime: 2021-05-02 11:49:11
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /web-admin/src/libs/amap/components/customize-marker/types/project.vue
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
-->
<template>
  <div class="marker-project">
    <div class="p-icon" @click="$emit('iconClick')">
      <div class="i-img">
        <img src="~@/assets/images/map/location.png" />
      </div>
      <div class="i-name">
        <p>{{ name }}</p>
      </div>
    </div>
    <div class="p-preview" v-if="active">
      <div class="p-title">
        <div class="t-left">
          <span>{{ name }}</span>
        </div>
      </div>
      <div class="p-content">
        <div class="c-thunmbnail">
          <img src alt />
        </div>
        <div class="c-desc">
          <p>恒兴御景园智慧工地项目，原名恒兴御景园智慧工地项目，1986年改名为深圳市红岭中学。为更好管理，市教育局将其由深圳市市属重点中学转变为福田区区属重点中学，属于深圳市福田区人民政府全额拨款的公办重点学校。学校园岭初中部在深圳市园岭九街，于2004年新建石厦初中部。2005年，市、区两级政府在市中心区划地10万平方米，投资近4亿元，于2006年初动工兴建新了红岭中学高中部。2013年南园小学合并进红岭中学，2014年竹林中学合并进红岭中学，2015年成立深圳红岭教育集团，形成一校五部新格局。</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    active: {
      type: Boolean
    },
    name: {
      type: String,
      default () {
        return '未填写';
      }
    },
    config: {
      type: Object,
      default () {
        return {
          word: {
            color: '#000'
          },
          shape: {
            color: '#fff'
          }
        };
      }
    }
  }
};
</script>
<style lang="less" >
.marker-project {
  position: relative;
  .p-icon {
    display: flex;
    align-items: center;
    .i-img {
      padding-right: 10px;
      img {
        width: 25px;
        height: 25px;
      }
    }
    .i-name {
      p {
        font-size: 12px;
        white-space: nowrap;
      }
    }
    &:hover {
      .y-name {
        display: block;
      }
    }
  }
  .p-preview {
    position: absolute;
    bottom: 70px;
    left: 50%;
    transform: translateX(-50%);
    background: url("~@/assets/images/map/pro-info.png") no-repeat;
    background-size: 100% 100%;
    .p-title {
      position: relative;
      display: flex;
      align-items: center;
      justify-content: space-between;
      height: 60px;
      .t-left {
        span {
          padding-left: 30px;
          font-size: 12px;
        }
        &:before {
          position: absolute;
          content: "";
          top: 10px;
          left: 10px;
          height: 40px;
          width: 5px;
          background: #2d8cf0;
        }
      }
    }
    .p-content {
      display: flex;
      align-items: center;
      padding: 10px 10px 20px 10px;
      .c-thunmbnail {
        width: 200px;
        height: 200px;
        img {
          width: 100%;
          height: 100%;
        }
      }
      .c-desc {
        width: 500px;
        margin-left: 20px;
        font-size: 12px;
      }
    }
  }
}
</style>
