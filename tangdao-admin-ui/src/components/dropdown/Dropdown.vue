<template>
  <Popper ref="popper" :trigger="trigger" :forceShow="show" tagClassname="dropdown">
    <template v-if="options.length==0">
      <div slot="reference">
        <slot name="reference"></slot>
      </div>
      <slot></slot>
    </template>
    <template v-else>
      <div slot="reference">
        <slot></slot>
      </div>
      <div class="dropdown-menu show">
        <DropdownItem
          v-for="i in searchResult"
          :key="i.name"
          @click.native="handleClick(i)"
          :value="i.name"
        />
      </div>
    </template>
  </Popper>
</template>
<script lang="ts">
import Popper from "../Popper.vue";
import DropdownItem from "./DropdownItem.vue";
import { Component, Prop, PropSync, Ref, Vue } from "vue-property-decorator";

interface Option {
  value: string | number;
  name: string | number;
}

@Component({
  components: {
    Popper,
    DropdownItem
  }
})
export default class TdDropdown extends Vue {
  @Ref("popper") private readonly popper!: any;
  @Prop({ default: "click" }) private trigger!: string;
  @Prop({ default: false }) private show!: boolean;
  @Prop({ default: false }) private search!: boolean;
  @Prop({ default: () => [] }) private options!: Option[];
  private searchString: string = "";
  private get searchResult() {
    return this.options.filter(
      (item: Option) => `${item.name}`.indexOf(this.searchString) !== -1
    );
  }
  private handleClick(i: Option) {
    this.$emit("click", i);
    this.popper.doClose();
  }
}
</script>