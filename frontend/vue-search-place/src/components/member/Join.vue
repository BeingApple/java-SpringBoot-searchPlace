<template>
  <div>
    <form class="form-signin" v-on:submit.prevent="onSubmit">
      <img class="mb-4" src="/docs/4.3/assets/brand/bootstrap-solid.svg" alt width="72" height="72" />

      <h1 class="text-center h3 mb-3 font-weight-normal">회원가입이 필요합니다.</h1>
      <div class="alert alert-danger" role="alert" v-if="errorState" >{{errorState}}</div>
      <div class="form-group">
        <label for="inputUserName">이름</label>
        <input
          type="text"
          class="form-control"
          id="inputUserName"
          name="userName"
          placeholder="이름"
          v-model="userName"
        />
      </div>
      <div class="form-group">
        <label for="inputUserId">아이디</label>
        <input
          type="text"
          class="form-control"
          id="inputUserId"
          name="userId"
          placeholder="아이디"
          v-model="userId"
        />
      </div>
      <div class="form-group">
        <label for="inputUserPassword">비밀번호</label>
        <input
          type="password"
          class="form-control"
          id="inputUserPassword"
          name="userPassword"
          placeholder="비밀번호"
          v-model="userPassword"
        />
      </div>
      <div class="form-group">
        <label for="inputUserPasswordCheck">비밀번호 확인</label>
        <input
          type="password"
          class="form-control"
          id="inputUserPasswordCheck"
          name="userPasswordCheck"
          placeholder="비밀번호 확인"
          v-model="userPasswordCheck"
        />
      </div>

      <button class="btn btn-lg btn-primary btn-block" type="submit">회원가입</button>

      <p class="text-center mt-5 mb-3 text-muted">&copy;BeingApple</p>
    </form>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'Join',
  data () {
    return {
      userName: '',
      userId: '',
      userPassword: '',
      userPasswordCheck: ''
    }
  },
  methods: {
    ...mapActions(['join']),
    async onSubmit () {
      try {
        let joinResult = await this.join({userId: this.userId, userPassword: this.userPassword, check: this.userPasswordCheck})
        if (joinResult) this.goToPages()
      } catch (err) {
        console.log(err)
      }
    },
    goToPages () {
      this.$router.push({
        name: 'login'
      })
    }
  },
  computed: {
    ...mapGetters({
      errorState: 'getErrorState'
    })
  }
}
</script>

<style scoped>
html,
body {
  height: 100%;
}

body {
  display: -ms-flexbox;
  display: flex;
  -ms-flex-align: center;
  align-items: center;
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #f5f5f5;
}

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: auto;
}
.form-signin .checkbox {
  font-weight: 400;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
