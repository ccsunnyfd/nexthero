import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

Vue.prototype.serverUrl = "https://localhost:8080/api";

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
