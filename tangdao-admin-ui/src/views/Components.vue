<template>
  <div class="container">
    <notifications group="foo" position="bottom right" />
    <Dialog id="test" :visable="false">
      body1
      <div slot="footer">footer</div>
    </Dialog>
    <Dialog id="test2" :visable="false">
      body2
      <div slot="footer">footer</div>
    </Dialog>
    <p class="mg-t-30">Commponents</p>
    <hr class="mg-y-30" />
    <div class="row">
      <div class="col-md-6">
        <TdInput />
      </div>
      <div class="col-md-12 mg-t-10">
        <TdInputTextArea />
      </div>
      <div class="col-md-3 mg-t-10">
        <Popper ref="popper" trigger="click" :options="{placement:'bottom'}">
          <button
            slot="reference"
            type="button"
            class="btn btn-secondary btn-block"
          >Popover on Bottom</button>
          <div class="popover fade bs-popover-bottom show" role="tooltip">
            <div class="arrow" x-arrow></div>
            <h3 class="popover-header"></h3>
            <div class="popover-body">Vivamus sagittis lacus vel augue laoreet rutrum faucibus.</div>
          </div>
        </Popper>
      </div>
      <div class="col-md-3 mg-t-10">
        <TdDropdown trigger="click">
          <button
            slot="reference"
            type="button"
            class="btn btn-secondary dropdown-toggle"
          >Dropdown Menu</button>
          <div class="dropdown-menu show">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something else here</a>
          </div>
        </TdDropdown>
      </div>
      <div class="col-md-3 mg-t-10">
        <TdDropdown
          trigger="hover"
          :options="[{name:'test1'},{name:'test2'},{name:'test3'}]"
          v-on:click="test"
        >
          <a href="#">Dropdown Menu</a>
        </TdDropdown>
      </div>
      <div class="col-md-3 mg-t-10">
        <div class="input-group mg-b-10">
          <div class="input-group-prepend">
            <span class="input-group-text" id="basic-addon1">@</span>
          </div>
          <input
            type="text"
            class="form-control"
            placeholder="Username"
            aria-label="Username"
            aria-describedby="basic-addon1"
          />
        </div>
      </div>
      <div class="col-md-6 mg-t-10">
        <div class="btn-group" role="group" aria-label="Basic example">
          <button type="button" class="btn btn-success" v-on:click="nofy('success')">Success</button>
          <button type="button" class="btn btn-warning" v-on:click="nofy('warn')">Warning</button>
          <button type="button" class="btn btn-danger" v-on:click="nofy('error')">Danger</button>
          <button type="button" class="btn btn-primary" v-on:click="nofy('primary')">Primary</button>
        </div>
      </div>
      <div class="col-md-6 mg-t-10">
        <div class="btn-group" role="group" aria-label="Basic example">
          <button type="button" class="btn btn-success" v-on:click="textnofy('success')">Success</button>
          <button type="button" class="btn btn-warning" v-on:click="textnofy('warn')">Warning</button>
          <button type="button" class="btn btn-danger" v-on:click="textnofy('error')">Danger</button>
          <button type="button" class="btn btn-primary" v-on:click="textnofy('primary')">Primary</button>
        </div>
      </div>
      <div class="col-md-12 mg-t-10">
        <div class="btn-group" role="group" aria-label="Basic example">
          <button type="button" class="btn btn-primary" v-dialog:test>Modal1</button>
          <button type="button" class="btn btn-warning" v-dialog:test2>Modal2</button>
        </div>
      </div>
      <div class="col-md-3 mg-t-10">1</div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Popper from "@/components/Popper.vue";
import Dialog from "@/components/dialog/Dialog.vue";
import { TdInput, TdInputTextArea } from "@/components/input";
import { TdDropdown, TdDropdownItem } from "@/components/dropdown";
@Component({
  components: {
    Popper,
    Dialog,
    TdInput,
    TdInputTextArea,
    TdDropdown,
    TdDropdownItem
  }
})
export default class App extends Vue {
  public test(e: any) {
    console.log(e);
  }
  public textnofy(type: string) {
    this.$notify({
      group: "foo",
      type: type,
      text: "Hello user! This is a notification!"
    });
  }
  public nofy(type: string) {
    this.$notify({
      group: "foo",
      type: type,
      title: "Important message",
      text: "Hello user! This is a notification1!"
    });
  }
}
</script>