<template>
  <common>
    <h2 class="mt-2">인기 검색</h2>
    <p class="lead">
      <b-list-group class="mt-2">
        <b-list-group-item v-if="(!popularData || popularData.length <= 0)" >검색 결과가 없습니다.</b-list-group-item>
        <b-list-group-item v-for="(item, index) in popularData" v-bind:key="item.id" >
          <p>{{index + 1}}위</p>
          <span>{{item.keyword}} {{item.count}}회</span>
        </b-list-group-item>
      </b-list-group>
    </p>
  </common>
</template>

<script>
import service from '@/service'

export default {
  name: 'Popular',
  data () {
    return {
      popularData: {}
    }
  },
  methods: {
    async popular () {
      try {
        let popularResult = await service.popular()
        if (popularResult.status === 200) {
          this.popularData = popularResult.data
        }
      } catch (err) {
        console.log(err)
      }
    }
  },
  created () {
    this.popularData = this.popular()
  }
}
</script>

<style scoped>
</style>
