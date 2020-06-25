module.exports= {
    // 基本路径
    publicPath: './',
    // 输出文件目录
    outputDir: 'vuehr',
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy: {
            '/': {
                target: 'http://localhost:8081',
                ws: false,
                changeOrigin: true
            },
        }
    }
}
