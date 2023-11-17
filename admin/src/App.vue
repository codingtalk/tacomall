<template>
  <router-view></router-view>
</template>

<script setup>
import { onMounted } from "vue";
import useAppStore from "@/store/app";

onMounted(() => {
  const appStore = useAppStore();
  appStore.init();
});
</script>

<style lang="less">
</style>
