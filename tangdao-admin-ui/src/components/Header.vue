<template>
  <header class="navbar navbar-header navbar-header-fixed">
    <a href class="burger-menu" v-on:click="onMainMenuOpen">
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
        <a href id="mainMenuClose" v-on:click="onMainMenuClose">
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
      <Popper ref="popper" trigger="hover" :options="{placement:'bottom-end'}" tagClassname="dropdown dropdown-notification mg-l-15-f" >
        <a slot="reference" href="javascript:;" class="dropdown-link new-indicator" data-toggle="dropdown">
          <BellIcon />
          <span>2</span>
        </a>
        <div class="dropdown-menu dropdown-menu-right show">
          <div class="dropdown-header">通知</div>
          <a href class="dropdown-item">
            Messager
            <!-- media -->
          </a>
          <div class="dropdown-footer">
            <a href>View all Notifications</a>
          </div>
        </div>
        <!-- dropdown-menu -->
      </Popper>
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
import {
  MenuIcon,
  BellIcon,
  ArrowLeftIcon,
  XIcon,
  LifeBuoyIcon
} from "vue-feather-icons";
import Popper from "@/components/Popper.vue";
import PerfectScrollbar from "perfect-scrollbar";
@Component({
  components: {
    MenuIcon,
    BellIcon,
    ArrowLeftIcon,
    XIcon,
    LifeBuoyIcon,
    Popper
  }
})
export default class TdHeader extends Vue {
  public $refs!: { navbarMenu: HTMLFormElement };
  mounted() {
    var psNavbar = new PerfectScrollbar(this.$refs.navbarMenu, {
      suppressScrollX: true
    });
  }
  onMainMenuOpen(e: any) {
    e.preventDefault();
    document.body.setAttribute("class", "navbar-nav-show");
  }
  onMainMenuClose(e: any) {
    e.preventDefault();
    document.body.removeAttribute("class");
  }
}
</script>