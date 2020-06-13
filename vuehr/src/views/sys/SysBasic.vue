<template>
    <el-tabs v-model="activeName" >
        <el-tab-pane label="部门管理" name="depmanager"><DepManager/></el-tab-pane>
        <el-tab-pane label="职位管理" name="posmanager"><PosManager/></el-tab-pane>
        <el-tab-pane label="职称管理" name="jlmanager"><JLManager/></el-tab-pane>
<!--
        <el-tab-pane label="奖惩规则" name="rapmanager"><RaPManager/></el-tab-pane>
-->
        <el-tab-pane label="权限组" name="rolemanger"><RoleManager/></el-tab-pane>
    </el-tabs>
</template>

<script>
    import DepManager from "@/components/sys/basic/DepManager";
    import PosManager from "@/components/sys/basic/PosManager";
    import JLManager from "@/components/sys/basic/JLManager";
    //import RaPManager from "@/components/sys/basic/RaPManager";
    import RoleManager from "@/components/sys/basic/RoleManager";

    export default {
        data() {
            return {
                activeName: 'depmanager'
            };
        },
        methods: {
            handleClick(tab, event) {
                console.log(tab, event);
            }
        },
        components:{DepManager,PosManager,JLManager,RoleManager}
    };
</script>

<style scoped>

</style>
