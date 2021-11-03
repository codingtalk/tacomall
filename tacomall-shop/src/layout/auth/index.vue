<template>
  <div class="auth">
    <div class="a-left" :class="{ 'a-left-unfold': leftUnfold }">
      <template v-if="leftUnfold">
        <div class="l-logo">
          <h4>塔可商城TACOMALL</h4>
        </div>
        <div class="l-menu">
          <el-menu
            default-active="1"
            background-color="#001529"
            text-color="#fff"
            active-text-color="#fff"
          >
            <template v-for="(item, key) in menu">
              <el-submenu :key="key" :index="key + ''" v-if="item.sub">
                <template slot="title">
                  <i :class="`iconfont ${item.icon}`"></i>
                  <span>{{ item.title }}</span>
                </template>
                <el-menu-item-group :key="key1 + ''" v-for="(item1, key1) in item.sub">
                  <el-menu-item
                    :index="`${key}-${key1}`"
                    @click.native="go(item1.path)"
                  >{{ item1.title }}</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              <el-menu-item :key="key" :index="key + ''" @click.native="go(item.path)" v-else>
                <i :class="`iconfont ${item.icon}`"></i>
                <span slot="title">{{ item.title }}</span>
              </el-menu-item>
            </template>
          </el-menu>
        </div>
      </template>
      <template v-else>
        <div class="l-logo">
          <img src="/image/logo-icon_small.png" alt />
        </div>
        <div class="l-menu">
          <div class="m-item" :key="key" v-for="(item, key) in menu">
            <div class="i-icon" @click="!item.sub && go(item.path)">
              <i :class="`iconfont ${item.icon}`"></i>
            </div>
            <div class="i-panel">
              <div
                class="p-item"
                :key="key1"
                @click="go(item1.path)"
                v-for="(item1, key1) in item.sub"
              >
                <span>{{ item1.title }}</span>
              </div>
            </div>
          </div>
        </div>
      </template>
    </div>
    <div class="a-right" v-loading="layoutAuthLoading">
      <div class="r-header">
        <div class="h-left">
          <div class="l-btn">
            <div class="b-item" @click="leftUnfold = !leftUnfold">
              <i class="iconfont icon-shouqi" v-if="leftUnfold"></i>
              <i class="iconfont icon-zhankai" v-else></i>
            </div>
            <div class="b-item" @click="doRefresh">
              <i class="iconfont icon-shuaxin"></i>
            </div>
          </div>
          <div class="l-path">
            <span>首页</span>
            <span>详情页面</span>
            <span>基础详情页面</span>
          </div>
        </div>
        <div class="h-right">
          <div class="r-search">
            <input type="text" placeholder="搜索页面信息" />
            <i class="iconfont icon-search" @click="go('/search?q=tacomall')"></i>
          </div>
          <div class="r-btn">
            <div class="b-item">
              <i class="iconfont icon-bug"></i>
            </div>
            <div class="b-item">
              <i class="iconfont icon-xiaoxi"></i>
            </div>
            <div class="b-item" @click="rep('/dashboard')">
              <i class="iconfont icon-tongji"></i>
            </div>
            <div class="b-item" @click="toggleFullScreen">
              <i class="iconfont icon-small-screen2" v-if="isFullScreen"></i>
              <i class="iconfont icon-quanping" v-else></i>
            </div>
            <div class="b-item">
              <dropdown-box type="hover" styles="display:flex;align-items: center;">
                <template>
                  <img src="/image/avatar-default.png" alt />
                  <span>{{ $_.get(info, 'nickname') }}</span>
                </template>
                <template slot="expand">
                  <div class="account-link">
                    <div class="al-block">
                      <div class="ab-item">
                        <i class="iconfont icon-geren"></i>
                        <span>个人中心</span>
                      </div>
                      <div class="ab-item">
                        <i class="iconfont icon-shezhi"></i>
                        <span>个人设置</span>
                      </div>
                    </div>
                    <div class="al-block">
                      <div class="ab-item" @click="doLogout">
                        <i class="iconfont icon-quit-s"></i>
                        <span>退出登录</span>
                      </div>
                    </div>
                  </div>
                </template>
              </dropdown-box>
            </div>
            <div class="b-item">
              <i class="iconfont icon-quanqiu"></i>
            </div>
          </div>
        </div>
      </div>
      <div class="r-history">
        <div class="h-left">
          <div class="l-item" :key="key" @click="go(item.url)" v-for="(item, key) in historyBar">
            <el-tag
              closable
              size="medium"
              @close="deleteHistoryBar(key)"
              v-if="key == historyBar.length - 1"
            >{{ item.name }}</el-tag>
            <el-tag
              closable
              size="medium"
              type="info"
              @close="deleteHistoryBar(key)"
              v-else
            >{{ item.name }}</el-tag>
          </div>
        </div>
        <div class="h-right">
          <i class="iconfont icon-xiala"></i>
        </div>
      </div>
      <div class="r-setting" @click="popupSetting = true">
        <i class="iconfont icon-shezhi"></i>
      </div>
      <div class="r-main">
        <div class="m-content">
          <router-view></router-view>
        </div>
        <div class="m-footer">
          <div class="f-link">
            <a href="/" target="_blank">官网</a>
            <a href="/" target="_blank">关于我们</a>
          </div>
          <div class="f-copyright">
            <span>Copyright © 2021 TACOMALL</span>
          </div>
        </div>
      </div>
    </div>
    <popup v-model="popupSetting">
      <div class="setting-content">
        <div class="sc-close">
          <i class="iconfont icon-guanbi"></i>
        </div>
        <div class="sc-sections">
          <div class="s-item">
            <div class="i-title">
              <span>整体风格设置</span>
            </div>
            <div class="i-content">
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>布局</span>
                  </div>
                </div>
                <div class="b-content b-content-layout">
                  <div class="lo-item">
                    <div class="a" style="background: #fff;"></div>
                    <div class="b" style="background: #fff;"></div>
                    <div class="c" style="background: #f0f2f5;"></div>
                    <i class="iconfont icon-xuanze"></i>
                  </div>
                  <div class="lo-item lo-item-active">
                    <div class="a" style="background: #001529;"></div>
                    <div class="b" style="background: #fff;"></div>
                    <div class="c" style="background: #f0f2f5;"></div>
                    <i class="iconfont icon-xuanze"></i>
                  </div>
                  <div class="lo-item">
                    <div class="a" style="background: #001529;"></div>
                    <div class="b" style="background: #001529;"></div>
                    <div class="c" style="background: rgba(0,21,41,.85);"></div>
                    <i class="iconfont icon-xuanze"></i>
                  </div>
                </div>
              </div>
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>主题</span>
                  </div>
                </div>
                <div class="b-content b-content-theme">
                  <div class="t-item" style="background: rgb(24, 144, 255);"></div>
                  <div class="t-item" style="background: rgb(245, 34, 45);"></div>
                  <div class="t-item" style="background: rgb(250, 84, 28);"></div>
                  <div class="t-item" style="background: rgb(250, 173, 20);"></div>
                  <div class="t-item" style="background: rgb(19, 194, 194);"></div>
                  <div class="t-item" style="background: rgb(82, 196, 26);"></div>
                  <div class="t-item" style="background: rgb(47, 84, 235);"></div>
                </div>
              </div>
            </div>
          </div>
          <div class="s-item">
            <div class="i-title">
              <span>内容区域</span>
            </div>
            <div class="i-content">
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>顶栏</span>
                  </div>
                  <div class="b-right">
                    <el-switch size="small" />
                  </div>
                </div>
              </div>
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>页脚</span>
                  </div>
                  <div class="b-right">
                    <el-switch size="small" />
                  </div>
                </div>
              </div>
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>菜单</span>
                  </div>
                  <div class="b-right">
                    <el-switch size="small" />
                  </div>
                </div>
              </div>
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>菜单头</span>
                  </div>
                  <div class="b-right">
                    <el-switch size="small" />
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="s-item">
            <div class="i-title">
              <span>其他设置</span>
            </div>
            <div class="i-content">
              <div class="c-block">
                <div class="b-title">
                  <div class="t-left">
                    <span>色弱模式</span>
                  </div>
                  <div class="b-right">
                    <el-switch size="small" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </popup>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from 'vuex';
