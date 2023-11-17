<template>
  <div class="layout_auth">
    <div class="auth_left" :class="{ 'auth_left--unfold': leftUnfold }">
      <template v-if="leftUnfold">
        <div class="left_logo">
          <h4>{{appInfoData?.nameEN}}|{{appInfoData?.name}}</h4>
        </div>
        <div class="left_menu">
          <a-menu mode="inline" theme="dark">
            <template v-for="(item, key) in menu">
              <a-sub-menu :key="`${key}-sub`" :index="key + ''" v-if="item.sub">
                <template #icon>
                  <i :class="`${item.isCoreIcon ? 'iconfont_core' : 'iconfont'}`" v-html="item.icon"></i>
                </template>
                <template #title>
                  <span>{{ item.title }}</span>
                </template>
                <a-menu-item :key="`${key}-${key1}`" v-for="(item1, key1) in item.sub" @click="go(item1.path)">{{
                item1.title }}</a-menu-item>
              </a-sub-menu>
              <a-menu-item :key="key" :index="key + ''" @click="go(item.path)" v-else>
                <template #icon>
                  <i :class="`${item.isCoreIcon ? 'iconfont_core' : 'iconfont'}`" v-html="item.icon"></i>
                </template>
                <span>{{ item.title }}</span>
              </a-menu-item>
            </template>
          </a-menu>
        </div>
      </template>
      <template v-else>
        <div class="left_logo">
          <img src="/image/logo-icon_small.png" alt />
        </div>
        <div class="left_menu">
          <div class="menu_item" :key="key" v-for="(item, key) in menu">
            <div class="item_icon" @click="!item.sub && go(item.path)">
              <i :class="`${item.isCoreIcon ? 'iconfont_core' : 'iconfont'}`" v-html="item.icon"></i>
            </div>
            <div class="item_panel">
              <div class="panel_item" :key="key1" @click="go(item1.path)" v-for="(item1, key1) in item.sub">
                <span>{{ item1.title }}</span>
              </div>
            </div>
          </div>
        </div>
      </template>
    </div>
    <div class="auth_right">
      <div class="right_header">
        <div class="header_left">
          <div class="left_btn">
            <div class="btn_item" @click="leftUnfold = !leftUnfold">
              <i class="iconfont_core" v-if="leftUnfold">&#xe623;</i>
              <i class="iconfont_core" v-else>&#xe624;</i>
            </div>
            <div class="btn_item" @click="doRefresh">
              <i class="iconfont_core">&#xe60e;</i>
            </div>
          </div>
          <div class="left_path">
            <span>首页</span>
            <span>dashboard</span>
          </div>
        </div>
        <div class="header_right">
          <div class="right_search">
            <input type="text" placeholder="搜索页面信息" />
            <i class="iconfont_core" @click="go('/search?q=tacomall')">&#xe616;</i>
          </div>
          <div class="right_btn">
            <div class="btn_item">
              <i class="iconfont_core">&#xe748;</i>
            </div>
            <div class="btn_item">
              <i class="iconfont_core">&#xe617;</i>
            </div>
            <div class="btn_item" @click="toggleFullScreen">
              <i class="iconfont_core" v-if="isFullScreen">&#xe6fe;</i>
              <i class="iconfont_core" v-else>&#xe61f;</i>
            </div>
            <div class="btn_item">
              <dropdown type="hover" styles="display:flex;align-items: center;">
                <img src="/image/avatar-default.png" alt />
                <span>{{ $_.get(user, "nickname") }}</span>
                <template #expand>
                  <div class="account_link">
                    <div class="link_block">
                      <div class="block_item">
                        <i class="iconfont_core">&#xe649;</i>
                        <span>个人中心</span>
                      </div>
                      <div class="block_item">
                        <i class="iconfont_core">&#xe620;</i>
                        <span>个人设置</span>
                      </div>
                    </div>
                    <div class="link_block">
                      <div class="block_item" @click="doLogout">
                        <i class="iconfont_core">&#xe795;</i>
                        <span>退出登录</span>
                      </div>
                    </div>
                  </div>
                </template>
              </dropdown>
            </div>
            <div class="btn_item">
              <i class="iconfont_core">&#xe663;</i>
            </div>
          </div>
        </div>
      </div>
      <div class="right_history">
        <div class="history_left">
          <div class="left_item" :key="key" @click="go(item.url)" v-for="(item, key) in history">
            <a-tag closable size="medium" @close="deleteHistoryBar(key)" v-if="key == history.length - 1">{{ item.name
            }}</a-tag>
            <a-tag closable size="medium" type="info" @close="deleteHistoryBar(key)" v-else>{{ item.name }}</a-tag>
          </div>
        </div>
        <div class="history_right">
          <i class="iconfont_core">&#xe63e;</i>
        </div>
      </div>
      <div class="right_setting" @click="openSetting">
        <i class="iconfont_core">&#xe620;</i>
      </div>
      <div class="right_main">
        <div class="main_content">
          <router-view></router-view>
        </div>
        <div class="main_footer">
          <div class="footer_link">
            <a href="/" target="_blank">官网</a>
            <a href="/" target="_blank">关于我们</a>
          </div>
          <div class="footer_copyright">
            <span>Copyright © 2021 {{appInfoData?.nameEN}}|{{appInfoData?.name}}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="jsx" setup>
