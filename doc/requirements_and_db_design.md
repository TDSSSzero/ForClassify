# Android 应用分类与统计需求文档（含数据库设计）

## 1. 项目背景
用户希望能对手机中所有应用进行分类和管理，并查看每个应用的使用时长与最近打开时间。

---

## 2. 功能需求

### 2.1 应用扫描功能
- 获取所有已安装应用
- 显示应用名、包名、图标
- 可筛选系统应用 / 用户应用

### 2.2 应用分类功能
- 使用 DeepSeek API 自动生成大类、小类
- 用户可以手动调整分类
- 分类信息本地持久化

### 2.3 应用启动功能
- 点击某个包名即可启动对应应用

### 2.4 使用时长统计
需获取：
- 今日使用时长
- 本周使用时长
- 最近一次打开时间（lastTimeUsed）
- 总使用时长（totalTimeInForeground）

### 2.5 搜索 / 排序功能
- 按名称搜索
- 按使用时长排序
- 按分类筛选

---

## 3. 非功能性需求

### 3.1 性能
- 应用列表加载 < 300ms（使用缓存）
- 使用时长统计每日增量更新

### 3.2 兼容性
- 最低 Android 8.0（API 26）
- 兼容国内厂商系统（MIUI、EMUI、Flyme 等）

---

## 4. 数据库设计（Room）

### 4.1 apps 表（应用信息）
| 字段名 | 类型 | 描述 |
|--------|------|------|
| id | INTEGER PK | 主键 |
| package_name | TEXT | 包名 |
| app_name | TEXT | 应用名称 |
| icon_path | TEXT | 图标缓存路径 |
| is_system | INTEGER | 是否系统应用 |
| install_time | INTEGER | 安装时间 |
| update_time | INTEGER | 更新日期 |

---

### 4.2 app_category 表（分类）
| 字段名 | 类型 | 描述 |
|--------|------|------|
| id | INTEGER PK | 主键 |
| package_name | TEXT | 外键，关联 apps 表 |
| category_large | TEXT | 大类 |
| category_small | TEXT | 小类 |
| ai_generated | INTEGER | 是否为 AI 自动生成 |

---

### 4.3 usage_stats 表（使用统计）
| 字段名 | 类型 | 描述 |
|--------|------|------|
| id | INTEGER PK | 主键 |
| package_name | TEXT | 关联 apps 表 |
| date | TEXT | YYYY-MM-DD |
| foreground_time | INTEGER | 前台使用时长（毫秒） |
| last_time_used | INTEGER | 最近使用时间戳 |

---

## 5. 使用流程图
```
首次启动 → 扫描应用 → 存入 apps 表
                ↓
           调用 DeepSeek 分类
                ↓
           存入 app_category 表
                ↓
       用户授予使用时长权限
                ↓
     定时任务刷新 usage_stats
```

---

## END
