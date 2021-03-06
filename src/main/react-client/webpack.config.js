const path = require("path");
const webpack = require("webpack");

module.exports = {
  entry: "./src/index.js",
  mode: "development",
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /(node_modules|bower_components)/,
        loader: "babel-loader",
        options: { presets: ["@babel/env"] }
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      }
    ]
  },
  resolve: { extensions: ["*", ".js", ".jsx"] },
  output: {
    path: path.resolve(__dirname, "js"),
    publicPath: "/dist/",
    filename: "bundle.js"
  },
  devServer: {
    contentBase: path.join(__dirname, "public"),
    port: 3000,
    publicPath: "http://localhost:3000/dist/",
    hotOnly: true,
    proxy: {
      '/message': 'http://127.0.0.1:8080',
      '/service': 'http://127.0.0.1:8080',
      '/world': 'http://127.0.0.1:8080',
      '/campaign': 'http://127.0.0.1:8080',
      '/scenario': 'http://127.0.0.1:8080',
      '/ws': 'ws://127.0.0.1:8080',
    },
  },
  plugins: [new webpack.HotModuleReplacementPlugin()]
};