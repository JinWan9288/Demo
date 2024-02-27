<template>
  <div class="home">

    <el-container style="height: 500px; border: 2px solid #eee">
      <el-aside width="200px" style=" height: 600px ; background-color: rgb(238, 241, 246) ">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100% " background-color="rgb(48,65,86)"
        text-color="#fff" active-text-color="#ffd04b"  :collapse-transition="false"
        box-shadow:2px  >

          <div style="height: 60px; line-height: 60px;text-align: center">
            <img src="../assets/logo.png" style="width: 20px; position: relative">
            <b style="color: azure" v-show="LogoTextShow">后台管理系统</b>
          </div>

          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>
              <span slot="title">导航一</span></template>
            <el-menu-item-group>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <div>
          <el-input style="width: 250px" placeholder="请输入用户姓名" suffix-icon="el-icon-search"
                    v-model="username" ></el-input>
          <el-button class="ml-10" type="info" round  @click="load">搜索   <i class="el-icon-loading"></i></el-button>
          <el-input style="width: 250px" class="ml-10" suffix-icon="el-icon-position" placeholder="请输入地址"
                    v-model="address"></el-input>
          <el-button class="ml-10" type="info" round  @click="loadAddr">搜索   <i class="el-icon-loading"></i></el-button>
          <el-input style="width: 250px;margin-bottom: 20px" class="ml-10" suffix-icon="el-icon-message"
                    placeholder="请输入邮箱 " v-model="address"></el-input>
          <el-button class="ml-10" type="info" round  @click="load">搜索   <i class="el-icon-loading"></i></el-button>
          <!-- 点击后就进行分页查询 --> <br/>
          <el-button class="ml-20" type="primary">新增   <i class="el-icon-circle-plus"></i></el-button>
          <el-button class="ml-20" type="danger">删除   <i class="el-icon-remove-outline"></i></el-button>
          <el-button class="ml-20" type="primary">导入   <i class="el-icon-download"></i></el-button>
          <el-button class="ml-20" type="primary">导出   <i class="el-icon-upload2"></i></el-button>

        </div>

        <el-main>
          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
            <el-table-column prop="id" label="id" width="60">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" width="80">
            </el-table-column>
            <el-table-column prop="username" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="60">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="60">
            </el-table-column>
            <el-table-column prop="phone" label="电话号码">
            </el-table-column>
            <el-table-column prop="address" label="地址" >
            </el-table-column>
            <el-table-column prop="email" label="邮箱" width="120">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="160">
            </el-table-column>
            <el-table-column label="操作" width="180">
              <template slot-scope="scope">
                <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑    <i class="el-icon-edit-outline"></i></el-button>
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除    <i class="el-icon-delete-solid"></i></el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-main>
      </el-container>
    </el-container>

    <span class="demonstration">
      <br/>
    </span>
    <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="sizes, prev, pager, next"
        :total="total"
        :address="address">
      <!--绑定后端传来的total-->
    res.total
    </el-pagination>
  </div>
</template>

<script>

import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  data() {

    return {
      tableData: [ ],
      total:0,
      pageNum:1,
      pageSize:2,
      username: "",
      address:"",

      LogoTextShow:true,
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4,
      headerBg: "headerBg",
    }
  },

  created() {
    // 请求分页查询数据 第一个参数 url
    this.load();
  },

  methods:{
    load(){
      // 请求分页查询数据 第一个参数 url
      fetch( "http://localhost:9090/user/page?pageNum= "
          + this.pageNum + "&pageSize=" + this.pageSize + "&username="+ this.username)
          .then(res => res.json() ).then(res => {
        console.log(res);
        this.tableData = res.data;  // 将前端的数据传入后端
        this.total =res.total;
        console.log(res.total);
      });
    },

    loadAddr(){
      // 请求分页查询数据 第一个参数 url
      fetch( "http://localhost:9090/user/pageAddr?pageNum= "
          + this.pageNum + "&pageSize=" + this.pageSize + "&address="+ this.address)
          .then(res => res.json() ).then(res => {
        console.log(res);
        this.tableData = res.data;  // 将前端的数据传入后端
        this.total = res.total;
        console.log(res.total);
      });
    },

    handleSizeChange(pageSize){
      console.log(`每页 ${pageSize} 条`);
      this.pageSize = pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      console.log(`当前页码: ${pageNum}`);
      this.pageNum = pageNum;
      this.load();
    },
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    }
  }
}
</script>
<style>
.headerBg{
  background:azure!important;
}
</style>