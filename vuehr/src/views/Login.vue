<template>
    <el-form :model="loginForm" :rules="rules" ref="loginForm"
             class="loginContainer">
        <h3 class="loginTitle">系统登录</h3>
        <el-form-item prop="username">
            <el-input type="text" size="lager" style="font-size: 18px" v-model="loginForm.username" auto-complete="false"
            placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password">
            <el-input type="password" size="lager" style="font-size: 18px"  v-model="loginForm.password" auto-complete="false"
            placeholder="请输入密码" @keydown.enter.native="submitLogin"></el-input>
        </el-form-item>
        <el-button type="primary" size="medium" style="width: 100%;margin-top: 20px;font-size: 18px" @click="submitLogin">登录</el-button>
    </el-form>
</template>

<script>

    export default {
        data() {
            return {
                loginForm: {
                    username: 'atomsk',
                    password: '123',
                },
                rules:{
                    username:[{ required: true, message: '请输入用户名', trigger: 'blur' }],
                    password:[{ required: true, message: '请输入密码', trigger: 'blur' }]
                }
            }
        },
        methods:{
            submitLogin(){
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.postKeyValueRequest('/doLogin',this.loginForm).then(resp=>{
                            if(resp){
                                window.sessionStorage.setItem("user",JSON.stringify(resp.obj))
                                let path = this.$route.query.redirect;
                                this.$router.replace(path==='/'||path===undefined?'/home':path)
                            }
                        })
                    } else {
                        this.$message.error('用户名和密码不能为空');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style scoped>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        height: 250px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }
    .el-form-item__content{
        display: flex;
        align-items: center;
    }
</style>
