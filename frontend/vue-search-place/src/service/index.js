import loginAPI from './loginAPI'
import joinAPI from './joinAPI'

export default {
  async login (userId, userPassword) {
    try {
      const loginResponse = await loginAPI.login(userId, userPassword)
      return loginResponse
    } catch (err) {
      console.log(err)
    }
  },
  async join (userId, userPassword, check) {
    try {
      const joinResponse = await joinAPI.join(userId, userPassword, check)
      return joinResponse
    } catch (err) {
      console.log(err)
    }
  }
}
