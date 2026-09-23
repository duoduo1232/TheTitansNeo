#!/usr/bin/env bash
# TheTitansNeo — NeoForge 侧构建脚本（MC 26.1.2 / ModDevGradle / JDK 25）
#
# 用法: bash build.sh build
#
# 两个刻意的选择（与 E:/DevTools/singleblock-neoforge 保持一致）：
# 1. GRADLE_USER_HOME 指到 E 盘 —— NeoForge 的依赖 + NeoForm 反编译产物全落这儿，
#    不吃 C 盘。NeoForm 产物已经在了（约 610 MB），所以这一步是复用的，不重新下载。
# 2. 用本机已缓存的 Gradle 发行版直接跑，不走 wrapper —— wrapper 会去 services.gradle.org
#    下载发行版，而这里 9.2.1 本来就在缓存里。
set -uo pipefail

export PATH="/usr/bin:/bin:$PATH"
export JAVA_HOME="C:/Users/admin/AppData/Roaming/.minecraft/runtime/java-runtime-epsilon"
export GRADLE_USER_HOME="E:/DevTools/gradle-home"

GRADLE_BIN="$(ls -d "$HOME"/.gradle/wrapper/dists/gradle-9.2.1-bin/*/gradle-9.2.1/bin/gradle 2>/dev/null | head -n 1)"
if [ -z "${GRADLE_BIN}" ]; then
  echo "找不到 gradle 9.2.1 发行版" >&2
  exit 1
fi

echo "JAVA_HOME       = $JAVA_HOME"
echo "GRADLE_USER_HOME= $GRADLE_USER_HOME"
echo "GRADLE          = $GRADLE_BIN"
echo "ARGS            = $*"
echo "---"

exec "$GRADLE_BIN" --no-daemon \
  -Djava.net.preferIPv4Stack=true \
  -Dorg.gradle.internal.http.connectionTimeout=60000 \
  -Dorg.gradle.internal.http.socketTimeout=60000 \
  "$@"
