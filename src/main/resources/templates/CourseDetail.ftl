<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>课程详情</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <style>
        .lesson-detail {
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto;
        }

        .section {
            margin-bottom: 40px;
        }

        /* 基本信息样式 */
        .basic-info {
            background: #fff;
            border-radius: 8px;
            padding: 24px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
        }

        .basic-info h1 {
            margin: 0 0 24px 0;
            font-size: 28px;
            color: #303133;
        }

        .info-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 24px;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 8px;
        }

        .time-info {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 20px;
            margin-bottom: 24px;
            padding: 20px;
            background: #f8f9fa;
            border-radius: 8px;
        }

        .info-item {
            display: flex;
            align-items: center;
            color: #606266;
        }

        .info-item i {
            font-size: 18px;
            margin-right: 8px;
            color: #409EFF;
        }

        .info-item .label {
            color: #909399;
            margin-right: 8px;
        }

        .info-item .value {
            font-weight: 500;
            color: #303133;
        }

        .description-section h3 {
            font-size: 18px;
            color: #303133;
            margin: 0 0 16px 0;
        }

        .description-section .description {
            color: #606266;
            line-height: 1.8;
            padding: 16px;
            background: #f8f9fa;
            border-radius: 8px;
            font-size: 14px;
        }

        /* 课程目标样式 */
        .objectives h2 {
            margin-bottom: 20px;
            font-size: 22px;
            color: #303133;
        }

        .objective-card {
            margin-bottom: 15px;
            border: 1px solid #EBEEF5;
            border-radius: 4px;
            background-color: #fff;
            overflow: hidden;
            color: #303133;
            transition: .3s;
            box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
            padding: 20px;
        }

        .objective-header {
            margin-bottom: 10px;
        }

        .objective-content {
            color: #606266;
            line-height: 1.6;
        }

        .el-tag {
            display: inline-block;
            height: 32px;
            padding: 0 10px;
            line-height: 30px;
            font-size: 12px;
            color: #fff;
            border-radius: 4px;
            box-sizing: border-box;
            border: 1px solid transparent;
            white-space: nowrap;
        }

        .el-tag--primary {
            background-color: #409EFF;
            border-color: #409EFF;
        }

        .el-tag--success {
            background-color: #67C23A;
            border-color: #67C23A;
        }

        .el-tag--warning {
            background-color: #E6A23C;
            border-color: #E6A23C;
        }

        .el-tag--info {
            background-color: #909399;
            border-color: #909399;
        }

        /* 课程活动样式 */
        .activities h2 {
            margin-bottom: 20px;
            font-size: 22px;
            color: #303133;
        }

        .activity-card {
            margin-bottom: 15px;
            border: 1px solid #EBEEF5;
            border-radius: 4px;
            background-color: #fff;
            overflow: hidden;
            color: #303133;
            transition: .3s;
            box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
            padding: 20px;
        }

        .activity-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }

        .activity-header h3 {
            margin: 0;
            color: #303133;
        }

        .activity-description {
            margin-bottom: 20px;
            color: #606266;
            line-height: 1.6;
        }

        .activity-steps {
            margin: 20px 0;
        }

        .activity-steps h4 {
            margin: 0 0 15px 0;
            font-size: 16px;
            color: #303133;
        }

        .step-container {
            margin-left: 20px;
            border-left: 2px solid #E4E7ED;
            padding-left: 20px;
        }

        .step-item {
            position: relative;
            padding-bottom: 20px;
        }

        .step-item::before {
            content: "";
            position: absolute;
            left: -29px;
            top: 0;
            width: 16px;
            height: 16px;
            background: #409EFF;
            border-radius: 50%;
        }

        .step-title {
            font-weight: 500;
            color: #303133;
        }

        .step-duration {
            margin-left: 8px;
            color: #909399;
            font-size: 13px;
        }

        .step-description {
            color: #606266;
            line-height: 1.6;
            margin-bottom: 10px;
            margin-top: 5px;
        }

        .step-tips {
            margin: 10px 0;
            padding: 8px 16px;
            background-color: #f4f4f5;
            border-left: 5px solid #909399;
        }

        .step-tips-title {
            font-weight: bold;
            display: flex;
            align-items: center;
        }

        .step-tips-title::before {
            content: "i";
            display: inline-block;
            width: 16px;
            height: 16px;
            line-height: 16px;
            text-align: center;
            border-radius: 50%;
            background-color: #909399;
            color: white;
            margin-right: 8px;
            font-style: italic;
            font-weight: bold;
        }

        .step-key-points {
            margin-top: 10px;
            padding: 10px;
            background: #f8f9fa;
            border-radius: 4px;
        }

        .key-points-title {
            font-weight: 500;
            color: #303133;
            margin-bottom: 8px;
        }

        .step-key-points ul {
            margin: 0;
            padding-left: 20px;
        }

        .step-key-points li {
            color: #606266;
            line-height: 1.6;
            margin-bottom: 4px;
        }

        .step-key-points li:last-child {
            margin-bottom: 0;
        }

        .materials {
            margin-top: 20px;
            padding-top: 20px;
            border-top: 1px solid #ebeef5;
        }

        .materials h4 {
            margin: 0 0 10px 0;
            font-size: 14px;
            color: #909399;
        }

        .material-tag {
            display: inline-block;
            height: 24px;
            padding: 0 8px;
            line-height: 22px;
            font-size: 12px;
            color: #909399;
            border-radius: 4px;
            box-sizing: border-box;
            border: 1px solid #d3d4d6;
            white-space: nowrap;
            margin-right: 8px;
            margin-bottom: 8px;
        }

        .el-timeline {
            margin-top: 20px;
        }

        .el-timeline-item {
            position: relative;
            padding-bottom: 20px;
        }

        .el-timeline-item__timestamp {
            color: #909399;
            line-height: 1;
            font-size: 13px;
            margin-bottom: 8px;
        }
    </style>
