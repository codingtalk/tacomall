<template>
  <div class="page index">
    <div class="index_welcome">
      <div class="welcome_title">
        <h2>工作台</h2>
      </div>
      <div class="welcome_content">
        <div class="content_left">
          <div class="left_avatar">
            <img src="/image/avatar-default.png" alt="">
          </div>
          <div class="left_text">
            <div class="text_hi">
              <p>Hi，Jerry，晚上好，工作再忙也要花点时间陪伴家人哟！</p>
            </div>
            <div class="text_weather">
              <p>今日阴转小雨，22℃ - 32℃，出门记得带伞哦。</p>
            </div>
          </div>
        </div>
        <div class="content_right">
          <div class="right_item">
            <div class="item_label">
              <span>订单数</span>
            </div>
            <div class="item_val">
              <span>12</span>
            </div>
          </div>
          <div class="right_item">
            <div class="item_label">
              <span>用户数</span>
            </div>
            <div class="item_val">
              <span>12</span>
            </div>
          </div>
          <div class="right_item">
            <div class="item_label">
              <span>支付金额</span>
            </div>
            <div class="item_val">
              <span>12</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="index_panel">
      <div class="panel_left">
        <div class="left_section left_section--app">
          <panel-card>
            <template #titleLeft>
              <span>我的应用</span>
            </template>
            <template #titleRight>
              <span>更多应用</span>
            </template>
            <cvt-grid class="app_gallery" :col="3" :border="true">
              <cvt-grid-item>
                <div class="gallery_item">
                  <div class="item_logo" style="background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);">
                    <i class="iconfont">&#xe608;</i>
                  </div>
                  <div class="item_info">
                    <div class="info_name">
                      <span>费用报销</span>
                    </div>
                    <div class="info_brief">
                      <p>希望是一个好东西，也许是最好的，好东西是不会消亡的。</p>
                    </div>
                  </div>
                </div>
              </cvt-grid-item>
              <cvt-grid-item></cvt-grid-item>
              <cvt-grid-item></cvt-grid-item>
              <cvt-grid-item></cvt-grid-item>
              <cvt-grid-item></cvt-grid-item>
              <cvt-grid-item></cvt-grid-item>
            </cvt-grid>
            <div class="app_gallery">

            </div>
          </panel-card>
        </div>
        <div class="left_section left_section--event">
          <panel-card>
            <template #titleLeft>
              <span>动态</span>
            </template>
          </panel-card>
        </div>
      </div>
      <div class="panel_right">
        <div class="right_section right_section--link">
          <panel-card>
            <template #titleLeft>
              <span>快速开始</span>
            </template>
          </panel-card>
        </div>
        <div class="right_section"></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import PanelCard from '@/components/panel-card/index.vue';
</script>

<style lang="less">
@import url("../assets/style/var.less");
@import url("../assets/style/mixins.less");

.index {
  &_welcome {
    padding: 14px;
    background: white;

    .welcome_title {}

    .welcome_content {
      .flex(@j: space-between);

      .content_left {
        .flex();

        .left_avatar {
          width: 72px;
          height: 72px;

          img {
            width: 100%;
            height: 100%;
          }
        }

        .left_text {
          margin-left: 20px;

          .text_hi {
            p {
              font-size: 20px
            }
          }

          .text_weather {
            p {
              color: @text-color ;
            }
          }
        }
      }

      .content_right {
        .flex();

        .right_item {
          .flex(@d: column, @a: flex-end);

          &:not(:first-child) {
            margin-left: 20px;
          }
        }
      }
    }
  }

  &_panel {
    .flex(@a: flex-start);
    margin-top: 20px;

    .panel_left {
      flex: 1;
      margin-right: 20px;

      .left_section {
        &--app {
          .app_gallery {
            .flex();

            .gallery_item {
              .flex();
              padding: 14px;

              .item_logo {
                .flex(@j: center);
                flex-shrink: 0;
                width: 80px;
                height: 80px;

                i {
                  font-size: 60px;
                  color: white;
                }
              }

              .item_info {
                margin-left: 10px;

                .info_name {
                  span {
                    font-size: 14px;
                    color: rgba(0, 0, 0, .85);
                  }
                }

                .info_brief {
                  p {
                    font-size: 12px;
                    color: @text-color;
                  }
                }
              }

              &:hover {
                cursor: pointer;
              }
            }
          }
        }

        &:not(:first-child) {
          margin-top: 20px;
        }
      }
    }

    .panel_right {
      width: 550px;

      .right_section {
        &:not(:first-child) {
          margin-top: 20px;
        }
      }
    }
  }
}
</style>