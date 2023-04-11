const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    // proxy: {
    //   '/api': {
    //     target: 'http://localhost:8080/api/', // 你请求的第三方接口
    //     changeOrigin: true, // 在本地会创建一个虚拟服务端，然后发送请求的数据，并同时接收请 求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
    //     pathRewrite: { // 路径重写，/api/customer/list
    //       '^/api': '' // 替换target中的请求地址，也就是说以后你在请求 http://localhost:8080/api/customer/list这个地址的时候直接写成/api即可
    //     }
    //   }
    // }
  },
  assetsDir: 'static',
  parallel: false,
  publicPath: './',
})
