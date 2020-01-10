<template>
  <div
    class="modal fade effect-scale modal-backdrop show"
    :id="id"
    tabindex="-1"
    role="dialog"
    aria-hidden="true"
    :style="`${show?'display: block':''}`"
    v-on:click="show=!show"
  >
    <div class="modal-dialog modal-dialog-centered show" role="document" v-on:click="show=!show">
      <div class="modal-content">
        <div class="modal-body pd-20 pd-sm-30">
          <button
            type="button"
            class="close pos-absolute t-15 r-20"
            data-dismiss="modal"
            aria-label="Close"
            v-on:click="show=!show"
          >
            <span aria-hidden="true">&times;</span>
          </button>
          <slot></slot>
        </div>
        <div class="modal-footer">
          <slot name="footer"></slot>
        </div>
        <!-- modal-footer -->
      </div>
      <!-- modal-content -->
    </div>
    <!-- modal-dialog -->
  </div>
  <!-- modal -->
</template>
<script lang="ts">
import { Component, Prop, Watch, Vue } from "vue-property-decorator";

@Component({})
export default class TdDialog extends Vue {
  @Prop() private id: any;
  @Prop({ default: false }) public visable!: boolean;
  private show: boolean = this.visable;
  mounted() {
    document.body.appendChild(this.$el);
  }
  //   @Watch("visable", { deep: true })
  //   private visableChanged(newVal: any, oldVal: any): void {}
}
</script>