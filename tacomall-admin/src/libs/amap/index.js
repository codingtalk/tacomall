
import amap from './components/map';
import amapOverlay from './components/overlay';

export default {
  install (Vue, options) {
    const { ak, version } = options;
    Vue.prototype._AMAP = () => ({ ak, version });

    Vue.component(amap.name, amap);
    Vue.component(amapOverlay.name, amapOverlay);
  }
};
