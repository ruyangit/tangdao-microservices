<template>
  <a href="javascript:;" class="dropdown-item" :class="{'active': (value === active && active)}" v-html="value"></a>
</template>
<script lang="ts">
import Vue from "vue";
import { Component, Prop } from "vue-property-decorator";
@Component({})
export default class RkDropdownItem extends Vue {
  @Prop() private active!: string | number;
  @Prop() private value!: string | number;
}
</script>