</head>
<body>
<div class="lesson-detail">
    <!-- 基本信息部分 -->
    <div class="section basic-info">
        <h1>${course.courseName!''}</h1>

        <!-- 主要信息 -->
        <div class="info-grid">
            <div class="info-item">
                <i class="el-icon-time"></i>
                <span class="label">总时长：</span>
                <span class="value">${course.duration!''}</span>
            </div>

            <div class="info-item">
                <i class="el-icon-notebook-2"></i>
                <span class="label">总节数：</span>
                <span class="value">${course.totalSessions!'0'}节</span>
            </div>

            <div class="info-item">
                <i class="el-icon-user"></i>
                <span class="label">适用年龄：</span>
                <span class="value">${course.ageGroup!''}</span>
            </div>

            <div class="info-item">
                <i class="el-icon-user-solid"></i>
                <span class="label">最多学生数：</span>
                <span class="value">${course.maxStudents!'0'}人</span>
            </div>
        </div>

        <!-- 时间信息 -->
        <div class="time-info">
            <div class="info-item">
                <i class="el-icon-date"></i>
                <span class="label">开始时间：</span>
                <span class="value">${course.startTime!''}</span>
            </div>

            <div class="info-item">
                <i class="el-icon-date"></i>
                <span class="label">结束时间：</span>
                <span class="value">${course.endTime!''}</span>
            </div>

            <div class="info-item">
                <i class="el-icon-refresh"></i>
                <span class="label">更新时间：</span>
                <span class="value">${course.updateTime!''}</span>
            </div>
        </div>

        <!-- 课程描述 -->
        <div class="description-section">
            <h3>课程描述</h3>
            <div class="description">
                ${course.courseDescription!''}
            </div>
        </div>
    </div>

    <!-- 课程目标部分 -->
    <div class="section objectives">
        <h2>课程目标</h2>
        <#if course.objectives?? && course.objectives?size gt 0>
            <#list course.objectives as objective>
                <div class="objective-card">
                    <div class="objective-header">
                        <#assign type = objective.type!'other'>
                        <#assign tagType = 'info'>
                        <#assign label = '其他目标'>

                        <#if type == 'knowledge'>
                            <#assign tagType = 'primary'>
                            <#assign label = '知识目标'>
                        <#elseif type == 'skill'>
                            <#assign tagType = 'success'>
                            <#assign label = '技能目标'>
                        <#elseif type == 'attitude'>
                            <#assign tagType = 'warning'>
                            <#assign label = '态度目标'>
                        </#if>

                        <span class="el-tag el-tag--${tagType}">${label}</span>
                    </div>
                    <div class="objective-content">
                        ${objective.description!''}
                    </div>
                </div>
            </#list>
        <#else>
            <p>暂无课程目标</p>
        </#if>
    </div>

    <!-- 课程活动部分 -->
    <div class="section activities">
        <h2>课程活动</h2>
        <div class="el-timeline">
            <#if course.activities?? && course.activities?size gt 0>
                <#list course.activities as activity>
                    <div class="el-timeline-item">
                        <div class="el-timeline-item__timestamp">预计时长：${activity.duration!''}</div>
                        <div class="activity-card">
                            <div class="activity-header">
                                <h3>${activity.name!''}</h3>
                                <span class="el-tag el-tag--primary">${activity.type!''}</span>
                            </div>

                            <div class="activity-description">
                                ${activity.description!''}
                            </div>

                            <!-- 活动步骤 -->
                            <div class="activity-steps">
                                <h4>活动步骤</h4>
                                <div class="step-container">
                                    <#if activity.steps?? && activity.steps?size gt 0>
                                        <#list activity.steps as step>
                                            <div class="step-item">
                                                <div>
                                                    <span class="step-title">${step.title!''}</span>
                                                    <#if step.duration?? && step.duration != ''>
                                                        <span class="step-duration">(${step.duration})</span>
                                                    </#if>
                                                </div>
                                                <div class="step-description">${step.description!''}</div>

                                                <!-- 步骤提示 -->
                                                <#if step.tips?? && step.tips != ''>
                                                    <div class="step-tips">
                                                        <div class="step-tips-title">教学提示</div>
                                                        ${step.tips}
                                                    </div>
                                                </#if>

                                                <!-- 步骤要点 -->
                                                <#if step.keyPoints?? && step.keyPoints?size gt 0>
                                                    <div class="step-key-points">
                                                        <div class="key-points-title">关键要点：</div>
                                                        <ul>
                                                            <#list step.keyPoints as point>
                                                                <li>${point}</li>
                                                            </#list>
                                                        </ul>
                                                    </div>
                                                </#if>
                                            </div>
                                        </#list>
                                    <#else>
                                        <p>暂无步骤信息</p>
                                    </#if>
                                </div>
                            </div>

                            <#if activity.materialsNeeded?? && activity.materialsNeeded?size gt 0>
                                <div class="materials">
                                    <h4>所需材料：</h4>
                                    <#list activity.materialsNeeded as material>
                                        <span class="material-tag">${material}</span>
                                    </#list>
                                </div>
                            </#if>
                        </div>
                    </div>
                </#list>
            <#else>
                <p>暂无课程活动</p>
            </#if>
        </div>
    </div>
</div>

<script src="https://unpkg.com/vue@2.6.14/dist/vue.js"></script>
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    // FreeMarker模板不需要Vue实例，这部分代码只是为了保持与Element UI的兼容性
    document.addEventListener('DOMContentLoaded', function() {
        console.log('课程详情页面已加载');
    });
</script>
</body>
</html>
