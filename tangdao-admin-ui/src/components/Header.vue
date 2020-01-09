<template>
  <header class="navbar navbar-header navbar-header-fixed">
    <a class="burger-menu" v-on:click="onCollapsed('navbar-nav-show')">
      <MenuIcon />
    </a>
    <a class="burger-menu d-none">
      <ArrowLeftIcon />
    </a>
    <div class="navbar-brand">
      <a class="td-logo">
        Vuejs
        <span>.org</span>
      </a>
    </div>
    <!-- navbar-brand -->
    <div ref="navbarMenu" class="navbar-menu-wrapper">
      <div class="navbar-menu-header">
        <a class="td-logo">
          Vuejs
          <span>.org</span>
        </a>
        <a id="mainMenuClose" v-on:click="onCollapsed('ui')">
          <XIcon />
        </a>
      </div>
      <!-- navbar-menu-header -->
      <ul class="nav navbar-menu">
        <li class="nav-label pd-l-20 pd-lg-l-25 d-lg-none">Main Navigation</li>
        <li class="nav-item active">
          <a class="nav-link">
            <BellIcon />首页
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <BellIcon />模拟考试
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <BellIcon />2020
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">
            <BellIcon />社区
          </a>
        </li>
      </ul>
    </div>
    <!-- navbar-menu-wrapper -->
    <div class="navbar-right">
      <div class="dropdown dropdown-notification mg-l-15-f">
        <a href="javascript:;" class="dropdown-link new-indicator" data-toggle="dropdown">
          <BellIcon />
          <span>2</span>
        </a>
        <div class="dropdown-menu dropdown-menu-right">
          <div class="dropdown-header">通知</div>
          <a href class="dropdown-item">
            薩芬撒旦飛灑發
            <!-- media -->
          </a>
          <div class="dropdown-footer">
            <a href>View all Notifications</a>
          </div>
        </div>
        <!-- dropdown-menu -->
      </div>
      <!-- dropdown -->
    </div>
    <!-- navbar-right -->

    <div class="navbar-search">search</div>
    <!-- navbar-search -->
  </header>
  <!-- navbar -->
</template>
<script lang="ts">
import { Component, Prop, Emit, Vue } from "vue-property-decorator";
import { MenuIcon, BellIcon, ArrowLeftIcon, XIcon } from "vue-feather-icons";
import PerfectScrollbar from "perfect-scrollbar";
@Component({
  components: {
    MenuIcon,
    BellIcon,
    ArrowLeftIcon,
    XIcon
  }
})
export default class UserLayout extends Vue {
  public $refs!: { navbarMenu: HTMLFormElement };
  @Emit("onCollapsed")
  onCollapsed(cla: string) {}
  mounted() {
    var psNavbar = new PerfectScrollbar(this.$refs.navbarMenu, {
      suppressScrollX: true
    });
  }
}
</script>