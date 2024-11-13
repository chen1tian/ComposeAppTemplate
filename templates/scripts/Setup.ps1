# 将模板文件移动到指定的命名空间目录下
function MoveNamespaceFiles($baseDir, $namespace, $subDir) {
    $oldDir = "$baseDir\$subDir\java\com\welldone\compose|app|template".Replace("|", "")
    $namespacePath = "$baseDir\$subDir\java\" + $namespace.Replace(".", "\")
    # Write-Host "Move-Item -Path $oldDir\* -Destination $namespacePath"
    # 父目录不存在时，创建父目录
    if (!(Test-Path $namespacePath)) {
        New-Item -ItemType Directory -Force -Path $namespacePath
    }
    Move-Item -Path "$oldDir\*" -Destination $namespacePath -Force

    # 删除空目录
    Remove-Item -Path $oldDir -Recurse -Force
}

# 命名空间
$namespace = "com.welldone.composeapptemplate"
# 项目根目录
$baseDir = "ComposeAppTemplate"


# 移动main文件
MoveNamespaceFiles $baseDir $namespace "app\src\main"

# 移动Android测试目录文件
MoveNamespaceFiles $baseDir $namespace "app\src\androidTest"

# 移动测试目录文件
MoveNamespaceFiles $baseDir $namespace "app\src\test"

# 移除scripts目录
Remove-Item -Path ".\scripts" -Recurse -Force
