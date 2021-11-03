<template>
  <div class="goods-info">
    <block title="商品信息">
      <grid :col="3" class="b-grid">
        <grid-item label="名称：">
          <span>{{ infoGet('name') }}</span>
        </grid-item>
        <grid-item label="售价：">
          <span>{{ infoGet('amount') }}</span>
        </grid-item>
        <grid-item label="成本价：">
          <span>{{ infoGet('amountCost') }}</span>
        </grid-item>
        <grid-item label="赠送业绩：">
          <span>{{ [infoGet('_raw.integralGiveDividend', 0), infoGet('_raw.integralGiveDivisor', 0)] | divisorFilter }}</span>
        </grid-item>
        <grid-item label="赠送兑换积分：">
          <span>{{ infoGet('integralExchangeGive') }}</span>
        </grid-item>
        <grid-item label="兑换所需积分：">
          <span>{{ infoGet('integralExchangeRequire') }}</span>
        </grid-item>
      </grid>
    </block>
    <block title="商品规格">
      <el-table :data="infoGet('goodsItemsList', [])">
        <el-table-column label="序号">
          <template slot-scope="scope">
            <p>{{ scope.row.id }}</p>
          </template>
        </el-table-column>
        <el-table-column label="封面">
          <template slot-scope="scope">
            <div style="width: 100px;height:100px;">
              <img-video-previewer :src="scope.row._raw.thumbnail"></img-video-previewer>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="名称">
          <template>
            <p>{{ infoGet('name') }}</p>
          </template>
        </el-table-column>
        <el-table-column label="规格">
          <template slot-scope="scope">
            <p>{{ scope.row.specJson2str }}</p>
          </template>
        </el-table-column>
        <el-table-column label="价格">
          <template slot-scope="scope">
            <p>￥{{ scope.row.amountCost | amountFormat}}</p>
          </template>
        </el-table-column>
        <el-table-column label="条形码">
          <template slot-scope="scope">
            <p>{{ scope.row.barCode }}</p>
          </template>
        </el-table-column>
        <el-table-column label="库存">
          <template slot-scope="scope">
            <p>{{ scope.row.stock }}</p>
          </template>
        </el-table-column>
      </el-table>
    </block>
    <block title="图文详情">
      <text-previewer :content="infoGet('detailMobileText')"></text-previewer>
    </block>
  </div>
</template>

<script>
import infoMixin from '@/mixins/type/info';
import imgVideoPreviewer from '@/components/img-video-previewer';
import textPreviewer from '@/components/text-previewer';
import grid from '@/components/grid';
import gridItem from '@/components/grid/item';
import block from '@/components/block';

export default {
  mixins: [infoMixin],
  components: {
    imgVideoPreviewer,
    textPreviewer,
    grid,
    gridItem,
    block
  },
  data () {
    return {
      info: {
        config: {
          entity: 'Goods',
          action: 'info'
        },
        result: {}
      }
    };
  }
};
</script>

<style lang="less">
.goods-info {
  background: white;
}
</style>
