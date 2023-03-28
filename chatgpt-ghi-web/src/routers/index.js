import Vue from 'vue'
import Router from 'vue-router'
import ReportTrending from "@/views/report-trending/index.vue";
import RepoList from "@/views/report-trending/repo-list.vue";
import RepoIntro from "@/views/report-trending/repo-intro.vue";

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'report-trending',
            component: ReportTrending,
            redirect: '/repo-list',
            children: [
                {
                    path: 'repo-list',
                    name: 'repo-list',
                    component: RepoList
                },
                {
                    path: 'repo-intro',
                    name: 'repo-intro',
                    component: RepoIntro
                }
            ]
        }
    ]
})
