<template>
  <div>
    <a-card :style="{ margin: '24px 16px', padding: '24px', background: '#fff' }">
      <a-list :grid="{ gutter: 16, column: 4 }" :data-source="repositorys">
        <a-list-item slot="renderItem" slot-scope="item" :key="item.id">
          <a-card :title="item.repoTitle" style="height: 290px" hoverable>
            <a slot="extra" href="#" @click="repoClick(item.id)"> 🌳 快速了解</a>
            <p ><a :href="item.repoUrl" class="text-ellipsis-1">{{item.repoUrl}}</a></p>
            <p>
              <span><b> ⭐ Stars</b>: {{item.stars}}</span>
              <span style="margin-left: 10px"><b> ⚡ Forks</b>: {{item.forks}}</span>
            </p>
            <p > 🍋 语言：{{item.repoLanguage}}</p>
            <p class="text-ellipsis-4"> 🎑 描述：{{item.repoDesc}}</p>
          </a-card>
        </a-list-item>
      </a-list>
    </a-card>
  </div>
</template>

<script>
import {getRepositoryApi} from "@/apis/reportTrending";

export default {
  name: "repo-list",
  data() {
    return {
      repositorys: []
    }
  },
  mounted() {
    if (this.$route.params.recordId) {
      this.getRepository(this.$route.params.recordId)
    }
  },
  methods: {
    getRepository(recordId) {
      getRepositoryApi(recordId).then(res => {
        this.repositorys = res.data
      })
    },
    repoClick(repoId) {
      this.$router.push({
        name: 'repo-intro',
        query:{
          t: Date.now(),
        },
        params: {
          repoId
        }
      })
    }
  }
}
</script>

<style scoped>
  .text-ellipsis-1 {
    overflow:hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 1;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
  .text-ellipsis-4 {
    overflow:hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 4;
    display: -webkit-box;
    -webkit-box-orient: vertical;
  }
</style>