import { defineComponent, ref } from "vue";
import { storeToRefs } from 'pinia';
import { $drawer, toParse, toStringify, localCache, Popup} from "codingtalk-vue-toolkit";
import config from "@/config";
import useAppStore from '@/store/app';
import { go, rep } from "@/utils/navigator";
import { message, Switch, Modal } from "ant-design-vue";
import dropdown from '@/components/dropdown/index.vue';

const appStore = useAppStore();
const { historyUrlKey } = config;

let leftUnfold = ref(true);
let { history, menu, user, appInfoData } = storeToRefs(appStore);

const deleteHistoryBar = (index) => {
  if (appStore.history.length === 1) {
    message.warning("已是最后一页啦");
    return;
  }
  let arrHis = localCache.get(historyUrlKey) ? toParse(localCache.get(historyUrlKey)) : [];
  arrHis.splice(index, 1);
  localCache.set(historyUrlKey, toStringify(arrHis));
  appStore.setHistory(toStringify(localCache.get(historyUrlKey)));
  this.rep(appStore.history[appStore.history.length - 1].url);
};

const openSetting = () => {
  const { open, ready, confirm } = Popup();
  open('drawer', defineComponent({
    setup() {
      return () => <div class="app_setting">
        <div class="setting_close">
          <i class="iconfont_core icon-guanbi"></i>
        </div>
        <div class="setting_section">
          <div class="section_item">
            <div class="item_title">
              <span>整体风格设置</span>
            </div>
            <div class="item_content">
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>布局</span>
                  </div>
                </div>
                <div class="block_main block_main--layout">
                  <div class="layout_item">
                    <div class="a" style="background: #fff"></div>
                    <div class="b" style="background: #fff"></div>
                    <div class="c" style="background: #f0f2f5"></div>
                    <i class="iconfont_core icon-xuanze"></i>
                  </div>
                  <div class="layout_item layout_item--active">
                    <div class="a" style="background: #001529"></div>
                    <div class="b" style="background: #fff"></div>
                    <div class="c" style="background: #f0f2f5"></div>
                    <i class="iconfont_core icon-xuanze"></i>
                  </div>
                  <div class="layout_item">
                    <div class="a" style="background: #001529"></div>
                    <div class="b" style="background: #001529"></div>
                    <div
                      class="c"
                      style="background: rgba(0, 21, 41, 0.85)"
                    ></div>
                    <i class="iconfont_core icon-xuanze"></i>
                  </div>
                </div>
              </div>
              <div class="content_block">
                <div class="block_title">
                  <div class="t-left">
                    <span>主题</span>
                  </div>
                </div>
                <div class="block_main block_main--theme">
                  <div
                    class="theme_item"
                    style="background: rgb(24, 144, 255)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(245, 34, 45)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(250, 84, 28)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(250, 173, 20)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(19, 194, 194)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(82, 196, 26)"
                  ></div>
                  <div
                    class="theme_item"
                    style="background: rgb(47, 84, 235)"
                  ></div>
                </div>
              </div>
            </div>
          </div>
          <div class="section_item">
            <div class="item_title">
              <span>内容区域</span>
            </div>
            <div class="item_content">
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>顶栏</span>
                  </div>
                  <div class="title_right">
                    <Switch size="small" />
                  </div>
                </div>
              </div>
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>页脚</span>
                  </div>
                  <div class="title_right">
                    <Switch size="small" />
                  </div>
                </div>
              </div>
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>菜单</span>
                  </div>
                  <div class="title_right">
                    <Switch size="small" />
                  </div>
                </div>
              </div>
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>菜单头</span>
                  </div>
                  <div class="title_right">
                    <Switch size="small" />
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="section_item">
            <div class="item_title">
              <span>其他设置</span>
            </div>
            <div class="item_content">
              <div class="content_block">
                <div class="block_title">
                  <div class="title_left">
                    <span>色弱模式</span>
                  </div>
                  <div class="title_right">
                    <Switch size="small" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    },
  }), {
    title: '系统设置'
  },)
}
const doLogout = () => {
  Modal.confirm({
    title: '登出提示',
    content: '确定注销当前登录账号？',
    onOk: () => {
      appStore.logout().then(() => {
        rep("/login");
        window.location.reload();
      });
    }
  });
};
</script>

