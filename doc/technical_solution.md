# Android 应用分类与使用时长统计技术方案文档

## 1. 概述
本技术方案用于实现一个 Android App，主要功能包括：
- 获取设备中所有已安装应用（包名、图标、名称等）
- 使用 DeepSeek API 对应用进行智能分类（大类 + 小类）
- 使用 UsageStatsManager 获取应用使用时长与最近使用时间
- 支持通过包名打开目标应用
- 使用本地数据库（SQLite / Room）存储应用信息、分类信息与统计数据

---

## 2. 整体架构设计

### 2.1 架构图（逻辑）
```
App Layer（UI）
   │
   ├── App List Module
   │       └── PackageManager（获取应用信息）
   │
   ├── Usage Stats Module
   │       └── UsageStatsManager（获取使用记录）
   │
   ├── AI Classification Module
   │       └── DeepSeek API（生成大类/小类）
   │
   └── Data Layer
           ├── Repository
           └── Room Database
```

### 2.2 模块说明
| 模块 | 功能 |
|------|------|
| App Scanner | 扫描所有安装应用 |
| AI Classifier | 调用 DeepSeek 归类 |
| App Launcher | 启动应用 |
| Usage Stats | 统计应用使用情况 |
| DB Layer | 持久化应用数据与分类信息 |

---

## 3. 功能设计

### 3.1 获取所有应用信息
使用 `PackageManager` 获取：
- 包名
- 应用名称
- 图标（可存本地或缓存）
- 是否系统应用
- 安装时间、更新时间

### 3.2 启动应用
```
val intent = pm.getLaunchIntentForPackage(packageName)
context.startActivity(intent)
```

### 3.3 AI 分类（DeepSeek API）
请求结构:
```json
{
  "name": "com.xxx.game",
  "label": "XXX Game",
  "description": ""
}
```

返回结构（建议）：
```json
{
  "category_large": "游戏",
  "category_small": "动作类"
}
```

### 3.4 应用使用时长
使用 `UsageStatsManager`：
- 每日使用时长
- 最近使用时间
- 前台时间统计

需要权限：
```
android.permission.PACKAGE_USAGE_STATS
```

用户需手动授予。

---

## 4. 数据存储设计

使用 Room（推荐），三张表：
- apps（应用信息）
- app_category（分类信息）
- usage_stats（使用统计）

详见《数据库设计文档》。

---

## 5. 权限说明
- QUERY_ALL_PACKAGES（Android 11+ 可能需要）
- PACKAGE_USAGE_STATS（必须手动授权）

---

## 6. 数据刷新逻辑
- 安装/卸载广播 → 更新应用列表
- 每次启动应用时自动扫描差异
- 使用时长每天统计一次并写入数据库

---

## 7. 风险与注意事项
- 使用时长权限授予率较低，需要引导动画
- 厂商 ROM 可能对 UsageStats 限制不同
- DeepSeek 分类调用次数可优化（缓存结果）

---

## END
