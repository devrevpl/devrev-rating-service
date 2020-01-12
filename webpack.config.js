var path = require('path');

const webpack = require('webpack');
const HtmlWebPackPlugin = require('html-webpack-plugin');
const express = require('express');

module.exports = (env, options) => { return {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    cache: false,
    mode: 'development',
    output: {
        path: path.resolve(__dirname, './src/main/resources'),
        filename: 'static/built/bundle.js',
        publicPath: '/'
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"],
                        plugins: [
                            ["@babel/plugin-proposal-class-properties", {"loose": true}]
                        ]
                    }
                }
            },
            {
                test: /\.html$/,
                use: [
                    {
                        loader: "html-loader"
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebPackPlugin({
            template: "./src/main/resources/templates/index.html"
        }),
        new webpack.DefinePlugin({
            MOVIES_URL: `${options.mode}`==='development'? JSON.stringify('http://localhost:8080/api/movies/') : JSON.stringify('/api/movies/'),
            RATE_MOVIE_URL: `${options.mode}`==='development'? JSON.stringify('http://localhost:8080/api/movie/') : JSON.stringify('/api/movie/')
        })
    ],
    devServer: {
        before: function(app, server, compiler) {
            app.use('/icons', express.static(path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'icons')));
            app.use('/main.css', express.static(path.resolve(__dirname, 'src', 'main', 'resources', 'static', 'main.css')))
        }
    }
}};