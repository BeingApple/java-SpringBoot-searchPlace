import axios from 'axios'

const getSearchData = (keyword, page, size) => {
  return axios.get('//localhost:8080/api/search/place', {
    params: {
      'keyword': keyword,
      'page': page,
      'size': size
    }
  })
}

const getHistoryData = (page, size) => {
  return axios.get('//localhost:8080/api/history', {
    params: {
      'page': page,
      'size': size
    }
  })
}
const getPopularData = () => {
  return axios.get('//localhost:8080/api/search/popular')
}

export default {
  async search (keyword, page, size) {
    try {
      const searchData = await getSearchData(keyword, page, size).then(function (response) { return response }).catch(function (error) { return error.response })
      return searchData
    } catch (err) {
      console.log(err)
    }
  },
  async history (page, size) {
    try {
      const historyData = await getHistoryData(page, size).then(function (response) { return response }).catch(function (error) { return error.response })
      return historyData
    } catch (err) {
      console.log(err)
    }
  },
  async popular () {
    try {
      const popularData = await getPopularData().then(function (response) { return response }).catch(function (error) { return error.response })
      return popularData
    } catch (err) {
      console.log(err)
    }
  }
}
