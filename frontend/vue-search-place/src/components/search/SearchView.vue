<template>
  <common>
    <div class="card" style="width: 100%;">
      <div id="map" class="map">

      </div>
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
  },
  methods: {
    makeLatLng () {
      return new window.daum.maps.LatLng(this.detail.y, this.detail.x)
    },
    makeMap (container, options) {
      return new window.daum.maps.Map(container, options)
    },
    makeMarker (map) {
      // 마커를 생성합니다
      var marker = new window.daum.maps.Marker({
        position: this.makeLatLng()
      })

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map)

      let iwContent = '<div style="padding:5px;"><span style="float:left; width:100%; display:inline-block;">' + this.detail.place_name + '</span><span style="float:left; width:100%; display:inline-block;">' + this.detail.address_name + '</span></div>'
      // 인포윈도우를 생성합니다
      var infowindow = new window.daum.maps.InfoWindow({
        position: this.makeLatLng(),
        content: iwContent
      })

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker)
    }
  },
  mounted () {
    let self = this
    window.daum.maps.load(() => {
      let container = document.getElementById('map') // 지도를 담을 영역의 DOM 레퍼런스
      let options = { // 지도를 생성할 때 필요한 기본 옵션
        center: self.makeLatLng(), // 지도의 중심좌표.
        level: 3 // 지도의 레벨(확대, 축소 정도)
      }

      let map = self.makeMap(container, options)
      this.makeMarker(map)
    })
  }
}
</script>

<style scoped>
.map{width: 100%; height: 400px;}
</style>
