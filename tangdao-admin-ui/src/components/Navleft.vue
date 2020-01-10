<template>
  <nav class="nav flex-column">
    <a href class="nav-link active">
      <span>
        <LayoutIcon />
      </span>
    </a>
    <a href class="nav-link">
      <span>
        <GridIcon />
      </span>
    </a>
    <a href class="nav-link">
      <span>
        <LayersIcon />
      </span>
    </a>
    <a href class="nav-link">
      <span v-tooltip:right="{content: '添加快捷菜单', popperCls: ['tooltip-primary']}">
        <PlusIcon />
      </span>
    </a>
  </nav>
</template>
<script lang="ts">
import { Component, Prop, Emit, Vue } from "vue-property-decorator";
import { LayoutIcon, LayersIcon, GridIcon, PlusIcon } from "vue-feather-icons";
@Component({
  components: {
    LayoutIcon,
    LayersIcon,
    GridIcon,
    PlusIcon
  }
})
export default class TdNavleft extends Vue {}
</script>