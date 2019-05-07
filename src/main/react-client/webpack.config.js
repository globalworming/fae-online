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
    path: path.resolve(__dirname, "../resources/static/js/"),
    publicPath: "/dist/",
    filename: "bundle.js"
  },
  devServer: {
    contentBase: path.join(__dirname, "../resources/static/"),
    port: 3000,
    publicPath: "http://localhost:3000/dist/",
    hotOnly: true,
    proxy: {
      '/ws': 'http://127.0.0.1:8080/ws'
    },
  },
  plugins: [new webpack.HotModuleReplacementPlugin()]
};