import Vue from 'vue';
import zh from '@/assets/lang/zh';
import VueI18n from 'vue-i18n';
import eventBus from './event-bus';
import router from './router';
// import components from './components';
import App from './App.vue';


Vue.use(eventBus);
Vue.use(VueI18n);

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

if (!window.Promise) { window.Promise = Promise; }

Vue.config.productionTip = false;

new Vue({
  i18n,
  router,
  render: (h) => h(App),
}).$mount('#app');
