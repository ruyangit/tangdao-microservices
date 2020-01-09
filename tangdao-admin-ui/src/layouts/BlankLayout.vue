<template>
  <router-view />
</template>
<script lang="ts">
import Vue from "vue";
import { Component, Prop } from "vue-property-decorator";
@Component
export default class BlankLayout extends Vue {}
</script>