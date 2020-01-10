<template>
    <div class="modal fade effect-scale show" :id="id" tabindex="-1" role="dialog" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered show" role="document">
        <div class="modal-content">
          <div class="modal-body pd-20 pd-sm-30">
            <button type="button" class="close pos-absolute t-15 r-20" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <slot></slot>
          </div>
          <div class="modal-footer">
              footer
              <slot name="footer"></slot>
          </div><!-- modal-footer -->
        </div><!-- modal-content -->
      </div><!-- modal-dialog -->
    </div><!-- modal -->
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component({})
export default class TdDialog extends Vue {
    @Prop() private id: any;
}
</script>