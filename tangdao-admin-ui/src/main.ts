import Vue from 'vue';
import zh from '@/assets/lang/zh';
import clickout from '@/utils/clickout';
import tooltip from '@/utils/tooltip';
import dialog from '@/utils/dialog';
import VNotifications from 'vue-notification';
import VueI18n from 'vue-i18n';
import eventBus from './event-bus';
import router from './router';
import App from './App.vue';

import "./assets/scss/tangdao.scss"

Vue.use(eventBus);
Vue.use(VueI18n);
Vue.use(VNotifications);
Vue.directive('clickout', clickout);
Vue.directive('tooltip', tooltip);
Vue.directive('dialog', dialog);

const savedLanguage = window.localStorage.getItem('lang');
let language = navigator.language.split('-')[0];
if (!savedLanguage) {
  window.localStorage.setItem('lang', language);
}
language = savedLanguage ? savedLanguage : language;

const i18n = new VueI18n({
  locale: language,
  messages: {
    zh
  },
});

// if (!window.Promise) { window.Promise = Promise; }

Vue.config.productionTip = false;

new Vue({
  i18n,
  router,
  render: (h) => h(App),
}).$mount('#app');
