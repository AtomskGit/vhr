<template>
    <div style="width: 500px">
        <el-input
                placeholder="输入关键字进行过滤"
                v-model="filterText">
        </el-input>

        <el-tree
                class="filter-tree"
                :data="deps"
                :props="defaultProps"
                default-expand-all
                :filter-node-method="filterNode"
                ref="tree">
             <span class="custom-tree-node" slot-scope="{ node, data }">
                 <span>{{ node.label }}</span>
                 <span>
                     <el-button
                          type="primary"
                          size="mini"
                          icon="el-icon-plus"
                          style="padding: 4px"
                          @click="() => showAddView(data)">
                    增加
                     </el-button>
                     <el-button
                          type="danger"
                          size="mini"
                          icon="el-icon-delete"
                          style="padding: 4px"
                          @click="() => deleteDep(data)">
                    删除
                     </el-button>
                 </span>
             </span>
        </el-tree>
        <el-dialog
                title="新增部门"
                :visible.sync="dialogVisible"
                width="30%">
            <div>
                <el-tag style="margin-right: 6px;margin-bottom: 7px">上级部门</el-tag>{{parentName}}<br>
                <el-tag>部门名称</el-tag>
                <el-input v-model="dep.name"
                          style="width:208px;margin-left: 5px;margin-bottom: 10px">
                </el-input>
                <br>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addDep">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        methods: {
            deleteDep(data){
                this.$confirm('此操作将永久删除【' + data.name + '】部门, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/department/" + data.id).then(resp => {
                        if (resp) {
                            this.initDeps();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            addDep(){
                this.postRequest("/system/basic/department/",this.dep).then(resp=>{
                    if(resp){
                        this.dep.name=''
                        this.dialogVisible=false
                        this.initDeps()
                    }
                })
            },
            showAddView(data){
                this.dep.parentId=data.id
                this.parentName=data.name
                console.log(this.dep)
                this.dialogVisible=true
            },
            initDeps(){
                this.getRequest("/system/basic/department/").then(resp=>{
                    if(resp){
                        this.deps=resp
                    }
                })
            },
            filterNode(value, data) {
                if (!value) return true;
                return data.name.indexOf(value) !== -1;
            }
        },
        data() {
            return {
                filterText: '',
                deps:[],
                defaultProps: {
                    children: 'children',
                    label: 'name'
                },
                dep:{
                    name:'',
                    parentId:''
                },
                parentName:'',
                dialogVisible:false
            }
        },
        watch: {
            filterText(val) {
                this.$refs.tree.filter(val);
            }
        },
        mounted(){
            this.initDeps()
        }
    }
</script>

<style scoped>
    .custom-tree-node {
        display: flex;
        justify-content: space-between;
        width: 100%;
    }
</style>
