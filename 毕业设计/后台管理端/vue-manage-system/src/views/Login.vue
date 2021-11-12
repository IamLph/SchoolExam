<template>
    <div class="login-wrap">
        <div class="ms-login">
            <div class="ms-title">后台管理系统</div>
            <el-form ref="login" label-width="0px" class="ms-content">
                <el-form-item prop="username">
                    <el-input v-model="userId" placeholder="请输入用户名">
                        <template #prepend>
                            <el-button icon="el-icon-user"></el-button>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input type="password" placeholder="请输入密码" v-model="password">
                        <template #prepend>
                            <el-button icon="el-icon-lock"></el-button>
                        </template>
                    </el-input>
                </el-form-item>

                <div class="loginStyle">
                    <div class="teacher" @click="selectStyle(0)">
                        <span :class="loginStyle === 0 ? 't_text':'text'">教师登陆</span>
                    </div>
                    <div class="admin" @click="selectStyle(1)">
                        <span :class="loginStyle === 1 ? 't_text':'text'">管理员登陆</span>
                    </div>
                </div>
<!--                登陆-->
                <div class="login-btn">
                    <el-button type="primary" @click="submitForm()">登录</el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>

<script>
// import { ref, reactive } from "vue";
// import { useStore } from "vuex";
// import { useRouter } from "vue-router";
// import { ElMessage } from "element-plus";
import { LoginTeacher,LoginAdmin } from '../api/api';
export default {
    data () {
        return {
           // 0教师 1管理员
            loginStyle:0,
            userId:'',
            password:'',
        }
    },
    methods:{
        selectStyle(e){
            this.loginStyle = e;
        },
        submitForm(){
            // const router = useRouter();
            let userId = this.userId;
            let password = this.password;
            let that = this;
            if(this.loginStyle == 0){
                // console.log("教师登陆");
                LoginTeacher({
                    id:userId,
                    password: password
                }).then(res=>{
                    // console.log(res);
                    if(res.status == 200){
                        that.$message({
                            message: '登陆成功',
                            type: 'success'
                        });
                        // console.log(res.data)
                    //    登陆成功，存储登陆用户
                        localStorage.setItem('loginUser',JSON.stringify(res.data));
                        localStorage.setItem('loginType',0);
                        // router.push("/dashboard");
                        that.$router.push('/')
                    }else{
                        // alert("登陆失败")
                        that.$message({
                            message: '登陆失败,账号密码错误',
                            type: 'warning'
                        });
                    }
                }).catch(err=>{
                    // console.log(err)
                    that.$message.error("网络异常");
                })
            }else{
                // console.log("管理员登陆");
                LoginAdmin({
                    id:userId,
                    password: password
                }).then(res=>{
                    // console.log(res);
                    if(res.status == 200){
                        that.$message({
                            message: '登陆成功',
                            type: 'success'
                        });
                        //    登陆成功，存储登陆用户
                        localStorage.setItem('loginUser',JSON.stringify(res.data));
                        localStorage.setItem('loginType',1);
                        that.$router.push('/')
                    }else{
                        // alert("登陆失败")
                        that.$message({
                            message: '登陆失败,账号密码错误',
                            type: 'warning'
                        });
                    }
                }).catch(err=>{
                    // console.log(err)
                    that.$message.error("网络异常");
                })
            }
        },
    },
    // setup() {
    //     const router = useRouter();
    //     // const param = reactive({
    //     //     username: "admin",
    //     //     password: "123123",
    //     // });
    //
    //     // const rules = {
    //     //     username: [
    //     //         {
    //     //             required: true,
    //     //             message: "请输入用户名",
    //     //             trigger: "blur",
    //     //         },
    //     //     ],
    //     //     password: [
    //     //         { required: true, message: "请输入密码", trigger: "blur" },
    //     //     ],
    //     // };
    //     // const login = ref(null);
    //     // const submitForm = () => {
    //     //     Login().then(res=>{
    //     //         console.log(res);
    //     //     }).catch(err=>{
    //     //         console.log(err)
    //     //     })
    //     //     // login.value.validate((valid) => {
    //     //     //     if (valid) {
    //     //     //         ElMessage.success("登录成功");
    //     //     //         localStorage.setItem("ms_username", param.username);
    //     //     //         router.push("/");
    //     //     //     } else {
    //     //     //         ElMessage.error("登录成功");
    //     //     //         return false;
    //     //     //     }
    //     //     // });
    //     // };
    //
    //     const store = useStore();
    //     store.commit("clearTags");
    //
    //     return {
    //         // param,
    //         // rules,
    //         // login,
    //         // submitForm,
    //     };
    // },
};
</script>

<style scoped>
.login-wrap {
    position: relative;
    width: 100%;
    height: 100%;
    background-image: url(../assets/img/login-bg.jpg);
    background-size: 100%;
}
.ms-title {
    width: 100%;
    line-height: 50px;
    text-align: center;
    font-size: 20px;
    color: #fff;
    border-bottom: 1px solid #ddd;
}
.ms-login {
    position: absolute;
    left: 50%;
    top: 50%;
    width: 350px;
    margin: -190px 0 0 -175px;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.3);
    overflow: hidden;
}
.ms-content {
    padding: 30px 30px;
}
.login-btn {
    text-align: center;
}
.login-btn button {
    width: 100%;
    height: 36px;
    margin-bottom: 10px;
    margin-top: 10px;
}
.login-tips {
    font-size: 12px;
    line-height: 30px;
    color: #fff;
}
    .loginStyle{
        width: 100%;
        height: 30px;
        /*background-color: #00a854;*/
        line-height: 30px;
        display: flex;
    }
    .teacher{
        float: left;
        width: 50%;
        text-align: center;
    }
    .text{
        font-size: 15px;
    }
    .t_text{
        /*color: red;*/
        font-size: 17px;
        color: #2cf5ff;
        border-bottom: 2px solid #59b8dd;
    }
    .admin{
        float: right;
        width: 50%;
        text-align: center;
    }
</style>
