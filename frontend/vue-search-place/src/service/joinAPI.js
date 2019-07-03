import axios from 'axios'

const getJoin = (userName, userId, userPassword, userPasswordCheck) => {
  return axios.post('//localhost:8080/api/join', {
    'userName': userName,
    'userId': userId,
    'userPassword': userPassword,
    'userPasswordCheck': userPasswordCheck
  })
}

export default {
  async join (userName, userId, userPassword, userPasswordCheck) {
    try {
      const joinResponse = await getJoin(userName, userId, userPassword, userPasswordCheck).then(function (response) { return response }).catch(function (error) { return error.response })
      return joinResponse
    } catch (err) {
      console.log(err)
    }
  }
}
