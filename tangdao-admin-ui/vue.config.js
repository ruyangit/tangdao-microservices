module.exports = {
  lintOnSave: false,
  devServer: {
    proxy: {
      '/frontend': {
        target: `${process.env.SW_PROXY_TARGET || "http://127.0.0.1:8000"}`,
        changeOrigin: true,
      },
    },
  },
  chainWebpack: config => {
    const svgRule = config.module.rule('svg');
    svgRule.uses.clear();
    svgRule
      .use('svg-sprite-loader')
      .loader('svg-sprite-loader')
      .options({
        symbolId: '[name]',
      });
  },
};