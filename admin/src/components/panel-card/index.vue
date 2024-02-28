<template>
    <div class="panel_card">
        <div class="card_title">
            <div class="title_left">
                <slot name="titleLeft"></slot>
            </div>
            <div class="title_right">
                <slot name="titleRight"></slot>
            </div>
        </div>
        <div class="card_main">
            <slot></slot>
        </div>
    </div>
</template>

<style lang="less">

.panel_card {
    display: inline-block;
    width: 100%;
    height: 100%;
    padding: 14px;
    background: white;

    .card_title {
        .flex(@j: space-between);
        padding-bottom: 14px;
        border-bottom: 1px solid #e8eaec;
    }
}
</style>