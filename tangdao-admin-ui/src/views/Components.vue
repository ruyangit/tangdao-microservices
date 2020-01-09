<template>
  <div class="container">
    <p>Commponents</p>
    <hr class="mg-y-30">
    <TdInput />
    <hr class="mg-y-10">
    <TdInputTextArea />
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { TdInput, TdInputTextArea } from "@/components/input";
@Component({
  components: {
    TdInput,
    TdInputTextArea
  }
})
export default class App extends Vue {

}
</script>