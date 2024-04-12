// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   // 生产环境和开发环境下，publicPath 设置为 /，（可不要）
//   publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
//   // 所有前端发送的以/api开头的请求都会被代理到http://localhost:8080，并且代理服务器会将/api前缀去掉。
//   devServer: {
//     // 自动打开浏览器
//     // open: true,
//     port: 8080,
//     proxy: {
//       '/api': {
//         target: 'http://localhost:8080', // 后端服务地址
//         changeOrigin: true,
//         pathRewrite: {
//           '^/api': '', // 将请求地址中的 /api 前缀替换为空(映射springMvc时)
//         },
//       },
//     },
//   },
//   transpileDependencies: true
// })
