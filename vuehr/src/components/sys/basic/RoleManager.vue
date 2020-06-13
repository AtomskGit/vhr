<template>
    <div>
        <div style="display: flex;">
            <el-input size="small" placeholder="请输入角色英文名" v-model="role.name"
                      style="width: 300px;margin-right: 5px">
                <template slot="prepend">ROLE_</template>
            </el-input>
            <el-input size="small" placeholder="请输入角色中文名" v-model="role.nameZh"
                      style="width: 350px;margin-right: 5px"
                      @keydown.enter.native="addRole"></el-input>
            <el-button type="primary" size="small" icon="el-icon-plus" @click="addRole">添加角色</el-button>
        </div>
        <div>
            <el-collapse v-model="activeName" accordion
                         style="width: 750px;margin-top: 10px"
                         @change="change">
                <el-collapse-item v-for="(r,index) in roles"
                                  :title="r.nameZh"
                                  :key="index"
                                  :name="r.id">
                    <el-card class="box-card">
                        <div slot="header" >
                            <span>可访问资源</span>
                            <el-button style="float: right; padding: 3px 0;color:red;" icon="el-icon-delete"
                                       type="text" size="medium" @click="deleteRole(r)"></el-button>
                        </div>
                        <div>
                            <el-tree
                                    show-checkbox
                                    node-key="id"
                                    ref="tree"
                                    :key="index"
                                    :default-checked-keys="selectedMenus"
                                    :data="menus" :props="props">
                            </el-tree>
                            <div style="display: flex;justify-content: flex-end ">
                                <el-button @click="cancelUpdate">取消修改</el-button>
                                <el-button type="primary" @click="doUpdate(r.id,index)">确认修改</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-collapse-item>
            </el-collapse>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                role: {
                    name: '',
                    nameZh: '',
                },
                roles: [],
                activeName: -1,
                menus:[],
                selectedMenus:[],
                props:{
                    label:'name',
                    children:'children'
                },

            }
        },
        methods: {
            deleteRole(role){
                this.$confirm('此操作将永久删除角色【' + role.nameZh + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/role/"+role.id).then(resp => {
                        if (resp) {
                            this.initRoles();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            addRole(){
                if(this.role.name&&this.role.nameZh){
                    this.postRequest("/system/basic/role/",this.role).then(resp=>{
                        if(resp){
                            this.role.name=''
                            this.role.nameZh=''
                            this.initRoles()
                        }
                    })
                }else{
                    this.$message.error("添加数据不能为空");
                }
            },
            cancelUpdate(){
                this.activeName=-1
            },
            doUpdate(rid,index){
                let tree=this.$refs.tree[index]
                let checkedKeys = tree.getCheckedKeys(true);
                let url='/system/basic/role/?rid='+rid
                checkedKeys.forEach(mid=>{
                    url+="&mids="+mid;
                })
                this.putRequest(url).then(resp=>{
                    if(resp){
                        this.activeName=-1
                    }
                })
            },
            change(rid) {
                if (rid) {
                    this.initMenus()
                    this.initSelectedMenus(rid)
                }
            },
            initSelectedMenus(rid){
                    this.getRequest("/system/basic/role/mids/"+rid).then(resp=>{
                        if(resp){
                            this.selectedMenus=resp
                        }
                    })
            },
            initMenus() {
                this.getRequest("/system/basic/role/menus").then(resp=>{
                    if(resp){
                        this.menus=resp
                    }
                })

            },
            initRoles() {
                this.getRequest("/system/basic/role/").then(resp => {
                    if (resp) {
                        this.roles = resp
                    }
                })
            }
        },
        mounted() {
            this.initRoles();
        }
    }
</script>

<style scoped>

</style>
