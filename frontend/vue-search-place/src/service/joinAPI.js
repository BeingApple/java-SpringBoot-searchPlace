import axios from 'axios'

const getJoin = (userId, userPassword, check) => {
  return axios.post('//localhost:8080/api/join', {
    'userId': userId,
    'userPassword': userPassword,
    'check': check
  })
}

export default {
  async join (userId, userPassword, check) {
    try {
      const getJoinPromise = await getJoin(userId, userPassword, check)
      const joinResponse = await Promise.all(getJoinPromise)
      return joinResponse
    } catch (err) {
      console.log(err.response)
    }
  }
}
