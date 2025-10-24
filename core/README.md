# Core
一、添加依赖
1. gradle.properties 加入 android.enableJetifier=true
2. implementation("com.github.benyq:AndroidSkeleton:$version"){
     exclude(group = "org.jspecify", module = "jspecify")
   }
二、初始化
Core.setup(context)

三、更新

2025-10-24 v0.0.2
1. 增加网络请求异常处理
2. 优化日志

2025-10-23 v0.0.1
1. 项目构建
2. 基础功能
