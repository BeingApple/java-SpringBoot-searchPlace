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
      const accessTokenResponse = await getAccessToken(userId, userPassword).then(function (response) { return response }).catch(function (error) { return error.response })
      if (accessTokenResponse.status === 200) axios.defaults.headers.common['Authorization'] = accessTokenResponse.data.message
      return accessTokenResponse
    } catch (err) {
      console.log(err)
    }
  }
}