<style lang="less">
.app_setting {
  padding: 20px;

  .setting_close {
    position: absolute;
    top: 20px;
    right: 20px;
  }

  .setting_section {
    .section_item {
      padding: 20px 0;

      .item_title {}

      .item_content {
        padding-top: 20px;

        .content_block {
          .block_title {
            display: flex;
            align-items: center;
            justify-content: space-between;
          }

          .block_main {
            padding: 20px 0;

            &--layout {
              display: flex;

              .layout_item {
                position: relative;
                width: 44px;
                height: 36px;
                box-shadow: 0 1px 2.5px 0 rgba(0, 0, 0, 0.18);

                .a {
                  position: absolute;
                  top: 0;
                  left: 0;
                  width: 10px;
                  height: 100%;
                }

                .b {
                  position: absolute;
                  top: 0;
                  right: 0;
                  width: 34px;
                  height: 10px;
                }

                .c {
                  position: absolute;
                  bottom: 0;
                  right: 0;
                  width: 34px;
                  height: 26px;
                }

                i {
                  display: none;
                }

                &-active {
                  i {
                    position: absolute;
                    bottom: 5px;
                    right: 5px;
                    display: inline-block;
                    color: #1890ff;
                  }
                }

                &:not(:first-child) {
                  margin-left: 20px;
                }
              }
            }

            &--theme {
              display: flex;

              .theme_item {
                width: 20px;
                height: 20px;

                &:not(:first-child) {
                  margin-left: 20px;
                }
              }
            }
          }

          &:not(:first-child) {
            margin-top: 20px;
          }
        }
      }

      &:not(:first-child) {
        margin-top: 30px;
        border-top: 1px solid #e8eaec;
      }
    }
  }
}

