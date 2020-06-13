<template>
    <div>
        <div>
            <el-input
                    size="small"
                    style="width: 300px"
                    placeholder="添加职称..."
                    prefix-icon="el-icon-plus"
                    v-loading="loading"
                    element-loading-text="正在加载..."
                    element-loading-spinner="el-icon-loading"
                    element-loading-background="rgba(0, 0, 0, 0.8)"
                    v-model="jl.name"
                    @keydown.enter.native="addJobLevel"
            >
            </el-input>
            <el-select v-model="jl.titleLevel" placeholder="职称等级"
                       size="small" style="margin-right: 5px;margin-left: 5px">
                <el-option
                        v-for="item in titleLevels"
                        :key="item"
                        :label="item"
                        :value="item">
                </el-option>
            </el-select>
            <el-button icon="el-icon-plus" type="primary"
                       @click="addJobLevel">添加</el-button>
            <el-button icon="el-icon-delete" type="danger"
                       :disabled="multipleSelection.length===0"
                       @click="deleteMany">批量删除</el-button>
        </div>
        <div style="margin-top: 10px;">
            <el-table
                    :data="jls"
                    stripe
                    border
                    @selection-change="handleSelectionChange"
                    style="width: 100%">
                <el-table-column type="selection" align="center" width="55"/>
                <el-table-column
                        prop="id"
                        label="编号"
                        align="center"
                        width="55">
                </el-table-column>
                <el-table-column
                        prop="name"
                        label="职称"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="titleLevel"
                        label="职称等级"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="创建时间"
                        align="center"
                        width="180">
                </el-table-column>
                <el-table-column label="是否启用" width="100" align="center">
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.enabled">已启用</el-tag>
                        <el-tag type="danger" v-else>未启用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="200" align="center">
                    <template slot-scope="scope">
                        <el-button
                                size="mini"
                                @click="showEditView(scope.$index, scope.row)">编辑
                        </el-button>
                        <el-button
                                size="mini"
                                type="danger"
                                @click="handleDelete(scope.$index, scope.row)">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog
                title="修改职称"
                :visible.sync="dialogVisible"
                class="updateJL"
                width="30%">
            <div style="margin-left: 10%">
                <el-tag>职称名称</el-tag>
                <el-input v-model="updateJl.name" size="small"
                          style="width:208px;margin-left: 5px;margin-bottom: 10px">
                </el-input>
                <br>
                <el-tag>职称等级</el-tag>
                <el-select v-model="updateJl.titleLevel" size="small"
                           style="margin-left: 5px;margin-bottom: 10px">
                    <el-option
                            v-for="item in titleLevels"
                            :key="item"
                            :label="item"
                            :value="item">
                    </el-option>
                </el-select>
                <br>
                <el-tag>是否启用</el-tag>
                <el-switch style="margin-left: 10px"
                        v-model="updateJl.enabled" active-text="启用" inactive-text="禁用">
                </el-switch>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible=false">取 消</el-button>
                <el-button type="primary" @click="updateJobLevel">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                jl: {
                    name: '',
                    titleLevel: '',
                    enabled:false,
                },
                jls: Array,
                loading: false,
                dialogVisible: false,
                updateJl: {
                    name: '',
                    titleLevel: '',
                    enabled:Boolean
                },
                multipleSelection:[],
                titleLevels: [
                    '正高级',
                    '副高级',
                    '中级',
                    '初级',
                    '员级',
                ]
            }
        },
        methods: {
            deleteMany(){
                this.$confirm('此操作将永久删除【' + this.multipleSelection.length + '】条记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    let ids = '?';
                    this.multipleSelection.forEach(item => {
                        ids += 'ids=' + item.id + '&';
                    })
                    this.deleteRequest("/system/basic/jobLevel/" + ids).then(resp => {
                        if (resp) {
                            this.initJobLevels();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            handleSelectionChange(val){
                this.multipleSelection=val
            },
            updateJobLevel() {
                this.putRequest("/system/basic/jobLevel/",this.updateJl).then(resp=>{
                    if(resp){
                        this.initJobLevels()
                        this.dialogVisible=false
                    }
                })
            },
            showEditView(index, data) {
                Object.assign(this.updateJl, data)
                this.dialogVisible = true
            },
            handleDelete(index, data) {
                this.$confirm('此操作将永久删除【' + data.name + '】职称, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/system/basic/jobLevel/" + data.id).then(resp => {
                        if (resp) {
                            this.initJobLevels();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            addJobLevel() {
                if (this.jl.name && this.jl.titleLevel) {
                    this.postRequest("/system/basic/jobLevel/", this.jl).then(resp => {
                        if (resp) {
                            this.initJobLevels()
                            this.jl.name = ''
                            this.jl.titleLevel = ''
                        }
                    })
                }else {
                    this.$message.error("职称名或者职称等级不能为空！")
                }
            },
            initJobLevels() {
                this.loading = true
                this.getRequest("/system/basic/jobLevel/").then(resp => {
                    this.loading = false
                    this.jls = resp
                })
            }
        },
        mounted() {
            this.initJobLevels()
        }
    }
</script>

<style scoped>
</style>
