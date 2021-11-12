import {createRouter, createWebHashHistory} from "vue-router";
//首页
import Home from "../views/Home.vue";
//系统首页
import Dashboard from "../views/Dashboard.vue";
//基础表格
import Basetable from '../views/BaseTable.vue';
//图表
import Basecharts from '../views/BaseCharts.vue';
//表单
import BaseForm from '../views/BaseForm.vue';
//标签
import Tabs from '../views/Tabs.vue';
//鼓励作者
import Donate from '../views/Donate.vue';
//管理权限
import Permission from "../views/Permission.vue";
//国际化语言
import I18n from '../views/I18n.vue'
//上传插件
import Upload from '../views/Upload.vue'
//自定义图标
import Icon from '../views/Icon.vue'
//没有页面
import Page404 from '../views/404.vue'
//没有权限
import Page403 from '../views/403.vue'
//个人中心
import User from '../views/User.vue'
//富文本编辑器
import Editor from '../views/Editor.vue'
//登陆
import Login from '../views/Login.vue'

//用户管理
import UserInfo from "../pages/UserInfo.vue";
//教师管理
import TeacherInfo from "../pages/TeacherInfo.vue";
//试题管理
import QuestionInfo from "../pages/QuestionInfo.vue";
//发布试卷
import ExamPaper from "../pages/ExamPaper.vue";
//通知管理
import NoticeInfo from "../pages/NoticeInfo.vue";
//学科管理
import CourseInfo from "../pages/CourseInfo.vue";
//成绩批改
import GetExamInfo from "../pages/GetExamInfo.vue";
const routes = [
    {
        path: '/',
        redirect: '/dashboard'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {path: "/dashboard", name: "dashboard",component: Dashboard, meta: {title: '系统首页'}},
            {path: "/table", name: "basetable", component: Basetable, meta: {title: '表格'}},
            {path: "/charts", name: "basecharts", component: Basecharts,meta: {title: '图表'}},
            {path: "/form", name: "baseform", component: BaseForm, meta: {title: '表单'}},
            {path: "/tabs", name: "tabs", component: Tabs, meta: {title: 'tab标签'}},
            {path: "/donate", name: "donate", component: Donate, meta: {title: '鼓励作者'}},
            {path: "/permission", name: "permission", component: Permission, meta: {title: '权限管理', permission: true}},
            {path: "/i18n", name: "i18n", component: I18n,meta: {title: '国际化语言'}},
            {path: "/upload", name: "upload", component: Upload, meta: {title: '上传插件'}},
            {path: "/icon", name: "icon", component: Icon, meta: {title: '自定义图标'}},
            {path: '/404', name: '404', component: Page404, meta: {title: '找不到页面'}},
            {path: '/403', name: '403', component: Page403, meta: {title: '没有权限'}},
            {path: '/user', name: 'user', component: User, meta: {title: '个人中心'}},
            {path: '/editor', name: 'editor', component: Editor, meta: {title: '富文本编辑器'}},
            {path: '/userInfo',name:'userInfo',component:UserInfo,meta:{title: '用户管理'}},
            {path: '/teacherInfo',name:'teacherInfo',component:TeacherInfo,meta:{title: '教师管理'}},
            {path: '/questionInfo',name:'questionInfo',component:QuestionInfo,meta:{title: '试题管理'}},
            {path: '/examPaper',name:'examPaper',component:ExamPaper,meta:{title: '发布考试通知'}},
            {path: '/noticeInfo',name:'noticeInfo',component:NoticeInfo,meta:{title: '通知管理'}},
            {path: '/courseInfo',name:'courseInfo',component:CourseInfo,meta:{title: '学科管理'}},
            {path: '/getExamInfo',name:'getExamInfo',component:GetExamInfo,meta:{title: '学科管理'}},
        ]
    },
    {path: "/login", name: "Login", component: Login,meta: {title: '登录'} }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

// router.beforeEach((to, from, next) => {
//     document.title = `${to.meta.title} | vue-manage-system`;
//     const role = localStorage.getItem('ms_username');
//     if (!role && to.path !== '/login') {
//         next('/login');
//     } else if (to.meta.permission) {
//         // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
//         role === 'admin'
//             ? next()
//             : next('/403');
//     } else {
//         next();
//     }
// });
export default router;
