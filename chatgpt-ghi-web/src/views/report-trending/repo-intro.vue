<template>
  <div>
    <a-card :style="{ margin: '24px 16px', padding: '24px', background: '#fff' }">
      <a-list :grid="{ gutter: 16, column: 1 }" :data-source="intros">
        <a-list-item slot="renderItem" slot-scope="item" :key="item.id">
          <a-card :title=" '🔥 ' + item.issue" hoverable>
            <markdown-it-vue class="md-body" :content="item.answer" :options="options" />
          </a-card>
        </a-list-item>
      </a-list>
    </a-card>
  </div>
</template>

<script>
import {getIntroApi} from "@/apis/reportTrending";
import MarkdownItVue from 'markdown-it-vue'
import 'markdown-it-vue/dist/markdown-it-vue.css'
export default {
  name: "repo-intro",
  components: {
    MarkdownItVue
  },
  data() {
    return {
      intros: [],
      options: {
        linkAttributes: {
          attrs: {
            target: '_blank',
            rel: 'noopener'
          }
        },
        katex: {
          throwOnError: false,
          errorColor: '#cc0000'
        },
        icons: 'font-awesome',
        githubToc: {
          tocFirstLevel: 2,
          tocLastLevel: 3,
          tocClassName: 'toc',
          anchorLinkSymbol: '',
          anchorLinkSpace: false,
          anchorClassName: 'anchor',
          anchorLinkSymbolClassName: 'octicon octicon-link'
        },
        mermaid: {
          theme: 'default'
        },
        image: {
          hAlign: 'left',
          viewer: true
        }
      }
    }
  },
  mounted() {
    if (this.$route.params.repoId) {
      this.getIntro(this.$route.params.repoId)
    }
  },
  methods: {
    getIntro(repoId) {
      getIntroApi(repoId).then(res => {
        this.intros = res.data
      })
    }
  }
}
</script>

<style scoped>

</style>