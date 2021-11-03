
const getMapInstance = $component => $component.initMap ? $component : getMapInstance($component.$parent);
class Mixin {
  constructor () {
    this.methods = {
      ready () {
        this.map = getMapInstance(this.$parent).map;
        this.load && this.load();
        this.$emit('ready', {
          map: this.map
        });
      }
    };
    this.mounted = function () {
      const map = getMapInstance(this.$parent).map;
      const { ready } = this;
      map ? ready() : getMapInstance(this.$parent).$on('complete', ready);
    };
  }
}

export default () => new Mixin();
