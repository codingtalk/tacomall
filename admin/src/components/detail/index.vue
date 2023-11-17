<template>
    <div class="detail">
        <slot name="toolbar"></slot>
        <div class="detail_content">
            <slot></slot>
        </div>
    </div>
</template>

<style lang="less">
@import "../../assets/style/var.less";
@import "../../assets/style/mixins.less";

.detail {
    &_header {
        padding: 20px;
        margin-bottom: 20px;
        background: white;

        .header_title {
            .flex(@a:center, @j:space-between);
            height: 70px;
            .title_left{
                span{
                    font-size: 22px;
                }
            }
        }
        .header_content{
            .flex();
            .content_left{
                flex:1;
            }
            .content_right{
                .flex();
                .right_item{
                    .flex(@d:column,@a:flex-end);
                    span{
                        color:@text-color;
                    }
                    em{
                        font-size: 24px;
                        font-weight: 600;
                    }
                    &:not(:first-child){
                        margin-left: 20px;
                    }
                }
            }
        }
    }
}
</style>