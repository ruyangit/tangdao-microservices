# tangdao-master
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)       [![star](https://gitee.com/ruyangit/tangdao-microservices/badge/star.svg?theme=dark)](https://gitee.com/ruyangit/tangdao-microservices/stargazers)

#### 介绍
微服务架构，Tangdao 项目拆分，前后端分离。项目采用 springboot、springcloud、springcloud-alibaba、mybatis、fst等，学习微服务相关代码设计。

#### 软件架构
<img src="doc/tangdao.jpg" width="100%" />

#### 主要功能
用户，角色，菜单，岗位，机构，公司，数据字典，系统配置，日志，缓存（可视化redis），代码生成，组件展示等。

### 运行项目
```
mvn springboot:run
```

### 打包发布
```
mvn package -f pom.xml -Dmaven.test.skip=true
```


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request
