import Vue from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue'
import 'ant-design-vue/dist/antd.css'; // or 'ant-design-vue/dist/antd.less'
import router from './router'

Vue.config.productionTip = false;
Vue.use(Antd);

new Vue({
  render: h => h(App),
  router,
}).$mount('#app')
