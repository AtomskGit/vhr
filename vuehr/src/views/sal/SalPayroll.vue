<template>
    <div>
        <div style="display: flex;margin: 10px">
            <el-button type="primary" @click="showDialog">添加账套</el-button>
            <el-button type="success" icon="el-icon-refresh" @click="initSalaries"></el-button>
        </div>
        <div>
            <el-table
                :data="salaries"
                style="width: 100%">
                <el-table-column
                        prop="name"
                        label="账套名称"
                        align="center"
                        width="110">
                </el-table-column>
                <el-table-column
                        prop="basicSalary"
                        label="基本工资"
                        align="center"
                        width="70">
                </el-table-column>
                <el-table-column
                        prop="bonus"
                        label="奖金"
                        align="center"
                        width="65">
                </el-table-column>
                <el-table-column
                        prop="trafficSalary"
                        label="交通补助"
                        align="center"
                        width="70">
                </el-table-column>
                <el-table-column
                        prop="lunchSalary"
                        label="午餐补助"
                        align="center"
                        width="70">
                </el-table-column>
                <el-table-column
                        prop="createDate"
                        label="启用时间"
                        align="center"
                        width="100">
                </el-table-column>
                <el-table-column
                        label="养老金"
                        align="center">
                    <el-table-column
                            prop="pensionBase"
                            label="基数"
                            align="center"
                            width="65">
                    </el-table-column>
                    <el-table-column
                            prop="pensionPer"
                            label="比率"
                            align="center"
                            width="65">
                    </el-table-column>
                </el-table-column>
                <el-table-column
                        label="医疗保险"
                        align="center">
                    <el-table-column
                            prop="medicalBase"
                            label="基数"
                            align="center"
                            width="65">
                    </el-table-column>
                    <el-table-column
                            prop="medicalPer"
                            label="比率"
                            align="center"
                            width="65">
                    </el-table-column>
                </el-table-column>
                <el-table-column
                        label="公积金"
                        align="center">
                    <el-table-column
                            prop="accumulationFundBase"
                            label="基数"
                            align="center"
                            width="65">
                    </el-table-column>
                    <el-table-column
                            prop="accumulationFundPer"
                            label="比率"
                            align="center"
                            width="65">
                    </el-table-column>
                </el-table-column>
                <el-table-column label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button @click="showDialog(scope.row)">编辑</el-button>
                        <el-button @click="deleteSalary(scope.row)" type="danger">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog
                :title="title"
                :visible.sync="dialogVisible"

                width="50%">
            <div style="display: flex;justify-content: space-around;align-items: center">
                <el-steps :active="activeItem" direction="vertical" style="width: 30%">
                    <el-step :title="itemName" v-for="(itemName,index) in salaryItems" :key="index"></el-step>
                </el-steps>
                <el-input v-model="salary[title]"
                          :placeholder="'请输入'+salaryItems[index]+'...'"
                          v-for="(value,title,index) in salary"
                          :key="index" v-show="activeItem===index"
                          style="width: 200px" @keydown.enter.native="nextStep">
                </el-input>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="preStep">{{activeItem===0?'取消':'上一步'}}</el-button>
                <el-button type="primary" @click="nextStep">{{activeItem===10?'完成':'下一步'}}</el-button>
            </span>
        </el-dialog>
    </div>

</template>

<script>
    export default {
        data(){
            return{
                salaries:[],
                title:'',
                dialogVisible:false,
                activeItem: 0,
                salaryItems:[
                    '账套名称',
                    '基本工资',
                    '奖金',
                    '交通补助',
                    '午餐补助',
                    '养老金基数',
                    '养老金比率',
                    '医疗保险基数',
                    '医疗保险比率',
                    '公积金基数',
                    '公积金比率'
                ],
                salary: {
                    name: '',
                    basicSalary: 0,
                    bonus: 0,
                    trafficSalary: 0,
                    lunchSalary: 0,
                    pensionBase: 0,
                    pensionPer: 0,
                    medicalBase: 0,
                    medicalPer: 0,
                    accumulationFundBase: 0,
                    accumulationFundPer: 0
                }
            }
        },
        methods:{
            deleteSalary(data){
                this.$confirm('此操作将永久删除【' + data.name + '】, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.deleteRequest("/salary/payroll/" + data.id).then(resp => {
                        if (resp) {
                            this.initSalaries();
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            updateSalary(){
                this.putRequest("/salary/payroll/",this.salary).then(resp=>{
                    if (resp) {
                        this.initSalaries()
                    }
                })
            },
            addSalary(){
                this.postRequest("/salary/payroll/",this.salary).then(resp=>{
                    if (resp) {
                        this.initSalaries()
                    }
                })
            },
            preStep(){
                if(this.activeItem===0){
                    this.dialogVisible=false
                }else{
                    this.activeItem--
                }
            },
            nextStep(){
                if(this.activeItem!==10){
                    this.activeItem++
                }else {
                    if(this.title==='添加账套'){
                        this.addSalary()
                        this.activeItem=0
                    }else{
                        this.updateSalary()
                        this.activeItem=0
                    }
                    this.dialogVisible=false
                }
            },
            showDialog(data){
                if(data.id){
                    this.title='修改账套'
                    Object.assign(this.salary,data)
                }else{
                    this.title='添加账套'
                    this.emptySalary()
                }
                this.dialogVisible=true
            },
            initSalaries(){
                this.getRequest("/salary/payroll/").then(resp=>{
                    if (resp) {
                        this.salaries=resp
                    }
                })
            },
            emptySalary(){
                this.salary= {
                        name: '',
                        basicSalary: 0,
                        bonus: 0,
                        trafficSalary: 0,
                        lunchSalary: 0,
                        pensionBase: 0,
                        pensionPer: 0,
                        medicalBase: 0,
                        medicalPer: 0,
                        accumulationFundBase: 0,
                        accumulationFundPer: 0
                }
            }
        },
        mounted() {
            this.initSalaries()
        }

    }
</script>

<style scoped>

</style>
