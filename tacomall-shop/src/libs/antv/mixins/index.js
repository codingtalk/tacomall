
import F2 from '@antv/f2';
import G6 from '@antv/g6';
import { Chart as G2Chart } from '@antv/g2';
const _ = require('lodash');
export const mixins = {
  props: ['data', 'defs'],
  data () {
    return {
      cId: `${this.$options.name}_${this._uid}`,
      width: 0,
      height: 0,
      throttleRender: _.throttle(this.resizeRender, 500),
      antPackage: ((name) => {
        const n2p = {
          AntvInterval: 'F2',
          AntvLine: 'F2',
          AntvNode: 'G6',
          G2Interval: 'G2',
          G2Pie: 'G2',
          G2LineInterval: 'G2'
        };
        return n2p[name];
      })(this.$options.name),
      F2: {
        chart: null,
        fn: {
          ready: () => {
            this.F2.chart = new F2.Chart(Object.assign({
              id: this.cId,
              pixelRatio: window.devicePixelRatio,
              width: this.width,
              height: this.height
            }, this.config.chart));
            this.F2.chart.source(this.data, this.defs);
            this.F2.chart.axis(false);
            if (this.config.axis) {
              this.F2.chart.axis('x', this.config.axis);
            }
            this.render(this.F2.chart);
          },
          changeSize: () => {
            this.F2.chart.changeSize(this.width, this.height);
          }
        }
      },
      G6: {
        chart: null,
        fn: {
          ready: () => {
            this.G6.chart = new G6.TreeGraph(Object.assign({
              container: this.cId,
              width: this.width,
              height: this.height
            }, this.config.chart));

            if (this.config.register) {
              this.config.register.forEach(i => {
                G6.registerNode(i.name, i.cfg(G6, this.G6.chart));
              });
            }

            this.G6.chart.data(this.data);
            this.G6.chart.render(this.G6.chart);
            this.G6.chart.fitView();
          },
          changeSize: () => {

          }
        }
      },
      G2: {
        chart: null,
        fn: {
          ready: () => {
            this.G2.chart = new G2Chart(Object.assign({
              container: this.cId,
              width: this.width,
              height: this.height
            }, this.config.chart));
            this.G2.chart.data(this.data);
            this.render(this.G2.chart);
          },
          changeSize: () => {
            this.G2.chart.changeSize(this.width, this.height);
          }
        }
      }
    };
  },
  methods: {
    listenResize () {
      window.addEventListener('resize', () => {
        this.fixedWidthAndHeight();
        this.throttleRender();
      });
    },
    fixedWidthAndHeight () {
      this.width = Number(window.getComputedStyle(this.$el).width.split('px')[0]);
      this.height = Number(window.getComputedStyle(this.$el).height.split('px')[0]);
    },
    ready () {
      this[this.antPackage].fn.ready();
    },
    resizeRender () {
      this[this.antPackage].chart.changeSize(this.width, this.height);
    }
  },
  mounted () {
    this.fixedWidthAndHeight();
    this.ready();
    this.listenResize();
  }
};
