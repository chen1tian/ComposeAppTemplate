# Android Compose App应用模板

包含基础界面

## 需求

- .net8.0 sdk [点击下载](https://dotnet.microsoft.com/download/dotnet/8.0)

## 安装

1. 克隆项目
2. 双击`安装模板.bat`运行

## 使用
1. 打开命令行工具
2. 定位到项目根目录，例如`cd D:\AndroidComposeApp`
3. 创建项目
   ```shell
   dotnet new compose.app -n MyComposeApp --ns com.mycompany.mycomposeapp
   ```
   - `-n` 指定项目名称
   - `--ns` 指定项目命名空间
4. 执行命令时，会提示是否运行脚本`Setup.ps1`，输入`y`并回车。此脚本的作用是修改项目目录结构使其匹配命名空间。
5. 使用 Android Studio 打开项目即可开始编码。
