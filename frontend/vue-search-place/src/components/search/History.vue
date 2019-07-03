<template>
  <common>
    <h2 class="mt-2">검색 내역</h2>
    <p class="lead">
      <b-list-group class="mt-2">
        <b-list-group-item v-if="(!historyData.content || historyData.content <= 0)" >검색 결과가 없습니다.</b-list-group-item>
        <b-list-group-item v-for="item in historyData.content" v-bind:key="item.id" >
          <p>{{item.keyword}}</p>
          <span>{{item.createdDate | moment("YYYY년 M월 d일 H:m:s ")}}</span>
        </b-list-group-item>
      </b-list-group>
    </p>

    <nav aria-label="Page navigation example" v-if="(historyData.content && historyData.content > 0)">
      <ul class="pagination">
        <li class="page-item">
          <a class="page-link" v-on:click.prevent="pageMove(1)" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>

        <li class="page-item" v-for="pageNumber in pageList" v-bind:key="pageNumber" ><a class="page-link" v-on:click.prevent="pageMove(pageNumber)">{{pageNumber}}</a></li>

        <li class="page-item">
          <a class="page-link" v-on:click.prevent="pageMove(pageSize)" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </common>
</template>

<script>
import service from '@/service'

export default {
  name: 'History',
  data () {
    return {
      page: 1,
      size: 15,
      perPage: 5,
      pageSize: 0,
      historyData: {}
    }
  },
  methods: {
    async history () {
      try {
        let historyResult = await service.history(this.page, this.size)
        if (historyResult.status === 200) {
          this.historyData = historyResult.data
          this.pageSize = historyResult.data.totalPages
        }
      } catch (err) {
        console.log(err)
      }
    },
    pageMove (pageNum) {
      this.page = pageNum
      this.history()
    }
  },
  created () {
    this.historyData = this.history()
  },
  computed: {
    pageList: function () {
      let list = []
      let divide = Math.floor(this.perPage / 2)

      if (this.pageSize > this.perPage) {
        if (this.page <= (divide + 1)) {
          for (let i = 1; i <= this.perPage; i++) list.push(i)
        } else if (this.page > (this.pageSize - divide)) {
          for (let i = this.pageSize - (this.perPage - 1); i <= this.pageSize; i++) list.push(i)
        } else {
          for (let i = this.page - divide; i <= this.page + divide; i++) list.push(i)
        }
      } else {
        for (let i = 1; i <= this.pageSize; i++) list.push(i)
      }
      return list
    }
  }
}
</script>

<style scoped>
</style>
