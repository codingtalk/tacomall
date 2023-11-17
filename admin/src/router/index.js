import { createRouter, createWebHistory } from "vue-router";
import { routesInit } from "./routes/init";
import middlewareAfterEach from "./middleware/afterEach";
import middlewareBeforeEach from "./middleware/beforeEach";

const router = createRouter({
  history: createWebHistory(),
  routes: [...routesInit],
});

middlewareBeforeEach(router);
middlewareAfterEach(router);

export default router;
