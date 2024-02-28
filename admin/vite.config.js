import { fileURLToPath, URL } from 'url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import topLevelAwait from 'vite-plugin-top-level-await'
import postcsspxtoviewport8plugin from "postcss-px-to-viewport-8-plugin";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    vueJsx(),
    topLevelAwait({
      promiseExportName: '__tla',
      promiseImportName: i => `__tla_${i}`
    })
  ],
  server: {
    host: '0.0.0.0',
    port: 3050,
    open: false
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  css: {
    preprocessorOptions: {
      less: {
        charset: false,
        additionalData:
          '@import "@/assets/style/var.less"; @import "@/assets/style/mixins.less";',
      },
    },
    postcss: {
      plugins: [
        postcsspxtoviewport8plugin({
          unitToConvert: "px",
          viewportWidth: (file) => {
            let num = 1920;
            if (file.indexOf("m_") !== -1) {
              num = 375;
            }
            return num;
          },
          unitPrecision: 5,
          propList: ["*"],
          viewportUnit: "vw",
          fontViewportUnit: "vw",
          selectorBlackList: [],
          minPixelValue: 1,
          mediaQuery: true,
          replace: true,
          exclude: [/node_modules\/ant-design-vue/],
          include: [],
          landscape: false,
          landscapeUnit: "vw",
          landscapeWidth: 1024,
        }),
      ],
    },
  },
})
