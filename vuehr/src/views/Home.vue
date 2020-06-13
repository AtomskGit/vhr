<template>
    <el-container>
        <el-header class="homeHeader">
            <div class="title">人事管理系统</div>
            <el-dropdown class="userInfo" @command="commandHandler">
                  <span class="el-dropdown-link">
                    {{user.name}}<i><img :src="user.userface" alt=""></i>
                  </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                    <el-dropdown-item command="setting">设置</el-dropdown-item>
                    <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </el-header>
        <el-container>
            <el-aside width="200px">
                    <el-menu router unique-opened>
                        <el-submenu :index="index+''" v-for="(item,index) in routes"
                                    :key="index">
                            <template slot="title">
                                <i style="color: #214e7d;margin-right: 5px"
                                   :class="item.iconCls"></i>
                                <span>{{item.name}}</span>
                            </template>
                            <el-menu-item :index="child.path"
                                          v-for="(child,indexj) in item.children"
                                          :key="indexj">
                                {{child.name}}
                            </el-menu-item>
                        </el-submenu>
                    </el-menu>
            </el-aside>
            <el-main>
                <el-breadcrumb separator-class="el-icon-arrow-right"
                               v-if="this.$router.currentRoute.path!=='/home'">
                    <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
                </el-breadcrumb>
                <div class="homeWelcome" v-if="this.$router.currentRoute.path==='/home'">
                    欢迎来到人事管理系统！
                </div>
                <router-view style="padding-top: 10px" />
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    export default {
        name: "Home",
        data(){
            return{
                user: JSON.parse(window.sessionStorage.getItem("user"))
            }
        },
        computed:{
          routes(){
              return this.$store.state.routes
          }
        },
        methods:{
            commandHandler(cmd){
                if(cmd==='logout'){
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest('/logout')
                        window.sessionStorage.removeItem("user")
                        this.$store.commit('initRoutes',[])
                        this.$message({
                            type: 'success',
                            message: '成功注销，已跳转到登录页面'
                        });
                        this.$router.replace('/')
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消注销'
                        });
                    });
                }
            }
        }
    }
</script>

<style scoped>
    .homeHeader {
        background-color: #214e7d;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 0px 15px;
        box-sizing: border-box;
    }

    .homeWelcome {
        text-align: center;
        font-size: 30px;
        font-family: 微软雅黑;
        color: #214e7d;
        padding-top: 50px;
    }

    .homeHeader .title {
        font-size: 30px;
        font-family: 华文行楷;
        color: #ffffff
    }
    .homeHeader .userInfo {
        cursor: pointer;
    }
    .el-dropdown-link img {
        width: 40px;
        height: 40px;
        margin-left: 10px;
    }

    .el-dropdown-link {
        font-size: 17px;
        color: #ffffff;
        display: flex;
        align-items: center;
    }
</style>
