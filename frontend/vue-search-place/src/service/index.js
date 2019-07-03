import loginAPI from './loginAPI'
import joinAPI from './joinAPI'
import searchAPI from './searchAPI'

export default {
  async login (userId, userPassword) {
    try {
      const loginResponse = await loginAPI.login(userId, userPassword)
      return loginResponse
    } catch (err) {
      console.log(err)
    }
  },
  async join (userName, userId, userPassword, userPasswordCheck) {
    try {
      const joinResponse = await joinAPI.join(userName, userId, userPassword, userPasswordCheck)
      return joinResponse
    } catch (err) {
      console.log(err)
    }
  },
  async search (keyword, page, size) {
    try {
      const searchResponse = await searchAPI.search(keyword, page, size)
      return searchResponse
    } catch (err) {
      console.log(err)
    }
  },
  async history () {
    try {
      const historyResponse = await searchAPI.history()
      return historyResponse
    } catch (err) {
      console.log(err)
    }
  },
  async popular () {
    try {
      const popularResponse = await searchAPI.popular()
      return popularResponse
    } catch (err) {
      console.log(err)
    }
  }
}
