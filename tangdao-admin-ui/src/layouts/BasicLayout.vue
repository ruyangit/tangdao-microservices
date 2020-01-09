<template>
  <div :class="`td-admin ${navbarCla}`">
    <Header @onCollapsed="collapsed" />
    <div class="td-admin-wrapper">
      <div class="td-admin-navleft">
        <Navleft />
      </div>
      <div class="td-admin-sidebar">二级导航</div>
      <div class="td-admin-content">
        <router-view />
      </div>
    </div>
    <div class="backdrop" v-on:click="collapsed('ui')"></div>
  </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import Header from "@/components/Header.vue";
import Navleft from "@/components/Navleft.vue";
import "../assets/scss/tangdao-admin-ui.scss";
@Component({
  components: {
    Header,
    Navleft
  }
})
export default class BasicLayout extends Vue {
  public navbarCla: string = "ui";
  public collapsed(navbarCla: string) {
    this.navbarCla = navbarCla;
  }
}
</script>