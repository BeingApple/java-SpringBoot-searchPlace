import Vue from 'vue'
import store from '@/store'
import Router from 'vue-router'
import Join from '@/components/member/Join'
import Login from '@/components/member/Login'

import Search from '@/components/search/Search'
import SearchView from '@/components/search/SearchView'
import History from '@/components/search/History'
import Popular from '@/components/search/Popular'

Vue.use(Router)

const requireAuth = () => (from, to, next) => {
  if (store.getters.getIsAuth) return next()
  next('/login')
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Join',
      component: Join
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/search',
      name: 'Search',
      component: Search,
      beforeEnter: requireAuth
    },
    {
      path: '/search/:id',
      name: 'SearchView',
      component: SearchView,
      beforeEnter: requireAuth
    },
    {
      path: '/history',
      name: 'History',
      component: History,
      beforeEnter: requireAuth
    },
    {
      path: '/popular',
      name: 'Popular',
      component: Popular,
      beforeEnter: requireAuth
    }
  ]
})
