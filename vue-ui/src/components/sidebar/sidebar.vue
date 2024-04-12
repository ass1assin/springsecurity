<template>
  <el-row  class="full-height">
    <el-row class="top-bar">
      <el-col :span="24">
        <div class="system-name">
          <span>Security系统</span>
        </div>
      </el-col>
    </el-row >
    <el-col class="el-col-full">
      <el-menu
          style="height: 100%;"
          :default-active="activeMenu"
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          @open="handleOpen"
          @close="handleClose"
          @select="handleMenuSelect"
          active-text-color="#ffd04b">


        <el-menu-item v-for="menuItem in menuItems" :key="menuItem.index" :index="menuItem.index" @click="handleMenuSelect2(menuItem.path)">
          <i class="el-icon-menu"></i>
          <span slot="title">{{ menuItem.index }}</span>
        </el-menu-item>
      </el-menu>

    </el-col>
  </el-row>


</template>

<script>
import { bus } from "@/main";
import router from "@/router";
export default {
name: "sidebar",
  data(){
  return{
    activeMenu: "首页",
    userRole: '',
    menuItems: [
      { index: '首页', path: '/home' },
      { index: '预约管理', path: '/reservation' },
      { index: '课程管理', path: '/course' },
    ] // 存放从后端获取的菜单数据
  }
  },
  created() {

  },
  methods: {
    handleMenuSelect(index) {
    },
    handleMenuSelect2(path){

    },
    handleOpen(key, keyPath) {
      this.closeOtherSubmenus(key);
    },
    handleClose(key, keyPath) {

    },

    // 有些问题
    closeOtherSubmenus(currentIndex) {
      // Close other submenus except the current one
      this.$refs.menuItems.forEach((submenu) => {
        if (submenu.index !== currentIndex) {
          submenu.collapse();
        }
      });
    },
  }
}
</script>

<style scoped>

.full-height {
    position: fixed; /* 固定在屏幕上 */
    height: 100vh; /* 占据整个视口高度 */
    display: flex;
    /*指定了flex容器的子元素应该沿着垂直方向从上到下排列*/
    flex-direction: column;
    overflow: hidden;
}
.el-menu{
  min-width:200px;
  flex: 1;
}

.el-menu .el-submenu .el-menu-item {
  min-width: inherit; /* 或者使用其他你想要的值，或者 unset */
}

.top-bar {
  background-color: #545c64;
  color: #fff;
  height: 90px;
  line-height: 70px;
  padding: 10px 20px;
}

.system-name {
  text-align: center;
  font-size: 25px;
}
.el-col-full {
  flex: 1;
}
</style>