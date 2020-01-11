<template>
  <div class="td-admin">
    <Header />
    <!-- <div class="td-admin-wrapper">
      <div class="td-admin-navleft">
        <Navleft />
      </div>
      <div class="td-admin-sidebar">二级导航</div>
    <div class="td-admin-content">-->
    <transition class="fade">
      <router-view />
    </transition>
    <!-- </div>
    </div>-->
    <!-- <div ref="backdrop" class="backdrop"></div> -->
  </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import Header from "@/components/Header.vue";
// import Navleft from "@/components/Navleft.vue";
// import { contains, selectAll } from "@/utils/dom";
import "../assets/scss/tangdao-admin-ui.scss";
@Component({
  components: {
    Header
    //   Navleft
  }
})
export default class BasicLayout extends Vue {
  public dropHandler(e: any) {
    e.stopPropagation();
    document.body.removeAttribute("class");
  }
  mounted() {
    const $drop = document.createElement("div");
    $drop.setAttribute("class", "backdrop fade");
    $drop.addEventListener("click", this.dropHandler);
    document.body.appendChild($drop);
    window.onresize = this.dropHandler;
  }
}
</script>