import { localCache } from '@/utils/localCache';
import { fullScreen, exitFullScreen } from '@/utils/browser';
import dropdownBox from '@/components/dropdown-box';
import popup from '@/components/popup/index.vue';

export default {
  components: {
    dropdownBox,
    popup
  },
  data () {
    return {
      isFullScreen: false,
      leftUnfold: true,
      popupSetting: false
    };
  },
  computed: {
    ...mapState('sys', ['layoutAuthLoading', 'menu', 'historyBar']),
    ...mapState('staff', ['info'])
  },
  methods: {
    ...mapMutations('sys', ['SET_HISTORY_BAR']),
    ...mapActions('staff', ['logout']),
    toggleFullScreen () {
      this.isFullScreen ? exitFullScreen() : fullScreen();
      this.isFullScreen = !this.isFullScreen;
    },
    doRefresh () {
      window.location.reload();
    },
    deleteHistoryBar (index) {
      if (this.historyBar.length === 1) {
        this.$message({
          message: '已是最后一页啦',
          type: 'warning'
        });
        return;
      }
      localCache.deleteHistory(index);
      this.SET_HISTORY_BAR(localCache.getAllHistory());
      this.rep(this.historyBar[this.historyBar.length - 1].url);
    },
    doLogout () {
      this.$confirm('确定注销当前登录账号？', '登出提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.logout().then(() => {
          this.rep('/login');
          window.location.reload();
        });
      }).catch(() => {
      });
    }
  }
};
</script>

