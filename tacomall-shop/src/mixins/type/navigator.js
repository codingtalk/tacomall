import Vue from 'vue';
Vue.mixin({
  methods: {
    go (s) {
      this.$router.push({ path: s });
    },
    rep (s) {
      if (s === this.$route.fullPath) {
        return;
      }
      this.$router.replace({ path: s });
    },
    back () {
      if (window.history.length === 1) {
        this.$router.replace({ path: '/' });
        return;
      }
      this.$router.go(-1);
    }
  }
});
