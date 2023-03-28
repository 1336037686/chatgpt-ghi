<template>
  <div>
    <a-layout id="components-layout-demo-custom-trigger" :style="{ height: windowHeight + 'px' }">
      <a-layout-sider :trigger="null" >
        <div class="logo" >
          GitHub Report Trending
        </div>
        <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
          <a-menu-item :key="item.id" v-for="item in records" @click="recordClick(item.id)">
            <a-icon type="github" />
            <span>{{item.createTime}}</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout>
        <a-layout-header style="background: #fff; padding: 0">
          <span class="header-title"> 🍁 ChatGPT 自动分析每日热门项目</span>
        </a-layout-header>
        <router-view :key="$route.path + $route.query.t" />
      </a-layout>
    </a-layout>
  </div>
</template>

<script>
import {getRecordApi} from '@/apis/reportTrending/index'
export default {
  name: "report-trending",
  mounted() {
    let that = this;
    // 把window.onresize事件挂在到mounted函数上
    window.onresize = () => {
      return (() => {
        window.fullHeight = document.documentElement.clientHeight;
        that.windowHeight = window.fullHeight;  // 高
      })()
    }
    this.getRecord()
  },
  data() {
    return {
      windowHeight: document.documentElement.clientHeight,   //实时屏幕高度
      records: []
    }
  },
  methods: {
    getRecord() {
      this.records = []
      getRecordApi().then(res => {
        this.records = res.data
        if (this.records && this.records.length > 0) this.recordClick(this.records[0].id)
      })
    },
    recordClick(recordId) {
      this.$router.push({
        name: 'repo-list',
        query:{
          t: Date.now(),
        },
        params: {
          recordId
        }
      })
    }
  }
}
</script>

<style scoped>
  .header-title {
    font-weight: bold;
    margin-left: 20px;
    text-align: center;
  }

  #components-layout-demo-custom-trigger .trigger {
    font-size: 18px;
    line-height: 64px;
    padding: 0 24px;
    cursor: pointer;
    transition: color 0.3s;
  }

  #components-layout-demo-custom-trigger .trigger:hover {
    color: #1890ff;
  }

  #components-layout-demo-custom-trigger .logo {
    height: 32px;
    margin: 10px;
    color: white;
    text-align: center;
    line-height: 32px;
    font-size: 15px;
    font-weight: bold;
  }
</style>