<style lang="less">
.auth {
  display: flex;
  width: 100%;
  height: 100%;
  .a-left {
    display: flex;
    flex-direction: column;
    width: 70px;
    background: #001529;
    box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
    z-index: 10;
    transition: width 100ms ease-in;

    .l-logo {
      display: flex;
      align-items: center;
      justify-content: center;
      flex-grow: 0;
      width: 100%;
      height: 65px;
      box-sizing: border-box;
      border-bottom: 1px solid #363e4f;
      img {
        width: 50px;
        height: 35px;
      }
    }

    .l-menu {
      flex: 1;
      overflow-y: scroll;
      overflow-x: visible;
      .m-item {
        position: relative;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 50px;
        .i-icon {
          i {
            font-size: 25px;
            color: white;
          }
        }
        .i-panel {
          position: absolute;
          top: 0;
          left: 71px;
          display: none;
          background: #001529;
          .p-item {
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
      & > .el-menu {
        padding-bottom: 50px;
      }
      &::-webkit-scrollbar {
        display: none;
      }
    }

    &-unfold {
      width: 256px;

      .l-logo {
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
      .l-menu {
        .el-menu {
          border: none;
        }
        .el-menu-item,
        .el-submenu__title {
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

  .a-right {
    display: flex;
    flex-direction: column;
    flex: 1;
    overflow-x: auto;

    .r-header {
      width: 100%;
      display: flex;
      justify-content: space-between;
      height: 64px;
      background: white;
      box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
      z-index: 10;

      .h-left {
        display: flex;

        .l-btn {
          display: flex;

          .b-item {
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

        .l-path {
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

      .h-right {
        display: flex;
        align-items: center;

        .r-link2v1 {
          margin-right: 20px;
          span {
            color: #1890ff;
          }
          &:hover {
            cursor: pointer;
          }
        }

        .r-search {
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

        .r-btn {
          display: flex;

          .b-item {
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

            .account-link {
              padding: 5px;
              background: white;
              box-shadow: 0 6px 16px -8px rgba(0, 0, 0, 0.08),
                0 9px 28px 0 rgba(0, 0, 0, 0.05),
                0 12px 48px 16px rgba(0, 0, 0, 0.03);
              .al-block {
                .ab-item {
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

    .r-history {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 44px;
      padding: 0 24px;
      background: #f7f7f7;
      z-index: 9;

      .h-left {
        display: flex;
        align-items: center;
        .l-item {
          &:hover {
            cursor: pointer;
          }
          &:not(:first-child) {
            margin-left: 10px;
          }
        }
      }

      .h-right {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 30px;
        height: 30px;
        border-radius: 5px;
        background: white;
      }
    }

    .r-setting {
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

    .r-main {
      flex: 1;
      overflow: auto;

      .m-content {
        padding: 0 24px;
      }
      .m-footer {
        padding: 48px 0 24px 0;

        .f-link {
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

        .f-copyright {
          display: flex;
          justify-content: center;
          padding-top: 10px;
        }
      }
    }
  }
  .setting-content {
    padding: 20px;
    .sc-close {
      position: absolute;
      top: 20px;
      right: 20px;
    }
    .sc-sections {
      .s-item {
        padding: 20px 0;
        .i-title {
        }
        .i-content {
          padding-top: 20px;
          .c-block {
            .b-title {
              display: flex;
              align-items: center;
              justify-content: space-between;
            }
            .b-content {
              padding: 20px 0;
              &-layout {
                display: flex;
                .lo-item {
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
              &-theme {
                display: flex;
                .t-item {
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
}
</style>