.layout_auth {
  position: fixed;
  top: 0;
  bottom: 0;
  width: 100%;
  display: flex;

  .auth_left {
    display: flex;
    flex-direction: column;
    width: 70px;
    background: #001529;
    box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
    z-index: 10;
    transition: width 100ms ease-in;

    .left_logo {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-grow: 0;
      width: 100%;
      height: 65px;
      box-sizing: border-box;
      border-bottom: 1px solid #363e4f;

      img {
        width: 35px;
        height: 35px;
      }
    }

    .left_menu {
      flex: 1;
      overflow-y: scroll;
      overflow-x: visible;

      .menu_item {
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 50px;

        .item_icon {
          i {
            font-size: 25px;
            color: white;
          }
        }

        .item_panel {
          position: absolute;
          top: 0;
          left: 71px;
          display: none;
          background: #001529;

          .panel_item {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 200px;
            height: 40px;

            span {
              color: white;
            }

            &:hover {
              cursor: pointer;
              background: #1890ff;
            }
          }
        }

        &:hover {
          cursor: pointer;
          background: #1890ff;

          .i-panel {
            display: block;
          }
        }
      }

      &>.ant-menu-item {
        padding-bottom: 50px;
      }

      &::-webkit-scrollbar {
        display: none;
      }
    }

    &--unfold {
      width: 256px;

      .left_logo {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 65px;
        box-sizing: border-box;
        border-bottom: 1px solid #363e4f;

        h4 {
          color: white;
        }
      }

      .left_menu {
        .ant-menu-item {
          border: none;
        }

        .ant-menu-item,
        .ant-menu-submenu-title {
          i {
            padding-right: 10px;
            font-size: 25px;
          }
        }

        .is-active {
          background: #1890ff !important;
        }
      }
    }
  }

  .auth_right {
    display: flex;
    flex-direction: column;
    flex: 1;
    overflow-x: auto;
    background: #f7f7f7;

    .right_header {
      width: 100%;
      display: flex;
      justify-content: space-between;
      height: 64px;
      background: white;
      box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
      z-index: 10;

      .header_left {
        display: flex;

        .left_btn {
          display: flex;

          .btn_item {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 42px;

            i {
              font-size: 25px;
            }

            &:hover {
              cursor: pointer;
            }
          }
        }

        .left_path {
          display: flex;
          align-items: center;

          span {
            position: relative;
            padding-left: 20px;

            &:not(:first-child) {
              &:before {
                position: absolute;
                left: 10px;
                content: "/";
              }
            }
          }
        }
      }

      .header_right {
        display: flex;
        align-items: center;
        padding-right: 20px;

        .right_search {
          display: flex;
          align-items: center;
          height: 30px;
          padding: 0 10px;
          background: #f7f7f7;

          input {
            border: none;
            background: #f7f7f7;
            outline: none;
          }

          i {
            font-size: 20px;

            &:hover {
              cursor: pointer;
            }
          }
        }

        .right_btn {
          display: flex;

          .btn_item {
            display: flex;
            align-items: center;
            height: 64px;
            padding: 0 10px;

            i {
              font-size: 25px;
            }

            img {
              width: 40px;
              height: 40px;
              border-radius: 20px;
            }

            span {
              padding-left: 10px;
            }

            .account_link {
              padding: 5px;
              background: white;
              box-shadow: 0 6px 16px -8px rgba(0, 0, 0, 0.08),
                0 9px 28px 0 rgba(0, 0, 0, 0.05),
                0 12px 48px 16px rgba(0, 0, 0, 0.03);

              .link_block {
                .block_item {
                  display: flex;
                  align-items: center;
                  height: 40px;
                  width: 120px;
                }

                &:not(:first-child) {
                  border-top: 1px solid #e8eaec;
                }
              }
            }

            &:hover {
              cursor: pointer;
            }
          }
        }
      }
    }

    .right_history {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 44px;
      padding: 0 24px;
      z-index: 9;

      .history_left {
        display: flex;
        align-items: center;

        .left_item {
          &:hover {
            cursor: pointer;
          }

          &:not(:first-child) {
            margin-left: 10px;
          }
        }
      }

      .history_right {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 30px;
        height: 30px;
        border-radius: 5px;
        background: white;
      }
    }

    .right_setting {
      position: fixed;
      top: 50%;
      right: 0;
      transform: translateY(-50%);
      display: flex;
      align-items: center;
      justify-content: center;
      width: 60px;
      height: 60px;
      background: #1890ff;
      z-index: 999;

      i {
        font-size: 30px;
        color: white;
      }

      &:hover {
        cursor: pointer;
      }
    }

    .right_main {
      position: relative;
      flex: 1;
      padding: 0 20px;
      overflow: auto;

      .main_content {}

      .main_footer {
        padding: 48px 0 24px 0;

        .footer_link {
          display: flex;
          align-items: center;
          justify-content: center;

          a {
            color: #515a6e;

            &:not(:first-child) {
              padding-left: 30px;
            }
          }
        }

        .footer_copyright {
          display: flex;
          justify-content: center;
          padding-top: 10px;
        }
      }
    }
  }
}
</style>
