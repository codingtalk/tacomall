
import eventTypes from './event-types';

export default function (instance) {
  this.$options.name && eventTypes[this.$options.name].forEach(et => {
    const listener = this.$listeners[et];
    listener && instance.on(et, () => {
      this.$emit(et, { AMap: global.AMap, map: this.map });
    });
  });
}
