<template>
  <common>
    <div class="card" style="width: 100%;">
      <img src="" class="card-img-top" alt="">
      <div class="card-body">
        <h5 class="card-title">{{detail.place_name}}</h5>
        <p class="card-text">주소 : {{detail.address_name}}</p>
        <p class="card-text">전화번호 : {{detail.phone}}</p>
        <a v-bind:href="'https://map.kakao.com/link/map/' + detail.place_name + ',' + detail.y + ',' + detail.x" target="_blank" class="btn btn-primary">지도 확인하기</a>
      </div>
    </div>
  </common>
</template>

<script>
import store from '@/store'

export default {
  name: 'SearchView',
  data () {
    return {
      detail: store.getters.getSearchData
    }
  }
}
</script>

<style scoped>
</style>
