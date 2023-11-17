import router from "@/router";

export function go(path) {
  router.push({
    path,
  });
}

export function rep(s) {
  router.replace({ path: s });
}
