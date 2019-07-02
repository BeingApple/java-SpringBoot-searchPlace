import axios from 'axios'

const getAccessToken = (userId, userPassword) => {
  return axios.post('//localhost:8080/api/login', {
    'userId': userId,
    'userPassword': userPassword
  })
}

export default {
  async login (userId, userPassword) {
    try {
      const getAccessTokenPromise = await getAccessToken(userId, userPassword)
      const accessTokenResponse = await Promise.all(getAccessTokenPromise)
      if (accessTokenResponse.status === 200) axios.defaults.headers.common['Authorization'] = accessTokenResponse.message
      return accessTokenResponse
    } catch (err) {
      console.log(err)
    }
  }
}
