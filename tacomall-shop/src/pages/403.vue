<template>
  <div class="_403">
    <div class="_4-content">
      <img src="/image/403.png" alt />
      <h1>403 Authorize Forbidden</h1>
      <div class="btn" @click="rep('/')">
        <span>返回首页</span>
      </div>
    </div>
  </div>
</template>

<style lang="less">
._403 {
    display: flex;
    justify-content: center;
    ._4-content {
        display: flex;
        flex-direction: column;
        align-items: center;
        width: 300px;
        padding: 100px 0;
        img {
            width: 100%;
        }
        h1 {
            padding-top: 20px;
            white-space: nowrap;
        }
        .btn {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 100px;
            height: 30px;
            background: #1890ff;
            margin-top: 50px;
            span {
                color: white;
            }
            &:hover {
                cursor: pointer;
            }
        }
    }
}
</style>
