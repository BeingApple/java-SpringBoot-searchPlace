import {ERROR_STATE, IS_AUTH, ACCESS_TOKEN} from './mutation_types'
import api from '../service'

let setErrorState = ({commit}, data) => {
  commit(ERROR_STATE, data)
}

let setIsAuth = ({commit}, data) => {
  commit(IS_AUTH, data)
}

let setAccessToken = ({commit}, data) => {
  commit(ACCESS_TOKEN, data)
}

let processResponse = (store, loginResponse) => {
  let status = loginResponse.response.status
  let responseData = loginResponse.response.data
  switch (status) {
    case 200 :
      setErrorState(store, '')
      setIsAuth(store, true)
      setAccessToken(store, responseData.message)
      break
    case 400 :
      setErrorState(store, '잘못된 아이디 혹은 비밀번호입니다.')
      setIsAuth(store, false)
      setAccessToken(store, '')
      break
  }
}

let processJoinResponse = (store, joinResponse) => {
  let status = joinResponse.response.status
  switch (status) {
    case 201 :
      setErrorState(store, '')
      setIsAuth(store, true)
      break
    case 409 :
      setErrorState(store, '중복된 유저아이디입니다.')
      setIsAuth(store, false)
      break
  }
}

export default {
  async login (store, {userId, userPassword}) {
    let loginResponse = await api.login(userId, userPassword)
    processResponse(store, loginResponse)
    return store.getters.getIsAuth
  },
  async join (store, {userId, userPassword, check}) {
    let joinResponse = await api.join(userId, userPassword, check)
    processJoinResponse(store, joinResponse)
    return store.getters.getIsAuth
  }
}
